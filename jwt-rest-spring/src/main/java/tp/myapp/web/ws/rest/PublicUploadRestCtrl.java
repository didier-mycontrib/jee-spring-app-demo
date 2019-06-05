package tp.myapp.web.ws.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController 
@CrossOrigin(origins = "*")
@RequestMapping(value="/public/rest/upload")
public class PublicUploadRestCtrl {
	
	private static Logger logger = LoggerFactory.getLogger(PublicUploadRestCtrl.class);
	private static final String UPLOAD_FOLDER ="./upload";
		

	//.../mvc/public/rest/upload/single
	@RequestMapping(value="/single" , method=RequestMethod.POST) 
	public ResponseEntity<String> uploadSingleFile(@RequestParam("file") MultipartFile uploadFile){
			if(uploadFile==null || uploadFile.isEmpty()){
				     return new ResponseEntity<String>("empty file received", HttpStatus.OK);
				}
				else {
					logger.debug("upload originalFileName:" + uploadFile.getOriginalFilename());
					saveUploadFile(uploadFile);
					return new ResponseEntity<String>("file received : "
							+ uploadFile.getOriginalFilename(), HttpStatus.OK);
				}
			}
	
	
		//.../mvc/public/rest/upload/model
		@RequestMapping(value="/model" , method=RequestMethod.POST) 
		public ResponseEntity<String> uploadFileWithInfo(@ModelAttribute MyUploadModel myUploadModel){
		logger.debug("upload name:" + myUploadModel.getName());
		logger.debug("upload description:" + myUploadModel.getDescription());
		
		MultipartFile uploadFile = myUploadModel.getFile();
				if(uploadFile==null || uploadFile.isEmpty()){
					     return new ResponseEntity<String>("empty file received", HttpStatus.OK);
					}
					else {
						logger.debug("upload originalFileName:" + uploadFile.getOriginalFilename());
						saveUploadFile(uploadFile);
						return new ResponseEntity<String>("file received : "
								+ uploadFile.getOriginalFilename(), HttpStatus.OK);
					}
				}
		
		private void saveUploadFile(MultipartFile uploadFile){
			try {
				byte[] bytes = uploadFile.getBytes();
				Path path = Paths.get(UPLOAD_FOLDER+ "/" + uploadFile.getOriginalFilename());
				Files.write(path, bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

}
