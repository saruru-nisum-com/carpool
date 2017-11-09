app.service('commonService', function(localStorageService) {
	var cs={};
	var profile=localStorageService.get('profile');
	cs.emailId=profile.emailId;
	cs.profile=profile;
	cs.categoriesList=localStorageService.get('categoriesList');
	return cs;
});