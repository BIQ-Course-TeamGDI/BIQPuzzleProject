package puzzleSolver;

import puzzle.Piece;
import puzzle.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleSolver {


    private static Piece[][] solution;
    private static List<Piece> fitPieces = new ArrayList<Piece>();
    private static boolean isSolved = false;

    public  static void solve(Puzzle puzzle){
        ArrayList<Integer> possibleSolutionRows = getPossibleSolutionRows(puzzle.size());
        for(int numOfRows : possibleSolutionRows) {
            if (!isSolved) {
                int columns = puzzle.size() / numOfRows;
                solution = new Piece[numOfRows][columns];
                findSolution(puzzle);
            }
        }
        if (isSolved){
            System.out.println(Arrays.deepToString(solution));
        } else {
            System.out.println("There is no solution  for this puzzle");
        }


    }

    private static void findSolution(Puzzle puzzle){
        int[] rowAndCol = getNextPlaceInThePuzzle();
        for (Piece p : puzzle.getPuzzlePieces()){
            if(isSolved){
                return;
            }
            if(!fitPieces.contains(p) ){
                if (isPieceFit(p, solution, rowAndCol[0], rowAndCol[1])) {
                    fitPieces.add(p);
                    solution[rowAndCol[0]][rowAndCol[1]] = p;
                    findSolution(puzzle);
                }
            }
        }
        if (puzzle.getPuzzlePieces().size()==fitPieces.size()){
            isSolved=true;
        } else {
            if(fitPieces.size()>0) {
                removeLastPieceFromPuzzle(fitPieces.get(fitPieces.size() - 1));
                fitPieces.remove(fitPieces.size() - 1);
            }
        }
    }


    public static boolean isPieceFit(Piece piece,  Piece[][] solution , int row , int col ) {
        int numOfRows = solution.length - 1;
        int numOfColumns = solution[0].length - 1;
        if (row == 0) {
            if(piece.getTop() != 0){
                return false;
            }
        } else if(!(isSumZero(solution[row-1][col].getBottom() , piece.getTop()))){
            return false;
        }

        if (row == numOfRows){
            if(piece.getBottom() != 0 ){
                return false;
            }
        }

        if (col == 0) {
            if(piece.getLeft() != 0){
                return false;
            }
        }
        else if (!(isSumZero(solution[row][col-1].getRight() , piece.getLeft()))){
            return false;
        }
        if (col == numOfColumns){
            if( piece.getRight() != 0){
                return false;
            }
        }
        return true;
    }

    private static void removeLastPieceFromPuzzle(Piece piece) {
        for (int i=0;i<solution.length;i++){
            for (int j=0;j<solution.length;j++){
                if(piece.equals(solution[i][j])){
                    solution[i][j]=null;
                    return;
                }
            }
        }
    }

    private static int[] getNextPlaceInThePuzzle() {
        for (int i=0;i<solution.length;i++){
            for (int j=0;j<solution.length;j++){
                if(solution[i][j]==null){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    private static boolean isSumZero(int side1, int side2) {
        if (side1+side2==0){
            return true;
        }
        return false;
    }

    public static ArrayList<Integer> getPossibleSolutionRows(int size) {
        ArrayList<Integer> possibleSolutionRows = new ArrayList<Integer>();
        for(int i = 1; i<=size;i++){
            if(size%i==0){
                possibleSolutionRows.add(i);
            }
        }
        return possibleSolutionRows;
    }

}
