package com.example.proyectoIntegrador;

import com.example.proyectoIntegrador.bd.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoIntegradorApplication {

	public static void main(String[] args) {
		BD.crearTablas();//Crea la base de datos
		SpringApplication.run(ProyectoIntegradorApplication.class, args);
	}

}
