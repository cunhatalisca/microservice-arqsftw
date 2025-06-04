package br.com.Software_MS.repositories;

import br.com.Software_MS.entities.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	 Optional<Usuario> findByEmail(String email);
}