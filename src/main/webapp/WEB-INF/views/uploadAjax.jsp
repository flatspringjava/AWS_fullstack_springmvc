<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>2023. 4. 7.오전 10:35:15</title>
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<style>
.bigPictureWrapper {
	position : absolute;
	display : none;
	justify-content:center;
	align-items:center;
	top:0;
	width:100%;
	height:100%;
	background-color:gray;
	z-index:100;
	background:rgba(255,255,255,0.5);	
}
.bigPicture {
	position:relative;
	display:flex;
	justify-content:center;
	align-items:center;
}
.bigPivture img {
	max-width : 600px;
}
</style>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<label for="files"><i class="fas fa-folder-open"></i></label>
			<input type="file" name="files" multiple id="files">
		<input type="reset" value="reset"/>
		<button>submit</button>
	</form>	
	<div class="uploadResult">
		<ul>			
		</ul>
	</div>
	<div class="bigPictureWrapper">
		<div class="bigPicture">
		</div>
	</div>
	
<script>
/* function showImage(param) {
	alert(param);
	$(".bigPictureWrapper").css("display", "flex").show();
	$(".bigPicture")
	.html("<img src='/display?" + param + "'>")
	.animate({width:'100%', height:'100%'}, 1000);
} */
$(function() {
	$(".bigPictureWrapper").click(function() {
		$(".bigPicture").animate({width:0, height:0}, 1000)
		setTimeout(function() {
			$(".bigPictureWrapper").hide();
		}, 1000)		
	})
})
$(function() {
	function checkExtension(files) {
		const MAX_SIZE = 5 * 1024 *1024; //업로드 할 수 있는 최대 파일 크기를 5MB로 지정.
		const EXCLUD_EXT = new RegExp("(.*?)\.(js|jsp|asp|php)"); // 파일 확장자는 js,jsp,asp,php를 제한
				
		for(let i in files) {
			if(files[i].size >= MAX_SIZE || EXCLUD_EXT.test(files[i].name)) {
				return false;
			}			
		}		
		return true;
	}
		$(".uploadResult ul").on("click", ".img-thumb", function() {
			event.preventDefault();
			$(".bigPictureWrapper").css("display", "flex").show();
			var param = $(this).find("img").data("src")		
	 		$(".bigPicture")
			.html("<img src='/display?" + param + "'>")
			.animate({width:'100%', height:'100%'}, 1000);
		})
		$(".uploadResult ul").on("click", ".btn-remove", function() {
		event.preventDefault();			
		var file = $(this).data("file");
		$.getJSON("/deleteFile?"+file).done(function(data) {
			console.log(data);
		});
	});
		
	
 	function showUploadedFile(uploadResultArr) {
		var str = "";
		console.log(str)
		for(var i in uploadResultArr) {
			// 여기서 image File을 올려도 !uploadResultArr 조건문이 동작. 무한 루프.	
			// uploadAjax 메서드에 @ResponseBody가 없음. 하지만 의문은 name값이 undifind인 것은 인정.
			// 하지만, 왜 for문이 무한루프 였는지?
			let obj = uploadResultArr[i];
			obj.thumb="on";
			var params = new URLSearchParams(obj).toString();
			if(!obj.image) { // name값이 undifind
			str += '<li><a href="/download?' + params + '"><i class="far fa-file"></i> ';
/* 			console.log(str); */
		}
		else {
			obj.thumb="off";
			var params2 = new URLSearchParams(obj).toString();
			str += '<li><a class="img-thumb" href=""><img src="/display?' + params + '" data-src="' + params2 + '" >';
			/* console.log(str); */
		}		
			 str += obj.name + '</a> <i class="fas fa-times-circle btn-remove" data-file="' + params + '"></i> </li>';
	}
		/* console.log(str); */
		$(".uploadResult ul").append(str);
}
	
	$("form button").click(function() {
		event.preventDefault();
		let files = $(":file").get(0).files;
		console.log(files);
		if(!checkExtension(files)) {
			alert("조건 불일치");
			alert("해당 메세지는 files 확장자 및 크기에 대한 규칙을 어겼을때 출력되는 메세지 입니다.")
			return false;
		}
		
		let formData = new FormData();
		for(let i in files) {
			formData.append("files", files[i]);
		}
		
		$.ajax({
			url : "/uploadAjax",
			processData : false,
			contentType : false,
			data : formData,
			method : "post",
			success : function(data) {
				console.log(data);
				$("form").get(0).reset();
				showUploadedFile(data);
			}
		})		
	})
})
</script>
	
</body>
</html>