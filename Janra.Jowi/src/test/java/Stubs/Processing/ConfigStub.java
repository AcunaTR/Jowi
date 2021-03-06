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
package Stubs.Processing;

import Server.IConfiguration;
import Server.IExceptionHandler;
import Server.IPipelineMiddleware;

/**
 *
 * @author jmillen
 */
public class ConfigStub implements IConfiguration
{
    private Integer _maxThreads = 0;
    public Integer MaxUriLength = 1024;
    public String Charset = "ISO-8859-1";
    
    @Override
    public void setTimeout(Integer value)
    {
        
    }

    @Override
    public void addMiddleware(String path, IPipelineMiddleware middleware)
    {
        
    }
    
    @Override
    public long timeout()
    {
        return 500;
    }

    @Override
    public void setMaxThreads(Integer maxThreads)
    {
        _maxThreads = maxThreads;
    }

    @Override
    public Integer maxThreads()
    {
        return _maxThreads;
    }

    @Override
    public void registerExceptionHandler(IExceptionHandler handler)
    {
        // Do nothing
    }

    @Override
    public void setMaxUriLength(Integer maxLength)
    {
        MaxUriLength = maxLength;
    }

    @Override
    public Integer maxUriLength()
    {
        return MaxUriLength;
    }

    @Override
    public void setDefaultCharsetIncoming(String charset)
    {
        Charset = charset;
    }

    @Override
    public String defaultCharset()
    {
        return Charset;
    }
}
