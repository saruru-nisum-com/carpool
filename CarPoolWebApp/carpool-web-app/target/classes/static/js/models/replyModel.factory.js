app.factory('ReplyModel', [ '$http',
		function($http) {

			function Reply(replyCopy) {
				var _self = this;
				this.replyId = null;
				this.replyDescription = null;
				this.updatedDate = null;
				this.emailId = null;
				this.displayImage = null;
				this.replyComments=[];
				angular.extend(this, replyCopy);
				this.clone = function(otherReply) {
					return new Reply(otherReply);
				};
				
			}

			return new Reply({})

		} ]);