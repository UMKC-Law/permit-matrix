(function() {
    'use strict';
    angular
        .module('permitmeApp')
        .factory('RightOfWay', RightOfWay);

    RightOfWay.$inject = ['$resource'];

    function RightOfWay ($resource) {
        var resourceUrl =  'api/right-of-ways/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
