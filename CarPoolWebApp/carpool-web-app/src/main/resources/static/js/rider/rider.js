/**
 * @Author: Dhiraj singh
 * description: riderApp for angularjs implementation.
 * date created: 11th nov. 20117
 * date modified: 29th nov. 20117
 */
var riderApp = angular.module('riderApp', ['ui.router'])
.config(function($stateProvider){
	$stateProvider.state('rider',{
		url:'/rider',
		templateUrl:'./js/rider/rider.html',
		controller: 'riderController'
	})
	
});	 
