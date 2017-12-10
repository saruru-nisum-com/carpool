app.factory('optRideService', function($http, $q) {
	var ors = {};
	ors.getMyOptedNotOptedRides = function(parentid, emailid, isOpted) {
		var deferred = $q.defer();
		$http.get("v1/carpool/getCarpoolsDataNotOptedOrOptedByMe/"+parentid+
				"?emailId="+emailid+"&optedData="+isOpted).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	return ors;
});