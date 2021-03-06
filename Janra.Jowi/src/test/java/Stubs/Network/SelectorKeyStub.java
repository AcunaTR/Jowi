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
package Stubs.Network;

import java.io.IOException;

import Network.Wrappers.ISelectorKey;
import Network.Wrappers.ISocketChannel;

/**
 *
 * @author jmillen
 */
public class SelectorKeyStub implements ISelectorKey
{
    public Boolean IsAcceptable;
    public Boolean IsReadable;
    public Boolean IsWriteable;
    public Boolean IsCancelled = false;
    public SocketStubComplete SocketStub;
    
    public SelectorKeyStub(Boolean isAcceptable, Boolean isReadable, Boolean isWriteable)
    {
        IsAcceptable = isAcceptable;
        IsReadable = isReadable;
        IsWriteable = isWriteable;
        SocketStub = new SocketStubComplete();
    }
    
    @Override
    public Boolean isAcceptable()
    {
        return IsAcceptable;
    }

    @Override
    public Boolean isReadable()
    {
        return IsReadable;
    }

    @Override
    public Boolean isWriteable()
    {
        return IsWriteable;
    }

    @Override
    public ISocketChannel getChannel() throws IOException
    {
        return SocketStub;
    }

    @Override
    public void cancel()
    {
        IsCancelled = true;
    }
    
}
