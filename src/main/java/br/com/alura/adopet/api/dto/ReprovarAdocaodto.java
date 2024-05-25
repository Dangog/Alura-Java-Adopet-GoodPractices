package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReprovarAdocaodto(@NotNull  Long idAdocao,@NotBlank String justificativa) {
}
