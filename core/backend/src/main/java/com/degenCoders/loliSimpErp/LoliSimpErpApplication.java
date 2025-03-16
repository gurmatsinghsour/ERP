package com.degenCoders.loliSimpErp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.degenCoders.loliSimpErp.dataBaseConnection.*;

@SpringBootApplication
public class LoliSimpErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoliSimpErpApplication.class, args);
		PostgreSQLConnection.connect();
	}

}
