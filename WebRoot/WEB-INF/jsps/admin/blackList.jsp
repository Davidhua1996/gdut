<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <title>管理员黑名单页面</title>
    <meta charset="UTF-8" />
    <meta name="viewp   ort" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">   
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/uniform.css" />
    <link rel="stylesheet" href="css/select2.css" />
    <link rel="stylesheet" href="css/unicorn.main.css" />
    <link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />
    <link rel="stylesheet" href="css/load.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

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
      <li >

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
      <li class="active">
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
  <button class="btn btn-info  next"    >后一页</button>

</div>
<div class="pagination-info">Displaying 1 to 10 of 114 items</div><div style="clear:both;"></div>
    </div>

                     
                        </div>
                    </div>
                    </div>

            </div>

        </div>
<div id="myModal1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">是否拉入白名单</h3>
  </div>
  <div class="modal-body">
    <p>确定要拉入白名单?</p>
  </div>
  <div class="modal-footer">
    <button class="btn" id="btn-true"  aria-hidden="true" data-dismiss="modal">是</button>
    <button class="btn btn-primary"data-dismiss="modal">否</button>
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
<script src="script/jquery.ui.custom.js"></script>
<script src="script/bootstrap.min.js"></script>
<script src="script/jquery.uniform.js"></script>
<script src="script/select2.min.js"></script>
<script src="script/base.js"></script>
<script src="script/jquery.easyui.min.js"></script>
<script src="script/pagediv.js"></script>
<script src="script/load.js"></script>
<script>
$(function(){
	getData();
});
 function getData(){
	 mask('正在加载中,请稍后...');
   $.ajax({
  type:'GET',
     url:'vendor_blackList.action',
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
       $("<td>"+el.companyName+"</td>").appendTo($tr);
       $("<td>"+el.loginName+"</td>").appendTo($tr);
       $("<td>"+el.bankCard+"</td>").appendTo($tr);
       $("<td>"+el.areaName+"</td>").appendTo($tr);
       $("<td>"+
  	    	"<img onclick='change(this)' id='img' src='images/show.jpg' height='50' width='45' value='upload/vendor/"+el.picUrl+"'/>"+
  	    "</td>").appendTo($tr);
       $("<td class='center'><button class='btn btn-info pass' onclick='pass(this)' data-toggle='modal' href='#myModal1'>白名单</button></td>").appendTo($tr);
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
 //点击白名单
function pass(e){
	$("#alert").show();
	var id = $(e).parents('tr').find("#id").val();
	$("#btn-true").unbind();
	$("#btn-true").bind("click",function(){
		$.ajax({
			url:'vendor_vendorUpdateAdmin.action?aa='+Math.random(),
			type:'POST',
			data:{'id':id,
				  'status':'S_APPROVED'},
			success:function(data){
		        getData();
			}
		});
	});
}
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
</body>
</html>