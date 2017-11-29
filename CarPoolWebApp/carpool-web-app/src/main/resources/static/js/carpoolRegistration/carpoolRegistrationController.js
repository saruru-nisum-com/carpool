carpoolRegApp.controller('carpoolRegistrationController', function($scope,
		$state, localStorageService, carpoolRegistrationService, $rootScope,
		$filter) {
	// $scope, $timeout, categoryService, userService,
	// roleService, localStorageService, CategoryListModel,
	// RoleListModel, UserListModel,commonService,$state
	
	//driver/rider screen code starts here
	
	$scope.showError=false;
	$scope.cb2wheel=false;
	$scope.cb4wheel=false;
	$scope.lat = undefined;
    $scope.lng = undefined;
	$scope.selectedLocation = undefined;
	$scope.$on('gmPlacesAutocomplete::placeChanged', function(){
		
		console.log('carpool method ')
		$scope.selectedLocation = $scope.autocomplete.getPlace().name;
	      var location = $scope.autocomplete.getPlace().geometry.location;
	      $scope.lat = location.lat();
	      $scope.lng = location.lng();
	      $scope.$apply();
	  });
	
	$scope.registerAsDriver = function() {

		console.log('checkbox values '+$scope.cb2wheel +'and '+$scope.cb4wheel);
		
		if ($scope.cb2wheel==false && $scope.cb4wheel==false) {
			alert('Please select the vehicle type.');
		}else {
			var registrationId = Math.floor(Math.random() * 100000000) + 1 ;//static for now, need to change

			var profileSessionData = localStorageService.get('profile');
			
			var userId = profileSessionData.emailId;//"dsingh@nisum.com"//profileSessionData.emailId;
			//alert("hi user id : "+userId);
			$scope.userId = userId
			if($scope.cb2wheel==2 && $scope.cb4wheel==4){
				var vehicleType = [$scope.cb2wheel, $scope.cb4wheel];
			}else if($scope.cb2wheel==2 && $scope.cb4wheel==0){
				var vehicleType = [$scope.cb2wheel];
				
			}else if($scope.cb4wheel==4 && $scope.cb2wheel==0){
				var vehicleType = [$scope.cb4wheel];
				
			}
			var location = $scope.selectedLocation;
			var latitude = $scope.lat;//static for now, need to change
			var longitude = $scope.lng;//static for now, need to change
			var nearby = $scope.nearBy;
			var mobile = "9000000000";//static for now, need to change
			var emailNotification = true;//static for now, need to change
			var isRider = 1;//static for now, need to change
			var createdDate = $filter('date')(new Date(), 'yyyy-MM-dd');
			var modifiedDate = $filter('date')(new Date(), 'yyyy-MM-dd');
			//window.alert(parseFromDate + " " + parseEndTime);
			
			$scope.registerDriverJson = {
					"registrationId" : registrationId,
				    "userId" : userId,
				    "vehicleType" :  vehicleType,
				    "location" :location,
				    "latitude" : latitude,
				    "longitude" : longitude,
				    "nearby" : nearby,
				    "mobile" : mobile,
				    "emailNotification" : emailNotification,
				    "isRider" : isRider,
				    "createdDate" : createdDate,
				    "modifiedDate": modifiedDate
			}
			//window.alert("what am i sending to the server::: "+JSON.stringify($scope.registerDriverJson));
			carpoolRegistrationService.registerAsDriver($scope.registerDriverJson).then(function(response) {
				if (response.errorCode === 500) {
					$scope.message = response.errorMessage
				}else {

					
					$scope.isRegisteredAsDriver= true;
		            alert('driver registered successfully.');
					//$scope.names = response.records;
		            //alert("***** "+$scope.userId);
		            carpoolRegistrationService.getRegisterDriverData($scope.userId).then(function(response) {
		            	if (response.errorCode === 500) {
							$scope.message = response.errorMessage
						}else {
							//alert('harish service called successfully.');
							window.alert('singh from server.............. :)'+JSON.stringify(response));
						}
		            }, function(response) {
						// console
						//window.alert("dks-- "+response)
					});
				}
			}, function(response) {
				// console
				//window.alert("dks-- "+response)
			});
			var onSuccess = function (data, status, headers, config) {
				$scope.isRegisteredAsDriver= true;
	            alert('driver registered successfully.');
	        };
	        
	        var onError = function (data, status, headers, config) {
	            alert('Error occured.');
	        };
	        
//	        $http.post('/student/submitData', { student:$scope.student })
//	        .success(onSuccess)
//	        .error(onError);

		}
		
	}
	
	$scope.updateAsDriver = function() {
		alert("update is clicked");
	}

	$scope.registerAsRider = function() {
		//alert("update is clicked");
		console.log('register is clicked');
	}
	
	$scope.updateAsRider = function() {
		alert("update is clicked");
	}
	
	//driver/rider screen code ends here

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

	//Vehicle type selection starts
	$scope.fnVehicleType=function() {
		$scope.seat = {
				value: 1
		};
	}
	//end

	$scope.fnAddPostRide = function() {
		var vType = $scope.vehicleSelect;
		var vSeatCap = $scope.seat.value;
		var fromDate = $scope.fromDate.value;
		var toDate = $scope.toDate.value;
		var startTime = $scope.startTime.value;
		var endTime = $scope.endTime.value;
		var parseFromDate = $filter('date')(new Date(fromDate), 'dd/MM/yyyy');
		var parseToDate = $filter('date')(new Date(toDate), 'dd/MM/yyyy');
		var parseStartTime = $filter('date')(new Date(startTime), 'h:mm a');
		var parseEndTime = $filter('date')(new Date(endTime), 'h:mm a');

		// Start date and End date difference validation starts
//		var d1 = new Date(parseFromDate);
//		var d2 = new Date(parseToDate);
//		var timeDiff = d2.getTime() - d1.getTime();
//		var DaysDiff = timeDiff / (1000 * 3600 * 24);
//		window.alert(DaysDiff);
		// end

		
		var profileData = localStorageService.get('profile');
		var userid= profileData.emailId;
		$scope.postRide = {
				"vType" : vType,
				"vSeatCap" : vSeatCap,
				"fromDate" : parseFromDate,
				"toDate" : parseToDate,
				"startTime" : parseStartTime,
				"endTime" : parseEndTime,
				"userid" : userid
		}

		carpoolRegistrationService.rideAddToGridFn($scope.postRide).then(function(response) {

			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			} else {
//				localStorageService.set('profile', response);
				$scope.names = response.records;
//				$state.go("carpoolRegistration");
				
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
			    }, {
			        "childId" : "1",
			        "parentId" : "1",
			        "isParent" : "false",
			        "isChild" : "true",
			        "vType" : "2",
			        "vSeatCap" : "1",
			        "fromDate" : "15/11/2017",
			        "toDate" : "15/11/2017",
			        "startTime" : "08:30AM",
			        "endTime" : "07:00PM"
			    }, {
			        "childId" : "1",
			        "parentId" : "1",
			        "isParent" : "false",
			        "isChild" : "true",
			        "vType" : "2",
			        "vSeatCap" : "1",
			        "fromDate" : "16/11/2017",
			        "toDate" : "16/11/2017",
			        "startTime" : "08:30AM",
			        "endTime" : "07:00PM"
			    }, {
			        "parentId" : "2",
			        "isParent" : "true",
			        "isChild" : "false",
			        "vType" : "4",
			        "vSeatCap" : "3",
			        "fromDate" : "17/11/2017",
			        "toDate" : "18/11/2017",
			        "startTime" : "08:30AM",
			        "endTime" : "07:00PM"
			    }, {
			        "childId" : "2",
			        "parentId" : "2",
			        "isParent" : "false",
			        "isChild" : "true",
			        "vType" : "4",
			        "vSeatCap" : "3",
			        "fromDate" : "17/11/2017",
			        "toDate" : "17/11/2017",
			        "startTime" : "08:30AM",
			        "endTime" : "07:00PM"
			    }, {
			        "childId" : "2",
			        "parentId" : "2",
			        "isParent" : "false",
			        "isChild" : "true",
			        "vType" : "4",
			        "vSeatCap" : "3",
			        "fromDate" : "18/11/2017",
			        "toDate" : "18/11/2017",
			        "startTime" : "08:30AM",
			        "endTime" : "07:00PM"
			    } ];
				
				
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

			    console.log('sortedImages: ', sortedVehicle);
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
	
	$scope.display = function(childId){
        $("."+childId).toggle();
    }
	
	

});