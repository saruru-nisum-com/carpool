myTodayRidesApp.controller('myTodayRidesController',function($scope,myTodayRidesService,commonService,localStorageService){
	
	$scope.todayRidesList=[];
	$scope.emailId=commonService.emailId;
	$scope.userType=localStorageService.get('userStatus');
	$scope.myTodayRides=function() {
		myTodayRidesService.getMyTodayRides($scope.emailId,$scope.userType).then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				$scope.todayRidesList=response;
			}
		}, function(response) {
			console.log(response);
		})
	}
	$scope.myTodayRides();
});