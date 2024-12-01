package controllers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import repositories.*;
import views.ManipularMenu;

public class MenuController {
    private JogoRepository jogoRepository;
    private GeneroRepository generoRepository;
    private JogoGeneroRepository relacaoRepository;
    private ManipularMenu menu;

    public MenuController() {
        jogoRepository = new JogoRepository();
        generoRepository = new GeneroRepository();
        relacaoRepository = new JogoGeneroRepository();
        menu = new ManipularMenu();
        initialize();
    }

    private void initialize() {
        //combobox dos jogos (para editar)
        JComboBox updateJogoBox = new JComboBox<>();
        updateJogoBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //combobox dos jogos (para deletar)
        JComboBox deleteJogoBox = new JComboBox<>();
        deleteJogoBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //combobox dos gêneros (para editar)
        JComboBox updateGeneroBox = new JComboBox<>();
        updateGeneroBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //combobox dos gêneros (para deletar)
        JComboBox deleteGeneroBox = new JComboBox<>();
        deleteGeneroBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //combobox das relações (para editar)
        JComboBox updateRelacaoBox = new JComboBox<>();
        updateRelacaoBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //combobox das relações (para deletar)
        JComboBox deleteRelacaoBox = new JComboBox<>();
        deleteRelacaoBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //toolbar das tabelas
        JToolBar toolBar = new JToolBar("Tabela", JToolBar.VERTICAL);
        JButton btnJogo = new JButton("Jogos");
        JButton btnGenero = new JButton("Gêneros");
        JButton btnRelacao = new JButton("Jog/Gên");
        toolBar.add(btnJogo);
        toolBar.add(btnGenero);
        toolBar.add(btnRelacao);
        menu.add(toolBar, BorderLayout.WEST);

        //toolbar de manipular jogos
        JToolBar jogoToolBar = new JToolBar("Jogos", JToolBar.VERTICAL);
        JButton btnCreateJogo = new JButton("Adicionar");
        JButton btnUpdateJogo = new JButton("Editar");
        JButton btnDeleteJogo = new JButton("Deletar");
        jogoToolBar.add(btnCreateJogo);
        jogoToolBar.add(btnUpdateJogo);
        jogoToolBar.add(btnDeleteJogo);
        menu.add(jogoToolBar, BorderLayout.CENTER);

        //toolbar de manipular generos
        JToolBar generoToolBar = new JToolBar("Generos", JToolBar.VERTICAL);
        JButton btnCreateGenero = new JButton("Adicionar");
        JButton btnUpdateGenero = new JButton("Editar");
        JButton btnDeleteGenero = new JButton("Deletar");
        generoToolBar.add(btnCreateGenero);
        generoToolBar.add(btnUpdateGenero);
        generoToolBar.add(btnDeleteGenero);
        menu.add(generoToolBar, BorderLayout.CENTER);

        generoToolBar.setVisible(false);

        //toolbar de manipular relações
        JToolBar relacaoToolBar = new JToolBar("Jog/Gên", JToolBar.VERTICAL);
        JButton btnCreateRelacao = new JButton("Adicionar");
        JButton btnUpdateRelacao = new JButton("Editar");
        JButton btnDeleteRelacao = new JButton("Deletar");
        relacaoToolBar.add(btnCreateRelacao);
        relacaoToolBar.add(btnUpdateRelacao);
        relacaoToolBar.add(btnDeleteRelacao);
        menu.add(relacaoToolBar, BorderLayout.CENTER);

        relacaoToolBar.setVisible(false);

        //painel vazio para o placeholder
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        emptyPanel.setPreferredSize(new Dimension(150, 100));
        emptyPanel.setLayout(new BorderLayout());
        menu.add(emptyPanel, BorderLayout.EAST);

        emptyPanel.setVisible(false);

        //painel de editar jogos
        JPanel updateJogoPanel = new JPanel();
        updateJogoPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        updateJogoPanel.setPreferredSize(new Dimension(150, 100));
        updateJogoPanel.setLayout(new BorderLayout());

        JButton updateJogo = new JButton("Editar");

        updateJogoPanel.add(new JLabel("Jogo:"), BorderLayout.NORTH);
        updateJogoPanel.add(updateJogoBox, BorderLayout.CENTER);
        updateJogoPanel.add(updateJogo, BorderLayout.SOUTH);

        menu.add(updateJogoPanel, BorderLayout.EAST);

        updateJogoPanel.setVisible(true);

        //painel de deletar jogos
        JPanel deleteJogoPanel = new JPanel();
        deleteJogoPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        deleteJogoPanel.setPreferredSize(new Dimension(150, 100));
        deleteJogoPanel.setLayout(new BorderLayout());

        JButton deleteJogo = new JButton("Deletar");

        deleteJogoPanel.add(new JLabel("Jogo:"), BorderLayout.NORTH);
        deleteJogoPanel.add(deleteJogoBox, BorderLayout.CENTER);
        deleteJogoPanel.add(deleteJogo, BorderLayout.SOUTH);

        menu.add(deleteJogoPanel, BorderLayout.EAST);

        deleteJogoPanel.setVisible(false);

        //painel de editar generos
        JPanel updateGeneroPanel = new JPanel();
        updateGeneroPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        updateGeneroPanel.setPreferredSize(new Dimension(150, 100));
        updateGeneroPanel.setLayout(new BorderLayout());

        JButton updateGenero = new JButton("Editar");

        updateGeneroPanel.add(new JLabel("Gênero:"), BorderLayout.NORTH);
        updateGeneroPanel.add(updateGeneroBox, BorderLayout.CENTER);
        updateGeneroPanel.add(updateGenero, BorderLayout.SOUTH);

        menu.add(updateGeneroPanel, BorderLayout.EAST);

        updateGeneroPanel.setVisible(false);

        //painel de deletar generos
        JPanel deleteGeneroPanel = new JPanel();
        deleteGeneroPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        deleteGeneroPanel.setPreferredSize(new Dimension(150, 100));
        deleteGeneroPanel.setLayout(new BorderLayout());

        JButton deleteGenero = new JButton("Deletar");

        deleteGeneroPanel.add(new JLabel("Gênero:"), BorderLayout.NORTH);
        deleteGeneroPanel.add(deleteGeneroBox, BorderLayout.CENTER);
        deleteGeneroPanel.add(deleteGenero, BorderLayout.SOUTH);

        menu.add(deleteGeneroPanel, BorderLayout.EAST);

        deleteGeneroPanel.setVisible(false);

        //painel de editar relacoes
        JPanel updateRelacaoPanel = new JPanel();
        updateRelacaoPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        updateRelacaoPanel.setPreferredSize(new Dimension(150, 100));
        updateRelacaoPanel.setLayout(new BorderLayout());

        JButton updateRelacao = new JButton("Editar");

        updateRelacaoPanel.add(new JLabel("Relação:"), BorderLayout.NORTH);
        updateRelacaoPanel.add(updateRelacaoBox, BorderLayout.CENTER);
        updateRelacaoPanel.add(updateRelacao, BorderLayout.SOUTH);

        menu.add(updateRelacaoPanel, BorderLayout.EAST);

        updateRelacaoPanel.setVisible(false);

        //painel de deletar relacoes
        JPanel deleteRelacaoPanel = new JPanel();
        deleteRelacaoPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        deleteRelacaoPanel.setPreferredSize(new Dimension(150, 100));
        deleteRelacaoPanel.setLayout(new BorderLayout());

        JButton deleteRelacao = new JButton("Deletar");

        deleteRelacaoPanel.add(new JLabel("Relação:"), BorderLayout.NORTH);
        deleteRelacaoPanel.add(deleteRelacaoBox, BorderLayout.CENTER);
        deleteRelacaoPanel.add(deleteRelacao, BorderLayout.SOUTH);

        menu.add(deleteRelacaoPanel, BorderLayout.EAST);

        deleteRelacaoPanel.setVisible(false);

        ArrayList<JComponent> toolbars = new ArrayList<JComponent>();

        toolbars.add(jogoToolBar);
        toolbars.add(generoToolBar);
        toolbars.add(relacaoToolBar);

        ArrayList<JComponent> panels = new ArrayList<JComponent>();

        panels.add(emptyPanel);
        panels.add(updateJogoPanel);
        panels.add(updateGeneroPanel);
        panels.add(updateRelacaoPanel);
        panels.add(deleteJogoPanel);
        panels.add(deleteGeneroPanel);
        panels.add(deleteRelacaoPanel);

        btnJogo.addActionListener(e -> {
            refreshElements(toolbars);
            jogoToolBar.setVisible(true);
            refreshElements(panels);
            emptyPanel.setVisible(true);
        });

        btnGenero.addActionListener(e -> {
            refreshElements(toolbars);
            generoToolBar.setVisible(true);
            refreshElements(panels);
            emptyPanel.setVisible(true);
        });

        btnRelacao.addActionListener(e -> {
            refreshElements(toolbars);
            relacaoToolBar.setVisible(true);
            refreshElements(panels);
            emptyPanel.setVisible(true);
        });

        btnCreateJogo.addActionListener(e -> {
            refreshElements(panels);
            emptyPanel.setVisible(true);
        });

        btnUpdateJogo.addActionListener(e -> {
            refreshElements(panels);
            updateJogoPanel.setVisible(true);
        });

        btnDeleteJogo.addActionListener(e -> {
            refreshElements(panels);
            deleteJogoPanel.setVisible(true);
        });

        btnCreateGenero.addActionListener(e -> {
            refreshElements(panels);
            emptyPanel.setVisible(true);
        });

        btnUpdateGenero.addActionListener(e -> {
            refreshElements(panels);
            updateGeneroPanel.setVisible(true);
        });

        btnDeleteGenero.addActionListener(e -> {
            refreshElements(panels);
            deleteGeneroPanel.setVisible(true);
        });

        btnCreateRelacao.addActionListener(e -> {
            refreshElements(panels);
            emptyPanel.setVisible(true);
        });

        btnUpdateRelacao.addActionListener(e -> {
            refreshElements(panels);
            updateRelacaoPanel.setVisible(true);
        });

        btnDeleteRelacao.addActionListener(e -> {
            refreshElements(panels);
            deleteRelacaoPanel.setVisible(true);
        });

        menu.pack();
        menu.setVisible(true);
    }

    private void refreshElements(ArrayList<JComponent> components) {
        for (JComponent elem : components) {
            elem.setVisible(false);
        }
    }

    public void start() {}
}
