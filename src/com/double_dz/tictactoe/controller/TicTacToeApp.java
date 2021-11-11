package com.double_dz.tictactoe.controller;

import com.apps.util.Console;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

// implements Serializable 
public class TicTacToeApp {
    private Scanner scanner = new Scanner(System.in);
    private Board board;
    private String player1Name;
    private String player2Name;
    private String backgroundColor;
    private static final String dataFilePath = "resources/banner.txt";

    public void execute(){
        welcome();
        selectTheme();
        String selectMode = selectMode();
        createName(selectMode);
        Console.clear();
        // startGame(selectMode);
        moreOptions(selectMode);
        // gameSurvey();

    }
    private void welcome(){
        try {
            String lines = Files.readString(Path.of(dataFilePath));
            System.out.println(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void selectTheme() {
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please select your preferred board color. [G]reen, [R]ed, [Y]ellow, [D]efault:  ");
            String theme = scanner.nextLine().toUpperCase();
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
            System.out.print("Please select your preferred play mode. Please enter [C] to play with computer, or [M] to play with another player: ");
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
        System.out.print("Please enter your name, Player 1: ");
        player1Name = scanner.nextLine();
        //board.humanPlayer.setName(player1Name);

        if(mode.equalsIgnoreCase("c")){
            System.out.println("Name for player: " + player1Name);
        }
        else{
            System.out.print("Please enter your name, Player 2: ");
            player2Name = scanner.nextLine();
           // board.humanPlayer2.setName(player2Name);
            System.out.println("Player 1: " + player1Name + " you'll go first, you are X");
            System.out.println("Player 2: " + player2Name + " you'll go second, you are O");
        }
    }


    private void startGame(String mode){
        createBoard();
        board.play(mode);
    }

    private void moreOptions(String mode){
        startGame(mode);
        while(true) {
            boolean validInput = false;
            while(!validInput){
                System.out.print("Do you want to [P]lay again? Or [Q]uit game?   ");
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
                }
            }
        }
    }

    private void playAgain(String mode){
        startGame(mode);
    }

    private void quitGame(){
        gameSurvey();
    }


    private void gameSurvey(){
        System.out.println("Please rate our Tic-Tac-Toe game: ");
        System.out.print("Is this the best game app you've ever used? Please enter [Y]es: ");
        String survey = scanner.nextLine();
        System.out.println("Thank you for giving us a 5 Star rating!");
    }

}
