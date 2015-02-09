package tp.myapp.minibank.impl.persistence.dao.jpa;

import javax.inject.Named;

import org.mycontrib.generic.persistence.GenericDaoJpaImpl;

import tp.myapp.minibank.impl.persistence.dao.ClientDao;
import tp.myapp.minibank.impl.persistence.entity._Client;

@Named
//@Named (de JEE6 , Spring3) ou bien @Component (de Spring 2.5)
//demande à Spring de créer un composant de la classe courante
//avec comme id par défaut le nom de la classe commencant par 
//une minuscule
// equivalent
// <bean id="clientDaoJpa class="....ClientDaoJpa" />

//@Transactional inherited
public class ClientDaoJpa  extends GenericDaoJpaImpl<_Client,Long> implements ClientDao {

}
