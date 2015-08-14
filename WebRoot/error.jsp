<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
  <title>错误页面</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <link href="css/student.css" type="text/css" rel="stylesheet"/>
  <link href="css/reset.css" type="text/css" rel="stylesheet"/>
</head>
<style>
  .pay{background:  url(images/error.jpg) 0 0 no-repeat;height: 500px;width: 800px;margin: 100px 0 50px 0;position: relative;left: 50px;}
  .pay h2{
    border:none;background-color:transparent;}
</style>
<body>

 

  <div class="pay">

    <h2 ><s:property value="errorMessage"/></h2>
  </div>
</body>
<script>
  //setInterval(, );

</script>

  </html>