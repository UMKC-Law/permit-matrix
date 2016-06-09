(function() {
    'use strict';

    angular
        .module('permitmeApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$rootScope', '$state', '$timeout', 'Auth', 'Contractor', '$uibModalInstance'];

    function LoginController ($rootScope, $state, $timeout, Auth, Contractor, $uibModalInstance) {
        var vm = this;

        vm.authenticationError = false;
        vm.cancel = cancel;
        vm.credentials = {};
        vm.login = login;
        vm.password = null;
        vm.register = register;
        vm.rememberMe = true;
        vm.requestResetPassword = requestResetPassword;
        vm.username = null;

        $timeout(function (){angular.element('#username').focus();});

        function cancel () {
            vm.credentials = {
                username: null,
                password: null,
                rememberMe: true
            };
            vm.authenticationError = false;
            $uibModalInstance.dismiss('cancel');
        }

        function login (event) {
            event.preventDefault();
            Auth.login({
                username: vm.username,
                password: vm.password,
                rememberMe: vm.rememberMe
            }).then(function () {
                vm.authenticationError = false;
                $uibModalInstance.close();

                $rootScope.$broadcast('authenticationSuccess');
                
                Contractor.getForUser(function(result) {
                    if(result != null && result != undefined) {
                    	$state.go('project');
                    }
                }, function(error) {
                	$state.go('contractor-profile');
                	console.log('Error encountered trying to retrieve a contractor the user: ');
                	console.log(error)
                });

                // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                // since login is succesful, go to stored previousState and clear previousState
//                if (Auth.getPreviousState()) {
//                    var previousState = Auth.getPreviousState();
//                    Auth.resetPreviousState();
//                    $state.go(previousState.name, previousState.params);
//                }
            }).catch(function () {
                vm.authenticationError = true;
            });
        }

        function register () {
            $uibModalInstance.dismiss('cancel');
            $state.go('register');
        }

        function requestResetPassword () {
            $uibModalInstance.dismiss('cancel');
            $state.go('requestReset');
        }
    }
})();
