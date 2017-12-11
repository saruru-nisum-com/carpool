app.factory('carpoolRegistrationService', function($http, $q, localStorageService,
		GoogleSignin) {

	var ls = {};
	ls.rideAddToGridFn = function(data) {
		var deferred = $q.defer();
		
		$http.post('v1/carpool/create', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	ls.getCarpolData = function(data) {
		var deferred = $q.defer();
		
		$http.post('v1/carpool/getCarpoolPoolData', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	ls.getCarpoolVehicleType = function(emailId) {
		var deferred = $q.defer();
		
		$http.get('v1/carpool/getProfile/'+emailId).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	ls.getVehicleDetails = function(emailId) {
		var deferred = $q.defer();
		$http.get('v1/carpool/getVehicleTypes').success(function(response) {
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
	
	
	ls.getUserLocation = function(data) {
		var deferred = $q.defer()
		var encodedUrl = encodeURI("v1/carpool/getUserLocation/"+data);
		$http.get(encodedUrl).success(function(response) {
			console.log("Location Loaded Successfully");
			deferred.resolve(response);
		}).error(function(errResponse) {
			console.log("Location is Not Loaded "+errResponse);
			deferred.reject(errResponse);
		})
		return deferred.promise;
	}
	
	return ls;

});