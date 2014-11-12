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

public class c
{
  // ERROR //
  public static void a(android.content.Context paramContext, java.lang.String paramString, java.io.File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 14	java/io/BufferedInputStream
    //   5: dup
    //   6: aload_0
    //   7: invokevirtual 20	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   10: new 22	java/lang/StringBuilder
    //   13: dup
    //   14: ldc 24
    //   16: invokespecial 28	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   19: aload_1
    //   20: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   26: invokevirtual 42	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   29: invokespecial 45	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   32: astore 4
    //   34: new 47	java/io/FileOutputStream
    //   37: dup
    //   38: aload_2
    //   39: invokespecial 50	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   42: astore 5
    //   44: sipush 8192
    //   47: newarray byte
    //   49: astore 12
    //   51: aload 4
    //   53: aload 12
    //   55: invokevirtual 54	java/io/BufferedInputStream:read	([B)I
    //   58: istore 13
    //   60: iload 13
    //   62: ifgt +24 -> 86
    //   65: aload 5
    //   67: invokevirtual 58	java/io/FileOutputStream:close	()V
    //   70: aload 4
    //   72: invokevirtual 59	java/io/BufferedInputStream:close	()V
    //   75: aload 4
    //   77: ifnull +8 -> 85
    //   80: aload 4
    //   82: invokevirtual 59	java/io/BufferedInputStream:close	()V
    //   85: return
    //   86: aload 5
    //   88: aload 12
    //   90: iconst_0
    //   91: iload 13
    //   93: invokevirtual 63	java/io/FileOutputStream:write	([BII)V
    //   96: goto -45 -> 51
    //   99: astore 10
    //   101: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   104: ifeq +8 -> 112
    //   107: aload 10
    //   109: invokevirtual 71	java/io/FileNotFoundException:printStackTrace	()V
    //   112: aload 4
    //   114: ifnull -29 -> 85
    //   117: aload 4
    //   119: invokevirtual 59	java/io/BufferedInputStream:close	()V
    //   122: return
    //   123: astore 11
    //   125: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   128: ifeq -43 -> 85
    //   131: aload 11
    //   133: invokevirtual 72	java/lang/Exception:printStackTrace	()V
    //   136: return
    //   137: astore 6
    //   139: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   142: ifeq +8 -> 150
    //   145: aload 6
    //   147: invokevirtual 73	java/io/IOException:printStackTrace	()V
    //   150: aload_3
    //   151: ifnull -66 -> 85
    //   154: aload_3
    //   155: invokevirtual 59	java/io/BufferedInputStream:close	()V
    //   158: return
    //   159: astore 9
    //   161: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   164: ifeq -79 -> 85
    //   167: aload 9
    //   169: invokevirtual 72	java/lang/Exception:printStackTrace	()V
    //   172: return
    //   173: astore 7
    //   175: aload_3
    //   176: ifnull +7 -> 183
    //   179: aload_3
    //   180: invokevirtual 59	java/io/BufferedInputStream:close	()V
    //   183: aload 7
    //   185: athrow
    //   186: astore 8
    //   188: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   191: ifeq -8 -> 183
    //   194: aload 8
    //   196: invokevirtual 72	java/lang/Exception:printStackTrace	()V
    //   199: goto -16 -> 183
    //   202: astore 14
    //   204: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   207: ifeq -122 -> 85
    //   210: aload 14
    //   212: invokevirtual 72	java/lang/Exception:printStackTrace	()V
    //   215: return
    //   216: astore 7
    //   218: aload 4
    //   220: astore_3
    //   221: goto -46 -> 175
    //   224: astore 6
    //   226: aload 4
    //   228: astore_3
    //   229: goto -90 -> 139
    //   232: astore 10
    //   234: aconst_null
    //   235: astore 4
    //   237: goto -136 -> 101
    //
    // Exception table:
    //   from	to	target	type
    //   34	51	99	java/io/FileNotFoundException
    //   51	60	99	java/io/FileNotFoundException
    //   65	75	99	java/io/FileNotFoundException
    //   86	96	99	java/io/FileNotFoundException
    //   117	122	123	java/lang/Exception
    //   2	34	137	java/io/IOException
    //   154	158	159	java/lang/Exception
    //   2	34	173	finally
    //   139	150	173	finally
    //   179	183	186	java/lang/Exception
    //   80	85	202	java/lang/Exception
    //   34	51	216	finally
    //   51	60	216	finally
    //   65	75	216	finally
    //   86	96	216	finally
    //   101	112	216	finally
    //   34	51	224	java/io/IOException
    //   51	60	224	java/io/IOException
    //   65	75	224	java/io/IOException
    //   86	96	224	java/io/IOException
    //   2	34	232	java/io/FileNotFoundException
  }

  // ERROR //
  public static void a(java.lang.String paramString1, java.lang.String paramString2)
  {
    // Byte code:
    //   0: new 14	java/io/BufferedInputStream
    //   3: dup
    //   4: new 76	java/io/FileInputStream
    //   7: dup
    //   8: new 78	java/io/File
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 79	java/io/File:<init>	(Ljava/lang/String;)V
    //   16: invokespecial 80	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   19: invokespecial 45	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   22: astore_2
    //   23: new 47	java/io/FileOutputStream
    //   26: dup
    //   27: new 78	java/io/File
    //   30: dup
    //   31: aload_1
    //   32: invokespecial 79	java/io/File:<init>	(Ljava/lang/String;)V
    //   35: invokespecial 50	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   38: astore_3
    //   39: sipush 8192
    //   42: newarray byte
    //   44: astore 10
    //   46: aload_2
    //   47: aload 10
    //   49: invokevirtual 54	java/io/BufferedInputStream:read	([B)I
    //   52: istore 11
    //   54: iload 11
    //   56: ifgt +20 -> 76
    //   59: aload_3
    //   60: invokevirtual 58	java/io/FileOutputStream:close	()V
    //   63: aload_2
    //   64: invokevirtual 59	java/io/BufferedInputStream:close	()V
    //   67: aload_2
    //   68: ifnull +7 -> 75
    //   71: aload_2
    //   72: invokevirtual 59	java/io/BufferedInputStream:close	()V
    //   75: return
    //   76: aload_3
    //   77: aload 10
    //   79: iconst_0
    //   80: iload 11
    //   82: invokevirtual 63	java/io/FileOutputStream:write	([BII)V
    //   85: goto -39 -> 46
    //   88: astore 8
    //   90: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   93: ifeq +8 -> 101
    //   96: aload 8
    //   98: invokevirtual 71	java/io/FileNotFoundException:printStackTrace	()V
    //   101: aload_2
    //   102: ifnull -27 -> 75
    //   105: aload_2
    //   106: invokevirtual 59	java/io/BufferedInputStream:close	()V
    //   109: return
    //   110: astore 9
    //   112: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   115: ifeq -40 -> 75
    //   118: aload 9
    //   120: invokevirtual 72	java/lang/Exception:printStackTrace	()V
    //   123: return
    //   124: astore 4
    //   126: aconst_null
    //   127: astore_2
    //   128: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   131: ifeq +8 -> 139
    //   134: aload 4
    //   136: invokevirtual 73	java/io/IOException:printStackTrace	()V
    //   139: aload_2
    //   140: ifnull -65 -> 75
    //   143: aload_2
    //   144: invokevirtual 59	java/io/BufferedInputStream:close	()V
    //   147: return
    //   148: astore 7
    //   150: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   153: ifeq -78 -> 75
    //   156: aload 7
    //   158: invokevirtual 72	java/lang/Exception:printStackTrace	()V
    //   161: return
    //   162: astore 5
    //   164: aconst_null
    //   165: astore_2
    //   166: aload_2
    //   167: ifnull +7 -> 174
    //   170: aload_2
    //   171: invokevirtual 59	java/io/BufferedInputStream:close	()V
    //   174: aload 5
    //   176: athrow
    //   177: astore 6
    //   179: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   182: ifeq -8 -> 174
    //   185: aload 6
    //   187: invokevirtual 72	java/lang/Exception:printStackTrace	()V
    //   190: goto -16 -> 174
    //   193: astore 12
    //   195: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   198: ifeq -123 -> 75
    //   201: aload 12
    //   203: invokevirtual 72	java/lang/Exception:printStackTrace	()V
    //   206: return
    //   207: astore 5
    //   209: goto -43 -> 166
    //   212: astore 4
    //   214: goto -86 -> 128
    //   217: astore 8
    //   219: aconst_null
    //   220: astore_2
    //   221: goto -131 -> 90
    //
    // Exception table:
    //   from	to	target	type
    //   23	46	88	java/io/FileNotFoundException
    //   46	54	88	java/io/FileNotFoundException
    //   59	67	88	java/io/FileNotFoundException
    //   76	85	88	java/io/FileNotFoundException
    //   105	109	110	java/lang/Exception
    //   0	23	124	java/io/IOException
    //   143	147	148	java/lang/Exception
    //   0	23	162	finally
    //   170	174	177	java/lang/Exception
    //   71	75	193	java/lang/Exception
    //   23	46	207	finally
    //   46	54	207	finally
    //   59	67	207	finally
    //   76	85	207	finally
    //   90	101	207	finally
    //   128	139	207	finally
    //   23	46	212	java/io/IOException
    //   46	54	212	java/io/IOException
    //   59	67	212	java/io/IOException
    //   76	85	212	java/io/IOException
    //   0	23	217	java/io/FileNotFoundException
  }

  // ERROR //
  public static boolean a(byte[] paramArrayOfByte, java.io.File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 47	java/io/FileOutputStream
    //   5: dup
    //   6: aload_1
    //   7: iconst_1
    //   8: invokespecial 84	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   11: astore_3
    //   12: aload_3
    //   13: aload_0
    //   14: invokevirtual 87	java/io/FileOutputStream:write	([B)V
    //   17: aload_3
    //   18: ifnull +7 -> 25
    //   21: aload_3
    //   22: invokevirtual 58	java/io/FileOutputStream:close	()V
    //   25: iconst_1
    //   26: istore 7
    //   28: iload 7
    //   30: ireturn
    //   31: astore 4
    //   33: aconst_null
    //   34: astore_3
    //   35: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   38: ifeq +8 -> 46
    //   41: aload 4
    //   43: invokevirtual 71	java/io/FileNotFoundException:printStackTrace	()V
    //   46: iconst_0
    //   47: istore 7
    //   49: aload_3
    //   50: ifnull -22 -> 28
    //   53: aload_3
    //   54: invokevirtual 58	java/io/FileOutputStream:close	()V
    //   57: iconst_0
    //   58: ireturn
    //   59: astore 8
    //   61: iconst_0
    //   62: ireturn
    //   63: astore 9
    //   65: getstatic 68	com/qihoo/permmgr/b:a	Z
    //   68: ifeq +8 -> 76
    //   71: aload 9
    //   73: invokevirtual 72	java/lang/Exception:printStackTrace	()V
    //   76: iconst_0
    //   77: istore 7
    //   79: aload_2
    //   80: ifnull -52 -> 28
    //   83: aload_2
    //   84: invokevirtual 58	java/io/FileOutputStream:close	()V
    //   87: iconst_0
    //   88: ireturn
    //   89: astore 10
    //   91: iconst_0
    //   92: ireturn
    //   93: astore 5
    //   95: aload_2
    //   96: ifnull +7 -> 103
    //   99: aload_2
    //   100: invokevirtual 58	java/io/FileOutputStream:close	()V
    //   103: aload 5
    //   105: athrow
    //   106: astore 6
    //   108: iconst_0
    //   109: ireturn
    //   110: astore 11
    //   112: iconst_0
    //   113: ireturn
    //   114: astore 5
    //   116: aload_3
    //   117: astore_2
    //   118: goto -23 -> 95
    //   121: astore 9
    //   123: aload_3
    //   124: astore_2
    //   125: goto -60 -> 65
    //   128: astore 4
    //   130: goto -95 -> 35
    //
    // Exception table:
    //   from	to	target	type
    //   2	12	31	java/io/FileNotFoundException
    //   53	57	59	java/io/IOException
    //   2	12	63	java/lang/Exception
    //   83	87	89	java/io/IOException
    //   2	12	93	finally
    //   65	76	93	finally
    //   99	103	106	java/io/IOException
    //   21	25	110	java/io/IOException
    //   12	17	114	finally
    //   35	46	114	finally
    //   12	17	121	java/lang/Exception
    //   12	17	128	java/io/FileNotFoundException
	 return true;
  }
}
