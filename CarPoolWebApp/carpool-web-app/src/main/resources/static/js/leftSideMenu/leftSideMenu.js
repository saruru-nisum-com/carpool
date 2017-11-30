var leftSideMenuApp=angular.module('leftSideMenuApp', ['ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('leftSideMenu',{
		//url:'/carpoolRegistration',
		templateUrl:'js/partials/leftSideMenu.html',
		//controller: 'carpoolRegistrationController'
	})
	
})	 