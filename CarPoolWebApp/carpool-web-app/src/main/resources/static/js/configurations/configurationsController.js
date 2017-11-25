adminApp.controller('configurationsController',
						function($scope, localStorageService,carpoolService,commonService) {
	$scope.carpoolList=[];
	$scope.userLocation = localStorageService.get('location');
	$scope.userName = commonService.userName;
	console.log('username is'+$scope.userName);
	$scope.vehicleDetails=[{"id":1,"vehicletype":"bike","noofseats":1},{"id":2,"vehicletype":"car","noofseats":3}];
	
	$scope.getCarpools = function() {
		$scope.getCarPoolObj={
				'location':'hyyd'
		}
		$scope.carpoolList = [{
			"id": 1,
			"parentid": 1,
			"userid": "mahesh",
			"vehicleType": 2,
			"totalNoOfSeats": 1,
			"fromDate": "2017-11-16",
			"toDate": "2017-11-16",
			"startTime": "2017-11-16",
			"toTime": "2017-11-16",
			"status": "completed",
			"createddate": 51078381000000,
			"modifieddate": 51078381000000
			},{
			"id": 2,
			"parentid": 2,
			"userid": "Madhu",
			"vehicleType": 1,
			"totalNoOfSeats": 1,
			"fromDate": "2017-11-16",
			"toDate": "2017-11-16",
			"startTime": "2017-11-16",
			"toTime": "2017-11-17",
			"status": "PartiallyCompleted",
			"createddate": 82082294000000,
			"modifieddate": 82082294000000
			}
];
			console.log('In getcarpoll controller');
			carpoolService.getCarPools($scope.getCarPoolObj.location).then(function(response) {
				if (response.errorCode) {
					$scope.message = response.errorMessage
				} else {
					console.log('response is....'+response);
					$scope.carpoolList=response.data;
					console.log('carpoolsize...'+$scope.carpoolList);
				}

			}, function(response) {
				console.log(response);
			})
	}
	
	$scope.getAllCarpools = function() {
		$scope.getCarPoolObj = {
				'location':''
		}
		$scope.carpoolList = [{
			"id": 1,
			"parentid": 1,
			"userid": "mahesh",
			"vehicleType": 1,
			"totalNoOfSeats": 1,
			"fromDate": "2017-11-16",
			"toDate": "2017-11-16",
			"startTime": "2017-11-16",
			"toTime": "2017-11-16",
			"status": "completed",
			"createddate": 51078381000000,
			"modifieddate": 51078381000000
			},{
			"id": 2,
			"parentid": 1,
			"userid": "Madhu",
			"vehicleType": 2,
			"totalNoOfSeats": 1,
			"fromDate": "2017-11-16",
			"toDate": "2017-11-16",
			"startTime": "2017-11-16",
			"toTime": "2017-11-17",
			"status": "canceled",
			"createddate": 82082294000000,
			"modifieddate": 82082294000000
			},
			{
				"id": 3,
				"parentid": 3,
				"userid": "Madhusds",
				"vehicleType": 2,
				"totalNoOfSeats": 1,
				"fromDate": "2017-11-16",
				"toDate": "2017-11-16",
				"startTime": "2017-11-16",
				"toTime": "2017-11-17",
				"status": "completed",
				"createddate": 82082294000000,
				"modifieddate": 82082294000000
				}
];
		console.log('in getAllCarpools fuction');
		carpoolService.getAllCarPools().then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				console.log('response is....'+response);
				$scope.carpoolList=response.data;
				console.log('carpoolsize...'+$scope.carpoolList);
			}

		}, function(response) {
			console.log(response);
		})
		
	}
	$scope.ngChange = function() {
		if ($scope.chkselct == true) {
			$scope.getAllCarpools();
		} else {
			$scope.getCarpools();
		}
	}
	
});