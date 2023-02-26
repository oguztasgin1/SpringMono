package com.oguztasgin.SpringMono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringMonoApplication {

	@GetMapping("/mesaj")
	public String merhaba() {
		return "Merhaba mesaji yayinliyoruz";
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringMonoApplication.class, args);
	}

}
