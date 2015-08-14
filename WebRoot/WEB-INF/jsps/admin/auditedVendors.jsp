<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html  lang="en">
<head>
  <title>管理员注册商家界面</title>
  <meta charset="UTF-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">   
  <link rel="stylesheet" href="css/bootstrap.min.css" />
 
  <link rel="stylesheet" href="css/uniform.css" />
  <link rel="stylesheet" href="css/select2.css" />
  <link rel="stylesheet" href="css/unicorn.main.css" />
  <link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />
  <link rel="stylesheet" href="css/load.css"/>
 

 <!--[if  gt IE 8]> <style> .input_text{ top:-3px;position: relative;}  </style> <![endif]-->
</head>
<body>
  <input id="tempId" type="hidden" value=""/>
  <input id="tempCode" type="hidden" value=""/>
  <div class="alert alert-info info" id="alert" name="pass" role="alert">
 	   确认发送邮件？
    <button type="button"  class="btn btn-info bt1 close2" onClick="close2()">关闭</button>
    <button type="button" id="send_pass" class="btn btn-info bt2 ">发送</button>
  </div>
  <div class="alert alert-warning warning"  name="unpass" id="alert1" role="alert" >
    <h2>请填写理由：</h2>
    <textarea name="meesage" id="reason" cols="30" rows="10"></textarea>
 
     <button type="button" id="send_unpass" class="btn btn-info unpass_Button">发送</button>
    <button type="button" id="close1" class="btn btn-info bt1 close1" onClick="close1()">关闭</button>
  </div>
  <div id="header">
    <h1>
      <a href="./dashboard.html">广工订水系统后台管理</a>
    </h1>
  </div>
  <!-- Modal -->
  <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    </div>
    <div class="modal-body">
      <p>确定要删除?</p>
    </div>
    <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
      <button class="btn btn-primary">Save changes</button>
    </div>
  </div>
  <div id="sidebar">
    <ul>
      <li class="active">

        <a href="vendor_toAuditedVendors.action" class="audit"> <i class="icon icon-th-list"></i>
               <span>未审核商家</span>
         </a>
        </li>
        <li>
            <a href="vendor_toVendorInfo.action"> <i class="icon icon-tint"></i>
            <span>商家信息</span>
            </a>
        </li>
        <li>
            <a href="student_toStudentInfo.action">
               <i class="icon icon-pencil"></i> 
             <span>用户信息</span>
            </a>
        </li>
      <li >
        <a href="worker_toWorkerInfo.action">
          <i class="icon icon-th"></i>
          <span>送水员工</span>
        </a>
      </li>
      <li >
        <a href="order_toFindForAdmin.action">
          <i class="icon icon-th-list"></i>
          <span>订单管理</span>
        </a>
      </li>
      <li >
        <a href="product_toProductInfo.action">
          <i class="icon icon-file"></i>
          <span>桶装水管理</span>
        </a>
      </li>
      <li>
        <a href="vendor_toBlackList.action">
          <i class="icon icon-signal"></i>
          <span>黑名单</span>
        </a>
      </li>
      <li>
        <a href="feedBack_toList.action">
          <i class="icon icon-inbox"></i>
          <span>反馈</span>
        </a>
      </li>
    </ul>
  </div>
  <div id="content">
    <div id="content-header">
      <h1>广工订水系统后台管理</h1>
      <a href="user_withdraw.action" class="out">退出登录</a>
    </div>
    <div id="breadcrumb">
   
    </div>
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span12">
          <div class="widget-box">
            <div class="widget-title">
              <h5>审核商家</h5>
            </div>
            <div class="widget-content nopadding">
              <table style="width:100%"class="table table-bordered data-table">
                <thead>
                  <tr>
                    <th>商家账号</th>
                    <th>商家名称</th>
                    <th>商家卡号</th>
                    <th>供水区域</th>
                    <th>卫生许可证</th>
                    <th>是否通过</th>
                  </tr>

                </thead>
                <tbody>
 </tbody>

              </table>
             <div class="page">
  <button class="btn btn-info  previous" >前一页</button>
  <input type="text" class="num"value="1" style="width: 50px;height:30px;margin-right: 10px;">
 <label for="">/<label class="Total">1</label></label>
  <button class="btn btn-info  next"    >后一页</button>

</div>



    <div class="pagination-info">Displaying 1 to 10 of 114 items</div><div style="clear:both;"></div></div>
  </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="alert alert-info info" id="change" style="width: 200px;height: 200px;" name="pass" role="alert">
    <button type="button"  class="close2 btn btn-info bt1 ">关闭</button>
    <img src="" id="big_img" alt="">
 </div>
<div class="black"></div>
<div class="black">
  <div class="data">数据正在加载中...
</div>
</div>
</body>
<script src="script/jquery.min.js"></script>
<script src="script/jquery.ui.custom.js"></script>
<script src="script/bootstrap.min.js"></script>
<script src="script/jquery.uniform.js"></script>
<script src="script/select2.min.js"></script>
<script src="script/fullcalendar.min.js"></script>
<script src="script/unicorn.calendar.js"></script>
<script src="script/base.js"></script>
<script src="script/pagediv.js"></script>
<script src="script/load.js"></script>
<script>
    
$(function(){
getData();
//点击通过的按钮
$(".pass").click(function(){
  var par=$(this).parent('tr');
  $.getJSON("url",{"activatedCode":$(this).parent("#id").val()})
})
//点击不通过的按钮
$(".unpass").click(function(){
  $.getJSON("url",{"activatedCode":$(this).parent("#id").val()})
})    
$(".previous").attr("disabled","true");  
})
//getData方法
 function getData(){
	mask('正在加载中,请稍后...');
   $.ajax({
 	type:'POST',
     url:'vendor_auditedVendors.action?aa='+Math.random(),
     data:{pageSize:'4',pageNum:$(".num").val()},
     dataType:'json',
    	success:function(data){
      $(".data-table tbody").html("");
  	 var pager = data.pager;
  	  $(".Total").text(pager.pageCount);
  	 if(jQuery.isEmptyObject(pager.recordList)){
  		 var $tr1=$("<tr class='gradeX'></tr>");
  		 $("<td colspan='6' align='right'>&nbsp;&nbsp;没有数据</td>").appendTo($tr1);
  		$tr1.appendTo(".data-table tbody"); 
  	 }
      $.each(pager.recordList,function(i,el){
        var $tr=$("<tr class='gradeX'></tr>");
       $("<td>"+el.companyName+"</td>").appendTo($tr);
       $("<td>"+el.loginName+"</td>").appendTo($tr);
       $("<td>"+el.bankCard+"</td>").appendTo($tr);
       $("<td>"+el.areaName+"</td>").appendTo($tr);
       $("<td>"+
    	 "<img onclick='change(this)' id='img' src='images/show.jpg' height='50' width='45' value='upload/vendor/"+el.picUrl+"'/>"+
    	 "</td>").appendTo($tr);
       $("<td class='center'><button class='btn btn-info pass' onClick='pass(this)' type='button'>通过</button>&nbsp;&nbsp;<button class='btn"
     		  +"btn-warning unpass' onClick='unpass(this)' type='button'>不通过</button></td>").appendTo($tr);
       $("<input id='code' type='hidden' value='"+el.activatedCode+"' />").appendTo($tr);
       $("<input id='id' type='hidden' value='"+el.id+"' />").appendTo($tr);
       $tr.appendTo(".data-table tbody");  
   	 });
      if((parseInt($(".Total").text())<=1)&&(pager.currentPage==1)){
  	  	$(".Total").text("1");
  	  	$(".previous").attr("disabled","true");
  		$(".next").attr("disabled","true");
    }else if(pager.currentPage==1){
  	  $(".previous").attr("disabled","true");
  	  $(".next").attr("disabled",false);
    }
      unmask();
    }
   });
}
  $("#send_pass").click(function(){
	 $.ajax({
	  type:'POST',
	  url:'vendor_activatedPass.action',
	  data:{id:$("#tempId").val(),activatedCode:$("#tempCode").val()}
	  });
	 $alert.hide();
     $black.hide();
     $("#tempId").val("");
     $("#tempCode").val("");
	 getData();
	});
  $("#send_unpass").click(function(){
	  $.ajax({
	    type:'POST',
		url:'vendor_activatedFail.action',
		data:{id:$("#tempId").val(),activatedCode:$("#tempCode").val(),message:$("#reason").val()}
	  });
	  $alert1.hide();
      $black.hide();
      $("#tempId").val("");
      $("#tempCode").val("");
	  getData();
  });
	   var $black=$(".black");
       var $alert=$("#alert");
       var $alert1= $("#alert1");
         //点击通过按钮
       function pass(e){
           $alert.show();
           $black.show();
           var code =$(e).parents('tr').find("#code");
           var id = $(e).parents('tr').find("#id");
           $("#tempId").val(id.val());
           $("#tempCode").val(code.val());
         }
       //点击不通过按钮
       function unpass(e){
          $alert1.show();
           $black.show();
           var code =$(e).parents('tr').find("#code");
           var id = $(e).parents('tr').find("#id");
           $("#tempId").val(id.val());
           $("#tempCode").val(code.val());
       };
       //关闭通过弹出框
       function close1(){
          $alert1.hide();
           $black.hide();
           $("#tempId").val("");
           $("#tempCode").val("");
       };
       //关闭不通过弹出框
       function close2(){
           $alert.hide();
           $black.hide();
           $("#tempId").val("");
           $("#tempCode").val("");
       };
     //点击关闭按钮
       $("button[class^='close']").click(function(){
           $("div[class^='alert']").hide();
             $("div[class^='black']").hide();
       });
       function change(e){
    	   $("#change").fadeIn("slow");
    	     $(".black").show();
    	     var zoomX=$("#change").width()/$("#img").width();
    	     var zoomY=$("#change").height()/$("#img").height();
    	     $("#big_img").attr("src",$(e).val());
    	     $("#big_img").width(zoomX*$("#img").width());
    	     $("#big_img").height(zoomY*$("#img").height());
		};   

</script>
</html>