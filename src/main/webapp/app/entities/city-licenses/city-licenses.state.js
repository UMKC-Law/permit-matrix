(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('city-licenses', {
            parent: 'entity',
            url: '/city-licenses',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CityLicenses'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/city-licenses/city-licenses.html',
                    controller: 'CityLicensesController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('city-licenses-detail', {
            parent: 'entity',
            url: '/city-licenses/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CityLicenses'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/city-licenses/city-licenses-detail.html',
                    controller: 'CityLicensesDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'CityLicenses', function($stateParams, CityLicenses) {
                    return CityLicenses.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('city-licenses.new', {
            parent: 'city-licenses',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/city-licenses/city-licenses-dialog.html',
                    controller: 'CityLicensesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                company_name: null,
                                business_address: null,
                                city: null,
                                state: null,
                                zip_code: null,
                                business_phone: null,
                                contractor_license_number: null,
                                license_expiration_date: null,
                                supervisor_last_name: null,
                                supervisor_first_name: null,
                                supervisor_middle_name: null,
                                master_craftsman_certificate_number: null,
                                certificate_expiration_date: null,
                                business_license_expiration_date: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('city-licenses', null, { reload: true });
                }, function() {
                    $state.go('city-licenses');
                });
            }]
        })
        .state('city-licenses.edit', {
            parent: 'city-licenses',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/city-licenses/city-licenses-dialog.html',
                    controller: 'CityLicensesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CityLicenses', function(CityLicenses) {
                            return CityLicenses.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('city-licenses', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('city-licenses.delete', {
            parent: 'city-licenses',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/city-licenses/city-licenses-delete-dialog.html',
                    controller: 'CityLicensesDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CityLicenses', function(CityLicenses) {
                            return CityLicenses.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('city-licenses', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
