package controllers;

import java.util.List;
import models.Genero;
import models.Jogo;
import models.JogoGenero;
import repositories.GeneroRepository;
import repositories.JogoGeneroRepository;
import repositories.JogoRepository;
import views.TableMenu;

public class TableController {
    private final JogoRepository jogoRepository;
    private final GeneroRepository generoRepository;
    private final JogoGeneroRepository relacaoRepository;
    private final TableMenu menu;

    public TableController() {
        jogoRepository = new JogoRepository();
        generoRepository = new GeneroRepository();
        relacaoRepository = new JogoGeneroRepository();
        menu = new TableMenu();
        initialize();
    }

    private void initialize() {
        List<Jogo> jogos = jogoRepository.listAllJogos();
        List<Genero> generos = generoRepository.listAllGeneros();
        List<JogoGenero> relacoes = relacaoRepository.listAllJogoGeneros();
        menu.fillTables(jogos, generos, relacoes);
    }

    public void start() {}
}
