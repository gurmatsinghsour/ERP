package com.degenCoders.loliSimpErp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.degenCoders.loliSimpErp.dataBaseConnection.*;

@SpringBootApplication
public class LoliSimpErpApplication {

	public static void main(String[] args) {
		PostgreSQLConnection db = new PostgreSQLConnection();
		
		db.connect();
		SpringApplication.run(LoliSimpErpApplication.class, args);

	}

}
