'use strict';
angular.module('limundo', ['limundo.controllers','limundo.services'])
.config(['$routeProvider',function($routeProvider) {
	$routeProvider.when('/', {templateUrl: 'pocetna.html', controller: 'RandomAukcijeCtrl'});
	$routeProvider.when('/pocetna', {templateUrl: 'pocetna.html', controller: 'RandomAukcijeCtrl'});
	$routeProvider.when('/predmetlicitacije/:id_aukcije', {templateUrl: 'predmetlicitacije.html', controller: 'PrikazCtrl'});
	$routeProvider.when('/sveaukcije', {templateUrl: 'sveaukcije.html', controller: 'SveAukcijeCtrl'});
	$routeProvider.when('/:kategorija', {templateUrl: 'sveaukcije.html', controller:  'KatCtrl'});
	$routeProvider.when('/registracija', {templateUrl: 'registracija.html', controller: 'RegCtrl'});
	$routeProvider.otherwise({redirectTo: '/'});
	
}]);

