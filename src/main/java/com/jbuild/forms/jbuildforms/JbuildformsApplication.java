package com.jbuild.forms.jbuildforms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.jbuild.forms.jbuildforms.model") // path of the entity model
@EnableJpaRepositories("com.jbuild.forms.jbuildforms.dao")
public class JbuildformsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JbuildformsApplication.class, args);
	}
	@Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

	
	      return application.sources(JbuildformsApplication.class);

	   }


}
