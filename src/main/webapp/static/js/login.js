$(function(){
	$("#login_btn").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		var verificationCode = $("#verificationCode").val();
		if(isValid(username,password,verificationCode)){
			$.ajax({
				url:"login",
				data:{
					username:username,
					password:password,
					verificationCode:verificationCode
				},
				type:"post",
				dataType:"json",
				success:function(data){
					if(data.result=="true"){
						location.href = "index";
					}else{
						easyShow(data.data);
					}
				},
				error:function(){
					
				}
			})
		}
	})
})

function isValid(username,password,verificationCode){
	if(verificationCode.length==0){
		easyShow("请输入验证码")
		return false;
	}
	if(username.length==0){
		easyShow("请输入用户名")
		return false;
	}
	if(password.length==0){
		easyShow("请输入密码")
		return false;
	}
	return true;
}

function easyShow(msg){
	$.messager.show({
		title:"提示",
		msg:msg,
		showType:"show"
	});
}