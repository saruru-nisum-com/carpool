app.factory('loginLogoutService', function($http, $q, localStorageService,
		GoogleSignin) {

	var ls = {};
	ls.login = function(data) {
		var deferred = $q.defer();
		$http.post('v1/user/create', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	ls.logout = function() {
		var deferred = $q.defer();
		GoogleSignin.disconnect();
		GoogleSignin.signOut().then(function() {
			
			localStorageService.remove('profile');
			sessionStorage.clear();
			deferred.resolve({
				'status' : true
			});

		}, function() {
			deferred.reject({
				'status' : false
			});
		});
	
		return deferred.promise;
	}
	return ls;

});