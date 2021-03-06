/*
 * Copyright (C) 2017 jmillen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Network.Handlers;

import java.io.IOException;

import Network.Wrappers.ISelector;
import Network.Wrappers.ISelectorKey;
import Network.Wrappers.ISelectorKeys;
import Network.Wrappers.ISocketChannel;
import Protocol.Builders.IRequestBuilder;
import Protocol.Models.HttpContext;
import Protocol.Models.ResponseImpl;
import Protocol.Parsers.ProtocolException;
import Request.Processing.IProcessRequest;
import Request.Processing.ISendResponse;
import Server.IExceptionHandler;
import Utilities.ILauncher;


/**
 *
 * @author jmillen
 */
public class RequestHandler implements Runnable
{
    private final ISelector _selector;
    private final long _timeout;
    private final ILauncher _launcher;
    private final IRequestBuilder _builder;
    private final IProcessRequest _processor;
    private final ISendResponse _responder;
    private final IExceptionHandler _handler;
    
    
    public RequestHandler(ISelector selector, ISocketChannel channel, IRequestBuilder builder, IProcessRequest processor, ISendResponse responder, long timeout, ILauncher launcher, IExceptionHandler handler) throws IOException
    {
        _selector = selector;
        _builder = builder;
        _processor = processor;
        _responder = responder;
        _timeout = timeout;
        _launcher = launcher;
        _handler = handler;
        
        selector.registerForReads(channel);
    }
    
    @Override
    public void run()
    {
        Boolean isFinished = false;
        ISocketChannel channel = null;
        HttpContext context = null;
        
        try
        { 
            while (!isFinished)
            {
                ISelectorKeys keys = _selector.waitForRequests(_timeout);

                if (keys == null)
                {
                    isFinished = true;
                    continue;
                }

                ISelectorKey key = keys.getNext();

                if (key.isReadable())
                {
                    channel = key.getChannel();
                    context = _builder.ProcessRequest(channel);

                    if (context.response().status() == 200)
                    {
                        context = _processor.processRequest(context);
                    }

                    isFinished = true;
                }
                else
                {
                    isFinished = true;
                    key.cancel();
                }
            }
        }
        catch (Exception ex)
        {
            // (try to) send 500
            if (context != null)
            {
                context.response().setStatus(500);
            }
            _handler.HandleException(ex);
        }
        finally
        {
            try
            {
                if (channel != null)
                {
                    if (context != null)
                    {
                    	//System.out.println(context.toString());
                    	//System.out.println(((ResponseImpl)context.response()).toString());
                    	//System.out.println(channel.toString());
                        _responder.sendResponse((ResponseImpl)context.response(), channel);
                    }
                    channel.close();
                }
            }
            catch (ProtocolException | IOException ex)
            {
                _handler.HandleException(ex);
            }
            
            _launcher.threadFinished();
        }
    }
}
