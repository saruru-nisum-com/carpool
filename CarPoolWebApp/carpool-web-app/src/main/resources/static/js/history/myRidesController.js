myRidesApp.controller("myRidesController", function($scope, $state,
		localStorageService,carpoolService, myRidesHistoryService, $rootScope, $filter,
		commonService) {
	console.log("my rides controller registered");

	$scope.emailId = commonService.emailId;
	console.log('in myRiderController');
	$scope.rideTable = false;
	$scope.fromdate = null;
	$scope.todate = null;
	$scope.status = null;
	$scope.location = null;
	$scope.vehicleTypesList=[];
	$scope.maxDate = new Date();

	$scope.myRideHistory = function() {
		var fromdate = $scope.fromdate;
		var todate = $scope.todate;
		var status = $scope.status;
		var location = $scope.location.getPlace()!=undefined?$scope.location.getPlace().name:null;

		var profileData = localStorageService.get('profile');
		var userid = $scope.emailId;
		var parseFromDate = $filter('date')(fromdate,
		'yyyy-MM-dd');
		var parseToDate = $filter('date')(todate,
		'yyyy-MM-dd');
		
		$scope.postMyRideHistory = {
			"email" : userid,
			"status" : status,
			"location" : location,
			"fromDate" : parseFromDate,
			"toDate" : parseToDate

		}
		
		myRidesHistoryService.getMyRiderHistory($scope.postMyRideHistory.email,$scope.postMyRideHistory).then(
				function(response) {
					console.log(response);
					if (response.errorCode === 500) {
						$scope.message = response.errorMessage
					} else {
						console.log("i got success"+response);
						$scope.riderBookingList = response;
					}
				}, function(response) {
					// console
					alert("response---" + response);
				});

	}
	$scope.getVehicleDetails = function() {
		carpoolService.getVehicleDetails().then(
				function(response) {
					console.log(response);
					if (response.errorCode === 500) {
						$scope.message = response.errorMessage
					} else {
						console.log('vehicletypes'+$scope.vehicleTypesList);
						$scope.vehicleTypesList = response;
					}
				}, function(response) {
					// console
					alert("response---" + response);
				});
	}
	$scope.getVehicleDetails();
});