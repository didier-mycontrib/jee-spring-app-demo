var myAjsApp = angular.module('myAjsApp', [ 'comptesServices']);
 
myAjsApp.controller('CompteListCtrl', [ '$scope' , 'Compte' , 'Transfert' , function ($scope,Compte,Transfert) {
 
$scope.numClient = '1';//by default

$scope.transfert =  {
		"montant": 0.0,
		"numCptDeb": 1,
		"numCptCred" : 2,
		"ok" : null};
	

$scope.refreshWithNumClientWithRest = function (){
		 $scope.comptes =  Compte.query({ numClient : $scope.numClient});   // url = .../comptes?numClient=1
		 $scope.message = "-/-";
	};
		
	
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
		    	 //Réactualiser les comptes seulement après que le virement soit effectué (mais pas avant) !!!
		    	 $scope.comptes =  Compte.query({ numClient : $scope.numClient});
		    	 }
		    },
		    //error
		    function( error ){
		        alert(error);
		     }
		  );	 
};

}]);