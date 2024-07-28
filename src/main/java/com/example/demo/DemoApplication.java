package com.example.demo;

import com.example.demo.repository.ViolationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private ViolationRepository violationRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		violationRepository.test(List.of("24-2", "24-3", "24-4"),
				Instant.now().minus(1, ChronoUnit.DAYS),
				Instant.now(),
				0,
				1);
		violationRepository.violationByCamGroup("24-4",
				Instant.now().minus(1, ChronoUnit.DAYS),
				Instant.now(),
				0,
				10);
	}
}
