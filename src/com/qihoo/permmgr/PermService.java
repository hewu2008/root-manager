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

import com.qihoo.permmgr.util.e;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Process;

public class PermService extends Service {
	protected static final String STATEROOTING = "com.qihoo.root.rooting";
	protected static final String STATEROOTOVER = "com.qihoo.root.rootover";
	public static String mPermmgrRootDir = "";
	private final f mBinder = new h(this);
	private Context mContext;
	private i updateState;

	private void registerBr() {
		this.updateState = new i(this);
		IntentFilter localIntentFilter = new IntentFilter();
		localIntentFilter.addAction("com.qihoo.root.rooting");
		localIntentFilter.addAction("com.qihoo.root.rootover");
		registerReceiver(this.updateState, localIntentFilter);
	}

	public String getErrKey() {
		return PermManager.getInstance(this).getErrKey();
	}

	public String getOutKey() {
		return PermManager.getInstance(this).getOutKey();
	}

	public IBinder onBind(Intent paramIntent) {
		mPermmgrRootDir = getFilesDir() + "/permmgr/";
		registerBr();
		return this.mBinder;
	}

	public void onCreate() {
		super.onCreate();
	}

	public void onDestroy() {
		try {
			unregisterReceiver(this.updateState);
			e.a("------root end and kill----" + Process.myPid());
			Process.killProcess(Process.myPid());
			super.onDestroy();
		} catch (Exception localException) {
			if (!b.a)
				localException.printStackTrace();
		}
	}

	public void onRebind(Intent paramIntent) {
		super.onRebind(paramIntent);
	}

	public boolean onUnbind(Intent paramIntent) {
		return super.onUnbind(paramIntent);
	}

	public void setContext(Context paramContext) {
		this.mContext = paramContext;
	}
}