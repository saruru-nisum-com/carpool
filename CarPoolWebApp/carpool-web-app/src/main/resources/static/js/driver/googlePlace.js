/**
 * @author dhiraj
 * 
 */

driverApp.directive('googlePlace', function() {
    return {
        require : 'ngModel',
        scope: {
	        	myLocation: '=',
		    lat: '=',
		    lng: '='
          },
        link : function(scope, element, attrs, model) {
        	var options = {
		   	  types: ['(regions)'],
		   	  componentRestrictions: {country: "in"}
		   	};
            scope.gPlace = new google.maps.places.Autocomplete(element[0],options);
            console.log("scope.gPlace :: "+scope.gPlace);
            google.maps.event.addListener(scope.gPlace, 'place_changed',
                    function() {
                        scope.$apply(function() {
                            model.$setViewValue(element.val());
                            myLocation = scope.gPlace.getPlace().geometry.location;
                            lat = myLocation.lat();
                            lng = myLocation.lng();
	                			console.log("location :: "+myLocation);
	                			console.log("lattitude :: "+lat);
	                			console.log("longitude :: "+lng);
                        });
                    });
        }
    };
});