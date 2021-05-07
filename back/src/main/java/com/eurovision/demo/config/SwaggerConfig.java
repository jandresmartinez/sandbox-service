package com.eurovision.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.eurovision.demo"))
            .paths(PathSelectors.any())
            .build()

            .apiInfo(apiInfo())
            .consumes(new HashSet<>(Arrays.asList(MediaType.APPLICATION_JSON_VALUE)))
			.produces(new HashSet<>(Arrays.asList(MediaType.APPLICATION_JSON_VALUE)));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
          "Sandbox Demo App",
          "Small application that allows to display cities.",
          "1.0",
          "", 
          new Contact("Jorge Andres", "https://github.com/jandresmartinez", "jandresmartinez@gmail.com"),
          "", "", Collections.emptyList());
    }



}