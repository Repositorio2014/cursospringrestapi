package votacao.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@SpringBootApplication
@EntityScan(basePackages = {"votacao.api.rest.model"})
@ComponentScan(basePackages = {"votacao.*"})
@EnableJpaRepositories(basePackages = {"votacao.api.rest.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableCaching
public class VotacaoApplication implements WebMvcConfigurer {

    public static void main(String[] args) {

        SpringApplication.run(VotacaoApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("123"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now)); //01/02/2019 14:08:43
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/usuario/**")
                .allowedMethods("*")
                .allowedOrigins("*");
        //WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
