app.factory('userService', function($http, $q) {
	var us={};
	
	us.getUsers=function() {
		var deferred = $q.defer();
		
		$http.get('v1/user/getUsers').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	us.postUser=function(data) {
		var deferred = $q.defer();
		
		$http.post('', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	us.deleteUser=function(userid) {
		var deferred = $q.defer();
		
		$http.put('v1/user/deleteUser/' + userid).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	us.editUser=function(data) {
		var deferred = $q.defer();
		
		$http.put('v1/user/update',data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	
	return us;
	
})