package br.com.Software_MS.dtos;

import br.com.Software_MS.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  // Adicionado para permitir a criação de um objeto sem parâmetros
public class UsuarioDTO {

    private Long idUsuario;
    private String nome;
    private String email;
    private String tipoUsuario;

    //  para converter de UsuarioDTO para Usuario (se necessário em alguma operação de persistência)
    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(this.idUsuario);
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setTipoUsuario(this.tipoUsuario);
        return usuario;
    }

    // Método auxiliar para converter Usuario em UsuarioDTO
    public static UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario()
        );
    }
}