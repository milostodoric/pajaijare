var zavrsniApp = angular.module("zavrsniApp", ['ngRoute']);

zavrsniApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/linije.html'
    }).when('/linije/edit/:id',{
        templateUrl: '/app/html/edit-linija.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

zavrsniApp.controller("linijeCtrl", function($scope, $http, $location){

	var baseUrlPrevoznici = "/api/prevoznici";
    var baseUrlLinije = "/api/linije";
    

    $scope.pageNum = 0;
    $scope.totalPages = 0;

    $scope.prevoznici = [];
    $scope.linije = [];

    $scope.novaLinija = {};
    $scope.novaLinija.brojMesta = "";
    $scope.novaLinija.cena = "";
    $scope.novaLinija.vremePolaska = "";
    $scope.novaLinija.destinacija = "";
    $scope.novaLinija.prevoznikId = "";
    


    $scope.trazenaLinija = {};
    $scope.trazenaLinija.destinacija = "";
    $scope.trazenaLinija.prevoznik = "";
    $scope.trazenaLinija.cena = "";

    var getPrevoznici = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazenaLinija.destinacija != ""){
            config.params.destinacija = $scope.trazenaLinija.destinacija;
        }

        if($scope.trazenaLinija.prevoznik != ""){
            config.params.prevoznik = $scope.trazenaLinija.prevoznik;
        }

        if($scope.trazenaLinija.cena != ""){
            config.params.cena = $scope.trazeniLinija.cena;
        }


        $http.get(baseUrlLinije, config)
            .then(
            	function success(res){
            		$scope.linije = res.data;
            		$scope.totalPages = res.headers('totalPages');
            	},
            	function error(res){
            		alert("Neuspesno dobavljanje linije!");
            	}
            );
    };

    var getPrevoznici = function(){

        $http.get(baseUrlPrevoznici)
            .then(
            	function success(res){
            		$scope.prevoznici = res.data;
            	},
            	function error(res){
            		alert("Neuspesno dobavljanje prevoznik!");
            	}
            );

    };

    getPrevoznici();
    getLinije();
   

    $scope.nazad = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getLinije();
        }
    };

    $scope.napred = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getLinije();
        }
    };

    $scope.dodaj = function(){
        $http.post(baseUrlLinije, $scope.novaLinija)
            .then(
            	function success(res){
            		getLinije();
            		
            		$scope.novaLinija.brojMesta = "";
            	    $scope.novaLinija.cena = "";
            	    $scope.novaLinija.vremePolaska = "";
            	    $scope.novaLinija.destinacija = "";
            	    $scope.novaLinija.prevoznikId = "";
            	},
            	function error(res){
            		alert("Neuspesno dodavanje!");
            	}
            );
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getLinije();
    }

    $scope.izmeni = function(id){
        $location.path('/linije/edit/' + id);
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlLinije + "/" + id).then(
            function success(data){
            	getLinije();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
/*    $scope.iznajmi = function(id){
    	$http.post(baseUrlLinije + "/" + id + "/iznajmljivanje").then(
    		function success(data){
    			alert("Linija je uspesno iznajmljen.");
    			getLinije();
    		},
    		function error(data){
    			alert("Nije uspelo iznajmljivanje")
    		}
    	)
    
});*/

zavrsniApp.controller("editLinijaCtrl", function($scope, $http, $routeParams, $location){

	var baseUrlLinije = "/api/linije";

    $scope.staraLinija = null;

    var getStaraLinija = function(){

        $http.get(baseUrlLinije + "/" + $routeParams.id)
            .then(
            	function success(res){
            		$scope.staraLinija = res.data;
            	},
            	function error(data){
            		alert("Neušpesno dobavljanje linije.");
            	}
            );

    }
    getStaraLinija();
    
    $scope.izmeni = function(){
        $http.put(baseUrlLinije + "/" + $scope.staraLinija.id, $scope.staraLinija)
            .then(
        		function success(data){
        			alert("Uspešno izmenjena linija!");
        			$location.path("/");
        		},
        		function error(data){
        			alert("Neuspešna izmena linije.");
        		}
            );
    }
});












