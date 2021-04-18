package console;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.Game;

public class Console {
    private Game game;

    public void start() {
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.println("ASZTEROIDABANYASZAT PROTOTIPUS\n");
        System.out.println("Egy új játék elkezdésére írd be a play parancsot, tesztelési módba való átlépésre írd be a test parancsot.");
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

        sc.close();
    }

    private void handlePlayerTurn() {
        game.resetChoosableSettlers();

        while (true) {
            try {
                handleSettlerTurn();
            } catch (Exception nextTurnException) {
                break;
            }
        }
    }

    private void handleSettlerTurn() throws Exception {
        Scanner sc = new Scanner(System.in);
        String input;
        
        printChoosableSettlers();
        
        /* settler valasztasa */
        input = sc.nextLine();
        System.out.println("just read this: " + input);
        boolean correctInput = false;
        while (!correctInput) {
            switch (input) {
            case "next turn":
                sc.close();
                correctInput = true;
                throw new Exception();
            case "exit":
                System.exit(1);
            default:
                try {
                    game.chooseSettler(Integer.parseInt(input));
                    correctInput = true;
                } catch (Exception wrongNumberException) {
                    System.out.println("invalid command!");
                }
                break;
            }
        }

        printAvailableActions();

        /* lepes */
        /* TODO */

        game.endSettlerTurn(Integer.valueOf(input));
        sc.close();
    }

    private void printChoosableSettlers() {
        for (Integer i : game.getChoosableSettlers()) {
            if (i != null) {
                System.out.println(i.toString() + ". settler");
            }
        }
    }

    private void printAvailableActions() {
        ArrayList<String> actions = game.getActions();
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(String.valueOf(i) + ". " + actions.get(i));
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