
//var tokenGlobal; 

function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return jsonPayload;
    //return JSON.parse(jsonPayload);
};

function doConversion(){
	
	var montant = Number(document.getElementById("txtMontant").value);
	var deviseSource = document.getElementById("txtDeviseSource").value;
	var deviseCible = document.getElementById("txtDeviseCible").value;
	console.log("doConversion() appelee avec montant= " + montant);
	
	var url = "./devise-api/public/convert?amount="
	            +montant+"&source="+deviseSource+
                "&target="+deviseCible;

    var callback = function(data){
	   console.log("sucess data=" + data);
       var resConv = JSON.parse(data);
       var montantConverti = resConv.result;
       document.getElementById("spanRes").innerHTML="<b>"+montantConverti+"</b>";
    }

    var errCallback = function(data){
	   console.log("erreur=" + data);
    }

	makeAjaxGetRequest(url,callback,errCallback) ;
	
}
