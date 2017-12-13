riderDetailsApp.controller('riderBookingDetailsController',
						function($scope,localStorageService,riderBookingDetailsService,commonService) {
	$scope.riderBookingList=[];
	$scope.emailId = commonService.emailId;
	$scope.reasons={};
	$scope.selection=[];
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
	
	$scope.changeLocation = function(riderDetails, selected) {
		
		console.log("selected name " + selected.id);
		riderDetails.reason = selected.id;
		console.log("riderDetails name " + riderDetails.userName + " email " + riderDetails.email + " mobile " + riderDetails.mobile);
		$scope.selection.push(riderDetails);
		
		
	}
	
	$scope.cancelRide = function() {
	      
		  riderBookingDetailsService.cancelRide(angular.toJson($scope.selection)).then(function(response) {
			  if(response.errorCode) {
				  $scope.message = response.errorMessage;
				  setTimeout(function(){ $('#cancelARideFormModal').modal('hide');
					}, 3000);
					$("#alertMsg").text("Cancelling a ride failed.");
					$('#cancelARideFormModal').modal('show'); 
			  } else {
				  console.log("selected items " + $scope.selection);
				  setTimeout(function(){ $('#cancelARideFormModal').modal('hide');
					}, 3000);
					$("#alertMsg").text("Cancelled a ride successfully.");
					$('#cancelARideFormModal').modal('show'); 
				  $scope.riderBookingList = response;
			  }
		  });
	  }

	
	
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
	
});