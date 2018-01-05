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
package test.java.Stubs.Processing;

import main.java.Protocol.Models.HttpContext;
import main.java.Request.Processing.IProcessRequest;

/**
 *
 * @author jmillen
 */
public class ProcessRequestExceptionStub implements IProcessRequest
{
    @Override
    public HttpContext processRequest(HttpContext context)
    {
        throw new IllegalArgumentException("Test Exception");
    }
}