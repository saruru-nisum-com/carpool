var carpoolRegApp=angular.module('carpoolRegistrationApp', ['ui.router', 'gm'])
.config(function($stateProvider){
	$stateProvider.state('carpoolRegistration',{
		url:'/carpoolRegistration',
		templateUrl:'js/carpoolRegistration/carpoolRegistration.html',
		controller: 'carpoolRegistrationController'
	}).state('driver',{
		url:'/driver',
		templateUrl:'./js/driver/driver.html',
		controller: 'carpoolRegistrationController'
	}).state('rider',{
		url:'/rider',
		templateUrl:'./js/rider/rider.html',
		controller: 'carpoolRegistrationController'
	})
	
})	 