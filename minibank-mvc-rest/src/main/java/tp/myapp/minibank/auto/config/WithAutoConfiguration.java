package tp.myapp.minibank.auto.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"tp.myapp.minibank"})//with excludeFilters if needed
public class WithAutoConfiguration {
   //la configuration automatique tient compte des éléments
   //présents dans le classpath (selon pom.xml)
   //et du fichier application.properties	
}
