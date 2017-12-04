/**
 * @Author: Dhiraj singh
 * description: riderService for calling the rest service.
 * date created: 11th nov. 20117
 * date modified: 29th nov. 20117
 */
riderApp.factory('riderService', function($http, $q, localStorageService,
		GoogleSignin) {

	var ls = {};
	
	ls.registerAsRider=function(data) {
        var deferred = $q.defer();

        $http.post('v1/carpool/registerrider', data).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {
            deferred.reject(response);
        })
        return deferred.promise;
    }
	
	ls.getRegisterRiderData=function(data) {
		var deferred = $q.defer()
		var encodedUrl = encodeURI("v1/carpool/getProfile/"+data);
//		alert(encodedUrl);
		$http.get(encodedUrl).success(function(response) {

			console.log("=====> v1/carpool/getProfile/"+data);
			console.log("got success");
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
			
		return deferred.promise;
	}
	
	ls.updateRiderData = function(data) {
		var deferred = $q.defer()
		$http.put("v1/carpool/updateDriver", data).success(function(response) {
			console.log("Rider data update SUCCESS at service");
			deferred.resolve(response);
		}).error(function(errResponse) {
			alert("update is failed...")
			console.log("Rider data update FAILED at service");
			deferred.reject(errResponse);
		})
		return deferred.promise;
	}
	
	return ls;

});