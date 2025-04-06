package com.soumyajit.ISA.HIT.HALDIA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableScheduling
@EnableMethodSecurity(prePostEnabled = true)
public class IsaHitHaldiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaHitHaldiaApplication.class, args);
	}

}
