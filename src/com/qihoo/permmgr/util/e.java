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
package com.qihoo.permmgr.util;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.util.Log;

public class e {
	private static boolean a = com.qihoo.permmgr.b.a;
	private static String b = "MyLog";
	private static boolean c = true;

	public static String a() {
		try {
			long l = System.currentTimeMillis();
			Calendar localCalendar = GregorianCalendar.getInstance();
			localCalendar.setTimeInMillis(l);
			String str = "[" + localCalendar.getTime().toLocaleString() + "] 1_";
			return str;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return "";
	}

	public static void a(String paramString) {
		if (a)
			Log.d(b, paramString);
	}

	public static void a(String paramString, File paramFile) {
		if (c) 
			com.qihoo.permmgr.util.c.a(paramString.getBytes(), paramFile);
	}

	public static void b(String paramString) {
		if (a)
			Log.e(b, paramString);
	}

	public static void b(String paramString, File paramFile) {
		if (c)
			a(a() + "1.2.4forzhushou" + "_" + paramString + "\n", paramFile);
	}
}
