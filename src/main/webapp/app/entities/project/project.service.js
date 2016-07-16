(function() {
    'use strict';
    angular
        .module('permitmeApp')
        .factory('Project', Project);

    Project.$inject = ['$resource', 'DateUtils'];

    function Project ($resource, DateUtils) {
        var resourceUrl =  'api/projects/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'getProjectsForContractor': { 
            	method: 'GET',
            	url: 'api/projects/contractor/:contractorId',
            	isArray: true
            },
            'getPermitTypesForProject': { 
            	method: 'GET',
            	url: 'api/projects/:id/permit-types',
            	isArray: true
            },
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.addedDate = DateUtils.convertLocalDateFromServer(data.addedDate);
                        data.submittedDate = DateUtils.convertLocalDateFromServer(data.submittedDate);
                        data.approvedDate = DateUtils.convertLocalDateFromServer(data.approvedDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.addedDate = DateUtils.convertLocalDateToServer(data.addedDate);
                    data.submittedDate = DateUtils.convertLocalDateToServer(data.submittedDate);
                    data.approvedDate = DateUtils.convertLocalDateToServer(data.approvedDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.addedDate = DateUtils.convertLocalDateToServer(data.addedDate);
                    data.submittedDate = DateUtils.convertLocalDateToServer(data.submittedDate);
                    data.approvedDate = DateUtils.convertLocalDateToServer(data.approvedDate);
                    return angular.toJson(data);
                }
            }
        });
    }
})();
