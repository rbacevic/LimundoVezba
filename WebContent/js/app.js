'use strict';
angular.module('limundo', ['limundo.controllers','limundo.services'])
.config(['$routeProvider',function($routeProvider) {
	$routeProvider.when('/', {templateUrl: 'pocetna.html', controller: 'RandomAukcijeCtrl'}).
	when('/registracija', {templateUrl: 'registracija.html', controller: 'RegCtrl'}).
	when('/login',{templateUrl:'login.html', controller: 'LoginController'}).
	when('/pocetna', {templateUrl: 'pocetna.html', controller: 'RandomAukcijeCtrl'}).
	when('/predmetlicitacije/:id_aukcije', {templateUrl: 'predmetlicitacije.html', controller: 'PrikazCtrl'}).
	when('/sveaukcije', {templateUrl: 'sveaukcije.html', controller: 'SveAukcijeCtrl'}).
	when('/:kategorija', {templateUrl: 'sveaukcije.html', controller:  'KatCtrl'}).
	otherwise({redirectTo: '/'});
	
}])
.constant('AUTH_EVENTS', {
  loginSuccess: 'auth-login-success',
  loginFailed: 'auth-login-failed',
  logoutSuccess: 'auth-logout-success',
  sessionTimeout: 'auth-session-timeout',
  notAuthenticated: 'auth-not-authenticated',
  notAuthorized: 'auth-not-authorized'
})
.constant('USER_ROLES', {
 // all: '*',
  admin: 'DA',
 // editor: 'editor',
  clan: 'NE'
});

