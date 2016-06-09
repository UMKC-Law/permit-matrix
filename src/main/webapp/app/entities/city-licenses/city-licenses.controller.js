(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('CityLicensesController', CityLicensesController);

    CityLicensesController.$inject = ['$scope', '$state', 'CityLicenses'];

    function CityLicensesController ($scope, $state, CityLicenses) {
        var vm = this;
        
        vm.cityLicenses = [];

        loadAll();

        function loadAll() {
            CityLicenses.query(function(result) {
                vm.cityLicenses = result;
            });
        }
    }
})();
