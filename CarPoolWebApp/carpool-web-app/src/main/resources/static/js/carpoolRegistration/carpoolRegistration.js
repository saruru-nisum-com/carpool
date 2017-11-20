var carpoolRegApp=angular.module('carpoolRegistrationApp', ['ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('carpoolRegistration',{
		url:'/carpoolRegistration',
		templateUrl:'js/carpoolRegistration/carpoolRegistration.html',
		controller: 'carpoolRegistrationController'
	})
	
})	 