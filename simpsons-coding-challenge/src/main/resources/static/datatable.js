$(document).ready(function() {
	var table = $('#personTable').DataTable({
		"sAjaxSource" : "/characterlist",
		"sAjaxDataProp" : "",
		"order" : [ [ 0, "asc" ] ],
		"aoColumns" : [  {
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
		}, {
			"mData" : "comments"
		} ]
	});
});