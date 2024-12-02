package views;

import java.awt.*;
import javax.swing.*;
import models.Genero;

public class GeneroForm extends JDialog {
    private JTextField txtNome;

    private JButton btnSave;
    private JButton btnCancel;

    private Genero genero;
    private final boolean isEditMode;

    public GeneroForm(Frame parent, String title) {
        super(parent, title, true);
        this.isEditMode = false;
        initialize();
    }

    public GeneroForm(Frame parent, String title, Genero genero) {
        super(parent, title, true);
        this.isEditMode = false;
        this.genero = genero;
        initialize();
        fillFields();
    }

    @SuppressWarnings("unused")
    private void initialize() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        txtNome = new JTextField(20);

        btnSave = new JButton("Salvar");
        btnCancel = new JButton("Cancelar");

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(btnSave);
        panel.add(btnCancel);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnSave.addActionListener(e -> {
            if (validateFields()) {
                if (isEditMode) {
                    updateGenero();
                } else {
                    createGenero();
                }
                dispose();
            }
        });

        btnCancel.addActionListener(e -> dispose());

        this.add(panel);
        this.setSize(220, 120);
        this.setLocationRelativeTo(getParent());
    }

    private void fillFields() {
        if (genero != null) {
            txtNome.setText(genero.getNome());
        }
    }

    private boolean validateFields() {
        if (txtNome.getText().trim().isEmpty()) {
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

    private void createGenero() {
        genero = new Genero(txtNome.getText().trim());
        
        System.out.println(genero.toString());
    }

    private void updateGenero() {
        if (genero != null) {
            genero.setNome(txtNome.getText().trim());
        }

        System.out.println(genero.toString());
    }

    public Genero getGenero() {
        return genero;
    }
}
