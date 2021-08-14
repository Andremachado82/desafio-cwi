package com.desafio.cwi.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${swagger.title}")
	private String title;

	@Value("${swagger.description}")
	private String description;

	@Value("${swagger.version}")
	private String version;

	@Value("${swagger.terms}")
	private String terms;

	@Value("${swagger.develloper-name}")
	private String develloperName;

	@Value("${swagger.develloper-url}")
	private String develloperUrl;

	@Value("${swagger.develloper-email}")
	private String develloperEmail;

	@Value("${swagger.license}")
	private String license;

	@Value("${swagger.license-url}")
	private String licenseUrl;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {

		return new ApiInfo(title, description, version, terms,
				new Contact(develloperName, develloperUrl, develloperEmail), license, licenseUrl, new ArrayList<>());
	}
}
