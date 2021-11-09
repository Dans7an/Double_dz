package com.double_dz.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer implements Player {

    private List<Integer> humanSelectedPositions = new ArrayList<>();
    private String name;


    @Override
    public List<Integer> placeLetter(int position) {
        humanSelectedPositions.add(position);
        return humanSelectedPositions;
    }

    public List<Integer> getSelectedPositions() {
        return humanSelectedPositions;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


}
