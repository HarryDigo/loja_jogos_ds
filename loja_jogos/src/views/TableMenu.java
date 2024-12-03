package views;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import models.Genero;
import models.Jogo;
import models.JogoGenero;

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

    @SuppressWarnings("unused")
    private void initialize() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                InitialMenu initialMenu = new InitialMenu();
                initialMenu.setVisible(true);
            }
        });

        JToolBar toolbar = new JToolBar();
        JButton btnJogo = new JButton("Jogos");
        JButton btnGenero = new JButton("Gêneros");
        JButton btnRelacao = new JButton("Relações");
        toolbar.add(btnJogo);
        toolbar.add(btnGenero);
        toolbar.add(btnRelacao);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        scrollPane.setLayout(new ScrollPaneLayout());

        String[] jogoColumns = {"Id", "Título", "Tipo", "Classificação", "Desenvolvedor", "Preço", "Lançamento"};
        jogoTableModel = new DefaultTableModel(jogoColumns, 0);
        jogoTable = new JTable(jogoTableModel);
        scrollPane.setViewportView(jogoTable);
        
        String[] generoColumns = {"Id", "Nome"};
        generoTableModel = new DefaultTableModel(generoColumns, 0);
        generoTable = new JTable(generoTableModel);
        
        String[] relacaoColumns = {"Id", "JogoId", "Jogo", "GêneroId", "Gênero"};
        relacaoTableModel = new DefaultTableModel(relacaoColumns, 0);
        relacaoTable = new JTable(relacaoTableModel);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        this.setContentPane(contentPanel);

        this.add(toolbar, BorderLayout.NORTH);

        this.add(scrollPane, BorderLayout.CENTER);

        btnJogo.addActionListener(e -> {
            scrollPane.setViewportView(jogoTable);
            scrollPane.revalidate();
            scrollPane.repaint();
        });

        btnGenero.addActionListener(e -> {
            scrollPane.setViewportView(generoTable);
            scrollPane.revalidate();
            scrollPane.repaint();
        });

        btnRelacao.addActionListener(e -> {
            scrollPane.setViewportView(relacaoTable);
            scrollPane.revalidate();
            scrollPane.repaint();
        });

        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void fillTables(List<Jogo> jogos, List<Genero> generos, List<JogoGenero> relacoes) {
        jogoTableModel.setRowCount(0);
        generoTableModel.setRowCount(0);
        relacaoTableModel.setRowCount(0);
        
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
                relacao.getJogoId(),
                jogoTitulo,
                relacao.getGeneroId(),
                generoNome
            };
            relacaoTableModel.addRow(row);
        }
    }
}
