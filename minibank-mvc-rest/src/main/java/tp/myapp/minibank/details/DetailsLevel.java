package tp.myapp.minibank.details;

/* paramétrage (essentiellement declaratif) des niveaux de détails
 * 
 * utilisations classique : @JsonView(DetailsLevel.Essential.class)
 * et switch(detailsLevel) { case DetailsLevel.ESSENTIAL : ... }
 * 
 * 5 niveaux progressifs:
 *    SUMMARY : strict essentiel (minimum) : id et ... (rare)
 *    ESSENTIAL : essentiel (sans objet annexe ni champs secondaires)
 *    MAINDETAILS : avec quelques champs secondaires et eventuel(s) objet(s)
 *                  annexes / lies (1 seul niv de profondeur maxi)
 *    MOSTDETAILS : encore un peu plus détaillé (niv de profondeur variable,
 *                  attention aux liens bi-directionnels : 1 des 2 cotés 
 *                  en "ALLDETAILS" ou rien)
 *    ALLDETAILS : niveau le plus detaillé (très rare)
 */

public class DetailsLevel {
   public enum DetailsLevelEnum { SUMMARY , 
	                              ESSENTIAL , 
	                              MAINDETAILS , 
	                              MOSTDETAILS , 
	                              ALLDETAILS };	                              

   public interface IDetailsLevel { 
	   public DetailsLevelEnum detailsLevel();
   };	                              
   public static class Summary implements IDetailsLevel {
	   public DetailsLevelEnum detailsLevel(){ return DetailsLevelEnum.SUMMARY ; }
   }	
   public static class Essential extends Summary{ 
	   public DetailsLevelEnum detailsLevel(){ return DetailsLevelEnum.ESSENTIAL ; }
   };
   public static class MainDetails extends Essential { 
	   public DetailsLevelEnum detailsLevel(){ return DetailsLevelEnum.MAINDETAILS ; }
   };
   public static class MostDetails extends MainDetails { 
	   public DetailsLevelEnum detailsLevel(){ return DetailsLevelEnum.MOSTDETAILS ; }
   };
   public static class AllDetails extends MostDetails { 
	   public DetailsLevelEnum detailsLevel(){ return DetailsLevelEnum.ALLDETAILS ; }
   };
}
