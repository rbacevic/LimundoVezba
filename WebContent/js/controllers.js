'use strict';

var app = angular.module('limundo.controllers', [ 'ngRoute' ]);

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
	RegistracijaFactory.create($scope.clan);
	
	$location.path('/pocetna');
	}
	
	

} ]);
