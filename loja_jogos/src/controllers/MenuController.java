package controllers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import models.*;
import repositories.*;
import views.GeneroForm;
import views.JogoForm;
import views.JogoGeneroForm;
import views.ManipularMenu;

public class MenuController {
    private final JogoRepository jogoRepository;
    private final GeneroRepository generoRepository;
    private final JogoGeneroRepository relacaoRepository;
    private final ManipularMenu menu;

    private JComboBox<String> updateJogoBox;
    private JComboBox<String> deleteJogoBox;
    private JComboBox<String> updateGeneroBox;
    private JComboBox<String> deleteGeneroBox;
    private JComboBox<String> updateRelacaoBox;
    private JComboBox<String> deleteRelacaoBox;
    private ArrayList<JComboBox<String>> combos;

    private List<Jogo> jogos;
    private List<Genero> generos;
    private List<JogoGenero> relacoes;

    public MenuController() {
        jogoRepository = new JogoRepository();
        generoRepository = new GeneroRepository();
        relacaoRepository = new JogoGeneroRepository();
        menu = new ManipularMenu();
        initialize();
    }

    @SuppressWarnings("unused")
    private void initialize() {

        //combobox dos jogos (para editar)
        updateJogoBox = new JComboBox<>();
        updateJogoBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //combobox dos jogos (para deletar)
        deleteJogoBox = new JComboBox<>();
        deleteJogoBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //combobox dos gêneros (para editar)
        updateGeneroBox = new JComboBox<>();
        updateGeneroBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //combobox dos gêneros (para deletar)
        deleteGeneroBox = new JComboBox<>();
        deleteGeneroBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //combobox das relações (para editar)
        updateRelacaoBox = new JComboBox<>();
        updateRelacaoBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //combobox das relações (para deletar)
        deleteRelacaoBox = new JComboBox<>();
        deleteRelacaoBox.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        combos = new ArrayList<>();
        combos.add(updateJogoBox);
        combos.add(deleteJogoBox);
        combos.add(updateGeneroBox);
        combos.add(deleteGeneroBox);
        combos.add(updateRelacaoBox);
        combos.add(deleteRelacaoBox);

        refreshCombos(combos);

        //toolbar das tabelas
        JToolBar toolBar = new JToolBar("Tabela", JToolBar.VERTICAL);
        JButton btnJogo = new JButton("Jogos");
        JButton btnGenero = new JButton("Gêneros");
        JButton btnRelacao = new JButton("Relações");
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
        JToolBar relacaoToolBar = new JToolBar("Relações", JToolBar.VERTICAL);
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

        emptyPanel.setVisible(true);

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

        updateJogoPanel.setVisible(false);

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

        ArrayList<JComponent> toolbars = new ArrayList<>();

        toolbars.add(jogoToolBar);
        toolbars.add(generoToolBar);
        toolbars.add(relacaoToolBar);

        ArrayList<JComponent> panels = new ArrayList<>();

        panels.add(emptyPanel);
        panels.add(updateJogoPanel);
        panels.add(updateGeneroPanel);
        panels.add(updateRelacaoPanel);
        panels.add(deleteJogoPanel);
        panels.add(deleteGeneroPanel);
        panels.add(deleteRelacaoPanel);

        //listeners dos botões de navegação
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

        btnCreateJogo.addActionListener(e -> {
            createJogo();
        });

        updateJogo.addActionListener(e -> {
            updateJogo();
        });

        deleteJogo.addActionListener(e -> {
            deleteJogo();
        });

        btnCreateGenero.addActionListener(e -> {
            createGenero();
        });

        updateGenero.addActionListener(e -> {
            updateGenero();
        });

        deleteGenero.addActionListener(e -> {
            deleteGenero();
        });

        btnCreateRelacao.addActionListener(e -> {
            createRelacao();
        });

        updateRelacao.addActionListener(e -> {
            updateRelacao();
        });

        deleteRelacao.addActionListener(e -> {
            deleteRelacao();
        });

        menu.pack();
        menu.setVisible(true);
    }

    private void createJogo() {
        JogoForm form = new JogoForm(menu, "Adicionar jogo");
        form.setVisible(true);
        Jogo newJogo = form.getJogo();
        if (newJogo != null) {
            jogoRepository.createJogo(newJogo);
            refreshCombos(combos);
        }
    }

    private void updateJogo() {
        int selectedIndex = updateJogoBox.getSelectedIndex();

        if (selectedIndex != -1) {
            Jogo jogo = jogos.get(selectedIndex);
            
            JogoForm form = new JogoForm(menu, "Editar jogo", jogo);
            form.setVisible(true);
            Jogo updatedJogo = form.getJogo();

            if (updatedJogo != null) {
                updatedJogo = new Jogo(
                    jogo.getId(),
                    updatedJogo.getTitulo(), 
                    updatedJogo.getImagem(), 
                    updatedJogo.isTipo(), 
                    updatedJogo.getClassificacao(), 
                    updatedJogo.getDesenvolvedor(), 
                    updatedJogo.getPreco(), 
                    updatedJogo.getLancamento()
                );
                jogoRepository.updateJogo(updatedJogo);
                refreshCombos(combos);
            }
        } else {
            JOptionPane.showMessageDialog(
                menu, 
                "Adicione um jogo antes de tentar editar",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void deleteJogo() {
        int selectedIndex = deleteJogoBox.getSelectedIndex();

        if (selectedIndex != -1) {
            int optionValue = JOptionPane.showConfirmDialog(
                menu,
                "Tem certeza que deseja deletar este jogo?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );

            if (optionValue == JOptionPane.YES_OPTION) {
                jogoRepository.deleteJogo(jogos.get(selectedIndex).getId());
                refreshCombos(combos);
            }
        } else {
            JOptionPane.showMessageDialog(
                menu, 
                "Adicione um jogo antes de tentar deletar", 
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void createGenero() {
        GeneroForm form = new GeneroForm(menu, "Adicionar gênero");
        form.setVisible(true);
        Genero newGenero = form.getGenero();
        if (newGenero != null) {
            generoRepository.createGenero(newGenero);
            refreshCombos(combos);
        }
    }

    private void updateGenero() {
        int selectedIndex = updateGeneroBox.getSelectedIndex();

        if (selectedIndex != -1) {
            Genero genero = generos.get(selectedIndex);
            
            GeneroForm form = new GeneroForm(menu, "Editar gênero", genero);
            form.setVisible(true);
            Genero updatedGenero = form.getGenero();

            if (updatedGenero != null) {
                updatedGenero = new Genero(
                    genero.getId(),
                    updatedGenero.getNome()
                );
                generoRepository.updateGenero(updatedGenero);
                refreshCombos(combos);
            }
        } else {
            JOptionPane.showMessageDialog(
                menu, 
                "Adicione um gênero antes de tentar editar",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void deleteGenero() {
        int selectedIndex = deleteGeneroBox.getSelectedIndex();

        if (selectedIndex != -1) {
            int optionValue = JOptionPane.showConfirmDialog(
                menu,
                "Tem certeza que deseja deletar este gênero?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );

            if (optionValue == JOptionPane.YES_OPTION) {
                generoRepository.deleteGenero(generos.get(selectedIndex).getId());
                refreshCombos(combos);
            }
        } else {
            JOptionPane.showMessageDialog(
                menu, 
                "Adicione um gênero antes de tentar deletar", 
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void createRelacao() {
        JogoGeneroForm form = new JogoGeneroForm(menu, "Adicionar relação", jogos, generos, relacoes);
        form.setVisible(true);
        JogoGenero newRelacao = form.getJogoGenero();
        if (newRelacao != null) {
            relacaoRepository.createJogoGenero(newRelacao);
            refreshCombos(combos);
        }
    }

    private void updateRelacao() {
        int selectedIndex = updateRelacaoBox.getSelectedIndex();

        if (selectedIndex != -1) {
            JogoGenero relacao = relacoes.get(selectedIndex);
            
            JogoGeneroForm form = new JogoGeneroForm(menu, "Editar relação", jogos, generos, relacoes, relacao);
            form.setVisible(true);
            JogoGenero updatedRelacao = form.getJogoGenero();

            if (updatedRelacao != null) {
                updatedRelacao = new JogoGenero(
                    relacao.getId(),
                    updatedRelacao.getJogoId(),
                    updatedRelacao.getGeneroId()
                );
                relacaoRepository.updateJogoGenero(updatedRelacao);
                refreshCombos(combos);
            }
        } else {
            JOptionPane.showMessageDialog(
                menu, 
                "Adicione uma relação antes de tentar editar",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void deleteRelacao() {
        int selectedIndex = deleteRelacaoBox.getSelectedIndex();

        if (selectedIndex != -1) {
            int optionValue = JOptionPane.showConfirmDialog(
                menu,
                "Tem certeza que deseja deletar este gênero?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
            );

            if (optionValue == JOptionPane.YES_OPTION) {
                relacaoRepository.deleteJogoGenero(relacoes.get(selectedIndex).getId());
                refreshCombos(combos);
            }
        } else {
            JOptionPane.showMessageDialog(
                menu, 
                "Adicione uma relação antes de tentar deletar", 
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void refreshElements(ArrayList<JComponent> components) {
        for (JComponent elem : components) {
            elem.setVisible(false);
        }
    }

    private void refreshCombos(ArrayList<JComboBox<String>> combos) {
        jogos = jogoRepository.listAllJogos();
        generos = generoRepository.listAllGeneros();
        relacoes = relacaoRepository.listAllJogoGeneros();

        for (JComboBox<String> combo : combos) {
            combo.removeAllItems();
        }

        for (Jogo jogo : jogos) {
            combos.get(0).addItem(jogo.getTitulo());
            combos.get(1).addItem(jogo.getTitulo());
        }

        for (Genero genero : generos) {
            combos.get(2).addItem(genero.getNome());
            combos.get(3).addItem(genero.getNome());
        }

        for (JogoGenero relacao : relacoes) {
            combos.get(4).addItem(relacao.getId()+", "+relacao.getJogoId()+", "+relacao.getGeneroId());
            combos.get(5).addItem(relacao.getId()+", "+relacao.getJogoId()+", "+relacao.getGeneroId());
        }
    }

    public void start() {}
}
