adminApp
		.controller(
				'configurationsController',
				function($scope, localStorageService, carpoolService,
						commonService, dateFilter, $location, $window,riderBookingDetailsService) {
					$scope.carpoolList = [];
					$scope.vehicleDetails = [];
					$scope.riderLocation = localStorageService
							.get('riderLocation') != null ? localStorageService
							.get('riderLocation') : commonService.riderLocation;
					$scope.emailId = commonService.emailId;
					console.log('useremailid' + $scope.emailId);
					$scope.mychoice = "js/configurations/carpoolsTilesView.html"
					$scope.riderBookingList = [];
					$scope.emailId = commonService.emailId;
					$scope.reasons = {};
					$scope.userStatus = localStorageService.get('userStatus');
					$scope.isRider = false;

					$scope.toggleView = function(type) {
						$scope.type = type;
						if ($scope.type == 'Tiles') {
							$scope.mychoice = "js/configurations/carpoolsTilesView.html";
						} else if ($scope.type == 'List') {
							$scope.mychoice = "js/configurations/carpoolListView.html";
						}
					}

					$scope.getCarpools = function() {
						if ($scope.riderLocation != null) {
							carpoolService.getCarPools($scope.riderLocation,
									$scope.emailId).then(function(response) {
								if (response.errorCode) {
									$scope.message = response.errorMessage
								} else {
									$scope.carpoolList = response;
								}
							}, function(response) {
								console.log(response);
							})
						} else {
							$scope.carpoolList = [];
						}
					}

					$scope.getAllCarpools = function() {
						carpoolService.getAllCarPools($scope.emailId).then(
								function(response) {
									if (response.errorCode) {
										$scope.message = response.errorMessage
									} else {
										$scope.carpoolList = response;
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
						carpoolService.getVehicleDetails().then(
								function(response) {
									if (response.errorCode) {
										$scope.message = response.errorMessage
									} else {
										$scope.vehicleDetails = response;
									}
								}, function(response) {
									console.log(response);
								})

					}
					$scope.checkRiderOrNot = function(parentId) {
						if ($scope.userStatus != null
								&& $scope.userStatus.length > 0) {
							for (var i = 0; i < $scope.userStatus.length; i++) {
								if ($scope.userStatus[i] == "R") {
									$location
											.url('/checkOrOptRide/' + parentId);
								} else {
									$location.url('/rider');
								}
							}
						} else {
							$location.url('/rider');
						}
					}
					$scope.checkUserStatus=function() {
						if ($scope.userStatus != null
								&& $scope.userStatus.length > 0) {
							for (var i = 0; i < $scope.userStatus.length; i++) {
								if ($scope.userStatus[i] == "R") {
									$scope.isRider = true;
								}
							}
						}
					}
					$scope.riderBookingList=[];
					$scope.emailId = commonService.emailId;
					$scope.reasons={};
					console.log('in riderBookingDetailsController');
					
					$scope.getRiderBookingDetails = function() {
						riderBookingDetailsService.rider($scope.emailId).then(function(response) {
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
					$scope.getVehicleDetails();
					$scope.checkUserStatus();
					$scope.getCarpools();
				});