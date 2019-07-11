package com.interview.truecaller;

import java.util.List;
import java.util.Set;
import javafx.util.Pair;


public class WarnsdorffHeuristic extends ChequerboardTour{

    WarnsdorffHeuristic(int boardSize){
        super(boardSize);
    }

    /**
     *
     * @param position given a position on the board
     * @param boardSize lenght / breadth of the board
     * @param path list of all points already traversed till this stage.
     * @return Implements the greedy method of Warnsdorff's implementation of Knights Tour problem.
     *         This will consider all the possible moves available from the given point,
     *         but will return the move which itself has least available positions to move.
     *         Why ? : This way we are covering the edges first and avoiding the tour to get stuck in the middle cells
     */
    @Override
    public Pair<Integer,Integer> algoSpecificNextMove(Pair<Integer,Integer> position, int boardSize, Set<Pair<Integer,Integer>> path){
        int minMovesAllowed = Integer.MAX_VALUE;
        Pair<Integer,Integer> possibleMove;
        Pair<Integer,Integer> resultantNextMove = null;

        for(int i=0;i<VALID_MOVES.length;i++){
            possibleMove = new Pair<>(position.getKey()+VALID_MOVES[i][0],position.getValue()+VALID_MOVES[i][1]);
            if(!isMoveInRangeAndNotVisited(possibleMove,boardSize,path)){
                continue;
            }
            List<Pair<Integer,Integer>> moves = movesAllowedFromPosition(possibleMove);
            if( moves.size()<minMovesAllowed){
                resultantNextMove = possibleMove;
                minMovesAllowed = moves.size();

            }
        }

        return resultantNextMove;

    }



}
