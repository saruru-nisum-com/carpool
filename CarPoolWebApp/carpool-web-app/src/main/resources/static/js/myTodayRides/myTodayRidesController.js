myTodayRidesApp.controller('myTodayRidesController',function($scope,myTodayRidesService,commonService,localStorageService){
	
	$scope.todayRidesList=[];
	$scope.emailId=commonService.emailId;
	$scope.userType=localStorageService.get('userStatus');
	$scope.myTodayRides=function() {
		if ($scope.userType != null
				&& $scope.userType.length > 0) {
			for (var i = 0; i < $scope.userType.length; i++) {
				if ($scope.userType[i] == "R") {
					$scope.isRider = true;
				} else if($scope.userType[i] == "D") {
					$scope.isDriver=true;
				}
			}
		}
		if($scope.isRider && $scope.isDriver) {
			$scope.userType="B";
		} 
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