angular.module('leagueOfComperors', ['ngRoute']).config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'login.html',
        controller: 'main',
        controllerAs: 'controller'
    }).when('/home', {
        templateUrl: 'home.html',
        controller: 'main',
        controllerAs: 'controller'
    }).when('/register', {
        templateUrl: 'register.html',
        controller: 'main',
        controllerAs: 'controller'
    }).when('/stats', {
        templateUrl: 'statistics.html',
        controller: 'statCtrl',
        controllerAs: 'controller'
    }).when('/compare', {
        templateUrl: 'compare.html',
        controller: 'compareCtrl',
        controllerAs: 'controller'
    }).when('/userConfig', {
        templateUrl: 'userConfig.html',
        controller: 'main',
        controllerAs: 'controller'
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('main', ['$scope', '$http', '$location', '$route', '$rootScope',

    function ($scope, $http, $location, $route, $rootScope) {

        var self = this;

        self.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };

        var authenticate = function (credentials, callback) {

            var headers = credentials ? {
                authorization: "Basic "
                + btoa(credentials.username + ":"
                    + credentials.password)
            } : {};

            $http.get('user', {
                headers: headers
            }).then(function (response) {
                if (response.data.name) {
                    $rootScope.authenticated = true;
                    $rootScope.userName = response.data.name;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback($rootScope.authenticated);
            }, function () {
                $rootScope.authenticated = false;
                callback && callback(false);
            });

        };

        authenticate();

        self.credentials = {};
        self.login = function () {
            authenticate(self.credentials, function (authenticated) {
                if (authenticated) {
                    console.log("Login succeeded"+self.credentials);
                    console.log("Login succeeded"+JSON.stringify(self.credentials));

                    $location.path("/home");
                    self.error = false;
                    $rootScope.authenticated = true;
                } else {
                    console.log("Login failed");
                    $location.path("/login");
                    self.error = true;
                    $rootScope.authenticated = false;
                }
            })
        };

        self.logout = function () {
            $http.post('logout', {}).finally(function () {
                $rootScope.authenticated = false;
                $location.path("/");
            });
        };

        self.register = function () {
            $http.post('/user/register', self.credentials).success(function (data, status) {
                $location.path("/");
                console.log("Registration succeeded")
            }).error(function (data, status) {
                console.log("Registration failure")
            });
        };

        self.setApiKey = function () {
            $http.post('/riot/setapikey', self.apikey).success(function (data, status) {
                console.log("Generate ApiKey success ");
                $rootScope.isApiKeySet = true;
            }).error(function (data, status) {
                $rootScope.isApiKeySet = false;
                console.log("Generate ApiKey failure " + status)
            });
        };

        self.isApiKeySet = function () {
            $http.get('/riot/isapikeyset').success(function (data, status) {
                $rootScope.isApiKeySet = true;
            }).error(function (data, status) {
                $rootScope.isApiKeySet = false;
            });
        };

        self.goToStats = function () {
            console.log("gotostats");
            $location.path("/stats");
        };

        self.goToComparison = function () {
            console.log("gotocomparison");
            $location.path("/compare");
        };

        self.goToUser = function () {
            $location.path("/userConfig");
        }
        self.isApiKeySet();
    }]).controller('home', '$http', '$scope',

    function ($http, $scope) {
        var scope = $scope;
        $http.get('/resource/').then(function (response) {
            scope.greeting = response.data;
        })
    }).controller('statCtrl', ['$scope', '$http', '$location', '$route', '$rootScope', function ($scope, $http, $location, $route, $rootScope) {
    var scope = $scope;
    console.log(scope);

    scope.refreshStats = function () {
        $http.post('/riot/refreshstats', {}).success(function (data, status) {
            console.log("Refresh Stats success ");
            scope.getStats();
        }).error(function (data, status) {
            console.log("Refresh Stats failure " + status)
        });
    };

    scope.getStats = function () {
        $http.get('/riot/getstats').success(function (data, status) {
            $rootScope.userData = data;
            $rootScope.isApiKeySet = true;
            console.log("Get Stats success ");
            console.log(JSON.stringify(data));
        }).error(function (data, status) {
            $rootScope.isApiKeySet = false;
            console.log("Get Stats failure " + status)
        });
    };

    scope.getLastRefresh = function () {
        $http.get('/riot/getlastrefresh').success(function (data, status) {
            $rootScope.lastRefresh = data;
            console.log("Get Last Refresh success ");
            console.log(JSON.stringify(data));
        }).error(function (data, status) {
            console.log("Get Last Refresh failure " + status)
        });
    };
    scope.getStats();
    scope.getLastRefresh();
    }]).controller('compareCtrl', ['$scope', '$http', '$location', '$route', '$rootScope', function ($scope, $http, $location, $route, $rootScope) {
        var scope = $scope;
        scope.summoner = {};
        scope.comparison = {};
        scope.notificationData = {};
        scope.compared = false;

        scope.compare = function () {
            $http.get('/riot/compare/' + scope.summoner.name, {}).success(function (data, status) {
                scope.comparison = data;
                scope.compared = true
            }).error(function (data, status) {
                console.log("Compare failure " + status)
            });
        };

        scope.getCompared = function () {
            $http.get('/riot/getcompared').success(function (data, status) {
                scope.notificationData = data;
                console.log(JSON.stringify(data));
            }).error(function (data, status) {
                console.log("Get Compared failure " + status)
            });
        };

        scope.setViewed = function () {
            $http.post('/riot/setviewed',{}).success(function (data, status) {
                console.log("setviewed success " + data);
                console.log(JSON.stringify(data));
            }).error(function (data, status) {
                console.log("setviewed failure " + status)
        });
    };
    }]);