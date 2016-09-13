
package tp.app.zz.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

 @Entity
 @Table(name="Devise")
public  class Devise  {

	private Double dChange;
	
	@Column(length=64)
	private String monnaie;
	
	@Column(length=8)
    @Id
	private String codeDevise;

	public Devise(){
		super(); 
	}      
	public String toString(){
		return "Devise("+ "dChange=" + dChange+","+ "monnaie=" + monnaie+","+ "codeDevise=" + codeDevise + ")";
	}
    /*
	public java.io.Serializable getPk(){
	 		return codeDevise;
  	}*/
	public Double getDChange() {
		return this.dChange;
	}
	public void setDChange(Double dChange){
		this.dChange=dChange;
	}
	public String getMonnaie() {
		return this.monnaie;
	}
	public void setMonnaie(String monnaie){
		this.monnaie=monnaie;
	}
	public String getCodeDevise() {
		return this.codeDevise;
	}
	public void setCodeDevise(String codeDevise){
		this.codeDevise=codeDevise;
	}

        
} 
