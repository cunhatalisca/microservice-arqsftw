package br.com.Software_MS.services;

import br.com.Software_MS.dtos.UsuarioDTO;
import br.com.Software_MS.entities.Usuario;
import br.com.Software_MS.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Listar todos os usuários como DTOs
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO:: toDTO)
                .collect(Collectors.toList());
    }

    // Criar/Salvar um novo usuário
    public UsuarioDTO salvarUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return UsuarioDTO.toDTO(usuario);
    }

    // Buscar um usuário por ID
    public Optional<UsuarioDTO> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).map(UsuarioDTO:: toDTO);
    }

    // Atualizar usuário
    public UsuarioDTO atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNome(usuarioAtualizado.getNome());
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            usuarioExistente.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
            usuarioExistente.setTipoUsuario(usuarioAtualizado.getTipoUsuario());
            Usuario usuarioSalvo = usuarioRepository.save(usuarioExistente);
            return UsuarioDTO.toDTO(usuarioSalvo);
        }).orElse(null);
    }

    // Deletar um usuário por ID
    public boolean deletarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Validar login comparando a senha criptografada
    public boolean validarLogin(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return passwordEncoder.matches(senha, usuario.getSenha()); // Comparação de senha
        }
        return false;
    }

}