package controllers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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

    private MouseAdapter mouseAdapter;

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

    JPanel leftPanel;
    JPanel rightPanel;

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
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getSource() instanceof JogoPanel panel) {
                    fillResponse(panel.getJogo());
                }
            }
        };

        jogos = jogoRepository.listAllJogos();
        generos = generoRepository.listAllGeneros();
        relacoes = relacaoRepository.listAllJogoGeneros();

        JPanel queryPanel = new JPanel();
        queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.Y_AXIS));
        queryPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        txtTituloFilter = new JTextField(12);

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

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(queryPanel);
        leftPanel.add(responsePanel);

        rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        Dimension rightDim = leftPanel.getPreferredSize();
        scrollPane = new JScrollPane();

        scrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        rightDim.width = 450;
        scrollPane.setPreferredSize(rightDim);

        jogosPanel = new JPanel();
        jogosPanel.setBackground(Color.cyan);

        refreshJogosGrid(jogos);
        fillGeneroBox();

        scrollPane.setViewportView(jogosPanel);
        rightPanel.add(scrollPane);

        rightPanel.revalidate();
        rightPanel.repaint();

        menu.add(leftPanel, BorderLayout.WEST);
        menu.add(rightPanel, BorderLayout.EAST);

        btnSearch.addActionListener(e -> {
            runQuery();
        });

        menu.pack();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

    private void refreshJogosGrid(List<Jogo> jogos) {
        int rows;

        jogosPanel.removeAll();

        if (jogos.size() % 3 == 0) rows = jogos.size()/3;
        else rows = jogos.size()/3 + 1;

        jogosPanel.setPreferredSize(new Dimension(260, rows*186));
        
        JogoPanel jogoPanel;
        for (Jogo jogo : jogos) {
            jogoPanel = new JogoPanel(jogo);

            jogoPanel.addMouseListener(mouseAdapter);

            jogosPanel.add(jogoPanel);
        }

        if (jogos.isEmpty()) {
            JLabel errLabel = new JLabel("Nenhum jogo encontrado :(");

            errLabel.setHorizontalAlignment(JLabel.CENTER);
            errLabel.setVerticalAlignment(JLabel.CENTER);

            Dimension errLabelDim = new Dimension(450, leftPanel.getPreferredSize().height - 20);

            errLabel.setMinimumSize(errLabelDim);
            errLabel.setMaximumSize(errLabelDim);
            errLabel.setPreferredSize(errLabelDim);

            errLabel.setFont(new Font("SansSerif", Font.BOLD,24));
            jogosPanel.add(errLabel);
        }
        
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void fillResponse(Jogo jogo) {
        lblTitulo.setText("<html> Título: "+jogo.getTitulo()+"</html>");

        String generoString = "<html> Gêneros: ";
        List<Genero> generos = relacaoRepository.listGenerosByJogo(jogo.getId());

        for (Genero genero : generos) {
            generoString += genero.getNome()+", ";
        }
        generoString = generoString.substring(0, generoString.length() - 2);
        generoString += "</html>";
        lblGenero.setText(generoString);

        lblDesenvolvedor.setText("<html> Desenvolvedor: "+jogo.getDesenvolvedor()+"</html>");

        String tipo = "Físico";
        if (jogo.isTipo()) tipo = "Digital";
        lblTipo.setText("<html> Tipo: "+tipo+"</html>");

        lblClassificacao.setText("<html> Classificação etária: "+jogo.getClassificacao()+"</html>");
        lblPreco.setText("<html> Preço: R$"+jogo.getPreco()+"</html>");
        lblLancamento.setText("<html> Lançamento: "+jogo.getLancamento()+"</html>");
    }

    private void fillGeneroBox() {
        generoFilterBox.addItem("Nada selecionado");

        for (Genero genero : generos) {
            generoFilterBox.addItem(genero.getNome());
        }
    }

    private void runQuery() {
        if (txtTituloFilter.getText().trim().isEmpty() && generoFilterBox.getSelectedIndex() < 1) {
            refreshJogosGrid(jogos);
            return;
        }

        List<Jogo> tituloFilteredJogos = null;
        List<Jogo> generoFilteredJogos = null;

        if (!txtTituloFilter.getText().trim().isEmpty() && generoFilterBox.getSelectedIndex() > 0) {
            tituloFilteredJogos = jogoRepository.listJogosByTitulo(txtTituloFilter.getText().trim());

            Genero selectedGenero = generos.get(generoFilterBox.getSelectedIndex() - 1);
            generoFilteredJogos = relacaoRepository.listJogosByGenero(selectedGenero.getId());

            List<Jogo> finalJogos = new ArrayList<>();
            for (Jogo jogo : tituloFilteredJogos) {
                for (Jogo jogo2 : generoFilteredJogos) {
                    if (jogo.getId() == jogo2.getId()) finalJogos.add(jogo);
                }
            }
            System.out.println(finalJogos);
            refreshJogosGrid(finalJogos);
            return;
        }

        if (!txtTituloFilter.getText().trim().isEmpty()) {
            tituloFilteredJogos = jogoRepository.listJogosByTitulo(txtTituloFilter.getText().trim());
            System.out.println(tituloFilteredJogos);
            refreshJogosGrid(tituloFilteredJogos);
        }
        
        if (generoFilterBox.getSelectedIndex() > 0) {
            Genero selectedGenero = generos.get(generoFilterBox.getSelectedIndex() - 1);

            generoFilteredJogos = relacaoRepository.listJogosByGenero(selectedGenero.getId());
            System.out.println(generoFilteredJogos);
            refreshJogosGrid(generoFilteredJogos);
        }
    }
    
    public void start() {}
}