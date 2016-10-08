$(document).ready(function() {
	$("#Register").click(function() {
		createUser($("form"));
	});
	
	$("#signin").click(function() {
		authenticate($("#username").val(),$("#password").val());
	});
});

function createUser(form) {
	var o = new Object();
	o.name = $(form).find('input[name="name"]').val();
	o.password = $(form).find('input[name="password"]').val();
	o.email = $(form).find('input[name="email"]').val();
	;
	$.ajax({
		type : "POST",
		dataType : "json",
		Accept : "application/json",
		contentType : "application/json",
		data : JSON.stringify(o),
		url : "/queelt-api/api/user",
		statusCode : {
			500 : function(data) {
				alert(data);
			},
			404 : function(data) {
				alert(data);
			},
			200 : function(data) {
				authenticate(o.email, o.password);
			}
		}
	});
}

function authenticate(user, pass) {
	var o = new Object();
	o.username = user;
	o.password = pass;
	o.grant_type = "password";
	o.client_id = "clientapp";
	o.client_secret = "clientapp";
	$.ajax({
		type : "GET",
		dataType : "json",
		Accept : "application/json",
		contentType : "application/json",
		data : o,
		url : "/queelt-api/oauth/token",
		statusCode : {
			500 : function(data) {
				alert(data);
			},
			404 : function(data) {
				alert(data);
			},
			401 : function(data) {
				alert("Login or password invalid!");
			},
			200 : function(data) {
				createOauthCookie(data.access_token, data.refresh_token);
				location.href = "chat.jsp";
			}
		}
	});
}

function createOauthCookie(access_token, refresh_token) {
	$.cookie("access_token", access_token);
	$.cookie("refresh_token", refresh_token);
}

function geHeaderAuthenticate()
{
	return {'Authorization':'bearer '+$.cookie("access_token")};
}