package tp.app.zz.config.auto;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*  different classic uses:
 
   in main() of XyBoot :
   		SpringApplication app = new SpringApplication(DomainAndPersistenceAutoConfig.class);
		app.setWebEnvironment(false);
		ConfigurableApplicationContext context = app.run(args);
    
      
   in spring test:
   		@RunWith(SpringJUnit4ClassRunner.class)
   		@SpringApplicationConfiguration(classes={tp.app.zz.config.auto.DomainAndPersistenceAutoConfig.class})
   
   ...
 */



@Configuration
@EnableAutoConfiguration   //auto configuration en tenant compte des librairies du "classpath" 
                           //pour découvrir et configurer automatiquement
						   // datasource en version H2 ou hsqldb , jpaVendor en version Hibernate ou ...
@EnableTransactionManagement() //"transactionManager" (not "txManager") is expected !!!
@ComponentScan(basePackages={"tp.app.zz.impl","org.mycontrib.generic"}) //to find and interpret @Component , @Named, ...
@EntityScan(basePackages={"tp.app.zz.impl.persistence.entity"}) //to find and interpret @Entity, ...
public class DomainAndPersistenceAutoConfig {
	
	/* Via @EnableAutoConfiguration , les éléments suivants seront automatiquement configurés:
	 *  JpaVendorAdapter (par exemple en version HibernateJpaVendorAdapter)
	 *  EntityManagerFactory ou ....
	 *  PlatformTransactionManager (par exemple en version JPA)
	 */
}
