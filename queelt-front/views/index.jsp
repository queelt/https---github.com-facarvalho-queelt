<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<c:url value="/resources/js/jquery-2.0.0.min.js" />"></script>
<script>
$(document).ready(function(){
	$("#enter").click(function(){
		enterChat();
	});
	
	$(document).keypress(function(e) {
	    if(e.which == 13) {
	    	enterChat();
	    }
	});
	
	function enterChat()
	{
		var o = new Object();
		o.name = $("#user").val();
		$.ajax({ 
			   type: "POST",
			   dataType: "json",
			   Accept : "application/json",
		       contentType: "application/json",
			   data: JSON.stringify(o),
			   url: "/api/user/create",
			   statusCode: {
			        404: function() {
			          console.log("error");
			        },
			        200: function(data) {
			          location.href = "/chat/user/"+data.id;
			        }
			      }
			});
	}
});
</script>
</head>
<body>
<nobr>User: <input type="text" name="user" id="user"/> <input type="button"  id="enter" value="Enter"/></nobr> 
</body>
</html>