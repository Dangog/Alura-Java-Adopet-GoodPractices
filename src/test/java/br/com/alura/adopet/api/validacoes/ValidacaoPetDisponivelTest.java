package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @InjectMocks
    private ValidacaoPetDisponivel validacaoPetDisponivel;

    @Mock
    private PetRepository petRepository;

    @Mock
    Pet pet;

    @Mock
    SolicitacaoAdocaoDto dto;

    @Test
    void deveriaPermitirAdocao() {

        SolicitacaoAdocaoDto solicitacaoAdocaoDto = new SolicitacaoAdocaoDto(1L, 2L,"Não posuo essa cor");

        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);

        Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validar(solicitacaoAdocaoDto));

    }


    @Test
    void naodeveriaPermitirAdocao() {

        SolicitacaoAdocaoDto solicitacaoAdocaoDto = new SolicitacaoAdocaoDto(1L, 2L,"Não posuo essa cor");

        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        Assertions.assertThrows(ValidacaoException.class,()-> validacaoPetDisponivel.validar(solicitacaoAdocaoDto));

    }
}