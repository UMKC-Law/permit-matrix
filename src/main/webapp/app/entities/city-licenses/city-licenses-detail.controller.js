(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('CityLicensesDetailController', CityLicensesDetailController);

    CityLicensesDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'CityLicenses'];

    function CityLicensesDetailController($scope, $rootScope, $stateParams, entity, CityLicenses) {
        var vm = this;

        vm.cityLicenses = entity;

        var unsubscribe = $rootScope.$on('permitmeApp:cityLicensesUpdate', function(event, result) {
            vm.cityLicenses = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
