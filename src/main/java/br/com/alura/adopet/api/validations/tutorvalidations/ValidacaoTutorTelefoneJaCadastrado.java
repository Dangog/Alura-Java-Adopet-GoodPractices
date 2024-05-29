package br.com.alura.adopet.api.validations.tutorvalidations;

import br.com.alura.adopet.api.dto.TutorDto;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoTutorTelefoneJaCadastrado implements ValidacaoTutor{

    @Autowired
    private TutorRepository repository;

    @Override
    public void validar(TutorDto tutorDto) {
        return repository.existsByTelefone(tutorDto.getTelefone());
    }
}
