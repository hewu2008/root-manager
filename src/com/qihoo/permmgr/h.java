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

import android.content.Context;
import android.os.Bundle;

class h extends f {
	Context localObject = null;
	h(PermService paramPermService) {
	}

	public boolean checkDaemonIsRunning() {
		// return PermManager.getInstance((Context) localObject).checkDaemonIsRunning();
		return true;
	}

	public Map checkIsSupportForSafe(String paramString, boolean paramBoolean) {
		// return PermManager.getInstance((Context) localObject).checkIsSupportForSafe(paramString, paramBoolean);
		return null;
	}

	public Map doCommand(String paramString1, String paramString2) {
		// return PermManager.getInstance((Context) localObject).doCommand(paramString1, paramString2);
		return null;
	}

	public int getRootForSafe(String paramString, boolean paramBoolean,
			Bundle paramBundle, c paramc) {
		// return PermManager.getInstance((Context) localObject).getRoot(paramString, paramBoolean, paramBundle, paramc);
		return 0;
	}

	public int getVersion() {
		// return Integer.parseInt(PermManager.getInstance((Context) localObject).getDaemonVersion());
		return 0;
	}
}
