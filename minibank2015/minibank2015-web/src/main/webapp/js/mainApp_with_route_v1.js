var myAjsApp = angular.module('myAjsApp', [ 'ngRoute','myAjsControllers']);

// app module 'myAjsApp' depends on  [ 'ngRoute','myAjsControllers']  other modules

myAjsApp.config(['$routeProvider',
  function($routeProvider) {
      $routeProvider.
	when('/comptes/:numCli', {
		templateUrl: 'partials_v1/compte_list_view.html',
		controller: 'CompteListCtrl'
	}).
	when('/virement/:numCli', {
		templateUrl: 'partials_v1/param_transfert_view.html',
		controller: 'TransfertCtrl'
	}).
	when('/welcome', {
		templateUrl: 'partials_v1/welcome_view.html',
		controller: 'MyDefaultCtrl'
	}).
	otherwise({
		redirectTo: '/welcome'
	});
   }]);

// when local relative url '/welcome' ,  '/comptes/:numCli' (in web browser) will be
// interpreted by Angular-js and ngRoute module (with $route and $routeParams services) ,
// partial template will be loaded in <div ng-view> and specific controller will be associated

 