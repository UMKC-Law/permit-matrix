'use strict';

describe('Controller Tests', function() {

    describe('Licensing Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockLicensing;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockLicensing = jasmine.createSpy('MockLicensing');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Licensing': MockLicensing
            };
            createController = function() {
                $injector.get('$controller')("LicensingDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'permitmeApp:licensingUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
