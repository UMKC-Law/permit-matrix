(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('CityLicensesDialogController', CityLicensesDialogController);

    CityLicensesDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CityLicenses'];

    function CityLicensesDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CityLicenses) {
        var vm = this;

        vm.cityLicenses = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.cityLicenses.id !== null) {
                CityLicenses.update(vm.cityLicenses, onSaveSuccess, onSaveError);
            } else {
                CityLicenses.save(vm.cityLicenses, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('permitmeApp:cityLicensesUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
