package xyz.energytrading.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import xyz.energytrading.demo.models.ImportantInfo;
import xyz.energytrading.demo.repository.ImportantInfoRepository;

@SpringBootApplication
@ComponentScan("xyz.energytrading")
@EnableJpaRepositories("xyz.energytrading")
@EntityScan("xyz.energytrading")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ImportantInfoRepository repository) {
		return args -> {
			ImportantInfo info = new ImportantInfo();
			info.setName("Test");
			repository.save(info);
		};
	}
}
