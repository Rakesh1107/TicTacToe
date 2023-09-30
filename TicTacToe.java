package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToe {
    static char ticTacToe = 'X';

    private static char[][] createBoard() {
        char[][] board = new char[3][3];
        //char[] defaultValues = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int temp = 1;
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = (char) (temp++ + '0');
            }
        }
        return board;
    }

    private static void printBoard(char[][] board) {
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
            System.out.println("------");
        }
    }

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        char[][] board = createBoard();
        playGame(board);
    }

    private static void playGame(char[][] board) {
        printBoard(board);
        int box = getInput(board);
        if (box == -1) {
            return;
        }
        validateAndUpdateBoard(board, box, ticTacToe);

        if(!gameOver(board)) {
            ticTacToe = ticTacToe == 'X' ? 'O' : 'X';
            playGame(board);
        }
        else if(gameDraw(board)) {
            printBoard(board);
            System.out.println("Draw game!. All cells filled");
        }
        else {
            printBoard(board);
            System.out.println(ticTacToe + " won the game!");
        }
    }

    private static int getInput(char[][] board) {
        System.out.println("Enter the cell to fill for " + ticTacToe);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
            int box = Integer.parseInt(s);
            if (box <= 0 || box > 9) {
                System.out.println("Enter valid box (0-9)");
                playGame(board);
                return 0;
            }
            return box;
        } catch (NumberFormatException e) {
            System.out.println("Box value should be a number");
            playGame(board);
            return 0;
        } catch (IOException e) {
            System.out.println("Exiting game!");
            return -1;
        }

    }

    private static void validateAndUpdateBoard(char[][] board, int box, char toFill) {
        int[] indices = getIndexToFill(box);
        if(board[indices[0]][indices[1]] == 'X' || board[indices[0]][indices[1]] == 'O') {
            System.out.println("Box is already filled");
            playGame(board);
            return;
        }
        board[indices[0]][indices[1]] = toFill;
    }

    // User enters 1-9 for the cell to fill. Logic the get the indices to fill in 2D char array
    private static int[] getIndexToFill(int box) {
        switch (box) {
            case 1:
                return new int[]{0, 0};
            case 2:
                return new int[]{0, 1};
            case 3:
                return new int[]{0, 2};
            case 4:
                return new int[]{1, 0};
            case 5:
                return new int[]{1, 1};
            case 6:
                return new int[]{1, 2};
            case 7:
                return new int[]{2, 0};
            case 8:
                return new int[]{2, 1};
            case 9:
                return new int[]{2, 2};
        }
        return new int[]{0, 0};
    }

    // Game Draw Logic - If all cells are filled and not game over yet, game ends in draw.
    private static boolean gameDraw(char[][] board) {
        int emptyCells = 0;
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar != 'X' && aChar != 'O') {
                    emptyCells++;
                }
            }
        }
        return emptyCells == 0;
    }

    // 8 possible cases for game win. 6 straight & 2 diagonal
    private static boolean gameOver(char[][] board) {
        if (board[0][0] == board[0][1] && board[0][1] == board[0][2]) {
            return true;
        }
        if (board[1][0] == board[1][1] && board[0][1] == board[1][2]) {
            return true;
        }
        if (board[2][0] == board[2][1] && board[2][1] == board[2][2]) {
            return true;
        }
        if (board[0][0] == board[1][0] && board[1][0] == board[2][0]) {
            return true;
        }
        if (board[0][1] == board[1][1] && board[1][1] == board[2][1]) {
            return true;
        }
        if (board[0][2] == board[1][2] && board[1][2] == board[2][2]) {
            return true;
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        return board[0][2] == board[1][1] && board[1][1] == board[2][0];
    }

}

