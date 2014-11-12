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

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class d extends Binder implements c {
	public d() {
		attachInterface(this, "com.qihoo.permmgr.CheckRootServerCallback");
	}

	public static c asInterface(IBinder paramIBinder) {
		if (paramIBinder == null)
			return null;
		IInterface localIInterface = paramIBinder.queryLocalInterface("com.qihoo.permmgr.CheckRootServerCallback");
		if ((localIInterface != null) && ((localIInterface instanceof c)))
			return (c) localIInterface;
		return new e(paramIBinder);
	}

	public IBinder asBinder() {
		return this;
	}

	public boolean onTransact(int paramInt1, Parcel paramParcel1,
			Parcel paramParcel2, int paramInt2) {
		switch (paramInt1) {
		default:
			try {
				return super.onTransact(paramInt1, paramParcel1, paramParcel2,
						paramInt2);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		case 1598968902:
			paramParcel2
					.writeString("com.qihoo.permmgr.CheckRootServerCallback");
			return true;
		case 1:
		}
		paramParcel1
				.enforceInterface("com.qihoo.permmgr.CheckRootServerCallback");
		boolean bool = onCheckRootServerExist();
		paramParcel2.writeNoException();
		if (bool)
			;
		for (int i = 1;; i = 0) {
			paramParcel2.writeInt(i);
			return true;
		}
	}
}
