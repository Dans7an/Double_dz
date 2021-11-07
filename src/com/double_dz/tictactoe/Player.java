package com.double_dz.tictactoe;

import java.util.List;

public interface Player {
    public List<Integer> placeLetter(int position);
    public List<Integer> getSelectedPositions();
}
