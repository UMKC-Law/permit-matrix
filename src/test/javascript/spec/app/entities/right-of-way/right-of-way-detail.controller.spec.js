'use strict';

describe('Controller Tests', function() {

    describe('RightOfWay Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockRightOfWay, MockProject;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockRightOfWay = jasmine.createSpy('MockRightOfWay');
            MockProject = jasmine.createSpy('MockProject');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'RightOfWay': MockRightOfWay,
                'Project': MockProject
            };
            createController = function() {
                $injector.get('$controller')("RightOfWayDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'permitmeApp:rightOfWayUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
