(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('ContractorMainController', ContractorMainController);

    ContractorMainController.$inject = ['$timeout', '$scope', '$state', '$stateParams', '$q', 'entity', 'Contractor', 'Project', 'CityLicenses'];

    function ContractorMainController ($timeout, $scope, $state, $stateParams, $q, entity, Contractor, Project, CityLicenses) {
        var vm = this;

        vm.contractor = entity;
        vm.clear = clear;
        vm.save = save;
        vm.projects = Project.query();
        vm.licenseInfo = {};
        vm.getLicenseInformationForContractor = getLicenseInformationForContractor;

        $timeout(function (){
            angular.element('.form-group:eq(0)>input').focus();
        });
        
        
        function getLicenseInformationForContractor(event) {
        	CityLicenses.getCityLicenseForContractorLicense({licenseNumber: event.target.value}, function(result) {
                vm.licenseInfo = result;
                updateContractorWithCityLicenseInfo();
            });
        }
        
        function updateContractorWithCityLicenseInfo() {
        	vm.contractor.businessName = vm.licenseInfo.company_name;
        	vm.contractor.phone = vm.licenseInfo.business_phone;
        	vm.contractor.streetAddress = vm.licenseInfo.business_address;
        	vm.contractor.city = vm.licenseInfo.city;
        	vm.contractor.state = vm.licenseInfo.state;
        	vm.contractor.zipCode = vm.licenseInfo.zip_code;
        	vm.contractor.agentFirstName = vm.licenseInfo.supervisor_first_name;
        	vm.contractor.agentMiddleInitial = vm.licenseInfo.supervisor_middle_name;
        	vm.contractor.agentLastName = vm.licenseInfo.supervisor_last_name;
        	vm.contractor.masterPlumberLicenseNumber = vm.licenseInfo.master_craftsman_certificate_number;
        }
        

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
