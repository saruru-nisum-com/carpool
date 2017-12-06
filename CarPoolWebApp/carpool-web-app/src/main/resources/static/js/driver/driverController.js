/**
 * @Author: Dhiraj singh
 * description: driverController for driverApp.
 * date created: 11th nov. 20117
 * date modified: 29th nov. 20117
 */
driverApp.controller('driverController', 
		function($scope, $state, localStorageService, driverService, $rootScope, $filter) {

	$scope.showError=false;
	$scope.cb2wheel=false;
	$scope.cb4wheel=false;
	$scope.lat = undefined;
	$scope.lng = undefined;
	$scope.selectedLocation = undefined;
	$scope.autocomplete = undefined;
	$scope.isRegisteredAsDriver = false;
	$scope.isVisible=false;
	$scope.disableGender = false;

	$scope.$on('gmPlacesAutocomplete::placeChanged', function(){
		if($scope.autocomplete != undefined) {
			$scope.selectedLocation = $scope.autocomplete.getPlace().name;
			var location = $scope.autocomplete.getPlace().geometry.location;
			$scope.lat = location.lat();
			$scope.lng = location.lng();
			$scope.$apply();
		}
	});

	/*
	 * @author dhiraj1125
	 * Method to get the Driver/Rider details and check if he is already registered 
	 * as a driver or the rider.
	 * @param userId
	 */
	$scope.getRegisteredDriverData = function() {
		var profileSessionData = localStorageService.get('profile');
		$scope.userId = profileSessionData.emailId;
		driverService.getRegisterDriverData($scope.userId).then (function(response){
			var responseData = response;
			if(responseData.length > 0) {
				$scope.mobile = responseData[0].mobile;
				$scope.gender = responseData[0].gender;
				$scope.disableGender = true;
			}
			angular.forEach(responseData, function(value, key) {
				if(value.isRider == 0) {//If isRider value is '0' then he is registered as Driver.
					$scope.isRegisteredAsDriver = true;
					$scope.autocomplete = value.location;
					$scope.nearBy = value.nearby;
					angular.forEach(value.vehicleType, function(value) {
						if(value == 2) 
							$scope.cb2wheel = 2;
						else
							$scope.cb4wheel = 4;
					})
				} 
			});
		});
	}

	/*
	 * On load method call to get the registered user data. 
	 */
	$scope.getRegisteredDriverData();

	$scope.registerAsDriver = function() {
		console.log('checkbox values '+$scope.cb2wheel +'and '+$scope.cb4wheel);
		if ($scope.cb2wheel==false && $scope.cb4wheel==false) {
			alert('Please select the vehicle type.');
		}else {
			var registrationId = Math.floor(Math.random() * 100000000) + 1 ;//static for now, need to change

			var profileSessionData = localStorageService.get('profile');

			var userId = profileSessionData.emailId;// "dsingh@nisum.com"//profileSessionData.emailId;
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
			var latitude = $scope.lat;
			var longitude = $scope.lng;
			var nearby = $scope.nearBy;
			var mobile = $scope.mobile;//static for now, need to change
			var emailNotification = true;//static for now, need to change
			var isRider = 0;//driver==>0 || rider==>1
			var createdDate = $filter('date')(new Date(), 'yyyy-MM-dd');
			var modifiedDate = $filter('date')(new Date(), 'yyyy-MM-dd');
			var gender = $scope.gender;
			
			$scope.registerDriverJson = {
					"registrationId" : registrationId,
					"emailId" : userId,
					"vehicleType" :  vehicleType,
					"location" :location,
					"latitude" : latitude,
					"longitude" : longitude,
					"nearby" : nearby,
					"mobile" : mobile,
					"isRider" : isRider,
					"createdDate" : createdDate,
					"modifiedDate": modifiedDate,
					"gender" : gender
			}
			//window.alert("what am i sending to the server::: "+JSON.stringify($scope.registerDriverJson));
			driverService.registerAsDriver($scope.registerDriverJson).then(function(response) {
				if (response.errorCode === 500) {
					$scope.message = response.errorMessage
				}else {
					$scope.isRegisteredAsDriver= true;
					$scope.isVisible=true;
					$scope.actionName="Registered";
					
					driverService.getRegisterDriverData($scope.userId).then(function(response) {
						if (response.errorCode === 500) {
							$scope.message = response.errorMessage
						}else {
							console.log("response from getRegisterDriverData from server...."+JSON.stringify(response));
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
			};

			var onError = function (data, status, headers, config) {
				alert('Error occured.');
			};

//			$http.post('/student/submitData', { student:$scope.student })
//			.success(onSuccess)
//			.error(onError);

		}

	}

	/*
	 * @author dhiraj1125
	 * Method to update the Driver data.
	 */
	$scope.updateAsDriver = function() {
		console.log("Update driver data method called.");

		var profileSessionData = localStorageService.get('profile');
		var userId = profileSessionData.emailId;
		$scope.userId = userId
		var vehicleType = [];
		if($scope.cb2wheel==2 && $scope.cb4wheel==4){
			vehicleType = [$scope.cb2wheel, $scope.cb4wheel];
		}else if($scope.cb2wheel==2 && $scope.cb4wheel==0){
			vehicleType = [$scope.cb2wheel];
		}else if($scope.cb4wheel==4 && $scope.cb2wheel==0){
			vehicleType = [$scope.cb4wheel];
		}

		var data = {
				"emailId" : userId,
				"location" : $scope.selectedLocation,
				"nearby" : $scope.nearBy,
				"vehicleType" : vehicleType,
				"isRider" : 0,
				"mobile" : $scope.mobile,
				"gender" : $scope.gender
		}
		driverService.updateDriverData(data).then(function(successResponse) {
			console.log("Driver data updated successfuly"+successResponse);
			$scope.isVisible=true;
			$scope.actionName="Updated";
		}, function(errorResponse) {
			console.log("Failed to update the driver data."+errorResponse);
		});

		var onSuccess = function (data, status, headers, config) {
			alert('Driver data updated successfully.');
		};

		var onError = function (data, status, headers, config) {
			alert('Error occured while updating the driver data.');
		};
	}

	
});