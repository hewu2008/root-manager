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

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class k {
	public static final TimeZone a = TimeZone.getTimeZone("GMT");
	private static final SimpleDateFormat b = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);

	public static String a(Context paramContext) {
		try {
			String str = ((TelephonyManager) paramContext
					.getSystemService("phone")).getDeviceId();
			return str;
		} catch (Exception localException) {
		}
		return "unknow";
	}

	public static String a(Context paramContext, String paramString) {
		try {
			PackageInfo localPackageInfo = paramContext.getPackageManager()
					.getPackageInfo(paramString, 0);
			if (localPackageInfo == null)
				return null;
			String str = String.valueOf(localPackageInfo.versionCode);
			return str;
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
		}
		return null;
	}

	public static boolean a(Object paramObject) {
		return (paramObject == null)
				|| ("".equals(paramObject.toString().trim()));
	}

	public static String[] a(Context paramContext, int paramInt) {
		return paramContext.getPackageManager().getPackagesForUid(paramInt);
	}

	public static boolean b(Context paramContext, String paramString) {
		if ((paramString == null) || ("".equals(paramString)))
			return false;
		try {
			paramContext.getPackageManager().getApplicationInfo(paramString,
					8192);
			return true;
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
		}
		return false;
	}
}