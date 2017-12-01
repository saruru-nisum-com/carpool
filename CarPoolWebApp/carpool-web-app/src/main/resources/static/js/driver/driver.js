/**
 * @Author: Dhiraj singh
 * description: driverApp for angularjs implementation.
 * date created: 11th nov. 20117
 * date modified: 29th nov. 20117
 */
var driverApp=angular.module('driverApp', ['ui.router'])
.config(function($stateProvider){
	$stateProvider.state('driver',{
		url:'/driver',
		templateUrl:'./js/driver/driver.html',
		controller: 'driverController'
	})
	
});	 