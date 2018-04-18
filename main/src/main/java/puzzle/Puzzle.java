package puzzle;

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

    public Piece[][] getSolution() {
        return solution;
    }

    public ArrayList<Integer> getPosibleSolutionRows() {
        return posibleSolutionRows;
    }

//    public static boolean checkSolutionFile(String input, String solutionFile) throws IOException {
//        Piece[][] solutionToCheck = validatePuzzleSolution.testPuzzleSolutions(input,solutionFile);
//        PuzzleSolver puzzleSolver = new PuzzleSolver();
//        if(puzzleSolver.checkSolution(solutionToCheck)){
//           System.out.println("Solution is good");
//           return true;
//       } else{
//           System.out.println("Solution is incorrect");
//           return false;
//       }
//    }

//    public static boolean validateSolution(String input, String solutionFile) throws IOException {
//        Piece[][] solutionToCheck = validatePuzzleSolution.testPuzzleSolutions(input,solutionFile);
//        PuzzleSolver puzzleSolver = new PuzzleSolver();
//        if(solutionToCheck !=null && puzzleSolver.checkSolution(solutionToCheck)){
//            return true;
//        } else{
//            return false;
//        }
//    }

    public String solutionToString(){
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

    public void saveSolutionToFile(String outPutFile) throws IOException {
        File fout = new File(outPutFile);
        try (FileOutputStream fos = new FileOutputStream(fout);
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)))
        {
            bw.write(this.solutionToString());
            bw.close();
        }
    }
}
