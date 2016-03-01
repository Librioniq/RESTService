'use strict';

angular.module('librioniqApp').controller('PostDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Post',
        function($scope, $stateParams, $uibModalInstance, entity, Post) {

        $scope.post = entity;
        $scope.load = function(id) {
            Post.get({id : id}, function(result) {
                $scope.post = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('librioniqApp:postUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.post.id != null) {
                Post.update($scope.post, onSaveSuccess, onSaveError);
            } else {
                Post.save($scope.post, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
