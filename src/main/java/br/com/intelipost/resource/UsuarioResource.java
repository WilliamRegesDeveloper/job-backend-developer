package br.com.intelipost.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.intelipost.action.RecursoCriado;
import br.com.intelipost.modelo.Usuario;
import br.com.intelipost.repository.UsuarioRepository;

/***
 * Classe CONTROLLER para atender requisições de usuários
 * @author willi
 *
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ApplicationEventPublisher publish;
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Usuario> buscaUsuarioPorEmail(@PathVariable("email") String email){
		
		Optional<Usuario> optonal = usuarioRepository.findByEmail(email);
		
		if(!optonal.isPresent())
			return ResponseEntity.notFound().build();
				
		return ResponseEntity.status(HttpStatus.OK).body(optonal.get());
	}
	
	@PostMapping
	public ResponseEntity<Usuario> salvarUsuario(@Valid @RequestBody Usuario usuario, HttpServletResponse response){
		
		Optional<Usuario> optonal = usuarioRepository.findByEmail(usuario.getEmail());
		
		if(optonal.isPresent())
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		
		Usuario save = usuarioRepository.save(usuario);
		publish.publishEvent(new RecursoCriado(this, save.getId(), response));
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
}
