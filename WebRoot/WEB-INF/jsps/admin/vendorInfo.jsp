<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head> 
    <title>管理员商家信息页面</title>
    <meta charset="UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
                <a href="vendor_toAuditedVendors.action" class="audit"> <i class="icon icon-th-list"></i>
                    <span>未审核商家</span>
                </a>
            </li>
            <li class="active">
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

                            <h5>商家信息</h5>
                        </div>
                        <div class="widget-content nopadding">
                            <table class="table table-bordered data-table">
                                <thead>
                                    <tr>
                                        <th>商家账号</th>
                                        <th>商家名称</th>
                                        <th>商家卡号</th>
                                        <th>供水区域</th>
                                        <th>卫生许可证</th>
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
   

<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">是否删除</h3>
    </div>
    <div class="modal-body">
        <p>确定要删除?</p>
    </div>
    <div class="modal-footer">
        <button class="btn" id="btn-true" data-dismiss="modal" aria-hidden="true">是</button>
        <button class="btn btn-primary" data-dismiss="modal">否</button>
    </div>
</div>
<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" class="modal hide fade in" id="myModal1" style="display: none;">
    <div class="modal-header">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h3 id="myModalLabel">拉入黑名单</h3>
    </div>
    <div class="modal-body">
        <p>确定将该商家拉入黑名单?</p>
    </div>
    <div class="modal-footer">
        <button aria-hidden="true" data-dismiss="modal" id="btn-true2" class="btn">是</button>
        <button data-dismiss="modal" class="btn btn-primary">否</button>
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
        <form class="form-horizontal" style="top:20%;" method="post" action="#" name="basic_validate" id="alter_form" novalidate >
         <div class="widget-content nopadding">
           <div class="control-group">
              <label class="control-label">商家账号:</label>
                <div class="controls">
                   <input type="text" id="loginName" disabled="disabled" class="validate" name="loginName"  margin-right:60px" maxlength="10" />
                   <br><font id="name_mesg" style="color:red;display: none;">&nbsp; 商家名称不能为空</font>
                </div>
        </div>
         <div class="control-group">
                <label class="control-label">商家卡号:</label>
               <div class="controls">
                  <input type="text" id="bankCard" class="validate" name="vendor.bankCard" maxlength="32" style="margin-right:60px"  id="Bankcard" />
                  <br><font id="bank_mesg" style="color:red;display: none;">&nbsp;商家卡号不能为空</font>
              </div>
         </div>
        <div class="control-group">
        <label class="control-label">宿舍:</label>
        <div class="controls">
          <select id="areaName" class="validate" name="vendor.areaName" class="dormitory_area" data-label="first">     
         </select>
         <select id="buildingName" class="validate" data-label="second">
         </select><br>
         <font id="area_mesg" style="color:red;display: none;">&nbsp; 商家供水区域不能为空</font>
         <font id="building_mesg" style="color:red;display: none;">&nbsp; 商家供水楼不能为空</font>
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
<div class="alert alert-info info" id="change" style="width: 200px;height: 200px;" name="pass" role="alert">
    <button type="button"  class="close2 btn btn-info bt1 ">关闭</button>
    <img src="" id="big_img" alt="">
 </div>
<div class="black"></div>
<div class="black">
  <div class="data">数据正在加载中...
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
var tempor = null;
//getData方法(ajax)
 function getData(){
	 mask('正在加载中,请稍后...');
   $.ajax({
   type:'POST',
   url:'vendor_usersInfo.action?aa='+Math.random(),
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
	       $("<td>"+el.areaName+"-"+el.buildingName+"</td>").appendTo($tr);
	       $("<td>"+
	    	 "<img onclick='reavel(this)' id='img' src='images/show.jpg' height='50' width='45' value='upload/vendor/"+el.picUrl+"'/>"+
	    	 "</td>").appendTo($tr); 
	       $("<td class='center'>"+
	    	  "<a class='btn revise' onClick='revise(this)'>修改</a>"+
	          "<a data-toggle='modal' onClick='dele(this)' class='btn' role='button' href='#myModal'>删除</a>"+
	          "<a href='#myModal1' role='button' class='btn' data-toggle='modal' onClick='black(this)'>拉入黑名单</a>"+
	         "</td>").appendTo($tr);
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
 //点击修改按钮
 function revise(e){
     var name=$(e).parents('tr').find("td:eq(1)").html();
     var bank=$(e).parents('tr').find("td:eq(2)").html();
     var message=$(e).parents('tr').find("td:eq(3)").html();
     var array = message.split("-");
     var areaName=array[0];
     var buildingName=array[1];
     var id =$(e).parents('tr').find("#id");
     $("#loginName").val(name);
     $("#bankCard").val(bank);
     $("#tempId").val(id.val());
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
 //删除按钮
 function dele(e){
    $("#btn-true").unbind();
    $("#btn-true").bind('click',function(){
    var id =$(e).parents('tr').find("#id");
    $.ajax({
      type:'POST',
      url:'vendor_vendorUpdateAdmin.action?aa='+Math.random(),
      data:{'status':'S_DELETE','id':id.val()},
      success:function(){
			getData();
		}
     });
    });
}
//拉入黑名单
 function black(e){
 	$("#btn-true2").unbind();
 	$("#btn-true2").bind('click',function(){
 		var id = $(e).parents('tr').find("#id");
 		$.ajax({
 			type:'POST',
 			url:'vendor_vendorUpdateAdmin.action?aa='+Math.random(),
 			data:{'status':'S_BLACKLIST','id':id.val()},
 			success:function(){
 				getData();
 			}
 		});
 	});
 };
//修改 
function alter(){
	 var pass = validate();
	 if(!pass){
		 return false;
	 }
	 $.ajax({
			url:'vendor_vendorUpdateAdmin.action?aa='+Math.random(),
			type:'POST',
			data:{'vendor.bankCard':$("#bankCard").val(),
				  'id':$("#tempId").val(),
				  'vendor.areaName':$("#areaName").val(),
				  'vendor.buildingName':$("#buildingName").val()},
			success:function(data){
				$("#form").hide();
				$("#loginName").val("");
		        $("#bankCard").val("");
		        $("#tempId").val("");
		        clear();
		        getData();
			}
		});
};
//清空验证数据 
function clear(){
	 $("#name_mesg").hide();
	 $("#bank_mesg").hide();
	 $("#area_mesg").hide();
	 $("#building_mesg").hide();
}
//表单数据验证
function validate(){
	 var bank=$("#bankCard").val();
	 var myReg = /^[\u4e00-\u9fa5]+$/;
	 var flag=myReg.test(bank);
	 if(flag){
	     $("#bank_mesg").html("&nbsp;&nbsp;商家卡号格式错误 ");
	     $("#bank_mesg").show();
	     return false;
	 }
	 if($("#bankCard").val()==''){
	     $("#bank_mesg").show();
	     return false;
	 }
	 if($("#areaName").val()=='宿舍区'){
	     $("#area_mesg").show();
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
//点击关闭按钮
$("button[class^='close']").click(function(){
    $("div[class^='alert']").hide();
      $("div[class^='black']").hide();
});
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
//展示卫生许可证
function reavel(e){
	   $("#change").fadeIn("slow");
	     $(".black").show();
	     var zoomX=$("#change").width()/$("#img").width();
	     var zoomY=$("#change").height()/$("#img").height();
	     $("#big_img").attr("src",$(e).val());
	     $("#big_img").width(zoomX*$("#img").width());
	     $("#big_img").height(zoomY*$("#img").height());
};
//关闭窗口按钮事件
function closeWind(){
	clear();
	$("#form").hide();
}
</script>
</body>
</html>