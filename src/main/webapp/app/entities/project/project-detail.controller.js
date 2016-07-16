(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('ProjectDetailController', ProjectDetailController);

    ProjectDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Project', 'Contractor', 'RightOfWay'];

    function ProjectDetailController($scope, $rootScope, $stateParams, entity, Project, Contractor, RightOfWay) {
        var vm = this;

        vm.project = entity;
        vm.permitTypes = [];

        var unsubscribe = $rootScope.$on('permitmeApp:projectUpdate', function(event, result) {
            vm.project = result;
        });
        $scope.$on('$destroy', unsubscribe);
        loadPermitTypesForProject();
        

        function loadPermitTypesForProject() {
            Project.getPermitTypesForProject({id: vm.project.id}, function(result) {
                vm.permitTypes = result;
            });
        }
    }
})();
