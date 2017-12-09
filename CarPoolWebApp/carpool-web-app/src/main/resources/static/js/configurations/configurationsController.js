adminApp.controller('configurationsController',
						function($scope, localStorageService,carpoolService,commonService,dateFilter) {
	$scope.carpoolList=[];
	$scope.vehicleDetails=[];
	$scope.riderLocation=localStorageService.get('riderLocation')!=null ? localStorageService.get('riderLocation') : commonService.riderLocation;
	$scope.emailId=commonService.emailId;
	console.log('useremailid'+$scope.emailId);
	
	$scope.getCarpools = function() {
		if ($scope.riderLocation != null) {
			carpoolService.getCarPools($scope.riderLocation,$scope.emailId).then(function(response) {
				if (response.errorCode) {
					$scope.message = response.errorMessage
				} else {
					$scope.carpoolList=response;
				}
			}, function(response) {
				console.log(response);
			})
		} else {
			$scope.carpoolList=[];
		}
	}
	
	$scope.getAllCarpools = function() {
		carpoolService.getAllCarPools($scope.emailId).then(function(response) {
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