package tp.myapp.minibank.details;

import java.util.Collection;

public interface DetailsFetch {
	   //demander à un objet de données (ex: @Entity avec "lazy collections" )
	   //de charger en mémoire certains détails jusqu'a un certain niveau
       public void fetchDetails(DetailsLevel.DetailsLevelEnum detailsLevel);

       //méthode utilitaire (helper) à appeler dans service transactionnel:
       public static <TC> void fetchLazyCollection(Collection<TC> c){ c.size();}
       
       //méthode utilitaire (helper) à appeler dans service transactionnel:
       public static <T extends DetailsFetch> 
         T fetchDetails(T obj , DetailsLevel.DetailsLevelEnum detailsLevel){
    	       if(obj!=null) obj.fetchDetails(detailsLevel);
    	       return obj;
       }
       //méthode utilitaire (helper) à appeler dans service transactionnel:
       public static <T extends DetailsFetch> 
       T fetchOptionalDetails(T obj , DetailsLevel.DetailsLevelEnum ...detailsLevelOpt){
    	   if(obj!=null && detailsLevelOpt!=null && detailsLevelOpt.length ==1){
    	    obj.fetchDetails(detailsLevelOpt[0]);
    	   }
	       return obj;
       }
}
