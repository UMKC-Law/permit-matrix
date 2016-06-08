(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('ContractorController', ContractorController);

    ContractorController.$inject = ['$scope', '$state', 'Contractor'];

    function ContractorController ($scope, $state, Contractor) {
        var vm = this;
        
        vm.contractors = [];

        loadAll();

        function loadAll() {
            Contractor.query(function(result) {
                vm.contractors = result;
            });
        }
    }
})();
