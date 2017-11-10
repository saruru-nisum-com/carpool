app.factory('UserListModel', [ "UserModel", function(userModel) {
	function UserList() {
		var _self = this;
		this.users = [];

		this.addUsers = function(obj) {
			var user = userModel.clone(obj);
			if (_self.users.length > 0) {
				var check = true;
				_self.users.map(function(innerUser) {
					if (innerUser.userId === user.userId) {
						check = false;
					}
				})
				if (check) {
					_self.users.push(user);
				}

			} else {
				_self.users.push(user);
			}
		};

		this.deleteUser = function(user) {
			var i = -1;

			_self.users.find(function(innerUser) {
				i++;
				return innerUser.userId === user.userId;
			});

			_self.users.splice(i, 1);
		}

		

		this.getUsers = function() {
			return _self.users;
		};

		this.editUser = function(user) {
			var i = -1;
			_self.users.find(function(innerUser) {
				i++;
				if (innerUser.userId === user.userId) {
					_self.users[i] = user;
				}
			})

		}
		

	}
	return {

		newUserListInstance : function() {
			return new UserList();
		}

	}

} ]);
