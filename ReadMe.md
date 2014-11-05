# 360秒装功能分析
最新版**360手机助手**提供了**秒装**的功能，能够在已经取得root权限的手机上，不调用su进行授权，直接安装app从而能够有效减少应用安装的时间。

## 一、功能模块
360秒装功能主要分为**permmgr**和**rtservice**两个模块， permmgr负责加载so库，实现root提权功能； rtservice模块在permmgr获得root权限的基础上以root身份启动qh_root_service进程，appstore通过跨进行通讯控制该进程执行秒装操作。

**permmgr**模块的com.qihoo.permmgr目录下主要包括以下几个核心文件：
<table>
<tr><td>类名</td><td>作用</td></tr>
<tr><td>IPermMgrService</td><td>定义权限管理服务接口</td></tr>
<tr><td>LocalRoot</td><td>声明jni函数调用lib360.so接口</td></tr>
<tr><td>PermService</td><td>权利管理服务，监控root状态</td></tr>
<tr><td>PermManager</td><td>负责临时root方案的权限获取和so库的加载</td></tr>
</table>

**rtservice**模块的com.qihoo.rtservice目录下主要包括以下几个核心文件：
<table>
<tr><td>类名</td> <td>作用</td></tr>
<tr><td>Cmd</td> <td>负责执行shell命令</td></tr>
<tr><td>IRTService</td> <td>定义action和service name等常量</td></tr>
<tr><td>IRTServiceImpl</td> <td>继承IRootService.Stub和IRTService，是系统服务的具体实现</td></tr>
<tr><td>IRootService</td> <td>系统服务的接口</td></tr>
<tr><td>MethodInfo</td> <td>method的接口实体类</td></tr>
<tr><td>NativeHelper</td> <td>负责对native库的处理</td></tr>
<tr><td>RTServiceManager</td> <td>与ServiceManager类似，客户端接口的封装</td></tr>
<tr><td>TempRoot</td> <td>定义错误码和常量</td></tr>
<tr><td>support</td> <td>目录，包含一些实用类</td></tr>
</table>

## 二、permmgr模块分析
permmgr模块主要由PermManager管理，PermManager负责获取root权限。

首先定义permmgr后台服务接口

    public abstract interface IPermMgrService extends IInterface
    {
      public abstract boolean checkDaemonIsRunning();
    
      public abstract Map checkIsSupportForSafe(String paramString, boolean paramBoolean);
    
      public abstract Map doCommand(String paramString1, String paramString2);
    
      public abstract int getRootForSafe(String paramString, boolean paramBoolean, Bundle paramBundle, c paramc);
    
      public abstract int getVersion();
    }

LocalRoot调用本地so库获取临时root权限，具体实现在lib360.so库中

	public class LocalRoot
	{
	  private static LocalRoot mInstance = null;
	
	  public static LocalRoot getInstance()
	  {
	    if (mInstance == null)
	      mInstance = new LocalRoot();
	    return mInstance;
	  }
		
      // 调用该方法获取临时root权限
	  private native int jmain(int paramInt);
	
	  private native void junmain(Class paramClass);
	
	  public int doRoot(java.lang.String paramString, android.content.SharedPreferences paramSharedPreferences, c paramc)
	  {
	  }
	}

PermService监听root广播：

	public class PermService extends Service
	{
	  protected static final String STATEROOTING = "com.qihoo.root.rooting";
	  protected static final String STATEROOTOVER = "com.qihoo.root.rootover";
	  public static String mPermmgrRootDir = "";
	  private final f mBinder = new h(this);
	  private Context mContext;
	  private i updateState;
	
	  private void registerBr()
	  {
	    this.updateState = new i(this);
	    IntentFilter localIntentFilter = new IntentFilter();
	    localIntentFilter.addAction("com.qihoo.root.rooting");
	    localIntentFilter.addAction("com.qihoo.root.rootover");
	    registerReceiver(this.updateState, localIntentFilter);
	  }
	
	  public String getErrKey()
	  {
	    return PermManager.getInstance(this).getErrKey();
	  }
	
	  public String getOutKey()
	  {
	    return PermManager.getInstance(this).getOutKey();
	  }
	
	  public IBinder onBind(Intent paramIntent)
	  {
	    mPermmgrRootDir = getFilesDir() + "/permmgr/";
	    registerBr();
	    return this.mBinder;
	  }
	
	  public void onCreate()
	  {
	    super.onCreate();
	  }
	
	  public void onDestroy()
	  {
	    try
	    {
	      unregisterReceiver(this.updateState);
	      e.a("------root end and kill----" + Process.myPid());
	      Process.killProcess(Process.myPid());
	      super.onDestroy();
	      return;
	    }
	    catch (Exception localException)
	    {
	      while (true)
	      {
	        if (!b.a)
	          continue;
	        localException.printStackTrace();
	      }
	    }
	  }
	
	  public void onRebind(Intent paramIntent)
	  {
	    super.onRebind(paramIntent);
	  }
	
	  public boolean onUnbind(Intent paramIntent)
	  {
	    return super.onUnbind(paramIntent);
	  }
	
	  public void setContext(Context paramContext)
	  {
	    this.mContext = paramContext;
	  }
	}

PermManager是root权限管理的接口管理类(仅保留函数声明)

	public class PermManager
	{
	
	  private PermManager(Context paramContext)
	  {
	  }
	
	  private void changeUidToMyReal()
	  {
	  }
	
	  private boolean checkFileByPath(String paramString)
	  {
	  }
	
	  private void checkFileNum()
	  {
	  }
	
	  private boolean checkFileSize(String paramString, File paramFile)
	  {
	  }
	
	  private void checkFiles()
	  {
	  }
	
	  private boolean checkRT_server(c paramc)
	  {
	  }
	
	  private void checkUpdate()
	  {
	  }
	
	  private void cleanFutext()
	  {
	  }
	
	  private String doCommandNoErr(String paramString1, String paramString2)
	  {
	  }
	
	  private int doSolutionBySU(String paramString, c paramc)
	  {
	  
	  }
	
	  private int doSolutionOnline(String paramString, c paramc)
	  {
	  }
	
	  private int doSuccessSolution(c paramc)
	  {
	  }
	
	  private String fingPkg(String paramString)
	  {
	  }
	
	  public static PermManager getInstance(Context paramContext)
	  {
	  }
	
	  private void getRealMyUid()
	  {
	  }
	
	  private void initLogFile()
	  {
	  }
	
	  private boolean isIgnoreSDK7()
	  {
	  }
	
	  private boolean isSupersu()
	  {
	  }
	
	  private native Object jcheckdaemon(Object paramObject);
	
	  private native Object jdocommand(Object paramObject, String paramString, int paramInt);
	
	  private native int jrestartdaemon(String paramString);
	
	  private void receiverAndWriteDataMobilesafe(Bundle paramBundle)
	  {
	  }
	
	  private void reportCrashList()
	  {
	  }
	
	  private void reportStat(int paramInt1, int paramInt2, int paramInt3)
	  {
	  }
	
	  private void rmEnv()
	  {
	  }
	
	  private void saveEnv(Bundle paramBundle)
	  {
	  }
	
	  private int startByRoot(Bundle paramBundle)
	  {
	  }
	
	  private boolean verifyFromData(Bundle paramBundle)
	  {
	  }
	
	  public boolean checkDaemonIsRunning()
	  {
	  }
	
	  public boolean checkIsSupport(String paramString, int paramInt)
	  {
	  }
	
	  public Map checkIsSupportForSafe(String paramString, boolean paramBoolean)
	  {
	  }
	
	  public Map doCommand(String paramString1, String paramString2)
	  {
	  }
	
	  public int doRoot_Local(c paramc)
	  {
	  }
	
	  public String getDaemonVersion()
	  {
	  }
	
	  protected String getErrKey()
	  {
	    return "err";
	  }
	
	  protected String getOutKey()
	  {
	    return "out";
	  }
	
	  public int getPermission(Bundle paramBundle, String paramString, boolean paramBoolean, c paramc)
	  {
	  }
	
	  public int getRoot(String paramString, boolean paramBoolean, Bundle paramBundle, c paramc)
	  {
	  }
	
	  protected String getSupportMKey()
	  {
	    return "mobile";
	  }
	
	  protected String getSupportPCKey()
	  {
	    return "pc";
	  }
	
	  public boolean restartDaemon(String paramString)
	  {
	    return jrestartdaemon(paramString) >= 0;
	  }
	
	  public void writeFileData(File paramFile, String paramString)
	  {
	  }
	}

**PermManager**需要进一步仔细分析

## 三、rtservice模块分析
rtService作为一个独立的进程，首先定义了进程通信的接口和实现类，该进程可以通过dexClassLoader加载jar包或apk文件，从而以root身份执行任意的代码!

IRootService接口

    public abstract interface IRootService extends IInterface
    {
      public abstract String exec(String[] paramArrayOfString);
    
      public abstract int getMyVersionCode();
    
      public abstract MethodInfo invokeStaticMethod(MethodInfo paramMethodInfo);
    
      public abstract boolean invokeStaticMethodForBoolean(MethodInfo paramMethodInfo);
    
      public abstract byte invokeStaticMethodForByte(MethodInfo paramMethodInfo);
    
      public abstract double invokeStaticMethodForDouble(MethodInfo paramMethodInfo);
    
      public abstract float invokeStaticMethodForFloat(MethodInfo paramMethodInfo);
    
      public abstract int invokeStaticMethodForInt(MethodInfo paramMethodInfo);
    
      public abstract long invokeStaticMethodForLong(MethodInfo paramMethodInfo);
    
      public abstract String invokeStaticMethodForString(MethodInfo paramMethodInfo);
    
      public abstract void invokeStaticMethodForVoid(MethodInfo paramMethodInfo);
    
      public abstract void setDebug(boolean paramBoolean);
    
      public abstract int stop();
    }

IRTServiceImpl继承IRootService.Stub作为独立root进程qh_root_service的服务端

    class IRTServiceImpl extends IRootService.Stub implements IRTService

具体实现暂不进行分析

RTServiceManager负责qh_root_service进程的启动和管理