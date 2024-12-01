import javax.swing.JFrame;
import views.JogoForm;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        JFrame oi = new JFrame();

        JogoForm tela = new JogoForm(oi, "Teste");

        oi.setVisible(true);
        tela.setVisible(true);
    }
}
