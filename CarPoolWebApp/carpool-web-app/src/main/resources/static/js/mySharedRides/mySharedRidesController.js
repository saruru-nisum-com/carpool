mySharedRidesApp.controller('mySharedRidesController', function($scope,commonService,mySharedRidesService) {
	
	$scope.userEmail = commonService.emailId;
	$scope.myRidesList=[];
	
	$scope.getMyRides = function() {
		mySharedRidesService.getMyRides($scope.userEmail).then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				$scope.myRidesList=response;
			}
		}, function(response) {
			console.log(response);
		})
}
	
});