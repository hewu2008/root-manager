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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class b {
	public static ArrayList a = new ArrayList();

	static {
		a.add("com.noshufou.android.su");
		a.add("com.qihoo.root");
		a.add("com.lbe.security.miui");
		a.add("com.lbe.security.su");
		a.add("com.lbe.security.shuame");
		a.add("eu.chainfire.supersu");
		a.add("com.miui.uac");
	}

	/**
	 * 从环境变量中查找su的全路径
	 */
	public static String getSuPath() {
		String path = System.getenv("PATH");
		String[] pathArray = new String[]{};
		int pathCount = 0;
		if ((path != null) && (path.length() > 0)) {
			pathArray = path.split(":");
			pathCount = pathArray.length;
		}
		for (int j = 0; j < pathCount; j++) {
			File suFile = new File(pathArray[j], "su");
			if (suFile.exists())
				return suFile.getPath();
		}
		return null;
	}
	
	/**
	 * 执行单条指令
	 */
	public static String a(File paramFile, String command) {
		String suPath = getSuPath();
		if (suPath != null) {
			Runtime runtime = Runtime.getRuntime();
			try {
				Process process = runtime.exec(new String[] {suPath, "-c", command});
				process.waitFor();
				InputStream is = process.getInputStream();
				String result = readStream(is, "utf-8");
				is.close();
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 执行多条指令
	 */
	public static String a(File paramFile, String[] paramArrayOfString) {
		try {
			ProcessBuilder pb = new ProcessBuilder();
			pb.command(paramArrayOfString);
			pb.redirectErrorStream(true);
			pb.directory(paramFile);
			Map<String, String> env = pb.environment();
			Process procees = pb.start();
			InputStream is = procees.getInputStream();
			String result = readStream(is, "utf-8");
			is.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 读取缓冲区
	 */
	private static String readStream(InputStream is, String charsetName) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is, charsetName));
			char[] buffer = new char[1024];
			StringBuilder sb = new StringBuilder();
			while (true) {
				int i = br.read(buffer);
				if (i == -1) {
					return sb.toString();
				} 
				sb.append(buffer, 0, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 不以su身份执行单条命令
	 */
	public static String a(String paramString) {
		try {
			Process localProcess = Runtime.getRuntime().exec(paramString);
			BufferedReader localBufferedReader = new BufferedReader(
					new InputStreamReader(localProcess.getInputStream()));
			char[] arrayOfChar = new char[4096];
			StringBuffer localStringBuffer = new StringBuffer();
			while (true) {
				int i = localBufferedReader.read(arrayOfChar);
				if (i <= 0) {
					localBufferedReader.close();
					localProcess.waitFor();
					return localStringBuffer.toString();
				}
				localStringBuffer.append(arrayOfChar, 0, i);
			}
		} catch (IOException localIOException) {
			return "unknow";
		} catch (InterruptedException localInterruptedException) {
			return localInterruptedException.getMessage();
		}
	}

	public static void b(String paramString) {
		try {
			Process localProcess = Runtime.getRuntime().exec(paramString);
			localProcess.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 是否有su文件
	 */
	public static boolean b() {
		try {
			String str = getSuPath();
			if (str != null)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
