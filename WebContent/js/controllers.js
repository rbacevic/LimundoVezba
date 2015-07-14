'use strict';

var app = angular.module('limundo.controllers', [ 'ngRoute', 'ngStorage','ngCookies']);

app.run(function($rootScope, $templateCache) {
	$rootScope.$on('$viewContentLoaded', function() {
		$templateCache.removeAll();
	});
});

var baseUrl = 'http://localhost:8080/Test/test/test';
app.controller('KategorijeCtrl', [ '$scope', 'SveKategorijeFactory',
		'$location', '$rootScope',
		function($scope, SveKategorijeFactory, $location, $rootScope) {
			$scope.svekategorije = SveKategorijeFactory.query();
			$scope.onclick = function(kategorija) {

				$location.path('/' + kategorija);

			};

		} ]);
app.controller('RandomAukcijeCtrl', [ '$scope', 'RandomAukcijeFactory',
		'$location', '$rootScope',
		function($scope, RandomAukcijeFactory, $location, $rootScope) {

			$scope.aukcije = RandomAukcijeFactory.query();
			$scope.onclick = function(id_aukcije) {

				$location.path('/predmetlicitacije/' + id_aukcije);

			};

		} ]);
app.controller('PrikazCtrl', [ '$scope', 'PrikazAukcijeFactory',
		'$routeParams', '$rootScope',
		function($scope, PrikazAukcijeFactory, $routeParams, $rootScope) {

			$scope.aukcija = PrikazAukcijeFactory.query({
				id_aukcije : $routeParams.id_aukcije
			});

		} ]);

app.controller('SveAukcijeCtrl', [ '$scope', 'SveAukcijeFactory', '$location',
		function($scope, SveAukcijeFactory, $location) {
			$scope.sveaukcije = SveAukcijeFactory.query();

			$scope.onclick = function(id_aukcije) {

				$location.path('/predmetlicitacije/' + id_aukcije);

			};

		} ]);

app.controller('KatCtrl',
		[
				'$scope',
				'KategorijaFactory',
				'$routeParams',
				'$location',
				'$rootScope',
				function($scope, KategorijaFactory, $routeParams, $location,
						$rootScope) {
					$scope.sveaukcije = KategorijaFactory.query({
						kategorija : $routeParams.kategorija
					});
					$scope.onclick = function(id_aukcije) {

						$location.path('/predmetlicitacije/' + id_aukcije);

					};

				} ]);

app.controller('RegCtrl', [ '$scope','RegistracijaFactory','$location',

function($scope,RegistracijaFactory,$location) {
	
	
	
	$scope.registracija= function(){
	
	RegistracijaFactory.registracija($scope.clan);
	
	$location.path('/pocetna');
	};
	
	

} ]);


app.controller('LoginController', function ($scope, $rootScope, /*AUTH_EVENTS,*/$cookies, AuthService) {
	  $scope.credentials = {
			    username: '',
			    password: ''
			  };
	  
		  $scope.login = function (credentials) {
			    AuthService.login(credentials).then(function (user) {
			    	if($cookies.get('userObj')!=null){
			    		$scope.currentUser=JSON.parse($cookies.get('userObj'));
			    	}else{
			    		$scope.currentUser=null ;
			    	}
			    		
			    		
			    	
			    	
			 /*     $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
			      $scope.setCurrentUser(user);
			      
			      
			      if(user!=null){
			    		
			    	}else{
			    		window.alert("Greska");
			    	}
			    	
			      
			      
			    }, function () {
			      $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
			    });*/
			  });
			}});


app.controller('ApplicationController', function ($scope,$cookies
		
		/*,
        USER_ROLES,
        AuthService,
        $sessionStorage*/) {
	if($cookies.get('userObj')!=null){
	$scope.currentUser=JSON.parse($cookies.get('userObj'));
}else{
	$scope.currentUser=null ;
}
	
	

		
	
	 
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
/*$scope.session = JSON.parse((sessionStorage.getItem('sess')));

$scope.userRoles = USER_ROLES;
$scope.isAuthorized = AuthService.isAuthorized;

$scope.setCurrentUser = function (user) {
	
$scope.currentUser = user;
$scope.userRoles = user[0].administrator;

};*/
});