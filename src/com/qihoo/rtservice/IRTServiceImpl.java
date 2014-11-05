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

import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.Log;

public class IRTServiceImpl extends IRootService.Stub {
	private static final String TAG = IRTServiceImpl.class.getSimpleName();
	public static final String SERVICE_NAME = "qh_root_service";
	public static void main(String[] args) {
		Log.e(TAG, "main function");
		IRTServiceImpl service = new IRTServiceImpl();
		ServiceManager.addService(SERVICE_NAME, service);
		Looper.prepareMainLooper();
		Looper.loop();
	}

	public String exec(String[] args) {
		Log.e(TAG, "exec function called.");
		return null;
	}

	@Override
	public int stop() throws RemoteException {
		Log.e(TAG, "stop function called.");
		return 0;
	}

	@Override
	public boolean install(String path) throws RemoteException {
		String[] command = new String[]{Utils.getShPath(), "-c", "pm install -r " + path};
		String result = Utils.exec(new File("/"), null, command);
		if (TextUtils.isEmpty(result) == false 
				&& result.indexOf("success") != -1)
			return true;
		return false;
	}
}