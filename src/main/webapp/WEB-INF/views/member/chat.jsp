<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>2023. 4. 12.오후 12:10:25</title>
<style>
	textarea {resize: none;}
</style>
</head>

<body>
	<c:if test ="${empty member}">
	<form method="post" action="login">
		<input name="id">
		<input name="pw" type="password">
		<button>로그인</button>
		<p>${msg}</p>
	</form>
	</c:if>
	<c:if test="${not empty member}">
	<p>로그인 됨</p>
	<p>${member.name} / ${member.id}</p>
	<a href="logout">로그아웃</a>
	<h1>chat client</h1>
	<form name="frm">
		<textarea rows="10" readonly name ="area"></textarea><br>
		<input name="message" autofocus>
		<button>전송</button>
	</form>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script>
		var ws = new WebSocket("ws://localhost/chat");
		ws.onopen = function(ev) {
			console.log("연결 완료", ev)
		}
		ws.onclose = function(ev) {
			console.log("연결 종료", ev);			
		}
		ws.onmessage = function(ev) {
			console.log(ev.data);
			frm.area.value = frm.area.value + ev.data + "\n";			
		}
		$(function() {
			$(document.frm).submit(function() {
				event.preventDefault();
				//메세지의 값을 var msg에 담음
				var msg = this.message.value;
				//초기화
				this.message.value="";
				var obj = {id:sender, msg:msg};
/* 				JSON.stringify를 사용하면 문자열의 형태로 출력됨.
				만약 사용하지 않으면 obj, obj만 출력될 것임. */
				ws.send(JSON.stringify(obj));
				console.log(msg);
			})
		})
	</script>
	</c:if>
</body>
</html>