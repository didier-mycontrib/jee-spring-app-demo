package tp.myapp.minibank.web.ws.rest.data;

public class ClientAuth {
	private Long numClient;
	private String password;
	private Boolean ok; //good pwd (after verify) or status after change
	
	
	@Override
	public String toString() {
		return "ClientAuth [numClient=" + numClient + ", password=" + password
				+ ", ok=" + ok + "]";
	}
	public Long getNumClient() {
		return numClient;
	}
	public void setNumClient(Long numClient) {
		this.numClient = numClient;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getOk() {
		return ok;
	}
	public void setOk(Boolean ok) {
		this.ok = ok;
	}
	
	
}
