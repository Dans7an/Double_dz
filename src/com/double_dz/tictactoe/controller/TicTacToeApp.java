package com.double_dz.tictactoe.controller;

import com.double_dz.tictactoe.Board;

import java.util.Scanner;

public class TicTacToeApp {
    private Scanner scanner = new Scanner(System.in);
    private Board board;
    private String player1Name;
    private String player2Name;
    private String backgroundColor;

    public void execute(){
        welcome();
        selectTheme();
        String selectMode = selectMode();
        createName(selectMode);
        // startGame(selectMode);
        moreOptions(selectMode);
        // gameSurvey();

    }
    private void welcome(){
        System.out.println();
        System.out.println("Welcome to Double d'Z Tic-Tac-Toe Game!");
        System.out.println("\n");
    }


    private void selectTheme() {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Please select your preferred board color. [G]reen, [R]ed, [Y]ellow, [D]efault");
            String theme = scanner.nextLine();
            switch (theme) {
                case "G":
                    backgroundColor = "\u001B[42m";
                    validInput = true;
                    break;
                case "R":
                    backgroundColor = "\u001B[41m";
                    validInput = true;
                    break;
                case "Y":
                    backgroundColor = "\u001B[43m";
                    validInput = true;
                    break;
                case "D":
               //     backgroundColor = "\u001B[40m";
                    backgroundColor = "";
                    validInput = true;
                    break;
            }
            System.out.println(theme);
        }
    }


    private String selectMode(){
        String selectedMode = null;
        boolean validInput = false;
        while(!validInput){
            System.out.println("Please select your preferred play mode. Please enter [C] to play with computer, or [M] to play with another player");
            String mode = scanner.nextLine().toUpperCase();
            System.out.println(mode);
            if(mode.matches("C|M")){
                selectedMode = mode;
                validInput = true;
            }
        }

        return selectedMode;
    }

    private void createBoard(){
        board = new Board();
        board.setBoardColor(backgroundColor);
        board.humanPlayer.setName(player1Name);
        board.humanPlayer2.setName(player2Name);

    }

    private void createName(String mode){
        System.out.println("Please enter your name: ");
        player1Name = scanner.nextLine();
        //board.humanPlayer.setName(player1Name);

        if(mode.equalsIgnoreCase("c")){
            System.out.println("Name for player: " + player1Name);
        }
        else{
            System.out.println("Please enter name of player 2");
            player2Name = scanner.nextLine();
           // board.humanPlayer2.setName(player2Name);
            System.out.println("Name for player 1: " + player1Name);
            System.out.println("Name for player 2: " + player2Name);
        }
    }


    private void startGame(String mode){
        createBoard();
        board.play(mode);
    }

    private void moreOptions(String mode){
        while(true) {
            startGame(mode);

            boolean validInput = false;
            while(!validInput){
                System.out.println("Do you want to [P]lay again? Or [Q]uit game?");
                String options = scanner.nextLine().toUpperCase();
                switch (options){
                    case "P":
                        playAgain(mode);
                        validInput = true;
                        break;
                    case "Q":
                        quitGame();
                        validInput = true;
                        return;
                    // break;
                }
            }

        }

    }

    private void playAgain(String mode){
        moreOptions(mode);
    }

    private void quitGame(){
        gameSurvey();
    }


    private void gameSurvey(){
        System.out.println("Please rate our Tic-Tac-Toe game: ");
        System.out.println("Is this the best game app you've ever used? Please enter [Y]es");
        String survey = scanner.nextLine();
        System.out.println("Thank you for giving us a 5 Star rating!");
    }

}
