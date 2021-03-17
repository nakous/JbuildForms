package com.jbuild.forms.jbuildforms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer  {

	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry
	          .addResourceHandler("/webjars/**")
	          .addResourceLocations("/webjars/");
	       // registry.addResourceHandler("/pdfs/**")
            //.addResourceLocations("/WEB-INF/pdfs/");

    registry.addResourceHandler("/css/**")
            .addResourceLocations("/WEB-INF/css/");

    registry.addResourceHandler("/js/**")
            .addResourceLocations("/WEB-INF/js/");
	    }
	  /*  @Bean
	    public LocaleResolver localeResolver() {
	        return new CookieLocaleResolver();
	    }*/
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	        localeChangeInterceptor.setParamName("lang");
	        registry.addInterceptor(localeChangeInterceptor);
	    }
	    
	
}
