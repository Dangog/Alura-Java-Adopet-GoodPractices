package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.TutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class TutorService {

    @Autowired
    private TutorRepository repository;

    public void cadastrar(TutorDto tutorDto){
        boolean telefoneJaCadastrado = repository.existsByTelefone(tutorDto.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(tutorDto.email());

        if (telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        } else {
            Tutor tutor = new Tutor(tutorDto.nome(),tutorDto.email(),tutorDto.telefone());
            repository.save(tutor);
        }
    }


}
