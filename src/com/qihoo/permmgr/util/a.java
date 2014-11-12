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


public class a {
	private static char[] a = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76,
			77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99,
			100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112,
			113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51,
			52, 53, 54, 55, 56, 57, 43, 47 };
	private static byte[] b;

	static {
		byte[] arrayOfByte = new byte[''];
		arrayOfByte[0] = -1;
		arrayOfByte[1] = -1;
		arrayOfByte[2] = -1;
		arrayOfByte[3] = -1;
		arrayOfByte[4] = -1;
		arrayOfByte[5] = -1;
		arrayOfByte[6] = -1;
		arrayOfByte[7] = -1;
		arrayOfByte[8] = -1;
		arrayOfByte[9] = -1;
		arrayOfByte[10] = -1;
		arrayOfByte[11] = -1;
		arrayOfByte[12] = -1;
		arrayOfByte[13] = -1;
		arrayOfByte[14] = -1;
		arrayOfByte[15] = -1;
		arrayOfByte[16] = -1;
		arrayOfByte[17] = -1;
		arrayOfByte[18] = -1;
		arrayOfByte[19] = -1;
		arrayOfByte[20] = -1;
		arrayOfByte[21] = -1;
		arrayOfByte[22] = -1;
		arrayOfByte[23] = -1;
		arrayOfByte[24] = -1;
		arrayOfByte[25] = -1;
		arrayOfByte[26] = -1;
		arrayOfByte[27] = -1;
		arrayOfByte[28] = -1;
		arrayOfByte[29] = -1;
		arrayOfByte[30] = -1;
		arrayOfByte[31] = -1;
		arrayOfByte[32] = -1;
		arrayOfByte[33] = -1;
		arrayOfByte[34] = -1;
		arrayOfByte[35] = -1;
		arrayOfByte[36] = -1;
		arrayOfByte[37] = -1;
		arrayOfByte[38] = -1;
		arrayOfByte[39] = -1;
		arrayOfByte[40] = -1;
		arrayOfByte[41] = -1;
		arrayOfByte[42] = -1;
		arrayOfByte[43] = 62;
		arrayOfByte[44] = -1;
		arrayOfByte[45] = -1;
		arrayOfByte[46] = -1;
		arrayOfByte[47] = 63;
		arrayOfByte[48] = 52;
		arrayOfByte[49] = 53;
		arrayOfByte[50] = 54;
		arrayOfByte[51] = 55;
		arrayOfByte[52] = 56;
		arrayOfByte[53] = 57;
		arrayOfByte[54] = 58;
		arrayOfByte[55] = 59;
		arrayOfByte[56] = 60;
		arrayOfByte[57] = 61;
		arrayOfByte[58] = -1;
		arrayOfByte[59] = -1;
		arrayOfByte[60] = -1;
		arrayOfByte[61] = -1;
		arrayOfByte[62] = -1;
		arrayOfByte[63] = -1;
		arrayOfByte[64] = -1;
		arrayOfByte[66] = 1;
		arrayOfByte[67] = 2;
		arrayOfByte[68] = 3;
		arrayOfByte[69] = 4;
		arrayOfByte[70] = 5;
		arrayOfByte[71] = 6;
		arrayOfByte[72] = 7;
		arrayOfByte[73] = 8;
		arrayOfByte[74] = 9;
		arrayOfByte[75] = 10;
		arrayOfByte[76] = 11;
		arrayOfByte[77] = 12;
		arrayOfByte[78] = 13;
		arrayOfByte[79] = 14;
		arrayOfByte[80] = 15;
		arrayOfByte[81] = 16;
		arrayOfByte[82] = 17;
		arrayOfByte[83] = 18;
		arrayOfByte[84] = 19;
		arrayOfByte[85] = 20;
		arrayOfByte[86] = 21;
		arrayOfByte[87] = 22;
		arrayOfByte[88] = 23;
		arrayOfByte[89] = 24;
		arrayOfByte[90] = 25;
		arrayOfByte[91] = -1;
		arrayOfByte[92] = -1;
		arrayOfByte[93] = -1;
		arrayOfByte[94] = -1;
		arrayOfByte[95] = -1;
		arrayOfByte[96] = -1;
		arrayOfByte[97] = 26;
		arrayOfByte[98] = 27;
		arrayOfByte[99] = 28;
		arrayOfByte[100] = 29;
		arrayOfByte[101] = 30;
		arrayOfByte[102] = 31;
		arrayOfByte[103] = 32;
		arrayOfByte[104] = 33;
		arrayOfByte[105] = 34;
		arrayOfByte[106] = 35;
		arrayOfByte[107] = 36;
		arrayOfByte[108] = 37;
		arrayOfByte[109] = 38;
		arrayOfByte[110] = 39;
		arrayOfByte[111] = 40;
		arrayOfByte[112] = 41;
		arrayOfByte[113] = 42;
		arrayOfByte[114] = 43;
		arrayOfByte[115] = 44;
		arrayOfByte[116] = 45;
		arrayOfByte[117] = 46;
		arrayOfByte[118] = 47;
		arrayOfByte[119] = 48;
		arrayOfByte[120] = 49;
		arrayOfByte[121] = 50;
		arrayOfByte[122] = 51;
		arrayOfByte[123] = -1;
		arrayOfByte[124] = -1;
		arrayOfByte[125] = -1;
		arrayOfByte[126] = -1;
		arrayOfByte[127] = -1;
		b = arrayOfByte;
	}

	public static String a(byte[] paramArrayOfByte) {
		StringBuffer localStringBuffer = new StringBuffer();
		int i = paramArrayOfByte.length;
		int j = 0;
		return localStringBuffer.toString();
	}

	public static byte[] a(String paramString)
	{
		return null;
	}
}