package tp.dao;

import org.springframework.data.repository.CrudRepository;

import tp.entity.Client;

public interface DaoClient extends CrudRepository<Client,Long>{

}
