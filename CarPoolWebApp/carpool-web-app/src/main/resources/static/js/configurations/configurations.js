var adminApp=angular.module('configurationsApp', ['ui.router' ])
.config(function($stateProvider){
	$stateProvider.state('configurations',{
		url:'/configurations',
		templateUrl:'js/configurations/configurations.html',
		controller: 'configurationsController'
	})
	
})	 