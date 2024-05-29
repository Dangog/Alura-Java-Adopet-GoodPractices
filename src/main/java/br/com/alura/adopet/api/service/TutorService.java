package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.TutorDto;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class TutorService {

    @Autowired
    private TutorRepository repository;

    public void cadastrar(TutorDto tutorDto){

        boolean emailJaCadastrado = repository.existsByEmail(tutorDto.getEmail());

        if (telefoneJaCadastrado || emailJaCadastrado) {
            return ResponseEntity.badRequest().body("Dados já cadastrados para outro tutor!");
        } else {
            repository.save(tutor);
            return ResponseEntity.ok().build();

        }
    }


}
