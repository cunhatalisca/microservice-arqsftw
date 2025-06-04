package br.com.Software_MS.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name = "usuarios")
public class Usuario {
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false, length = 20)
    private String tipoUsuario;
}
