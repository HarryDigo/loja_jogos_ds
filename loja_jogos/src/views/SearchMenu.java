package views;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SearchMenu extends JFrame {
    public SearchMenu() {
        super("Pesquisa de jogos");
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                InitialMenu initialMenu = new InitialMenu();
                initialMenu.setVisible(true);
            }
        });

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.setLayout(new BorderLayout());
        this.setContentPane(contentPanel);
    }
}
