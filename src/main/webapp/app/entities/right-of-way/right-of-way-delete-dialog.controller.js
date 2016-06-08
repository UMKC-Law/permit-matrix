(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('RightOfWayDeleteController',RightOfWayDeleteController);

    RightOfWayDeleteController.$inject = ['$uibModalInstance', 'entity', 'RightOfWay'];

    function RightOfWayDeleteController($uibModalInstance, entity, RightOfWay) {
        var vm = this;

        vm.rightOfWay = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RightOfWay.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
