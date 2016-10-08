<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<c:url value="/resources/js/jquery-2.0.0.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.cookie.js" />"></script>
<script src="<c:url value="/resources/js/common.js" />"></script>
<script src="https://datejs.googlecode.com/files/date.js"></script>
<script>
var $_userIds = null;
var $_chatgroups = null;
var $_chatFriendId = "";
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
		   url: "/queelt-api/api/chat/friends?page=0&size=10",
		   headers: geHeaderAuthenticate(),
		   statusCode: {
		        404: function() {
		          console.log("error");
		        },
		        200: function(result) {
		        	
		        	var data = result.items;
		        	$_userIds = data;
		        	for (var i=0; i<data.length;i++){
		        		$("#users").append("<li><a href='javascript:;' onclick=startChat('"+data[i].userId+"'); >"+data[i].name+"</a></li>");
		        	}
		        }
		      }
		});
	
});

function addFriend(){
	$('body').append("<div style='clear:left;'/><div style='float:left;'>Name or email:<br/><input type='text' onkeypress='search($(this).val());' /></div>");
}

function search(text){
	
	if (text != "undefined"  && text.length >= 3) {
	$.ajax({ 
		   type: "GET",
		   dataType: "json",
		   Accept : "application/json",
	       contentType: "application/json",
		   url: "/queelt-api/api/user/search/"+text+"?page=0&size=10",
		   headers: geHeaderAuthenticate(),
		   statusCode: {
		        404: function() {
		          console.log("error");
		        },
		        200: function(data) {
		        	
		        }
		      }
		});
	}
}

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
		   url: "/queelt-api/api/chat/creategroup",
		   headers: geHeaderAuthenticate(),
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


function startChat(chatFriendId){
	$_chatFriendId = chatFriendId;
	var refreshIntervalId = setInterval(function(){ loadMessages(); }, 2000);
	clearInterval(refreshIntervalId);
	setInterval(function(){ loadMessages(); }, 2000);
	
	
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
		   url: "/queelt-api/api/chat/friend/"+$_chatFriendId+"/message",
		   headers: geHeaderAuthenticate(),
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
		   
		   url: "/queelt-api/api/chat/friend/"+$_chatFriendId+"/messages?page=0&size=10",
		   headers: geHeaderAuthenticate(),
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
Friends:
<ul id="users">
</ul>
<input type="button" value="add friend" id="addFriend" onclick="addFriend()"/>
</div>



<div style="float:left; margin-left: 20px;display: none">
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