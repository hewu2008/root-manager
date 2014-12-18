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

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.qihoo.rtservice.support.RootAppHelper;

public class RTServiceManager implements IRTService {
	public static final boolean DEBUG = true;
	private static final Object a = new Object();
	private static final Object b = new Object();
	private static Boolean c = null;
	private static Object d = new Object();
	private static final Handler e = new Handler(Looper.getMainLooper());
	private static boolean f = false;
	private static Context mContext = null;

	public static void getPermBundle(Context paramContext, Bundle paramBundle) {
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String str4 = "";
		str1 = paramContext.getPackageCodePath();
		str2 = String.format("CLASSPATH=%s", new Object[] { str1 });
		str3 = System.getenv("LD_LIBRARY_PATH");
		str4 = RootAppHelper.getMyLibDir();
		String str5 = String.format(
				"LD_LIBRARY_PATH=/vendor/lib:/system/lib:%s", str4);
		String str6 = String.format(
				"_LD_LIBRARY_PATH=/vendor/lib:/system/lib:%s", str4);
		paramBundle.putString("env_0", str2);
		paramBundle.putString("env_1", str6);
		paramBundle.putString("env_2", str5);
		paramBundle.putString("env_3",
				"PATH=/sbin:/vendor/bin:/system/sbin:/system/bin:/system/xbin");
		paramBundle.putInt("env_num", 3);
		paramBundle.putInt("root_type", 1);
		paramBundle.putInt("arg_num", 5);
		paramBundle.putString("arg_0", "/system/bin/app_process");
		paramBundle.putString("arg_1", "/system/bin");
		paramBundle.putString("arg_2", IRTServiceImpl.class.getName());
		paramBundle.putString("arg_3", "--nice-name=qh_rt_service");
		paramBundle.putString("arg_4", "--application&");
		try {
			str1 = paramContext.getPackageManager().getPackageInfo(
					paramContext.getPackageName(), 0).applicationInfo.publicSourceDir;
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			localNameNotFoundException.printStackTrace();
		}
	}
}
