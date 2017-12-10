checkOrOptRideModuleApp.controller('checkOptRideController', 
		function($http, $scope, $stateParams, commonService, optRideService){ 
	$scope.optedorNotOptedDataList={};
	$scope.userEmail = commonService.emailId;
	$scope.showNotOptedOrOptedPool = false;
    $scope.radioChecked = function(){
	if(!$scope.showNotOptedOrOptedPool)
			{
			$scope.showNotOptedOrOptedPool = false;
			optRideService.getMyOptedNotOptedRides($stateParams.parentid, $scope.userEmail, $scope.showNotOptedOrOptedPool).then(function(response) {		
				
				if (response.errorCode) {
						$scope.message = response.errorMessage;
					} else {
						$scope.optedorNotOptedDataList=response;
					}
				}, function(response) {
					console.log(response);
				})
			}
		
		else
			{
			$scope.showNotOptedOrOptedPool = true;
			optRideService.getMyOptedNotOptedRides($stateParams.parentid, $scope.userEmail, $scope.showNotOptedOrOptedPool).then(function(response) {		
				
				if (response.errorCode) {
						$scope.message = response.errorMessage;
					} else {
						$scope.optedorNotOptedDataList=response;
					}
				}, function(response) {
					console.log(response);
				})
			}	
				
	}
	optRideService.getMyOptedNotOptedRides($stateParams.parentid, $scope.userEmail, $scope.showNotOptedOrOptedPool).then(function(response) {			
		if (response.errorCode) {
				$scope.message = response.errorMessage;
			} else {
				$scope.optedorNotOptedDataList=response;
			}
		}, function(response) {
			console.log(response);
		})
		$scope.showPoolsOpted = function() {
			$scope.selectedAll = false;
		   };
		 $scope.checkAll = function() {
		        if ($scope.selectedAll) {
		            $scope.selectedAll = true;
		        } else {
		            $scope.selectedAll = false;
		        }
		        angular.forEach($scope.notoptdata, function (notOptOrOptARide) {
		           notoptdatamodel= $scope.selectedAll;
		        });
		    };
		    
		   $scope.opt1 = true;
		   $scope.opt2 = false;
		    
		   $scope.toggler1 = function (){
		   $scope.opt1 = true;
		   $scope.opt2 =  false;
		   } 
		$scope.toggler2 = function (){
			   $scope.opt1 = false;
			   $scope.opt2 = true;  
		   }
});


