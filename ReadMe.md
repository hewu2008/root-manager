# root-manager
最新版360手机助手提供了秒装的功能，能够在已经取得root权限的手机上，不调用su进行授权，直接安装app从而能够有效减少应用安装的时间。

本项目拟通过逆向360手机助手，实现秒装功能。

## 一、静态代码分析
秒装的核心代码在com.qihoo.rtservice目录下，该目录包括以下几个核心文件：
<table>
<tr>
<td>类名</td> <td>作用</td>
</tr>
<tr>
<td>Cmd</td> <td>负责执行shell命令</td>
</tr>
<tr>
<td>IRTService</td> <td>定义action和service name等常量</td>
</tr>
<tr>
<td>IRTServiceImpl</td> <td>继承IRootService.Stub和IRTService，是系统服务的具体实现</td>
</tr>
<tr>
<td>IRootService</td> <td>系统服务的接口</td>
</tr>
<tr>
<td>MethodInfo</td> <td>method的接口实体类</td>
</tr>
<tr>
<td>NativeHelper</td> <td>负责对native库的处理</td>
</tr>
<tr>
<td>RTServiceManager</td> <td>与ServiceManager类似，客户端接口的封装</td>
</tr>
<tr>
<td>TempRoot</td> <td>定义错误码和常量</td>
</tr>
<tr>
<td>support</td> <td>目录，包含一些实用类</td>
</tr>
</table>