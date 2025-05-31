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

@CrossOrigin(origins = "http://localhost:4200")
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
        return usuarioService.buscarUsuarioPorId(id);
    }

    // Atualizar um usuário existente
    @PutMapping("/{id}")
    public UsuarioDTO atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioService.atualizarUsuario(id, usuarioAtualizado);
    }

    // Deletar um usuário por ID
    @DeleteMapping("/{id}")
    public Map<String, Object> deletarUsuario(@PathVariable Long id) {
        boolean deletado = usuarioService.deletarUsuario(id);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("sucesso", deletado);
        resposta.put("mensagem", deletado ? "Usuário deletado com sucesso" : "Usuário não encontrado");

        return resposta;
    }

    // validar login
    @PostMapping("/login")
    public Map<String, Object> validarLogin(@RequestBody Usuario usuario) {
        Map<String, Object> resultado = usuarioService.validarLogin(usuario.getEmail(), usuario.getSenha());

        Map<String, Object> response = new HashMap<>();
        boolean loginValido = (boolean) resultado.get("sucesso");

        response.put("sucesso", loginValido);
        if (loginValido) {
            response.put("mensagem", "Login realizado com sucesso");
            response.put("usuario", resultado.get("usuario")); // Inclui todas as informações do DTO
        } else {
            response.put("mensagem", "Email ou senha inválidos");
        }
        return response;
    }
}
