package com.app.confeitaria.docelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;


@EntityScan("com.app.confeitaria.docelivery.model.entity")
@SpringBootApplication
public class DoceliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoceliveryApplication.class, args);
	}

}
