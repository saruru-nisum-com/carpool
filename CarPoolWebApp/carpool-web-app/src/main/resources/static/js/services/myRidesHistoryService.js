app.factory('myRidesHistoryService', function($http, $q) {

	var ls = {};

	ls.getMyRiderHistory = function(emailId,data) {
		var deferred = $q.defer();
		$http.post('v1/carpool/myrider/history/'+emailId,data).success(
				function(response) {
					console.log("v1/carpool/myrider/history/"+emailId + response)
					deferred.resolve(response);
				}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}

	return ls;

});