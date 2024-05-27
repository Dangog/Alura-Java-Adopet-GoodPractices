package br.com.alura.adopet.api.validations.validacaoabrigo;

import br.com.alura.adopet.api.dto.AbrigoCadastrodto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoAbrigoEmailJaCadastrado implements ValidacaoAbrigo {

    @Autowired
    private AbrigoRepository repository;

    @Override
    public void validar(AbrigoCadastrodto dto) {
        if (repository.existsByEmail(dto.email())){
            throw new ValidacaoException("Email do abrigo já previamente cadastrado");
        }
    }
}
