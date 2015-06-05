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

services.factory('AuthService', function ($http, Session) {
	  var authService = {};
	  
	  authService.login = function (credentials) {
	    return $http
	      .post(baseUrl+'/login', credentials)
	      .then(function (res) {
	        Session.create(res.data.id, res.data.user.id,
	                       res.data.user.role);
	        return res.data.user;
	      });
	  };
	 
	  authService.isAuthenticated = function () {
	    return !!Session.userId;
	  };
	 
	  authService.isAuthorized = function (authorizedRoles) {
	    if (!angular.isArray(authorizedRoles)) {
	      authorizedRoles = [authorizedRoles];
	    }
	    return (authService.isAuthenticated() &&
	      authorizedRoles.indexOf(Session.userRole) !== -1);
	  };
	 
	  return authService;
	});

services.service('Session', function () {
	  this.create = function (sessionId, userId, userRole) {
		    this.id = sessionId;
		    this.userId = userId;
		    this.userRole = userRole;
		  };
		  this.destroy = function () {
		    this.id = null;
		    this.userId = null;
		    this.userRole = null;
		  };
		})
	

