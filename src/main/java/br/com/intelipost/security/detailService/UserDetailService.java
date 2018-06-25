package br.com.intelipost.security.detailService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.intelipost.modelo.Usuario;
import br.com.intelipost.repository.UsuarioRepository;

/**
 * Classe que implementa um serviço que busca o usuário no banco 
 * para autenticação e autorização para uso da api
 * @author willi
 *
 */
@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Usuario> optional = usuarioRepository.findByEmail(email);

		Usuario usuario = optional.orElseThrow(() -> new UsernameNotFoundException("usuario não encontrado"));
		
		return new User(usuario.getEmail(), usuario.getPassword(), getGrantedAuthority(usuario));
	}

	
	
	private Collection<? extends GrantedAuthority> getGrantedAuthority(Usuario usuario) {
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.getGrupo());
		
		HashSet<GrantedAuthority> hashSet = new HashSet<>();
		hashSet.add(authority);
		return hashSet;
	}

}
