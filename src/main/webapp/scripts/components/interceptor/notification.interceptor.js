 'use strict';

angular.module('librioniqApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-librioniqApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-librioniqApp-params')});
                }
                return response;
            }
        };
    });
