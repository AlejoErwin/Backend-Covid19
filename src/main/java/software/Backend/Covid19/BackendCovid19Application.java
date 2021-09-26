package software.Backend.Covid19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BackendCovid19Application {

	public static void main(String[] args) {
		SpringApplication.run(BackendCovid19Application.class, args);
	}


}
