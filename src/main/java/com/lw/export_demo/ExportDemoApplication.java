package com.lw.export_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lw.export_demo.mapper")
public class ExportDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExportDemoApplication.class, args);
	}

}
