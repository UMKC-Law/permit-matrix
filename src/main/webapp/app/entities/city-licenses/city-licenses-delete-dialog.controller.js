(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('CityLicensesDeleteController',CityLicensesDeleteController);

    CityLicensesDeleteController.$inject = ['$uibModalInstance', 'entity', 'CityLicenses'];

    function CityLicensesDeleteController($uibModalInstance, entity, CityLicenses) {
        var vm = this;

        vm.cityLicenses = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CityLicenses.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
