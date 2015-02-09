var myAjsControllers = angular.module('myAjsControllers', [ 'comptesServices']);

myAjsControllers.controller('MyDefaultCtrl',[ '$scope' ,  function ($scope) {
	//empty
}]);
		
 
myAjsControllers.controller('CompteListCtrl', [ '$scope' , 'Compte' , '$routeParams' , function ($scope,Compte,$routeParams) {
 
$scope.numClient = $routeParams.numCli; //numCli en fin d'url (in $routeProvider config)

$scope.comptes =  Compte.query({ numClient : $scope.numClient});   // url REST = .../comptes?numClient=1		

}]);


myAjsControllers.controller('TransfertCtrl', [ '$scope' , 'Compte' , 'Transfert', '$routeParams' , function ($scope,Compte,Transfert , $routeParams) {
	 
$scope.numClient = $routeParams.numCli; //numCli en fin d'url (in $routeProvider config)

$scope.transfert =  {
			"montant": 0.0,
			"numCptDeb": 1,
			"numCptCred" : 2,
			"ok" : null}; //by default
		
		
	$scope.doVirementAndRefresh = function (){
		 //$scope.transfert = Compte.doVirement($scope.transfert);
		/* $scope.transfert = */Transfert.save($scope.transfert).
		    $promise.then(
			    //success
			    function( value ){
			    	 $scope.transfert = value;
			    	 if($scope.transfert.ok){
			    		 $scope.message="virement de " + $scope.transfert.montant + " euros bien effectue du compte "
			    		 + $scope.transfert.numCptDeb + " vers le compte "  + $scope.transfert.numCptCred;	
			    	//+ RAZ du montant pour éviter deux transferts consecutifs (par erreurs)
			    		 $scope.transfert.montant=0; $scope.transfert.ok=null;
			    	 }
			    },
			    //error
			    function( error ){
			        alert(error);
			     }
			  );	 
	};

	}]);