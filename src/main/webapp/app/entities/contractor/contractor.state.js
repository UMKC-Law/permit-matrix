(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
//        .state('contractor', {
//            parent: 'entity',
//            url: '/contractor',
//            data: {
//                authorities: ['ROLE_USER'],
//                pageTitle: 'Contractors'
//            },
//            views: {
//                'content@': {
//                    templateUrl: 'app/entities/contractor/contractors.html',
//                    controller: 'ContractorController',
//                    controllerAs: 'vm'
//                }
//            },
//            resolve: {
//            }
//        })
        .state('contractor-detail', {
            parent: 'entity',
            url: '/contractor/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Contractor'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contractor/contractor-detail.html',
                    controller: 'ContractorDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Contractor', function($stateParams, Contractor) {
                    return Contractor.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('contractor-profile', {
            parent: 'entity',
            url: '/contractor-profile',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Your Profile'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contractor/contractor-main.html',
                    controller: 'ContractorMainController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: function () {
                    return {
                        businessName: null,
                        businessLicenseNumber: null,
                        phoneNumber: null,
                        streetAddress: null,
                        zipCode: null,
                        city: null,
                        state: null,
                        agentFirstName: null,
                        agentMiddleInitial: null,
                        agentLastName: null,
                        agentJobTitle: null,
                        email: null,
                        agentPhoneNumber: null,
                        contractLicenseNumber: null,
                        occupationalLicenseNumber: null,
                        masterPlumberLicenseNumber: null,
                        hasGeneralLiabilityCoverage: null,
                        requirementsMet: null,
                        carrier: null,
                        policyNumber: null,
                        id: null
                    };
                }
            }
        })
        .state('contractor.new', {
            parent: 'contractor',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contractor/contractor-dialog.html',
                    controller: 'ContractorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                businessName: null,
                                businessLicenseNumber: null,
                                phoneNumber: null,
                                streetAddress: null,
                                zipCode: null,
                                city: null,
                                state: null,
                                agentFirstName: null,
                                agentMiddleInitial: null,
                                agentLastName: null,
                                agentJobTitle: null,
                                email: null,
                                agentPhoneNumber: null,
                                contractLicenseNumber: null,
                                occupationalLicenseNumber: null,
                                masterPlumberLicenseNumber: null,
                                hasGeneralLiabilityCoverage: null,
                                requirementsMet: null,
                                carrier: null,
                                policyNumber: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('contractor', null, { reload: true });
                }, function() {
                    $state.go('contractor');
                });
            }]
        })
        .state('contractor.edit', {
            parent: 'contractor',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contractor/contractor-dialog.html',
                    controller: 'ContractorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Contractor', function(Contractor) {
                            return Contractor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contractor', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contractor.delete', {
            parent: 'contractor',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contractor/contractor-delete-dialog.html',
                    controller: 'ContractorDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Contractor', function(Contractor) {
                            return Contractor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contractor', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
