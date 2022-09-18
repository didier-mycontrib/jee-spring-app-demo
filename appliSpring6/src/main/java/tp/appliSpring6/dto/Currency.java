package tp.appliSpring6.dto;

//@Getter @Setter @ToString
//@NoArgsConstructor 
public class Currency {
	
	private String code;
	private String name;
	
	//@Schema(example = "1.1" , description = "nb unite pour 1 euro")
	private Double rate;
	
	
	
	public Currency() {
		super();
	}



	public Currency(String code, String name, Double rate) {
		super();
		this.code = code;
		this.name = name;
		this.rate = rate;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Double getRate() {
		return rate;
	}



	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	
    
    

}
