<%--
  Created by IntelliJ IDEA.
  User: dinglei
  Date: 2018/1/10
  Time: 上午11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>webTemplate Demo</title>
</head>
<body>
<a href="/download?fileId=11">文件下载</a>
<br/>
<br/>
<br/>
<form action="/upload" enctype="multipart/form-data" method="post">
    <input id="file" name="file" type="file">
    <input type="submit" value="上传"/>
</form>
</body>
</html>
