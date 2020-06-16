import java.util.*;

//      Basic steps involved in making of a tic - tac - toe game :

//   1. First create a 2D gameBoard
//   2. Placing 'X' and 'O' in the gameBoard from user as well as CPU
//   3. Constantly checking the result after taking the each step in the gameBoard

//      Methods used in the tic - tac - toe game :

//   1. printGameBoard() : to print the 2D array board
//   2. placePiece() : to place piece in the game board after the inputs from the user and the computer
//   3. checkWinner() : to check the winner after each step by checking all the 8 possible combinations of winning the game

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };

        printGameBoard(gameBoard);

        while (true) {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter your placement (1-9):");
            int playerPos = scan.nextInt();

            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken! Enter a correct Position");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");

            String result = checkWinner();
            if (result.length() > 0) {               //  Until the condition becomes equal to true it skips giving the result
                System.out.println(result);          //  And once the condition becomes true it prints the result and exits out of the loop
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1; //  It generates random integers between 1-9

            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                System.out.println("Position taken! Enter a correct Position");
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");      // We can use some A.I. for input from CPU

            printGameBoard(gameBoard);

             result = checkWinner();
            if (result.length() > 0) {                //  Until the condition becomes equal to true it skips giving the result
                System.out.println(result);           //  And once the condition becomes true it prints the result and exits out of the loop
                break;
            }

        }

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;

            case 2:
                gameBoard[0][2] = symbol;
                break;

            case 3:
                gameBoard[0][4] = symbol;
                break;

            case 4:
                gameBoard[2][0] = symbol;
                break;

            case 5:
                gameBoard[2][2] = symbol;
                break;

            case 6:
                gameBoard[2][4] = symbol;
                break;

            case 7:
                gameBoard[4][0] = symbol;
                break;

            case 8:
                gameBoard[4][2] = symbol;
                break;

            case 9:
                gameBoard[4][4] = symbol;
                break;

            default:
                break;
        }
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {   // If playerPositions list matches with any of the combinations of the winning list
                return "Congratulations You Won!";
            } else if (cpuPositions.containsAll(l)) { // If cpuPositions list matches with any of the combinations of the winning list
                return "Sorry You Lost :( ";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "DRAW!";     // If the list size matches and is equal to 9 then it results into DRAW!!!
            }

        }
        return "";
    }
}

/*
     ContainsAll() method
            &
     Contains() method
 */