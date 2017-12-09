var checkOrOptRideModuleApp=angular.module('checkOrOptRideApp', ['ui.router'])
.config(function($stateProvider){
	$stateProvider.state('checkOrOptRide',{
		url:'/checkOrOptRide/:parentid',
		templateUrl:'js/checkOrOptRide/checkOrOptARide.html',
		controller: 'checkOptRideController'
	})
});