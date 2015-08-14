<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html  lang="en">
<html>
<head>
  <title>商家登陆页面</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <link href="css/student.css" type="text/css" rel="stylesheet"/>
  <link href="css/reset.css" type="text/css" rel="stylesheet"/>

  


</head>
<body style="margin:0 auto;">
  <div class="content">
    <div class="adv">
      <img src="<c:url value='images/adv_03.png'/>" height="440" width="525" alt=""></div>
    <h1>欢迎使用广工桶装水订水系统</h1>

    <form action="1.js" method="post" id="login" class="login">
      <div class="int">
        <label for="username">帐号:</label>
        <input type="text" id="username" name="username" maxlength="20"class="required" />
      </div>
      <div class="int">
        <label for="password">密码:</label>
        <input type="password" id="password" name="password"maxlength="10" class="required" />
      </div>
      <a href="#" class="forget">忘记密码？</a>
      <div class="button1">

        <input type="submit" name="login" class="submit"  value="登录" style="width:80px;" />
        <a href="businessRegister.html">
        <input type="submit" name="register"   value="注册"style="width:80px;" /></a>
        <input type="hidden" value="business" ></div>
    </div>
  </form>
</div>
<div class="black"></div>
<div role="alert" name="pass" id="alert" class="alert alert-info info" style="display:none;">
   <input type="text" class="user_forget" style="width:200px" Value ="请输入你的帐号"> 
    <button class="close2 btn btn-info bt1 " type="button" id="close1">关闭</button>
    <button class="btn btn-info bt2 " type="button">点击发送邮件</button>

  </div>
</body>

<script type="text/javascript" src="script/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="script/jquery.validate.js"></script>
  <script type="text/javascript" src="script/jquery.validate.messages_cn.js"></script>
<script type="text/javascript" src="script/common.js"></script>
 <script>

$(function(){
  $(".forget").bind("click",function(){
     $("#alert").show();
   $(".black").show();
  
})
$("#close1").bind("click",function(){
    $("#alert").hide();
    $(".black").hide();

})
$(".user_forget").bind("focus",function(){
     $(this).val("");
})
})
 </script>
 
</html>