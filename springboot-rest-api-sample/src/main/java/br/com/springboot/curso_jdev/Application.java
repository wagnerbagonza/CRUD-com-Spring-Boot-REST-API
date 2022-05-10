package br.com.springboot.curso_jdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Starta a aplicação Spring
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
    	//Linha principal que roda o projeto Java Spring Boot
        SpringApplication.run(Application.class, args);
    }
}
