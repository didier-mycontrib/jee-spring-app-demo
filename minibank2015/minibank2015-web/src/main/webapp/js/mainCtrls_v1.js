var myAjsApp = angular.module('myAjsApp', [ 'comptesServices']);
 
myAjsApp.controller('CompteListCtrl', [ '$scope' , 'Compte' , 'Transfert' , function ($scope,Compte,Transfert) {
 
$scope.numClient = '1';//by default

$scope.transfert =  {
		"montant": 0.0,
		"numCptDeb": 1,
		"numCptCred" : 2,
		"ok" : null};
	

$scope.refreshWithNumClientWithRest = function (){
	//url REST = .../comptes?numClient=1	
	Compte.query({ numClient : $scope.numClient}).$promise.then(
		    //success
		    function( value ){
		    	$scope.comptes = value;
		    });

		 $scope.message = "--/--";
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
		    	 Compte.query({ numClient : $scope.numClient}).$promise.then(
		    			    //success
		    			    function( value ){
		    			    	$scope.comptes = value;
		    			    });
		    	 }
		    },
		    //error
		    function( error ){
		        alert(error);
		     }
		  );	 
};

}]);