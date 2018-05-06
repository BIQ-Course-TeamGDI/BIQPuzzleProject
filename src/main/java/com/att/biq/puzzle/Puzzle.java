package com.att.biq.puzzle;

import java.io.*;
import java.util.ArrayList;

/**
 * This is the puzzle class.
 *
 * Author: Doron Niv Date: 01/04/2018
 */
public class Puzzle implements Runnable {

    /**
	 * Class fields: puzzlePieces - List of puzzle pieces. solution - Two
	 * dimensional array that represent the puzzle solution.
	 */
    private PuzzleIndexer indexerPieces;
	private Piece[][] solution;
    private int numOfRows;
    private int numOfColumns;
	public boolean iSolved = false;
	
	/**
	 * This constructor initialize new Puzzle with a list of all his pieces.
	 */
 	public Puzzle(PuzzleIndexer indexerPieces , int numOfRows ,int numOfColumns ){
	    this.indexerPieces=indexerPieces;
	    this.numOfRows=numOfRows;
	    this.numOfColumns=numOfColumns;
    }

    public Piece[][] getSolution() {
        return solution;
    }


    public boolean solve(){
        PuzzleSolver puzzleSolver = new PuzzleSolver(indexerPieces,numOfRows,numOfColumns);
        System.out.println("try to solve puzzle in "+ numOfRows+"x"+numOfColumns+"solution");
        solution = puzzleSolver.solve();
        return solution != null;
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

	@Override
	public void run() {
		// each thread trying to solve a puzzle
		iSolved = solve();

	}
}
