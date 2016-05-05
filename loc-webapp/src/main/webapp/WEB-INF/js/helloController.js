angular.module('hello', [])
    .controller('home', function($scope, $http) {

        /**
         * To tak przyszłościowo do komunikacji z serwerem (zapytania http ;) )
         */
        /*        $http.get('/resource/').success(function(data) {
            $scope.greeting = "Welcome in League of Comparers!";
        })*/
        //this.scope = $scope;
        $scope.greeting = 'Welcome in League of Comparers!';
        console.log($scope.greeting);
    });
