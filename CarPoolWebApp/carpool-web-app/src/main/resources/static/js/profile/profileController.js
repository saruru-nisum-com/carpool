mainApp.controller('profileController', function($scope, $http,
		localStorageService, categoryService, commonService, CategoryListModel,
		$q) {

	$scope.categoriesList = [];
	$scope.profile = commonService.profile;
	$scope.categoriesList = CategoryListModel.newCategoryListInstance();
	if (commonService.categoriesList !== (undefined || null)) {
		var list = commonService.categoriesList;
		list.map(function(category) {

			$scope.categoriesList.addCategories(category);

		})

	} else {
		categoryService.getCategories().then(function(response) {

			if (response.errorCode === 500) {
				 $scope.message=response.errorMessage
			 }else{
				response.map(function(category) {
					$scope.categoriesList.addCategories(category);
				})
				localStorageService.set('categoriesList', response);
			}

		},function(response){
			console.log(response);
		})
	}

	var notification = "Yes";

	$scope.saveProfile = function() {

		// alert("calling");

		var deferred = $q.defer();

		$http.put('v1/userprofile/update', $scope.profile).success(
				function(response) {
					deferred.resolve(response);
				}).error(function(response) {
			deferred.reject(response);
		})
		alert("User Updated Successfully");
		return deferred.promise;

	}

});