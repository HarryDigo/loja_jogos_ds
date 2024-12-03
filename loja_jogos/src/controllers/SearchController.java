package controllers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import models.Genero;
import models.Jogo;
import models.JogoGenero;
import repositories.GeneroRepository;
import repositories.JogoGeneroRepository;
import repositories.JogoRepository;
import views.JogoPanel;
import views.SearchMenu;

public class SearchController {
    private final JogoRepository jogoRepository;
    private final GeneroRepository generoRepository;
    private final JogoGeneroRepository relacaoRepository;
    private final SearchMenu menu;

    private JTextField txtTituloFilter;

    private JComboBox<String> generoFilterBox;

    private JLabel lblTitulo;
    private JLabel lblGenero;
    private JLabel lblDesenvolvedor;
    private JLabel lblTipo;
    private JLabel lblClassificacao;
    private JLabel lblPreco;
    private JLabel lblLancamento;

    private JPanel jogosPanel;

    private JScrollPane scrollPane;

    private List<Jogo> jogos;
    private List<Genero> generos;
    private List<JogoGenero> relacoes;

    public SearchController() {
        jogoRepository = new JogoRepository();
        generoRepository = new GeneroRepository();
        relacaoRepository = new JogoGeneroRepository();
        menu = new SearchMenu();
        initialize();
    }

    private void initialize() {
        jogos = jogoRepository.listAllJogos();
        generos = generoRepository.listAllGeneros();
        relacoes = relacaoRepository.listAllJogoGeneros();

        JPanel queryPanel = new JPanel();
        queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.Y_AXIS));
        queryPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        txtTituloFilter = new JTextField(10);

        JPanel tituloPanel = new JPanel( new FlowLayout(FlowLayout.LEFT));
        tituloPanel.add(new JLabel("Titulo:"));
        tituloPanel.add(txtTituloFilter);

        queryPanel.add(tituloPanel);

        generoFilterBox = new JComboBox<>();
        generoFilterBox.setPreferredSize(txtTituloFilter.getPreferredSize());

        JPanel generoPanel = new JPanel( new FlowLayout(FlowLayout.LEFT));
        generoPanel.add(new JLabel("Gênero:"));
        generoPanel.add(generoFilterBox);

        queryPanel.add(generoPanel);

        JButton btnSearch = new JButton("Pesquisar");

        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        btnPanel.add(btnSearch);

        queryPanel.add(btnPanel);

        queryPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblTitulo = new JLabel("<html> Título: </html>");
        lblGenero = new JLabel("<html> Gêneros: </html>");
        lblDesenvolvedor = new JLabel("<html> Desenvolvedor: </html>");
        lblTipo = new JLabel("<html> Tipo: </html>");
        lblClassificacao = new JLabel("<html> Classificação etária: </html>");
        lblPreco = new JLabel("<html> Preço: </html>");
        lblLancamento = new JLabel("<html> Lançamento: </html>");

        JPanel responsePanel = new JPanel();
        responsePanel.setLayout(new BoxLayout(responsePanel, BoxLayout.Y_AXIS));
        responsePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        Dimension responseDim = queryPanel.getPreferredSize();

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelPanel.add(lblTitulo);
        labelPanel.add(lblGenero);
        labelPanel.add(lblDesenvolvedor);
        labelPanel.add(lblTipo);
        labelPanel.add(lblClassificacao);
        labelPanel.add(lblPreco);
        labelPanel.add(lblLancamento);

        responseDim.height = labelPanel.getPreferredSize().height + 98;
        responsePanel.setPreferredSize(responseDim);

        responsePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        responsePanel.add(labelPanel);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(queryPanel);
        leftPanel.add(responsePanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        Dimension rightDim = leftPanel.getPreferredSize();
        scrollPane = new JScrollPane();

        scrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        rightDim.width = 450;
        scrollPane.setPreferredSize(rightDim);

        jogosPanel = new JPanel();
        jogosPanel.setBackground(Color.cyan);

        refreshJogosGrid(jogos);

        scrollPane.setViewportView(jogosPanel);
        rightPanel.add(scrollPane);

        rightPanel.revalidate();
        rightPanel.repaint();

        menu.add(leftPanel, BorderLayout.WEST);
        menu.add(rightPanel, BorderLayout.EAST);

        menu.pack();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

    public void refreshJogosGrid(List<Jogo> jogos) {
        int rows;

        if (jogos.size() % 3 == 0) rows = jogos.size()/3;
        else rows = jogos.size()/3 + 1;

        jogosPanel.setPreferredSize(new Dimension(260, rows*186));
        
        for (Jogo jogo : jogos) {
            jogosPanel.add(new JogoPanel(jogo));
        }
    }
    
    public void start() {}
}