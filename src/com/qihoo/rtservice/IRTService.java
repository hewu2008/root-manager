/**
 * Copyright (C) 2014-2015 hewu <hewu2008@gmail.com>
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
package com.qihoo.rtservice;

public abstract interface IRTService {
	public static final String ACTION_RT_EXTRA_PID = "com.qihoo.appstore.rtservice.EXTRA_PID";
	public static final String ACTION_RT_SERVICE_EXIT = "com.qihoo.appstore.rtservice.action.ACTION_RT_SERVICE_EXIT";
	public static final String ACTION_RT_SERVICE_READY = "com.qihoo.appstore.rtservice.action.ACTION_RT_SERVICE_READY";
	public static final String PERMISSION_ACCESS_RTSERVICE = "com.qihoo.appstore.rtservice.ACCESS_RTSERVICE";
	public static final String SERVICE_NAME = "qh_rt_service";
}