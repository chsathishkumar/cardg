var cardg = angular.module('cardg', [ 'ngRoute', 'ngResource', 'ui.router',
		'ui.bootstrap', 'ngAnimate', 'ngMaterial' ]);

cardg.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : "index.html",
		controller : "cardgController"
	}).when('/dealer', {
		templateUrl : "views/dealer.html",
		controller : "cardgController"
	}).otherwise({
		redirectTo : '/dealer'
	});
});

cardg.directive("footer", function() {
	return {
		restrict : 'A',
		templateUrl : 'views/footer.html',
		scope : true,
		transclude : false,
		controller : 'loginController'
	};
});

cardg.filter('dateformat', function($filter) {
	return function(input) {
		if (input == null) {
			return "";
		}
		var _date = $filter('date')(new Date(input), 'dd-MM-yyyy');
		return _date.toUpperCase();
	};
});