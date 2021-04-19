package console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controllers.Game;
import model.playfield.Asteroid;
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
                correctInput = true;
                ArrayList<AsteroidField> neighbors = game.getNeighbours();
                showNeighbors(neighbors);
                int nField = chooseNeighbor(neighbors);
                
                showFieldAsteroids(nField, neighbors);
                int nAsteroid = chooseAsteroid(nField, neighbors);

                game.moveSettler(nField, nAsteroid);
            default:
                System.out.println("invalid command!");
            }
        }
    }

    private void showNeighbors(ArrayList<AsteroidField> neighbors) {
        for (int i = 0; i < neighbors.size(); i++) {
            List<Asteroid> asteroids = neighbors.get(i).getAsteroids();
            System.out.println(String.valueOf(i) + ". field - " + asteroids.size());
        }
    }

    private int chooseNeighbor(ArrayList<AsteroidField> neighbors) {
        String input = new String();

        boolean correctInput = false;
        while (!correctInput) {
            input = sc.nextLine();
            switch(input) {
                case "exit":
                    System.exit(1);
                default:
                    int nField = Integer.parseInt(input);
                    if (nField > neighbors.size() || nField < 0) {
                        System.out.println("invalid command!");
                    } else {
                        correctInput = true;
                    }
            }
        }

        return Integer.parseInt(input);
    }

    private void showFieldAsteroids(int nField, ArrayList<AsteroidField> neighbors) {
        AsteroidField af = neighbors.get(nField - 1);
        List<Asteroid> asteroids = af.getAsteroids();

        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid asteroid = asteroids.get(i);

            String core = "?";
            if (asteroid.getThickness() == 0) {
                if (asteroid.isEmpty()) {
                    core = "empty";
                } else {
                    core = asteroid.getMaterial().toString();
                }
            }

            System.out.println(String.valueOf(i) + ". asteroid - layers: " + String.valueOf(asteroid.getThickness()) + ", core: " + core);
        }
    }

    private int chooseAsteroid(int nField, ArrayList<AsteroidField> neighbors) {
        AsteroidField af = neighbors.get(nField - 1);
        List<Asteroid> asteroids = af.getAsteroids();
        
        String input = new String();

        boolean correctInput = false;
        while (!correctInput) {
            input = sc.nextLine();
            switch(input) {
                case "exit":
                    System.exit(1);
                default:
                    int nAsteroid = Integer.parseInt(input);
                    if (nAsteroid > asteroids.size() || nAsteroid <= 0) {
                        System.out.println("invalid command!");
                    } else {
                        correctInput = true;
                    }
            }
        }

        return Integer.parseInt(input);
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