package br.com.exceptions;

public class UsuarioReferenciadoException extends RuntimeException {
    public UsuarioReferenciadoException(String mensagem) {
        super(mensagem);
    }
}
