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
package Network.Factories;

import java.io.IOException;

import Network.Wrappers.ISocketChannel;
import Pipeline.Configuration.Configuration;
import Request.Processing.IMarshaller;
import Utilities.ILauncher;

/**
 *
 * @author jmillen
 */
public class RequestHandlerFactoryWrapper implements IRequestHandlerFactory
{
    @Override
    public Runnable create(ISocketChannel channel, IMarshaller marshaller, Configuration config, ILauncher launcher) throws IOException
    {
        return RequestHandlerFactory.create(channel, marshaller, config, launcher);
    }
}
