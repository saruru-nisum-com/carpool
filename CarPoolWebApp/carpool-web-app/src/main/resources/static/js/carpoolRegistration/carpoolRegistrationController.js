carpoolRegApp
		.controller(
				'carpoolRegistrationController',
				function($scope, $state, localStorageService,
						carpoolRegistrationService, $rootScope, $filter) {
					// $scope, $timeout, categoryService, userService,
					// roleService, localStorageService, CategoryListModel,
					// RoleListModel, UserListModel,commonService,$state

					// driver/rider screen code starts here

					$scope.showError = false;
					$scope.cb2wheel = false;
					$scope.cb4wheel = false;
					$scope.lat = undefined;
					$scope.lng = undefined;
					$scope.selectedLocation = undefined;
					$scope.autocomplete = undefined;

					$scope
							.$on(
									'gmPlacesAutocomplete::placeChanged',
									function() {
										$scope.selectedLocation = $scope.autocomplete
												.getPlace().name;
										var location = $scope.autocomplete
												.getPlace().geometry.location;
										$scope.lat = location.lat();
										$scope.lng = location.lng();
										$scope.$apply();
									});

					/*
					 * @author dhiraj1125 Method to get the Driver/Rider details
					 * and check if he is already registered as a driver or the
					 * rider. @param userId
					 */
					$scope.getRegisteredDriverData = function() {
						var profileSessionData = localStorageService
								.get('profile');
						$scope.userId = profileSessionData.emailId;
						carpoolRegistrationService
								.getRegisterDriverData($scope.userId)
								.then(
										function(response) {
											angular
													.forEach(
															response,
															function(value, key) {
																if (value.isRider == 0) {// If
																	// isRider
																	// value
																	// is
																	// '0'
																	// then
																	// he
																	// is
																	// registered
																	// as
																	// Driver.
																	$scope.isRegisteredAsDriver = true;
																	$scope.autocomplete = value.location;
																	$scope.nearBy = value.nearby;
																	angular
																			.forEach(
																					value.vehicleType,
																					function(
																							value) {
																						if (value == 2)
																							$scope.cb2wheel = 2;
																						else
																							$scope.cb4wheel = 4;
																					})
																} else if (value.isRider == 1) {// If
																	// isRider
																	// value
																	// is
																	// '1'
																	// then
																	// he
																	// is
																	// registered
																	// as
																	// Rider.
																	// TODO:
																	// Logic to
																	// populate
																	// the input
																	// data if
																	// user is
																	// already
																	// registered
																	// as a
																	// Rider.
																	console
																			.log("Registered as Rider");
																}
															});
										});
					}

					/*
					 * On load method call to get the registered user data.
					 */
					$scope.getRegisteredDriverData();

					$scope.registerAsDriver = function() {
						console.log('checkbox values ' + $scope.cb2wheel
								+ 'and ' + $scope.cb4wheel);
						if ($scope.cb2wheel == false
								&& $scope.cb4wheel == false) {
							alert('Please select the vehicle type.');
						} else {
							var registrationId = Math
									.floor(Math.random() * 100000000) + 1;// static
							// for
							// now,
							// need
							// to
							// change

							var profileSessionData = localStorageService
									.get('profile');

							var userId = profileSessionData.emailId;// "dsingh@nisum.com"//profileSessionData.emailId;
							// alert("hi user id : "+userId);
							$scope.userId = userId
							if ($scope.cb2wheel == 2 && $scope.cb4wheel == 4) {
								var vehicleType = [ $scope.cb2wheel,
										$scope.cb4wheel ];
							} else if ($scope.cb2wheel == 2
									&& $scope.cb4wheel == 0) {
								var vehicleType = [ $scope.cb2wheel ];

							} else if ($scope.cb4wheel == 4
									&& $scope.cb2wheel == 0) {
								var vehicleType = [ $scope.cb4wheel ];

							}
							var location = $scope.selectedLocation;
							var latitude = $scope.lat;// static for now, need
							// to change
							var longitude = $scope.lng;// static for now, need
							// to change
							var nearby = $scope.nearBy;
							var mobile = "9000000000";// static for now, need
							// to change
							var emailNotification = true;// static for now,
							// need to change
							var isRider = 1;// static for now, need to change
							var createdDate = $filter('date')(new Date(),
									'yyyy-MM-dd');
							var modifiedDate = $filter('date')(new Date(),
									'yyyy-MM-dd');
							// window.alert(parseFromDate + " " + parseEndTime);

							$scope.registerDriverJson = {
								"registrationId" : registrationId,
								"userId" : userId,
								"vehicleType" : vehicleType,
								"location" : location,
								"latitude" : latitude,
								"longitude" : longitude,
								"nearby" : nearby,
								"mobile" : mobile,
								"emailNotification" : emailNotification,
								"isRider" : isRider,
								"createdDate" : createdDate,
								"modifiedDate" : modifiedDate
							}
							// window.alert("what am i sending to the server:::
							// "+JSON.stringify($scope.registerDriverJson));
							carpoolRegistrationService
									.registerAsDriver($scope.registerDriverJson)
									.then(
											function(response) {
												if (response.errorCode === 500) {
													$scope.message = response.errorMessage
												} else {
													$scope.isRegisteredAsDriver = true;
													alert('driver registered successfully.');
													// $scope.names =
													// response.records;
													// alert("*****
													// "+$scope.userId);
													carpoolRegistrationService
															.getRegisterDriverData(
																	$scope.userId)
															.then(
																	function(
																			response) {
																		if (response.errorCode === 500) {
																			$scope.message = response.errorMessage
																		} else {
																			// alert('harish
																			// service
																			// called
																			// successfully.');
																			window
																					.alert('singh from server.............. :)'
																							+ JSON
																									.stringify(response));
																		}
																	},
																	function(
																			response) {
																		// console
																		// window.alert("dks--
																		// "+response)
																	});
												}
											}, function(response) {
												// console
												// window.alert("dks--
												// "+response)
											});
							var onSuccess = function(data, status, headers,
									config) {
								$scope.isRegisteredAsDriver = true;
								alert('driver registered successfully.');
							};

							var onError = function(data, status, headers,
									config) {
								alert('Error occured.');
							};

							// $http.post('/student/submitData', {
							// student:$scope.student })
							// .success(onSuccess)
							// .error(onError);

						}

					}

					/*
					 * @author dhiraj1125 Method to update the Driver data.
					 */
					$scope.updateAsDriver = function() {
						console.log("Update driver data method called.");

						var profileSessionData = localStorageService
								.get('profile');
						var userId = profileSessionData.emailId;
						$scope.userId = userId

						if ($scope.cb2wheel == 2 && $scope.cb4wheel == 4) {
							var vehicleType = [ $scope.cb2wheel,
									$scope.cb4wheel ];
						} else if ($scope.cb2wheel == 2 && $scope.cb4wheel == 0) {
							var vehicleType = [ $scope.cb2wheel ];
						} else if ($scope.cb4wheel == 4 && $scope.cb2wheel == 0) {
							var vehicleType = [ $scope.cb4wheel ];
						}

						var data = {
							"userId" : userId,
							"location" : $scope.autocomplete,
							"nearBy" : $scope.nearBy,
							"vehicleType" : vehicleType,
						}
						carpoolRegistrationService
								.updateDriverData(data)
								.then(
										function(successResponse) {
											console
													.log("Driver data updated successfuly"
															+ successResponse);
										},
										function(errorResponse) {
											console
													.log("Failed to update the driver data."
															+ errorResponse);
										});

						var onSuccess = function(data, status, headers, config) {
							alert('Driver data updated successfully.');
						};

						var onError = function(data, status, headers, config) {
							alert('Error occured while updating the driver data.');
						};
					}

					$scope.registerAsRider = function() {
						// alert("update is clicked");
						console.log('register is clicked');
					}

					$scope.updateAsRider = function() {
						alert("update is clicked");
					}

					// driver/rider screen code ends here

					$scope.data = [ {
						id : '0',
						name : 'Select Vehicle Type'
					}, {
						id : '2',
						name : 'TwoWheeler'
					}, {
						id : '4',
						name : 'FourWheeler'
					} ];
					$scope.fromDate = {
						value : new Date(),
						currentDate : new Date()
					};
					$scope.toDate = {
						value : new Date(),
						currentDate : new Date()
					};

					$scope.startTime = {
						value : new Date(2015, 10, 10, 09, 00, 0)
					};
					$scope.endTime = {
						value : new Date(2015, 10, 10, 19, 00, 0)
					};

					// Vehicle type selection starts
					$scope.fnVehicleType = function() {
						$scope.seat = {
							value : 1
						};
					}
					// end

					
					/*
					 * @author Harish Kumar Gudivada Method to get the Carpool Details by carpoolid
					*/ 
					$scope.getCarpolData = function(id) {
						$scope.id = id;
						carpoolRegistrationService.getCarpolData($scope.id).then(function(response) {
											angular.forEach(response,function(value, key) {
																	$scope.autocomplete = value.location;
																	$scope.fromDate.value = value.fromDate;
																	$scope.seat.value = value.totalNoOfSeats;
																	$scope.toDate.value = value.toDate;
																	$scope.startTime.value = value.startTime;
																	$scope.endTime.value = value.toTime;
																	$scope.vehicleSelect= value.vehicleType;
																	
											});
										});
					}
					
					
					
					$scope.fnAddPostRide = function() {
						var vType = $scope.vehicleSelect;
						var vSeatCap = $scope.seat.value;
						var fromDate = $scope.fromDate.value;
						var toDate = $scope.toDate.value;
						var startTime = $scope.startTime.value;
						var endTime = $scope.endTime.value;
						var parseFromDate = $filter('date')(new Date(fromDate),
								'MM/dd/yyyy');
						var parseToDate = $filter('date')(new Date(toDate),
								'MM/dd/yyyy');
						var parseStartTime = $filter('date')(
								new Date(startTime), 'h:mm a');
						var parseEndTime = $filter('date')(new Date(endTime),
								'h:mm a');

						// Start date and End date difference validation starts
						// var d1 = new Date(parseFromDate);
						// var d2 = new Date(parseToDate);
						// var timeDiff = d2.getTime() - d1.getTime();
						// var DaysDiff = timeDiff / (1000 * 3600 * 24);
						// window.alert(DaysDiff);
						// end

						var profileData = localStorageService.get('profile');
						var userid = profileData.emailId;
						$scope.postRide = {
							"vType" : vType,
							"vSeatCap" : vSeatCap,
							"fromDate" : parseFromDate,
							"toDate" : parseToDate,
							"startTime" : parseStartTime,
							"endTime" : parseEndTime,
							"userid" : userid
						}

						carpoolRegistrationService
								.rideAddToGridFn($scope.postRide)
								.then(
										function(response) {

											if (response.errorCode === 500) {
												$scope.message = response.errorMessage
											} else {
												// localStorageService.set('profile',
												// response);
												$scope.names = response.records;
												// $state.go("carpoolRegistration");

												var vehicleList = [ {
													"parentId" : "1",
													"isParent" : "true",
													"isChild" : "false",
													"vType" : "2",
													"vSeatCap" : "1",
													"fromDate" : "15/11/2017",
													"toDate" : "16/11/2017",
													"startTime" : "08:30AM",
													"endTime" : "07:00PM"
												}];

												var skipStep = true;
												var sortedVehicle = {};
												for (var i = 0; i < vehicleList.length; i++) {
													if (!sortedVehicle[vehicleList[i].parentId]) {
														sortedVehicle[vehicleList[i].parentId] = [];
														sortedVehicle[vehicleList[i].parentId]
																.push(vehicleList[i]);
														skipStep = false;
													}
													if (skipStep) {
														sortedVehicle[vehicleList[i].parentId]
																.push(vehicleList[i]);
													}
													skipStep = true;
												}

												console.log('sortedImages: ',
														sortedVehicle);
												$scope.foo = sortedVehicle;

											}
										}, function(response) {
											// console
											window.alert(response)
										});

					}

					// Vehicle type selection starts
					$scope.fnVehicleType = function() {
						$scope.seat = {
							value : 1
						};
					}
					// end

					$scope.userselected = [];

					$scope.display = function(childId) {
						$("." + childId).toggle();
					}
				});

carpoolRegApp
		.controller(
				'postARideCtrl',
				function($scope, $state, localStorageService,
						carpoolRegistrationService, carpoolService, $rootScope, $filter, check) {

					$scope.data = [ {
						id : '0',
						name : 'Select Vehicle Type'
					}, {
						id : '2',
						name : 'Two Wheeler'
					}, {
						id : '4',
						name : 'Four Wheeler'
					} ];

					$scope.fromDate = {
						value : new Date(),
						currentDate : new Date()
					};
					$scope.toDate = {
						value : new Date(),
						currentDate : new Date()
					};

					$scope.startTime = {
						value : new Date(2015, 10, 10, 09, 00, 0)
					};
					$scope.endTime = {
						value : new Date(2015, 10, 10, 19, 00, 0)
					};

					$scope.fnVehicleType = function() {
						$scope.seat = {
							value : 1
						};
					}
					
					$scope.lat = undefined;
					$scope.lng = undefined;
					$scope.selectedLocation = undefined;
					$scope.shareARideAutocomplete = undefined;

					$scope
							.$on(
									'gmPlacesAutocomplete::placeChanged',
									function() {
										$scope.selectedLocation = $scope.shareARideAutocomplete
												.getPlace().name;
										var location = $scope.shareARideAutocomplete
												.getPlace().geometry.location;
										$scope.lat = location.lat();
										$scope.lng = location.lng();
										$scope.$apply();
									});					
					

					$scope.names = [ 'Two-Wheller', 'Four-Wheller' ];
					$scope.seats = function(vType) {
						check.checkVehicle(vType);
					}
					
					$scope.getAvailablePools = function(){
	                	 console.log(" into getAvailablePools ");
	                	 carpoolService.getLoggedInUserCarpools(localStorageService.get('profile').emailId)
			              .then(function(response){
					            	  if (response.errorCode === 500) {
										//$scope.message = response.errorMessage
					            		  console.log(response);
								  }else{
									    console.log(response);
									    console.log(response.length+ " : response.lengths ");
									    if(response.length > 0){
						                	 console.log(" if condition for length ");
										  $scope.loadTableGrid(response);
									    }else{
									      console.log("No Carpools Available");
									    }
								  }
			              },function(response){
			            	         console.log(response);
			              });
                    }
					
					//Loading TableGrid of Carpools on page load
					   $scope.getAvailablePools();
					  
					$scope.fnAddPostRide = function() {
						var vType = $scope.vehicleSelect;
						var vSeatCap = $scope.seat.value;
						var fromDate = $scope.fromDate.value;
						var toDate = $scope.toDate.value;
						var startTime = $scope.startTime.value;
						var endTime = $scope.endTime.value;
						var location=$scope.selectedLocation;
						var lat=$scope.lat;
						var lng=$scope.lng;
						
						
						var parseFromDate = $filter('date')(new Date(fromDate),
								'yyyy-MM-dd');
						var parseToDate = $filter('date')(new Date(toDate),
								'yyyy-MM-dd');
						var parseStartTime = $filter('date')(
								new Date(startTime), 'h:mm a');
						var parseEndTime = $filter('date')(new Date(endTime),
								'h:mm a');
						var profileObj = localStorageService.get('profile');
						
						console.log(JSON.stringify(profileObj))
						$scope.postRide = {

							"parentid" : 9999,
							"id" : 9999,
							"status" : 1,
							"createddate" : "2017-11-25",
							"modifieddate" : "2017-11-24",
							"vehicleType" : parseInt(vType),
							"totalNoOfSeats" : parseInt(vSeatCap),
							"fromDate" : parseFromDate,
							"toDate" : parseToDate,
							"startTime" : parseStartTime,
							"toTime" : parseEndTime,
							"emailId" : profileObj.emailId,
							"location" : location,
							"latitude" : lat,
							"longitude" : lng
						}
						
						carpoolRegistrationService
								.rideAddToGridFn($scope.postRide)
								.then(
										function(response) {

											if (response.errorCode === 500) {
												$scope.message = response.errorMessage
											} else {
												$scope.loadTableGrid(response);
											}
										}, function(response) {
											window.alert(response)
										});

					}
					
					
					                 $scope.loadTableGrid = function(response){
					                	 
					                	    console.log("response");
					                	    console.log(response);
					                	        $scope.jsonData = response;
											$scope.length = $scope.jsonData.length;
											$scope.parentIncrement = 0;
											$scope.childIncrement = 0;
											$scope.parentIdData = []; // Sorted
											// Array
											// Object
											$scope.childIdData = [];
											$scope.parentIdDetails = [];    
											
											$scope.jsonData
											.forEach(function(
													value, index) {
												value['startTime'] = $scope.timeParser(value.startTime);
												value['toTime'] = $scope.timeParser(value.toTime);

												if (value.id == value.parentid) {
													$scope.parentIdData[$scope.parentIncrement] = value;
													$scope.parentIdDetails[$scope.parentIncrement] = value.parentid;
													++$scope.parentIncrement;
												}
											});
											
											for (var i = 0; i < $scope.parentIdData.length; i++) {
												$scope.jsonData
														.forEach(function(
																value,
																index) {

															if (value.id != value.parentid
																	&& value.parentid == $scope.parentIdDetails[i]) {
																$scope.childIdData[$scope.childIncrement] = value;
																++$scope.childIncrement;
															}

														});
												$scope.parentIdData[i].child = $scope.childIdData; // adding
												// childData
												// to
												// parent
												$scope.childIncrement = 0;
												$scope.childIdData = [];
										   }
											
											// Child Elements Toggle
											for (var j = 0; j < $scope.parentIdDetails.length; j++) { // Dynamically
												// creating
												// $scope
												// variables
												var x = "show" + j;
												$scope[x] = false;
											}
											
											console.log($scope.parentIdData);
					                	 
					                 }
					                 
					                 // displaying all the Child while click on (+) button on table header
									 /*$scope.showGridData = function() {

											for (var z = 0; z < $scope.parentIdDetails.length; z++) {
												var gridToggle = "show"+ z;
												$scope[gridToggle] = !$scope[gridToggle];
											}
											if ($scope.show0) {
												$("#gridToggleButton").text("-");

												for (var y = 0; y < $scope.parentIdDetails.length; y++) {
													$("#gridButton" + y)
															.text("-");
													$("#gridButton" + y)
															.parent()
															.parent()
															.attr(
																	"style",
																	"background-color:#c4e2ed");
												}

											} else {
												$("#gridToggleButton").text("+");

												for (var y = 0; y < $scope.parentIdDetails.length; y++) {
													$("#gridButton" + y)
															.text("+");
													$("#gridButton" + y)
															.parent()
															.parent()
															.removeAttr(
																	"style");
												}
											}

									}*/
					                 
									 // Displaying Child on click of (+) button
					                 $scope.showChildData = function(x) {

											var x1 = "show" + x;
											$scope[x1] = !$scope[x1];

											if ($scope[x1]) {
												$("#gridButton" + x)
														.text("-");
												$("#gridButton" + x)
														.parent()
														.parent()
														.attr("style",
																"background-color:#c4e2ed");
											} else {
												$("#gridButton" + x)
														.text("+");
												$("#gridButton" + x)
														.parent()
														.parent()
														.removeAttr(
																"style");
											}

									}
					                 
				                    $scope.timeParser = function (date){
										var time = date.split(" ")[0];
									    var meridian = date.split(" ")[1];
									    var hrs = time.split(':')[0];
									    var mins = time.split(':')[1];
									    if(meridian == 'PM' && hrs != "12"){
									       hrs = 12 + parseInt(hrs);
									    }
									      return new Date(2015, 10, 10, hrs, mins, 0);
									}
					                 
									$scope.confirmEdit = function(name, item) {
										item['startTime'] = $filter('date')(new Date(item.startTime), 'h:mm a');
										item['toTime'] = $filter('date')(new Date(item.toTime), 'h:mm a');
										$scope.editteditem = item;
										$('#editModal').modal('show');
									}
									
									$scope.editItem = function() {
										   carpoolService.updateCarpoolDetails($scope.editteditem)
										               .then(function(response){
										            	       if(response.errorCode){
										            	    	       $scope.errorMessage = response.errorMessage;
										            	       }else{
										            	    	       $scope.successMessage = response.message;
										            	    	       $scope.errorMessage = '';
										            	    	       $scope.getAvailablePools();
										            	       }
										            	       $('#editStatus').modal('show');
										               },function(response){
										            	        console.log(response);
										            	        window.alert(response.errorMessage);
										               });
										$('#editModal').modal('hide');
								   }
									$scope.confirmDelete = function(name, item) {
										item['startTime'] = $filter('date')(new Date(item.startTime), 'h:mm a');
										item['toTime'] = $filter('date')(new Date(item.toTime), 'h:mm a');
										$scope.deleteitem = item;
										$('#deleteModal').modal('show');
									}
									$scope.deleteItem = function() {
										   carpoolService.cancelCarpoolDetails($scope.deleteitem)
										               .then(function(response){
										            	       if(response.errorCode){
										            	    	       $scope.errorMessage = response.errorMessage;
										            	       }else{
										            	    	       $scope.successMessage = response.message;
										            	    	       $scope.errorMessage = '';
										            	    	       $scope.getAvailablePools();
										            	       }
										            	       $('#editStatus').modal('show');
										               },function(response){
										            	        console.log(response);
										            	        window.alert(response.errorMessage);
										               });
										$('#deleteModal').modal('hide');
								   }		
										
				});

carpoolRegApp.factory('check', function() {
	return {
		checkVehicle : function(text) {
			if (text == 'Two-Wheller') {
				document.getElementById("two").value = 1;
				document.getElementById("two").setAttribute("readonly",
						"readonly");
			} else if (text == 'Four-Wheller') {
				document.getElementById("two").value = 1;
				document.getElementById("two").removeAttribute("readonly");
			}
		}
	};
});
 