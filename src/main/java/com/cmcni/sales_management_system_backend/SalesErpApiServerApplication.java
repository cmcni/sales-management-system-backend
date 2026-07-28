package com.cmcni.sales_management_system_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SalesErpApiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesErpApiServerApplication.class, args);
	}

}
