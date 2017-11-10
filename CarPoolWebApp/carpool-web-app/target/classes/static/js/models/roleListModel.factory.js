app.factory('RoleListModel',["RoleModel", function(RoleModel){
    	function RoleList() {
    	   var _self = this;
    	   this.roles = [];
    	   
    	   this.addRoles = function( obj){
				var role = RoleModel.clone(obj);
				if(_self.roles.length > 0){
					var check = true;
					_self.roles.map(function(innerRoles){
			    		 if(innerRoles.roleId === role.roleId){
			    			check = false;
			    		 }
					})
					if(check){
						_self.roles.push(role);
					}
					
				}else{
					_self.roles.push(role);
				}
      	  };
      	  
         this.deleteRole =function(role){
        	 var i = -1;
    			
				_self.roles.find(function(innerRole){
				i++;
				return innerRole.roleId === role.roleId;
				});
      			
				_self.roles.splice(i,1);
         }
         
      	  
      	  this.getRoles = function(){
      		  return _self.roles;
      	  };
      	  
      	  this.editRole=function(role)
      	  {
      		  	var i= -1;
      		  	_self.roles.find(function(innerRole){
      		  		i++;
      		  		if(innerRole.roleId===role.roleId){
      		  			_self.roles[i]=role;
      		  		}
      		  	})
      		  	
      	  }
      	 
      	  
    	}
    	return {
    		
    		
	      	newRoleListInstance: function() {
	  			 return new RoleList();
	  		 }  
    		
    	}
    	
    }]);
