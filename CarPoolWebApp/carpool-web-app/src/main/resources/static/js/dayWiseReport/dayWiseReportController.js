dayWiseReportsApp.controller('dayWiseReportController',function($scope,$stateParams,mySharedRidesService,$state){
	    $scope.parentid = $stateParams.parentid;
		mySharedRidesService.getRideDetails($scope.parentid).then(function(response){
			if(response.errorCode){
				$scope.message = response.errorMessage;
			}else{
				$scope.dayWiseList = response;
			}
		},function(response){
			console.log(response);
		})
		
		$scope.closePopup = function () {
	        $scope.$close(true);
	    };
	
});