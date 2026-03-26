package br.com.ufu.recsys.identityaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.ufu.recsys")
@EntityScan(basePackages = "br.com.ufu.recsys")
@EnableJpaRepositories(basePackages = "br.com.ufu.recsys")
public class IdentityAccessApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityAccessApplication.class, args);
    }

}
