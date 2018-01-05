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
package test.java.Stubs.Protocol;

import java.util.HashMap;
import java.util.Map;

import main.java.Protocol.Models.Header;
import main.java.Protocol.Models.HttpMethod;
import main.java.Protocol.Models.HttpRequest;
import main.java.Protocol.Parsers.IParser;
import main.java.Protocol.Parsers.ProtocolException;
import main.java.Server.IHeader;

/**
 *
 * @author jmillen
 */
public class ParserStub implements IParser
{
    public String PassedBuffer;
    public Map<String,String> headers = new HashMap<>();
    
    public void addHeaderToReturn(String key, String value)
    {
        headers.put(key, value);
    }
    
    @Override
    public HttpRequest ParseRequestLine(String buffer)
    {
        PassedBuffer = buffer;
        
        return new HttpRequest(HttpMethod.GET, "/my/path", "HTTP/1.1", "UTF-8");
    }

    @Override
    public IHeader ParseHeader(String line) throws ProtocolException
    {
        return Header.create("Host", "my.host.com:80");
    }
    
}