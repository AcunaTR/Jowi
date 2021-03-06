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
package Stubs.Factories;

import Network.SocketServer;
import Network.Wrappers.*;
import Stubs.Network.SelectorStub;
import Stubs.Network.ServerSocketStub;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jmillen
 */
public class ServerStubFactory
{
    static public Map<String, Object> Create()
    {
        Map<String, Object> retVal = new HashMap<>();
        
        retVal.put("ServerSocketStub", new ServerSocketStub());
        retVal.put("SelectorStub", new SelectorStub());
        retVal.put("Server", new SocketServer((IServerSocketChannel)retVal.get("ServerSocketStub"),
                                        (ISelector)retVal.get("SelectorStub")));
        return retVal;
    }
}
