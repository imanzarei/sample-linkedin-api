/* Services */

var phonecatServices = angular.module('phonecatServices', ['ngResource']);

phonecatServices.factory('ProfileService', ['$resource',
    function ($resource) {
        return $resource('showProfile', {}, {
            query: {method: 'GET', isArray: false}
        });
    }]);