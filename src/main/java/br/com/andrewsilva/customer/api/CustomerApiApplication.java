package br.com.andrewsilva.customer.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.andrewsilva.customer.api.entity.User;
import br.com.andrewsilva.customer.api.enums.ProfileEnum;
import br.com.andrewsilva.customer.api.repository.UserRepository;

@SpringBootApplication
public class CustomerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			initUser(userRepository, passwordEncoder);
		};
	}

	private void initUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		User admin = new User();
		admin.setEmail("admin@andrewsilva.com.br");
		admin.setPassword(passwordEncoder.encode("123456"));
		admin.setProfile(ProfileEnum.ROLE_ADMIN);

		User find = userRepository.findByEmail("admin@andrewsilva.com.br");
		if (find == null) {
			userRepository.save(admin);
		}
	}
}
