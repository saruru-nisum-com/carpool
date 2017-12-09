app.factory('optRideService', function($http, $q) {
	var ors = {};

	ors.getMyOptedNotOptedRides = function(parentid, emailid) {
		var deferred = $q.defer();
		console.log("inside service++++");
		$http.get("v1/carpool/getCarpoolsDataNotOptedOrOptedByMe/"+parentid+
				"?emailId="+emailid+"&optedData="+false).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	return ors;
});