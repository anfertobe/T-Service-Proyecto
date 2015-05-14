(function () {
    var app = angular.module('modone', ['ngRoute']);
   
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

                    .when('/MNOferta', {
                        templateUrl: 'MNOferta.html'
                    })
                    .when('/MNPersona', {
                        templateUrl: 'MNPersona.html'
                    })
                    .when('/MNCategoria', {
                        templateUrl: 'MNCategoria.html'
                    })
                    .otherwise({
                        redirectTo: '/MNCategoria'
                    });
        }]);

    app.controller('controlregistro',
            function ($scope, $http) {
                this.producto =
                        {
                            idproducto: 0, nombre: '', precio: 0
                        };

                this.registrar = function () {
                    $http.put('rest/products', this.producto).
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
            
                $scope.OptionTipo=null;
                $scope.OptionFind=null;
                this.Categorias=[];
                $scope.ofertasG = [];
                $scope.Publicantes = [];
                $scope.Postulantes = [];
            

                this.Persona={
                    identificacion: 0,
                    nombre: '',
                    fechaNacimiento: new Date(),
                    correo: '',
                    direccion: '',
                    telefono: '',
                    pais: '',
                    region: '',
                    ciudad: '',
                }
                
                
                this.Postulante = {
                        identificacion: 0,hojaDeVida: {hojaDeVida: '',
                        fechaActualizacion: new Date(),foto: ''},
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
                }
                
                this.Publicante={ 
                    identificacion:0,
                    experiencia:'',
                    fechaUltimaLicecia: new Date(),
                    nombre:'',
                    fechaNacimiento:new Date(),
                    direccion:'',
                    telefono:'',
                    pais:'',
                    ragion:'',
                    ciudad:'',
                    correo:'',
                    facturas:[],
                    ofertas:null
                }
                
                this.Buscar=function(){
               
                    var persona=null;
                    
                    
                    for (var i = 0; i < $scope.Publicantes.length; i++) {
                        
                         if ($scope.Publicantes[i].identificacion == $scope.OptionFind || $scope.Publicantes[i].nombre.contains($scope.OptionFind)) {
                             persona = $scope.Publicantes[i];
                             this.Persona.region=persona.ragion;
                             $scope.OptionTipo="Publicante";
                             this.Publicante=persona;
                        }
                    }
                    
                    if(persona==null){
                        for (var i = 0; i < $scope.Postulantes.length; i++) {
                           if ($scope.Postulantes[i].identificacion == $scope.OptionFind || $scope.Postulantes[i].nombre.contains($scope.OptionFind)) {
                                persona = $scope.Postulantes[i];
                                this.Persona.region=persona.region;
                                $scope.OptionTipo="Postulante";
                                this.Postulante=persona;
                           }
                        }
     
                    }
                    
                    
                    this.Persona.identificacion=persona.identificacion;
                    this.Persona.nombre=persona.nombre;
                    this.Persona.fechaNacimiento=persona.fechaNacimiento;
                    this.Persona.correo=persona.correo;
                    this.Persona.direccion=persona.direccion;
                    this.Persona.telefono=persona.telefono;
                    this.Persona.pais=persona.pais;
                    this.Persona.ciudad=persona.ciudad;
                    
                    this.habilitarControles();    
            
                    
                };


            this.habilitarControles =  function (){
                    
                    if($scope.OptionTipo=="Postulante"){
                        document.getElementById("experiencia").disabled = true;
                        this.Publicante.experiencia="";
                        document.getElementById("hojadevida").disabled = false;
                        document.getElementById("fechaActualizacion").disabled = false;
                        document.getElementById("foto").disabled = false;
                        document.getElementById("aspiracionSalarial").disabled = false;
                        
                    }else{
                        document.getElementById("experiencia").disabled = false;
                        this.Postulante.hojaDeVida.hojaDeVida="";
                        document.getElementById("hojadevida").disabled = true;
                        document.getElementById("foto").disabled = true;
                        this.Postulante.hojaDeVida.foto="";
                        document.getElementById("aspiracionSalarial").disabled = true;
                        this.Postulante.aspiracionSalarial="";
                        document.getElementById("fechaActualizacion").disabled = true;
                        this.Postulante.hojaDeVida.fechaActualizacion="";
                
                    }
            };


            this.registro = function () {
                 
                                
                if($scope.OptionTipo!= null){
                
                if($scope.OptionTipo=="Postulante"){
                    
                    this.Postulante.identificacion=this.Persona.identificacion;
                    this.Postulante.nombre=this.Persona.nombre;
                    this.Postulante.fechaNacimiento=this.Persona.fechaNacimiento;
                    this.Postulante.correo=this.Persona.correo;
                    this.Postulante.direccion=this.Persona.direccion;
                    this.Postulante.telefono=this.Persona.telefono;
                    this.Postulante.pais=this.Persona.pais;
                    this.Postulante.ciudad=this.Persona.ciudad;
                    this.Postulante.region=this.Persona.region;
                    
                    $http.put('rest/tservice/Postulantes', this.Postulante).
                                success(function (data, status, headers, config) {
                                    alert('success!');
                                }).
                                error(function (data, status, headers, config) {
                                    alert('error: ' + status + " - " + data );
                                });
                    
                }else{
                    
                    this.Publicante.identificacion=this.Persona.identificacion;
                    this.Publicante.nombre=this.Persona.nombre;
                    this.Publicante.fechaNacimiento=this.Persona.fechaNacimiento;
                    this.Publicante.correo=this.Persona.correo;
                    this.Publicante.direccion=this.Persona.direccion;
                    this.Publicante.telefono=this.Persona.telefono;
                    this.Publicante.pais=this.Persona.pais;
                    this.Publicante.ciudad=this.Persona.ciudad;
                    this.Publicante.ragion=this.Persona.region;
                    
                    $http.put('rest/tservice/Publicantes', this.Publicante).
                                success(function (data, status, headers, config) {
                                    alert('success!');
                                }).
                                error(function (data, status, headers, config) {
                                    alert('error: ' + status + " - " + data );
                    });
                }
            }else{
                
                alert('Debe escoger un tipo de persona');
                
            }
                    
            };
                
            this.consultar = function () {
                   $http.get("rest/tservice/Ofertas").
                            success(function (response) {
                                $scope.ofertasG = response;
                            }).
                            error(function (data, status, headers, config) {
                                alert('error!');
                            });
                    $http.get("rest/tservice/Publicantes").
                            success(function (response) {
                                $scope.Publicantes = response;
                            }).
                            error(function (data, status, headers, config) {
                                alert('error!');
                            });
                     $http.get("rest/tservice/Postulantes").
                            success(function (response) {
                                $scope.Postulantes = response;
                            }).
                            error(function (data, status, headers, config) {
                                alert('error!');
                     });        
             };
           }
    );
    
<<<<<<< HEAD
    app.controller('PagoLicencia',
    function($scope,$http){
       $scope.licencia = null;
       
       this.TraerLicencias = function(){
            $http.get("rest/tservice/licencias").success(function (response) {
                $scope.licencias = response;
            }).error(function (data, status, headers, config) {
                alert('error!');
            });  
       };
       
       this.pagarLicencia = function(){
           $http.get("rest/tservice/licencias").success(function (response) {
                $scope.licencias = response;
            }).error(function (data, status, headers, config) {
                alert('error!');
            });
       };
    });
    

        app.controller('Oferta',
=======
    
    app.controller('Categoria',
            function ($scope, $http) {
                       
                    $scope.Categorias=[];    
                    $scope.OptionOf=null;
<<<<<<< HEAD
                    $scope.Postulantes=null;
         
                           
                this.Postulante = {
                        identificacion: 0,hojaDeVida: {hojaDeVida: '',
                        fechaActualizacion: new Date(),foto: ''},
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
                   }
         
                
                    
                    this.Interes={
                        id:0,
                        experiencia:[],
                        categorias:[],
=======
                
                    this.Interes={
                        id:0,
                        experiencia:[],
                        categorias:[]
>>>>>>> origin/master
                    }
                    
                    this.Categoria={
                        id:0,
                        interes:[],
                        nombre:'',
                        ofertas:[]
                    }
                    
                    this.cargarPostulante=function () {
                            var persona=null;
                        
                            for (var i = 0; i < $scope.Postulantes.length; i++) {
                                if ($scope.Postulantes[i].identificacion == $scope.OptionPub.split('-')[0] && $scope.Postulantes[i].nombre == $scope.OptionPub.split('-')[1].replace('(', '').replace(')', '')) {
                                    persona = $scope.Postulantes[i];
                                    
                                 }
                            }
                            
                            this.Postulante=persona;
     
                    }
                    
                    this.agregarInteres=function () {
            
                            this.Categoria.interes[this.Categoria.interes.length]=this.Interes;
<<<<<<< HEAD
                             
                            var persona=null;
                        
                            for (var i = 0; i < $scope.Postulantes.length; i++) {
                                if ($scope.Postulantes[i].identificacion == $scope.OptionPub.split('-')[0] && $scope.Postulantes[i].nombre == $scope.OptionPub.split('-')[1].replace('(', '').replace(')', '')) {
                                    persona = $scope.Postulantes[i];
                                 }
                            }
     
                           persona.intereses[persona.intereses.length]=this.Interes;
                            
                            $http.put('rest/tservice/Interes', persona).
                                success(function (data, status, headers, config) {
                                    alert('success!');
                                }).
                                error(function (data, status, headers, config) {
                                    alert('error: ' + status + " - " + data );
                            });
 
=======
>>>>>>> origin/master
                            
                            this.Interes={
                                id:0,
                                experiencia:[],
<<<<<<< HEAD
                                categorias:[],
=======
                                categorias:[]
>>>>>>> origin/master
                            }
                    }
                
                
                    this.consultar = function () {
                     $http.get("rest/tservice/Categorias").
                            success(function (response) {
                                $scope.Categorias = response;
                            }).
                            error(function (data, status, headers, config) {
                                alert('error!');
                            });
                            
                    };
            
                    this.cargarCategoria= function () {
                        var ofertaT = null;
 
                        
 
                        for (var i = 0; i < $scope.Categorias.length; i++) {
                              if ($scope.Categorias[i].id == $scope.OptionOf.split('-')[0] && $scope.Categorias[i].nombre == $scope.OptionOf.split('-')[1].replace('(', '').replace(')', '')) {
                                  alert('Find');
                                  ofertaT = $scope.Categorias[i];
                             }
                        }

                       alert('Value');

                       this.Categoria=ofertaT;
                    };
            
                    this.registro = function () {
                        
                        $http.put('rest/tservice/Categorias', this.Categoria).
                                success(function (data, status, headers, config) {
                                    alert('success!');
                                }).
                                error(function (data, status, headers, config) {
                                    alert('error: ' + status + " - " + data );
                                });
                };
                
            
            
            
            }
    
    );
    
    
    
    app.controller('Oferta',
>>>>>>> 77351b9b08fd09877ed9f796bb1e928366edafcd
            function ($scope, $http) {
                       
                $scope.OptionOf=null;
                $scope.OptionPub=null;
                 
                 this.Oferta = {
                       id:'',
                       calificacion: {rango: '',comentario: '', valor: 0},
                       postulante:{identificacion: 0,hojaDeVida: {hojaDeVida: '',
                                fechaActualizacion: new Date(),foto: ''},
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
                       },
                       publicante:{ identificacion:0,experiencia:'',fechaUltimaLicecia: new Date(),
                                    nombre:'',fechaNacimiento:new Date(),direccion:'',telefono:'',
                                    pais:'',ragion:'',ciudad:'',correo:'',facturas:[],ofertas:[]
                                                    
                       },
                       fechaCreacion:new Date(),
                       fechaFinalizacion:new Date(),
                       valor:0,
                       descripcion:'',
                       estado:'',
                       categorias: [],
                       postulantes: []
                };
                
            
            $scope.ofertasG = [];
            
           
            $scope.Publicantes = [];
                
            this.cargarOferta   = function () {
               var ofertaT = null;
 
               for (var i = 0; i < $scope.ofertasG.length; i++) {
                     if ($scope.ofertasG[i].id == $scope.OptionOf.split('-')[0] && $scope.ofertasG[i].descripcion == $scope.OptionOf.split('-')[1].replace('(', '').replace(')', '')) {
                         ofertaT = $scope.ofertasG[i];
                    }
               }
               
               this.Oferta=ofertaT;
            };
            
            this.consultar = function () {
                     $http.get("rest/tservice/Ofertas").
                            success(function (response) {
                                $scope.ofertasG = response;
                            }).
                            error(function (data, status, headers, config) {
                                alert('error!');
                            });
                            
                       $http.get("rest/tservice/Publicantes").
                            success(function (response) {
                                $scope.Publicantes = response;
                            }).
                            error(function (data, status, headers, config) {
                                alert('error!');
                            });
                            
            };
                
            this.registro = function () {
                var ofertaT = null;
 
                for (var i = 0; i < $scope.Publicantes.length; i++) {
                        if ($scope.Publicantes[i].identificacion == $scope.OptionPub.split('-')[0] && $scope.Publicantes[i].nombre == $scope.OptionPub.split('-')[1].replace('(', '').replace(')', '')) {
                             ofertaT = $scope.Publicantes[i];
                        }
                }
                           
                this.Oferta.publicante=ofertaT;
               
                 $http.put('rest/tservice/Ofertas/'+this.Oferta.publicante.identificacion, this.Oferta).
                                success(function (data, status, headers, config) {
                                    alert('success!');
                                }).
                                error(function (data, status, headers, config) {
                                    alert('error: ' + status + " - " + data );
                                });
                };
                
                
            this.modificar = function () {
                   $http.put('rest/tservice/Ofertas/0', this.Oferta).
                                success(function (data, status, headers, config) {
                                    alert('success!');
                                }).
                                error(function (data, status, headers, config) {
                                    alert('error: ' + status + " - " + data );
                                });
                };
            }
             
            
                    
                    
                    
    );


})();