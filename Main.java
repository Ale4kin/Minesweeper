package minesweeper;

import java.util.*;

public class Main {

    static final int BoardWidth = 11;
    static final int BoardHeight = 9;

    public static void main(String[] args) {
        // write your code here

        Scanner scanner = new Scanner (System.in);
        int moveFreeCount = 0;
        ArrayList<String> stackMine = new ArrayList<String>();
        ArrayList<String> stackFree = new ArrayList<String>();

        String[][] boardOne = new String[BoardHeight][BoardWidth];
        String[][] boardTwo = new String[BoardHeight][BoardWidth];

        System.out.println ("How many mines do you want on the field?");

        String input = scanner.nextLine ().replace ("> ", "");
        final int mines = Integer.parseInt (input);

        initializeBoard (boardOne);

        int minesLocal = mines;
        while (minesLocal > 0) {

            if (randomSetBombs(boardOne, -1, -1)) continue;
            minesLocal -= 1;
        }

        for (int i = 0; i < boardOne.length; i++) {
            for (int j = 0; j < boardOne.length; j++) {
                addOnesAroundMine (boardOne, i, j);
            }
        }

        initializeBoard (boardTwo);

        printBoard (boardTwo);

        boolean finished = false;

        int free = 81 - mines;

        while (stackMine.size () < 81 - free || stackFree.size () < free) {

            if (stackFree.size () == free) {
                printBoard (boardTwo);
                System.out.println ("Congratulations! You found all the mines!");
                break;
            }
            if (stackMine.size () == 81 - free) {
                printBoard (boardOne);
                System.out.println ("Congratulations! You found all the mines!");
                break;
            }

            System.out.println ("Set/unset mines marks or claim a cell as free:");

            String hod = scanner.nextLine ().replace ("> ", "");
            String[] steps = hod.split (" ");
            int hodX = Integer.parseInt (steps[0]);
            int hodY = Integer.parseInt (steps[1]);
            String mineOrFree = steps[2];
            if (hod.contains("free")) {
                moveFreeCount += 1;
            }


            if ("X".equals(boardOne[hodY - 1][hodX]) && moveFreeCount == 1 && hod.contains("free")) {
                initializeBoard (boardOne);

                int minesLocal2 = mines;
                while (minesLocal2 > 0) {

                    if (randomSetBombs(boardOne, hodY, hodX)) continue;
                    minesLocal2 -= 1;
                }

                for (int i = 0; i < boardOne.length; i++) {
                    for (int j = 0; j < boardOne.length; j++) {
                        addOnesAroundMine (boardOne, i, j);
                    }
                }

            }

            switch (mineOrFree) {

                case "mine": {

                    if ("X".equals (boardOne[hodY - 1][hodX]) && "*".equals (boardTwo[hodY - 1][hodX]) || ".".equals (boardOne[hodY - 1][hodX]) && "*".equals (boardTwo[hodY - 1][hodX]) ||
                            "1".equals (boardOne[hodY - 1][hodX]) && "*".equals (boardTwo[hodY - 1][hodX]) || "2".equals (boardOne[hodY - 1][hodX]) && "*".equals (boardTwo[hodY - 1][hodX]) || "3".equals (boardOne[hodY - 1][hodX]) && "*".equals (boardTwo[hodY - 1][hodX])
                            || "4".equals (boardOne[hodY - 1][hodX]) && "*".equals (boardTwo[hodY - 1][hodX]) || "5".equals (boardOne[hodY - 1][hodX]) && "*".equals (boardTwo[hodY - 1][hodX]) || "6".equals (boardOne[hodY - 1][hodX]) && "*".equals (boardTwo[hodY - 1][hodX]) || "7".equals (boardOne[hodY - 1][hodX]) && "*".equals (boardTwo[hodY - 1][hodX])
                            || "8".equals (boardOne[hodY - 1][hodX]) && "*".equals (boardTwo[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = ".";
                        printBoard (boardTwo);
                        continue;
                    }

                    if ("X".equals (boardOne[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = "*";
                        stackMine.add (mineOrFree);
                        printBoard (boardTwo);

                        continue;
                    }
                    if (".".equals (boardOne[hodY - 1][hodX]) || "1".equals (boardOne[hodY - 1][hodX]) || "2".equals (boardOne[hodY - 1][hodX]) || "3".equals (boardOne[hodY - 1][hodX])
                            || "4".equals (boardOne[hodY - 1][hodX]) || "5".equals (boardOne[hodY - 1][hodX]) || "6".equals (boardOne[hodY - 1][hodX]) || "7".equals (boardOne[hodY - 1][hodX])
                            || "8".equals (boardOne[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = "*";
                        printBoard (boardTwo);

                        continue;
                    }


                    break;
                }

                case "free": {


                    if (".".equals (boardOne[hodY - 1][hodX])) {

                        checkTile (hodY-1, hodX, boardOne, boardTwo, stackFree);
                        printBoard (boardTwo);
                        continue;
                    }

                    if ("X".equals (boardOne[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = "X";
                        printBoard (boardOne);
                        System.out.println ("You stepped on a mine and failed!");
                        finished = true;
                        break;
                    }

                    if ("1".equals (boardOne[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = "1";
                        stackFree.add (mineOrFree);
                        printBoard (boardTwo);
                        continue;
                    }
                    if ("2".equals (boardOne[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = "2";
                        stackFree.add (mineOrFree);
                        printBoard (boardTwo);
                        continue;
                    }
                    if ("3".equals (boardOne[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = "3";
                        stackFree.add (mineOrFree);
                        printBoard (boardTwo);
                        continue;
                    }
                    if ("4".equals (boardOne[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = "4";
                        stackFree.add (mineOrFree);
                        printBoard (boardTwo);
                        continue;
                    }
                    if ("5".equals (boardOne[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = "5";
                        stackFree.add (mineOrFree);
                        printBoard (boardTwo);
                        continue;
                    }
                    if ("6".equals (boardOne[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = "6";
                        stackFree.add (mineOrFree);
                        printBoard (boardTwo);
                        continue;
                    }
                    if ("7".equals (boardOne[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = "7";
                        stackFree.add (mineOrFree);
                        printBoard (boardTwo);
                        continue;
                    }
                    if ("8".equals (boardOne[hodY - 1][hodX])) {
                        boardTwo[hodY - 1][hodX] = "8";
                        stackFree.add (mineOrFree);
                        printBoard (boardTwo);
                        continue;
                    }

                    break;
                }

            }


        }
    }

    private static boolean randomSetBombs(String[][] boardOne, int hodY, int hodX) {
        //false if we set a bomb and true we could not set a bomb
        int y;
        int x;
        int maxY = 9;
        int maxX = 8;
        int min = 0;
        Random random = new Random ();
        x = random.nextInt (maxX - min + 1) + min;
        y = random.nextInt (maxY - min + 1) + min;

        if (x == hodY - 1 && y == hodX || y ==0) {
            return true;
        }
        if (boardOne[x][y].equals ("X")) {
            return true;
        }

        boardOne[x][y] = "X";
        return false;
    }

    private static void printBoard(String[][] boardTwo) {
        System.out.println (" |123456789|");
        System.out.println ("-|---------|");
        for (String[] strings : boardTwo) {
            for (String string : strings) {
                System.out.print (string);
            }
            System.out.println ();
        }
        System.out.println ("-|---------|");
    }

    private static void initializeBoard(String[][] boardOne) {
        for (int i = 0; i < BoardHeight; i++) {
            for (int j = 0; j < BoardWidth; j++) {

                if (j == 10) {
                    boardOne[i][j] = "|";
                } else if (j == 0) {
                    boardOne[i][j] = String.valueOf (i + 1) + "|";
                } else {
                    boardOne[i][j] = ".";
                }
            }
        }
    }

    private static void addOnesAroundMine(String[][] board, int i, int j) {
        if ("X".equals (board[i][j])) {
            for (int k = i - 1; k <= i + 1; k++) {
                for (int l = j - 1; l <= j + 1; l++) {
                    if (k >= 0 && k <= BoardHeight - 1 && l <= BoardWidth - 1 && l >= 0) {
                        if (".".equals (board[k][l])) {
                            int num = 0;
                            num += 1;
                            board[k][l] = Integer.toString (num);
                        } else if ("X".equals (board[k][l]) || "|".equals (board[k][l]) || "1|".equals (board[k][l]) || "2|".equals (board[k][l]) || "3|".equals (board[k][l]) || "4|".equals (board[k][l]) || "5|".equals (board[k][l]) || "6|".equals (board[k][l]) || "7|".equals (board[k][l]) || "8|".equals (board[k][l]) || "9|".equals (board[k][l])) {

                        } else {
                            int num = Integer.parseInt (board[k][l]);
                            num += 1;
                            board[k][l] = Integer.toString (num);
                        }
                    }
                }
            }
        }
    }


    static void checkTile(int i, int j, String[][] boardOne, String[][] boardTwo, ArrayList<String> stackFree) {

        if (i >= 0 && i <= BoardHeight - 1 && j <= BoardWidth - 1 && j >= 0) {

            if ("X".equals (boardOne[i][j]) || "*".equals(boardTwo[i][j]) && "X".equals(boardOne[i][j])) {
                return;
            }

            boolean isDot = ".".equals (boardTwo[i][j]);

            if ("|".equals (boardOne[i][j]) || "1|".equals (boardOne[i][j]) || "2|".equals (boardOne[i][j]) || "3|".equals (boardOne[i][j]) || "4|".equals (boardOne[i][j]) || "5|".equals (boardOne[i][j]) || "6|".equals (boardOne[i][j]) || "7|".equals (boardOne[i][j]) || "8|".equals (boardOne[i][j]) || "9|".equals (boardOne[i][j])) {

            }

            if (isDot) {
                boardTwo[i][j] = "/";
                stackFree.add ("free");
            }
            if ("*".equals(boardTwo[i][j])){
                boardTwo[i][j] = "/";
                stackFree.add ("free");
            }
            if ("1".equals (boardOne[i][j])) {
                boardTwo[i][j] = "1";
            }
            if ("2".equals (boardOne[i][j])) {
                boardTwo[i][j] = "2";
            }
            if ("3".equals (boardOne[i][j])) {
                boardTwo[i][j] = "3";
            }
            if ("4".equals (boardOne[i][j])) {
                boardTwo[i][j] = "4";
            }
            if ("5".equals (boardOne[i][j])) {
                boardTwo[i][j] = "5";
            }
            if ("6".equals (boardOne[i][j])) {
                boardTwo[i][j] = "6";
            }
            if ("7".equals (boardOne[i][j])) {
                boardTwo[i][j] = "7";
            }
            if ("8".equals (boardOne[i][j])) {
                boardTwo[i][j] = "8";
            }

            if (isDot) {
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if (x == 0 && y == 0) {
                            //   continue;
                        } else {
                            checkTile (i + x, j + y, boardOne, boardTwo, stackFree);

                        }
                    }
                }
            }

        }

    }


}

