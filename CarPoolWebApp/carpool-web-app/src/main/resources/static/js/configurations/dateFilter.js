adminApp.filter('dateFilter', function($filter) {
	return function(input) {

		var date = new Date(input);
		return($filter('date')(date, 'dd/MM/yyyy') );
		  }
});