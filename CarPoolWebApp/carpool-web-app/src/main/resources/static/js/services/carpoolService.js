app.factory('carpoolService', function($http, $q) {
	var cs = {};

	cs.getCarPools = function(location,emailId) {
		var deferred = $q.defer();
		$http.get('v1/carpool/getCarPoolDetails?location='+location+"&emailId="+emailId).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	cs.getAllCarPools = function(emailId) {
		var deferred = $q.defer();
		$http.get('v1/carpool/getCarPoolDetails?emailId='+emailId).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	cs.getVehicleDetails = function() {
		var deferred = $q.defer();
		$http.get('v1/carpool/getVehicleTypes').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	cs.updateCarpoolDetails = function(data){
			var deferred = $q.defer();
			$http.put('v1/carpool/update', data).success(function(response) {
				deferred.resolve(response);
			}).error(function(response) {
				deferred.reject(response);
			})
			return deferred.promise;
	}
	cs.cancelCarpoolDetails = function(data){
		var deferred = $q.defer();
		$http.put('v1/carpool/cancel', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
}
	cs.getLoggedInUserCarpools = function(data) {
		console.log("email: "+ data);
		var deferred = $q.defer();
		var encodedUrl = encodeURI("v1/carpool/getLoggedInUserCarpoolDetails/"+data);
		$http.get(encodedUrl).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	return cs;
});