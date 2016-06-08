(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('RightOfWayDetailController', RightOfWayDetailController);

    RightOfWayDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'RightOfWay', 'Project'];

    function RightOfWayDetailController($scope, $rootScope, $stateParams, entity, RightOfWay, Project) {
        var vm = this;

        vm.rightOfWay = entity;

        var unsubscribe = $rootScope.$on('permitmeApp:rightOfWayUpdate', function(event, result) {
            vm.rightOfWay = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
