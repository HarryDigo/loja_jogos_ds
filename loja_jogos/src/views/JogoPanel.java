package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
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
import javax.swing.SwingConstants;
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

        try {
            int height = imagem.getHeight();
            int width = imagem.getWidth();

            float scaleFactor;
            if (height > width) {
                scaleFactor = (float) 108/height;
            } else {
                scaleFactor = (float) 108/width;
            }

            Image scaledImage = imagem.getScaledInstance(Math.round(width*scaleFactor), Math.round(height*scaleFactor), Image.SCALE_SMOOTH);

            lblImg.setIcon(new ImageIcon(scaledImage));
        } catch (NullPointerException e) {
            System.err.println("Imagem não encontrada para jogo: "+jogo.getTitulo());
        }

        lblImg.setHorizontalAlignment(JLabel.CENTER);
        lblImg.setVerticalAlignment(JLabel.CENTER);

        Dimension imgDim = new Dimension(107, 107);

        lblImg.setMinimumSize(imgDim);
        lblImg.setMaximumSize(imgDim);
        lblImg.setPreferredSize(imgDim);

        lblImg.setBackground(new Color(222, 222, 222));
        lblImg.setOpaque(true);
        lblImg.setBorder(BorderFactory.createLineBorder(new Color(206, 206, 206), 1));

        JLabel lblTitulo = new JLabel("<html>"+jogo.getTitulo()+"</html>", SwingConstants.CENTER);
        
        this.setPreferredSize(new Dimension(120, 180));
        this.add(lblImg);
        this.add(lblTitulo);
    }

    public Jogo getJogo() {
        return jogo;
    }
}
