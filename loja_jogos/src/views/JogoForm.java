package views;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.*;
import models.Jogo;

public class JogoForm extends JDialog {
    private JTextField txtTitulo;
    private JTextField txtDesenvolvedor;

    private NumberFormat classificacaoFormat;
    private JTextField txtClassificacao;

    private NumberFormat precoFormat; // = new DecimalFormat("####0.00");
    private JFormattedTextField txtPreco; // = new JFormattedTextField(precoFormat);

    private DateFormat lancamentoFormat; // = new SimpleDateFormat("yyyy-MM-dd");
    private JFormattedTextField txtLancamento; // = new JFormattedTextField(lancamentoFormat);

    private JRadioButton radioBtnFisico;
    private JRadioButton radioBtnDigital;

    private ButtonGroup tipoRadioGroup;

    private JFileChooser imgChooser; // = new JFileChooser();
    private JButton btnImg;

    private JButton btnSave;
    private JButton btnCancel;

    private Jogo jogo;
    private final boolean isEditMode;

    private java.sql.Blob selectedImg;
    private byte[] imgContent;
    private File imgFile;

    private boolean tipo;

    public JogoForm(Frame parent, String title) {
        super(parent, title, true);
        this.isEditMode = false;
        initialize();
    }

    public JogoForm(Frame parent, String title, Jogo jogo) {
        super(parent, title, true);
        this.jogo = jogo;
        this.isEditMode = false;
        initialize();
        fillFields();
    }

    @SuppressWarnings("unused")
    private void initialize() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        txtTitulo = new JTextField(20);
        txtDesenvolvedor = new JTextField(20);
        
        classificacaoFormat = new DecimalFormat("#0");
        txtClassificacao = new JFormattedTextField(classificacaoFormat);
        
        DecimalFormatSymbols teste = new DecimalFormatSymbols();
        teste.setDecimalSeparator('.');

        precoFormat = new DecimalFormat("####0.00", teste);
        txtPreco = new JFormattedTextField(precoFormat);

        lancamentoFormat = new SimpleDateFormat("yyyy-MM-dd");
        txtLancamento = new JFormattedTextField(lancamentoFormat);

        radioBtnFisico = new JRadioButton();
        radioBtnDigital = new JRadioButton();

        radioBtnFisico.setText("Físico");
        radioBtnDigital.setText("Digital");

        tipoRadioGroup = new ButtonGroup();

        tipoRadioGroup.add(radioBtnFisico);
        tipoRadioGroup.add(radioBtnDigital);

        imgChooser = new JFileChooser();

        btnImg = new JButton("Selecionar");

        btnSave = new JButton("Salvar");
        btnCancel = new JButton("Cancelar");

        JPanel panel = new JPanel(new GridLayout(5, 4, 10, 10));
        panel.add(new JLabel("Título:"));
        panel.add(txtTitulo);
        panel.add(new JLabel("Tipo:"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Desenvolvedor:"));
        panel.add(txtDesenvolvedor);
        panel.add(radioBtnFisico);
        panel.add(radioBtnDigital);
        panel.add(new JLabel("Lançamento:"));
        panel.add(txtLancamento);
        panel.add(new JLabel("Imagem:"));
        panel.add(btnImg);
        panel.add(new JLabel("Preço:"));
        panel.add(txtPreco);
        panel.add(new JLabel("Classificação etária:"));
        panel.add(txtClassificacao);
        panel.add(btnSave);
        panel.add(btnCancel);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnSave.addActionListener(e -> {
            if (validateFields()) {
                if (isEditMode) {
                    updateJogo();
                } else {
                    createJogo();
                }
                dispose();
            }
        });

        btnImg.addActionListener(e -> {
            int value = imgChooser.showOpenDialog(panel);

            if (value == JFileChooser.APPROVE_OPTION) {
                imgFile = imgChooser.getSelectedFile();

                try {
                    imgContent = Files.readAllBytes(imgFile.toPath());
                } catch (IOException ex) {
                    System.err.println("Erro ao converter em byte[]");
                    return;
                }
                
                try {
                    selectedImg = new SerialBlob(imgContent);
                } catch (SQLException ex) {
                    System.err.println("Erro ao converter em blob");
                }
            } else {
                selectedImg = null;
            }
        });

        btnCancel.addActionListener(e -> dispose());

        this.add(panel);
        this.setSize(540, 220);
        this.setLocationRelativeTo(getParent());
    }

    private void fillFields() {
        if (jogo != null) {
            txtTitulo.setText(jogo.getTitulo());
            txtClassificacao.setText(String.valueOf(jogo.getClassificacao()));
            txtDesenvolvedor.setText(jogo.getDesenvolvedor());
            txtPreco.setText(String.valueOf(jogo.getPreco()));
            txtLancamento.setText(jogo.getLancamento().toString());

            selectedImg = jogo.getImagem();

            if (jogo.isTipo()) {
                radioBtnDigital.setSelected(true);
            } else {
                radioBtnFisico.setSelected(true);
            }
        }
    }

    private boolean validateFields() {
        if (
            txtTitulo.getText().trim().isEmpty() ||
            txtClassificacao.getText().trim().isEmpty() ||
            txtDesenvolvedor.getText().trim().isEmpty() ||
            txtPreco.getText().trim().isEmpty() ||
            txtLancamento.getText().trim().isEmpty() || 
            (
                !radioBtnFisico.isSelected() &&
                !radioBtnDigital.isSelected()
            ) ||
            selectedImg == null
        ) {
            JOptionPane.showMessageDialog(
                this, 
                "Todos os campos são obrigatórios",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }

    private void createJogo() {
        tipo = !radioBtnFisico.isSelected();

        jogo = new Jogo(
            txtTitulo.getText().trim(),
            selectedImg,
            tipo,
            Integer.parseInt(txtClassificacao.getText().trim()),
            txtDesenvolvedor.getText().trim(),
            Double.parseDouble(txtPreco.getText().trim()),
            java.sql.Date.valueOf(txtLancamento.getText().trim())
        );
        
        System.out.println(jogo.toString());
    }
    
    private void updateJogo() {
        if (jogo != null) {
            jogo.setTitulo(txtTitulo.getText().trim());
            jogo.setImagem(selectedImg);
            jogo.setTipo(tipo);
            jogo.setClassificacao(Integer.parseInt(txtClassificacao.getText().trim()));
            jogo.setDesenvolvedor(txtDesenvolvedor.getText().trim());
            jogo.setPreco(Double.parseDouble(txtPreco.getText().trim()));
            jogo.setLancamento(java.sql.Date.valueOf(txtLancamento.getText().trim()));
        }

        System.out.println(jogo.toString());
    }

    public Jogo getJogo() {
        return jogo;
    }
}
