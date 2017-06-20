<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>index</title>
<script type="text/javascript" src="static/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#btn1").click(function(){
			$.ajax({
				url:"WebSocketController/sendMessage",
				dataType:"json",
				data:{methodName:"message",
					  parameter:"it is a parameter"
					  },
				success:function(result){
					alert("success!");
				}
			})
		})
		$("#btn2").click(function(){
			$.ajax({
				url:"testBean",
				success:function(data){
					console.log(data);
				},
				headers:{
					"Accept":"application/xml"
				}
			})
		})
	})
	
</script>
</head>
<body>
	<h1>sendTest！</h1>
	<button id="btn1">点我发送消息</button>
	<button id="btn2">测试ResponseBody</button>
</body>
</html>