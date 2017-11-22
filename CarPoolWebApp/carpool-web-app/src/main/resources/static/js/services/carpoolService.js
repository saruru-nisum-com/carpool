app.factory('carpoolService', function($http, $q) {
	var cs = {};

	cs.getCarPools = function(location) {
		console.log('In carpool service')
		var deferred = $q.defer();

		$http.get('v1/carpool/getCarPoolDetails/'+location).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	cs.getAllCarPools = function() {
		console.log('In getAllcarpool service')
		var deferred = $q.defer();

		$http.get('v1/carpool/getAllCarPoolDetails/').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	return cs;
});