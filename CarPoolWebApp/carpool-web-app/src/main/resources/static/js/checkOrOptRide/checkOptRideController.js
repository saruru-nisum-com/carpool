checkOrOptRideModuleApp.controller('checkOptRideController', 
		function($http, $scope, $stateParams, commonService, optRideService,localStorageService){ 
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
				 
		        if (!$scope.notoptdatamodel) {
		            $scope.notoptdatamodel = true;
		        } else {
		            $scope.notoptdatamodel = false;   
		        }
		      angular.forEach($scope.optedorNotOptedDataList, function (notOptARide) {
		    	  
		    	  $scope.notoptdatamodel= $scope.notoptdatamodel;
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
		
		$scope.optedARideFn = function(notOptARide) {
			
			//debugger;
			var notOptedARideVar = notOptARide;
			//$scope.cpid = notOptedARideVar.cpid;
			window.alert("cpid "+notOptedARideVar.cpId)
			$scope.optRiderData = [
						{
						"cpid":notOptedARideVar.cpId,
						"emailid":localStorageService.get('profile').emailId,
						"status":1
						
						}
					]
		}
		
		
		
		$scope.saveOptedRider = function(){
			console.log("saveOptedRider clicked");
			//debugger;
			optRideService.saveOptRiderDetails($scope.optRiderData).then(function(response) {
				console.log("response from saveOptedRider from server...."+JSON.stringify(response));
				$("#alertMsg").text("Opt a ride successfully.")
				$("#optARideFormModal").modal('show');
			})
		}
		
		$scope.cancelModelPopUp = function() {
			$("#optARideFormModal").modal('hide');
		}
});


