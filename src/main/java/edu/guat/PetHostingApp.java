package edu.guat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.guat.mapper")
public class PetHostingApp {

	public static void main(String[] args) {
		SpringApplication.run(PetHostingApp.class);
	}

}
