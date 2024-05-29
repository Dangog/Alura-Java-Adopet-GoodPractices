package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoCadastrodto;
import br.com.alura.adopet.api.dto.Abrigodto;
import br.com.alura.adopet.api.dto.Petdto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validations.validacaoabrigo.ValidacaoAbrigo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private List<ValidacaoAbrigo> validacaoAbrigoList;

    public void cadastrar(AbrigoCadastrodto dto) {

        validacaoAbrigoList.forEach(v -> v.validar(dto));

        Abrigo abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());
        repository.save(abrigo);
    }

    public List<Petdto> listarPets(String idOuNome){
        try {
            Long id = Long.parseLong(idOuNome);
            List<Pet> pets = repository.getReferenceById(id).getPets();
            return pets.stream()
                    .map(Petdto::new)
                    .toList();
        } catch (EntityNotFoundException enfe) {
            return null;
        } catch (NumberFormatException e) {
            try {
                List<Pet> pets = repository.findByNome(idOuNome).getPets();
                return pets.stream()
                        .map(Petdto::new)
                        .toList();
            } catch (EntityNotFoundException enfe) {
                return null;
            }
        }
    }

    public List<Abrigodto> listarAbrigos(){
        return repository.findAll()
                .stream()
                .map(Abrigodto::new)
                .toList();
    }

    public void cadastrar(String idOuNome, Pet pet ){
        try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = repository.getReferenceById(id);
            pet.setAbrigo(abrigo);
            pet.setAdotado(false);
            abrigo.getPets().add(pet);
            repository.save(abrigo);
        } catch (EntityNotFoundException enfe) {
            throw new ValidacaoException("Id informado não encontrado");
        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = repository.findByNome(idOuNome);
                pet.setAbrigo(abrigo);
                pet.setAdotado(false);
                abrigo.getPets().add(pet);
                repository.save(abrigo);
            } catch (EntityNotFoundException enfe) {
                throw new ValidacaoException("Id informado não encontrado");
            }
        }
    }


}
