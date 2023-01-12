package com.prototype.protoapp;


import com.prototype.protoapp.stock.config.LoggingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProtoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtoAppApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<?> loggingFilter() {
		FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>(new LoggingFilter());
		registrationBean.setOrder(Integer.MIN_VALUE);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}