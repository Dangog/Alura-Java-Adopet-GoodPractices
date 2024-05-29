package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;

public record Abrigodto(String nome, String telefone, String email) {

    public Abrigodto (Abrigo abrigo) {this(abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail());}

}
