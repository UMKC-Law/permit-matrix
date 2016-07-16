(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('PermitPurposeDialogController', PermitPurposeDialogController);

    PermitPurposeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance'];

    function PermitPurposeDialogController ($timeout, $scope, $stateParams, $uibModalInstance) {
        var vm = this;
        vm.done = done;
        vm.permitName = $stateParams.permitName;
        vm.permitPurpose = $stateParams.permitPurpose;

        function done () {
        	console.log('entering permit-purpose-dialog-controller.done()');
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
