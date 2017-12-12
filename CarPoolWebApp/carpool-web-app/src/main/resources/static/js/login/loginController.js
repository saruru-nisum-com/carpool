loginApp.controller('loginController', function($scope, $state,
		localStorageService, loginLogoutService, carpoolRegistrationService,$rootScope,GoogleSignin) {

	// --google sign in methods
	$scope.login = function() { 
		
		$scope.userStatus = [];
		 
		 GoogleSignin.signIn().then(function(authResult) {
			 var profile = authResult.getBasicProfile();

			 $scope.profile = {
			 "userName" : profile.getName(),
			 "emailId" : profile.getEmail(),
			 "image" : profile.getImageUrl()
			 }

			 loginLogoutService.login($scope.profile).then(function(response) {
			 if (response.errorCode === 500) {
			 $scope.message = response.errorMessage
			 } else {
			 localStorageService.set('profile', response);
			 $state.go("configurations");
			 $scope.getUserData = function() {
					var profileSessionData = localStorageService
							.get('profile');
					$scope.userId = profileSessionData.emailId;
					carpoolRegistrationService
							.getRegisterDriverData($scope.userId)
							.then(
									function(response) {
										angular
												.forEach(
														response,
														function(value, key) {
															var userStatus = [];
															if (value.isRider == 0) {// If
																$scope.isRegisteredAsDriver = true;
																$scope.autocomplete = value.location;
																$scope.nearBy = value.nearby;
																$scope.userStatus.push("D");
																localStorageService.set("userStatus",$scope.userStatus)
																
															} else if (value.isRider == 1) {// If
																		console.log("Registered as Rider");
																		$scope.userStatus.push("R")
																		localStorageService.set("userStatus",$scope.userStatus)
																		
															}
														});
									});
				}
			 
			 $scope.getUserData();
			 }
			 }, function(response) {
			 // console
			 });
			 });

	};

})
