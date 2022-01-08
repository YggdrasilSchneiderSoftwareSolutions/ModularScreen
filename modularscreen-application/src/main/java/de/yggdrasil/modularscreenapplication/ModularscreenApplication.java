package de.yggdrasil.modularscreenapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "de.yggdrasil")
public class ModularscreenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModularscreenApplication.class, args);
	}

}
