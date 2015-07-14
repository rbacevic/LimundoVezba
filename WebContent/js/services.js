'use strict';
var services = angular.module('limundo.services', [ 'ngResource', 'ngStorage','ngCookies']);
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

services.factory('AuthService',['$http','$cookies',function ($http,$cookies /*, Session*/) {
	  var authService = {};
	  
	  authService.login = function (credentials) {
	    return $http
	      .post(baseUrl+'/login', credentials)
	      .then(function (res) {
	       
	     if(res.data!=null){
	    	  var expDate = new Date();
	    	  expDate.setMinutes(expDate.getMinutes()+1);
	    	  $cookies.put('userObj', JSON.stringify(res.data), {'expires': expDate});
	    	  return res.data;
	     }else{
	    	 return null;
	     }
	    	 
	    	  
	    	  
	    	  
	    	  /*var sesija=Session.create(res.data.id_clana, res.data.id_clana,
	                       res.data.administrator);
	    	sessionStorage.setItem("sess", JSON.stringify(sesija));*/
	      
	    	  
	    	  
	    	  
	    	  
	    	 
	    
	      }); 
	  };
	 
	 /* authService.isAuthenticated = function () {
	    return !!Session.userId;
	  };
	 
	  authService.isAuthorized = function (authorizedRoles) {
	    if (!angular.isArray(authorizedRoles)) {
	      authorizedRoles = [authorizedRoles];
	    }
	    return (authService.isAuthenticated() &&
	      authorizedRoles.indexOf(Session.userRole) !== -1);
	  };*/
	 
	  return authService;
	}]);




















/*services.service('Session', function () {
	
	
	
	this.create = function (sessionId, userId, userRole) {
		
	 this.id = sessionId;
	    this.userID = userId;
	    this.userROLE = userRole;
	    
	   
	   
		  };
		  this.destroy = function () {
			    var sesija= {
						 id: null,
						 userID: null,
						 userRole: null
				   }
			   
			  
		  };
		 
		  
		})*/
	

