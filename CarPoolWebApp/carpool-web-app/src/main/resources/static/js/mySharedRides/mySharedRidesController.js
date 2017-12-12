mySharedRidesApp.controller('mySharedRidesController', function($scope,commonService,mySharedRidesService,$state) {
	
	$scope.userEmail = commonService.emailId;
	$scope.myRidesList=[];
	$scope.poolsList=[];
	
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
	
	$scope.cancelRide=function(poolData) {
		mySharedRidesService.cancelRide(poolData).then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				alert('Deleted successfully');
				$scope.message=response.message;
				$scope.getMyRides();
			}
		}, function(response) {
			console.log(response);
		})
	}
	
	$scope.getRidesByParentId=function(parentId) {
		mySharedRidesService.getRidesByParentId(parentId).then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				$scope.poolsList=response;
			}
		}, function(response) {
			console.log(response);
		})
	}
	$scope.getMyRides();
});