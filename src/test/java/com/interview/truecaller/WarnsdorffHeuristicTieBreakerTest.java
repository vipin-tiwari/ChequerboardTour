package com.interview.truecaller;

import java.util.*;
import javafx.util.Pair;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;





public class WarnsdorffHeuristicTieBreakerTest {
    WarnsdorffHeuristicTieBreaker WarnsdorffHeuristicTieBreakerTour ;

    @Before
    public void setup(){
        WarnsdorffHeuristicTieBreakerTour = new WarnsdorffHeuristicTieBreaker(10);
    }

    @Test
    public void TestTieBreakerNextMove(){
        List<Map<String,Integer>> table = new ArrayList<>();
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(6,6);
        Set<Pair<Integer,Integer>> path = new LinkedHashSet<>();
        path.add(new Pair<>(3,6));
        path.add(new Pair<>(9,6));
        path.add(new Pair<>(6,3));
        path.add(new Pair<>(4,8));
        path.add(new Pair<>(4,4));
        path.add(new Pair<>(8,4));
        path.add(new Pair<>(3,9));
        path.add(new Pair<>(4,7));

        /**
         * currPosition: (6,6)
         * positionAddedToPath : (3,6),(9,6),(6,3),(4,8),(4,4),(8,4)
         * Available Positions : (8,8) and (6,9)
         * Expected: Since, this is a Tie we should get (6,9) as it is farthest from the point (6,6)
         *
         */

        List<Pair<Integer,Integer>> moves1 = WarnsdorffHeuristicTieBreakerTour.movesAllowedFromPosition(new Pair<>(8,8),10,path);
        List<Pair<Integer,Integer>> moves2 = WarnsdorffHeuristicTieBreakerTour.movesAllowedFromPosition(new Pair<>(6,9),10,path);

        assertEquals(3,moves1.size());
        assertEquals(3,moves2.size());

        /**
         * Both the available position has equal possible moves, So, its a Tie.
         * The algorithm should give the point farthest from the (6,6).
         */
        assertEquals(moves1.size(),moves2.size());

        double distance1 = WarnsdorffHeuristicTieBreakerTour.getEuclideanDistance(new Pair<>(6,6),new Pair<>(8,8));
        double distance2 = WarnsdorffHeuristicTieBreakerTour.getEuclideanDistance(new Pair<>(6,6),new Pair<>(6,9));

        assertEquals(2.8284271247461903,distance1,0.1);
        assertEquals(3.0,distance2,0.1);

        Pair<Integer,Integer> nextMove = WarnsdorffHeuristicTieBreakerTour.algoSpecificNextMove(position,10,path);


        assertNotNull(nextMove);

        assertEquals(6,(long)nextMove.getKey());
        assertEquals(9,(long)nextMove.getValue());

    }


    @Test
    public void TestEdgePointNullNextMove(){
        List<Map<String,Integer>> table = new ArrayList<>();
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(1,1);
        Set<Pair<Integer,Integer>> path = new LinkedHashSet<>();
        path.add(new Pair<>(4,1));
        path.add(new Pair<>(3,3));
        path.add(new Pair<>(3,1));
        path.add(new Pair<>(1,4));

        /**
         * currPosition: (1,1)
         * positionAddedToPath : (4,1),(3,3),(3,1),(1,4)
         * Available Positions : N/A as it is an edge point and rest available are already occupied.
         * Expected: Since, there are no position left, we should be getting null.
         */



        assertNull(WarnsdorffHeuristicTieBreakerTour.algoSpecificNextMove(position,10,path));

    }

    @Test
    public void TestEdgePointNextMove(){
        List<Map<String,Integer>> table = new ArrayList<>();
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(1,1);
        Set<Pair<Integer,Integer>> path = new LinkedHashSet<>();
        path.add(new Pair<>(4,1));
        path.add(new Pair<>(3,3));
        path.add(new Pair<>(3,1));

        /**
         * currPosition: (1,1)
         * positionAddedToPath : (4,1),(3,3),(3,1)
         * Available Positions : (1,4)
         * Expected: Since, there are only one position left we would get (1,4)
         */

        Pair<Integer,Integer> nextMove = WarnsdorffHeuristicTieBreakerTour.algoSpecificNextMove(position,10,path);


        assertNotNull(nextMove);

        assertEquals(1,(long)nextMove.getKey());
        assertEquals(4,(long)nextMove.getValue());

    }
}
