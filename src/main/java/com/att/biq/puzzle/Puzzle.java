package com.att.biq.puzzle;

import java.io.*;
import java.util.ArrayList;

/**
 * This is the puzzle class.
 *
 *  Author: Doron Niv
 *  Date:   01/04/2018
 */
public class Puzzle {


    /**
     * Class fields:
     * puzzlePieces - List of puzzle pieces.
     * solution - Two dimensional array that represent the puzzle solution.
     */
    private ArrayList<Piece> puzzlePieces;
    private ArrayList<Integer> posibleSolutionRows;
    private Piece[][] solution;

    /**
     * This constructor initialize new Puzzle with a list of all his pieces.
     */
    public Puzzle(ArrayList<Piece> puzzlePieces,ArrayList<Integer> posibleSolutionRows ){
        this.puzzlePieces=puzzlePieces;
        this.posibleSolutionRows=posibleSolutionRows;
    }


    /**
     * This method is call to solve method in PuzzleSolver class.
     */
    public void solve(){
        PuzzleSolver puzzleSolver = new PuzzleSolver();
        solution = puzzleSolver.solve(this);
    }

    public int size(){
        return puzzlePieces.size();
    }

    public ArrayList<Piece> getPuzzlePieces() {
        return puzzlePieces;
    }

    public ArrayList<Integer> getPosibleSolutionRows() {
        return posibleSolutionRows;
    }

    public String solution2String(){
        String sol="";
        if(solution!=null) {
            for (int i=0;i<solution.length;i++){
                for (int j=0;j<solution[0].length;j++){
                    System.out.print(solution[i][j]+" ");
                    sol+=solution[i][j]+" ";
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

    public void saveSolution2File(String outPutFile) throws IOException {
        File fout = new File(outPutFile);
        try (FileOutputStream fos = new FileOutputStream(fout);
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)))
        {
            bw.write(this.solution2String());
            bw.close();
        }
    }
}
