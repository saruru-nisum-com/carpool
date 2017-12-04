adminApp.controller('configurationsController',
						function($scope, localStorageService,carpoolService,commonService,dateFilter) {
	$scope.carpoolList=[];
	$scope.userName = commonService.userName;
	$scope.vehicleDetails=[];
	$scope.location=commonService.location;
	
	$scope.getCarpools = function() {
			carpoolService.getCarPools($scope.location).then(function(response) {
				if (response.errorCode) {
					$scope.message = response.errorMessage
				} else {
					$scope.carpoolList=response;
				}
			}, function(response) {
				console.log(response);
			})
	}
	
	$scope.getAllCarpools = function() {
		carpoolService.getAllCarPools().then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				$scope.carpoolList=response;
			}
		}, function(response) {
			console.log(response);
		})
		
	}
	$scope.ngChange = function() {
		console.log($scope.isCheck)
		if ($scope.isCheck == true) {
			$scope.getAllCarpools();
		} else {
			$scope.getCarpools();
		}
	}
	
	$scope.getVehicleDetails = function() {
		carpoolService.getVehicleDetails().then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				$scope.vehicleDetails=response;
			}
		}, function(response) {
			console.log(response);
		})
		
	}
	$scope.getVehicleDetails();
});