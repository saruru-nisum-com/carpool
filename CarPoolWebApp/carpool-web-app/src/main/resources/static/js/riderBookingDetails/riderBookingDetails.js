var riderDetailsApp=angular.module('riderDetailsApp', ['ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('riderBookingDetails',{
		url:'/riderBookingDetails',
		templateUrl:'js/riderBookingDetails/riderBookingDetails.html',
		controller: 'riderBookingDetailsController'
	})
	
})	 