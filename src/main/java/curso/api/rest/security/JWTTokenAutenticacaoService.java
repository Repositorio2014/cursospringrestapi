package curso.api.rest.security;

import curso.api.rest.ApplicationContextLoad;
import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
@Component
public class JWTTokenAutenticacaoService {

    /*Tempo de validade do token = 2 dias*/
    private static final long EXPIRATION_TIME = 172800000;

    /*Uma senha única para compor a autenticação para ajudar na segurança*/
    private static final String SECRET = "SenhaExtremamenteSecreta";

    /*Prefixo padrão de Token*/
    private static final String TOKEN_PREFIX = "Bearer";

    /**/
    private static final String HEADER_STRING = "Authorization";

    /*Gerando TOKEN de autenticação e adicionando ao cabeçalho a resposta http*/
    public void addAuthentication(HttpServletResponse response
                                    , String userName) throws IOException {

        /*Montagem do TOKEN*/
        String JWT = Jwts.builder() /*Chama o gerador de token*/
                .setSubject(userName) /*Adiciona o usuário*/
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /*Tempo de expiração*/
                .signWith(SignatureAlgorithm.HS512, SECRET).compact(); /*Compactação e algoritmo de geração de senha*/

        /*Junta tokem com o prefixo*/
        String token = TOKEN_PREFIX + " " + JWT; /*Bearer 8745847847475397593w7w78w*/

        /*Adiciona no cabeçalho*/
        response.addHeader(HEADER_STRING, token); /*Authorization: Bearer 8745847847475397593w7w78w*/

        /*Escrever Token como resposta*/
        response.getWriter().write("{\"Autorization\": \""+token+"\"}");

    }

    /*Retorna o usuário validado com o token. Caso não seja válido retorna null*/
    public Authentication getAuthentication(HttpServletRequest request){

        /*Pega o token enviado no cabeçalho HTTP*/
        String token = request.getHeader(HEADER_STRING);

        if(token != null){
            /*Faz a validação do token do usuário na requisição*/
            String user = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().getSubject();

            if(user != null){
                Usuario usuario = ApplicationContextLoad.getApplicationContext()
                        .getBean(UsuarioRepository.class).findUserByLogin(user);

                if(usuario != null){
                    return new UsernamePasswordAuthenticationToken(
                            usuario.getLogin(),
                            usuario.getSenha(),
                            usuario.getAuthorities());
                }
            }
        }
            return null; /*Não autorizado*/
    }


}
