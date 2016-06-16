(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .config(urlProviderConfig);

    urlProviderConfig.$inject = ['$urlRouterProvider'];

    function urlProviderConfig($urlRouterProvider){
    	var dynamicHome = ['$state', 'Principal', function($state, Principal){
	    	Principal.identity().then(function(profile){
	    		if(Principal.isAuthenticated()) {
	    			$state.go('project');
	    		} else {
	    			$state.go('home');
	    		}
	    	});
	    	return false;
	    }];
    	
	    $urlRouterProvider.when('', dynamicHome);
	    $urlRouterProvider.when('/', dynamicHome);
    }
})();
