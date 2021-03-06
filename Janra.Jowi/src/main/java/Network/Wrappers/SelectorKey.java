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
package Network.Wrappers;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 *
 * @author jmillen
 */
public class SelectorKey implements ISelectorKey
{
    private final SelectionKey _key;
    
    public SelectorKey(SelectionKey key)
    {
        _key = key;
    }
    
    @Override
    public ISocketChannel getChannel() throws IOException
    {
        if (!_key.isValid())
        {
            throw new IOException("Selector key is not valid");
        }
        
        SocketChannel socket = (SocketChannel)_key.channel();
        
        return new SocketChannelWrapper(socket);
    }
    
    @Override
    public Boolean isAcceptable()
    {
        return _key.isValid() && _key.isAcceptable();
    }

    @Override
    public Boolean isReadable()
    {
        return _key.isValid() && _key.isReadable();
    }
    
    @Override
    public Boolean isWriteable()
    {
        return _key.isValid() && _key.isWritable();
    }
    
    @Override
    public void cancel()
    {
        _key.cancel();
    }
}
