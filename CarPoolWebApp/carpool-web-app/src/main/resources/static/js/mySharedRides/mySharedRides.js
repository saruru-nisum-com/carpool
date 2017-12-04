var mySharedRidesApp = angular.module('mySharedRidesApp', ['ui.router'])
.config(function($stateProvider){
	$stateProvider.state('mySharedRides',{
		url:'/mySharedRides',
		templateUrl:'js/mySharedRides/mySharedRides.html',
		controller: 'mySharedRidesController'
	})
	
})	 