package puzzle;

import puzzleSolver.PuzzleSolver;

import java.util.ArrayList;
import java.util.Arrays;

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
    private Piece[][] solution;

    /**
     * This constructor initialize new Puzzle with a list of all his pieces.
     */
    public Puzzle(ArrayList<Piece> puzzlePieces ){
        this.puzzlePieces=puzzlePieces;
    }


    /**
     * This method is call to solve method in PuzzleSolver class.
     */
    public void solve(){
        solution = PuzzleSolver.solve(this);
    }

    public int size(){
        return puzzlePieces.size();
    }

    public ArrayList<Piece> getPuzzlePieces() {
        return puzzlePieces;
    }

    public Piece[][] getSolution() {
        return solution;
    }

    public void printSolution(){
        if(solution!=null) {
            //System.out.println(Arrays.deepToString(solution));
            for (int i=0;i<solution.length;i++){
                for (int j=0;j<solution[0].length;j++){
                    System.out.print(solution[i][j]+" ");
                }
                System.out.print("\n");
            }
        } else{
            System.out.println("Cannot solve puzzle: it seems that there is no proper solution");
        }

    }
}
