var myTodayRidesApp=angular.module('myTodayRidesApp', ['ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('myTodayRides',{
		url:'/myTodayRides',
		templateUrl:'js/myTodayRides/myTodayRides.html',
		controller: 'myTodayRidesController'
	})
	
})	