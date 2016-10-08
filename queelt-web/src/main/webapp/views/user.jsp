<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<c:url value="/resources/js/jquery-2.0.0.min.js" />"></script>
<script src="https://datejs.googlecode.com/files/date.js"></script>
<script>
var $_userIds = null;
var $_chatgroups = null;
var $_chatGroupId = "";
var $_userId = document.location.href.substring(document.location.href.lastIndexOf("/")+1);
var $_links = null;
$(document).ready(function(){
	$("#chat").click(function(){chat();});
	$("#sendMessage").click(function(){sendMessage();});
	$(document).keypress(function(e) {
	    if(e.which == 13) {
	    	sendMessage();
	    }
	});
	
	$.ajax({ 
		   type: "GET",
		   dataType: "json",
		   Accept : "application/json",
	       contentType: "application/json",
		   url: "/queelt-api/api/user/"+$_userId+"/all/",
		   statusCode: {
		        404: function() {
		          console.log("error");
		        },
		        200: function(data) {
		        	$_userIds = data;
		        	for (var i=0; i<data.length;i++){
		        		
		        		$("#users").append("<li><input type='checkbox' id='"+data[i].id+"'/>"+data[i].name+"</li>");
		        		
		        		
		        	}
		        	
		        	loadGroups();
		        }
		      }
		});
	
});

function chat(){
	var _userIds = new Array();
	
	$("input[type='checkbox']:checked").each(function(cb,i){
		var _cb = $(this);
		_userIds.push($.grep( $_userIds, function( n, i ) {
			  return n.id===$(_cb).attr("id");
			})[0].id);
	});
	
	$.ajax({ 
		   type: "POST",
		   dataType: "json",
		   Accept : "application/json",
	       contentType: "application/json",
		   data: JSON.stringify(_userIds),
		   url: "/queelt-api/api/chat/user/"+$_userId+"/creategroup",
		   statusCode: {
		        404: function() {
		          console.log("error");
		        },
		        200: function(data) {
		        	loadGroups();
		        }
		      }
		});

}

function loadGroups(){
	$.ajax({ 
		   type: "POST",
		   dataType: "json",
		   Accept : "application/json",
	       contentType: "application/json",
		   url: "/queelt-api/api/chat/user/"+$_userId+"/allgroups",
		   statusCode: {
		        404: function() {
		          console.log("error");
		        },
		        200: function(data) {
		        	$_chatgroups = data;
		        	$("#group").html("");
		        	for (var i=0; i<data.length;i++){
		        		
		        		$("#group").append("<li><a href='javascript:;' onclick=startChat('"+data[i].id+"'); >"+data[i].name+"</a></li>");
		        		
		        		
		        	} 
		        }
		      }
		});
}
function startChat(chatGroupId){
	$_chatGroupId = chatGroupId;
	var refreshIntervalId = setInterval(function(){ loadMessages(); }, 2000);
	clearInterval(refreshIntervalId);
	setInterval(function(){ loadMessages(); }, 500);
	
	
}
function sendMessage(){
	if ($.trim($("#txtMessage").val()) != "") {
	var o =new Object();
	o.message = $("#txtMessage").val();
	$.ajax({ 
		   type: "POST",
		   dataType: "json",
		   Accept : "application/json",
	       contentType: "application/json",
		   data: JSON.stringify(o),
		   url: "/queelt-api/api/chat/user/"+$_userId+"/group/"+$_chatGroupId+"/message",
		   statusCode: {
		        404: function() {
		          console.log("error");
		        },
		        200: function(data) {
		        	$("#txtMessage").val("");
		        }
		      }
		});
	}
}
function loadMessages(){
	$.ajax({ 
		   type: "GET",
		   dataType: "json",
		   Accept : "application/json",
	       contentType: "application/json",
		   
		   url: "/queelt-api/api/chat/user/"+$_userId+"/group/"+$_chatGroupId+"/message?page=0&size=10",
		   statusCode: {
		        404: function() {
		          console.log("error");
		        },
		        200: function(dataResult) {
		        	$("#messages").html("");
		        	var data = dataResult.content;
		        	$_links = dataResult.links;
					for (var i=0; i<data.length;i++){
		        		$("#messages").append("<p><span>"+new Date(data[i].createDate).toString("dd/MM/yyyy HH:mm:ss")+"</span> - <b>"+data[i].userName+"</b> <br/>"+data[i].message+"</p>");
		        		
		        	}	
		        }
		      }
		});
}
</script>
</head>
<body>
<div style="float:left;">
Users:
<ul id="users">
</ul>
<input type="button" value="create group" id="chat"/>
</div>

<div style="float:left; margin-left: 20px">
Grupos:
<ul id="group" >
</ul>
</div>

<div style="float:left; margin-left: 20px">
<table>
	<tr>
		<td>
			<div id="chatStart">
				<textarea rows="5" cols="50" id="txtMessage"></textarea><br/>
				<input type="button" value="send" id="sendMessage"/>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<div id="messages">
			</div>
		</td>
	</tr>
</table>
</div>

</body>
</html>