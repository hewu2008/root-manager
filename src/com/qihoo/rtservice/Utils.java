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

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import android.text.TextUtils;
import android.util.Log;

public class Utils {
	static final String TAG = Utils.class.getSimpleName();
	
	static String readStream(InputStream stream) throws IOException {
		final char[] buffer = new char[0x10000];
		StringBuilder out = new StringBuilder();
		Reader in = new InputStreamReader(stream, "UTF-8");
		int read;
		do { 
			read = in.read(buffer, 0, buffer.length);
			if (read > 0)
				out.append(buffer, 0, read);
		} while (read >= 0);
		return out.toString();
	}

	static int getHashMapIndex(LinkedHashMap<?, ?> map, Object search) {
		Set<?> keys = map.keySet();
		Iterator<?> i = keys.iterator();
		Object curr;
		int count = -1;
		do {
			curr = i.next();
			count++;
			if (curr.equals(search))
				return count;
		} while (i.hasNext());
		return -1;
	}

	static String[] SU_OPTIONS = { 
			"/data/bin/su", 
			"/system/bin/su",
			"/system/xbin/su", };
	
	public static String exec(File dir, String[] command) {
		Process process = null;
		DataOutputStream os = null;
		String result = "";
		try {
			Log.d(TAG, command.toString());
			ProcessBuilder pb = new ProcessBuilder(command);
			pb.redirectErrorStream(true);
			pb.directory(dir);
			Map<String, String> env = pb.environment();
			env.put("CLASSPATH", "/data/local/com.qihoo.appstore-2.apk");
			process = pb.start();
			process.waitFor();
			result = Utils.readStream(process.getInputStream());
			Log.e(TAG, "Process returned with " + process.exitValue());
			Log.e(TAG, "Process stdout was: " + result);
			return result;
		} catch (Exception e) {
			Log.e(TAG, "Failed to run command", e);
			return result;
		} finally {
			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			if (process != null) {
				try {
					process.exitValue();
				} catch (IllegalThreadStateException e) {
					process.destroy();
				}
			}
		}
	}

	public static boolean isProcessAlive(Process p) {
		try {
			p.exitValue();
			return false;
		} catch (IllegalThreadStateException e) {
			return true;
		}
	}

	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static String getSuPath() {
		for (String p : SU_OPTIONS) {
			File su = new File(p);
			if (su.exists()) {
				return p;
			} 
		}
		return null;
	}
	
	public static String getShPath() {
		String path = System.getenv("PATH");
		if ((path != null) && (path.length() > 0)) {
			String[] line = path.split(":");
			int i = line.length;
			for (int j = 0; j < i; j++) {
				File file = new File(line[j], "sh");
				if (file.exists())
					return file.getPath();
			}
		}
		return null;
	}

	public static boolean hasSuCmd() {
		try {
			String str = getSuPath();
			if (str != null)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static void checkPermission(String output) {
		if (TextUtils.isEmpty(output))
			throw new SecurityException("Permission denied");
		if (-1 == output.indexOf("root"))
			throw new SecurityException("Permission denied");
	}
}