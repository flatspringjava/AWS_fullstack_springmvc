const URL = cp + '/note/';
let noteService = (function() {
	function send(obj, callback) {
		$.ajax({
			url: URL + "new",
			method : 'post',
			dataType : "json",
			data : JSON.stringify(obj),
			contentType : "application/json; charset=utf-8",
			success : callback			
		})
	}
	function get(noteno, callback) {
		$.getJSON(URL + noteno).done(callback);
	}
	function receive(noteno, callback) {
		$.ajax({
			url: URL + noteno,
			method : 'put',
			dataType : "json",
			success : callback
		})
	}
	function remove(noteno, callback) {}
	function getSendList(id, callback) {
		$.getJSON(URL + "s/" + id).done(callback);
	}
	function getReceiveList(id, callback) {
		$.getJSON(URL + "r/" + id).done(callback);
	}
	function getReceiveCheckedList(id, callback) {
		$.getJSON(URL + "r/c/" + id).done(callback);
	}
	return {
		send:send
		,get:get
		,receive:receive
		,remove:remove
		,getSendList:getSendList
		,getReceiveList:getReceiveList
		,getReceiveCheckedList:getReceiveCheckedList		
	}	
})()

	// 컨트롤러에서 값을 가져올때 이런식의 초기화 및 선 작업 필요.
/*	function get(noteno, callback) {}
	function receive(noteno, callback) {}
	function remove(noteno, callback) {}
	function getSendList(id, callback) {}
	function getReceiveCheckedList(id, callback) {}
	return {send:send} */