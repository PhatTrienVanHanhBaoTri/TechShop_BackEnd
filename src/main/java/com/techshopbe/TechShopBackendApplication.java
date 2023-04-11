package com.techshopbe;

import com.techshopbe.controller.AuthenticationController;
import com.techshopbe.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(
		exclude={DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class},
		scanBasePackages = {"com.techshopbe", "com.techshopbe.repository"},
		scanBasePackageClasses = {AuthenticationController.class})
public class TechShopBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechShopBackendApplication.class, args);
	}

}
