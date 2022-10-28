package cn.hhnail.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication(exclude = {
		// DataSourceAutoConfiguration.class,
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})

@MapperScan("cn.hhnail.backend.mapper")
public class VBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(VBackendApplication.class, args);

	}

}
