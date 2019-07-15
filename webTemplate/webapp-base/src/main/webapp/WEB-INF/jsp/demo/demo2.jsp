<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %><%--
  Created by IntelliJ IDEA.
  User: dinglei
  Date: 2018/2/23
  Time: 下午5:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>webTemplate Demo</title>
    <style>table {border: 1px solid #000;border-width:1px 0px 0px 1px;} table td {border:solid #000;border-width:0px 1px 1px 0px;font-size:14px;}</style>
</head>
<body>
<table>
    <tr>
        <td>appCode</td>
        <td>appName</td>
    </tr>
<%
    Object data = request.getAttribute("data");
    if(data!=null){
        JSONObject result = (JSONObject)data;
        if(result.getIntValue("code")>0) {
            JSONArray records = result.getJSONArray("records");
            for(int i=0;i<records.size();i++){
                JSONObject row = records.getJSONObject(i);
                out.println("<tr>");
                out.println("<td>"+row.getString("appCode")+"</td>");
                out.println("<td>"+row.getString("appName")+"</td>");
                out.println("</tr>");
            }
        }
    }
%>
</table>
</body>
</html>