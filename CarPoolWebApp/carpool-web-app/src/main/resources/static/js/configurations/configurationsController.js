adminApp.controller('configurationsController',
						function($scope, localStorageService,carpoolService,commonService,dateFilter) {
	$scope.carpoolList=[];
	$scope.userLocation = localStorageService.get('location');
	$scope.userName = commonService.userName;
	$scope.vehicleDetails=[{"id":1,"vehicletype":"bike","noofseats":1},{"id":2,"vehicletype":"car","noofseats":3}];
	
	$scope.getCarpools = function() {
		$scope.getCarPoolObj={
				'location':'hyd'
		}
		$scope.carpoolList = [];
			carpoolService.getCarPools($scope.getCarPoolObj.location).then(function(response) {
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
		$scope.carpoolList = [];
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
	
});