$(function(){ 
//点击修改按钮弹出表单，点击关闭按钮关闭表单
$(".revise").click(function(){
    $("#form").show();
})
$("#close").click(function(){
    $("#form").hide();
})
//验证输入密码和确认密码是否相同
$("form").submit(function(){
    if($("#password").val()==$("#Confirmpassword").val()){
          return true;
    }
    else{
        alert("两次输入的密码不一样")
        return false;
    }
})


})
