(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('RightOfWayDialogController', RightOfWayDialogController);

    RightOfWayDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RightOfWay', 'Project'];

    function RightOfWayDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RightOfWay, Project) {
        var vm = this;

        vm.rightOfWay = entity;
        vm.clear = clear;
        vm.save = save;
        vm.projects = Project.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.rightOfWay.id !== null) {
                RightOfWay.update(vm.rightOfWay, onSaveSuccess, onSaveError);
            } else {
                RightOfWay.save(vm.rightOfWay, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('permitmeApp:rightOfWayUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
