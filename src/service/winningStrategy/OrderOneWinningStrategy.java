package service.winningStrategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy{

    private int dimension;
    private List<HashMap<Character, Integer>> rowHashMaps;
    private List<HashMap<Character, Integer>> colHashMaps;
    private HashMap<Character, Integer> topLeftHashMap;
    private HashMap<Character, Integer> topRightHashMap;
    private HashMap<Character, Integer> cornerHashMap;


    public OrderOneWinningStrategy(int dimension) {
        this.dimension = dimension;
        rowHashMaps = new ArrayList<>();
        colHashMaps = new ArrayList<>();
        topLeftHashMap = new HashMap<>();
        topRightHashMap = new HashMap<>();
        cornerHashMap = new HashMap<>();

        for (int i = 0; i < dimension; i++) {
            rowHashMaps.add(new HashMap<>());
            colHashMaps.add(new HashMap<>());
        }
    }

    public boolean isTopLeftDiagonalCell(int row, int col){
        return row == col;
    }

    public boolean isTopRightDiagonalCell(int row, int col){
        return (row + col) == (dimension - 1);
    }

    public boolean isCornerCell(int row, int col){
        if(row == 0 || row == dimension - 1){
            return (col == 0 || col == dimension - 1);
        }
        return false;
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {

        Player player = lastMove.getPlayer();
        char symbol = player.getSymbol();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        if(checkRowWin(row, symbol)
                || checkColWin(col, symbol)
                || (isTopLeftDiagonalCell(row, col) && checkTopLeftWin(row, col, symbol))
                || (isTopRightDiagonalCell(row, col) && checkTopRightWin(row, col, symbol))
                || (isCornerCell(row, col) && checkCornerWin(row, col, symbol))){
            return player;
        }


        return null;
    }

    public boolean checkRowWin(int row, char symbol){
        if(!rowHashMaps.get(row).containsKey(symbol)){
            rowHashMaps.get(row).put(symbol, 0);
        }

        rowHashMaps.get(row).put(symbol, rowHashMaps.get(row).get(symbol) + 1);

        return rowHashMaps.get(row).get(symbol) == dimension;
    }

    public boolean checkColWin(int col, char symbol){
        if(!colHashMaps.get(col).containsKey(symbol)){
            colHashMaps.get(col).put(symbol, 0);
        }

        colHashMaps.get(col).put(symbol, colHashMaps.get(col).get(symbol) + 1);

        return colHashMaps.get(col).get(symbol) == dimension;
    }

    public boolean checkTopLeftWin(int row, int col, char symbol){

        if(!topLeftHashMap.containsKey(symbol)){
            topLeftHashMap.put(symbol, 0);
        }

        topLeftHashMap.put(symbol, topLeftHashMap.get(symbol) + 1);

        return topLeftHashMap.get(symbol) == dimension;
    }

    public boolean checkTopRightWin(int row, int col, char symbol){
        if(!topRightHashMap.containsKey(symbol)){
            topRightHashMap.put(symbol, 0);
        }

        topRightHashMap.put(symbol, topRightHashMap.get(symbol) + 1);

        return topRightHashMap.get(symbol) == dimension;
    }

    public boolean checkCornerWin(int row, int col, char symbol){
        if(!cornerHashMap.containsKey(symbol)){
            cornerHashMap.put(symbol, 0);
        }

        cornerHashMap.put(symbol, cornerHashMap.get(symbol) + 1);

        return topRightHashMap.get(symbol) == 4;
    }


}
