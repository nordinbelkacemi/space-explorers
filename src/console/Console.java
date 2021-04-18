package console;
import java.util.Scanner;
import controllers.Game;

public class Console {
    private Game game;

    public void start() {
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.println("ASZTEROIDABANYASZAT PROTOTIPUS\n");
        System.out.println("Egy új játék elkezdésére írd be a play parancs");
        while (!(input = sc.nextLine()).equals("exit")) {
            switch (input) {
            case "play":
                game = new Game();
                game.start();
                while (!game.over()) {
                    handleCommands();
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