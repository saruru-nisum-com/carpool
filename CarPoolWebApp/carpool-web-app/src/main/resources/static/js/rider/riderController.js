/**
 * @Author: Dhiraj singh 
 * description: riderController for riderApp. 
 * date created: 11th nov. 20117 date modified: 29th nov. 20117
 */
riderApp.controller('riderController',function($scope, $state, localStorageService, riderService,$rootScope, $filter) {
	$scope.showError = false;
	$scope.lat = undefined;
	$scope.lng = undefined;
	$scope.selectedLocation = undefined;
	$scope.riderAutocomplete = undefined;
	$scope.isRegisteredAsRider = false;	
	$scope.emailNotification = false;
	$scope.isVisible = false;
	$scope.disableGender = false;
	$scope.silentlyModifiedDateRider = $filter('date')(new Date(), 'yyyy-MM-dd');
	
	$scope.$on('gmPlacesAutocomplete::placeChanged',function() {
		if ($scope.riderAutocomplete != undefined) {
			$scope.selectedLocation = $scope.riderAutocomplete.getPlace().name;
			var location = $scope.riderAutocomplete.getPlace().geometry.location;
			$scope.lat = location.lat();
			$scope.lng = location.lng();
			$scope.$apply();
		}
	});

	/*
	 * @author dhiraj1125 Description: Method to get the Rider
	 * details and check if he is already registered as a rider.
	 * @param userId
	 */
	$scope.getRegisteredRiderData = function() {
		var profileSessionData = localStorageService.get('profile');
		$scope.userId = profileSessionData.emailId;
		riderService.getRegisterRiderData($scope.userId).then(function(response) {
			var responseData = response;
			if (responseData.length > 0) {
				$scope.mobile = responseData[0].mobile;
				$scope.gender = responseData[0].gender;
				$scope.riderAutocomplete = responseData[0].location;
				$scope.selectedLocation = responseData[0].location;
				$scope.lat = responseData[0].latitude;
				$scope.lng = responseData[0].longitude;
				$scope.disableGender = true;
			}
			angular.forEach(response,function(value, key) {
				if (value.isRider == 1) {// If isRider value is '0' then he is registered as Rider.
					$scope.isRegisteredAsRider = true;
					$scope.riderAutocomplete = value.location;
					$scope.riderNearBy = value.nearby;
					$scope.notifyEmail = value.emailNotification;
					// $scope.mobile = value.mobile;
					// $scope.gender  =  value.gender;
				}
			});
		});
	}

	/*
	 * On load method call to get the registered rider data.
	 */
	$scope.getRegisteredRiderData();

	$scope.registerAsRider = function() {
		var registrationId = Math.floor(Math.random() * 100000000) + 1;

		var profileSessionData = localStorageService.get('profile');

		var userId = profileSessionData.emailId;
		// alert("hi user id : "+userId);
		$scope.userId = userId
		var location = $scope.selectedLocation;
		var latitude = $scope.lat;
		var longitude = $scope.lng;
		var nearby = $scope.riderNearBy;
		var mobile = $scope.mobile;
		var gender = $scope.gender;
		var emailNotification = $scope.notifyEmail;

		var isRider = 1;// driver==>0 || rider==>1
		var createdDate = $filter('date')(new Date(),'yyyy-MM-dd');
		var modifiedDate = $filter('date')(new Date(),'yyyy-MM-dd');

		$scope.registerRiderJson = {
				"registrationId" : registrationId,
				"emailId" : userId,
				"location" : location,
				"latitude" : latitude,
				"longitude" : longitude,
				"nearby" : nearby,
				"mobile" : mobile,
				"emailNotification" : emailNotification,
				"isRider" : isRider,
				"createdDate" : createdDate,
				"modifiedDate" : modifiedDate,
				"gender" : gender
		}
		riderService.registerAsRider($scope.registerRiderJson).then(function(response) {
			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			} else {
				$scope.isRegisteredAsRider = true;
				$scope.isVisible = true;
				$scope.actionName = "Registered";
				//Author:Vasu
				//Start: For leftside menu validations(as a Rider register)
				localStorageService.set('riderLocation',location);
				var userStatus = localStorageService.get('userStatus');
				if (userStatus !== null) {
					if (userStatus.indexOf("R") < 0) {
						userStatus.push("R");
					}
				}else{
					userStatus=[];
					userStatus.push("R");
				}
				localStorageService.set( "userStatus", userStatus);
				$scope.$broadcast( "checkStatus",userStatus);
				//End: For leftside menu validations(as a Rider register)
			}
		});

	}

	/*
	 * @author Dhiraj Singh Method to update the Rider data.
	 */
	$scope.updateAsRider = function() {
		console.log("Update rider data method called.");

		var profileSessionData = localStorageService.get('profile');
		var userId = profileSessionData.emailId;
		$scope.userId = userId
		var emailNotification = $scope.notifyEmail;
		var modifiedDate = $filter('date')(new Date(),'yyyy-MM-dd');

		var data = {
				"emailId" : userId,
				"location" : $scope.selectedLocation,
				"latitude" : $scope.lat,
				"longitude" : $scope.lng,
				"nearby" : $scope.riderNearBy,
				"isRider" : 1,
				"emailNotification" : emailNotification,
				"modifiedDate" : modifiedDate,
				"mobile" : $scope.mobile,
				"gender" : $scope.gender
		}
		riderService.updateRiderData(data).then(function(successResponse) {
			console.log("Rider data updated successfuly"+ successResponse);
			$scope.isVisible = true;
			$scope.actionName = "Updated";
		},function(errorResponse) {
			console.log("Failed to update the rider data."+ errorResponse);
		});
		var onSuccess = function(data, status, headers, config) {
			alert('Rider data updated successfully.');
		};

		var onError = function(data, status, headers, config) {
			alert('Error occured while updating the Rider data.');
		};
	}

	$scope.resetMsgVisibility = function() {
		$scope.isVisible = false;
	}

});
