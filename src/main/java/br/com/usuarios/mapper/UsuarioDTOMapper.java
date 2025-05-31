package br.com.dtos.Usuarios;

import br.com.entities.Usuarios.Usuario;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UsuarioDTOMapper implements Function<Usuario, UsuarioDTO> {
    @Override
    public UsuarioDTO apply(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                usuario.getEscola()
        );
    }
}
