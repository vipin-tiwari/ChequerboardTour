package com.interview.truecaller;

public class BoardTour {
    public static void main(String[] args){

        long timeTakeByWarnsdorffHeuristic=0;
        long timeTakeByWarnsdorffHeuristicTieBreaker=0;

        ChequerboardTour boardTour = new WarnsdorffHeuristic(10);
        long time = System.nanoTime();
        if(boardTour.findPath()){
            timeTakeByWarnsdorffHeuristic =  System.nanoTime() - time;
            System.out.printf("\nTime used to solve by WarnsdorffHeuristic : %d nanoseconds\n",
                    timeTakeByWarnsdorffHeuristic);
            boardTour.printPathTablular();
        }




        boardTour = new WarnsdorffHeuristicTieBreaker(10);
        time = System.nanoTime();
        if(boardTour.findPath()){
            timeTakeByWarnsdorffHeuristicTieBreaker = System.nanoTime() - time;
            System.out.printf("\nTime used to solve by WarnsdorffHeuristicTieBreaker : %d nanoseconds\n",
                    timeTakeByWarnsdorffHeuristicTieBreaker);
            boardTour.printPathTablular();
        }

        System.out.printf("\nTime taken by WarnsdorffHeuristicTieBreaker is less than WarnsdorffHeuristic by  : %d nanoseconds\n" +
                "This shows WarnsdorffHeuristicTieBreaker is Efficient by the Factor of %d percentage",
                (timeTakeByWarnsdorffHeuristic - timeTakeByWarnsdorffHeuristicTieBreaker),
                (timeTakeByWarnsdorffHeuristic )/timeTakeByWarnsdorffHeuristicTieBreaker*100);






    }
}
