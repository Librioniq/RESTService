'use strict';

angular.module('librioniqApp')
    .factory('PostSearch', function ($resource) {
        return $resource('api/_search/posts/:query', {}, {
            'query': { method: 'GET', isArray: true}
        });
    });
