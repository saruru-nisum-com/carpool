mySharedRidesApp.controller('mySharedRidesController', function($scope,commonService,mySharedRidesService,$state) {
	
	$scope.userEmail = commonService.emailId;
	$scope.myRidesList=[];
	$scope.poolsList=[];
	$scope.riderBookingList=[];
	$scope.emailId = commonService.emailId;
	$scope.reasons={};
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
	$scope.confirmCancel = function(myRide) {
		$scope.rideData=myRide;
		$('#deleteModal').modal('show');
	}
	
	$scope.cancelRide=function() {
		mySharedRidesService.cancelRide($scope.rideData).then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				alert('Ride cancelled Successfully')
				$scope.message=response.message;
				$scope.getMyRides();
			}
		}, function(response) {
			console.log(response);
		})
		$('#deleteModal').modal('hide');
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
	
	$scope.go = function ( path ) {
		$state.go( path );
		}
	$scope.getMyRides();
});