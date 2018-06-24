package br.com.intelipost.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Usuario> buscaUsuarioPorEmail(@PathVariable("email") String email){
		
		Optional<Usuario> optonal = usuarioRepository.findByEmail(email);
		
		if(!optonal.isPresent())
			return ResponseEntity.notFound().build();
				
		return ResponseEntity.status(HttpStatus.OK).body(optonal.get());
	}
}
