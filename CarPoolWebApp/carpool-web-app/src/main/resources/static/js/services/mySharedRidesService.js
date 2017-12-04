app.factory('mySharedRidesService', function($http, $q) {
	var srs = {};

	srs.getMyRides = function(emailId) {
		var deferred = $q.defer();
		$http.get('v1/carpool/getMySharedRides/'+emailId).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	
	return srs;
});