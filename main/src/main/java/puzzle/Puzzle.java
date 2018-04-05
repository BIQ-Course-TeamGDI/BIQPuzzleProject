package puzzle;

import puzzleSolver.PuzzleSolver;

import java.util.ArrayList;
import java.util.Arrays;

public class Puzzle {


    private ArrayList<Piece> puzzlePieces;
    private Piece[][] solution;


    public Puzzle(ArrayList<Piece> puzzlePieces ){
        this.puzzlePieces=puzzlePieces;
    }


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
            System.out.println(Arrays.deepToString(solution));
        } else{
            System.out.println("There is no solution for this puzzle");
        }

    }
}
