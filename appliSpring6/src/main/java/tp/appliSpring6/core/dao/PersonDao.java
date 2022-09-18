package tp.appliSpring6.core.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.appliSpring6.core.entity.Person;

public interface PersonDao extends JpaRepository<Person,UUID> {

}
