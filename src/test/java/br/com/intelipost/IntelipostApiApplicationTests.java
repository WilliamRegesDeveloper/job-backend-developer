package br.com.intelipost;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.intelipost.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntelipostApiApplicationTests {

	@Mock
	private UsuarioRepository repos;
	
	
	@Test
	public void contextLoads() {
		
	   
	}
	
	@Test
	public void cryptId() {
		
		BCryptPasswordEncoder cript = new BCryptPasswordEncoder();
		 
	   System.out.println(cript.encode("1234"));
	   
	}

}
