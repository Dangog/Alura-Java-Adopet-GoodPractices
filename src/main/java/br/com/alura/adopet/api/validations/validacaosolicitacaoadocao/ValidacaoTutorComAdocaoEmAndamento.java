package br.com.alura.adopet.api.validations.validacaosolicitacaoadocao;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaodto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public void validar(SolicitacaoAdocaodto solicitacaoAdocaodto) {
        if (adocaoRepository.existsByTutorIdAndStatus(solicitacaoAdocaodto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO)) ;
        throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
    }

}

