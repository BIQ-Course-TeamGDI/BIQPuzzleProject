package puzzle;

import utility.TestPuzzleSolution;

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

    public ArrayList<Integer> getPosibleSolutionRows() {
        return posibleSolutionRows;
    }

    public static void testPuzzleSolutions(String input, String solutionFile) throws IOException {
        Piece[][] solutionToCheck = TestPuzzleSolution.testPuzzleSolutions(input,solutionFile);
       if(PuzzleSolver.checkSolution(solutionToCheck)){
           System.out.println("Solution is good");
       } else{
           System.out.println("Solution is incorrect");
       }
    }

    public String solutionToString(){
        String sol="";
        if(solution!=null) {
            for (int i=0;i<solution.length;i++){
                for (int j=0;j<solution[0].length;j++){
                    System.out.print(solution[i][j]+" ");
                    sol+=solution[i][j]+" ";
                }
                sol+="\n";
                System.out.print("\n");
            }
        } else{
            sol = "Cannot solve puzzle: it seems that there is no proper solution";
            System.out.println("Cannot solve puzzle: it seems that there is no proper solution");
        }
        return sol;
    }

//    public void printSolution(){
//        String sol="";
//        if(solution!=null) {
//            for (int i=0;i<solution.length;i++){
//                for (int j=0;j<solution[0].length;j++){
//                    System.out.print(solution[i][j]+" ");
//                    sol+=solution[i][j]+" ";
//                }
//                sol+="\n";
//                System.out.print("\n");
//            }
//        } else{
//            sol = "Cannot solve puzzle: it seems that there is no proper solution";
//            System.out.println("Cannot solve puzzle: it seems that there is no proper solution");
//        }
//
//        File fout = new File("C:\\Development_Course\\Passover_Project\\AmirTests\\AdditionalPuzzleTests\\test15.out");
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(fout);
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//            bw.write(sol);
//            bw.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
