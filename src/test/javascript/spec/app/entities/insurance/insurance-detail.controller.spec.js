'use strict';

describe('Controller Tests', function() {

    describe('Insurance Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockInsurance;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockInsurance = jasmine.createSpy('MockInsurance');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Insurance': MockInsurance
            };
            createController = function() {
                $injector.get('$controller')("InsuranceDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'permitmeApp:insuranceUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
