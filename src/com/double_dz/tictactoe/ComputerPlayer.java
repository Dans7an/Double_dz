package com.double_dz.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class ComputerPlayer implements Player {

    private List<Integer> cpuSelectedPositions = new ArrayList<>();
    private int cpuIndex;
    private String name = "Computer";

    @Override
    public List<Integer> placeLetter(int position) {
        cpuSelectedPositions.add(position);
        return cpuSelectedPositions;
    }

    public int generateCPUPosition(List<Integer> availablePosition){
        cpuIndex = (int) Math.floor(Math.random() * availablePosition.size());
        int cpuPosition = availablePosition.get(cpuIndex);
        placeLetter(cpuPosition);
        return cpuPosition;
    }

    public int getCPUPositionIndex(){
        return cpuIndex;
    }

    @Override
    public List<Integer> getSelectedPositions() {
        return cpuSelectedPositions;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return name;
    }
}
