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
	
	
	
	$scope.registerAsDriver = function() {

		alert('inside...');
		console.log('checkbox values '+$scope.cb2wheel +'and '+$scope.cb4wheel);
		
		if ($scope.cb2wheel==false && $scope.cb4wheel==false) {
			alert('Error in cb occured.');
		}else {
			//alert("register is clicked");
			var registrationId = 1;//static for now, need to change
			var userId = "test@tes.com";//static for now, need to change
			var vehicleType = [1,2];//static for now, need to change
			var location = "gachibowli";//static for now, need to change
			var latitude = "24";//static for now, need to change
			var longitude = "36";//static for now, need to change
			var nearby = "near yellamma temple";//static for now, need to change
			var mobile = "9000000000";//static for now, need to change
			var emailNotification = true;//static for now, need to change
			var isRider = 1;//static for now, need to change
			var createdDate = "20171115074306232";//static for now, need to change
			var modifiedDate = "20171115074306232";//static for now, need to change
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
			window.alert(JSON.stringify($scope.registerDriverJson));
			carpoolRegistrationService.registerAsDriver($scope.registerDriverJson).then(function(response) {
				window.alert("dhiraj");
				if (response.errorCode === 500) {
					$scope.message = response.errorMessage
				}else {
					window.alert('singh from server.............. :)'+JSON.stringify(response))
					//$scope.names = response.records;
				}
			}, function(response) {
				// console
				window.alert("dks-- "+response)
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
		window.alert(parseFromDate + " " + parseEndTime);

		// Start date and End date difference validation starts
//		var d1 = new Date(parseFromDate);
//		var d2 = new Date(parseToDate);
//		var timeDiff = d2.getTime() - d1.getTime();
//		var DaysDiff = timeDiff / (1000 * 3600 * 24);
//		window.alert(DaysDiff);
		// end

		$scope.postRide = {
				"vType" : vType,
				"vSeatCap" : vSeatCap,
				"fromDate" : parseFromDate,
				"toDate" : parseToDate,
				"startTime" : parseStartTime,
				"endTime" : parseEndTime
		}

		window.alert(JSON.stringify($scope.postRide))
		carpoolRegistrationService.rideAddToGridFn($scope.postRide).then(function(response) {

			window.alert(123)
			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			} else {
//				localStorageService.set('profile', response);
				window.alert('Hold on.............. :)'+JSON.stringify(response))
				$scope.names = response.records;
//				$state.go("carpoolRegistration");
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

});