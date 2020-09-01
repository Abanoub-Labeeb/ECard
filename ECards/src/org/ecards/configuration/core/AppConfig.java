package org.ecards.configuration.core;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = {"org.ecards"})
@Import({DataSourceConfig.class, JpaConfig.class, RepositoryConfig.class, WebConfig.class} )
public class AppConfig {
	
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource() {
		  /*Configure message source file to enable localization of the application*/
	      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	      messageSource.setBasename("classpath:org/ecards/messages/validation");
	      messageSource.setDefaultEncoding("UTF-8");
	      messageSource.setDefaultLocale(Locale.ENGLISH);
	      //messageSource.setUseCodeAsDefaultMessage(true);
	      return messageSource;
	}	
}







