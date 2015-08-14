<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
  <title>管理员送水员工页面</title>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">   
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/unicorn.main.css" />
    <link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />
  <link rel="stylesheet" href="css/select2.css" />
  <link rel="stylesheet" href="css/load.css"/>
  
  <style>
 	#form{ top: 20%;}
  	select{ width: 100px;}
  	#addWorker{position: relative;top: 10px;}
  </style>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
      <li>
        <a href="student_toStudentInfo.action">
          <i class="icon icon-pencil"></i>
          <span>用户信息</span>
        </a>
      </li>
      <li class="active">
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
    </div>
    <div id="breadcrumb">
      
    </div>
    <div class="container-fluid">
        <a href="#" id="addWorker" >添加送水员工</a>
      <div class="row-fluid">
        <div class="span12">
          <div class="widget-box">
            <div class="widget-title">
              <h5>后台管理</h5>
            </div>
            <div class="widget-content nopadding">
              <table class="table table-bordered data-table">
                <thead>
                  <tr>
                    <th>真实姓名</th>
                    <th>账号</th>
                    <th>送水区域</th>
         			<th>操作</th>         
                  </tr>
                </thead>
                <tbody>                  
                </tbody>
              </table>
                         <div class="page">
  <button class="btn btn-info  previous" >前一页</button>
  <input type="text" class="num"value="1" style="width: 50px;height:30px;margin-right: 10px;">
 <label for="">/<label class="Total">1</label></label>
  <button class="btn btn-info  next" >后一页</button>


</div>



    <div class="pagination-info">Displaying 1 to 10 of 114 items</div><div style="clear:both;"></div>
    </div> 
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

<div class="row-fluid" id="form" style="display: none;">
  <div class="span12">
    <div class="widget-box">
      <div class="widget-title">
        <span class="icon">
          <i class="icon-align-justify"></i>
        </span>
        <h5>请输入详细信息：</h5>
      </div>
      <div class="widget-content nopadding">
        <form class="form-horizontal" method="post" name="basic_validate" id="basic_validate" novalidate >
 		<div class="widget-content nopadding">
        <div class="control-group">
          <label class="control-label">真实姓名</label>
          <div class="controls">
            <input type="text"  maxlength="10" class="validate" id="realName" name="realName" />
            <br><font id="realName_mesg" style="color:red;display: none;">&nbsp; 真实姓名不能为空</font>
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">账号:</label>
          <div class="controls">
            <input type="text"  maxlength="10" class="validate" id="loginName" name="loginName"/>
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">新密码:</label>
          <div class="controls">
            <input type="password"  name="password"  maxlength="10"   id="password" />
            <br><font id="password_mesg" style="color:red;display: none;">&nbsp; 请输入新密码</font>
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">确认密码:</label>
          <div class="controls">
            <input type="password"  name="confirmPwd" id="confirmPwd" maxlength="10" />
            <br><font id="confirmPwd_mesg01" style="color:red;display: none;">&nbsp; 请再次输入密码</font>
            <font id="confirmPwd_mesg02" style="color:red;display: none;">&nbsp; 两次密码不一致</font>
          </div>
        </div>
        <div class="control-group">
          <label class="control-label">区域选择:</label>
          <div class="controls">
            <select id="areaName" class="validate" name="vendor.areaName" class="dormitory_area" data-label="first">     
         </select>
         <select id="buildingName" class="validate" data-label="second">
         </select><br>
           <font id="areaName_mesg" style="color:red;display: none;">&nbsp; 送水区域不能为空</font>
         <font id="building_mesg" style="color:red;display: none;">&nbsp; 送水楼不能为空</font>
        </div>
        </div>
        </div>
         <div class="form-actions" style="margin-top:0px;margin-bottom: 0px; text-align: center; border-bottom: 1px solid #e5e5e5;">
             <input type="hidden" value="" id="tempId" name="id"/>
             <input type="button" value="修改" onclick="alter()" class="btn btn-primary" style="float:none;" />
             <input style="width:100px;float:none;" onclick="closeWind()" value="关闭" class="btn btn-primary" />
         </div>
      </form>
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
      <button id="btn-true" class="btn btn-primary"data-dismiss="modal">否</button>
      </div>
    </div>
 <script src="script/jquery.min.js"></script>
<script src="script/bootstrap.min.js"></script>
<script src="script/jquery.validate.js"></script>
<script src="script/common.js"></script>
<script src="script/base.js"></script>
<script src="script/jquery.easyui.min.js"></script>
<script src="script/pagediv.js"></script>
<script src="script/load.js"></script>
  <script>
$(function(){
getData();
});
//getData方法
 function getData(){
	mask('正在加载中,请稍后...');
   $.ajax({
  type:'GET',
     url:'worker_workerInfo.action?aa=Math.random()',
     data:{pageSize:'4',pageNum:$(".num").val()},
     dateType:'json',
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
       $("<td>"+el.realName+"</td>").appendTo($tr);
       $("<td>"+el.loginName+"</td>").appendTo($tr);
       $("<td>"+el.areaName+"-"+el.buildingName+"</td>").appendTo($tr);
       $("<td class='center'><a class='btn revise' onclick='revise(this)'>修改</a>"+"&nbsp;&nbsp;"+
          "<a data-toggle='modal' onclick='dele(this)' class='btn' role='button' href=#myModal>删除</a></td>").appendTo($tr);
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
  </script>
<script type="text/javascript">
	var tempor = null;
	//点击修改按钮
	function revise(e){
		var realName=$(e).parents('tr').find("td:eq(0)").html();
		var loginName=$(e).parents('tr').find("td:eq(1)").html();
		var message=$(e).parents('tr').find("td:eq(2)").html();
		var array = message.split("-");
		var areaName = array[0];
		var buildingName = array[1];
		var id = $(e).parents('tr').find("#id");
		$("#realName").val(realName);
		$("#loginName").val(loginName);
		$("#areaName").val(areaName);
		$("#tempId").val(id.val());
		$("#loginName").attr("disabled","true");
		$("#confirmPwd").val("          ").css("background-color","#C9CFCB").click(function(){
			$(this).css("background-color","").val("");
		}).blur(function(){
			if($(this).val()==""){
				$("#confirmPwd").val("          ").css("background-color","#C9CFCB");
			}
			validate();
		});
		$("#password").val("          ").css("background-color","#C9CFCB").click(function(){
			$(this).css("background-color","").val("");
		}).blur(function(){
			if($(this).val()==""){
				$("#password").val("          ").css("background-color","#C9CFCB");
			}
			validate();
		});
		 //获得宿舍区域和栋信息
	     $.ajax({
	 		type:'POST',
	 		url:'area_areaMessage.action?aa='+Math.random(),
	 		success:function(data){
	 			tempor=data;
	 			$("#areaName").empty();
	 			$("#areaName").append("<option value='宿舍区'>宿舍区</option>");
	 			$.each(data,function(i,element){
	 				$("<option value='"+element['name']+"'>"+element['name']+"</option>").appendTo("#areaName");
	 				if(areaName==element['name']){
	 					$("#buildingName").empty();
	 					$("#buildingName").append("<option value='宿舍楼'>宿舍楼</option>");
	 					$.each(element['buildings'],function(e,items){
	 						$("<option></option>").val(items['name'])
	 											  .text(items['name']+"栋")
	 											  .appendTo("#buildingName");
	 					});
	 				}
	 			});
	 			$("#areaName").find("option[value='"+areaName+"']").attr("selected",true);
	 			$("#buildingName").find("option[value='"+buildingName+"']").attr("selected",true);
	 			$("#form").show();
	 		}
	 	});
	}
	$("#areaName").change(function(){
		if(tempor!=null&&$("#areaName").val()!="宿舍区"){
			$("#buildingName").empty();
			$("#buildingName").append("<option value='宿舍楼'>宿舍楼</option>");
			$.each(tempor,function(e,element){
				if(element['name']==$("#areaName").val()){
					$.each(element['buildings'],function(i,items){
						$("<option></option>").val(items['name'])
						  .text(items['name']+"栋")
						  .appendTo("#buildingName");
					});
				}
			});
		}else{
			$("#buildingName").empty();
			$("#buildingName").append("<option value='宿舍楼'>宿舍楼</option>")
		}
	});
	//修改送水工信息
	function alter(){
		var pass = validate();
		if(!pass){
			return false;
		}
		$.ajax({
			url:'worker_workerUpdate.action?aa=Math.random()',
			type:'POST',
			data:{'id':$("#tempId").val(),
					'realName':$("#realName").val(),
				   'worker.areaName':$("#areaName").val(),
				   'worker.buildingName':$("#buildingName").val()},
			success:function(data){
				$("#form").hide();
				$("#realName").val("");
				$("#loginName").val("");
				$("#tempId").val("");
				clear();
				getData();
			}
		});
	}
	//删除送水工
	function dele(e){
		$("#btn-true").unbind();
		$("#btn-true").bind('click',function(){
			var id = $(e).parents('tr').find("#id");
			$.ajax({
				url:'worker_workerUpdate.action?aa=Math.random()',
				type:'POST',
				data:{'status':'S_DELETE','id':id.val()},
				success:function(){
					getData();
				}
			});
		});
	}
	//清空验证数据
	function clear(){
		 $("#realName_mesg").hide();
		 $("#password_mesg").hide();
		 $("#confirmPwd_mesg01").hide();
		 $("#confirmPwd_mesg02").hide();
		 $("#areaName_mesg").hide();
		 $("#building_mesg").hide();
	}
	//表单数据验证
	function validate(){
		if($("#realName").val()==""){
			$("#realName_mesg").show();
			return false;
		}
		if($("#password").val()!=""&&$("#confirmPwd").val()==""){
			$("#confirmPwd_mesg01").show();
			return false;
		}
		if($("#password").val()==""&&$("#confirmPwd").val()!=""){
			$("#password_mesg").show();
			return false;
		}
		if($("#password").val()!=$("#confirmPwd").val()){
			$("#confirmPwd_mesg02").show();
			return false;
		}
		 if($("#areaName").val()=='宿舍区'){
		     $("#areaName_mesg").show();
		     return false;
		 }
		 if($("#buildingName").val()=='宿舍楼'){
			 $("#building_mesg").show();
			 return false;
		 }
		 clear();
		 return true;
	}
	//失去焦点事件
	$(".validate").blur(function(){
		clear();
		validate();
	});
	//改变事件
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
<script src="script/jquery.easyui.min.js"></script>

</body>
</html>