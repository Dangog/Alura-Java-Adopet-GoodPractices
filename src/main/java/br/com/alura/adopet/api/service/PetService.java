package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.Petdto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class PetService {

    @Autowired
    private PetRepository repository;

    public List<Petdto> listar() {
        return repository.findAllByAdotadoFalse()
                .stream()
                .map(Petdto::new)
                .toList();
    }

}
