'use strict'

var limundoServices = angular.module('limundo.services', ['ngResource']);

limundoServices.factory('Kategorije', [ '$scope', '$http', 
		function($scope,$http){ 
	$http.get('http://localhost:8080/Test/test/test/kategorije').success(function(data) {
		
		return data;
		
	})
	
	
	
}]);