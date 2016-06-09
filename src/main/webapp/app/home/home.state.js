(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
            parent: 'app',
            url: '/',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('home.learn-more', {
            parent: 'home',
            url: '/learn-more',
            data: {
                authorities: []
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/home/learn-more-dialog.html',
                    controller: 'LearnMoreDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'md',
                    resolve: {}
                }).result.then(function() {
                    $state.go('city-licenses', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }
})();
