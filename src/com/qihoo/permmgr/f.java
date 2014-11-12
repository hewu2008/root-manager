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

import java.util.Map;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class f extends Binder implements IPermMgrService {
	public f() {
		attachInterface(this, "com.qihoo.permmgr.IPermMgrService");
	}

	public static IPermMgrService a(IBinder paramIBinder) {
		if (paramIBinder == null)
			return null;
		IInterface localIInterface = paramIBinder
				.queryLocalInterface("com.qihoo.permmgr.IPermMgrService");
		if ((localIInterface != null)
				&& ((localIInterface instanceof IPermMgrService)))
			return (IPermMgrService) localIInterface;
		return new g(paramIBinder);
	}

	public IBinder asBinder() {
		return this;
	}

	public boolean onTransact(int paramInt1, Parcel paramParcel1,
			Parcel paramParcel2, int paramInt2) {
		boolean bool3 = false;
		switch (paramInt1) {
		default:
			try {
				return super.onTransact(paramInt1, paramParcel1, paramParcel2,
						paramInt2);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		case 1598968902:
			paramParcel2.writeString("com.qihoo.permmgr.IPermMgrService");
			return true;
		case 1:
			paramParcel1.enforceInterface("com.qihoo.permmgr.IPermMgrService");
			String str2 = paramParcel1.readString();
			if (paramParcel1.readInt() != 0) {
				bool3 = true;
				if (paramParcel1.readInt() == 0)
					break ;
			}
			for (Bundle localBundle = (Bundle) Bundle.CREATOR
					.createFromParcel(paramParcel1);; localBundle = null) {
				int m = getRootForSafe(str2, bool3, localBundle,
						d.asInterface(paramParcel1.readStrongBinder()));
				paramParcel2.writeNoException();
				paramParcel2.writeInt(m);
				return true;
			}
		case 2:
			paramParcel1.enforceInterface("com.qihoo.permmgr.IPermMgrService");
			int k = getVersion();
			paramParcel2.writeNoException();
			paramParcel2.writeInt(k);
			return true;
		case 3:
			paramParcel1.enforceInterface("com.qihoo.permmgr.IPermMgrService");
			String str1 = paramParcel1.readString();
			int j = paramParcel1.readInt();
			boolean bool2 = false;
			if (j != 0)
				bool2 = true;
			Map localMap2 = checkIsSupportForSafe(str1, bool2);
			paramParcel2.writeNoException();
			paramParcel2.writeMap(localMap2);
			return true;
		case 4:
			label158: paramParcel1
					.enforceInterface("com.qihoo.permmgr.IPermMgrService");
			Map localMap1 = doCommand(paramParcel1.readString(),
					paramParcel1.readString());
			paramParcel2.writeNoException();
			paramParcel2.writeMap(localMap1);
			return true;
		case 5:
		}
		paramParcel1.enforceInterface("com.qihoo.permmgr.IPermMgrService");
		boolean bool1 = checkDaemonIsRunning();
		paramParcel2.writeNoException();
		int i = 0;
		if (bool1)
			i = 1;
		paramParcel2.writeInt(i);
		return true;
	}
}