package curso.api.rest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import curso.api.rest.model.Usuario;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*Estabelece o nosso gerenciador de token*/
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    /*Configurando o gerenciador de autenticação*/
    protected JWTLoginFilter(String url, AuthenticationManager authenticationManager) {

        /*Obriga a autenticar a url*/
        super(new AntPathRequestMatcher(url));

        /*Gerenciador de Autenticação*/
        setAuthenticationManager(authenticationManager);
    }

    /*Retorna o usuário ao processar a aplicação*/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {
        /*Está pegando o token para validar*/
        Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

        /*Retorna o usuário login, senha e acesso*/
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        new JWTTokenAutenticacaoService().addAuthentication(response, authResult.getName());
    }
}
