'use strict';

angular.module('librioniqApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


