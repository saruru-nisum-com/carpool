var mySharedRidesApp = angular.module('mySharedRidesApp', ['ui.router','ui.bootstrap.tpls','ui.bootstrap.modal'])
.config(function($stateProvider){
	$stateProvider.state('mySharedRides',{
		url:'/mySharedRides',
		templateUrl:'js/mySharedRides/mySharedRides.html',
		controller: 'mySharedRidesController'
	}).state('dayWiseReport',{
		/*parent: "mySharedRides",
	      params: {
	    	  parentid: 0
	      },
		onEnter: [
	        "$modal",
	        function($modal) {
	          $modal.open({
	            controller: "dayWiseReportController",
	            templateUrl: 'js/dayWiseReport/dayWiseReport.html',
	            size: 'lg'
	          }).result.finally(function() {
	              $stateProvider.go('^');
	          });
	        }
	      ]*/
		url:'/dayWiseReport/:parentid',
		templateUrl:'js/dayWiseReport/dayWiseReport.html',
		controller: 'dayWiseReportController'
	})
	
})	 