package br.com.alura.adopet.api.validations;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaodto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidcaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaodto solicitacaoAdocaodto){

        if (adocaoRepository.existsByTutorIdAndStatus(solicitacaoAdocaodto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)) ;        List<Adocao> adocoes = adocaoRepository.findAll();
        {
                throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
            }
        }

    }
