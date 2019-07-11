package com.interview.truecaller;

import java.util.*;
import javafx.util.Pair;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class WarnsdorffHeuristicTest {
    WarnsdorffHeuristic WarnsdorffHeuristicTour ;

    @Before
    public void setup(){
        WarnsdorffHeuristicTour = new WarnsdorffHeuristic(10);
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



        assertNull(WarnsdorffHeuristicTour.algoSpecificNextMove(position,10,path));

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

        Pair<Integer,Integer> nextMove = WarnsdorffHeuristicTour.algoSpecificNextMove(position,10,path);


        assertNotNull(nextMove);

        assertEquals(1,(long)nextMove.getKey());
        assertEquals(4,(long)nextMove.getValue());

    }

}
