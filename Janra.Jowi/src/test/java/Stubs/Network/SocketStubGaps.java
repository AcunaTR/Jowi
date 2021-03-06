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
import java.nio.ByteBuffer;

import Network.Wrappers.ISocketChannel;

/**
 *
 * @author jmillen
 */
public class SocketStubGaps implements ISocketChannel
{
    public Boolean IsNonBlocking = false;
    public Integer NumReads = 0;
    public Integer NumWrites = 0;
    private Integer _bytesToWrite = 0;
    private String bytesToRead = "";
    private Boolean returnData = true;
    
    @Override
    public Integer read(ByteBuffer buffer) throws IOException
    {
        if (!returnData)
        {
            returnData = true;
            return 0;
        }
        NumReads++;
        Integer bytes = buffer.remaining();
        
        String toReturn;
        
        if (bytes < bytesToRead.length())
        {
            toReturn = bytesToRead.substring(0, (buffer.remaining()));
            bytesToRead = bytesToRead.substring(buffer.remaining());
        }
        else
        {
            toReturn = bytesToRead;
            bytesToRead = "";
            bytes = toReturn.length();
        }
        
        buffer.put(toReturn.getBytes("UTF-8"));
        
        returnData = false;
        
        return bytes;
    }

    @Override
    public Integer write(ByteBuffer buffer) throws IOException
    {
        NumWrites++;
        
        for (int i = 0; i <  _bytesToWrite; i++)
        {
            buffer.get();
        }

        return _bytesToWrite;
    }

    @Override
    public void close() throws IOException
    {
        // Do nothing
    }

    @Override
    public void setNonBlocking(Boolean flag) throws IOException
    {
        IsNonBlocking = true;
    }
    
    public void setMessageToRead(String message)
    {
        bytesToRead = message;
    }
    
    public void setBytestoRead(Integer bytes)
    {
        bytesToRead = new String(new char[bytes]).replace("\0", "X");
        bytesToRead = bytesToRead.concat("\r\n");
    }
    
    public void setBytesToWrite(Integer bytes)
    {
        _bytesToWrite = bytes;
    }
}
