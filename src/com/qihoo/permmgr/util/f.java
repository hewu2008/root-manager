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

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class f {
	public static String a(String paramString) {
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramString.getBytes());
			byte[] arrayOfByte = localMessageDigest.digest();
			StringBuffer localStringBuffer = new StringBuffer("");
			for (int i = 0;; i++) {
				if (i >= arrayOfByte.length)
					return localStringBuffer.toString();
				int j = arrayOfByte[i];
				if (j < 0)
					j += 256;
				if (j < 16)
					localStringBuffer.append("0");
				localStringBuffer.append(Integer.toHexString(j));
			}
		} catch (Exception localException) {
		}
		return null;
	}

	private static String a(byte[] paramArrayOfByte) {
		int i = 0;
		char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98,
				99, 100, 101, 102 };
		char[] arrayOfChar2 = new char[32];
		int j = 0;
		while (true) {
			if (i >= 16)
				return new String(arrayOfChar2);
			int k = paramArrayOfByte[i];
			int m = j + 1;
			arrayOfChar2[j] = arrayOfChar1[(0xF & k >>> 4)];
			j = m + 1;
			arrayOfChar2[m] = arrayOfChar1[(k & 0xF)];
			i++;
		}
	}

	private static MessageDigest a() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String b(String paramString) {
		try {
			String str = "";
			File localFile = new File(paramString);
			MessageDigest localMessageDigest = null;
			FileInputStream localFileInputStream = null;
			byte[] arrayOfByte = null;
			if (localFile.exists()) {
				localMessageDigest = a();
				localFileInputStream = new FileInputStream(localFile);
				arrayOfByte = new byte[2048];
			} else {
				return "";
			}
			while (true) {
				int i = localFileInputStream.read(arrayOfByte);
				if (i == -1) {
					localFileInputStream.close();
					str = a(localMessageDigest.digest());
					return str;
				}
				localMessageDigest.update(arrayOfByte, 0, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}