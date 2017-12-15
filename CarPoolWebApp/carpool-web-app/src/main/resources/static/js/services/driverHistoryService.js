/**
 *	@author vkallada 
 *	Driver history service js
 *	createdDate: 13-12-17
 */
"use strict";

console.log(":: Driver history service initiated ::");
driverHistoryMod.factory('DriverHistoryService', function($http, $q) {
	
	console.log(":: Driver history service loaded :: ");
	var ls = {};
	
	/**
	 * @author vkallada
	 * Service method to make the Restful call to fetch the list of driver posted pools4
	 * @param data
	 */
	ls.searchDriverHistory = function(data) {
		var deferred = $q.defer();
		var emailId = data.emailId;
		$http.get('v1/carpool/driverPoolHistory/'+emailId).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	return ls;

});