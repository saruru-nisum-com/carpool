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
	
	srs.cancelRide = function(carpoolData) {
		var deferred = $q.defer();
		$http.put('v1/carpool/cancelByParentId',carpoolData).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	srs.getRidesByParentId = function(parentId) {
		var deferred = $q.defer();
		$http.get('v1/carpool/parent/'+parentId).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	
	return srs;
});