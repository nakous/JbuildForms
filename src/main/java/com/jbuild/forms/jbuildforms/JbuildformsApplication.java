package com.jbuild.forms.jbuildforms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JbuildformsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JbuildformsApplication.class, args);
	}
	@Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

	
	      return application.sources(JbuildformsApplication.class);

	   }


}
