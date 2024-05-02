package com.example.cache;

import com.example.cache.domain.entitiy.User;
import com.example.cache.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class CacheApplication implements ApplicationRunner {
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		userRepository.save(
				User.builder().name("aaa").email("aaa@gmailc.com").build()
		);
		userRepository.save(
				User.builder().name("bbb").email("bbb@gmailc.com").build()
		);
		userRepository.save(
				User.builder().name("ccc").email("ccc@gmailc.com").build()
		);
	}
}