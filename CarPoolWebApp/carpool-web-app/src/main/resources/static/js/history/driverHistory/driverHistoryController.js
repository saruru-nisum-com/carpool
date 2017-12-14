/**
 * @author vkallada
 * Controller file for driver history view.
 */
"use strict";

console.log("Driver history controller initiated");

driverHistoryMod.controller("DriverHistoryCtrl", function($scope,DriverHistoryService,localStorageService) {
	
	console.log("Driver history controller initiated");
	$scope.searchResults = [];
	$scope.currentDate = new Date();
	$scope.driverPoolHistoryData = [];
	
	$scope.$on('gmPlacesAutocomplete::placeChanged', function(){
		if($scope.location != undefined) {
			$scope.selectedLocation = $scope.location.getPlace().name;
			var actualLocation = $scope.location.getPlace().geometry.location;
			$scope.$apply();
		}
	});
	
	/**
	 * @author vkallada
	 * Method to search the driver's history based on the input search criteria provided.
	 */
	$scope.searchHistory = function() {
		 var methodName = "[DriverHistoryMod :: DriverHistoryCtrl :: searchHistory()]";
		console.log(methodName+" Started");
		
		$scope.driverPoolHistoryData = [];
		//Get user email from local storage
		var profileSessionData = localStorageService.get('profile');
		$scope.userId = profileSessionData.emailId;
	
		console.log("location :: "+$scope.selectedLocation);
		
		console.log("emailId :: "+$scope.userId)
		
		var data = {
				"emailId" : $scope.userId,
				"fromDate" : $scope.fromDate,
				"toDate" : $scope.toDate,
				"status" : $scope.status,
				"location" : $scope.selectedLocation
		}
		
		DriverHistoryService.searchDriverHistory(data).then(function(successResponse) {
			$scope.driverPoolHistoryData = successResponse;
			if($scope.driverPoolHistoryData.length == 0)
				console.log("No History found.");
			console.log("The driver history response data : "+successResponse);
		}, function(errorResponse) {
			console.log("Error occured fetching the driver history.");
			console.log("The driver history error response : "+errorResponse);
		});
		
		console.log(methodName+" End");
	}
	
	/**
	 * @author vkallada
	 * Method to fetch the complete details of the ride
	 */
	$scope.getDetails = function() {
		var methodName = "[DriverHistoryMod :: DriverHistoryCtrl :: getDetails]";
		console.log(methodName+" Started");
		
		console.log(methodName+" End");
		
	}
	

});