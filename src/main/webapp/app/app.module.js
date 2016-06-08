(function() {
    'use strict';

    angular
        .module('permitmeApp', [
            'ngStorage', 
            'ngResource',
            'ngCookies',
            'ngAria',
            'ngCacheBuster',
            'ngFileUpload',
            'ui.bootstrap',
            'ui.bootstrap.datetimepicker',
            'ui.router',
            'infinite-scroll',
            // jhipster-needle-angularjs-add-module JHipster will add new module here
            'angular-loading-bar'
        ])
        .run(run);

    run.$inject = ['stateHandler', 'UiRouterConsole'];

    function run(stateHandler, UiRouterConsole) {
        stateHandler.initialize();
        UiRouterConsole.active = true;
    }
})();
