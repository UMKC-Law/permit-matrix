(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('ContractorDetailController', ContractorDetailController);

    ContractorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Contractor', 'Project', 'User'];

    function ContractorDetailController($scope, $rootScope, $stateParams, entity, Contractor, Project, User) {
        var vm = this;

        vm.contractor = entity;

        var unsubscribe = $rootScope.$on('permitmeApp:contractorUpdate', function(event, result) {
            vm.contractor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
