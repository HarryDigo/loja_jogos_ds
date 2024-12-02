package views;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import models.*;

public class TableMenu extends JFrame {
    private JTable jogoTable;
    private JTable generoTable;
    private JTable relacaoTable;
    private DefaultTableModel jogoTableModel;
    private DefaultTableModel generoTableModel;
    private DefaultTableModel relacaoTableModel;

    public TableMenu() {
        super("Visualização de Tabelas");
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] jogoColumns = {"Id", "Título", "Tipo", "Classificação", "Desenvolvedor", "Preço", "Lançamento"};
        jogoTableModel = new DefaultTableModel(jogoColumns, 0);
        jogoTable = new JTable(jogoTableModel);
        JScrollPane jogoScrollPane = new JScrollPane(jogoTable);
        jogoScrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        jogoScrollPane.setVisible(true);
        
        String[] generoColumns = {"Id", "Nome"};
        generoTableModel = new DefaultTableModel(generoColumns, 0);
        generoTable = new JTable(generoTableModel);
        JScrollPane generoScrollPane = new JScrollPane(generoTable);
        generoScrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        jogoScrollPane.setVisible(false);
        
        String[] relacaoColumns = {"Id", "Jogo", "Gênero"};
        relacaoTableModel = new DefaultTableModel(relacaoColumns, 0);
        relacaoTable = new JTable(relacaoTableModel);
        JScrollPane relacaoScrollPane = new JScrollPane(relacaoTable);
        relacaoScrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        jogoScrollPane.setVisible(false);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        this.setContentPane(contentPanel);

        this.add(jogoScrollPane, BorderLayout.CENTER);
        this.add(generoScrollPane, BorderLayout.CENTER);
        this.add(relacaoScrollPane, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(getParent());
    }

    public void fillTables(List<Jogo> jogos, List<Genero> generos, List<JogoGenero> relacoes) {
        for (Jogo jogo : jogos) {
            Object[] row = {
                jogo.getId(),
                jogo.getTitulo(),
                jogo.isTipo(),
                jogo.getClassificacao(),
                jogo.getDesenvolvedor(),
                jogo.getPreco(),
                jogo.getLancamento()
            };
            jogoTableModel.addRow(row);
        }

        for (Genero genero : generos) {
            Object[] row = {
                genero.getId(),
                genero.getNome()
            };
            generoTableModel.addRow(row);
        }

        for (JogoGenero relacao : relacoes) {
            int jogoId = relacao.getJogoId();
            int generoId = relacao.getGeneroId();
            String jogoTitulo = null;
            String generoNome = null;

            for (Jogo jogo : jogos) {
                if (jogo.getId() == jogoId) {
                    jogoTitulo = jogo.getTitulo();
                }
            }

            for (Genero genero : generos) {
                if (genero.getId() == generoId) {
                    generoNome = genero.getNome();
                }
            }

            Object[] row = {
                relacao.getId(),
                jogoTitulo,
                generoNome
            };
            relacaoTableModel.addRow(row);
        }
    }
}
