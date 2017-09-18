package tp.myapp.minibank.web.ws.rest.data;

public class Transfert {
	private Double montant;
	private Long numCptDeb;
	private Long numCptCred;
    private Boolean ok;
    
	@Override
	public String toString() {
		return "Transfert [montant=" + montant + ", numCptDeb=" + numCptDeb
				+ ", numCptCred=" + numCptCred + ", ok=" + ok + "]";
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public Long getNumCptDeb() {
		return numCptDeb;
	}
	public void setNumCptDeb(Long numCptDeb) {
		this.numCptDeb = numCptDeb;
	}
	public Long getNumCptCred() {
		return numCptCred;
	}
	public void setNumCptCred(Long numCptCred) {
		this.numCptCred = numCptCred;
	}
	public Boolean getOk() {
		return ok;
	}
	public void setOk(Boolean ok) {
		this.ok = ok;
	}
    
    
}
