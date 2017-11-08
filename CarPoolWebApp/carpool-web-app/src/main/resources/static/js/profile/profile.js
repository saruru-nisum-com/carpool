var mainApp=angular.module('profileApp', [ 'ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('profile',{
		url:'/profile',
		templateUrl:'js/profile/profile.html',
		controller: 'profileController'
	})
})    
		