package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoCadastrodto;
import br.com.alura.adopet.api.dto.Abrigodto;
import br.com.alura.adopet.api.dto.Petdto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<Abrigodto>> listar() {
        List<Abrigodto> abrigos = abrigoService.listarAbrigos();
        return ResponseEntity.ok(abrigos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(AbrigoCadastrodto abrigoCadastrodto) {
        abrigoService.cadastrar(abrigoCadastrodto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<Petdto>> listarPets(@PathVariable String idOuNome) {
        List<Petdto> pets = abrigoService.listarPets(idOuNome);
        if (pets.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pets);
        }
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid Pet pet) {
       try {
           abrigoService.cadastrar(idOuNome,pet);
           return ResponseEntity.ok().build();
       } catch (Exception e){
           return ResponseEntity.notFound().build();
       }
    }

}
