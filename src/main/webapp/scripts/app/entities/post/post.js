'use strict';

angular.module('librioniqApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('post', {
                parent: 'entity',
                url: '/posts',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'librioniqApp.post.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/post/posts.html',
                        controller: 'PostController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('post');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('post.detail', {
                parent: 'entity',
                url: '/post/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'librioniqApp.post.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/post/post-detail.html',
                        controller: 'PostDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('post');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Post', function($stateParams, Post) {
                        return Post.get({id : $stateParams.id});
                    }]
                }
            })
            .state('post.new', {
                parent: 'post',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/post/post-dialog.html',
                        controller: 'PostDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    content: null,
                                    type: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('post', null, { reload: true });
                    }, function() {
                        $state.go('post');
                    })
                }]
            })
            .state('post.edit', {
                parent: 'post',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/post/post-dialog.html',
                        controller: 'PostDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Post', function(Post) {
                                return Post.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('post', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('post.delete', {
                parent: 'post',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/post/post-delete-dialog.html',
                        controller: 'PostDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Post', function(Post) {
                                return Post.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('post', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
