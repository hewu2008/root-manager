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
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;
import java.io.File;
import java.net.URLEncoder;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class g
{
  private static g f = null;
  ExecutorService a = Executors.newFixedThreadPool(3);
//  private ArrayBlockingQueue b = new ArrayBlockingQueue(20);
//  private ArrayBlockingQueue c = new ArrayBlockingQueue(20);
//  private h d = null;
//  private h e = null;
  private Context g;
  private boolean h = true;

  private g(Context paramContext)
  {
    this.g = paramContext;
  }

  static int a(String paramString)
  {
    if (k.a(paramString))
      return -1;
    return Integer.parseInt(paramString);
  }

  public static g a(Context paramContext)
  {
    if (f == null)
      f = new g(paramContext);
    return f;
  }

	public static String a() {
		String str1 = SystemProperties.get("ro.build.version.release");
		if (TextUtils.isEmpty(str1))
			str1 = "unknow";
		String str2 = Build.MODEL;
		if (TextUtils.isEmpty(str2))
			str2 = "unknow";
		String str3 = "";
		if (!TextUtils.isEmpty(SystemProperties.get("ro.mtk.hardware")))
			str3 = "mtk";
		if (TextUtils.isEmpty(str3))
			str3 = "unknow";
		str3.trim();
		String str4 = b.a(new File("/"),
				new String[] { "cat", "/proc/version" });
		String str5 = SystemProperties.get("ro.board.platform");
		str3 = SystemProperties.get("ro.hardware");
		return str1 + "--;--" + str3 + "--;--" + str5 + "--;--" + str2;
	}

	private String a(Context paramContext, String paramString, int paramInt)
  {
    int i = 1;
    String str1 = SystemProperties.get("ro.build.version.release");
    if (TextUtils.isEmpty(str1))
      str1 = "unknow";
    String str2 = Build.MODEL;
    if (TextUtils.isEmpty(str2))
      str2 = "unknow";
    String str3;
    if ((!TextUtils.isEmpty(SystemProperties.get("ro.product.manufacturer"))) || (!TextUtils.isEmpty(SystemProperties.get("ro.mtk.hardware"))))
      str3 = "mtk";
    while (true)
    {
      if (TextUtils.isEmpty(str3))
        str3 = "unknow";
      str3.trim();
      File localFile = new File("/");
      String[] arrayOfString = new String[2];
      arrayOfString[0] = "cat";
      arrayOfString[i] = "/proc/version";
      String str4 = b.a(localFile, arrayOfString);
      String str5 = "unknow";
      try
      {
        str5 = str4.split(" ")[2];
        label137: String str6 = SystemProperties.get("ro.build.display.id");
        if (TextUtils.isEmpty(str6))
          str6 = "unknow";
        String str7 = k.a(paramContext);
        String str8 = f.a(str7);
        if (TextUtils.isEmpty(str7))
          str8 = "unknow";
        if (paramInt == 3000);
        while (true)
        {
          String str9 = "planid=" + URLEncoder.encode(paramString) + "&model=" + URLEncoder.encode(str2) + "&target=1" + "&buildno=" + URLEncoder.encode(str5) + "&version=" + URLEncoder.encode(str1) + "&platform=" + URLEncoder.encode(str3) + "&support=" + URLEncoder.encode(String.valueOf(i)) + "&displayid=" + URLEncoder.encode(str6) + "&errcode=" + URLEncoder.encode(String.valueOf(paramInt)) + "&buildinfo=" + URLEncoder.encode(String.valueOf(str4));
          return "http://api.shuaji.360.cn/c/getErrcode?" + str9 + "&pkg=" + a.e + "&mid=" + URLEncoder.encode(str8);
          str3 = SystemProperties.get("ro.board.platform");
          if (!TextUtils.isEmpty(str3))
            break;
          str3 = SystemProperties.get("ro.hardware");
          break;
          i = 0;
        }
      }
      catch (Exception localException)
      {
        break label137;
      }
    }
  }

	private String b(Context paramContext, int paramInt1, int paramInt2, int paramInt3, String paramString1, int paramInt4, String paramString2)
  {
    String str1 = SystemProperties.get("ro.build.version.release");
    if (TextUtils.isEmpty(str1))
      str1 = "unknow";
    String str2 = Build.MODEL;
    if (TextUtils.isEmpty(str2))
      str2 = "unknow";
    String str3;
    if ((!TextUtils.isEmpty(SystemProperties.get("ro.product.manufacturer"))) || (!TextUtils.isEmpty(SystemProperties.get("ro.mtk.hardware"))))
      str3 = "mtk";
    while (true)
    {
      if (TextUtils.isEmpty(str3))
        str3 = "unknow";
      str3.trim();
      String str4 = b.a(new File("/"), new String[] { "cat", "/proc/version" });
      String str5 = "unknow";
      try
      {
        str5 = str4.split(" ")[2];
        label123: String str6 = SystemProperties.get("ro.build.display.id");
        if (TextUtils.isEmpty(str6))
          str6 = "unknow";
        String str7 = paramString1 + ";" + str1 + ";" + str3 + ";" + str5 + ";" + str6;
        int i = a(k.a(paramContext, paramContext.getApplicationInfo().packageName));
        String str8 = k.a(paramContext);
        String str9 = f.a(str8);
        if (TextUtils.isEmpty(str8))
          str9 = "unknow";
        String str10 = "type=" + URLEncoder.encode(String.valueOf(paramInt1)) + "&action=" + URLEncoder.encode(String.valueOf(paramInt2)) + "&mod=" + URLEncoder.encode(str2) + "&status=" + URLEncoder.encode(String.valueOf(paramInt3)) + "&moda=" + URLEncoder.encode(str7) + "&appver=" + URLEncoder.encode(String.valueOf(i)) + "&pkg=" + a.e + "&mid=" + URLEncoder.encode(str9) + "&step=" + URLEncoder.encode(String.valueOf(paramInt4)) + "&buildinfo=" + URLEncoder.encode(String.valueOf(str4));
        return "http://s.360.cn/shuaji/s.html?" + str10;
        str3 = SystemProperties.get("ro.board.platform");
        if (!TextUtils.isEmpty(str3))
          continue;
        str3 = SystemProperties.get("ro.hardware");
      }
      catch (Exception localException)
      {
        break label123;
      }
    }
  }

	public void a(Context paramContext, int paramInt1, int paramInt2,
			int paramInt3, String paramString1, int paramInt4,
			String paramString2) {
		try {
			this.a.execute(new i(this, "report", b(paramContext, paramInt1, paramInt2, paramInt3, paramString1, paramInt4, paramString2), paramString2));
			return;
		} catch (Exception localException) {
		}
	}

	public void a(Context paramContext, int paramInt, String paramString) {
		try {
			this.a.execute(new i(this, "feedback", a(paramContext, paramString,
					paramInt), "feedback---"));
			return;
		} catch (Exception localException) {
		}
	}
}
