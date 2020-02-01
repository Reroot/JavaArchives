package Basic;


import java.util.Scanner;

public class ChipGame {

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}

class Game {
    int turn = 1;
    long chips;
    Player player1;
    Player player2;
    boolean done = false;
    static Scanner scanner = new Scanner(System.in);

    void initPlayers() {
        // Initialize players and assign their names.

        System.out.println("What is the name of the first player?");
        String player1Name = scanner.nextLine();

        player1 = new Player(player1Name);

        System.out.println("What is the name of the second player?");
        String player2Name = scanner.nextLine();
        
        while (player1Name.toLowerCase().equals(player2Name.toLowerCase())) {
            System.out.println("Both players cannot be named " + player1Name + ". Try another name.");
            player2Name = scanner.nextLine();
        }

        player2 = new Player(player2Name);
    }

    void initChips() {
        // Set the initial number of chips.

        System.out.println("How many chips does the initial pile contain?");

        while (true) {
            chips = getNumber();
            if (chips < 3) {
                System.out.println("You have to start with at least 3 chips. Choose another number: ");
            } else if (chips % 2 == 0) {
                System.out.println("You must start with an odd number of chips. Try again.");
            } else {
                break;
            }
        }
    }

    void turn() {
        System.out.println("\nTurn " + turn);

        Player currentPlayer = turn % 2 == 1 ? player1 : player2;

        displayPlayerChips();

        System.out.println("It is your turn, " + currentPlayer.name);
        System.out.println("There are " + chips + " chips remaining.");

        long maxChips = turn == 0 ? (chips - 1) / 2 : chips / 2;

        if (maxChips == 0)
            maxChips = 1;
        System.out.println("You may take any number of chips from 1 to " + maxChips + " chips. How many will you take, "
                + currentPlayer.name + "");
        long numberTaken = 0;

        // Loop until a valid number of chips is selected
        while (true) {
            numberTaken = getNumber();
            if (numberTaken < 1) {
                System.out.println("Illegal move: you must take at least one chip.");
            } else if (numberTaken > maxChips) {
                System.out.println("Illegal move: you may not take more than " + maxChips + " chips.");
            } else {
                chips -= numberTaken;
                currentPlayer.chips += numberTaken;
                break;
            }
        }
        turn++;
    }

    void displayPlayerChips() {
        System.out.println(player1.name + " has " + player1.chips + " chips.");
        System.out.println(player2.name + " has " + player2.chips + " chips.");
    }

    void decideWinner() {
        displayPlayerChips();
        Player winner = player1.chips % 2 == 0 ? player1 : player2;
        System.out.println(winner.name + " wins!");
    }

    void requestNewGame() {
        System.out.println("Play another game? (y/n)");
        String retry = scanner.nextLine().toLowerCase();
        while (!retry.equals("y") && !retry.equals("n")) {
            System.out.println("Invalid choice. Please try again.");
            retry = scanner.nextLine().toLowerCase();
        }
        if (!retry.equals("y")) {
            done = true;
        }
    }

    void run() {
        initPlayers();
        while (!done) {
            startGame();
        }
        System.out.println("The end!");
    }

    void startGame() {
        System.out.println("Starting game");
        initChips();

        // main game loop
        while (chips > 0) {
            turn();
        }
        decideWinner();
        requestNewGame();
    }

    public static long getNumber() {
        for (;;) {
            String input = scanner.nextLine();
            try {
                long num = Long.parseLong(input);
                return num;
            } catch (Exception e) {
                System.out.println("Error: Invalid input. Try again.");
            }
        }
    }
}

class Player {
    String name;
    int chips = 0;

    public Player(String newName) {
        name = newName;
    }
}
