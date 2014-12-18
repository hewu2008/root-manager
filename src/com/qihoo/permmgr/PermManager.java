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
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

import com.qihoo.constant.Constants;
import com.qihoo.permmgr.util.AESUtils;
import com.qihoo.rtservice.RTServiceManager;
import com.uucun.root.MainActivity;

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
			File localFile = new File(this.mContext.getFilesDir()
					.getAbsolutePath() + this.mBaseLibiPath);
			if (localFile.exists())
				localFile.delete();
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
				File localFile = new File("/data/data/"
						+ this.mContext.getPackageName() + "/shared_prefs/");
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
		File localFile = new File(this.mContext.getFilesDir().getAbsolutePath()
				+ "/permmgr");
		File[] arrayOfFile = new File[] {};
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

	private void cleanFutext() {
		String str = this.prefs.getString("successSolution", "");
		if ((!TextUtils.isEmpty(str))
				&& ((str.equalsIgnoreCase("53b3a16759d31c1253e137e4"))
						|| (str.equalsIgnoreCase("53b2a92d59d31c1253e137e2"))
						|| (str.equalsIgnoreCase("53ba781759d3727902183a3f"))
						|| (str.equalsIgnoreCase("53b22b96e4b0847d8a6d9fcc"))
						|| (str.equalsIgnoreCase("53bbf2aae4b0dc36d1b8e0d7")) || (str
							.equalsIgnoreCase("53c62d3fe4b0a822654c1792")))) {
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
		OutInfo outInfo = (OutInfo) jdocommand(this.mInfo, paramString1, 30);
		return outInfo.out;
	}

	/**
	 * 检查daemon是否处于运行状态
	 */
	public boolean checkDaemonIsRunning() {
		OutInfo info = (OutInfo) jcheckdaemon(this.mInfo);
		return info.running >= 0;
	}

	/**
	 * 执行成功的root方案
	 */
	public int doSuccessSolution(String md5) {
		return RootMan.getInstance(mContext).doSuccessSolution(md5);
	}

	/**
	 * 执行本地root方案
	 */
	public int doSolutionLocal() {
		return LocalRoot.getInstance().doRoot(mContext);
	}

	public int doSolutionOnline(MainActivity activity) {
		String str1 = SystemProperties.get("ro.build.version.release");
		if (TextUtils.isEmpty(str1))
			str1 = "unknow";
		String str2 = Build.MODEL;
		if (TextUtils.isEmpty(str2))
			str2 = "unknow";
		String str3 = "unknow";
		if ((!TextUtils
				.isEmpty(SystemProperties.get("ro.product.manufacturer")))
				|| (!TextUtils.isEmpty(SystemProperties.get("ro.mtk.hardware"))))
			str3 = "mtk";
		String str4 = com.qihoo.permmgr.util.k.a(mContext);
		String str5 = com.qihoo.permmgr.util.f.a(str4);
		if (TextUtils.isEmpty(str4))
			str5 = "unknow";
		File localFile = new File("/");
		String[] arrayOfString = new String[2];
		arrayOfString[0] = "cat";
		arrayOfString[1] = "/proc/version";
		String str6 = com.qihoo.permmgr.util.b.a(localFile, arrayOfString);
		String str7 = str6.split(" ")[2];
		String str8 = "model=" + URLEncoder.encode(str2) + "&target=1&buildno="
				+ URLEncoder.encode(str7) + "&version="
				+ URLEncoder.encode(str1) + "&platform="
				+ URLEncoder.encode(str3);
		String url = "http://api.shuaji.360.cn/c/getSolution?" + str8 + "&pkg="
				+ a.e + "&mid=" + URLEncoder.encode(str5) + "&new=" + 1
				+ "&src=1";
		try {
			HttpGet localHttpGet2 = new HttpGet(url);
			HttpResponse localHttpResponse = new DefaultHttpClient()
					.execute(localHttpGet2);
			int statusCode = localHttpResponse.getStatusLine().getStatusCode();
			String jsonData = AESUtils.b(EntityUtils.toString(localHttpResponse
					.getEntity()));
			JSONArray arr = new JSONArray(jsonData);
			byte[] bs = new byte[1024];
			int len;
			for (int i = 0; i < arr.length(); i++) {
				JSONObject temp = (JSONObject) arr.get(i);
				String md5 = temp.getString("solution_md5");
				String solution = temp.getString("solution");
				activity.setStatus("downloading " + solution);
				URL sUrl = new URL(solution);
				URLConnection con = sUrl.openConnection();
				InputStream is = con.getInputStream();
				String md5FilePath = mContext.getFilesDir().getAbsoluteFile()
						+ "/permmgr/" + md5;
				OutputStream os = new FileOutputStream(md5FilePath);
				while ((len = is.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
				os.close();
				is.close();
				if (RootMan.getInstance(mContext).doRoot(md5FilePath) == Constants.ROOT_SUCCESS) {
					return Constants.ROOT_SUCCESS;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.NOTSUPPORT;
	}

	private native Object jcheckdaemon(Object object);

	public native Object jdocommand(Object object, String paramString,
			int paramInt);

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

	public static class OutInfo {
		public int err;
		public String out;
		public int running;
		public String version;

		OutInfo(PermManager paramPermManager) {
		}
	}

	public void receiverAndWriteDataMobilesafe(Bundle paramBundle) {
		File localFile = new File(this.mContext.getFilesDir().getAbsoluteFile()
				+ "/" + "env_file");
		if ((localFile != null) && (localFile.exists()))
			localFile.delete();
		int j = paramBundle.getInt("root_type", 0);

		int k = paramBundle.getInt("env_num", 0);
		String[] arrayOfString1 = new String[k];

		int n = paramBundle.getInt("arg_num", 0);
		String[] arrayOfString2 = new String[n];

		StringBuilder localStringBuilder = new StringBuilder();
		localStringBuilder.append("source=").append(this.mSourceType)
				.append('\n');
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

	public void saveEnv() {
		Bundle bundle = new Bundle();
		RTServiceManager.getPermBundle(mContext, bundle);
		saveEnv(bundle);
	}

	private void saveEnv(Bundle paramBundle) {
		File permDir = new File(this.mContext.getFilesDir().getAbsoluteFile()
				+ "/permmgr/");
		Log.e("permmgr", "permDir:" + permDir.getAbsolutePath());
		permDir.mkdirs();
		File localFile = new File(this.mContext.getFilesDir().getAbsoluteFile()
				+ "/permmgr/" + "env_file");
		if ((localFile != null) && (localFile.exists()))
			localFile.delete();
		paramBundle.getInt("root_type", 0);
		int j = paramBundle.getInt("env_num", 0);
		int k = paramBundle.getInt("arg_num", 0);
		String[] arrayOfString1 = new String[j];
		String[] arrayOfString2 = new String[k];
		StringBuilder localStringBuilder = new StringBuilder();
		localStringBuilder.append("#!/system/bin/sh");
		localStringBuilder.append('\n');
		for (int i = 0; i < j; ++i) {
			arrayOfString1[i] = paramBundle.getString("env_" + i);
			localStringBuilder.append("export " + arrayOfString1[i]);
			localStringBuilder.append('\n');
		}
		for (int i = 0; i < k; ++i) {
			arrayOfString2[i] = paramBundle.getString("arg_" + i);
			localStringBuilder.append(arrayOfString2[i]);
			localStringBuilder.append(" ");
		}
		localStringBuilder.append("\n");
		localStringBuilder.append("rm " + localFile.getAbsolutePath());
		localStringBuilder.append("\n");
		writeFileData(localFile, localStringBuilder.toString());
		try {
			Runtime.getRuntime().exec("chmod 755 " + localFile.getPath())
					.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeFileData(File paramFile, String paramString) {
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream(
					paramFile);
			localFileOutputStream.write(paramString.getBytes());
			localFileOutputStream.close();
			return;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
}