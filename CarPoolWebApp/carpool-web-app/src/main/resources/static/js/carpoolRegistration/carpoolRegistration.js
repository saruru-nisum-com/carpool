var carpoolRegApp=angular.module('carpoolRegistrationApp', ['ui.router', 'gm'])
.config(function($stateProvider){
	$stateProvider.state('carpoolRegistration',{
		url:'/shareARide',
		templateUrl:'js/carpoolRegistration/carpoolRegistration.html',
		controller: 'postARideCtrl'
	})
	.state('sharedRides',{
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