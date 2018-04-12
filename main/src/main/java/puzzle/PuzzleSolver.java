package puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 *  This is the PuzzleSolver class.
 *  This class try to solve a given puzzle.
 *
 *  Author: Doron Niv
 *  Date:   01/04/2018
 */
public class PuzzleSolver {

    /**
     * Class fields:
     * solution - Two dimensional array that represent the puzzle solution.
     * fitPieces - List of the puzzle pieces that are already inside the solution array.
     * isSolved - this field set to true in case there is a solution for the given puzzle.
     */
    private Piece[][] solution;
    private List<Piece> fitPieces = new ArrayList<Piece>();
    private boolean isSolved = false;


    public PuzzleSolver() {

    }

    /**
     * This method is used to calculate the number of possible solutions rows numbers
     * and call to to the findSolution recursive method with all possibilities.
     *
     * @param puzzle This is the puzzle we wish to solve
     */
    public Piece[][] solve(Puzzle puzzle){
        for(int numOfRows : puzzle.getPosibleSolutionRows()) {
            if (!isSolved) {
                int columns = puzzle.size() / numOfRows;
                solution = new Piece[numOfRows][columns];
                findSolution(puzzle);
            }
        }
        if (isSolved) {
            return solution;
        }
        return null;
    }


    /**
     * This method is recursive method for finding puzzle solution.
     *
     * @param puzzle This is the puzzle we wish to solve
     */
    private void findSolution(Puzzle puzzle){
        // Loop over all puzzle pieces
        for (Piece p : puzzle.getPuzzlePieces()){
            if(isSolved){ // if isSolve flag is true the puzzle is solve and no need to continue checking.
                return;
            }
            // the next empty row/column number in the solution array.
            int[] rowAndCol = getNextPlaceInThePuzzle();
            if(!fitPieces.contains(p) ){ // Check if current piece is already inside the solution array.
                if (isPieceFit(p, rowAndCol[0], rowAndCol[1])) { // Check if current piece fit to the current place in the puzzle solution array.
                    fitPieces.add(p);
                    solution[rowAndCol[0]][rowAndCol[1]] = p;
                    findSolution(puzzle);
                }
            }
        }
        if (puzzle.getPuzzlePieces().size()==fitPieces.size()){
            // All puzzle pieces are inside the solution array and fit each other.
            // In this case the puzzle is solved
            isSolved=true;
        } else {
            if(fitPieces.size()>0) {
                removeLastPieceFromPuzzle(fitPieces.get(fitPieces.size() - 1));
                fitPieces.remove(fitPieces.size() - 1);
            }
        }
    }

    /**
     * This method is recursive method for finding puzzle solution.
     *
     * @param piece This is the current piece we want to check if fit in the given place in the solution array.
     * @param row The row number (solution[row][col]).
     * @param col The column number (solution[row][col]).
     *
     * @return true if piece fit to the current puzzle status else return false.
     */
    private boolean isPieceFit(Piece piece, int row , int col ) {
        int numOfRows = solution.length - 1;
        int numOfColumns = solution[0].length - 1;
        if (row == 0) {  //Top row in the solution array.
            if(piece.getTop() != 0){ //in this case top value must be zero (if not return false).
                return false;
            }
        }
        //Check if current piece top value fit to above piece bottom value (sum is zero).
        else if(!(isSumZero(solution[row-1][col].getBottom() , piece.getTop()))){
            return false; // if sum not zero return false
        }

        if (row == numOfRows){ //Bottom row in the solution array.
            if(piece.getBottom() != 0 ){ //in this case bottom value must be zero (if not return false).
                return false;
            }
        }

        if (col == 0) { //Right (first) column in the solution array.
            if(piece.getLeft() != 0){ //in this case right value must be zero (if not return false).
                return false;
            }
        }
        //Check if current piece left value fit to left piece right value (sum is zero).
        else if (!(isSumZero(solution[row][col-1].getRight() , piece.getLeft()))){
            return false; // if sum not zero return false
        }
        if (col == numOfColumns){ //Left (last) row in the solution array.
            if( piece.getRight() != 0){ //in this case left value must be zero (if not return false).
                return false;
            }
        }
        // return true if the piece fit to the current puzzle status.
        return true;
    }

    /**
     * This method remove given piece from the puzzle solution array.
     *
     * @param piece This is the piece we want to remove from the puzzle (the last piece that was entered)
     */
    private void removeLastPieceFromPuzzle(Piece piece) {
        for (int i=0;i<solution.length;i++){
            for (int j=0;j<solution[0].length;j++){
                if(piece.equals(solution[i][j])){
                    solution[i][j]=null;
                    return;
                }
            }
        }
    }

    /**
     * This method return array in the size of 2 with the next empty place in the solution array (solution[row][col]).
     *
     * @return int[2] {row number, column number} - the next empty place in the solution array.
     */
    private int[] getNextPlaceInThePuzzle() {
        for (int i=0;i<solution.length;i++){
            for (int j=0;j<solution[0].length;j++){
                if(solution[i][j]==null){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     * This method get two pieces side value and check if the sum of the values is zero.
     *
     * @return true if the sum is zero else return false.
     */
    private boolean isSumZero(int side1, int side2) {
        if (side1+side2==0){
            return true;
        }
        return false;
    }

//    public ArrayList<Integer> getPossibleSolutionRows(int size) {
//        ArrayList<Integer> possibleSolutionRows = new ArrayList<Integer>();
//        for(int i = 1; i<=size;i++){
//            if(size%i==0){
//                possibleSolutionRows.add(i);
//            }
//        }
//        return possibleSolutionRows;
//    }

    public boolean checkSolution(Piece[][] sol){
        solution=sol;
        for (int i=0;i<solution.length;i++){
            for (int j=0;j<solution[0].length;j++){
                if(!isPieceFit(solution[i][j],i,j)){
                    return false;
                }
            }
        }
        return true;
    }

}
