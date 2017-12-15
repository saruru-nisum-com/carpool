var myRidesApp = angular.module('myRidesApp', [ 'ui.router']);
myRidesApp.config(function($stateProvider) {
	$stateProvider.state('myRides', {
		url : '/myRides',
		templateUrl : './js/history/myRides.html',
		controller : 'myRidesController'
	})

});
