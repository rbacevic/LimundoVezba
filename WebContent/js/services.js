'use strict';
var services = angular.module('limundo.services', [ 'ngResource' ]);
var baseUrl = 'http://localhost:8080/Test/test/test';

services.factory('SveAukcijeFactory', function($resource) {
	return $resource(baseUrl + '/aukcije', {}, {
		query : {
			method : 'GET',
			params : {},
			isArray : true
		}

	})

});

services.factory('SveKategorijeFactory', function($resource) {
	return $resource(baseUrl + '/kategorije', {}, {
		query : {
			method : 'GET',
			params : {},
			isArray : true
		}

	})

});

services.factory('RandomAukcijeFactory', function($resource) {
	return $resource(baseUrl + '/randomaukcije', {}, {
		query : {
			method : 'GET',
			params : {},
			isArray : true
		}

	})

});
services.factory('PrikazAukcijeFactory', function($resource) {
	return $resource(baseUrl + '/aukcija/:id_aukcije', {}, {
		query : {
			method : 'GET',
			params : {id_aukcije: '@id_aukcije'},
			isArray : true
		}

	})

});
services.factory('KategorijaFactory', function($resource) {
	return $resource(baseUrl + '/kategorija/:kategorija', {}, {
		query : {
			method : 'GET',
			params : {kategorija: '@kategorija'},
			isArray : true
		}

	})

});
services.factory('RegistracijaFactory', function($resource){
	return $resource(baseUrl+'/registracija',{},
	{
		registracija :{
			method: 'POST'
		}
			
})});
	
	

