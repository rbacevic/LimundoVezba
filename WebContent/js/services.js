var limundoServices = angular.module('limundo.Services', ['ngResource']);

limundoServices.factory('Kategorije', function ($resource){
	return $resource('http://localhost:8080/Test/test/test/kategorije',{},{
		query:{method: 'GET', isArray: true}
		
	});
	
	
	
	
});