app.service('commonService', function(localStorageService) {
	var cs={};
	var profile=localStorageService.get('profile');
	cs.emailId=profile ? profile.emailId : null;
	cs.profile=profile;
	cs.categoriesList=localStorageService.get('categoriesList');
	cs.userName=profile ? profile.userName : null;
	cs.riderLocation=profile ? profile.riderLocation : null;
	cs.driverLocation=profile ? profile.driverLocation : null;
	return cs;
});