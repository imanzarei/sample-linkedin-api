var appCtrl = angular.module("LinkedinController", []);
appCtrl.controller('profileShowCtrl', ['$scope', 'ProfileService',
    function ($scope, ProfileService) {
        $scope.profile = ProfileService.query();
    }]);