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

import android.content.Context;
import android.util.Log;
import android.os.Process;

import com.qihoo.rtservice.NativeHelper;
import com.qihoo.rtservice.Utils;

public class RootMan {
	private static RootMan mInstance = null;
	private static final String TAG = RootMan.class.getSimpleName();

	public static RootMan getInstance() {
		if (mInstance == null)
			mInstance = new RootMan();
		return mInstance;
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
		System.load(lib2);
		System.load(lib3);
		int nRet = jmain(0);
		Utils.exec(libSu);
		Log.d(TAG, "uid:" + Process.myUid());
		return nRet;
	}
}