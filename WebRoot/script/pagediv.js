$(function(){
//记录当前页数
var offset;
//点击前一页的按钮
 $(".previous").click(function(){
	 if(isNaN($(".num").val())){
		 $(".num").val("1");
         $(".previous").attr("disabled","true");
         if(parseInt($(".Total").text())>=2){
         	$(".next").attr("disabled",false);
         }else{
        	 $(".next").attr("disabled","true");
         }
	 }else{
	    var num=$(".num").val();
	    if(parseInt(--num)<=1){
	    	 $(".num").val(1);
	         $(".previous").attr("disabled","true");   
	    }
	    else{
		   $(".num").val(--num);
	    }
	    if(parseInt($(".Total").text())>=2){
	    	$(".next").attr("disabled",false);
	    }else{
	    	$(".next").attr("disabled","true");
	    }
	 }
	//ajax分页获取新一页的数据
    getData();
 }) 
//点击后一页的按钮
$(".next").click(function(){
	 if(isNaN($(".num").val())){
		 $(".num").val("1");
         $(".previous").attr("disabled","true");
         if(parseInt($(".Total").text())>=2){
         	$(".next").attr("disabled",false);
         }
	}else{
		   var num=$(".num").val();
		  if(parseInt(++num)>=parseInt($(".Total").text())){
			  $(".num").val(parseInt($(".Total").text()));
		      $(".next").attr("disabled","true");
		  }
		  else{
			  $(".num").val(++num);
		  }
		  if(parseInt($(".Total").text())>=2){
		     $(".previous").attr("disabled",false);
		  }else{
			  $(".previous").attr("disabled","true");
		  }
	}
	//ajax分页获取新一页的数据
	getData();
 })  
//点击输入框
$(".num").click(function(){
	offset=$(this).val();
})
$(".num").blur(function(){
//  if((parseInt($(".num").val())>parseInt($(".Total").html()))||isNaN($(".num").val())){
//    $(".num").val("1");
//    $(".previous").attr("disabled","true");
//  }
//  if(parseInt($(".Total").text())>=1){
//	     $(".previous").attr("disabled",false);
//  }else{
//		 $(".previous").attr("disabled","true");
//  }
	$(this).val(offset);
//ajax分页获取新一页的数据
//  getData();
});
//判断按下回车键
$(document).on('keyup',function(e){
          if(e.keyCode === 13){
//        	alert($(".num").val()+""+$(".Total").val());
        	if(isNaN($(".num").val())){
        		 $(".num").val("1");
                 $(".previous").attr("disabled","true");
                 if(parseInt($(".Total").text())>=2){
                 	$(".next").attr("disabled",false);
                 }else{
                	$(".next").attr("disabled","true");
                 }
                 getData();
        		return false;
        	}
            if((parseInt($(".num").val())>parseInt($(".Total").text()))||
            		(parseInt($(".num").val())<=1)){
                $(".num").val("1");
                $(".previous").attr("disabled","true");
                if(parseInt($(".Total").text())>=2){
                	$(".next").attr("disabled",false);
                }
                else{
                	$(".next").attr("disabled","true");
                 }
                getData();
                return false;
            }else if(parseInt($(".num").val())==parseInt($(".Total").text())){
 				$(".next").attr("disabled","true");
 				if(parseInt($(".Total").text())>=2){
                	$(".previous").attr("disabled",false);
                }else{
                    $(".previous").attr("disabled","true");
                }
            	getData();
            	return false;
            } else{
            	$(".previous").attr("disabled",false);
            	$(".next").attr("disabled",false);
            	getData();
            	return false;
            }
          }
	});

})