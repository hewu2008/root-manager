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

import android.content.Context;
import android.util.Log;

import com.qihoo.constant.Constants;
import com.qihoo.rtservice.NativeHelper;

public class RootMan {
	private static Context mContext;
	private static RootMan mInstance = null;
	private static final String TAG = RootMan.class.getSimpleName();

	public static RootMan getInstance(Context paramContext) {
		mContext = paramContext;
		if (mInstance == null)
			mInstance = new RootMan();
		return mInstance;
	}

	private native int jmain(int paramInt);

	private native void junmain(Class<?> paramClass);

	public int doRoot(Context context) {
		NativeHelper.copyNativeLib(context, "libsu.so");
		String lib3 = NativeHelper.copyNativeLib(context, "1413965415665.so");
		System.load(lib3);
		int nRet = jmain(0);
		Log.e(TAG, "jmain return code:" + nRet);
		junmain(getClass());
		Log.e(TAG, "junmain release resource finish");
		return nRet;
	}
	
	public int doRoot(String md5FilePath) {
		System.load(md5FilePath);
		int nRet = jmain(0);
		junmain(getClass());
		return nRet;
	}

	public int doSuccessSolution(String md5) {
		NativeHelper.copyNativeLib(mContext, "libsu.so");
		String filePath = mContext.getFilesDir().getAbsoluteFile() + "/permmgr/" + md5;
		File outFile = new File(filePath);
		if (outFile.exists() == true) {
			System.load(filePath);
			int nRet = jmain(0);
			junmain(getClass());
			return nRet;
		}
		return Constants.ROOT_FAILED_SOLUTION_FILE_NOT_EXISTS;
	}
}