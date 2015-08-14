<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html  lang="en">
<head>
  <title>管理员订单管理页面</title>
  <link rel="stylesheet" href="css/bootstrap.min.css" />
  <link rel="stylesheet" href="css/uniform.css" />
  <link rel="stylesheet" href="css/select2.css" />
  <link rel="stylesheet" href="css/unicorn.main.css" />
  <link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />
    <link rel="stylesheet" href="css/laydate.css" />
  <link rel="stylesheet" href="css/load.css"/>
    <link rel="stylesheet" href="css/laydate 2.css" />
 <meta charset="UTF-8" />
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">   
<style>
.widget-content{overflow-x:auto;overflow-y:hidden; }
  select{ width: 100px;}
  
 table th{
            white-space: nowrap;
        }
        table td{
            white-space: nowrap;
        }
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
      <li class="active">
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
<div style="float:left;margin-left:50px;padding-bottom:0px; position: relative; left: 10px;top: 13px;">
        
        按区域排:
      <select data-label="first" class="dormitory_area" id="dormitory_area">
        <option value="宿舍区" selected="">宿舍区</option>
      </select>
        &nbsp; &nbsp; &nbsp;
        按订单时间排
<input type="text" class="date" style="height:28px" id="J-xl"/>
 &nbsp;
        <input type="button" id="search" value="查询" class="btn btn-info" style="position: relative;top: -4px;" id="query"/>
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
	                  <th>订单号</th>
	                  <th>订水者姓名</th>
	                  <th>学号</th>
	                  <th>支付状态</th>
	                  <th>订水者宿舍</th>
	                   <th>品牌</th>
	                  <th>提供的公司</th>
	                  <th>数量</th>
	                  <th>总价</th>                
                  <th style="width:140px">操作</th>
                </tr>
              </thead>
              <tbody>
              	<tr>
          			<td colspan="11" >没有数据</td>
              	</tr>
              </tbody>
            </table>
           <div class="page">
  <button class="btn btn-info  previous" disabled="disabled">前一页</button>
  <input type="text" class="num"value="1" style="width: 50px;height:30px;margin-right: 10px;">
 <label for="">/<label class="Total" disabled="disabled">1</label></label>
  <button class="btn btn-info  next"   disabled="disabled">后一页</button>
</div>

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
      <h3 id="myModalLabel">是否撤销订单</h3>
    </div>
    <div class="modal-body">
      <p>撤销订单的操作不可逆，是否撤销订单?</p>
    </div>
    <div class="modal-footer">
      <button class="btn" data-dismiss="modal"id="btn-true" aria-hidden="true">是</button>
      <button class="btn btn-primary"
      data-dismiss="modal" >否</button>
    </div>
  </div>
</body>
<script src="script/jquery.min.js"></script>
  <script src="script/jquery.ui.custom.js"></script>
  <script src="script/bootstrap.min.js"></script>
  <script src="script/jquery.uniform.js"></script>
  <script src="script/select2.min.js"></script>
  <script type="text/javascript" src="script/jquery.validate.js"></script>
  <script type="text/javascript" src="script/jquery.validate.messages_cn.js"></script>
<script src="script/jquery.easyui.min.js"></script>
<script src="script/common.js"></script>
 <script src="script/load.js"></script>
<script src="script/laydate.js"></script>
<script src="script/pagediv.js"></script>
<script src="script/laydate.dev.js"></script>
<script>
  $(function(){
	//加载宿舍信息
	  $.ajax({
	  	  type:'POST',
	  	  url:'area_areaMessage.action',
	  	  dataType:'json',
	  	  success:function(data){
	  		  $(".dormitory_area").empty();
	  		  $(".dormitory_area").append("<option value=' '>宿舍区</option>");
	  		  $.each(data,function(commentIndex,comment){
	  			  $("<option></option>").val(comment['name'])
	  			  						.text(comment['name'])
	  			  						.appendTo(".dormitory_area");
	  		  });
	  	}
	  });
	  laydate({
		     elem: '#J-xl'
		 });
		  //动态改变日期框的位置
		 $("#J-xl").click(function(){ 
		    	 var date=$("#J-xl");
		    	 var top=date.offset().top;
		    	 var left=date.offset().left;
		   		 $("#laydate_box").get(0).style.left=left-200+'px';
		    	 $("#laydate_box").get(0).style.top=top+35+'px';
		});
  });
//getData方法
  function getData(){
 	 mask('正在加载中,请稍后...');
    $.ajax({
   type:'POST',
      url:'order_findForAdmin.action',
      data:{pageSize:'4',pageNum:$(".num").val(),
    	  	areaName:$(".dormitory_area").val(),
     	 	dateStr:$("#J-xl").val()},
      dataType:'json',
       success:function(data){
       $(".data-table tbody").html("");
      var pager = data.pager;
      $(".Total").text(pager.pageCount);
      if(jQuery.isEmptyObject(pager.recordList)){
   		 var $tr1=$("<tr class='gradeX'></tr>");
   		 $("<td colspan='12' align='right'>&nbsp;&nbsp;没有数据</td>").appendTo($tr1);
   		$tr1.appendTo(".data-table tbody");  
   	 }
       $.each(pager.recordList,function(i,el){
        var $tr=$("<tr class='gradeX'></tr>");
        $("<td>"+el.id+"</td>").appendTo($tr);
        $("<td>"+el.student_name+"</td>").appendTo($tr);
        $("<td>"+el.student_id+"</td>").appendTo($tr);
        if(el.status=='S_CREATE'){
        	$("<td>未支付</td>").appendTo($tr);
        }
        if(el.status=='S_PAYMENT'){
        	$("<td>已支付</td>").appendTo($tr);
        }
        if(el.status=='S_SENT'){
        	$("<td>已送货</td>").appendTo($tr);
        }
        if(el.status=='S_OVERDUE'){
        	$("<td>已过期</td>").appendTo($tr);
        }
        $("<td>"+el.areaName+"-"+el.student_dormitory+"-"+el.student_romNum+"</td>").appendTo($tr);
        $("<td>"+el.product_brand+"</td>").appendTo($tr);
        $("<td>"+el.vendor_companyName+"</td>").appendTo($tr);
        $("<td>"+el.product_quantity+"</td>").appendTo($tr);
        $("<td>"+el.totalAmount+"</td>").appendTo($tr);
        if(el.status!='S_SENT'){
	        $(" <td class='center'>"+
	          "<a href='#myModal' role='button' class='btn' name='remove' data-toggle='modal' onclick='dele(this)' >撤销订单</a>"+
	          "</td>").appendTo($tr);
        }else{
        	 $(" <td class='center'>"+
       	          "<a role='button' disabled='disabled' class='btn' name='remove' data-toggle='modal'  >撤销订单</a>"+
       	          "</td>").appendTo($tr);
        }
        $("<input type='hidden' id='claz' value='"+el.clazz+"'/>").appendTo($tr);
        $("<input type='hidden' id='maj' value='"+el.major+"'/>").appendTo($tr);
        $tr.appendTo(".data-table tbody");     
      });
       if((parseInt($(".Total").text())<=1)&&(pager.currentPage==1)){
   	  	$(".Total").text("1");
   	  	$(".previous").attr("disabled","true");
   	  	$(".Total").attr("disabled",false);
   		$(".next").attr("disabled","true");
     }else if(pager.currentPage==1){
   	  $(".previous").attr("disabled","true");
   	  $(".next").attr("disabled",false);
   	$(".Total").attr("disabled",false);
     }
       unmask();
    }
    });
 }
 

 //撤销订单
  function dele(e){
	 $("#btn-true").unbind();
     $("#btn-true").bind('click',function(){
    	var id = $(e).parents('tr').find("td:eq(0)").html();
    	$.ajax({
    		type:'POST',
    		url:'order_cancel.action?aa=Math.random()',
    		dataType:'json',
    		data:{'id':id},
    		success:function(data){
    			if(data.errorCode == "0000"){
    				getData();
    			}else{
    				alert(data.errorMessage);	
    			}
    		}
    	});
      });
   };
   //查询订单
   $("#search").click(function(){
	  getData(); 
   });
</script>
</html>