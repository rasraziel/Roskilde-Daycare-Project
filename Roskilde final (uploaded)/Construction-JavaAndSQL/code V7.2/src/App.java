/* APP CLASS - this is only to create the controller and launch the main method */

public class App {

    private static Controller controller;

    public static void main(String[] args) throws Exception {
        controller = new Controller();
        new InitialLaunch();
    }

    public static Controller getController(){
        return controller;
    }
}
