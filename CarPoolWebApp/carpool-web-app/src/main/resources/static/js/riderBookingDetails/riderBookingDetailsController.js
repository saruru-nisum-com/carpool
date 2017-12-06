riderDetailsApp.controller('riderBookingDetailsController',
						function($scope,localStorageService,riderBookingDetailsService,commonService) {
	$scope.riderBookingList=[];
	$scope.emailId = commonService.emailId;
	$scope.reasons={};
	console.log('in riderBookingDetailsController');
	
	$scope.getRiderBookingDetails = function() {
		riderBookingDetailsService.rider("radha@gmail.com").then(function(response) {
				if (response.errorCode) {
					$scope.message = response.errorMessage
				} else {
					
					//console.log(riderBookingList);
					$scope.riderBookingList=response;
				}
			}, function(response) {
				console.log(response);
			})
	}

	$scope.getRiderBookingDetails();
	
	
	$scope.getReasons = function() {
		riderBookingDetailsService.getReason().then(function(response) {
				if (response.errorCode) {
					$scope.message = response.errorMessage
				} else {
					
					console.log(response);
					$scope.reasons=response;
					//console.log(reasons);
				}
			}, function(response) {
				console.log(response);
			})
	}
	
	$scope.getReasons();
	
});