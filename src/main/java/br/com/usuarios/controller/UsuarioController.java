package br.com.controllers.Usuarios;

import br.com.dtos.Usuarios.UsuarioDTO;
import br.com.entities.Usuarios.Usuario;
import br.com.services.Usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos os usuários
    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {

        return usuarioService.listarTodos();
    }

    // Criar um novo usuário
    @PostMapping
    public UsuarioDTO criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    // Buscar um usuário por ID
    @GetMapping("/{id}")
    public UsuarioDTO buscarUsuario(@PathVariable Long id) {
        Optional<UsuarioDTO> usuario = usuarioService.buscarUsuarioPorId(id);
        return usuario.orElse(null); // Retorna o usuário ou null caso não encontrado
    }

    // Atualizar um usuário existente
    @PutMapping("/{id}")
    public UsuarioDTO atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioService.atualizarUsuario(id, usuarioAtualizado);
    }

    // Deletar um usuário por ID
    @DeleteMapping("/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        return usuarioService.deletarUsuario(id) ? "Usuário deletado com sucesso" : "Usuário não encontrado";
    }

    // validar login
    @PostMapping("/login")
    public Map<String, Object> validarLogin(@RequestBody Usuario usuario) {
        boolean loginValido = usuarioService.validarLogin(usuario.getEmail(), usuario.getSenha());

        //Hash map para estruturar melhor a resposta
        Map<String, Object> response = new HashMap<>();
        response.put("sucesso", loginValido);
        if (loginValido) {
            response.put("mensagem", "Login realizado com sucesso");
        } else {
            response.put("mensagem", "Email ou senha inválidos");
        }
        return response;
    }
}