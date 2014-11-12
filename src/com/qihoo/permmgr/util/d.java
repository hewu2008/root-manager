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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class d {
	private static HashMap a = new HashMap();
	private static final Object b;
	private static final Locale c;
	private static String d;

	static {
		a.put("cmwap", "10.0.0.172:80");
		a.put("cmnet", "none");
		a.put("uniwap", "10.0.0.172:80");
		a.put("uninet", "none");
		a.put("ctwap", "10.0.0.200:80");
		a.put("ctnet", "none");
		a.put("3gwap", "10.0.0.172:80");
		a.put("3gnet", "none");
		b = new Object();
		c = Locale.getDefault();
		d = null;
	}

	public static String a(String paramString, int paramInt) {
		try {
			HttpGet localHttpGet = new HttpGet(paramString);
			DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
			localDefaultHttpClient.getParams().setParameter(
					"http.connection.timeout", Integer.valueOf(paramInt));
			localDefaultHttpClient.getParams().setParameter(
					"http.socket.timeout", Integer.valueOf(paramInt));
			HttpResponse localHttpResponse = localDefaultHttpClient
					.execute(localHttpGet);
			if (200 == localHttpResponse.getStatusLine().getStatusCode()) {
				String str = EntityUtils
						.toString(localHttpResponse.getEntity());
				return str;
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return null;
	}

	public static boolean a(Context paramContext) {
		try {
			ConnectivityManager localConnectivityManager = (ConnectivityManager) paramContext
					.getSystemService("connectivity");
			if (localConnectivityManager.getActiveNetworkInfo() != null) {
				boolean bool = localConnectivityManager.getActiveNetworkInfo()
						.isAvailable();
				return bool;
			}
		} catch (Exception localException) {
			localException.printStackTrace();
			return true;
		}
		return false;
	}
}
