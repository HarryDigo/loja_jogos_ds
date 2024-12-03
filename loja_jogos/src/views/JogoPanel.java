package views;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import models.Jogo;

public class JogoPanel extends JPanel {
    private final Jogo jogo;

    private JLabel lblImg;

    public JogoPanel(Jogo jogo) {
        this.jogo = jogo;
        initialize();
    }

    private void initialize() {
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        java.sql.Blob imagemBlob = jogo.getImagem();
        InputStream imageStream = null;
        try {
            imageStream = imagemBlob.getBinaryStream();
        } catch (SQLException e) {
            System.err.println("Erro ao converter blob em imagem");
        }

        BufferedImage imagem = null;
        try {
            imagem = ImageIO.read(imageStream);
        } catch (IOException ex) {

        }

        lblImg = new JLabel();
        lblImg.setIcon(new ImageIcon(imagem.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));

        this.add(lblImg);

        this.setPreferredSize(new Dimension(200, 200));
    }

    public Jogo getJogo() {
        return jogo;
    }
}
