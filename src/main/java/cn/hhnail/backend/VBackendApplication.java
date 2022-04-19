package cn.hhnail.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("cn.hhnail.backend.mapper")
public class VBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(VBackendApplication.class, args);

	}

}
