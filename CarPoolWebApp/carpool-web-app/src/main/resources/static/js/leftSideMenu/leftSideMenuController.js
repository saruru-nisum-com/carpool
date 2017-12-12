leftSideMenuApp.controller('leftSideMenuController', function($scope, $state,
		localStorageService) {
	$scope.rider=false;
	$scope.driver=false;
	$scope.checkUserStatus =function() {
		var status = localStorageService.get("userStatus");
		if(status == "R,D"){
			//for both R,D
			$scope.rider=false;
			$scope.driver=false;
			//enable all links.
		}else if(status == "R"){
			//for R
			$scope.rider=true;
			$scope.driver=false;
		}else if(status == "D"){
			//for D
			$scope.rider=false;
			$scope.driver=true;
		}//else if(status == "undefined" || status == null){
		else if(status==null){
			$scope.rider=true;
			$scope.driver=true;
		}
	}
	$scope.$on("checkStatus",function(event,data){
		$scope.checkUserStatus();
	})
});