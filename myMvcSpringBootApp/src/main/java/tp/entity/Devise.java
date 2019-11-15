
package tp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

 @Entity
 @Table(name="devise")
 @NamedQueries({
	})
public  class Devise  {

	@Column(name="d_change")
	private Double dChange;
	
	@Column(length=64)
	private String monnaie;
	
	@Column(length=8,name="code_devise")
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
