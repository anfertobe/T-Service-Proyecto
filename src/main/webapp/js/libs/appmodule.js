(function () {
    var app = angular.module('modone', ['ngRoute']);
    var idInscrito = '2';
    var nombreInscrito = 'TService';
    
    app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
                // route for the about page
                .when('/new', {
                    templateUrl: 'registro.html'

                })
                
                // route for the contact page
                .when('/list', {
                    templateUrl: 'catalogo.html'

                })
                
                .when('/Author', {
                    templateUrl: 'Autor.html'

                })
                
                .when('/login', {
                    templateUrl: 'login.html'

                })
                
                .when('/singup', {
                    templateUrl: 'singup.html'

                })
                
                .when('/home', {
                    templateUrl: 'home.html'

                })
                .otherwise({
                    redirectTo: '/home'
                  });
    }]);

    app.controller('controlregistro',
     function($scope,$http){
            this.producto=
            {
                idproducto:0, nombre:'', precio:0
            };
            
            this.registrar=function(){
                $http.post('rest/products', this.producto).
                success(function (data, status, headers, config) {
                    alert('success!');
                }).
                error(function (data, status, headers, config) {
                    alert('error!');
                });
            };
     }
     );
     
    app.controller('pedirProduc',
        function ($scope, $http){
            this.consultar=function(){
                $http.get("rest/products").
                success(function (response) {$scope.productos = response;}).
                error(function(data, status, headers, config) {
                   alert('error!');
                });
            };
        });

     
})();





