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
package test.java.Stubs.Factories;

import java.util.HashMap;
import java.util.Map;

import test.java.Stubs.Protocol.ParserStub;

/**
 *
 * @author jmillen
 */
public class ParserStubFactory
{
    static public Map<String, Object> Create()
    {
        Map<String, Object> retVal = new HashMap<>();
        
        retVal.put("ParserStub", new ParserStub());
        return retVal;
    }
}