app
		.factory(
				'PagerService',
				function() {
					// service definition
					var service = {};

					service.GetPager = GetPager;

					return service;

					// service implementation
					function GetPager(totalItems, currentPage, pageSize) {
						// default to first page
						currentPage = currentPage || 1;

						// default page size is 10
						pageSize = pageSize || 10;

						// calculate total pages
						var totalPages = Math.ceil(totalItems / pageSize);

						var startPage, endPage;
						if (totalPages <= 10) {
							// less than 10 total pages so show all
							startPage = 1;
							endPage = totalPages;
						} else {
							// more than 10 total pages so calculate start and
							// end pages
							if (currentPage <= 6) {
								startPage = 1;
								endPage = 10;
							} else if (currentPage + 4 >= totalPages) {
								startPage = totalPages - 9;
								endPage = totalPages;
							} else {
								startPage = currentPage - 5;
								endPage = currentPage + 4;
							}
						}

						// calculate start and end item indexes
						var startIndex = (currentPage - 1) * pageSize;
						var endIndex = Math.min(startIndex + pageSize - 1,
								totalItems - 1);

						// create an array of pages to ng-repeat in the pager
						// control
						var pages = _.range(startPage, endPage + 1);

						// return object with all pager properties required by
						// the view
						return {
							totalItems : totalItems,
							currentPage : currentPage,
							pageSize : pageSize,
							totalPages : totalPages,
							startPage : startPage,
							endPage : endPage,
							startIndex : startIndex,
							endIndex : endIndex,
							pages : pages
						};
					}
				})
		.directive(
				"limitTo",
				function() {
					return {
						restrict : "A",
						link : function(scope, elem, attrs) {
							var limit = parseInt(attrs.limitTo);
							elem.bind("paste", function(e) {
								var data = e.originalEvent.clipboardData
										.getData('Text');
								if (data.length >= limit && e.keyCode != 8
										&& e.keyCode != 27)
									e.preventDefault();
							});
							elem.bind("keypress", function(e) {
								if (this.value.length == limit
										&& e.keyCode != 8 && e.keyCode != 27)
									e.preventDefault();
							});
						}
					}
				})
		.directive('tooltip', function() {
			return {
				restrict : 'A',
				link : function(scope, element, attrs) {
					$(element).hover(function() {
						// on mouseenter
						$(element).tooltip('show');
					}, function() {
						// on mouseleave
						$(element).tooltip('hide');
					});
				}
			};
		})
		.directive(
				'datetimepicker',
				[
						'$timeout',
						function($timeout) {
							return {
								restrict : 'EA',
								require : 'ngModel',
								scope : {
									options : '=?',
									onChange : '&?',
									onClick : '&?'
								},
								link : function($scope, $element, $attrs,
										ngModel) {
									var dpElement = $element.parent().hasClass(
											'input-group') ? $element.parent()
											: $element;
									$scope
											.$watch(
													'options',
													function(newValue) {
														var dtp = dpElement
																.data('DateTimePicker');
														$
																.map(
																		newValue,
																		function(
																				value,
																				key) {
																			dtp[key]
																					(value);
																		});
													}, true);

									ngModel.$render = function() {
										// if value is undefined/null do not do
										// anything, unless
										// some date was set before
										var currentDate = dpElement.data(
												'DateTimePicker').date();
										if (!ngModel.$viewValue && currentDate) {
											dpElement.data('DateTimePicker')
													.clear();
										} else if (ngModel.$viewValue) {
											// otherwise make sure it is moment
											// object
											if (!moment
													.isMoment(ngModel.$viewValue)) {
												ngModel
														.$setViewValue(moment(ngModel.$viewValue));
											}
											dpElement.data('DateTimePicker')
													.date(ngModel.$viewValue);
										}
									};

									var isDateEqual = function(d1, d2) {
										return moment.isMoment(d1)
												&& moment.isMoment(d2)
												&& d1.valueOf() === d2
														.valueOf();
									};

									dpElement
											.on(
													'dp.change',
													function(e) {
														if (!isDateEqual(
																e.date,
																ngModel.$viewValue)) {
															var newValue = e.date === false ? null
																	: e.date
																			.format();
															ngModel
																	.$setViewValue(newValue);

															$timeout(function() {
																if (typeof $scope.onChange === 'function') {
																	$scope
																			.onChange();
																}
															});
														}
													});

									dpElement
											.on(
													'click',
													function() {
														$timeout(function() {
															if (typeof $scope.onClick === 'function') {
																$scope
																		.onClick();
															}
														});
													});

									dpElement.datetimepicker($scope.options);
								}
							};
						} ]).directive('autogrow', function () {
						    return {

						        restrict: 'A',
						        link: function postLink(scope, element, attrs) {
						            // hidding the scroll of textarea
						            element.css('overflow', 'hidden');
						            element.css("resize",'none');
						            var update = function(){

						                element.css("height", "auto");
						                
						                var height = element[0].scrollHeight;
						           
						                if(height > 0){

						                    element.css("height", height + "px");
						                  }
						                
						            };

						            scope.$watch(attrs.ngModel, function(){

						                update();
						            });

						            attrs.$set("ngTrim", "false");
						        }
						      };
						    }).filter('formatTimer', function () {
						    	return function (input) {
						    	    function z(n) { return (n < 10 ? '0' : '') + n; }
						    	    var seconds = input % 60;
						    	    var minutes = Math.floor(input % 3600 / 60);
						    	    var hours = Math.floor(input / 3600);
						    	    
						    	    return {'hours':hours,'minutes':minutes,'seconds':seconds};
						    	};
						    })