package tp.appliSpring6.dto;

//@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class MessageGenerique {
	
	public String message;
	public String details;
	
	
	
	@Override
	public String toString() {
		return "MessageGenerique [message=" + message + ", details=" + details + "]";
	}



	public MessageGenerique() {
		super();
	}



	public MessageGenerique(String message) {
		this.message =message;
	}
	
	



	public MessageGenerique(String message, String details) {
		super();
		this.message = message;
		this.details = details;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getDetails() {
		return details;
	}



	public void setDetails(String details) {
		this.details = details;
	}

	
	
}
