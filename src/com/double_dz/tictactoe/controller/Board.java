package com.double_dz.tictactoe.controller;

import com.double_dz.tictactoe.ComputerPlayer;
import com.double_dz.tictactoe.HumanPlayer;
import com.double_dz.tictactoe.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Board {
        public static List<List> winningCombos = new ArrayList<>();
        public Player humanPlayer = new HumanPlayer();
        public  Player humanPlayer2 = new HumanPlayer();
        public ComputerPlayer cpuPlayer = new ComputerPlayer();
        public  List<Integer> selectedPosition = new ArrayList<>();
        public  List<Integer> availablePosition = createNumbers();
        private String boardColor;
        private static final String resetColor = "\u001B[0m";
        public char[][] exampleBoard = {
                {'1', '|', '2', '|', '3'},
                {'_','+','_','+','_'},
                {'4', '|', '5', '|', '6'},
                {'_','+','_','+','_'},
                {'7', '|', '8', '|', '9'}};


        public void play(String mode){
            printBoard(exampleBoard);
            System.out.println("Please enter a number between 1 and 9");
            if(mode.equals("C")){
                playComputer(selectedPosition, availablePosition);
            }
            else{
                multiPlayer(selectedPosition, availablePosition);
            }
        }
         boolean validatePosition(int position,List<Integer> selectedPosition, List<Integer> availablePosition){
            boolean isValidPosition = false;
            if (position > 9 || position < 1) {
                System.out.println("Invalid input. Please enter one of these available numbers: " + availablePosition);
            } else if (selectedPosition.contains(position)) {
                System.out.println("Position " + position + " already selected, please choose again from " + availablePosition);
            }
            else{
                isValidPosition = true;
            }
            return isValidPosition;
        }

        public void multiPlayer(List<Integer> selectedPosition, List<Integer> availablePosition){
            boolean isPlayer2PositionValid = true;
            while(true) {
                System.out.println(humanPlayer.getName() + ", it is your turn: ");
                Scanner scanner = new Scanner(System.in);
                boolean isPositionANumber = scanner.hasNextInt();

                if (isPositionANumber && isPlayer2PositionValid) {
                    int player1Position = scanner.nextInt();
                    if (validatePosition(player1Position, selectedPosition, availablePosition)) {
                        addLetter(exampleBoard, player1Position, "Player 1");
                        humanPlayer.placeLetter(player1Position);
                        availablePosition.remove(Integer.valueOf(player1Position));
                        if (checkWinner(humanPlayer.getSelectedPositions(), humanPlayer).length() > 0) {
                            break;
                        }
                        if (availablePosition.size() == 0) {
                            printBoard(exampleBoard);
                            System.out.println("Game over! Result: Tie.");
                            break;
                        }
                        selectedPosition.add(player1Position);
                        printBoard(exampleBoard);
                    } else {
                        continue;
                    }
                }

                if(isPositionANumber) {
                    System.out.println(humanPlayer2.getName() + ", it is your turn: ");
                    boolean isPosition2ANumber = scanner.hasNextInt();
                    if (isPosition2ANumber) {
                        int player2Position = scanner.nextInt();
                        System.out.println("working");
                        if (validatePosition(player2Position, selectedPosition, availablePosition)) {
                            addLetter(exampleBoard, player2Position, "Player 2");
                            humanPlayer2.placeLetter(player2Position);
                            availablePosition.remove(Integer.valueOf(player2Position));
                            if (checkWinner(humanPlayer2.getSelectedPositions(), humanPlayer2).length() > 0) {
                                break;
                            }
                            if (availablePosition.size() == 0) {
                                printBoard(exampleBoard);
                                System.out.println("Game over! Result: Tie.");
                                break;
                            }
                            selectedPosition.add(player2Position);
                            printBoard(exampleBoard);
                            isPlayer2PositionValid = true;
                        }
                    } else {
                        isPlayer2PositionValid = false;
                    }
                }
            }
        }
    public void playComputer(List<Integer> selectedPosition, List<Integer> availablePosition){
            while(true) {
               // Console.clear();
            Scanner scanner = new Scanner(System.in);
            boolean isPositionANumber = scanner.hasNextInt();

            if(isPositionANumber){
                int position = scanner.nextInt();
                if (validatePosition(position, selectedPosition, availablePosition)) {
                    addLetter(exampleBoard, position, "Player 1");
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
            else {
                System.out.println("Invalid input. Please enter one of these available numbers: " + availablePosition);
            }
        }

    }
        public void printBoard(char[][] board) {
            for (char[] row : board) {
                for (char c : row) {
                    System.out.print(getBoardColor() + c + resetColor);
                }
                System.out.println();
            }
        }

        public void addLetter(char[][] board, int position, String name){
            char sign;
            if(name.equals("Player 1")){
                sign='X';
            }
            else{
                sign='O';
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

        public List<Integer> createNumbers(){
            List<Integer> availableNumbers = new ArrayList<>();
            for(int i = 1; i <= 9; i++){
                availableNumbers.add(i);
            }
            return availableNumbers;
        }

        public String checkWinner(List<Integer> positions, Player player){
            String whoWon = "";
            // System.out.println("winningCombos: " + winningCombos);
            System.out.println(player.getName() + " positions " + positions);
            for (var i: winningCombos) {
                if (positions.containsAll(i)){
                    whoWon = player.getName() + " win. Congratulations!";
                    System.out.println(whoWon);
                }
            }
            return whoWon;
        }

    public String getBoardColor() {
        return boardColor;
    }

    public void setBoardColor(String boardColor) {
        this.boardColor = boardColor;
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




















   /* char[][] gameBoard = {
            {'_', '|', '_', '|', '_'},
            {'_', '|', '_', '|', '_'},
            {' ', '|', ' ', '|', ' '}};*/
}
