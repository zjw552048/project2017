<%@ page pageEncoding="utf-8"%>
<%@ include file="common/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Login</title>
<link rel="stylesheet" href="${staticPath }/css/login.css">
<link rel="stylesheet" href="${staticPath }/plugins/jquery-easyui-1.5.1/themes/bootstrap/easyui.css">

<script src="${staticPath }/js/jquery/jquery-1.9.1.js"></script>
<script src="${staticPath }/plugins/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script src="${staticPath }/plugins/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>

<script src="${staticPath }/js/login.js"></script>
</head>
<body>
	<div>
		<span>用户名</span>
		<input id="username" name="username" type="text" value="${param.username }" placeholder="用户名">
	</div>
	<div>
		<span>密&nbsp;码</span>
		<input id="password" name="password" type="password" value="${param.password }" placeholder="密码">
	</div>
	<div>
		<span>验证码</span>
		<input id="verificationCode" name="verificationCode"type="text"  placeholder="验证码">
	</div>
	<div>
		<input if="remenber" name="remenber"type="checkbox" >
		<label for="remenber">记住密码</label>
	</div>
	<div>
		<img id="kaptchaImage" src="captcha-image" onclick="this.src='captcha-image?x='+Math.random();" alt="验证码" title="点击更换"  style="margin-bottom: -3px"/>
		<img id="login_btn" alt="登录" src="static/images/login.png" />
	</div>
	<div>
		<span class="errorText">${errorMsg }</span>
	</div>
</body>
</html>


