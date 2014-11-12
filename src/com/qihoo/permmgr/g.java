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

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class g implements IPermMgrService {
	private IBinder a;

	g(IBinder paramIBinder) {
		this.a = paramIBinder;
	}

	public IBinder asBinder() {
		return this.a;
	}

	public boolean checkDaemonIsRunning() {
		Parcel localParcel1 = Parcel.obtain();
		Parcel localParcel2 = Parcel.obtain();
		int i = 0;
		try {
			localParcel1.writeInterfaceToken("com.qihoo.permmgr.IPermMgrService");
			this.a.transact(5, localParcel1, localParcel2, 0);
			localParcel2.readException();
			i = localParcel2.readInt();
		} catch (RemoteException e) {
			e.printStackTrace();
			i = 0;
		} finally {
			localParcel2.recycle();
			localParcel1.recycle();
		}
		return i != 0;
	}

	public Map checkIsSupportForSafe(String paramString, boolean paramBoolean) {
		Parcel localParcel1 = Parcel.obtain();
		Parcel localParcel2 = Parcel.obtain();
		try {
			localParcel1.writeInterfaceToken("com.qihoo.permmgr.IPermMgrService");
			localParcel1.writeString(paramString);
			int i = 0;
			if (paramBoolean) i = 1;
			localParcel1.writeInt(i);
			this.a.transact(3, localParcel1, localParcel2, 0);
			localParcel2.readException();
			HashMap localHashMap = localParcel2.readHashMap(getClass().getClassLoader());
			return localHashMap;
		} catch (RemoteException e) {
			return null;
		} finally {
			localParcel2.recycle();
			localParcel1.recycle();
		}
	}

	public Map doCommand(String paramString1, String paramString2) {
		Parcel localParcel1 = Parcel.obtain();
		Parcel localParcel2 = Parcel.obtain();
		try {
			localParcel1.writeInterfaceToken("com.qihoo.permmgr.IPermMgrService");
			localParcel1.writeString(paramString1);
			localParcel1.writeString(paramString2);
			this.a.transact(4, localParcel1, localParcel2, 0);
			localParcel2.readException();
			HashMap localHashMap = localParcel2.readHashMap(getClass().getClassLoader());
			return localHashMap;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		} finally {
			localParcel2.recycle();
			localParcel1.recycle();
		}
	}

	public int getRootForSafe(String paramString, boolean paramBoolean,
			Bundle paramBundle, c paramc) {
		int i = 1;
		Parcel localParcel1 = Parcel.obtain();
		Parcel localParcel2 = Parcel.obtain();
		IBinder localIBinder = null;
		try {
			localParcel1.writeInterfaceToken("com.qihoo.permmgr.IPermMgrService");
			localParcel1.writeString(paramString);
			localParcel1.writeInt(i);
			localParcel1.writeInt(1);
			paramBundle.writeToParcel(localParcel1, 0);
			if (paramc != null) {
				localIBinder = paramc.asBinder();
				localParcel1.writeStrongBinder(localIBinder);
				this.a.transact(1, localParcel1, localParcel2, 0);
				localParcel2.readException();
				int j = localParcel2.readInt();
				localParcel1.writeInt(0);
				return j;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} finally {
			localParcel2.recycle();
			localParcel1.recycle();
		}
		return i;
	}

	public int getVersion() {
		Parcel localParcel1 = Parcel.obtain();
		Parcel localParcel2 = Parcel.obtain();
		try {
			localParcel1.writeInterfaceToken("com.qihoo.permmgr.IPermMgrService");
			this.a.transact(2, localParcel1, localParcel2, 0);
			localParcel2.readException();
			int i = localParcel2.readInt();
			return i;
		} catch (RemoteException e) {
			e.printStackTrace();
			return 0;
		} finally {
			localParcel2.recycle();
			localParcel1.recycle();
		}
	}
}
