package tp.appliSpring6.core.dao;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring6.core.entity.Devise;
import tp.appliSpring6.core.entity.Person;

@ExtendWith(SpringExtension.class) //si junit5/jupiter
@SpringBootTest
@ActiveProfiles({ "embeddedDB" , "dev" })
//@ActiveProfiles({ "remoteDB" , "dev" })
public class TestPersonDao {
	
private static Logger logger = LoggerFactory.getLogger(TestPersonDao.class);
	
	@Autowired
	private PersonDao personDao; //à tester
	
	
	@Test
	public void testRechercherPersonParId() {

		Person p1 = new Person(null,"titi");
		personDao.save(p1); //INSERT INTO
		UUID idP1 = p1.getId();
	
		Person p1Relu = personDao.findById(idP1).get(); //SELECT
		Assertions.assertEquals("titi",p1Relu.getDescription());
		logger.debug("p1Relu=" +p1Relu);
		
	}

}
