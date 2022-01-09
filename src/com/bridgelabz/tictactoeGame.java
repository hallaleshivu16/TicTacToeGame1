package com.bridgelabz;

import java.util.Random;
import java.util.Scanner;
public class tictactoeGame {

    //Playing Board Array for Locations
    private static char[] playingBoard;
    private static char player1Sybmol,player2Symbol;
    private static int player1Play = 1;

    //Making Scanner Object Global
    static Scanner sc;

    //Method to Initialize the Playing Board, Player Symbols
    private static void initializeGame() {
        playingBoard = new char[10];
        player1Sybmol = ' ';
        player2Symbol = ' ';
        initializeBoard();
    }

    //Method to Initialize Scanner Object
    private static void initScanner() {
        sc = new Scanner(System.in);
    }
    //UC 1 Method for initializing board position
    private static void initializeBoard() {
        for(int i = 1; i < 10; i++) {
            playingBoard[i] = '_';
        }
    }
    //UC 3 Showing the Board Elements
    private static void showBoard() {
        for (int i = 1; i < 10; i++) {
            if ((i) % 3 == 0) {
                System.out.println(playingBoard[i]);
                if (i != 10 - 1) {
                    System.out.println("-----");
                }
            } else {
                System.out.print(playingBoard[i] + "|");
            }
        }
    }
    //UC 2 Method for setting Input Symbol for User
    private static void setPlayingSymbol() {
        while(player1Sybmol != 'X' && player1Sybmol != 'O') {
            System.out.println("Player 1 Please Select the Symbol for Play: X or O");
            //Scanner Object Initilize Method for User Input
            initScanner();
            player1Sybmol = sc.next().toUpperCase().charAt(0);
            switch (player1Sybmol) {
                case 'X':
                    player2Symbol = 'O';
                    System.out.println("Player 1 Selected " + player1Sybmol + " Symbol");
                    System.out.println("Player 2 Selected " + player2Symbol + " Symbol");
                    break;
                case 'O':
                    player2Symbol = 'X';
                    System.out.println("Player 1 Selected " + player1Sybmol + " Symbol");
                    System.out.println("Player Selected " + player2Symbol + " Symbol");
                    break;
                default:
                    System.out.println("Please Select the Symbol From X or O only");
                    break;
            }
        }
    }
    //Method for Setting Player Turn
    private static void setPlayChance() {
        if(player1Play == 1) {
            player1Play = 0;
        } else {
            player1Play = 1;
        }
    }
    //UC 4 Get User Input and Move on Board
    private static void playGame() {
        if(player1Play == 1) {
            getSymbol(1,player1Sybmol);
        } else {
            getSymbol(2,player2Symbol);
        }
        setPlayChance();
    }
    //Methode for Getting Input From Player
    private static void getSymbol(int playerNo,char symbol) {
        initScanner();
        int playerPosition;
        System.out.println("Player "+playerNo+" Please Enter the Position for Your Play :");
        playerPosition = sc.nextInt();
        if(checkFreePosition(playerPosition)) {
            playingBoard[playerPosition] = symbol;
        } else {
            playGame();
            setPlayChance();
        }
    }
    //Uc 5 Check for Free Space and Make Move
    private static boolean checkFreePosition(int enteredPosition) {
        if(enteredPosition < 1 || enteredPosition > 9) {
            System.out.println("Please Enter the Position between 1 to 9 only.");
            return false;
        } else if(playingBoard[enteredPosition] != '_') {
            System.out.println("Entered Location Contain Symbol. Please Enter Another Location.");
            return false;
        } else {
            return true;
        }
    }
    //Uc 6 Make Toss for Player Chance
    private static void flipToss() {
        Random tossValue = new Random();
        int toss = tossValue.nextInt(2)+1;
        if(toss == 1) {
            player1Play = 1;
            System.out.println("Player 1 Won the Toss.");
        } else {
            player1Play = 0;
            System.out.println("Player 2 Won the Toss.");
        }
    }
    //UC 7 Check for Winner or Draw
    private static boolean checkWin() {
        if(checkDraw()) {
            System.out.println("The Game is DRAW. As there is no any location for Player Symbol.");
            showBoard();
            return true;
        } else {
            if(checkDiagonal() || checkColumnWin() || checkRowWin()) {
                if(player1Play == 0) {
                    System.out.println("Player 1 WON THE GAME !!!!");
                } else {
                    System.out.println("Player 2 WON THE GAME !!!!");
                }
                showBoard();
                return true;
            }
        }
        return false;
    }
    //Method to check for Game Draw
    private static boolean checkDraw() {
        boolean flag = true;
        for(int i = 1; i <= 9; i++) {
            if(playingBoard[i] == '_') {
                flag = false;
            }
        }
        return flag;
    }
    //Method to check for Diagonal Win
    private static boolean checkDiagonal() {
        if(!(playingBoard[1] == '_') && playingBoard[1] == playingBoard[5] && playingBoard[1] == playingBoard[9]
                || !(playingBoard[3] == '_') && playingBoard[3] == playingBoard[5] && playingBoard[3] == playingBoard[7]) {
            return true;
        }
        return false;
    }
    //Method to Check for Row Win
    private static boolean checkRowWin() {
        for( int i = 1; i < playingBoard.length; i += 3) {
            if(!(playingBoard[i] == '_') && playingBoard[i] == playingBoard[i+1] && playingBoard[i] == playingBoard[i+2]) {
                return true;
            }
        }
        return false;
    }
    //Method to Check for Column Win
    private static boolean checkColumnWin() {
        for(int i = 1; i <= 3; i++) {
            if(!(playingBoard[i] == '_') && playingBoard[i] == playingBoard[i+3] && playingBoard[i] == playingBoard[i+6]) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        //Tic Tac Toe Game Development
        System.out.println("Welcome to Tic Tac Toe Game Simulator.");
        //Play the Game till Win Or Draw
            char userChoice;
            do {
                //Set The New Game
                initializeGame();
                //Setting the Symbol for Play
                setPlayingSymbol();
                //Showing the Initial Board
                showBoard();
            //Flip the Toss for Player Play Chance
            flipToss();

            while(!checkWin()) {
                playGame();
                showBoard();
            }
            System.out.println("Do you want to Play Again? (Y/N) :");
            userChoice = sc.next().toUpperCase().charAt(0);
        }while(userChoice == 'Y');
    }
}
