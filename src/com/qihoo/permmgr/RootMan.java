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
package com.qihoo.permmgr;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;

import com.qihoo.rtservice.NativeHelper;
import com.qihoo.rtservice.RTServiceManager;
import com.qihoo.rtservice.Utils;

public class RootMan {
	private static Context b;
	private static RootMan mInstance = null;
	private static final String TAG = RootMan.class.getSimpleName();

	public static RootMan getInstance() {
		if (mInstance == null)
			mInstance = new RootMan();
		return mInstance;
	}
	
	public static RootMan a(Context paramContext) {
		b = paramContext;
		if (mInstance == null)
			mInstance = new RootMan();
		return mInstance;
	}
	
	public String a(String paramString, int paramInt)
	  {
//	    String str1 = SystemProperties.get("ro.build.version.release");
//	    if (TextUtils.isEmpty(str1))
//	      str1 = "unknow";
//	    String str2 = Build.MODEL;
//	    if (TextUtils.isEmpty(str2))
//	      str2 = "unknow";
//	    String str3;
//	    if (!TextUtils.isEmpty(SystemProperties.get("ro.mtk.hardware")))
//	      str3 = "mtk";
//	    while (true)
//	    {
//	      if (TextUtils.isEmpty(str3))
//	        str3 = "unknow";
//	      str3.trim();
//	      String str4 = com.qihoo.permmgr.util.k.a(b);
//	      String str5 = f.a(str4);
//	      if (TextUtils.isEmpty(str4))
//	        str5 = "unknow";
//	      String str6 = com.qihoo.permmgr.util.b.a(new File("/"), new String[] { "cat", "/proc/version" });
//	      String str7 = "unknow";
//	      try
//	      {
//	        str7 = str6.split(" ")[2];
//	        StringBuffer localStringBuffer1 = new StringBuffer("http://api.shuaji.360.cn/c/getMobileSupport?");
//	        localStringBuffer1.append("req=");
//	        StringBuffer localStringBuffer2 = new StringBuffer("mid=");
//	        localStringBuffer2.append(str5);
//	        localStringBuffer2.append("&act=");
//	        localStringBuffer2.append(paramInt);
//	        localStringBuffer2.append("&pkg=");
//	        localStringBuffer2.append(paramString);
//	        localStringBuffer2.append("&m=");
//	        localStringBuffer2.append(str2);
//	        localStringBuffer2.append("&v=");
//	        localStringBuffer2.append(str7);
//	        localStringBuffer2.append("&a=");
//	        localStringBuffer2.append(str1);
//	        localStringBuffer2.append("&p=");
//	        localStringBuffer2.append(str3);
//	        localStringBuffer1.append(com.qihoo.permmgr.util.a.a(localStringBuffer2.toString().getBytes()));
//	        return localStringBuffer1.toString();
//	        str3 = SystemProperties.get("ro.board.platform");
//	        if (!TextUtils.isEmpty(str3))
//	          continue;
//	        str3 = SystemProperties.get("ro.hardware");
//	      }
//	      catch (Exception localException)
//	      {
//	        break label160;
//	      }
//	    }
		return null;
	  }

	private native int jmain(int paramInt);

	private native void junmain(Class<?> paramClass);
	
	public int doRoot(Context context) {
		String libPath = NativeHelper.copyNativeLib(context, "permmgr/lib360.so");
		String libPermc = NativeHelper.copyNativeLib(context, "permmgr/Libpermc.so");
		String libSu = NativeHelper.copyNativeLib(context, "permmgr/libsu.so");
		String lib1 = NativeHelper.copyNativeLib(context, "permmgr/1403615456842.so");
		String lib2 = NativeHelper.copyNativeLib(context, "permmgr/1406613154560.so");
		String lib3 = NativeHelper.copyNativeLib(context, "permmgr/1413965415665.so");
		NativeHelper.copyNativeElf(context, "libroot");
		System.load(libPath);
		System.load(libPermc);
		System.load(lib1);
		int nRet = jmain(0);
		junmain(getClass());
		System.load(lib2);
		System.load(lib3);
		Bundle bundle = new Bundle();
		RTServiceManager.getPermBundle(context, bundle);
		PermManager.getInstance(context).receiverAndWriteDataMobilesafe(bundle);
		List<String> commands = new ArrayList<String>();
		commands.add(libSu);
		Utils.execP(commands);
		int uid = Process.myUid();
		Log.e(TAG, "uid:" + uid);
		return nRet;
	}
}