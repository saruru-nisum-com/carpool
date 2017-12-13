app.factory('myTodayRidesService',function($http,$q) {
	var tr={};
	
	tr.getMyTodayRides=function(emailId,userType){
		var deferred = $q.defer();
		$http.get('v1/carpool/getMySharedRides/'+emailId+"/"+userType).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	return tr;
});