package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovarAdocaodto;
import br.com.alura.adopet.api.dto.ReprovarAdocaodto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaodto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validations.validacaosolicitacaoadocao.ValidacaoSolicitacaoAdocao;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdocaoService {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    List<ValidacaoSolicitacaoAdocao> validacaoSolicitacaoAdocaoList;

    public void solicitar(SolicitacaoAdocaodto solicitacaoAdocaodto) {

        Pet pet = petRepository.getReferenceById(solicitacaoAdocaodto.idPet());
        Tutor tutor = tutorRepository.getReferenceById(solicitacaoAdocaodto.idTutor());

        validacaoSolicitacaoAdocaoList.forEach(v -> v.validar(solicitacaoAdocaodto));
        
        Adocao adocao = new Adocao(tutor,pet, solicitacaoAdocaodto.motivo());
        adocaoRepository.save(adocao);

        emailService.enviarEmail(adocao.getPet().getAbrigo().getEmail(),
                "Solicitação de adoção","Olá " + adocao.getPet().getAbrigo().getNome() + "!\n\nUma solicitação de adoção foi registrada hoje para o pet: " + adocao.getPet().getNome() + ". \nFavor avaliar para aprovação ou reprovação.");
    }

    public void aprovar(AprovarAdocaodto aprovarAdocaodto) {

        Adocao adocao = adocaoRepository.getReferenceById(aprovarAdocaodto.idAdocao());

        adocao.setStatus(StatusAdocao.APROVADO);
        adocaoRepository.save(adocao);

        emailService.enviarEmail(adocao.getTutor().getEmail(),
                "Adoção aprovada",
                "Parabéns " + adocao.getTutor().getNome() + "!\n\nSua adoção do pet " + adocao.getPet().getNome() + ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", foi aprovada.\nFavor entrar em contato com o abrigo " + adocao.getPet().getAbrigo().getNome() + " para agendar a busca do seu pet.");
    }

    public void reprovar(ReprovarAdocaodto reprovarAdocaodto) {

        Adocao adocao = adocaoRepository.getReferenceById(reprovarAdocaodto.idAdocao());

        adocao.setStatus(StatusAdocao.REPROVADO);
        adocao.setJustificativaStatus(reprovarAdocaodto.justificativa());
        adocaoRepository.save(adocao);

        emailService.enviarEmail(adocao.getTutor().getEmail(),
                "Adoção reprovada",
                "Olá " + adocao.getTutor().getNome() + "!\n\nInfelizmente sua adoção do pet " + adocao.getPet().getNome() + ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", foi reprovada pelo abrigo " + adocao.getPet().getAbrigo().getNome() + " com a seguinte justificativa: " + adocao.getJustificativaStatus());
    }
}
