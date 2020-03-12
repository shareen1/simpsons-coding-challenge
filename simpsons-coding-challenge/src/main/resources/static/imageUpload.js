$(document).ready(function() {
$("#ImageUpload").submit(function(event) {
	event.preventDefault();
	ajaxSubmitForm();
});
function ajaxSubmitForm() {
	var form = $('#ImageUpload')[0];
	var data = new FormData($("#ImageUpload")[0]);
	$("#upload").prop("disabled", true);
	$.ajax({
			type: "POST",
			enctype: 'multipart/form-data',
			url: "http://localhost:8083/imageUpload",
			data: data,
			processData: false,
			contentType: false,
			cache: false,
			timeout: 1000000,
			success: function(data, textStatus, jqXHR) {
				$("#resultData").html(data);
				console.log("SUCCESS : ", data);
				$("#upload").prop("disabled", false);
				$('#ImageUpload')[0].reset();
				},
				error: function(jqXHR, textStatus, errorThrown) {
					$("#resultData").html(jqXHR.responseText);
					console.log("ERROR : ", jqXHR.responseText);
					$("#upload").prop("disabled", false);
			}
		});
}
});