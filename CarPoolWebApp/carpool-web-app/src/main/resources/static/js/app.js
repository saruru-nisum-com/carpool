var app = angular
		.module(
				'nisumApp',
				[ 'ui.router', 'gm', 'profileApp', 'loginApp', 'carpoolRegistrationApp','checkOrOptRideApp', 'LocalStorageModule',
						'textAngular', 'am.multiselect', 'leftSideMenuApp','google-signin','configurationsApp','mySharedRidesApp', 'driverApp', 'riderApp','riderDetailsApp'])
			

		.config(function($stateProvider, $urlRouterProvider) {

			$urlRouterProvider.otherwise('/login');
		})
		.config(
				[
						'GoogleSigninProvider',
						function(GoogleSigninProvider) {
							GoogleSigninProvider
									.init({
										clientId : '167391935529-bns0200aplm1inm0qpb5ie7te1g1n50t.apps.googleusercontent.com',
										hostedDomain : 'nisum.com'
									});
						} ])

		.run(
				function($rootScope, $window, $state, $location,
						localStorageService, $timeout) {
					$rootScope.navBarToggle = false;
					$rootScope.$on("$locationChangeStart",
							function(event, next, current) {

								$rootScope.urlChanged = $location.path();

								var urls = [ '/home', '/profile', '/carpoolRegistration' ]
								if (urls.indexOf($rootScope.urlChanged) > -1) {
									$rootScope.navBarToggle = false;
								} else if ($rootScope.urlChanged
										.indexOf('/question/') > -1) {
									$rootScope.navBarToggle = false;
								} else {
									$rootScope.navBarToggle = false;
								}

								// 
								var profile = localStorageService
										.get("profile");
								if (profile !== (undefined || null)
										&& $rootScope.urlChanged === '/carpoolRegistration') {
									$timeout(function() {
										$state.go('carpoolRegistration');
									}, 0);

								} else if (profile === null) {
									$timeout(function() {
										$state.go('login');
									}, 0);

								}

							})
				})
		.controller(
				'mainController',
				function($scope, $rootScope, localStorageService, $state,
						$http, loginLogoutService,$window) {
					var vm = this;
//					vm.redirect = function() {
//						$state.go('addquestion');
//					}
					vm.getProfile = function() {

						vm.profile = localStorageService.get('profile');
					}
//					vm.init = function() {
//						questionService
//								.getQuestionsCount()
//								.then(
//										function(response) {
//											if (response.errorCode === 500) {
//												$scope.message = response.errorMessage
//											} else {
//												$rootScope.questionCount = response.questionCount;
//												$rootScope.userCount = response.userCount;
//											}
//										}, function(response) {
//											console.log(response);
//										});
//					}
					vm.logout = function() {

						loginLogoutService.logout().then(function(response) {
							if (response.status) {
								$window.localStorage.clear();
								$window.location.reload();
								$state.go('login');
							}
						})

					}

				})