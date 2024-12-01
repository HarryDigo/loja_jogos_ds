import controllers.MenuController;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        MenuController controller = new MenuController();
        controller.start();
    }
}
