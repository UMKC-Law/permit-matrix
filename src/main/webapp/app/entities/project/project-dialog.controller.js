(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('ProjectDialogController', ProjectDialogController);

    ProjectDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Project', 'Contractor', 'RightOfWay'];

    function ProjectDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Project, Contractor, RightOfWay) {
        var vm = this;

        vm.project = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.rightofways = RightOfWay.query();
        vm.wizardStep = 0;
        vm.wizardMax = 6;
        vm.back = back;
        vm.next = next;
        vm.wizardTitleList = [
            "General", 
            "Repair of Existing Line",
            "Install New Line",
            "Assessments",
            "Excavations",
            "New Construction",
            "Rights of Way"
        ];

        $timeout(function (){
            angular.element('.form-group:eq(0)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.project.id !== null) {
                Project.update(vm.project, onSaveSuccess, onSaveError);
            } else {
                Project.save(vm.project, onSaveSuccess, onSaveError);
            }
        }
        
        function back() {
        	if(vm.wizardStep > 0) {
        		vm.wizardStep--;
        	}
        }
        
        function next() {
        	//TODO - add validation
        	if(vm.wizardStep <= vm.wizardMax) {
        		vm.wizardStep++;
        	}
        }

        function onSaveSuccess (result) {
            $scope.$emit('permitmeApp:projectUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.addedDate = false;
        vm.datePickerOpenStatus.submittedDate = false;
        vm.datePickerOpenStatus.approvedDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
