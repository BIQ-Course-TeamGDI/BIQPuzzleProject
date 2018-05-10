package com.att.biq.puzzle;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is the puzzle class.
 *
 * Author: Doron Niv Date: 01/04/2018
 */
public class Puzzle{

    /**
	 * Class fields: puzzlePieces - List of puzzle pieces. solution - Two
	 * dimensional array that represent the puzzle solution.
	 */
    private PuzzleIndexer indexerPieces;
    private int numOfPieces;
    private boolean isRotate;
    private int numOfThreads;
    ArrayList<Integer> solutionPossibleRows;
	private Piece[][] solution;
	public boolean iSolved = false;
    public static AtomicBoolean solutionFound = new AtomicBoolean(false);
	/**
	 * This constructor initialize new Puzzle with a list of all his pieces.
	 */
    public Puzzle(PuzzleIndexer puzzleIndexer, int numOfPieces, boolean isRotate, int numOfThreads, ArrayList<Integer> solutionPossibleRows) {
        this.indexerPieces=puzzleIndexer;
        this.numOfPieces = numOfPieces;
        this.isRotate=isRotate;
        this.numOfThreads=numOfThreads;
        this.solutionPossibleRows = solutionPossibleRows;
    }

    public Piece[][] getSolution() {
        return solution;
    }

    public void solve(){
        if (numOfThreads <= 1) {
            solvePuzzleRegular();
        }
        else {
            solvePuzzleByThreads();
        }
        //saveSolution2File();
    }


    private void solvePuzzleRegular(){
        PuzzleSolver puzzleSolver;
 	    for (Integer row : solutionPossibleRows) {
            int columns = numOfPieces/row;
            puzzleSolver = new PuzzleSolver(indexerPieces,row,columns);
            solution = puzzleSolver.solve(solutionFound);
            if(solution!=null){
                break;
            }
        }
    }


    public void solvePuzzleByThreads(){
        //boolean waitForThread = true;
        int index = 0;
        ArrayList<PuzzleSolver> pSolver = new ArrayList<>();
        ArrayList<Integer> rows = solutionPossibleRows;
        rows.clear();
        rows.add(1);
        rows.add(2);
        rows.add(4);
        rows.add(5);
        rows.add(10);
        rows.add(25);
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        for(int row : rows) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    int columns = numOfPieces/row;
                    PuzzleSolver puzzleSolver = new PuzzleSolver(indexerPieces,row,columns);
                    System.out.println("Try to solve puzzle with " + row+ "x"+columns +  " board size ");
                    Piece[][] currThreadSolution = puzzleSolver.solve(solutionFound);
                    pSolver.add(puzzleSolver);
                }
            });
        }
        executor.shutdown();
        while(!iSolved){
            for(PuzzleSolver ps : pSolver){
                if(ps.isSolved()){
                    index = pSolver.indexOf(ps);
                    iSolved=true;
                    for (PuzzleSolver psss : pSolver) {
                        psss.stop();
                    }
                    break;
                }
            }
            try {
                executor.awaitTermination(1, TimeUnit.MINUTES); /// should be in while ??
                System.out.println("after awaitTermination...");
            } catch (InterruptedException e) {
                // TODO : to handle thread interrupted exception
            }
        }
        solution = pSolver.get(index).getSolution();
    }

//    public int size(){
//        return puzzlePieces.size();
//    }


    public String solution2String(){
        String sol="";
        if(solution!=null) {
            for (int i=0;i<solution.length;i++){
                for (int j=0;j<solution[0].length;j++){
                    //System.out.print(solution[i][j]+" ");
                    if(solution[i][j].getRotation()==0) {
                        sol += solution[i][j] + " ";
                    } else {
                        sol += solution[i][j]+"[" +(solution[i][j].getRotation()*90)+"] ";
                    }
                }
                sol = sol.trim();
                sol+="\n";
                System.out.print("\n");
            }
        } else{
            sol = "Cannot solve puzzle: it seems that there is no proper solution\n";
            System.out.println("Cannot solve puzzle: it seems that there is no proper solution");
        }
        return sol;
    }

	public synchronized void saveSolution2File(String outPutFile) throws IOException {
		File fout = new File(outPutFile);
		try (FileOutputStream fos = new FileOutputStream(fout);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos))) {
			bw.write(this.solution2String());
			bw.close();
		}
	}

//	@Override
//	public void run() {
//		// each thread trying to solve a puzzle
//		iSolved = solve();
//
//	}
}
