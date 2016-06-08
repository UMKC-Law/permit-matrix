(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('ContractorMainController', ContractorMainController);

    ContractorMainController.$inject = ['$timeout', '$scope', '$state', '$stateParams', '$q', 'entity', 'Contractor', 'Project'];

    function ContractorMainController ($timeout, $scope, $state, $stateParams, $q, entity, Contractor, Project) {
        var vm = this;

        vm.contractor = entity;
        vm.clear = clear;
        vm.save = save;
        vm.projects = Project.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        	console.log('clear called');
            $state.go('contractor', null, { reload: true });
        }

        function save () {
            vm.isSaving = true;
            if (vm.contractor.id !== null) {
                Contractor.update(vm.contractor, onSaveSuccess, onSaveError);
            } else {
                Contractor.save(vm.contractor, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('permitmeApp:contractorUpdate', result);
            vm.isSaving = false;
            $state.go('project', null, { reload: true });
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
