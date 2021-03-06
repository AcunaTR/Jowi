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
package Protocol.Models;

import java.net.URI;
import java.net.URISyntaxException;

import Server.IHeader;

/**
 *
 * @author jmillen
 */
public class HttpRequest implements Cloneable
{
    private final HttpMethod _method;
    private final String _path;
    private final String _version;
    private String _host = "";
    private String _mediaType = "application/octet-stream"; // default (rfc7230 3.1.1.5)
    private String _charset = "ISO-8859-1";
    private Headers _headers;
    private RequestBody _body;
    
    public HttpRequest(HttpMethod method, String path, String version, String charset)
    {
        _method = method;
        // Need to handle absolute form rfc7230 5.3.2
        _path = path;
        _version = version;
        _host = "";
        _headers = new Headers();
        _charset = charset;
    }
    
    public HttpRequest(HttpRequest request)
    {
        _method = request._method;
        _path = request._path;
        _version = request._version;
        _host = request._host;
        _mediaType = request._mediaType;
        _charset = request._charset;
        _headers = new Headers(request._headers);
        _body = request._body == null ? null : new RequestBody(request._body.raw());
    }
    
    public void addHost(IHeader hostHeader) throws URISyntaxException
    {
        URI.create(hostHeader.value());
        
        _host = hostHeader.value();
    }
    
    public void addHeaders(Headers headers)
    {
        _headers = headers;
    }
    
    public void setBody(RequestBody body)
    {
        _body = body;
    }
    
    public void setMediaType(String mediaType)
    {
        _mediaType = mediaType;
    }
    
    public void setCharset(String charset)
    {
        _charset = charset;
    }
    
    public HttpMethod method()
    {
        return _method;
    }
    
    public String path()
    {
        return _path;
    }
    
    public String version()
    {
        return _version;
    }
    
    public String host()
    {
        return _host;
    }
    
    public IHeader header(String name)
    {
        return _headers.get(name);
    }
    
    public RequestBody body()
    {
        return _body;
    }
    
    public String mediaType()
    {
        return _mediaType;
    }
    
    public String charset()
    {
        return _charset;
    }
    
    @Override
    public String toString() {
    	String q = "Http method - " + _method.toString(); 
    	//String w = "\nPath - " + _path;
		//String e = "\nVersion - " + _version;
		//String r = "\nHost - " + _host;
		//String t = "\nMedia type - " + _mediaType;
		//String y = "\nCharset - " + _charset; 
		//String u = "\nHeaders - " + _headers.toString();
		return q;
    }
     
 }
