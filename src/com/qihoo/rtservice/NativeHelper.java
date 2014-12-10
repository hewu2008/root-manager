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

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.util.Log;

public class NativeHelper {
	private static final String TAG = NativeHelper.class.getSimpleName();

	public static String copyNativeLib(Context paramContext, String libname) {
		// 检查文件是否是否存在
		File permDir = new File(paramContext.getFilesDir().getAbsoluteFile(), "permmgr");
		if (permDir.exists() == false)
			permDir.mkdirs();
		File outFile = new File(permDir, libname);
		if (outFile.exists() == true) 
			return outFile.getAbsolutePath();
		try {
			// 拷贝asset下的库文件至指定的目录
			InputStream is  = paramContext.getAssets().open("permmgr/" + libname);
			OutputStream dbOut = new FileOutputStream(outFile.getAbsolutePath());
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				dbOut.write(buffer, 0, length);
			}
			dbOut.flush();
			dbOut.close();
			is.close();
			// 赋可执行权限
			Log.e(TAG, "copy " + outFile.getAbsolutePath() + " success.");
			Runtime.getRuntime().exec("chmod 755 " + outFile.getPath()).waitFor();
			return outFile.getPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String copyNativeElf(Context paramContext, String elfFile) {
		// 检查文件是否是否存在
		File outFile = new File("/data/local/tmp");
		if (outFile.exists() == true) 
			return outFile.getAbsolutePath();
		try {
			// 拷贝asset下的库文件至指定的目录
			InputStream is  = paramContext.getAssets().open(elfFile);
			OutputStream dbOut = new FileOutputStream(outFile.getAbsolutePath());
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				dbOut.write(buffer, 0, length);
			}
			dbOut.flush();
			dbOut.close();
			is.close();
			// 赋可执行权限
			Runtime.getRuntime().exec("chmod 755 " + outFile.getPath()).waitFor();
			return outFile.getPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
