/**
 * 
 */
var app = angular.module('riderApp', []);
app.controller('riderController', function($scope){
	$scope.register = function() {
		alert("clicked register");
	}
});