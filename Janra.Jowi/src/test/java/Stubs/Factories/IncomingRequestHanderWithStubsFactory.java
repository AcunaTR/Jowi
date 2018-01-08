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
package java.Stubs.Factories;

import java.Network.SocketServer;
import java.Network.Factories.IRequestHandlerFactory;
import java.Network.Handlers.IncomingRequestHandler;
import java.Request.Processing.IMarshaller;
import java.Request.Processing.ISendResponse;
import java.Server.IConfiguration;
import java.Utilities.ILauncher;
import java.Stubs.Processing.*;

import java.io.IOException;

/**
 *
 * @author jmillen
 */
public class IncomingRequestHanderWithStubsFactory
{
    public static IncomingRequestHandler create(SocketServer socketServer, Integer port, ILauncher launcher, ISendResponse responder) throws IOException
    {
        IMarshaller marshaller = new MarshallerStub();
        IConfiguration config = ConfigurationStubFactory.Create();
        IRequestHandlerFactory factory = new RequestHandlerStubFactory();
        return new IncomingRequestHandler(factory, socketServer, launcher, port, config, marshaller, responder);
    }
}
