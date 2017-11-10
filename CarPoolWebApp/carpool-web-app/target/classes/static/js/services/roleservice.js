app.factory('roleService', function($http, $q) {
	var rs={};
	

	rs.getRoles=function() {
		var deferred = $q.defer();
		$http.get('v1/userrole/retrieve').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	rs.addRole=function(data) {
		var deferred = $q.defer();
		$http.post('v1/userrole/create', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	rs.deleteRole=function(roleid) {
		var deferred = $q.defer();
		$http.delete('v1/userrole/delete/' + roleid).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	rs.editRole=function(data) {
		var deferred = $q.defer();
		$http.put('v1/userrole/update', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	return rs;
	
})