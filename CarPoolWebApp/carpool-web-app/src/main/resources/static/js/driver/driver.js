/**
 * 
 */
var app = angular.module('driverApp', []);
app.controller('driverController', function($scope){
	$scope.register = function() {
		alert("clicked register");
	}
	
	$scope.update = function() {
		alert("clicked update");
	}
});