package views;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import models.*;

public class JogoGeneroForm extends JDialog {
    private JComboBox<String> jogoBox;
    private JComboBox<String> generoBox;

    private JButton btnSave;
    private JButton btnCancel;

    private JogoGenero relacao;
    private final boolean isEditMode;

    private final List<Jogo> jogos;
    private final List<Genero> generos;
    private final List<JogoGenero> relacoes;

    public JogoGeneroForm(Frame parent, String title, List<Jogo> jogos, List<Genero> generos, List<JogoGenero> relacoes) {
        super(parent, title, true);
        this.jogos = jogos;
        this.generos = generos;
        this.relacoes = relacoes;
        this.isEditMode = false;
        initialize();
    }

    public JogoGeneroForm(Frame parent, String title, List<Jogo> jogos, List<Genero> generos, List<JogoGenero> relacoes, JogoGenero relacao) {
        super(parent, title, true);
        this.jogos = jogos;
        this.generos = generos;
        this.relacoes = relacoes;
        this.relacao = relacao;
        this.isEditMode = true;
        initialize();
        fillFields();
    }

    @SuppressWarnings("unused")
    private void initialize() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jogoBox = new JComboBox<>();

        for (Jogo jogo : jogos) {
            jogoBox.addItem(jogo.getTitulo());
        }

        generoBox = new JComboBox<>();

        for (Genero genero : generos) {
            generoBox.addItem(genero.getNome());
        }

        btnSave = new JButton("Salvar");
        btnCancel = new JButton("Cancelar");

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("Jogo:"));
        panel.add(jogoBox);
        panel.add(new JLabel("Gênero:"));
        panel.add(generoBox);
        panel.add(btnSave);
        panel.add(btnCancel);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnSave.addActionListener(e -> {
            if (validateFields()) {
                if (isEditMode) {
                    updateRelacao();
                } else {
                    createRelacao();
                }
                dispose();
            }
        });

        btnCancel.addActionListener(e -> dispose());

        this.add(panel);
        this.setSize(300, 160);
        this.setLocationRelativeTo(getParent());
    }

    private void fillFields() {

        if (relacao != null) {
            String selectedJogo = null;
            String selectedGenero = null;

            for (Jogo jogo : jogos) {
                if (relacao.getJogoId() == jogo.getId()) {
                    for (Genero genero : generos) {
                        if (relacao.getGeneroId() == genero.getId()) {
                            selectedJogo = jogo.getTitulo();
                            selectedGenero = genero.getNome();
                        }
                    }
                }
            }

            jogoBox.setSelectedItem(selectedJogo);
            generoBox.setSelectedItem(selectedGenero);
        }
    }

    private boolean validateFields() {
        if (jogoBox.getSelectedIndex() > -1 && generoBox.getSelectedIndex() > -1) {
            int selectedJogo = jogoBox.getSelectedIndex();
            int selectedJogoId = jogos.get(selectedJogo).getId();

            int selectedGenero = generoBox.getSelectedIndex();
            int selectedGeneroId = generos.get(selectedGenero).getId();

            for (JogoGenero arrRelacao : relacoes) {
                if (arrRelacao.getJogoId() == selectedJogoId && arrRelacao.getGeneroId() == selectedGeneroId) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Esta relação já existe",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return false;
                }
            }

            return true;
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Todos os campos são obrigatórios",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    private void createRelacao() {
        int selectedJogo = jogoBox.getSelectedIndex();
        int selectedJogoId = jogos.get(selectedJogo).getId();

        int selectedGenero = generoBox.getSelectedIndex();
        int selectedGeneroId = generos.get(selectedGenero).getId();

        relacao = new JogoGenero(selectedJogoId, selectedGeneroId);

        System.out.println(relacao.toString());
    }

    private void updateRelacao() {
        int selectedJogo = jogoBox.getSelectedIndex();
        int selectedJogoId = jogos.get(selectedJogo).getId();

        int selectedGenero = generoBox.getSelectedIndex();
        int selectedGeneroId = generos.get(selectedGenero).getId();

        if (relacao != null) {
            relacao.setJogoId(selectedJogoId);
            relacao.setGeneroId(selectedGeneroId);
        }

        System.out.println(relacao.toString());
    }

    public JogoGenero getJogoGenero() {
        return relacao;
    }
}
