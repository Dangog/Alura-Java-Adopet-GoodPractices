package br.com.alura.adopet.api.validations.validacaosolicitacaoadocao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaodto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel implements ValidacaoSolicitacaoAdocao{

    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaodto solicitacaoAdocaodto) {

        Pet pet = petRepository.getReferenceById(solicitacaoAdocaodto.idPet());

        if (pet.getAdotado() == true) {
            throw new ValidacaoException("Pet já foi adotado!");
        }
    }
}
