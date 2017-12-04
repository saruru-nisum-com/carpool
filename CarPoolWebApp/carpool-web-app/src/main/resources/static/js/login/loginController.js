loginApp.controller('loginController', function($scope, $state,
		localStorageService, loginLogoutService, $rootScope, GoogleSignin) {

	// --google sign in methods
	$scope.login = function() { 
		 
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

			 $state.go("leftSideMenu");
			 }
			 }, function(response) {
			 // console
			 });
			 });

	};

})
