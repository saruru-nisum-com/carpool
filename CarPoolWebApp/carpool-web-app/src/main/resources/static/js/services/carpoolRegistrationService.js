app.factory('carpoolRegistrationService', function($http, $q, localStorageService,
		GoogleSignin) {

	var ls = {};
	ls.rideAddToGridFn = function(data) {
		alert(JSON.stringify(data))
		var deferred = $q.defer();
		
		$http.get("https://www.w3schools.com/angular/customers.php")
    .success(function (response) {
    	deferred.resolve(response);
    	}).error(function(response) {
			deferred.reject(response);
    		})
		return deferred.promise;
	}
	
	ls.registerAsDriver=function(data) {
		var deferred = $q.defer();

		$http.post('v1/carpool/registerdriver', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	ls.getRegisterDriverData=function(data) {
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
	
	ls.updateDriverData = function(data) {
		var deferred = $q.defer()
		//TODO: correct the encodedUrl with the valid backend service url to update
		var encodedUrl = encodeURI("v1/carpool/"+data);
		$http.post(encodedUrl).success(function(response) {
			console.log("Driver data update SUCCESS at service");
			deferred.resolve(response);
		}).error(function(errResponse) {
			console.log("Driver data update FAILED at service");
			deferred.reject(errResponse);
		})
		return deferred.promise;
	}
	
	return ls;

});