/**
 * @author vkallada
 * Js for Driver history module initialisation
 */
"use strict";

console.log(":: Driver history module initiated ::");

var driverHistoryMod = angular.module("DriverHistoryMod", ['ui.router', 'gm']);

driverHistoryMod.config(function($stateProvider){
	$stateProvider.state('driverHistory',{
		url:'/driverHistory',
		templateUrl:'./js/history/driverHistory/driverHistory.html',
		controller: 'DriverHistoryCtrl'
	})

});
