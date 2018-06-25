package br.com.intelipost.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.intelipost.modelo.Usuario;
/**
 * Interface que representa o reposotorio da
 * tabela de usuários
 * @author willi
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail(String email);
	
}
