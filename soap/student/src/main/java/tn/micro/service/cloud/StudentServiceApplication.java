package tn.micro.service.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("tn.micro.service.cloud.proxy")
public class StudentServiceApplication {
	// Injecte la valeur de l'URL du microservice à partir du fichier
	// application.properties
	//@Value("${adress.service.url}")
	private String adressServiceUrl;

	// Crée un bean nommé 'webClient' utilisable dans tout le projet
	//@Bean
	/*public WebClient webClient() {
		return WebClient.builder().baseUrl(adressServiceUrl) // Configure l'URL de base
				.build();
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class);
	}
}
