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
		$scope.saveOptedRidesDetails = [];
		$scope.notOptedARideVar = undefined; 
		$scope.optedARideFn = function(notOptARide) {
			//debugger;
			var notOptedARideVar = notOptARide;
			//$scope.cpid = notOptedARideVar.cpid;
			$scope.optRiderData = 
						{
					
						"cpid":notOptedARideVar.cpId,
						"emailid":localStorageService.get('profile').emailId,
						"status":1
						}
					
			$scope.saveOptedRidesDetails.push($scope.optRiderData);
		}
		//notified pass data cpid and emailId
//		$scope.notifiedRiderData=[
//			{
//				"cpid":$scope.notOptedARideVar.cpId,
//				"emailid":localStorageService.get('profile').emailId
//			}
//		]
		
		
		$scope.saveOptedRider = function(){
			console.log("saveOptedRider clicked");
			//debugger;
			//alert(JSON.stringify($scope.saveOptedRidesDetails))
			//console.log("bala===> "+JSON.stringify($scope.saveOptedRidesDetails));
			optRideService.saveOptRiderDetails($scope.saveOptedRidesDetails).then(function(response) {
				console.log("response from saveOptedRider from server...."+JSON.stringify(response));
				$("#alertMsg").text("Opt a ride successfully.")
				$("#optARideFormModal").modal('show');
			})
		}
		
		$scope.cancelModelPopUp = function() {
			$("#optARideFormModal").modal('hide');
		}
		
		//notified controller function
		$scope.saveNotifiedRider = function(){
			console.log("saveNotifiedRider clicked");
			//debugger;
			optRideService.saveNotifiedRiderDetails($scope.notifiedRiderData).then(function(response) {
				console.log("response from saveNotifiedRider from server...."+JSON.stringify(response));
				$("#alertMsg").text("Notified successfully.")
				$("#optARideFormModal").modal('show');
			})
		}
});


