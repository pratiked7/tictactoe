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

    @Override
    public Player checkWinner(Board board, Move lastMove) {

        Player player = lastMove.getPlayer();
        char symbol = player.getSymbol();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        if(checkRowWin(row, symbol)
                || checkColWin(col, symbol)
                || (isTopLeftDiagonalCell(row, col) && checkTopLeftWin(symbol))
                || (isTopRightDiagonalCell(row, col) && checkTopRightWin(symbol))
                || (isCornerCell(row, col) && checkCornerWin(symbol))){
            return player;
        }

        return null;
    }

    private boolean isTopLeftDiagonalCell(int row, int col){
        //cells in this diagonal will always satisfy following condition
        return row == col;
    }

    private boolean isTopRightDiagonalCell(int row, int col){
        //cells in this diagonal will always satisfy following condition
        return (row + col) == (dimension - 1);
    }

    private boolean isCornerCell(int row, int col){
        if(row == 0 || row == dimension - 1){
            return (col == 0 || col == dimension - 1);
        }
        return false;
    }

    private boolean checkRowWin(int row, char symbol){
        rowHashMaps.get(row).putIfAbsent(symbol, 0);
        rowHashMaps.get(row).put(symbol, rowHashMaps.get(row).get(symbol) + 1);
        return rowHashMaps.get(row).get(symbol) == dimension;
    }

    private boolean checkColWin(int col, char symbol){
        colHashMaps.get(col).putIfAbsent(symbol, 0);
        colHashMaps.get(col).put(symbol, colHashMaps.get(col).get(symbol) + 1);
        return colHashMaps.get(col).get(symbol) == dimension;
    }

    private boolean checkTopLeftWin(char symbol){
        topLeftHashMap.putIfAbsent(symbol, 0);
        topLeftHashMap.put(symbol, topLeftHashMap.get(symbol) + 1);
        return topLeftHashMap.get(symbol) == dimension;
    }

    private boolean checkTopRightWin(char symbol){
        topRightHashMap.putIfAbsent(symbol, 0);
        topRightHashMap.put(symbol, topRightHashMap.get(symbol) + 1);
        return topRightHashMap.get(symbol) == dimension;
    }

    private boolean checkCornerWin(char symbol){
        cornerHashMap.putIfAbsent(symbol, 0);
        cornerHashMap.put(symbol, cornerHashMap.get(symbol) + 1);
        return cornerHashMap.get(symbol) == 4;
    }
}
