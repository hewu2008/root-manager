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

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

class i extends Thread {
	private String b = "";
	private String c = "";
	private String d = "";

	public i(g paramg, String paramString1, String paramString2,
			String paramString3) {
		this.b = paramString1;
		this.c = paramString2;
		this.d = paramString3;
	}
	
	@Override
	public void run() {
		Log.e("MyLog", Thread.currentThread().getName() + "--正在执行。。。" + this.b + "---"
				+ this.d + "---" + "---" + this.c);
		try {
			this.c = "http://api.shuaji.360.cn/c/getSolution?model=2013022&target=1&buildno=3.4.5&version=4.2.1&platform=mtk&pkg=2004&mid=fcc62dd788bfaf3bc53abf61cae9708d&new=1&src=1";
			HttpGet localHttpGet2 = new HttpGet(this.c);
			HttpResponse localHttpResponse = new DefaultHttpClient()
					.execute(localHttpGet2);
			int statusCode = localHttpResponse.getStatusLine().getStatusCode();
			String result = EntityUtils.toString(localHttpResponse.getEntity());
			Log.e("MyLog", result);
			Log.e("MyLog", AESUtils.b(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}