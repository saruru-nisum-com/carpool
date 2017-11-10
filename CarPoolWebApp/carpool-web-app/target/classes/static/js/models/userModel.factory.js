app
    .factory('UserModel', ['$http', function($http) {
    		function User(userCopy) {
    		  this.userId = null;
    		  this.activeStatus = null;
    		  this.role={};
    		  this.name=null;
    		  this.emailId=null;
    		  this.image=null;
    		  this.loginDate=null;
    		  this.createDate=null;
    		  this.role.roleId=null;
    		  this.role.role=null;
    		  this.role.createdDate=null;
      		  angular.extend(this, userCopy);
      		  this.clone = function(otherUser) {
      			 return new User(otherUser);
      		 };
      	  }
      	  
      	  return new User({});

    }]);