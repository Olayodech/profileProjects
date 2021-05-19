$(document).ready(function(){
	$("#fileImage").change(function(){
		checkFileSize(this)
		showImageThumbNail(this);
	})	
})

function showImageThumbNail(fileInput){
	var file = fileInput.files[0];
	var reader = new FileReader();
	reader.onload = function(e){
		$("#thumbnail").attr("src", e.target.result);
	}
	reader.readAsDataURL(file)
}

function checkFileSize(fileInput){
	fileSize = fileInput.files[0].size;
	if(fileSize > 102400){
		alert('File Size too large')
	}
}
