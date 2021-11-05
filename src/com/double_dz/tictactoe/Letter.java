package com.double_dz.tictactoe;

public enum Letter {
    X('X'),
    O('O');

    private char letter;
    Letter(char letter){
        this.letter=letter;
    }

    public char getLetter() {
        return letter;
    }
}
