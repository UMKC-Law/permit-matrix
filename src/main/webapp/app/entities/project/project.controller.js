(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('ProjectController', ProjectController);

    ProjectController.$inject = ['$scope', '$state', 'Project', 'Contractor'];

    function ProjectController ($scope, $state, Project, Contractor) {
        var vm = this;
        vm.contractor = {};
        vm.projects = [];

        loadData();
        
        function loadData() {
        	// get the active contractor - there is 1 per user
            Contractor.getForUser(function(result) {
                if(result != null && result != undefined) {
                	vm.contractor = result;
                	// get the projects for the active contractor
                	loadProjectsForContractor(vm.contractor.id);
                }
            }, function(error) {
            	console.log('Error encountered trying to retrieve current contractor');
            	console.log(error)
            });
        	
        }

        function loadProjectsForContractor(contractorId) {
            Project.getProjectsForContractor({contractorId: contractorId}, function(result) {
                vm.projects = result;
            });
        }
    }
})();
