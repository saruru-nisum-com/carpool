app.factory('carpoolRegistrationService', function($http, $q, localStorageService,
		GoogleSignin) {

	var ls = {};
	ls.rideAddToGridFn = function(data) {
		alert(JSON.stringify(data))
		var deferred = $q.defer();
//		$http.post('v1/user/create', data).success(function(response) {
//			deferred.resolve(response);
//		}).error(function(response) {
//			deferred.reject(response);
//		})
		
		$http.get("https://www.w3schools.com/angular/customers.php")
    .success(function (response) {
    	deferred.resolve(response);
    	}).error(function(response) {
			deferred.reject(response);
    		})
	  console.log(deferred)
		debugger;
		return deferred.promise;
	  window.alert(123456)
	}
//	ls.logout = function() {
//		var deferred = $q.defer();
//		GoogleSignin.disconnect();
//		GoogleSignin.signOut().then(function() {
//			
//			localStorageService.remove('profile');
//			sessionStorage.clear();
//			deferred.resolve({
//				'status' : true
//			});
//
//		}, function() {
//			deferred.reject({
//				'status' : false
//			});
//		});
//	
//		return deferred.promise;
//	}
	return ls;

});