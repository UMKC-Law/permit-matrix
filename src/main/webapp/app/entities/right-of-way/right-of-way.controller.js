(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('RightOfWayController', RightOfWayController);

    RightOfWayController.$inject = ['$scope', '$state', 'RightOfWay'];

    function RightOfWayController ($scope, $state, RightOfWay) {
        var vm = this;
        
        vm.rightOfWays = [];

        loadAll();

        function loadAll() {
            RightOfWay.query(function(result) {
                vm.rightOfWays = result;
            });
        }
    }
})();
