package com.double_dz.tictactoe.controller;

import com.double_dz.tictactoe.Board;

import java.util.Scanner;

public class TicTacToeApp {
    private Scanner scanner = new Scanner(System.in);

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



    private void selectTheme(){
        System.out.println("Please select your preferred theme. [G]reen, [R]ed, [Y]ellow");
        String theme = scanner.nextLine();
        System.out.println(theme);
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

    private void createName(String mode){
        System.out.println("Please enter your name: ");
        String player1Name = scanner.nextLine();
        Board.humanPlayer.setName(player1Name);

        if(mode.equalsIgnoreCase("c")){
            System.out.println("Name for player: " + player1Name);
        }
        else{
            System.out.println("Please enter name of player 2");
            String player2Name = scanner.nextLine();
            Board.humanPlayer2.setName(player2Name);
            System.out.println("Name for player 1: " + player1Name);
            System.out.println("Name for player 2: " + player2Name);
        }
    }

    private void startGame(String mode){
        Board.play(mode);
    }

    private void moreOptions(String mode){
        while(true) {
            startGame(mode);


            boolean validInput = false;
            while(!validInput){
                System.out.println("Do you want to [R]estart the game? [P]lay again? Or [Q]uit game?");
                String options = scanner.nextLine().toUpperCase();
                switch (options){
                    case "R":
                        restartGame();
                        validInput = true;
                        break;
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
        startGame(mode);
    }

    private void restartGame(){

    }

    private void quitGame(){

    }


    private void gameSurvey(){


    }

}
