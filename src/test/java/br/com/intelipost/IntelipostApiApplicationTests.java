package br.com.intelipost;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Optional;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import br.com.intelipost.modelo.Usuario;
import br.com.intelipost.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntelipostApiApplicationTests {

	@Mock
	private UsuarioRepository repos;
	
	
	/**
	 * Teste de criptografia de número para password
	 */
	@Test
	public void cryptId() {
		
		BCryptPasswordEncoder cript = new BCryptPasswordEncoder();
	   System.out.println(cript.encode("1234"));
	   
	}
	
	
	/**
	 * Teste para buscar usuário pelo email
	 */
	@Test
	public void buscaUsuarioEmail(){
		Optional<Usuario> optional = repos.findByEmail("william@gmail.com.br");
		Usuario usuario = optional.get();
		assertNotNull(usuario);
	}
	
	/**
	 * Teste para buscar informações do usuário pelo email. Essa requisição
	 * está sendo feito uma autenticação basic de login e senha
	 * @throws AuthenticationException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void buscarWebEmail() throws AuthenticationException, ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://intelipost-api.herokuapp.com/usuarios/email/william@gmail.com.br");
		
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("william@gmail.com.br", "1234");
		httpGet.setHeader(new BasicScheme().authenticate(credentials, httpGet, null));
		
		CloseableHttpResponse response = httpClient.execute(httpGet);
		assertNotNull(response);
		
		InputStream inputStream = response.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		Gson gson = new Gson();
		HashMap<?,?> map = gson.fromJson(reader, HashMap.class);
		
	    
	    assertThat(map.get("email")).isEqualTo("william@gmail.com.br");
	     
		
	}

}
