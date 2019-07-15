[【返回目录】](../README.md)
# 开发环境的搭建

### 1.下载安装gralde构建工具
* 官网下载gradle最新版本`https://gradle.org/releases/`
* 设置%gradle_home%/bin到环境变量

### 2.下载安装IDEA
* 官网下载地址`http://www.jetbrains.com/`

### 3.公司OA上申请oss账号和gitlab账号
* OA业务导航中`oss用户申请`和`gitlab用户申请`流程；

### 4.下载WebTemplate模版工程
##### 方法一：通过命令行
```
git clone https://{user}:{password}@git.apexsoft.com.cn/framework/webTemplate.git
```
##### 方法二：使用git客户端工具，比如macos操作系统下使用`smartGit`,windows操作系统下使用`TortoiseGit`;

### 5.启动运行hello world模块
##### 方法一：
```
//进入helloworld模块
cd helloworld-module
//使用gradle构建启动模块
gradle bootRun
```
##### 方法二
用IDEA导入工程，使用idea的gradle面板菜单来启动项目：
![df](images/idea-gradle.png)

### 6.运行
##### 方法一：
> 浏览器输入：http://localhost:8080/hello
当界面上出现helloworld，说明当前`gradle`+`springboot`开发环境搭建成功；

##### 方法二：
执行`gradle bootRepackage`,然后`java -jar`执行`build/libs`目录下对应生成的jar包。如:
```
$ java -jar ./build/libs/helloworld-1.0.0.jar
```

如果jar服务需要采用自定义的log4j2.yml配置文件,请在java后面增加-Dlog4j.configurationFile=log4j2.yml

[【返回目录】](../README.md)
