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

import java.net.URLDecoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
	public static String key1 = "OjmMdGxTcV5k0ueG";
	public static String b = "OjmMdGxTcV5k0ueG";
	public static byte[] c = null;
	public static byte[] d = null;

	static {
		try {
			c = key1.getBytes("UTF-8");
			d = b.getBytes("UTF-8");
		} catch (Exception localException) {
			System.out.println("dont't init the AES KEY");
		}
	}

	public static String b(String paramString) {
		try {
			URLDecoder.decode(paramString, "UTF-8");
			byte[] arrayOfByte = Base64Utils.decode(paramString);
			SecretKeySpec localSecretKeySpec = new SecretKeySpec(c, "AES");
			Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			localCipher.init(2, localSecretKeySpec, new IvParameterSpec(d));
			String str = new String(localCipher.doFinal(arrayOfByte), "UTF-8");
			return str;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return null;
	}
}
