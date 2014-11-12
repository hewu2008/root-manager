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

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class e implements c {
	private IBinder a;

	e(IBinder paramIBinder) {
		this.a = paramIBinder;
	}

	public IBinder asBinder() {
		return this.a;
	}

	public boolean onCheckRootServerExist() {
		int i = 1;
		Parcel localParcel1 = Parcel.obtain();
		Parcel localParcel2 = Parcel.obtain();
		try {
			localParcel1.writeInterfaceToken("com.qihoo.permmgr.CheckRootServerCallback");
			this.a.transact(1, localParcel1, localParcel2, 0);
			localParcel2.readException();
			int j = localParcel2.readInt();
			if (j != 0)
				return true;
		} catch (RemoteException e) {
			e.printStackTrace();
		} finally {
			localParcel2.recycle();
			localParcel1.recycle();
		}
		return false;
	}
}
