'use strict';

angular.module('librioniqApp')
    .controller('PostDetailController', function ($scope, $rootScope, $stateParams, entity, Post) {
        $scope.post = entity;
        $scope.load = function (id) {
            Post.get({id: id}, function(result) {
                $scope.post = result;
            });
        };
        var unsubscribe = $rootScope.$on('librioniqApp:postUpdate', function(event, result) {
            $scope.post = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
