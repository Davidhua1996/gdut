<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
  <title>管理员用户信息页面</title>
  <meta charset="UTF-8" />
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">   
   <link rel="stylesheet" href="css/bootstrap.min.css" />
  <link rel="stylesheet" href="css/uniform.css" />
  <link rel="stylesheet" href="css/unicorn.main.css" />
  <link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />
   <link rel="stylesheet" href="css/select2.css" />
   <link rel="stylesheet" href="css/load.css"/>
<style>
    #form{ top: 20%;}
  select{ width: 100px;}
</style>
</head>
<body>

  <div id="header">
    <h1>
      <a href="./dashboard.html">广工订水系统后台管理</a>
    </h1>
  </div>
  <div id="sidebar">
    <ul>
      <li >
        <a href="vendor_toAuditedVendors.action"> <i class="icon icon-th-list"></i>
          <span>未审核商家</span>
        </a>
      </li>
      <li >
        <a href="vendor_toVendorInfo.action"> <i class="icon icon-tint"></i>
          <span>商家信息</span>
        </a>
      </li>
      <li class="active">
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
      <!--<a href="#" id="addStu">添加用户</a><br>-->
      <div style="float:left;margin-left:50px;padding-bottom:0px; position: relative; left: 15px;top: 13px;">
      	姓名:<input type="text" id="search_name" style="width:75px;margin-top:0px;height:26px;
       " maxlength="8"/>&nbsp;
      	专业:<input type="text" id="search_major" style="width:75px;margin-top:0px;height:26px" maxlength="8"/>&nbsp;
      	宿舍区:
      <select  id="search_areaName" class="dormitory_area" style="height:26px" data-label="first">
        <option selected value="">宿舍区</option>
      </select>
        &nbsp;
      	宿舍楼:
	  <select id="search_building" class="dormitory_building" style="height:26px" data-label="second">
        <option selected value="">宿舍楼</option>
      </select>
      	<input type="button" id="search" style="position: relative;top: -4px;margin-left:60px"class="btn btn-info" value="查询"/>
      </div>
      </div>
      <div class="row-fluid">
        <div class="span12">
          <div class="widget-box">             
            <div class="widget-title">
              <h5>用户管理</h5>
            </div>
            <div class="widget-content nopadding">
              <table class="table table-bordered data-table">
                <thead>
                  <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>宿舍信息</th>
                    <th>操作</th>
                  </tr>
                </thead>
                <tbody>
                <td colspan="6">没有数据</td>
                </tbody>
              </table>
                    <div class="page">
  <button class="btn btn-info  previous" disabled="disabled">前一页</button>
  <input type="text" class="num"value="1" style="width: 50px;height:30px;margin-right: 10px;">
 <label for="">/<label class="Total" disabled="disabled">1</label></label>
  <button class="btn btn-info  next"   disabled="disabled">后一页</button>
</div>

</div><div class="pagination-info">Displaying 1 to 10 of 114 items</div><div style="clear:both;"></div>
    </div>
            </div>
          </div>
        </div>
      </div>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">是否删除</h3>
  </div>
  <div class="modal-body">
    <p>确定要删除?</p>
  </div>
  <div class="modal-footer">
    <button class="btn" id="btn-true"  aria-hidden="true" data-dismiss="modal">是</button>
    <button class="btn btn-primary"data-dismiss="modal">否</button>
  </div>
</div>
<div class="row-fluid" id="form" style="display: none;top:20%;">
  <div class="span12">
    <div class="widget-box">
      <div class="widget-title">
        <span class="icon">
          <i class="icon-align-justify"></i>
        </span>
        <h5>请输入详细信息：</h5>
      </div>
      <div class="widget-content nopadding">
        <form class="form-horizontal validate" method="post" action="#" name="basic_validate" id="basic_validate" novalidate />
        <div class="control-group">
          <label class="control-label">学号:</label>
          <div class="controls">
            <input type="text" name="required" id="stu_No"  class="validate" maxlength="10" />
            <br><font id="stu_mesg" style="color:red;display: none;">&nbsp; 学号不能为空</font>
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">姓名:</label>
          <div class="controls">
            <input type="text" name="required" id="username" class="validate" maxlength="10"  />
            <br><font id="name_mesg" style="color:red;display: none;">&nbsp; 姓名不能为空</font>
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">专业:</label>
          <div class="controls">
            <input type="text" name="required" id="major" class="validate" maxlength="10"  />
            <br><font id="major_mesg" style="color:red;display: none;">&nbsp; 专业不能为空</font> 
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">班级:</label>
          <div class="controls">
            <input type="text" name="required" id="clazz" class="validate" maxlength="10"  />
            <br><font id="clazz_mesg" style="color:red;display: none;">&nbsp; 班级不能为空</font> 
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">宿舍:</label>
          <div  class="controls">
            <select id="area" class="dormitory_area validate" >
            </select>
            <select id="building" class="dormitory_building validate" >
            </select>
            <span>宿舍号:</span>
            <input type="text" style="width:50px;" maxlength="5" class="validate" id="romNum" >
            <br><font style="color: red; display:none;" id="dormitory_mesg">&nbsp; 宿舍信息不能为空</font></div>
        </div>
        <div class="form-actions">
          <input type="hidden" value="" id="tempId"/>
          <input type="button" id="addAlter" value="修改" class="btn btn-primary" />
          <input onclick="closeWind()" value="关闭" class="btn btn-primary" />
        </div>
      </form>
    </div>
  </div>
</div>
</div>
<div class="black">
  <div class="data">数据正在加载中...
</div>
</div>
 <script src="script/jquery.min.js"></script>
  <script src="script/bootstrap.min.js"></script>
  <script src="script/jquery.uniform.js"></script>
  <script src="script/unicorn.js"></script>
  <script src="script/jquery.validate.js"></script>
  <script src="script/jquery.validate.messages_cn.js"></script>
  <script src="script/common.js"></script>
<script src="script/jquery.easyui.min.js"></script>
<script src="script/load.js"/></script>
 <script src="script/pagediv.js"></script>


<script>
//存放临时数据
var tempor = null;
$(function(){
//加载宿舍信息
$.ajax({
	  type:'POST',
	  url:'area_areaMessage.action',
	  dataType:'json',
	  success:function(data){
		  $(".dormitory_area").empty();
		  $(".dormitory_area").append("<option value=''>宿舍区</option>");
		  tempor = data;
		  $.each(data,function(commentIndex,comment){
			  $("<option></option>").val(comment['name'])
			  						.text(comment['name'])
			  						.appendTo(".dormitory_area");
		  });
	}
});
});
//查询信息
$("#search").click(function(){
	getData();
});
//getData方法
 function getData(){
	 mask('正在加载中,请稍后...');
   $.ajax({
  type:'POST',
     url:'student_findAll.action',
     data:{pageSize:'4',pageNum:$(".num").val(),
    	 'realName':$("#search_name").val(),
    	 'student.major':$("#search_major").val(),
    	 'student.dormitory':$("#search_building").val(),
    	 'student.areaName':$("#search_areaName").val()},
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
       $("<td>"+el.loginName+"</td>").appendTo($tr);
       $("<td>"+el.name+"</td>").appendTo($tr);
       $("<td align='center'>"+el.areaName+"-"+el.dormitory+"-"+el.romNum+"</td>").appendTo($tr);
       $("<td class='center' align='center'>"+
    	  "<a class='btn revise' onclick='alterBtn(this)'>修改</a>&nbsp;&nbsp;&nbsp;"+
          "<a data-toggle='modal' onclick='dele(this)' class='btn' role='button' href=#myModal>删除</a>"+
          "</td").appendTo($tr);
       $("<input type='hidden' id='claz' value='"+el.clazz+"'/>").appendTo($tr);
       $("<input type='hidden' id='maj' value='"+el.major+"'/>").appendTo($tr);
       $("<input type='hidden' id='id' value='"+el.id+"'/>").appendTo($tr);
       $tr.appendTo(".data-table tbody");     
     });
      if((parseInt($(".Total").text())<=1)&&(pager.currentPage==1)){
  	  	$(".Total").text("1");
  	  	$(".previous").attr("disabled","true");
  		$(".next").attr("disabled","true");
  		$(".Total").attr("disabled",false);
    }else if(pager.currentPage==1){
  	  $(".previous").attr("disabled","true");
  	  $(".next").attr("disabled",false);
  	$(".Total").attr("disabled",false);
    }
      unmask();
   }
   });
}
  //修改按钮
function alterBtn(e){
   var loginName=$(e).parents('tr').find("td:eq(0)").html();
   var name=$(e).parents('tr').find("td:eq(1)").html();
   var message=$(e).parents('tr').find("td:eq(2)").html();
   var clazz = $(e).parents('tr').find("#claz").val();
   var major = $(e).parents('tr').find("#maj").val();
   var id = $(e).parents('tr').find("#id").val();
   //拆分宿舍信息
   var array = message.split("-");
   $("#stu_No").val(loginName);
   $("#username").val(name);
   $("#clazz").val(clazz);
   $("#major").val(major);
   $("#tempId").val(id);
   $("#area").find("option[value='"+array[0]+"']").attr("selected",true);
   if(null!=tempor){
	   $.each(tempor,function(e,element){
		   if($("#area").val()==element['name']){
				$("#building").empty();
				$("#building").append("<option value=''>宿舍楼</option>");
			    $.each(element['buildings'],function(i,items){
					$("<option></option").val(items['name'])
							  			.text(items['name']+"栋")
							  			.appendTo("#building");
			     });
		   }
	   })
   }
   $("#building").find("option[value='"+array[1]+"']").attr("selected",true);
   $("#romNum").val(array[2]);
   $("#stu_No").attr("disabled","true");
   $("#addAlter").unbind();
   $("#addAlter").bind("click",alter);
   $("#form").show();
}
//修改
function alter(){
	 var pass = validate();
	 if(!pass){
		 return false;
	 }
	$.ajax({
		type:'POST',
		url:'student_updateStu.action?aa='+Math.random(),
		data:{'student.areaName':$("#area").val(),
			'student.dormitory':$("#building").val(),
			'student.romNum':$("#romNum").val(),
			'realName':$("#username").val(),
			'loginName':$("#stu_No").val(),
			'student.clazz':$("#clazz").val(),
			'student.major':$("#major").val(),
			'id':$("#tempId").val()},
		dataType:'json',
		success:function(){
			$("#form").hide();
			$("#romNum").val("");
			$("#username").val("");
			$("#stu_No").val("");
			$("#clazz").val("");
			$("#major").val("");
			getData();
		}
	});
}
//区域选项改变事件(查询框)
$("#search_areaName").change(function(){
	if(null != tempor&&$("#search_areaName").text()!="宿舍区"){
		$(".dormitory_building").empty();
		$(".dormitory_building").append("<option value=''>宿舍楼</option>");
		$.each(tempor,function(e,element){
			if(element['name']==$("#search_areaName").val()){
				$.each(element['buildings'],function(i,items){
					$("<option></option>").val(items['name'])
										  .text(items['name']+"栋")
										  .appendTo(".dormitory_building");
				});
			};
		});
	}else{
		$("#search_areaName").empty();
		$("#search_areaName").append("<option value=''>宿舍楼</option>");
	}
});
//区域选项改变事件(添加删除框)
$("#area").change(function(){
	if(null != tempor&&$("#area").text()!="宿舍区"){
		$(".dormitory_building").empty();
		$(".dormitory_building").append("<option value=''>宿舍楼</option>");
		$.each(tempor,function(e,element){
			if(element['name']==$("#area").val()){
				$.each(element['buildings'],function(i,items){
					$("<option></option>").val(items['name'])
										  .text(items['name']+"栋")
										  .appendTo(".dormitory_building");
				});
			};
		});
	}else{
		$("#area").empty();
		$("#area").append("<option value=''>宿舍楼</option>");
	}
});
//删除按钮
function dele(e){
   $("#btn-true").unbind();
   $("#btn-true").bind('click',function(){
   			var id = $(e).parents('tr').find("#id").val();
   			$.ajax({
   				type:'POST',
   				url:'student_delStu.action?aa='+Math.random(),
   			    data:{'id':id},
   				dataType:'json',
   				success:function(){
   					getData();	
   				}
   			});
	});
};
  //添加用户按钮
 //$("#addStu").click(function(){
	// $("#area").find("option[value='']").attr("selected",true);
	// $("#building").empty();
	// $("#romNum").val("");
	// $("#username").val("");
	// $("#stu_No").val("");
	// $("#clazz").val("");
	// $("#major").val("");
	// $("#stu_No").attr("disabled",false);
	// $("#addAlter").unbind();
	// $("#addAlter").bind("click",addStu);
    //$("#form").show();
 //});
  //添加用户
 //function addStu(){
//	 var pass = validate();
//	 if(!pass){
//		 return false;
//	 }
//	 $.ajax({
//		 type:'POST',
//		 url:'student_addStu.action',
//		 data:{'student.areaName':$("#area").val(),
//				'student.dormitory':$("#building").val(),
//				'student.romNum':$("#romNum").val(),
//				'student.name':$("#username").val(),
//				'loginName':$("#stu_No").val(),
//				'student.clazz':$("#clazz").val(),
//				'student.major':$("#major").val()},
//		 success:function(data){
//			 if(jQuery.isEmptyObject(data.errorMessage)){
//				 $("#form").hide();
//					$("#area").val("");
//					$("#building").val("");
//					$("#romNum").val("");
//					$("#username").val("");
//					$("#stu_No").val("");
//					$("#clazz").val("");
//					$("#major").val("");
//					getData();
//			 }else{
//				 alert(data.errorMessage);
//			 }
//		 }
//	 });
 // }
 //验证
function validate(){
  if($("#stu_No").val()==''){
      $("#stu_mesg").show();
      return false;
  }
  if($("#username").val()==''){
      $("#name_mesg").show();
      return false;
  }
  if($("#major").val()==''){
	  $("#major_mesg").show();
	  return false;
  }
  if($("#clazz").val()==''){
	  $("#clazz_mesg").show();
	  return false;
  }
  if($("#area").val()==''){
      $("#dormitory_mesg").show();
      return false;
  }
  if($("#building").val()==''){
      $("#dormitory_mesg").show();
      return false;
  }
  if($("#romNum").val()==''){
      $("#dormitory_mesg").show();
      return false;
  }
  clear();
  return true;
 };
 //清除错误信息
 function clear(){
	 $("#name_mesg").hide();
	 $("#stu_mesg").hide();
	 $("#dormitory_mesg").hide();
	 $("#clazz_mesg").hide();
	 $("#major_mesg").hide();
 }
 //失去焦点事件
 $(".validate").blur(function(){
	 clear();
	 validate();
 });
 //状态改变事件
 $(".validate").change(function(){
	 clear();
	 validate();
 });
 //关闭窗口按钮事件
 function closeWind(){
	clear();
	$("#form").hide();
 }
</script>
</body>
</html>