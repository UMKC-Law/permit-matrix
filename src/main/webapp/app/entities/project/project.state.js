(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('project', {
            parent: 'entity',
            url: '/project',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Projects'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project/projects.html',
                    controller: 'ProjectController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('project-detail', {
            parent: 'entity',
            url: '/project/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Project'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project/project-detail.html',
                    controller: 'ProjectDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Project', function($stateParams, Project) {
                    return Project.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('project.new', {
            parent: 'project',
            url: '/new/:contractorId',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', '$filter', function($stateParams, $state, $uibModal, $filter) {
                $uibModal.open({
                    templateUrl: 'app/entities/project/project-dialog.html',
                    controller: 'ProjectDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectName: null,
                                projectDescription: null,
                                projectStatus: 'INCOMPLETE',
                                addedDate: new Date(),
                                submittedDate: null,
                                approvedDate: null,
                                isWorkOnExistingPlumbing: null,
                                repairLocation: null,
                                customRepairLocation: null,
                                meterLocation: null,
                                isNewLine: null,
                                plumbingType: null,
                                customPlumbingType: null,
                                isConnectionAvailableFromProperty: null,
                                isConnectionReadyForHookup: null,
                                hasMasterSewerApprovalLetter: null,
                                platSubdivision: null,
                                hasAssessmentOrLien: null,
                                isAssessmentOrLienPaid: null,
                                workSiteType: null,
                                streetAddress: null,
                                zipCode: null,
                                city: null,
                                state: null,
                                includesExcavation: null,
                                excavationDescription: null,
                                excavationPermitNumber: null,
                                isNewConstruction: null,
                                newConstructionBuildingPermitNumber: null,
                                zoningDistrict: null,
                                occGroup: null,
                                constructionType: null,
                                structuralClass: null,
                                hasSitePlan: null,
                                controlNumber: null,
                                totalFloors: null,
                                grossBuildingArea: null,
                                totalDwellingUnits: null,
                                serviceLineLocation: null,
                                existingSewerMaterialDescription: null,
                                connectionType: null,
                                serviceLineSize: null,
                                sewerMainSize: null,
                                usesRightOfWay: null,
                                id: null,
                                contractor: {id: $stateParams.contractorId}
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('project', null, { reload: true });
                }, function() {
                    $state.go('project');
                });
            }]
        })
        .state('project.edit', {
            parent: 'project',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project/project-dialog.html',
                    controller: 'ProjectDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Project', function(Project) {
                            return Project.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project.delete', {
            parent: 'project',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project/project-delete-dialog.html',
                    controller: 'ProjectDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Project', function(Project) {
                            return Project.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
