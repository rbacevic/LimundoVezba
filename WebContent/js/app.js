'use strict';
var limundo = angular.module('limundo', ['ngRoute']).config(['$routeProvider',function($routeProvider) {
	$routeProvider.when('/', {templateUrl: 'pocetna.html', controller: 'RandomAukcijeCtrl'});
	$routeProvider.when('/pocetna', {templateUrl: 'pocetna.html', controller: 'RandomAukcijeCtrl'});

	$routeProvider.otherwise({redirectTo: '/'});
	
}]);


limundo.controller('KategorijeCtrl',[ '$scope', '$http', 
  function($scope,$http){ 
	$http.get('http://localhost:8080/Test/test/test/kategorije').success(function(data) {
 $scope.svekategorije= data;

})
 }]);
limundo.controller('RandomAukcijeCtrl',[ '$scope', '$http',
                                      function($scope,$http){ 
                                    	$http.get('http://localhost:8080/Test/test/test/randomaukcije').success(function(data) {
                                     $scope.aukcije= data;
                                    	})
                                 
                                     }]);




