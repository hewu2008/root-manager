# 360秒装功能分析
最新版**360手机助手**提供了**秒装**的功能，能够在已经取得root权限的手机上，不调用su进行授权，直接安装app从而能够有效减少应用安装的时间。

## 一、模块分析
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
permmgr模块主要由PermManager管理，PermManager负责获取root权限

