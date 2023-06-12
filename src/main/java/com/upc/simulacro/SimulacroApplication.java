package com.upc.simulacro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SimulacroApplication {

	@GetMapping("/message")
	public String message(){
		return "felicitaciones si funciona";
	}
	public static void main(String[] args) {
		SpringApplication.run(SimulacroApplication.class, args);
	}

}
