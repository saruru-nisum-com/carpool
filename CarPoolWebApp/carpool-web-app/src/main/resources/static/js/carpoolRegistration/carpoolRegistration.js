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
	}).state('sharedRides',{
		url:'/sharedRides',
		templateUrl:'./js/history/sharedRides.html',
		controller: 'carpoolRegistrationController'
	}).state('myRides',{
		url:'/myRides',
		templateUrl:'./js/history/myRides.html',
		controller: 'carpoolRegistrationController'
	}).state('todaysRide',{
		url:'/todaysRide',
		templateUrl:'./js/todaysRide/todaysRide.html',
		controller: 'carpoolRegistrationController'
	}).state('faq',{
		url:'/faq',
		templateUrl:'./js/faq/faq.html',
		controller: 'carpoolRegistrationController'
	})
	
})	 