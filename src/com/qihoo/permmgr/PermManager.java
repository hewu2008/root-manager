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

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;

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
	
	private native Object jcheckdaemon(Object object);
	private native Object jdocommand(Object object, String paramString, int paramInt);
	private native int jrestartdaemon(String paramString);
	
	class OutInfo {
		private int err;
		private String out;
		private int running;
		private String version;

		OutInfo(PermManager paramPermManager) {
		}
	}
}