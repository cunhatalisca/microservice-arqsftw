package br.com.repositories.Usuarios;

import br.com.entities.Usuarios.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	 Optional<Usuario> findByEmail(String email);
     boolean existsByEmail(String email);
}