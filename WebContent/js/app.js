'use strict';
angular.module('limundo', ['limundo.controllers','limundo.services'])
.config(['$routeProvider',function($routeProvider) {
	$routeProvider.when('/', {templateUrl: 'pocetna.html', controller: 'RandomAukcijeCtrl'}).
	when('/registracija', {templateUrl: 'registracija.html', controller: 'RegCtrl'}).
	when('/pocetna', {templateUrl: 'pocetna.html', controller: 'RandomAukcijeCtrl'}).
	when('/predmetlicitacije/:id_aukcije', {templateUrl: 'predmetlicitacije.html', controller: 'PrikazCtrl'}).
	when('/sveaukcije', {templateUrl: 'sveaukcije.html', controller: 'SveAukcijeCtrl'}).
	when('/:kategorija', {templateUrl: 'sveaukcije.html', controller:  'KatCtrl'}).
	
	otherwise({redirectTo: '/'});
	
}]);

