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
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

import com.qihoo.permmgr.a;

public class g {
	ExecutorService es = Executors.newFixedThreadPool(3);
	private static g sInstance = null;
	private Context g;

	private g(Context paramContext) {
		this.g = paramContext;
	}

	public static g a(Context paramContext) {
		if (sInstance == null)
			sInstance = new g(paramContext);
		return sInstance;
	}

	private static int a(String paramString) {
		if (k.a(paramString))
			return -1;
		return Integer.parseInt(paramString);
	}

	private String b(Context paramContext, int paramInt1, int paramInt2,
			int paramInt3, String paramString1, int paramInt4,
			String paramString2) {
		String str1 = SystemProperties.get("ro.build.version.release");
		if (TextUtils.isEmpty(str1))
			str1 = "unknow";

		String str2 = Build.MODEL;
		if (TextUtils.isEmpty(str2))
			str2 = "unknow";

		String str3 = "";
		if ((!TextUtils
				.isEmpty(SystemProperties.get("ro.product.manufacturer")))
				|| (!TextUtils.isEmpty(SystemProperties.get("ro.mtk.hardware"))))
			str3 = "mtk";
		if (TextUtils.isEmpty(str3))
			str3 = "unknow";
		str3.trim();

		String str4 = b.a(new File("/"),
				new String[] { "cat", "/proc/version" });

		String str5 = "unknow";
		str5 = str4.split(" ")[2];

		String str6 = SystemProperties.get("ro.build.display.id");
		if (TextUtils.isEmpty(str6))
			str6 = "unknow";

		String str7 = paramString1 + ";" + str1 + ";" + str3 + ";" + str5 + ";"
				+ str6;
		int i = a(k.a(paramContext,
				paramContext.getApplicationInfo().packageName));
		String str8 = k.a(paramContext);
		String str9 = f.a(str8);
		if (TextUtils.isEmpty(str8))
			str9 = "unknow";
		String str10 = "type=" + URLEncoder.encode(String.valueOf(paramInt1))
				+ "&action=" + URLEncoder.encode(String.valueOf(paramInt2))
				+ "&mod=" + URLEncoder.encode(str2) + "&status="
				+ URLEncoder.encode(String.valueOf(paramInt3)) + "&moda="
				+ URLEncoder.encode(str7) + "&appver="
				+ URLEncoder.encode(String.valueOf(i)) + "&pkg=" + a.e
				+ "&mid=" + URLEncoder.encode(str9) + "&step="
				+ URLEncoder.encode(String.valueOf(paramInt4)) + "&buildinfo="
				+ URLEncoder.encode(String.valueOf(str4));
		return "http://s.360.cn/shuaji/s.html?" + str10;
	}

	public void a(Context paramContext, int paramInt1, int paramInt2,
			int paramInt3, String paramString1, int paramInt4,
			String paramString2) {
		try {
			String url = b(paramContext, paramInt1, paramInt2, paramInt3, paramString1, paramInt4, paramString2);
			Log.e("MyLog", url);
			new i(this, "report", url, paramString2).start();
		} catch (Exception localException) {
		}
	}
}