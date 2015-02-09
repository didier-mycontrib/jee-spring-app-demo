package tp.myapp.minibank.itf.domain.service;

public class MyServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyServiceException() {
		
	}

	public MyServiceException(String msg) {
		super(msg);
		
	}

	public MyServiceException(Throwable ex) {
		super(ex);
		
	}

	public MyServiceException(String msg, Throwable ex) {
		super(msg, ex);
		
	}

}
