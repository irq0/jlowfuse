/*
 * Copyright (c) 2011 Marcel Lauhoff.
 * 
 * This file is part of jlowfuse.
 * 
 * jlowfuse is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * jlowfuse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with jlowfuse.  If not, see <http://www.gnu.org/licenses/>.
 */

package jlowfuse;

import fuse.FuseContext;
import fuse.FuseRequest;
import fuse.SWIGTYPE_p_fuse_req;

public class FuseReq extends SWIGTYPE_p_fuse_req {
	public FuseReq(long ptr) {
		super(ptr, false);
	}

	long getCPtr() {
		return SWIGTYPE_p_fuse_req.getCPtr(this);
	}

	@Override
	public String toString() {
		return "fuse_req_t: ptr=0x" + Long.toHexString(getCPtr(this));
	}

	public FuseContext getContext() {
		return FuseRequest.getContext(this);
	}
}
