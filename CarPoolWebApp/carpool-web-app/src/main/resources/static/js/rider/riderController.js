/**
 * @Author: Dhiraj singh
 * description: riderController for riderApp.
 * date created: 11th nov. 20117
 * date modified: 29th nov. 20117
 */
riderApp.controller('riderController', 
		function($scope, $state, localStorageService, riderService, $rootScope, $filter) {
	

	$scope.showError=false;
	$scope.lat = undefined;
	$scope.lng = undefined;
	$scope.selectedLocation = undefined;
	$scope.riderAutocomplete = undefined;
	$scope.isRegisteredAsRider = false;
	
	$scope.$on('gmPlacesAutocomplete::placeChanged', function(){
		$scope.selectedLocation = $scope.riderAutocomplete.getPlace().name;
		var location = $scope.riderAutocomplete.getPlace().geometry.location;
		$scope.lat = location.lat();
		$scope.lng = location.lng();
		$scope.$apply();
	});
	
	/*
	 * @author dhiraj1125
	 * Description: Method to get the Rider details and check if he is already registered 
	 * as a rider.
	 * @param userId
	 */
	$scope.getRegisteredRiderData = function() {
		var profileSessionData = localStorageService.get('profile');
		$scope.userId = profileSessionData.emailId;
		riderService.getRegisterRiderData($scope.userId).then (function(response){
			angular.forEach(response, function(value, key) {
				if(value.isRider == 1) {//If isRider value is '0' then he is registered as Rider.
					$scope.isRegisteredAsRider = true;
					$scope.riderAutocomplete = value.location;
					$scope.riderNearBy = value.nearby;
				}
			});
		});
	}
	

	/*
	 * On load method call to get the registered rider data. 
	 */
	$scope.getRegisteredRiderData();
	
	$scope.registerAsRider = function() {

		alert("registerAsRider is clicked");

		var registrationId = Math.floor(Math.random() * 100000000) + 1 ;

		var profileSessionData = localStorageService.get('profile');

		var userId = profileSessionData.emailId;
		//alert("hi user id : "+userId);
		$scope.userId = userId
		var location = $scope.selectedLocation;
		var latitude = $scope.lat;
		var longitude = $scope.lng;
		var nearby = $scope.riderNearBy;
		var mobile = "9000000000";//static for now, need to change

		if($scope.notifyEmail == true){
			var emailNotification = true;
		}else{
			var emailNotification = false;
		}

		var isRider = 1;//driver==>0 || rider==>1
		var createdDate = $filter('date')(new Date(), 'yyyy-MM-dd');
		var modifiedDate = $filter('date')(new Date(), 'yyyy-MM-dd');
		//window.alert(parseFromDate + " " + parseEndTime);

		$scope.registerRiderJson = {
				"registrationId" : registrationId,
				"userId" : userId,
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
		riderService.registerAsRider($scope.registerRiderJson).then(function(response) {
			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			}else {
				$scope.isRegisteredAsRider= true;
				alert('rider registered successfully.');
			}
		});

	}

	$scope.updateAsRider = function() {
		alert("update is clicked");
	}

});