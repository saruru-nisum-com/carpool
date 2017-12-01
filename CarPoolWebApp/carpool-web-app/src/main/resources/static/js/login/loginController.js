loginApp.controller('loginController', function($scope, $state,
		localStorageService, loginLogoutService, $rootScope, GoogleSignin) {

	// --google sign in methods
	$scope.login = function() { 
		 
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

	};

})
