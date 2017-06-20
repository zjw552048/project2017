<%@ page pageEncoding="utf-8"%>
<%@ include file="common/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/meta.jsp" %>
<title>主页</title>
<%@ include file="common/css.jsp" %>
<%@ include file="common/js.jsp" %>
</head>
<body>
    <button id="logout_btn">退出</button>
	<h1>index!</h1>
	<h1>${sessionScope.SESSION_sysUserId }</h1>
	<h1>${basePath }</h1>
	<h1>${staticPath }</h1>
	
</body>
</html>