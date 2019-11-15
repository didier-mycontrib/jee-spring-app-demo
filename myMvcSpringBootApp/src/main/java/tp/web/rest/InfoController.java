package tp.web.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.web.data.Info;

@RestController
@RequestMapping(value="/rest/info" , 
                headers="Accept=application/json")
public class InfoController {
	
	private Map<Long,Info> mapInfos = new HashMap<Long,Info>();
	
	public InfoController(){
		mapInfos.put(1L, new Info(1L,"info1"));
		mapInfos.put(2L, new Info(2L,"info2"));
	}
	
	@GetMapping(value="/{infoId}" )
	public Info getInfoById(@PathVariable("infoId") Long infoId){
		return mapInfos.get(infoId);
	}

}
