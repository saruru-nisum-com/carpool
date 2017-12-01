var sharedRideApp = angular.module('driverSharedRideApp', ['ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('driverSharedRides',{
		url:'/driverSharedRides',
		templateUrl:'js/driverSharedRides/sharedRides.html',
		controller: 'sharedRidesController'
	})
	
})	 