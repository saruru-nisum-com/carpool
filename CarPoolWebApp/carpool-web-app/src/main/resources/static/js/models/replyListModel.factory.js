app
		.factory(
				'ReplyListModel',
				[
						'$http',
						'ReplyModel',
						'CommentListModel',
						function($http, replyModel, CommentListModel) {
							function ReplyList() {
								var replyCommentList = CommentListModel
										.newCommentListInstance();
								var _self = this;

								this.replies = [];
								this.addReplies = function(obj) {
									var replyDetail = replyModel.clone(obj);

									if (_self.replies.length > 0) {
										var check = true;
										_self.replies
												.map(function(innerReply) {

													if (innerReply.replyId === replyDetail.replyId) {
														check = false;
													}

												});

										if (check) {
											if (obj.replyComments.length > 0) {
												obj.replyComments
														.map(function(
																innerComment) {
															replyCommentList
																	.addComments(innerComment)
														});
												replyDetail.replyComments = replyCommentList.comments;
											}
											_self.replies.push(replyDetail);
											replyCommentList.comments=[];
										}
									} else {
										if (obj.replyComments.length > 0) {
											obj.replyComments
													.map(function(innerComment) {
														replyCommentList
																.addComments(innerComment)
													});
											replyDetail.replyComments = replyCommentList.comments;
										}
										_self.replies.push(replyDetail);
										replyCommentList.comments=[];
									}

								};
							}
							return {

								newReplyListInstance : function() {
									return new ReplyList();
								}
							}

						} ]);