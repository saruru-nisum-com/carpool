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
	    $scope.doApproval = function() {
			var poolDetVar = $scope.poolDetails;  
			if(poolDetVar == undefined){
				$("#alertMsg").text("Please select rider.");
				$("#MyShareRidesModal").modal('show');
				return false;
			}
			
			if(poolDetVar.statusName == "APPROVED") {
				$("#alertMsg").text("Rider already approved.");
				$("#MyShareRidesModal").modal('show');
				return false;
			}
		};
		
		$scope.cancelModelPopUp = function() {
			$("#MyShareRidesModal").modal('hide');
		}
		
//		$scope.poolDetails = [];
		
		$scope.checkedRowRiderDetails = function(pool) {
//			for(var riderRow = 0; riderRow < poolDetails.length; riderRow++){
//				var riderVar = poolDetails[riderRow];
//				
//			}
//			poolDetails.push(pool);
			$scope.poolDetails = pool; 
		}
		
	    
			mySharedRidesService.getRidesByParentId($scope.parentid).then(function(response) {
				if (response.errorCode) {
					$scope.message = response.errorMessage;
				} else {
					$scope.poolsList=response;
				}
			}, function(response) {
				console.log(response);
			})

      $scope.getReasons = function() {
    	  mySharedRidesService.getReason().then(function(response) {
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
});