package com.jukusoft.browsergame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.jukusoft.browsergame", "com.jukusoft.authentification.jwt"})
@EnableCaching
@EnableScheduling
@EntityScan({"com.jukusoft.browsergame", "com.jukusoft.authentification.jwt"})
@EnableJpaRepositories({"com.jukusoft.browsergame", "com.jukusoft.authentification.jwt"})
public class BrowsergameRtsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrowsergameRtsBackendApplication.class, args);
	}

}
