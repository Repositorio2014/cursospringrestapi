package votacao.api.rest.security;

import votacao.api.rest.service.ImplementacaoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*Mapeia url's, endereços, autoriza ou bloqueia acessos a url's*/
@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementacaoUserDetailsService implementacaoUserDetailsService;

    /*Configura as solicitações de acesso HTTP*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*Ativando a proteção contra usuários que não estão validados por token*/
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

        /*Ativandoa a permissão para acesso a página inicial do sistema EX: sistema.com.br/index.html */
                .disable().authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()

                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                /*URL de logout - Redireciona após o user deslogar do sistema*/
                .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")

                /*Mapeia URL de logOut e invalida o usuário*/
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                /*Filtra as requisições e login para autenticação*/
                .and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                /*Filtra as demais requisições para verificar a presença do TOKEN JWT no HEADER HTTP*/
                .addFilterBefore(new JWTApiAutenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*Service irá consultar o usuário no banco de dados*/
        auth.userDetailsService(implementacaoUserDetailsService)
                /*Padrão de configuração de senha do usuário*/
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
