package tp.web.mvc.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

//utile pour DeviseListCtrlV2 et form:form 
public class ConversionForm {
	
	@Min(value=0)
	@Max(value=999999)
	private Double montant;
	private String monnaieSrc;
	private String monnaieDest;
	
	public ConversionForm(){
		monnaieSrc="dollar";
		monnaieDest="dollar"; //par defaut
	}
	
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public String getMonnaieSrc() {
		return monnaieSrc;
	}
	public void setMonnaieSrc(String monnaieSrc) {
		this.monnaieSrc = monnaieSrc;
	}
	public String getMonnaieDest() {
		return monnaieDest;
	}
	public void setMonnaieDest(String monnaieDest) {
		this.monnaieDest = monnaieDest;
	}
	
	

}
