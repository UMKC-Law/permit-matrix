(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('right-of-way', {
            parent: 'entity',
            url: '/right-of-way',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RightOfWays'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/right-of-way/right-of-ways.html',
                    controller: 'RightOfWayController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('right-of-way-detail', {
            parent: 'entity',
            url: '/right-of-way/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RightOfWay'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/right-of-way/right-of-way-detail.html',
                    controller: 'RightOfWayDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'RightOfWay', function($stateParams, RightOfWay) {
                    return RightOfWay.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('right-of-way.new', {
            parent: 'right-of-way',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/right-of-way/right-of-way-dialog.html',
                    controller: 'RightOfWayDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                rightOfWayType: null,
                                closureBoundaries: null,
                                proposedDetour: null,
                                rightOfWayName: null,
                                closureDurationDays: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('right-of-way', null, { reload: true });
                }, function() {
                    $state.go('right-of-way');
                });
            }]
        })
        .state('right-of-way.edit', {
            parent: 'right-of-way',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/right-of-way/right-of-way-dialog.html',
                    controller: 'RightOfWayDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RightOfWay', function(RightOfWay) {
                            return RightOfWay.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('right-of-way', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('right-of-way.delete', {
            parent: 'right-of-way',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/right-of-way/right-of-way-delete-dialog.html',
                    controller: 'RightOfWayDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RightOfWay', function(RightOfWay) {
                            return RightOfWay.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('right-of-way', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
