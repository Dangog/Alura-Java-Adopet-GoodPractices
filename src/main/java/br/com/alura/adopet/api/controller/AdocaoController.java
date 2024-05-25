package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AprovarAdocaodto;
import br.com.alura.adopet.api.dto.ReprovarAdocaodto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaodto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.AdocaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    @Autowired
    private AdocaoService adocaoService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid SolicitacaoAdocaodto solicitacaoAdocaodto) {
        try {
            this.adocaoService.solicitar(solicitacaoAdocaodto);
            return ResponseEntity.ok("Adoção solicitada com sucesso");
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<String> aprovar(@RequestBody @Valid AprovarAdocaodto aprovarAdocaodto) {
        this.adocaoService.aprovar(aprovarAdocaodto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(@RequestBody @Valid ReprovarAdocaodto reprovarAdocaodto) {
        this.adocaoService.reprovar(reprovarAdocaodto);
        return ResponseEntity.ok().build();
    }

}
