package br.com.services.Usuarios;

import br.com.dtos.Usuarios.UsuarioDTO;
import br.com.entities.Usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://usuario-core-service/usuarios";

    public List<UsuarioDTO> listarTodos() {
        ResponseEntity<UsuarioDTO[]> response = restTemplate.getForEntity(BASE_URL, UsuarioDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public UsuarioDTO salvarUsuario(Usuario usuario) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Usuario> request = new HttpEntity<>(usuario, headers);
        ResponseEntity<UsuarioDTO> response = restTemplate.postForEntity(BASE_URL, request, UsuarioDTO.class);

        return response.getBody();
    }

    public Optional<UsuarioDTO> buscarUsuarioPorId(Long id) {
        try {
            UsuarioDTO dto = restTemplate.getForObject(BASE_URL + "/" + id, UsuarioDTO.class);
            return Optional.ofNullable(dto);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public UsuarioDTO atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Usuario> request = new HttpEntity<>(usuarioAtualizado, headers);

        ResponseEntity<UsuarioDTO> response = restTemplate.exchange(
                BASE_URL + "/" + id,
                HttpMethod.PUT,
                request,
                UsuarioDTO.class
        );

        return response.getBody();
    }

    public boolean deletarUsuario(Long id) {
        try {
            restTemplate.delete(BASE_URL + "/" + id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validarLogin(String email, String senha) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> loginPayload = new HashMap<>();
        loginPayload.put("email", email);
        loginPayload.put("senha", senha);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(loginPayload, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(BASE_URL + "/login", request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Object sucesso = response.getBody().get("sucesso");
            return Boolean.TRUE.equals(sucesso);
        }
        return false;
    }
}
