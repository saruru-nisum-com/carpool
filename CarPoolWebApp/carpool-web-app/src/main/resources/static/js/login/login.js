var loginApp = angular.module('loginApp', [ 'ui.router'])
loginApp.config(function($stateProvider) {
	$stateProvider.state('login', {
		url : '/login',
		templateUrl : 'js/login/login.html',
		controller : 'loginController'
	})

})
