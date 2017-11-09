app
    .factory('RoleModel', ['$http', function($http) {
    		function Role(roleCopy) {
    		  this.roleId = null;
    		  this.role= null;
    		  
      		  angular.extend(this, roleCopy);
      		  this.clone = function(otherRole) {
      			 return new Role(otherRole);
      		 };
      	  }
      	  
      	  return new Role({});

    }]);