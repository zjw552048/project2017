$(function(){
	//获取ws地址
	$.ajax({
		url:"getWSUrl",
		success:function(url){
			WebSocketConnection(url);
		}
	})
})
function WebSocketConnection(url){
	console.log(url);
	var ws = new WebSocket(url);
	
	ws.onopen = function () {  
        console.log('Info: connection opened.');  
    };  
    ws.onmessage = function (event) {
    	var json = JSON.parse(event.data);
    	
    	if(typeof(ws.events[json.method]) == "function"){
        	ws.events[json.method](json.param);	
        }
    };
    ws.onclose = function (event) {
        console.log('Info: connection closed.');  
        console.log(event);  
//        location.href = basePath + "/login";
    };  
    //TODO
    ws.events = {
    		receiveMessage:receiveMessage,
    		forcedOffline:forcedOffline
    }
    
    window.onbeforeunload = function(){
    	ws.close();
    };
}
//TODO
function receiveMessage(data){
	alert(data);
}
function forcedOffline(data){
	$.messager.alert("系统提示",data,"warning",function(){
		top.location = basePath + "logout";
	})
	
}



