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

import java.util.concurrent.atomic.AtomicInteger;

import com.qihoo.permmgr.PermService;
import com.qihoo.rtservice.support.RootAppHelper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

public class RTServiceManager implements IRTService {
	public static final boolean DEBUG = true;
	private static final Object a = new Object();
	private static final Object b = new Object();
	private static Boolean c = null;
	private static Object d = new Object();
	private static final Handler e = new Handler(Looper.getMainLooper());
	private static boolean f = false;

	// private static h g;
	private static int a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2) 
	{
	    f = true;
	    Intent localIntent = new Intent(paramContext, PermService.class);
	    AtomicInteger localAtomicInteger = new AtomicInteger(691);
	    com.qihoo.appstore.utils.e locale = new com.qihoo.appstore.utils.e();
	    RTServiceConnection local5 = new RTServiceConnection(paramContext, localAtomicInteger, paramBoolean1, locale);
	    locale.b();
	    try
	    {
	      if (paramContext.bindService(localIntent, local5, 1))
	        if (paramBoolean1)
	        {
	          Log.d("RTService", "startByTempRoot callWait");
	          locale.a(60000L);
	        }
	      while (true)
	      {
	        Log.d("RTService", "startByTempRoot back from wait");
	        Log.d("RTService", "startByTempRoot return " + localAtomicInteger.get());
	        f = false;
	        return localAtomicInteger.get();
	        localAtomicInteger.set(692);
	      }
	    }
	    catch (Exception localException)
	    {
	      while (true)
	        localAtomicInteger.set(693);
	    }
	  }

	public static void getPermBundle(Context paramContext, Bundle paramBundle) {
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String str4 = "";
		str1 = paramContext.getPackageCodePath();
		str2 = String.format("CLASSPATH=%s", new Object[] { str1 });
		str3 = System.getenv("LD_LIBRARY_PATH");
		str4 = RootAppHelper.getMyLibDir();
		String str5 = String.format("LD_LIBRARY_PATH=/vendor/lib:/system/lib:%s", str4);
		String str6 = String.format("_LD_LIBRARY_PATH=%s", new Object[] { str5 });
		String str7 = String.format("LD_LIBRARY_PATH=%s", new Object[] { str5 });
		paramBundle.putString("env_0", str2);
		paramBundle.putString("env_1", str6);
		paramBundle.putString("env_2", str7);
		paramBundle.putString("env_3", "PATH=/sbin:/vendor/bin:/system/sbin:/system/bin:/system/xbin");
		paramBundle.putInt("env_num", 3);
		paramBundle.putInt("root_type", 1);
		paramBundle.putInt("arg_num", 5);
		paramBundle.putString("arg_0", "/system/bin/app_process");
		paramBundle.putString("arg_1", "/system/bin");
		paramBundle.putString("arg_2", IRTServiceImpl.class.getName());
		paramBundle.putString("arg_3", "--nice-name=qh_rt_service");
		paramBundle.putString("arg_4", "--application");
		try {
			str1 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.publicSourceDir;
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			localNameNotFoundException.printStackTrace();
		}
		}
	}

	final class RTServiceConnection implements ServiceConnection {
		public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
	  {
	    Log.d("RTService", "onServiceConnected");
	    new RTServiceConnectionThread(this, f.a(paramIBinder), this).start();
	  }

		public void onServiceDisconnected(ComponentName paramComponentName) {
		}

		class RTServiceConnectionThread extends Thread {
			public void run()
	    {
	      boolean bool1 = true;
	      try
	      {
	        Bundle localBundle = new Bundle();
	        RTServiceManager.getPermBundle(this.c.a, localBundle);
	        i = this.a.getRootForSafe("2004", true, localBundle, new RTServiceManager.5.1.1(this));
	        bn.b("RTService", "resultCode is:" + i);
	        if (i == 3000)
	        {
	          int j;
	          for (boolean bool2 = bool1; RTServiceManager.getRTService() == null; bool2 = j)
	          {
	            j = bool2 + true;
	            if (bool2 >= true)
	              break;
	            Thread.sleep(1000L);
	          }
	        }
	        if ((i == 3000) && (RTServiceManager.getRTService() != null))
	        {
	          this.c.b.set(0);
	          bn.b("RTService", "startByTempRoot result true");
	          if (this.c.b.get() != 0)
	            break label314;
	          f.n(bool1);
	        }
	      }
	      catch (Exception localException1)
	      {
	        try
	        {
	          int i;
	          label163: this.c.a.unbindService(this.b);
	          label177: if (this.c.c)
	          {
	            bn.b("RTService", "startByTempRoot callResume");
	            this.c.d.c();
	          }
	          return;
	          if (i == -1000)
	            this.c.b.set(695);
	          while (true)
	          {
	            bn.b("RTService", "startByTempRoot result false");
	            break;
	            localException1 = localException1;
	            this.c.b.set(694);
	            localException1.printStackTrace();
	            break label163;
	            if ((i > 3000) && (i < 3900))
	            {
	              this.c.b.set(-3000 + (i + 600));
	              continue;
	            }
	            this.c.b.set(695);
	          }
	          label314: bool1 = false;
	        }
	        catch (Exception localException2)
	        {
	          break label177;
	        }
	      }
	    }
		}
	}
}
