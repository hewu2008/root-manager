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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class i extends BroadcastReceiver {
	i(PermService paramPermService) {
	}

	public void onReceive(Context paramContext, Intent paramIntent) {
		if (paramIntent.getAction().equals("com.qihoo.root.rooting")) {
			a.f = true;
		} else if (paramIntent.getAction().equals("com.qihoo.root.rootover")) {
			a.f = false;
		}
	}
}