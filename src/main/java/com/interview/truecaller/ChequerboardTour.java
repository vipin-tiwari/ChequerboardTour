package com.interview.truecaller;

import java.util.*;
import javafx.util.Pair;


public abstract class ChequerboardTour {

    static final int[][] VALID_MOVES = {
            { 3, 0 }, { -3, 0 }, { 0, 3 }, { 0, -3 },
            { 2, 2 }, { 2, -2 }, { -2, 2 }, { -2, -2 }
    };

    private Set<Pair<Integer,Integer>> path;
    private int boardSize;

    ChequerboardTour(int boardSize) {
        this.boardSize = boardSize;
        this.reset();

    }

    Set<Pair<Integer,Integer>> getPath(){

        return Collections.unmodifiableSet(this.path);
    }

    /**
     * Print path elements in the order of traversal
     */
    void printPath(){

        StringBuilder sb = new StringBuilder();
        for (Pair<Integer,Integer> p : getPath()) {
            sb.append("N: ").append(p).append('\n');
        }
        System.out.print(sb);
        System.out.println();

        System.out.println("Total Moves in the Path: "+getPath().size());
    }

    /**
     * Print path elements in the tabular form by iterating the ordered list of traversal points
     */
    void printPathTablular(){
        int[][] board = new int[boardSize][boardSize];

        for (int i=0; i < boardSize ; i++){
            for (int j=0; j < boardSize ; j++){
                board[i][j] = -1;
            }

        }

        int count = 0;
        for(Pair<Integer,Integer> point: getPath()){
            board[point.getKey()][point.getValue()] = count++;
        }

        for (int i=0; i < boardSize ; i++){
            for (int j=0; j < boardSize ; j++){
                System.out.print(board[i][j] + "\t");
            }
            System.out.print("\n");
        }



        System.out.println("Total Moves in the Path: "+getPath().size());
    }

    /**
     * resets the path to the empty. This takes to the initial point.
     */
    void reset(){
        this.path = new LinkedHashSet<Pair<Integer,Integer>>(boardSize * boardSize);
    }

    /**
     *
     * @param p current position in the board
     * @return status if there was atleast one path found which traversed each box in the board, when started from given current position
     */
    private boolean findPathFromPosition(Pair<Integer,Integer> p) {

        this.path.add(new Pair<Integer,Integer>(p.getKey(),p.getValue()));
        while (this.path.size() < this.boardSize * this.boardSize) {
            Pair<Integer,Integer> nextMove = getNextMove(p);
            if (null == nextMove) {
                System.out.println("No more Points : ("+p.getKey()+","+p.getValue()+")");
                return false;
            }

            this.path.add(p = nextMove);
        }
        return true;
    }

    /**
     *
     * @return status if there was atleast one path found which traversed each box in the board, from any given starting point
     */

    public boolean findPath(){

        for(int i=0;i<this.boardSize;i++){
            for(int j=0;j<this.boardSize;j++){
                reset();
                System.out.println("Starting with position: ("+i+","+j+").");

                if(findPathFromPosition(new Pair<>(i,j))){
                    return true;
                }

            }
        }

        return false;
    }

    /**
     *
     * @param position current position
     * @return it returns the next best move to go to. this depends on the implementation of the algorithm
     */
    private Pair<Integer,Integer> getNextMove(Pair<Integer,Integer> position) {
        return algoSpecificNextMove(position, this.boardSize, this.path);
    }

    /**
     *
     * @param position  position to be checked
     * @param boardSize lenght / breadth of the board
     * @param path list of all points already traversed till this stage.
     * @return status if the given position is within the limits of the board and has not been visited
     */
    boolean isMoveInRangeAndNotVisited(Pair<Integer,Integer> position, int boardSize, Set<Pair<Integer,Integer>> path){

        if (position.getKey() >= 0 && position.getValue() >= 0 && position.getKey() < boardSize && position.getValue() < boardSize) {
            if (!path.contains(position)) {

                //System.out.println("Move is Valid : ("+position.getKey()+","+position.getValue()+")"+" boardSize: "+boardSize+" .");
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param position given a position on the board
     * @return list of all possible position which could be considered for the next move.
     */
    List<Pair<Integer,Integer>> movesAllowedFromPosition(Pair<Integer,Integer> position){
        return this.movesAllowedFromPosition(position,this.boardSize,this.path);

    }

    /**
     *
     * @param position given a position on the board
     * @param boardSize lenght / breadth of the board
     * @param path list of all points already traversed till this stage.
     * @return list of all possible position which could be considered for the next move.
     */
    List<Pair<Integer,Integer>> movesAllowedFromPosition(Pair<Integer,Integer> position, int boardSize, Set<Pair<Integer,Integer>> path){
        List<Pair<Integer,Integer>> movesAllowed = new ArrayList<>();
        Pair<Integer,Integer> nextMove;



        for(int i=0;i<VALID_MOVES.length;i++){
            nextMove = new Pair<>(position.getKey()+VALID_MOVES[i][0],position.getValue()+VALID_MOVES[i][1]);
            if(isMoveInRangeAndNotVisited(nextMove, boardSize,  path)){
                movesAllowed.add(nextMove);
            }

        }

        return movesAllowed;

    }

    /**
     *
     * @param position given a position on the board
     * @param boardSize lenght / breadth of the board
     * @param path list of all points already traversed till this stage.
     * @return This is an abstract method, to be implemented by the different algorithmic implementation, to find the best next move
     */
    public abstract Pair<Integer,Integer> algoSpecificNextMove(Pair<Integer,Integer> position, int boardSize, Set<Pair<Integer,Integer>> path);

}
