package spring.testcontainers;

import org.springframework.boot.SpringApplication;

public class TestTestcontainersApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestcontainersApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
