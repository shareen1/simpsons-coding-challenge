$(document).ready(function() {
	$("#id_form").submit(function(event){
		event.preventDefault();
		ajaxPost();
		 });
function ajaxPost(){
	var formData = {
			chId : $("#chId").val(),
			 firstName : $("#firstName").val(),
			 lastName : $("#lastName").val(),
			 comments : [$("#comments").val()],
			 age : $("#age").val()
			}
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8083/postCharacter',
        contentType: "application/json",
        dataType: 'json',
        data:JSON.stringify(formData),
        success: function(result) {
            console.log(result);
            if(result.status == "success")
            	{
            	$("#postResultDiv").html(""+result.data.firstName+"Post Successfully");
            	window.location.replace("http://localhost:8083");
            	}else{
            		$("#postResultDiv").html("<strong>ERROR</strong>");
            	 	}
        },
            	 	error :function(e){
            	 		alert("Error")
            	 		 console.log("Error",e);
            	 		}          

    });
}
});