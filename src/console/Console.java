package console;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.Game;
import model.playfield.AsteroidField;

public class Console {
    private Game game;
    private Scanner sc = new Scanner(System.in);

    public void start() {
        String input;

        System.out.println("ASZTEROIDABANYASZAT PROTOTIPUS\n");
        System.out.println("Egy új játék elkezdéséhez írd be a play parancsot, tesztelési módba való átlépéshez írd be a test parancsot.");
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
        printChoosableSettlers();
        
        Integer nSettler = 0;
        try {
            nSettler = chooseSettler();
        } catch (Exception wrongNumberException) {
            throw new Exception();
        }

        printAvailableActions();

        /* lepes */
        // performAction();

        game.endSettlerTurn(Integer.valueOf(nSettler));
    }

    private void printChoosableSettlers() {
        for (Integer i : game.getChoosableSettlers()) {
            if (i != null) {
                System.out.println(i.toString() + ". settler");
            }
        }
    }

    private Integer chooseSettler() throws Exception {
        String input = new String();

        boolean correctInput = false;
        while (!correctInput) {
            input = sc.nextLine();
            switch (input) {
            case "next turn":
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

        return Integer.valueOf(input);
    }

    private void printAvailableActions() {
        ArrayList<String> actions = game.getActions();
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(String.valueOf(i) + ". " + actions.get(i));
        }
    }

    private void performAction() {
        String input = new String();

        boolean correctInput = false;
        while (!correctInput) {
            input = sc.nextLine();
            switch (input) {
            case "move":
                showNeighbors();
            case ""
            }
        }
    }

    private void showNeighbors() {
        ArrayList<AsteroidField> neighbors = game.getNeighbours();
        for (int i = 0; i < neighbors.size(); i++) {
            // System.out.println);
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