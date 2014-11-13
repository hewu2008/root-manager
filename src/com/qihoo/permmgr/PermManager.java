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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import com.qihoo.permmgr.util.b;

public class PermManager {
	private static final int ACT_FAILBYSU = 3;
	private static final int ACT_IGNORE = 1;
	private static final int ACT_NOSU = 0;
	private static final int ACT_SUCCESSBYSU = 2;
	private static final String CHECKSUPPORTKEY = "checksupportlasttime";
	private static final String CHECKSUPPORTRESULTFORPCKEY = "checksupportresultforpc";
	private static final String CHECKSUPPORTRESULTKEY = "checksupportresult";
	private static final String CHECKUPDATEKEY = "checklasttime";
	private static final String CHECKUPDATE_CLIENT_KEY = "check_version_client";
	private static final String CHECKUPDATE_LIBSUKEY = "check_version_libsu";
	private static final String ERRKEY = "err";
	protected static final String FAIL_3016TIME = "last3016time";
	protected static final String FAIL_DONUM = "fail_donum";
	protected static final String FAIL_SOLUTIONS = "fail_solutions";
	protected static final String ISFIRSTKEY = "firstdolocalroot";
	protected static final String LASTROOTTIMEKEY = "lastroottime";
	private static final int NETERR = 1;
	protected static final int NETERRCODE = 102;
	protected static final int NONETCODE = 101;
	protected static final int NORMALCODE = 100;
	private static final int NO_NETERR = 0;
	private static final int NO_SUPPORT = 0;
	protected static final int OTHERERRCODE = 103;
	private static final String OUTKEY = "out";
	private static final int PHONE_SUPPORT = 1;
	public static final String ROOT_VERSION = "1.2.4forzhushou";
	private static final int SOURCE_APPSTORE = 2;
	private static final int SOURCE_MOBILESAFE = 1;
	private static final int SOURCE_POWERCTL = 3;
	private static final int SOURCE_UNKNOW = 0;
	protected static final String SUPPMOKEY = "mobile";
	protected static final String SUPPPCKEY = "pc";
	private static final String logDirPath = "/mnt/sdcard/360/";
	public static final String logFilePath = "/mnt/sdcard/360/permmgr";
	private static PermManager mInstance = null;
	private static final String s93F = "53b3a16759d31c1253e137e4";
	private static final String s93FT = "53b22b96e4b0847d8a6d9fcc";
	private static final String s94F = "53b2a92d59d31c1253e137e2";
	private static final String s94FT = "53bbf2aae4b0dc36d1b8e0d7";
	private static final String s96F = "53ba781759d3727902183a3f";
	private static final String s96FT = "53c62d3fe4b0a822654c1792";
	private boolean isChecking = false;
	boolean isHaveSu = true;
	boolean isTest = false;
	private String mBaseLib360 = "/permmgr/lib360.so";
	private String mBaseLibcPath = "/permmgr/Libpermc.so";
	private String mBaseLibiPath = "/permmgr/libsu.so";
	private Context mContext;
	Map mDoCommandArray = new HashMap();
	private final Handler mHandler = new Handler(Looper.getMainLooper());
	private PermManager.OutInfo mInfo = new PermManager.OutInfo(this);
	private boolean mIsSupport = true;
	private boolean mIsSupportByPC = false;
	private int mMyRealUid = -1;
	boolean mRefuseByUser = false;
	private int mSourceType = 0;
	private String mUpdateUrlString_Client = "http://api.shuaji.360.cn/r/getClient?pkg=com.qihoo.360.shuaji.root&type=RT_CLIENT";
	private String mUpdateUrlString_Libsu = "http://api.shuaji.360.cn/r/getClient?pkg=com.qihoo.360.shuaji.root&type=RT_LIBSU";
	String out = "";
	private int outTime = 120;
	private SharedPreferences prefs;
	private int resultcode = -1;
	private PowerManager.WakeLock wakeLock = null;
	
	private PermManager(Context context) {
		this.mContext = context;
		try {
			File localFile = new File(this.mContext.getFilesDir().getAbsolutePath() + this.mBaseLibiPath);
			if (localFile.exists())
				localFile.delete();
			// checkFiles();
			this.prefs = context.getSharedPreferences("permmgr", 0);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
	
	/**
	 * 改变permmgr.xml为root权限
	 */
	private void changeUidToMyReal() {
		int i = this.mMyRealUid;
		try {
			if (Process.myUid() == 0) {
				File localFile = new File("/data/data/" + this.mContext.getPackageName() + "/shared_prefs/");
				String[] arrayOfString = new String[3];
				arrayOfString[0] = "chown";
				arrayOfString[1] = (i + "." + i);
				arrayOfString[2] = "permmgr.xml";
				com.qihoo.permmgr.util.b.a(localFile, arrayOfString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkFileByPath(String paramString) {
		File localFile = new File(this.mContext.getFilesDir().getAbsolutePath()
				+ "/permmgr/" + paramString);
		if ((localFile.exists()) && (checkFileSize(paramString, localFile))) {
			Log.d("PermManager:checkFileByPath", "file is normal");
			return true;
		}
		return false;
	}
	
	/**
	 * 检查文件数量，操作15个文件需要删除多余文件
	 */
	private void checkFileNum() {
		File localFile = new File(this.mContext.getFilesDir().getAbsolutePath() + "/permmgr");
		File[] arrayOfFile = new File[]{};
		if (localFile.exists()) {
			arrayOfFile = localFile.listFiles();
			if (arrayOfFile.length <= 15)
				return;
		}
	}
	
	/** 
	 * 检查asset/permmgr目录与程序目录下的文件大小是否一致
	 */
	private boolean checkFileSize(String paramString, File paramFile) {
		AssetManager assetManager = mContext.getAssets();
		try {
			InputStream is = assetManager.open("permmgr/" + paramString);
			if (is.available() == paramFile.length()) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 检查root服务是否存在
	 */
	private boolean checkRT_server(c paramc) {
		if (paramc != null) {
			return paramc.onCheckRootServerExist();
		}
		return false;
	}
	
	/**
	 * 更新permManager
	 */
	private void checkUpdate() {
	    new Thread(new UpdateRunnable()).start();
	}
	
	private void cleanFutext() {
		String str = this.prefs.getString("successSolution", "");
		if ((!TextUtils.isEmpty(str))
				&& ((str.equalsIgnoreCase("53b3a16759d31c1253e137e4"))
						|| (str.equalsIgnoreCase("53b2a92d59d31c1253e137e2"))
						|| (str.equalsIgnoreCase("53ba781759d3727902183a3f"))
						|| (str.equalsIgnoreCase("53b22b96e4b0847d8a6d9fcc"))
						|| (str.equalsIgnoreCase("53bbf2aae4b0dc36d1b8e0d7")) 
						|| (str.equalsIgnoreCase("53c62d3fe4b0a822654c1792")))) {
			File localFile = new File(this.mContext.getFilesDir()
					.getAbsolutePath() + "/permmgr/" + str);
			if ((localFile != null) && (localFile.exists())) {
				this.prefs.edit().remove("successSolution").commit();
				localFile.delete();
				Log.d("PermManager", "cleanFutext");
			}
		}
	}
	
	/**
	 * 执行jdocommand命令并返回结果
	 */
	private String doCommandNoErr(String paramString1, String paramString2) {
		checkUpdate();
		OutInfo outInfo = (OutInfo)jdocommand(this.mInfo, paramString1, 30);
		return outInfo.out;
	}
	
	/**
	 * 检查daemon是否处于运行状态
	 */
	public boolean checkDaemonIsRunning() {
		OutInfo info = (OutInfo)jcheckdaemon(this.mInfo);
		return info.running >= 0;
	}
	
	/**
	 * 检查是否支持saferoot
	 * Map
	 */
	public Map checkIsSupportForSafe(String paramString, boolean paramBoolean)
	  {
//	    HashMap localHashMap = new HashMap();
//	    if (this.prefs == null) this.prefs = this.mContext.getSharedPreferences("permmgr", 0);
//	    // 检查是否曾经root过
//	    if ((System.currentTimeMillis() - this.prefs.getLong("checksupportlasttime", 0L) < 7200000L) && (!paramBoolean))
//	    {
//	      localHashMap.clear();
//	      localHashMap.put("mobile", Integer.valueOf(this.prefs.getInt("checksupportresult", 0)));
//	      localHashMap.put("pc", Integer.valueOf(this.prefs.getInt("checksupportresultforpc", 0)));
//	      localHashMap.put("err", Integer.valueOf(100));
//	      return localHashMap;
//	    }
//	    // 检查是否有网络连接
//	    if (!com.qihoo.permmgr.util.d.a(this.mContext))
//	    {
//	      localHashMap.clear();
//	      localHashMap.put("mobile", Integer.valueOf(0));
//	      localHashMap.put("pc", Integer.valueOf(0));
//	      localHashMap.put("err", Integer.valueOf(101));
//	      return localHashMap;
//	    }
//	    try
//	    {
//	      String str = d.a(RootMan.a(this.mContext).a(paramString, 1), 10000);
//	      if (str == null)
//	      {
//	        localHashMap.clear();
//	        localHashMap.put("mobile", Integer.valueOf(0));
//	        localHashMap.put("pc", Integer.valueOf(0));
//	        localHashMap.put("err", Integer.valueOf(102));
//	        return localHashMap;
//	      }
//	    }
//	    catch (ParseException localParseException)
//	    {
//	      String str;
//	      localHashMap.clear();
//	      localHashMap.put("mobile", Integer.valueOf(0));
//	      localHashMap.put("pc", Integer.valueOf(0));
//	      localHashMap.put("err", Integer.valueOf(103));
//	      return localHashMap;
//	      JSONObject localJSONObject = new JSONObject(str);
//	      int i = localJSONObject.getInt("mobile");
//	      int j = localJSONObject.getInt("pc");
//	      this.prefs.edit().putLong("checksupportlasttime", System.currentTimeMillis()).commit();
//	      this.prefs.edit().putInt("checksupportresult", i).commit();
//	      this.prefs.edit().putInt("checksupportresultforpc", j).commit();
//	      localHashMap.clear();
//	      localHashMap.put("mobile", Integer.valueOf(i));
//	      localHashMap.put("pc", Integer.valueOf(j));
//	      localHashMap.put("err", Integer.valueOf(100));
//	      return localHashMap;
//	    }
//	    catch (IOException localIOException)
//	    {
//	        localIOException.printStackTrace();
//	    }
//	    catch (JSONException localJSONException)
//	    {
//	        localJSONException.printStackTrace();
//	    }
		return null;
	  }
		
//	private int doSolutionBySU(String paramString, c paramc) {
//		if (isHaveSu) {
//			new Thread(new CheckSuRunnable()).start();
//			g instance = g.a(mContext);
//			int paramInt1 = 22;
//			int paramInt2 = 3000;
//			int paramInt3 = 1;
//			String paramString1 = "";
//			int paramInt4 = 203;
//			String paramString2 = "";
//			instance.a(mContext, paramInt1, paramInt2, paramInt3, paramString1, paramInt4, paramString2);
//			paramc.onCheckRootServerExist();
//		}
//		return 0;
//	}
//	
//	private int doSolutionOnline(String paramString, c paramc)
//	  {
//	    Log.d("doSolutionOnline", "[*] online");
//	    // 写入机型信息至permmgr中
//	    // e.b("3", new File("/mnt/sdcard/360/permmgr"));
//	    if (!d.a(this.mContext))
//	    {
//	      Log.d("doSolutionOnline", "----doroot online net not conncet----");
//	      return 3025;
//	    }
//	    if ((!this.mIsSupport) && (!this.mIsSupportByPC))
//	      return -1000;
//	    if ((!this.mIsSupport) && (this.mIsSupportByPC))
//	      return 3026;
//	    g.a(this.mContext).a(this.mContext, 21, 0, -1, "0", 300, "do online solution");
//	    int i = RootMan.a(this.mContext).a();
//	    if ((i == 3000) && (!checkRT_server(paramc)))
//	    {
//	      i = 3046;
//	      Log.d("doSolutionOnline", "3000 but service not running");
//	    }
//	    this.resultcode = i;
//	    reportStat(this.resultcode, 2, 1000);
//	    Log.d("doSolutionOnline", "[*] end");
//	    return this.resultcode;
//	  }
	
	private native Object jcheckdaemon(Object object);
	
	private native Object jdocommand(Object object, String paramString, int paramInt);
	
	private native int jrestartdaemon(String paramString);
	
	protected String getErrKey() {
		return "err";
	}

	protected String getOutKey() {
		return "out";
	}
	
	public static PermManager getInstance(Context paramContext) {
		if (mInstance == null)
			mInstance = new PermManager(paramContext);
		return mInstance;
	}
	
	class OutInfo {
		private int err;
		private String out;
		private int running;
		private String version;

		OutInfo(PermManager paramPermManager) {
		}
	}
	
	class UpdateRunnable implements Runnable
	{
	  public void run()
	  {
//	    if (PermManager.access$0(this.this$0) == null)
//	      PermManager.access$2(this.this$0, PermManager.access$1(this.this$0).getSharedPreferences("permmgr", 0));
//	    if (System.currentTimeMillis() - PermManager.access$0(this.this$0).getLong("checklasttime", 0L) < 86400000L);
//	    do
//	      return;
//	    while (PermManager.access$3(this.this$0));
//	    try
//	    {
//	      boolean bool = d.a(PermManager.access$1(this.this$0));
//	      if (!bool)
//	        return;
//	      PermManager.access$4(this.this$0, true);
//	      String str1 = d.a(PermManager.access$5(this.this$0), 10000);
//	      e.a("updatabegin--vvv--" + str1);
//	      PermManager.ConfigInfo localConfigInfo1 = PermManager.ConfigInfo.loadFromString(str1, PermManager.access$1(this.this$0));
//	      if ((localConfigInfo1 != null) && (localConfigInfo1.apkVersion > PermManager.access$0(this.this$0).getInt("check_version_client", 0)))
//	      {
//	        String str4 = PermManager.access$1(this.this$0).getFilesDir().getAbsolutePath() + PermManager.access$6(this.this$0);
//	        d.a(localConfigInfo1.apkUrl, 60000, str4);
//	        com.qihoo.permmgr.util.b.b("chmod 755 " + str4);
//	        PermManager.access$0(this.this$0).edit().putInt("check_version_client", localConfigInfo1.apkVersion);
//	      }
//	      String str2 = d.a(PermManager.access$7(this.this$0), 10000);
//	      e.a("updatabegin--vvv--" + str2);
//	      if (localConfigInfo1 != null)
//	        localConfigInfo1.clean(localConfigInfo1);
//	      PermManager.ConfigInfo localConfigInfo2 = PermManager.ConfigInfo.loadFromString(str2, PermManager.access$1(this.this$0));
//	      if ((localConfigInfo2 != null) && (localConfigInfo2.apkVersion > PermManager.access$0(this.this$0).getInt("check_version_libsu", 0)))
//	      {
//	        String str3 = PermManager.access$1(this.this$0).getFilesDir().getAbsolutePath() + PermManager.access$8(this.this$0);
//	        d.a(localConfigInfo2.apkUrl, 60000, str3);
//	        com.qihoo.permmgr.util.b.b("chmod 755 " + str3);
//	        PermManager.access$0(this.this$0).edit().putInt("check_version_libsu", localConfigInfo2.apkVersion);
//	      }
//	      PermManager.access$0(this.this$0).edit().putLong("checklasttime", System.currentTimeMillis()).commit();
//	      PermManager.access$4(this.this$0, false);
//	      return;
//	    }
//	    catch (ParseException localParseException)
//	    {
//	      PermManager.access$4(this.this$0, false);
//	      if (b.a)
//	        localParseException.printStackTrace();
//	      return;
//	    }
//	    catch (IOException localIOException)
//	    {
//	      PermManager.access$4(this.this$0, false);
//	      if (b.a)
//	        localIOException.printStackTrace();
//	      return;
//	    }
//	    catch (Exception localException)
//	    {
//	      PermManager.access$4(this.this$0, false);
//	      if (b.a)
//	        localException.printStackTrace();
//	      return;
//	    }
//	    finally
//	    {
//	      PermManager.access$4(this.this$0, false);
//	    }
//	    throw localObject;
	  }
	}
	
	 public void receiverAndWriteDataMobilesafe(Bundle paramBundle)
	  {
	    File localFile = new File(this.mContext.getFilesDir().getAbsoluteFile() + "/" + "env_file");
	    if ((localFile != null) && (localFile.exists()))
	      localFile.delete();
	    int j = paramBundle.getInt("root_type", 0);
	    
	    int k = paramBundle.getInt("env_num", 0);
	    String[] arrayOfString1 = new String[k];
	    
	    int n = paramBundle.getInt("arg_num", 0);
	    String[] arrayOfString2 = new String[n];
	 
	    StringBuilder localStringBuilder = new StringBuilder();
	    localStringBuilder.append("source=").append(this.mSourceType).append('\n');
	    localStringBuilder.append('\n');
	    localStringBuilder.append("root_type").append('\n');
	    localStringBuilder.append(j).append('\n').append('\n');
	    
	    localStringBuilder.append("env").append('\n');
	    for (int i = 0; i < k; ++i) {
	    	arrayOfString1[i] = paramBundle.getString("env_" + i);
	    	localStringBuilder.append(arrayOfString1[i]).append('\n');
	    }
	    localStringBuilder.append('\n');
	    
	    localStringBuilder.append("arg").append('\n');
	    for (int i = 0; i < n; ++i) {
	    	arrayOfString2[i] = paramBundle.getString("arg_" + i);
	    	localStringBuilder.append(arrayOfString2[i]).append('\n');
	    }
	    localStringBuilder.append('\n');
	    writeFileData(localFile, localStringBuilder.toString());
	  }
	 
	public void writeFileData(File paramFile, String paramString) {
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
			localFileOutputStream.write(paramString.getBytes());
			localFileOutputStream.close();
			return;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	
	class CheckSuRunnable implements Runnable {
		public void run() {
			try {
				out = b.a(new File(mContext.getFilesDir().getAbsolutePath()
						+ "/permmgr/"), "chmod 755 " + mContext.getFilesDir().getAbsolutePath() + "/permmgr/libsu.so;" 
					    + mContext.getFilesDir().getAbsolutePath() + "/permmgr/libsu.so &");
				Log.d("run", "su out----" + out);
				if ((!TextUtils.isEmpty(out)) && ((out.contains("denied")) || (out.contains("unallowed"))))
					mRefuseByUser = true;
			} catch (Exception localException) {
				isHaveSu = false;
				Log.d("run", "[-] su not exists");
			}
		}
	}
}