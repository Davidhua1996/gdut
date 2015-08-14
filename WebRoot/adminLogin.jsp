<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html  lang="en">
<head>
  <title>管理员登录界面</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <meta name="apple-mobile-web-app-capable" content="yes"/>
  <link href="css/student.css" type="text/css" rel="stylesheet"/>
  <link href="css/reset.css" type="text/css" rel="stylesheet"/>

</head>
<body>
  <div class="content">
    <div class="adv">
      <img src="<c:url value='images/adv_03.png'/>" height="440" width="525" alt=""></div>
    <h1>欢迎使用广工桶装水订水系统</h1>
    <form action="user_login.action"  method="post" id="login" class="login">
      <div class="int">
        &nbsp;&nbsp;<label for="username">帐号:</label>
        <input type="text" id="username" name="loginName" maxlength="20" class="required" />&nbsp;&nbsp;
      </div>
      <div class="int"> 
        &nbsp;&nbsp;<label for="password">密码:</label>
        <input type="password" id="password" name="password"maxlength="10"  class="required focus" />
      </div>
      <input type="hidden" value="ADMIN" name="role"/>
      <font color="red" size="2">
      	&nbsp;&nbsp;<s:fielderror/><s:actionerror/>
      </font>	 
      <div class="button1">
        <input type="submit" name="login"  value="登录"/>
      </div>
  </form>
</div>
</body>
<script type="text/javascript" src="script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="script/jquery.validate.js"></script>
<script type="text/javascript" src="script/jquery.validate.messages_cn.js"></script>
<script type="text/javascript" src="script/common.js"></script>
</html>