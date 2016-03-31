package com.vz.ts.vt.lighting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
@EnableJpaRepositories(basePackages={ "com.vz.ts.vt.lighting.repository"})
public class VtLightingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VtLightingApplication.class, args);
	}
}
