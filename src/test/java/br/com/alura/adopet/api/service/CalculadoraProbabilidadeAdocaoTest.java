package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    @DisplayName("Probabilidade alta para gatos jovens com peso baixo")
    void probabilidadeAltaCenario1() {

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo dos cria", "(12)3456-7890","abrigodoscria@gmail.com"));
        CadastroPetDto petDto = new CadastroPetDto(TipoPet.GATO,"Seu Jorge","Vagabundo",4,"Todas",4f);

        Pet pet = new Pet(petDto,abrigo);

        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);

       Assertions.assertEquals(ProbabilidadeAdocao.ALTA,probabilidadeAdocao);
    }

    @Test
    @DisplayName("Probabilidade alta para gatos jovens com peso baixo")
    void probabilidadeMediaCenario1() {
        //idade 15 anos e 4kg - MEDIA

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "94999999999",
                "abrigofeliz@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Fia da Puta",
                "Medíocre",
                15,
                "Rosa Choque (Claro)",
                4.0f
        ), abrigo);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
    }

}