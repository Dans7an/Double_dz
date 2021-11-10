package com.double_dz.tictactoe.controller;

import com.double_dz.tictactoe.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {

    private HumanPlayer human;
    private List<Integer> selectedPosition;
    private List<Integer> availablePosition;
    private Board board;
    private char[][] exampleBoard;
    @Before
    public void setUp(){
        exampleBoard = new char[][]{
                {'1', '|', '2', '|', '3'},
                {'_','+','_','+','_'},
                {'4', '|', '5', '|', '6'},
                {'_','+','_','+','_'},
                {'7', '|', '8', '|', '9'}
        };
        board = new Board();
        human = new HumanPlayer();
        selectedPosition = new ArrayList<>();
        availablePosition = new ArrayList<>();
        for(int i = 1; i <= 9; i++){
            availablePosition.add(i);
        }
    }

    @Test
    public void validatePositionReturnsTrue_whenPositionIsValid() {
        assertTrue(board.validatePosition(4,selectedPosition,availablePosition));
    }

    @Test
    public void validatePositionReturnsFalse_whenPositionIsInvalid() {
        assertFalse(board.validatePosition(10,selectedPosition,availablePosition));
    }

    @Test
    public void testAddLetter() {
        board.addLetter(board.exampleBoard,6 ,"Player 1");
        board.printBoard(board.exampleBoard);

    }
}