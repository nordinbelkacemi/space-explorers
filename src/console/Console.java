package console;
import exceptions.InvalidOrBackCmdException;
import exceptions.NextTurnException;
import exceptions.BackCmdException;
import exceptions.ExitException;
import exceptions.InvalidCmdException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controllers.Game;
import model.playfield.Asteroid;
import model.playfield.AsteroidField;

public class Console {
    private Game game;
    private Scanner sc = new Scanner(System.in);

    /**
     * When the player chooses where to move, this type holds the asteroid field
     * index and and asteroid number (also an index, but 1-based) chosen
     */
    class DestinationSelection {
        private int fieldIdx, asteroidNum;

        public DestinationSelection() {
            fieldIdx = -1;
            asteroidNum = -1;
        }

        public int getFieldIdx() {
            return fieldIdx;
        }

        public void setFieldIdx(int n) {
            fieldIdx = n;
        }

        public int getAsteroidNum() {
            return asteroidNum;
        }

        public void setAsteroidNum(int num) {
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
         * 
         * @param input the string passed in by the user (or an input file in a test)
         * @throws InvalidCmdException thrown if the string passed in is an invalid
         *                             command
         */
        public void run(String input) throws InvalidCmdException, BackCmdException, ExitException;
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
        public void run(String input) throws InvalidCmdException, BackCmdException, ExitException {
            switch(input) {
            case "move":
                ArrayList<AsteroidField> neighbors = game.getNeighbours();

                try {
                    selectDestination(neighbors);
                } catch (BackCmdException ex) {
                    throw ex;
                } catch (ExitException ex) {
                    throw ex;
                }

                game.moveSettler();
                break;
            case "build teleportgate":
            	game.buildTeleportGate();
            	break;
            case "build robot":
            	game.buildRobot();
            	break;
            case "putback iron":
            	game.putIronBack();
            	break;
            case "putback ice":
            	game.putIceBack();
            	break;
            case "putback coal":
            	game.putCoalBack();
            	break;
            case "putback uranium":
            	game.putUraniumBack();
            	break;
            case "place teleportgate":
            	//TODO
            	break;
            default:
                throw new InvalidCmdException(); // in case of invalid command
            }
        }
    };

    
    private void selectDestination(ArrayList<AsteroidField> neighbors) throws BackCmdException, ExitException {
        DestinationSelection destination = new DestinationSelection();
        
        boolean destinationSelected = false;
        while (!destinationSelected) {
            try {
                showNeighbors(neighbors);
                destination.setFieldIdx(Integer.valueOf(getInputNavigableExitable(chooseFieldMethod)));
                try {
                    showFieldAsteroids(destination.getFieldIdx());
                    destination.setAsteroidNum(Integer.valueOf(getInputNavigableExitable(chooseAsteroidMethod)));
                    destinationSelected = true;
                } catch (BackCmdException ex) {
                    continue;
                } catch (ExitException ex) {
                    throw ex;
                }
            } catch (BackCmdException ex) {
                throw ex;
            } catch (ExitException ex) {
                throw ex;
            }
        }
    }
    
    private void showNeighbors(ArrayList<AsteroidField> neighbors) {
        for (int i = 0; i < neighbors.size(); i++) {
            List<Asteroid> asteroids = neighbors.get(i).getAsteroids();
            System.out.println(String.valueOf(i) + ". field - " + asteroids.size() +  " asteroids");
        }
    }

    private void showFieldAsteroids(int fieldIdx) {
        List<Asteroid> asteroids = game.getFieldAsteroids(fieldIdx);

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

    private IRunnable chooseFieldMethod = new IRunnable() {
        @Override
        public void run(String input) throws InvalidCmdException {
            List<AsteroidField> neighbors = game.getNeighbours();
            int fieldIdx = Integer.parseInt(input);
            if (fieldIdx >= 0 && fieldIdx < neighbors.size()) {
                game.selectField(fieldIdx);
            } else {
                throw new InvalidCmdException();
            }
        }
    };

    private IRunnable chooseAsteroidMethod = new IRunnable() {
        @Override
        public void run(String input) throws InvalidCmdException {
            List<Asteroid> asteroids = game.getSelectedField().getAsteroids();
            int asteroidNum = Integer.parseInt(input);
            if (asteroidNum >= 1 && asteroidNum <= asteroids.size()) {
                game.selectAsteroid(asteroidNum);
            } else {
                throw new InvalidCmdException();
            }
        }
    };























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
        /* player choosing a settler */
        Integer nSettler = 0;
        try {
            printChoosableSettlers();
            nSettler = Integer.parseInt(getInputSkippableExitable(chooseSettlerMethod));
        } catch (NextTurnException ex) {
            throw ex;
        } catch (ExitException ex) {
            throw ex;
        }

        /* player choosing the action to be performed by the settler */
        boolean doneWithAction = false;
        while(!doneWithAction) {
            try {
                printAvailableActions();
                getInputExitable(chooseActionMethod);
                doneWithAction = true;
            } catch (ExitException ex) {
                throw ex;
            } catch (BackCmdException ex) {
                /* do nothing just need to loop back (keep doneWithAction false) */
            }
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
                } catch (BackCmdException ex) {
                    /* do nothing */
                } catch (ExitException ex) {
                    throw ex;
                }
                break;
            }
        }

        return input;
    }

    /**
     * For commands where the user can exit the program with the exit command.
     * Additionally, if the command given within the getInputMethod parameter of the
     * function was a back command, a BackException gets caught and thrown to the caller.
     * 
     * @throws ExitException if the user input was the exit commands
     */
    private String getInputExitable(IRunnable getInputMethod) throws BackCmdException, ExitException {
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
                } catch (BackCmdException ex) {
                    throw ex;
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
     * 
     * @throws BackException if the user input was the back command
     * @throws ExitException if the user input was the exit command
     */
    private String getInputNavigableExitable(IRunnable getInputMethod) throws BackCmdException, ExitException {
        String input = new String();

        boolean correctInput = false;
        while (!correctInput) {
            input = sc.nextLine();
            switch (input) {
            case "exit":
                throw new ExitException();
            case "back":
                throw new BackCmdException();
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