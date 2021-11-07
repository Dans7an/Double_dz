package com.double_dz.tictactoe.client;


import com.double_dz.tictactoe.ComputerPlayer;
import com.double_dz.tictactoe.HumanPlayer;
import com.double_dz.tictactoe.Letter;
import com.double_dz.tictactoe.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<List> winningCombos = new ArrayList<>();
    public static Player humanPlayer = new HumanPlayer();
    public static ComputerPlayer cpuPlayer = new ComputerPlayer();

    public static void main(String[] args) {
     /*   char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'_','+','_','+','_'},
                {' ', '|', ' ', '|', ' '},
                {'_','+','_','+','_'},
                {' ', '|', ' ', '|', ' '}};
        printBoard(gameBoard);*/

        char[][] exampleBoard = {
                {'1', '|', '2', '|', '3'},
                {'_','+','_','+','_'},
                {'4', '|', '5', '|', '6'},
                {'_','+','_','+','_'},
                {'7', '|', '8', '|', '9'}};
        printBoard(exampleBoard);
        List<Integer> selectedPosition = new ArrayList<>();
        List<Integer> availablePosition = createNumbers();
        System.out.println("Please enter a number between 1 and 9");
        while(true) {
            Scanner scanner = new Scanner(System.in);
            int position = scanner.nextInt();
            if (position > 9 || position < 1) {
                System.out.println("Invalid input: " + position + ". Please enter a number between 1 and 9.");
            }
            else if(selectedPosition.contains(position)){
                System.out.println("Position " + position + " already selected, please choose again from " + availablePosition);
            }
            else {
                addLetter(exampleBoard, position, "Player");
                humanPlayer.placeLetter(position);
                availablePosition.remove(Integer.valueOf(position));
                if(checkWinner(humanPlayer.getSelectedPositions(), humanPlayer).length() > 0){
                    break;
                }
                if(availablePosition.size()==0){
                    printBoard(exampleBoard);
                    System.out.println("Game over! Result: Tie.");
                    break;
                }
                int cpuPosition = cpuPlayer.generateCPUPosition(availablePosition);
                addLetter(exampleBoard, cpuPosition, "Computer");
                availablePosition.remove(cpuPlayer.getCPUPositionIndex());
                selectedPosition.add(cpuPosition);
            }
            if (checkWinner(cpuPlayer.getSelectedPositions(), cpuPlayer).length() > 0){
                break;
            }
            selectedPosition.add(position);
            printBoard(exampleBoard);
        }
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

   public static void addLetter(char[][] board, int position, String name){
        char sign;
        if(name.equals("Player")){
            sign=Letter.X.getLetter();
        }
        else{
            sign=Letter.O.getLetter();
        }
        switch (position){
            case 1:
                board [0][0] = sign;
                break;
            case 2:
                board [0][2] =  sign;
                break;
            case 3:
                board [0][4] =  sign;
               break;
            case 4:
                board [2][0] =  sign;
                break;
            case 5:
                board [2][2] =  sign;
                break;
            case 6:
                board [2][4] =  sign;
                break;
            case 7:
                board [4][0] =  sign;
                break;
            case 8:
                board [4][2] =  sign;
                break;
            case 9:
                board [4][4] =  sign;
                break;
        }

    }

    public static List<Integer> createNumbers(){
         List<Integer> availableNumbers = new ArrayList<>();
         for(int i = 1; i <= 9; i++){
             availableNumbers.add(i);
         }
         return availableNumbers;
    }

    public static String checkWinner(List<Integer> positions, Player player){
        String whoWon = "";
        System.out.println("winningCombos: " + winningCombos);
        System.out.println(player.getClass().getSimpleName() + " positions " + positions);
        for (var i: winningCombos) {
            if (positions.containsAll(i)){
                whoWon = player.getClass().getSimpleName() + ", You win";
                System.out.println(whoWon);
            }
        }
        return whoWon;
    }

    static {
        winningCombos.add(Arrays.asList(1,2,3));
        winningCombos.add(Arrays.asList(4,5,6));
        winningCombos.add(Arrays.asList(7,8,9));
        winningCombos.add(Arrays.asList(1,4,7));
        winningCombos.add(Arrays.asList(2,5,8));
        winningCombos.add(Arrays.asList(3,6,9));
        winningCombos.add(Arrays.asList(1,5,9));
        winningCombos.add(Arrays.asList(7,5,3));
    }
}