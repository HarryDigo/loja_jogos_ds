package views;

import controllers.MenuController;
import java.awt.*;
import javax.swing.*;

public class InitialMenu extends JFrame {
    private JLabel titulo;

    private JButton btnSair;
    private JButton btnManip;
    private JButton btnTable;
    private JButton btnSearch;

    public InitialMenu() {
        super("Menu inicial");
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titulo = new JLabel("BQ Jogos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(new Color(12, 215, 242));

        btnSair = new JButton("Sair");
        btnManip = new JButton("Manipular");
        btnTable = new JButton("Tabela");
        btnSearch = new JButton("Pesquisar");

        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));     
        btnPanel.add(btnManip);
        btnPanel.add(btnSearch);
        btnPanel.add(btnTable);
        btnPanel.add(btnSair);

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));         

        contentPanel.setLayout(new BorderLayout());
        this.setContentPane(contentPanel);

        contentPanel.add(titulo, BorderLayout.NORTH);
        contentPanel.add(btnPanel, BorderLayout.CENTER);

        btnSair.addActionListener(e -> {
            dispose();
        });

        btnManip.addActionListener(e -> {
            MenuController manipMenu = new MenuController();
            manipMenu.start();
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}