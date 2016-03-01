'use strict';

angular.module('librioniqApp')
	.controller('PostDeleteController', function($scope, $uibModalInstance, entity, Post) {

        $scope.post = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Post.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
