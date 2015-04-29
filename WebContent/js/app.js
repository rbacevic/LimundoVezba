'use strict';
var limundo = angular.module('limundo', ['ngRoute']).config(['$routeProvider',function($routeProvider) {
	$routeProvider.when('/', {templateUrl: 'pocetna.html', controller: 'RandomAukcijeCtrl'});
	$routeProvider.when('/pocetna', {templateUrl: 'pocetna.html', controller: 'RandomAukcijeCtrl'});
	$routeProvider.when('/predmetlicitacije/:id_aukcije', {templateUrl: 'predmetlicitacije.html', controller: 'PrikazCtrl'});
	$routeProvider.when('/sveaukcije', {templateUrl: 'sveaukcije.html', controller: 'SveAukcijeCtrl'});
	$routeProvider.when('/:kategorija', {templateUrl: 'sveaukcije.html', controller:  'KatCtrl'});
	$routeProvider.otherwise({redirectTo: '/'});
	
}]);


limundo.controller('KategorijeCtrl',[ '$scope', '$http','$location',
  function($scope,$http,$location){ 
	$http.get('http://localhost:8080/Test/test/test/kategorije').success(function(data) {
 $scope.svekategorije= data;
	})
 $scope.onclick= function(kategorija){
		
  	$location.path('/'+kategorija);
  	
  	  

  	};

 }]);
limundo.controller('RandomAukcijeCtrl',[ '$scope', '$http','$location',
                                      function($scope,$http,$location){ 
                                    	$http.get('http://localhost:8080/Test/test/test/randomaukcije').success(function(data) {
                                     $scope.aukcije= data;
                                    	})
                                     $scope.onclick= function(id_aukcije){
                                    		
                                    	$location.path('/predmetlicitacije/'+id_aukcije);
                                    	
                                    	  
                                
                                    	};
                                 
}]);
limundo.controller('PrikazCtrl',[ '$scope', '$http','$routeParams', 
                                      function($scope,$http,$routeParams){ 
	$http.get('http://localhost:8080/Test/test/test/aukcija/'+$routeParams.id_aukcije).success(function(data) {
        $scope.aukcija= data
      
                                    })
                                     }]);


limundo.controller('SveAukcijeCtrl',[ '$scope', '$http','$location', 
                                      function($scope,$http,$location){ 
                                    	$http.get('http://localhost:8080/Test/test/test/aukcije').success(function(data) {
                                     $scope.sveaukcije= data;
                                     $scope.onclick= function(id_aukcije){
                                 		
                                     	$location.path('/predmetlicitacije/'+id_aukcije);
                                     	
                                     	  
                                 
                                     	};
                                    })
                                     }]);

limundo.controller('KatCtrl',[ '$scope', '$http','$routeParams','$location',
                                  function($scope,$http,$routeParams,$location){ 
$http.get('http://localhost:8080/Test/test/test/kategorija/'+$routeParams.kategorija).success(function(data) {
    $scope.sveaukcije= data
 
                                })
                                $scope.onclick= function(id_aukcije){
		
 	$location.path('/predmetlicitacije/'+id_aukcije);
 	
 	  

 	};
                                 }]);