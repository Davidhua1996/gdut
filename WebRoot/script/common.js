$(function(){
function fileChange(){
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
    function fileChange(target){
        var fileSize = 0;
        if (isIE && !target.files) {    // IE浏览器
            /**
             */
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            var file = fileSystem.GetFile(filePath);
            fileSize = file.Size;    // 文件大小，单位：b
        }
        else {   
            fileSize = target.files[0].size;
        }
        var size = fileSize / 1024 / 1024;
        if (size > 1) {
            alert("附件不能大于1M");
        }
    }
  
}


//点击删除按钮之后弹出选择框
$("[name=remove]").click(function(){
        $("#myModal").show();   
})
//移开输入框的时候判断输入框中的值是否为空
$(".required").keyup(function(){
    if($(this).val()==''){
        $(this).parent().find($("em")).removeClass("success")
    }
})
//提交表单的时候验证宿舍区域是否未选择
$("form").submit(function(){
    if($("#dormitory_area").val()=='宿舍区'){
         alert("宿舍区域不能为空")
          return false;
     }
    if($("#dormitory_building").val()=='宿舍楼'){
          alert("宿舍楼不能为空")
          return false;
    }
    if($("#dormitory_No").val()==''){
          alert("宿舍号不能为空")
          return false;
    }
 })
//点击修改按钮弹出表单，点击关闭按钮关闭表单
$(".revise").click(function(){
    $("#form").show();
})
$("#close").click(function(){
    $("#form").hide();
})
})
//表单验证
$("#login").validate({
        rules: {
            username: {
                required: true,
                minlength: 0
            },
            password: {
                required: true,
                minlength:0
            },
            brand:{
                required: true,
                minlength:0 
            },
            count:{
                required: true,
                minlength:0
            },
            img:{
                required: true
            },
            price:{
                required: true,
                minlength:0
            },
             companyName:{
                required: true,
                minlength:0
            },
             Bankcard:{
                required: true,
                minlength:0
            },
            businessName: {
                required:true,
                minlength:0}
            },
            identifying: {
                required:true,
                minlength:0
            },
            required:{
                required:true,
                minlength:0
            },
            messages: {
             required: {
                required: '必填信息',
                minlength: '必填信息不能为空'
            },
            username: {
                required: '请输入帐号',
                minlength: '姓名不能为空'
            },
            brand: {
                required: '请输入品牌',
                minlength: '品牌不能为空'
            },
            count: {
                required: '请输入数量',
                minlength: '数量不能为空'
            },
             businessName: {
                required: '请输入商家名称',
                minlength: '商家名称不能为空'
            },
            img: {
                required: '请选择图片'
               
            },
            price: {
                required: '请输入单价',
                minlength: '单价不能为空'
            },
            password: {
                required: '请输入密码',
                minlength: '密码不能为空'
            },
            companyName:{
                required:'请输入公司名称',
                minlength: '公司名称不能为空'
            },
             Bankcard:{
                required:'请输入银行卡号',
                minlength: '银行卡号不能为空'

            },
              Confirmpassword:{
                required:'请再一次输入密码',
                minlength: '密码不能为空',
                equalTo: "#password"
            },
                identifying:{ required: '请输入验证码'}
            },
                errorElement: "em",             
                success: function(label) {          
                label.text("")         
                .addClass("success");   
             }
         });
