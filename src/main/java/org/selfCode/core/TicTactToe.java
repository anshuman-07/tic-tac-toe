package src.main.java.org.selfCode.core;

import src.main.java.org.selfCode.models.Player;
import src.main.java.org.selfCode.models.CombinedResult;
import src.main.java.org.selfCode.models.SuccessResult;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TicTactToe {
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private  HashMap<Integer, String> indexMap;

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public TicTactToe() {
        initializeBoard();
    }

    public void setPlayer1(Player player){
        this.player1 = player;
    }

    public void setPlayer2(Player player){
        this.player2 = player;
    }

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }



    private void initializeBoard() {
        indexMap = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            indexMap.put(i,String.valueOf(i));
        }
    }

    /**
     * Handles a click event at the specified index with the given value.
     * Updates the internal index map, checks for a win or completion,
     * and invokes the provided callback with a src.main.java.org.example.Result indicating the outcome.
     *
     * @param index    the index where the click occurred
     * @param value    the value to set at the specified index
     */
    public CombinedResult handleClick(int index, String value) {
        indexMap.put(index, value);
        SuccessResult result = isSuccess(index);
        if (result.isSuccess()) {
            return new CombinedResult(true, false, result.getList());
        } else if (isBoardFull()) {
            return new CombinedResult(false, true, List.of());
        } else {
            return new CombinedResult(false, false, List.of());
        }
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer.getPlayerName().equals(player1.getPlayerName()) ? player2 : player1;
    }

    /**
     * Returns the current mapping of indices to their assigned values.
     *
     * @return a HashMap where the key is the index and the value is the assigned String
     */
    public HashMap<Integer, String> getIndexMap() {
        return indexMap;
    }

    // 0, 1 , 2
    // 3, 4 , 5
    // 6, 7 , 8
    private static final List<List<Integer>> WINNING_LINES = List.of(
            List.of(0, 1, 2),
            List.of(3, 4, 5),
            List.of(6, 7, 8),
            List.of(0, 3, 6),
            List.of(1, 4, 7),
            List.of(2, 5, 8),
            List.of(0, 4, 8),
            List.of(2, 4, 6)
    );


    private SuccessResult isSuccess(int index) {
        for (List<Integer> line : WINNING_LINES) {
            if (line.contains(index)) {
                Object a = indexMap.get(line.get(0));
                Object b = indexMap.get(line.get(1));
                Object c = indexMap.get(line.get(2));

                if (a != null && Objects.equals(a, b) && Objects.equals(b, c)) {
                    return new SuccessResult(line, true);
                }
            }
        }
        return new SuccessResult(List.of(), false);
    }

    public boolean isBoardFull() {
        for (String cell : indexMap.values()) {
            if (cell.matches("\\d")) {
                return false;
            }
        }
        return true;

    }
}
