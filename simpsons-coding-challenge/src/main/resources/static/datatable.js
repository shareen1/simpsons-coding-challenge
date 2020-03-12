$(document).ready(function() {
	var table = $('#personTable').DataTable({
		"sAjaxSource" : "/characterlist",
		"sAjaxDataProp" : "",
		"order" : [ [ 0, "asc" ] ],
		"aoColumns" : [ {
			"mData" : "chId"
		},  {
			"mData" : "pictureURL",
			"render" : function(mData, type, row) {
				return '<img src="' + mData + '"  height="60" width="60" />';
			}
		}, {
			"mData" : "firstName"
		}, {
			"mData" : "lastName"
		}, {
			"mData" : "age"
		},{
			"mData" : "comments"
		},{
            "mData": null,
            "bSortable": false,
           "mRender": function (o) { return '<button type="button" class="btn btn-primary" id="edit">&nbsp;Edit&nbsp;&nbsp;&nbsp;</button>&nbsp;<button type="button" class="btn btn-danger" id="delete">Delete</button>' ; }
        }]
	});
	table.column(0).visible(false);
	$('#personTable tbody').on( 'click', 'button', function () {
		
        var data = table.row( $(this).parents('tr') ).data();
        //alert('Are You Sure You Want to edit '+data["firstName"] +'ChId :'+data["chId"]+this.id  );
        if(this.id=="edit"){
        	alert('you are in edit');
        	
        	
        }else{
        	//alert('you are in delete');
        
        	var formData = {
        			firstName : data["firstName"],
        			chId : data["chId"]
				}
        	$.ajax({
        	    type: 'DELETE',
        	    url: 'http://localhost:8083/deleteChracter',
        	    contentType: 'application/json',
        	    data: JSON.stringify(formData), // access in body
        	}).done(function () {
        	    console.log('SUCCESS');
        		window.location.replace("http://localhost:8083");
        	}).fail(function (msg) {
        	    console.log('FAIL');
        	}).always(function (msg) {
        	    console.log('ALWAYS');
        	});
        	
        }
       // window.location.replace("http://localhost:8083/createupdate.html?id="+data.id+"&firstName="+data.firstName+"&lastName="+data.lastName+"&pictureURL="+data.pictureURL+"&age="+data.age);
               
    } );
});