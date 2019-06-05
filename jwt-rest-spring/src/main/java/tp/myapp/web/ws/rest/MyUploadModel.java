package tp.myapp.web.ws.rest;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MyUploadModel {
	private String name;
	private String description;
	private MultipartFile file;
	//private MultipartFile[] files;
}
