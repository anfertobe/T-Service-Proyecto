(function () {
    var app = angular.module('modone', ['ngRoute']);
    var idInscrito = '2';
    var nombreInscrito = 'TService';

    app.config(['$routeProvider',
        function ($routeProvider) {
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
            function ($scope, $http) {
                this.producto =
                        {
                            idproducto: 0, nombre: '', precio: 0
                        };

                this.registrar = function () {
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
            function ($scope, $http) {
                this.consultar = function () {
                    $http.get("rest/products").
                            success(function (response) {
                                $scope.productos = response;
                            }).
                            error(function (data, status, headers, config) {
                                alert('error!');
                            });
                };
            });

    app.controller('TipoRol',
            function ($scope, $http) {
                this.Postulante = {
                    identificacion: 0,
                    hojaDeVida: {hojaDeVida: '',fechaActualizacion: new Date(),foto: ''},
                    aspiracionSalarial: 0,
                    nombre: '',
                    fechaNacimiento: new Date(),
                    correo: '',
                    direccion: '',
                    telefono: '',
                    pais: '',
                    region: '',
                    ciudad: '',
                    ofertas: [],
                    ofertas_1: [],
                    intereses: [],
                    experienciaLaborals: []
                };
                $scope.rol = 'empleado';
                $scope.isEmpleado = true;
                $scope.isPostulante = true;

                $scope.Empleador = function () {
                    $scope.isEmpleado = false;
                    $scope.isPostulante = true;
                    $scope.rol = 'empleador';
                };

                $scope.Empleado = function () {
                    $scope.isEmpleado = true;
                    $scope.isPostulante = false;
                    $scope.rol = 'empleado';
                };

                this.registro = function () {
                    if ($scope.isEmpleado) {
                        $http.put('rest/tservice/Postulantes', this.Postulante).
                                success(function (data, status, headers, config) {
                                    alert('success!');
                                }).
                                error(function (data, status, headers, config) {
                                    alert('error: ' + status + " - " + data );
                                });
                    } else if ($scope.isPostulante) {
                        informacion = empleador;
                    }

                };
            }
    );

})();