This is the solution to the chequerboard tour problem.
# ChequerboardTour

It has solved the problem by implementing the Warnsdorff's Heuristic method to find a path such that it includes all the boxes of the 10x10 chequerboard.

##How to Run:

1. To build the code you can use gradle:
   `gradle build`

   ```
   Starting a Gradle Daemon, 1 incompatible Daemon could not be reused, use --status for details
   BUILD SUCCESSFUL in 8s 
   4 actionable tasks: 4 execute
   ```

2. To run the unit test cases:
	`gradle test`

	```
	BUILD SUCCESSFUL in 0s
	3 actionable tasks: 3 up-to-date
	```

3. To run the binary:
   `java -jar build/libs/Truecaller-1.0-SNAPSHOT.jar`

Result:

```
Starting with position: (0,0).

Time used to solve by WarnsdorffHeuristic : 11015761 nanoseconds
0	61	39	15	60	38	74	48	37	73
53	17	2	52	57	3	51	71	4	50
40	14	59	66	93	78	65	94	77	47
1	62	56	16	63	68	75	49	36	72
54	18	98	81	58	97	90	70	5	89
41	13	28	67	92	79	64	95	76	46
22	82	55	23	85	69	24	86	35	9
29	19	99	80	31	96	91	32	6	88
42	12	27	43	11	26	44	10	25	45
21	83	30	20	84	33	7	87	34	8
Total Moves in the Path: 100
Starting with position: (0,0).

Time used to solve by WarnsdorffHeuristicTieBreaker : 2184938 nanoseconds
0	61	39	15	60	38	74	48	37	73
53	17	2	52	57	3	51	71	4	50
40	14	59	66	93	78	65	94	77	47
1	62	56	16	63	68	75	49	36	72
54	18	98	81	58	97	90	70	5	89
41	13	28	67	92	79	64	95	76	46
22	82	55	23	85	69	24	86	35	9
29	19	99	80	31	96	91	32	6	88
42	12	27	43	11	26	44	10	25	45
21	83	30	20	84	33	7	87	34	8
Total Moves in the Path: 100

Time taken by WarnsdorffHeuristicTieBreaker is less than WarnsdorffHeuristic by  : 8830823 nanoseconds
This shows WarnsdorffHeuristicTieBreaker is Efficient by the Factor of 500 percentage

```


##Interested to use the docker?

1. Build the docker Image:
   `docker build -t tourdemo .`
2. Run the demo:
   `docker run -ti --rm -P tourdemo`