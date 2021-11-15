/**
 * Java Home Work №4
 * Author: Anna Zausalina
 * Date: 15.11.2021
 */

import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    final char SIGN_X = 'x'; // sign of human
    final char SIGN_O = 'o'; // sign of AI
    final char SIGN_EMPTY = '.'; // sign of empty cell
    int tableSize = 5; // Size of game table (3rd* exercise)
    char[][] table;
    Scanner scan;
    Random random;

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    TicTacToe() {

        table = new char[tableSize][tableSize];
        scan =  new Scanner(System.in);
        random = new Random();
    }

    void game() {
        initTable();
        while (true) {
            turnHuman();
            if (checkWin(SIGN_X)){
                System.out.println("YOU WON!");
                break;
            }
            if (isTableFull()) {
                System.out.println("IT'S DRAW!");
                break;
            }
            turnAI();
            printTable();
            if (checkWin(SIGN_O)) {
                System.out.println("YOU LOSE!");
                break;
            }
            if (isTableFull()) {
                System.out.println("IT'S DRAW!");
                break;
            }
        }
        System.out.println("GAME OVER");
        printTable();
    }

    void initTable() {
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                table[i][j] = SIGN_EMPTY;
            }
        }
    }

    void printTable() {
        for (int y = 0; y < tableSize; y++) {
            for (int x = 0; x < tableSize; x++) {
                System.out.print(table[x][y] + " ");
            }
            System.out.println();
        }
    }

    void turnHuman() {
        int x, y;
        do {
            System.out.print("YOUR TURN. TYPE x y [1.." + tableSize + "]: ");
            x = scan.nextInt() - 1;
            y = scan.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[x][y] = SIGN_X;
    }

    void turnAI() {
        int x, y;
        do {
            x = random.nextInt(tableSize);
            y = random.nextInt(tableSize);
        } while (!isCellValid(x, y));
        table[x][y] = SIGN_O;
    }

    boolean checkDiag(char ch) {
        // main diagonal
        boolean winFlagMain = true;
        for (int i = 0; i < tableSize; i++){
            if (ch != table[i][i]){
                winFlagMain = false;
                break;
            }
        }
        // side diagonal
        boolean winFlagSide = true;
        for (int i = 0; i < tableSize; i++){
            if (ch != table[(tableSize - 1) - i][i]) {
                winFlagSide = false;
                break;
            }
        }
        return winFlagMain || winFlagSide;
    }

    boolean checkColumns(char ch){
        for (int i = 0; i < tableSize; i++){
            boolean winFlag = true;
            for (int j= 0; j < tableSize; j++){
                if (ch != table[i][j]) {
                    winFlag = false;
                    break;
                }
            }
            if (winFlag) {
                return true;
            }
        }
        return false;
    }

    boolean checkRows(char ch){
        for (int j = 0; j < tableSize; j++){
            boolean winFlag = true;
            for (int i= 0; i < tableSize; i++){
                if (ch != table[i][j]) {
                    winFlag = false;
                    break;
                }
            }
            if (winFlag) {
                return true;
            }
        }
        return false;
    }

    boolean checkWin(char ch) {
        // check all rows
        boolean rowsFlag = checkRows(ch);
        // check all columns
        boolean columnsFlag = checkColumns(ch);
        // check all diagonals
        boolean diagonalFlag = checkDiag(ch);

        return diagonalFlag || rowsFlag || columnsFlag;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x > tableSize - 1 || y > tableSize - 1) {
            return false;
        }
        return table[x][y] == SIGN_EMPTY;
    }

    boolean isTableFull() {
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                if (table[i][j] == SIGN_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
