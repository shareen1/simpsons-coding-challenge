$(document)
		.ready(
				function() {
					var table = $('#personTable')
							.DataTable(
									{
										"sAjaxSource" : "/characterlist",
										"sAjaxDataProp" : "",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													"mData" : "chId"
												},
												{
													"mData" : "pictureURL",
													"render" : function(mData,
															type, row) {
														return '<img src="'
																+ mData
																+ '"  height="60" width="60" />';
													}
												},
												{
													"mData" : "firstName"
												},
												{
													"mData" : "lastName"
												},
												{
													"mData" : "age"
												},
												{
													"mData" : "comments",
													"width" : "350px"
												},
												{
													"mData" : null,
													"bSortable" : false,
													"mRender" : function(o) {
														return '<button type="button" class="btn btn-primary" id="edit">&nbsp;Edit&nbsp;&nbsp;&nbsp;</button>&nbsp;<button type="button" class="btn btn-danger" id="delete">Delete</button>';
													}
												} ]
									});
					table.column(0).visible(false);
					$('#personTable tbody')
							.on(
									'click',
									'button',
									function() {

										var data = table.row(
												$(this).parents('tr')).data();
										// alert('Are You Sure You Want to edit
										// '+data["firstName"] +'ChId
										// :'+data["chId"]+this.id );
										if (this.id == "edit") {
											alert('you are in edit');

											var firstName = data["firstName"];
											var lastName = data["lastName"];
											var age = data["age"];
											var pictureURL = data["pictureURL"];
											var comments = data["comments"];

											$(this).parents("tr").find(
													"td:eq(1)").html(
													'<input type="text" class="form-control" name="firstName" value="'
															+ firstName + '">');
											$(this).parents("tr").find(
													"td:eq(2)").html(
													'<input type="text" class="form-control" name="lastName" value="'
															+ lastName + '">');
											$(this).parents("tr").find(
													"td:eq(3)").html(
													'<input type="number" class="form-control" name="age" value="'
															+ age + '">');
											$(this).parents("tr").find(
													"td:eq(4)").html(
													'<textarea class="form-control" rows="5" name="comments" >"'
															+ comments + '"');

											$(this)
													.parents("tr")
													.find("td:eq(5)")
													.prepend(
															"<button class='btn btn-info' id='update'>Update</button>&nbsp;<button class='btn btn-warning' id='cancel'>Cancel</button>")
											$(this).hide();

										} else if (this.id == "delete") {
											alert('you are in delete');

											var formData = {
												firstName : data["firstName"],
												chId : data["chId"]
											}
											$
													.ajax(
															{
																type : 'DELETE',
																url : 'http://localhost:8083/deleteChracter',
																contentType : 'application/json',
																data : JSON
																		.stringify(formData), // access
																								// in
																								// body
															})
													.done(
															function() {
																console
																		.log('SUCCESS');
																window.location
																		.replace("http://localhost:8083");
															})
													.fail(function(msg) {
														console.log('FAIL');
													}).always(function(msg) {
														console.log('ALWAYS');
													});

										} else if (this.id == "update") {
											//table.ajax.reload( null, false );
											var data = table.row(
													$(this).parents('tr'))
													.data();
											//var firstName1=$(this).closest('tr').find('td:eq(1)').data('firstName');
											var formData = {
												chId : data["chId"],
												pictureURL : data["pictureURL"],
												firstName : $(this)
														.closest('tr')
														.find(
																'input[name="firstName"]')
														.val(),
												lastName : $(this)
														.closest('tr')
														.find(
																'input[name="lastName"]')
														.val(),
												comments : [ $(this)
														.closest('tr')
														.find(
																'textarea[name="comments"]')
														.val() ],
												age : $(this)
														.closest('tr')
														.find(
																'input[name="age"]')
														.val()
											}
											$
													.ajax({
														type : 'PUT',
														url : 'http://localhost:8083/putChracter',
														contentType : "application/json",
														dataType : 'json',
														data : JSON
																.stringify(formData),
														success : function(
																result) {
															console.log(result);
															if (result.status == "success") {
																window.location
																		.replace("http://localhost:8083");
															} else {
																$(
																		"#postResultDiv")
																		.html(
																				"<strong>ERROR</strong>");
															}
														},
														error : function(e) {
															alert("Error")
															console.log(
																	"Error", e);
														}

													});

										}
										// window.location.replace("http://localhost:8083/createupdate.html?id="+data.id+"&firstName="+data.firstName+"&lastName="+data.lastName+"&pictureURL="+data.pictureURL+"&age="+data.age);

									});
					$(document).on(
							'click',
							'span.editrow',
							function() {
								alert('edit');
							});
				});