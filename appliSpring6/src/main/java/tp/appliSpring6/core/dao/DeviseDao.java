package tp.appliSpring6.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring6.core.entity.Devise;

public interface DeviseDao extends JpaRepository<Devise,String> {

}
