package com.interview.truecaller;


import javafx.util.Pair;

import java.util.List;
import java.util.Set;


public class WarnsdorffHeuristicTieBreaker extends ChequerboardTour{

    WarnsdorffHeuristicTieBreaker(int boardSize){
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
     *         Tie Breaker: In case, when we get two positions with same no. of avaiable moves, we select the one which is farthest from the current position
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
            List<Pair<Integer,Integer>> moves = movesAllowedFromPosition(possibleMove,boardSize,path);

            if(null != resultantNextMove &&  moves.size() == minMovesAllowed ){
                if(Double.compare(getEuclideanDistance(possibleMove,position),getEuclideanDistance(resultantNextMove,position))>0){
                    resultantNextMove = possibleMove;
                    minMovesAllowed = moves.size();
                }
            } else if(moves.size()<minMovesAllowed){
                resultantNextMove = possibleMove;
                minMovesAllowed = moves.size();

            }


        }

        return resultantNextMove;

    }



    double getEuclideanDistance (Pair<Integer,Integer> center, Pair<Integer,Integer> other) {
        int ycoord = Math.abs (center.getKey() - other.getKey());
        int xcoord = Math.abs (center.getValue()- other.getValue());
        double distance = Math.sqrt((ycoord)*(ycoord) +(xcoord)*(xcoord));

        return distance;
    }
}



