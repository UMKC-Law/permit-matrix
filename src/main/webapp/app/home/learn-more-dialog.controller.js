(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('LearnMoreDialogController', LearnMoreDialogController);

    LearnMoreDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance'];

    function LearnMoreDialogController ($timeout, $scope, $stateParams, $uibModalInstance) {
        var vm = this;
        vm.done = done;

        function done () {
        	console.log('entering learn-more-dialog-controller.done()');
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
