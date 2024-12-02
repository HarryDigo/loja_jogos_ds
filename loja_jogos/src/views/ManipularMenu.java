package views;

import java.awt.*;
import javax.swing.*;

public class ManipularMenu extends JFrame {
    public ManipularMenu() {
        super("Manipulação de dados");
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.setLayout(new BorderLayout());
        this.setContentPane(contentPanel);

        this.setLocationRelativeTo(getParent());
    }
}
