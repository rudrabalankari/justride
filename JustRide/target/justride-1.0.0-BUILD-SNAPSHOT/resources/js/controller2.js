//var app = angular.module('app', [ 'ngRoute' ]);
//app.config(function($routeProvider) {
//	$routeProvider.when("/", {
//		templateUrl : "/home.jsp"
//	}).when("/login", {
//		templateUrl : "/login.jsp"
//	}).when("/register", {
//		templateUrl : "/register.jsp"
//	});
//});
//
//app.controller('getcontroller2', function($scope, $http, $location) {
//	$scope.test = [ {
//		"type" : "Condo",
//		"id" : "1"
//	}, {
//		"type" : "Condo",
//		"id" : "2"
//	} ];
//	$scope.getfunction = function() {
//		var url = $location.absUrl() + "getCars";
//		var config = {
//			headers : {
//				'Content-Type' : 'application/json;charset=utf-8;'
//			}
//		}
//
//		$http.get(url, config).then(function(response) {
//			$scope.response = response.data
//		}, function(response) {
//			$scope.getResultMessage = "Fail!";
//		});
//	}
//});
