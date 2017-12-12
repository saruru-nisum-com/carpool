/**
 * @Author: Vasu Anupoju
 * description: for Left side menu html page.
 */
var leftSideMenuApp=angular.module('leftSideMenuApp', ['ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('leftSideMenu',{
		//url:'/carpoolRegistration',
		templateUrl:'js/partials/leftSideMenu.html',
		//controller: 'carpoolRegistrationController'
	})
	
})	 