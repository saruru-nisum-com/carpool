app.factory('riderBookingDetailsService', function($http, $q) {

	var ls = {};
	ls.rider = function(email) {
		var deferred = $q.defer();
		$http.get('v1/carpool/getRiderBookingDetails/'+email).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	ls.getReason=function() {
		var deferred = $q.defer();

		$http.get('v1/carpool/loadRiderStatusReasons').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	ls.cancelRide=function(data) {
        var deferred = $q.defer();

        $http.post('v1/carpool/cancelRiderBookingDetails', data).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {
            deferred.reject(response);
        })
        return deferred.promise;
    }
	
	return ls;

});