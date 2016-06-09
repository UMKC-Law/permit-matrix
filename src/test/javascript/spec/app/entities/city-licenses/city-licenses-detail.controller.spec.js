'use strict';

describe('Controller Tests', function() {

    describe('CityLicenses Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockCityLicenses;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockCityLicenses = jasmine.createSpy('MockCityLicenses');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'CityLicenses': MockCityLicenses
            };
            createController = function() {
                $injector.get('$controller')("CityLicensesDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'permitmeApp:cityLicensesUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
