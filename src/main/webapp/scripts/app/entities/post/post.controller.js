'use strict';

angular.module('librioniqApp')
    .controller('PostController', function ($scope, $state, Post, PostSearch) {

        $scope.posts = [];
        $scope.loadAll = function() {
            Post.query(function(result) {
               $scope.posts = result;
            });
        };
        $scope.loadAll();


        $scope.search = function () {
            PostSearch.query({query: $scope.searchQuery}, function(result) {
                $scope.posts = result;
            }, function(response) {
                if(response.status === 404) {
                    $scope.loadAll();
                }
            });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.post = {
                content: null,
                type: null,
                id: null
            };
        };
    });
