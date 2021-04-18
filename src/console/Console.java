package console;
import java.util.Scanner;
import controllers.Game;

public class Console {
    private Game game;

    public void start() {
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.println("ASZTEROIDABANYASZAT PROTOTIPUS\n");
        System.out.println("Egy új játék elkezdéséhez írd be a play parancsot, tesztelési módba való átlépésre írd be a test parancsot.");
        while (!(input = sc.nextLine()).equals("exit")) {
            switch (input) {
            case "play":
                game = new Game();
                game.start();
                while (!game.over()) {
                    handlePlayerTurn();
                    // szolunk a game controllernek hogy a kör további részeit végezze el
                }
                break;
            case "test":
                System.out.println("entered testing mode ");
                break;
            default:
                System.out.println("invalid command");
                break;
            }
        }
    }

    private void handlePlayerTurn() {
        game.resetChoosableSettlers();

        while (true) {
            try {
                handleSettlerTurn();
            } catch (Exception e) {
                break;
            }
        }
    }

    private void handleSettlerTurn() throws Exception {
        Scanner sc = new Scanner(System.in);
        String input;

        while (!(input = sc.nextLine()).equals("exit")) {
            switch(input) {
                case "next turn":
                    throw new Exception();  
                default:
                    game.chooseSettler(Integer.parseInt(input));
            }
        }
    }












    private void handleCommands() {
        Scanner sc = new Scanner(System.in);
        String input;
        while (!(input = sc.nextLine()).equals("exit")) {
            handleCommand(input);
        }
    }

    private void handleCommand(String cmd) {
        switch (cmd) {
            case "move":
                handleMoveCmd();
        }
    }

    private void handleMoveCmd() {

    }
}