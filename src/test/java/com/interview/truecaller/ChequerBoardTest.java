package com.interview.truecaller;

import java.util.*;
import javafx.util.Pair;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ChequerBoardTest {

    ChequerboardTour chequerboardTour ;

    @Before
    public void setup(){
        chequerboardTour = new WarnsdorffHeuristic(10);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void TestGetPathUnModifiable(){

        Set<Pair<Integer,Integer>> path = chequerboardTour.getPath();

        /**
        Since, we get the unmodifiable version of the path, we would not be able to modify it and should get exception.
         */
        path.add(new Pair<>(1,1));


    }

    @Test
    public void TestResetPath(){
        if(chequerboardTour.findPath()){
            Set<Pair<Integer,Integer>> path = chequerboardTour.getPath();
            /**
            Since, we got the tour Path, size of the path set should be 10*10 = 100
             */
            assertEquals(100,path.size());

            // Now, lets reset the path and test the size, it should be 0
            chequerboardTour.reset();
            path = chequerboardTour.getPath();
            assertEquals(0,path.size());
        }

    }

    @Test
    public void TestMoveInRange(){

        boolean isInRangeStatus = chequerboardTour.isMoveInRangeAndNotVisited(new Pair<>(-1,3),10,new LinkedHashSet<>());

        /**
         * Since, the position is outside the boardlimit, it should be false
         */
        assertFalse(isInRangeStatus);

        isInRangeStatus = chequerboardTour.isMoveInRangeAndNotVisited(new Pair<>(4,3),10,new LinkedHashSet<>());

        /**
         * Since, the position is within the boardlimit and not visited, it should be true
         */
        assertTrue(isInRangeStatus);

    }

    @Test
    public void TestMoveInRangeAndNotVisited(){

        Set<Pair<Integer,Integer>> path = new LinkedHashSet<>();
        path.add(new Pair<>(1,3));
        boolean isInRangeStatus = chequerboardTour.isMoveInRangeAndNotVisited(new Pair<>(1,3),10,path);

        /**
         * Since, the position is outside the boardlimit, but it is visited it should be false
         */
        assertFalse(isInRangeStatus);

        isInRangeStatus = chequerboardTour.isMoveInRangeAndNotVisited(new Pair<>(4,3),10,path);

        /**
         * Since, the position is within the boardlimit, and not visited yet, it should be true
         */
        assertTrue(isInRangeStatus);

    }

    @Test
    public void TestMovesAllowedFromPosition(){
        Set<Pair<Integer,Integer>> path = new LinkedHashSet<>();
        path.add(new Pair<>(3,6));
        path.add(new Pair<>(9,6));
        path.add(new Pair<>(6,9));
        path.add(new Pair<>(8,4));
        path.add(new Pair<>(8,8));

        List<Pair<Integer,Integer>> moves = chequerboardTour.movesAllowedFromPosition(new Pair<>(6,6),10,path);

        assertEquals(3,moves.size());

        assertEquals(6,(long)moves.get(0).getKey());
        assertEquals(3,(long)moves.get(0).getValue());

        assertEquals(4,(long)moves.get(1).getKey());
        assertEquals(8,(long)moves.get(1).getValue());

        assertEquals(4,(long)moves.get(2).getKey());
        assertEquals(4,(long)moves.get(2).getValue());


    }

}
