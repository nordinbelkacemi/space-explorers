package console;
import java.controllers.Game.InvalidCmdException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controllers.Game;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;

public class Console {
    private Game game;
    private Scanner sc = new Scanner(System.in);

    class ExitException extends Exception { }
    class NextTurnException extends Exception { }
    class BackException extends Exception { }

    /** When the player chooses where to move, this type holds the asteroid field index and and asteroid number (also an index, but 1-based) chosen */
    class DestinationSelection {
        private Integer fieldIdx, asteroidNum;

        public DestinationSelection() {
            fieldIdx = -1;
            asteroidNum = -1;
        }

        public Integer getFieldIdx() {
            return fieldIdx;
        }

        public void setFieldIdx(Integer n) {
            fieldIdx = n;
        }

        public Integer getAsteroidNum() {
            return asteroidNum;
        }

        public void setAsteroidNum(Integer num) {
            asteroidNum = num;
        }
    }
    
    /**
     * Interface with a run function. The run function in our case is always a
     * function that takes in an input and throws an exception if the input is an
     * invalid command. Wherever an instance of the interface is called, if an
     * exception is thrown by it, it will get called again until it doesn't throw
     * anything (this is how invalid commands are handled)
     */
    interface IRunnable {
        /**
         * the referenced function's run method
         * @param input the string passed in by the user (or an input file in a test)
         * @throws InvalidCmdException thrown if the string passed in is an invalid command
         */
        public void run(String input) throws InvalidCmdException;
    }

    /**
     * Interface with a run function. The run function in our case is always a
     * function that takes in an input and can throw exceptions in two cases:
     * 
     * - if the input is an invalid command (throws InvalidCmdException)
     * - if the input is a back command (throws BackException)
     * 
     * Wherever an instance of the interface is called, if an exception is thrown
     * by it, it will get called again until it doesn't throw anything.
     */
    interface IRunnableWithBackCmd {
        /**
         * the referenced function's run method
         * 
         * @param input the string passed in by the user (or an input file in a test)
         * @throws InvalidCmdException thrown if the string passed in is an invalid command
         * @throws BackException thrown if the string passed in is the back command
         */
        public void run(String input) throws InvalidCmdException, BackException;
    }

    private IRunnable chooseSettlerMethod = new IRunnable() {
        @Override
        public void run(String input) throws InvalidCmdException {
            int n = Integer.parseInt(input);
            try {
                game.chooseSettler(n);
            } catch (InvalidCmdException ex) {
                throw ex;
            }
        }
    };

    private IRunnable chooseActionMethod = new IRunnable() {
        @Override
        public void run(String input) throws InvalidCmdException {
            switch(input) {
            case "move":
                ArrayList<AsteroidField> neighbors = game.getNeighbours();
                DestinationSelection destination = selectDestination(neighbors);
                if (destination.getAsteroidNum() == null || destination.getFieldIdx() == null) {
                    return;
                }
                try {
                    game.moveSettler(nField, nAsteroid);
                } catch (Exception ex) {
                    throw ex;
                }
                break;
            default:
                throw new Exception(); // in case of invalid command
            }
        }
    };
    
    private IRunnable chooseNeighborMethod = new IRunnable() {
        @Override
        public void run(String input) throws InvalidCmdException {
            int n = Integer.parseInt(input);
        }
    };

    private void showNeighbors(ArrayList<AsteroidField> neighbors) {
        for (int i = 0; i < neighbors.size(); i++) {
            List<Asteroid> asteroids = neighbors.get(i).getAsteroids();
            System.out.println(String.valueOf(i) + ". field - " + asteroids.size() +  " asteroids");
        }
    }

    private DestinationSelection selectDestination(ArrayList<Asteroid> neighbors) {
        DestinationSelection destination = new DestinationSelection();

        try {
            boolean completed = false;
            while (!completed) {
                showNeighbors(neighbors);
                destination.setFieldIdx(Integer.valueOf(getInputNavigableExitable(chooseFieldMethod)));
                try {
                    destination.setAsteroidNum(Integer.valueOf(getInputNavigableExitable(chooseAsteroidMethod)));
                } catch (BackException ex) {
                    destination.setAsteroidNum(null);
                }
            }
        } catch (BackException ex) {
            destination.setFieldIdx(null);
        }

        return destination;
    }























    public void start() {
        String input;

        System.out.println("ASZTEROIDABANYASZAT PROTOTIPUS\n");
        System.out.println("Egy új játék elkezdéséhez írd be a play parancsot, tesztelési módba való átlépéshez írd be a test parancsot.");

        boolean playModeExited = false;
        while (!playModeExited && !(input = sc.nextLine()).equals("exit")) {
            switch (input) {
            case "play":
                game = new Game();
                game.start();
                while (!game.over()) {
                    try {
                        handlePlayerTurn();
                    } catch (ExitException ex) {
                        playModeExited = true;
                        break;
                    }
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

    private void handlePlayerTurn() throws ExitException {
        game.resetChoosableSettlers();

        while (true) {
            try {
                handleSettlerTurn();
            } catch (NextTurnException ex) {
                break;
            } catch (ExitException ex) {
                throw ex;
            }
        }
    }

    private void handleSettlerTurn() throws NextTurnException, ExitException { 
        printChoosableSettlers();
        
        Integer nSettler = 0;
        try {
            nSettler = Integer.parseInt(getInputSkippableExitable(chooseSettlerMethod));
        } catch (NextTurnException ex) {
            throw ex;
        } catch (ExitException ex) {
            throw ex;
        }

        printAvailableActions();

        /* lepes */
        try {
            getInputExitable(chooseActionMethod);
        } catch (ExitException ex) {
            throw ex;
        }

        game.endSettlerTurn(Integer.valueOf(nSettler));
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















    


    private int chooseNeighbor(ArrayList<AsteroidField> neighbors) {
        String input = new String();

        boolean correctInput = false;
        while (!correctInput) {
            input = sc.nextLine();
            switch(input) {
                case "exit":
                    System.exit(1);
                    // break;
                default:
                    int nField = Integer.parseInt(input);
                    if (nField > neighbors.size() || nField < 0) {
                        System.out.println("invalid command!");
                    } else {
                        correctInput = true;
                    }
                    break;
            }
        }

        return Integer.parseInt(input);
    }

    private void showFieldAsteroids(int nField, ArrayList<AsteroidField> neighbors) {
        AsteroidField af = neighbors.get(nField);
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
                    // break;
                default:
                    int nAsteroid = Integer.parseInt(input);
                    if (nAsteroid > asteroids.size() || nAsteroid <= 0) {
                        System.out.println("invalid command!");
                    } else {
                        correctInput = true;
                    }
                    break;
            }
        }

        return Integer.parseInt(input);
    } 
















    /**
     * For commands where the user can: 
     * - go back to previous menu point (selection of asteroid field and selection of asteroid) 
     * - exit the program with the exit command
     */
    private String getInputSkippableExitable(IRunnable getInputMethod) throws NextTurnException, ExitException {
        String input = new String();

        boolean correctInput = false;
        while (!correctInput) {
            input = sc.nextLine();
            switch (input) {
            case "next turn":
                correctInput = true;
                throw new NextTurnException();
            case "exit":
                throw new ExitException();
            default:
                try {
                    getInputMethod.run(input);
                    correctInput = true;
                } catch (InvalidCmdException ex) {
                    System.out.println("invalid command!");
                }
                break;
            }
        }

        return input;
    }

    /**
     * For commands where the user can exit the program with the exit command
     */
    private String getInputExitable(IRunnable getInputMethod) throws ExitException {
        String input = new String();

        boolean correctInput = false;
        while (!correctInput) {
            input = sc.nextLine();
            switch (input) {
            case "exit":
                throw new ExitException();
            default:
                try {
                    getInputMethod.run(input);
                    correctInput = true;
                } catch (InvalidCmdException ex) {
                    System.out.println("invalid command!");
                } catch (BackException ex) {

                }
                break;
            }
        }

        return input;
    }

    /**
     * For commands where the user can:
     * - go back to previous menu point (selection of asteroid field and selection of asteroid)
     * - exit the program with the exit command
     */
    private String getInputNavigableExitable(IRunnable getInputMethod) throws BackException, ExitException {
        String input = new String();

        boolean correctInput = false;
        while (!correctInput) {
            input = sc.nextLine();
            switch (input) {
            case "exit":
                throw new ExitException();
            case "back":
                throw new BackException();
            default:
                try {
                    getInputMethod.run(input);
                    correctInput = true;
                } catch (InvalidCmdException ex) {
                    System.out.println("invalid command!");
                }
                break;
            }
        }
        return input;
    }
}