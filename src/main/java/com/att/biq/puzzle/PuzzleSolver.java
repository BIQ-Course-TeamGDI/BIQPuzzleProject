package com.att.biq.puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the PuzzleSolver class. This class try to solve a given puzzle.
 *
 * Author: Doron Niv Date: 01/04/2018
 */
public class PuzzleSolver {

	/**
	 * Class fields: solution - Two dimensional array that represent the puzzle
	 * solution. fitPieces - List of the puzzle pieces that are already inside the
	 * solution array. isSolved - this field set to true in case there is a solution
	 * for the given puzzle.
	 */
	private Piece[][] solution;
	private List<Piece> fitPieces = new ArrayList<Piece>();
	private boolean isSolved = false;
	private int[] nextEmptyPlace = { 0, 0 };
	private int[] prevPiecePlace = new int[2];

	public PuzzleSolver() {

	}

	/**
	 * This method is used to calculate the number of possible solutions rows
	 * numbers and call to to the findSolution recursive method with all
	 * possibilities.
	 *
	 * @param puzzle
	 *            This is the puzzle we wish to solve
	 */
	public Piece[][] solve(Puzzle puzzle) {
		if (!isSolved) {
			int columns = puzzle.size() / puzzle.getSolutionByRow();
			solution = new Piece[puzzle.getSolutionByRow()][columns];
			findSolution(puzzle);
		}
		if (isSolved) {
			return solution;
		}

		return null;
	}

	/**
	 * This method is recursive method for finding puzzle solution.
	 *
	 * @param puzzle
	 *            This is the puzzle we wish to solve
	 */
	private void findSolution(Puzzle puzzle) {
		// Loop over all puzzle pieces
		for (Piece p : puzzle.getPuzzlePieces()) {

			if (isSolved) { // if isSolve flag is true the puzzle is solve and no need to continue checking.
				// System.out.println("Thread: "+Thread.currentThread().getName());
				return;
			}
			if (!fitPieces.contains(p)) { // Check if current piece is already inside the solution array.
				if (isPieceFit(p, nextEmptyPlace[0], nextEmptyPlace[1])) { // Check if current piece fit to the current
																			// place in the puzzle solution array.
					fitPieces.add(p);
					solution[nextEmptyPlace[0]][nextEmptyPlace[1]] = p;
					setNextEmptyPlace(); // the next empty place (row/column number) in the solution array.
					findSolution(puzzle);
				}
			}
		}
		if (puzzle.getPuzzlePieces().size() == fitPieces.size()) {
			// All puzzle pieces are inside the solution array and fit each other.
			// In this case the puzzle is solved
			isSolved = true;
		} else {
			if (fitPieces.size() > 0) {
				removeLastPieceFromPuzzle();
				fitPieces.remove(fitPieces.size() - 1);
			}
		}
	}

	/**
	 * This method is recursive method for finding puzzle solution.
	 *
	 * @param piece
	 *            This is the current piece we want to check if fit in the given
	 *            place in the solution array.
	 * @param row
	 *            The row number (solution[row][col]).
	 * @param col
	 *            The column number (solution[row][col]).
	 *
	 * @return true if piece fit to the current puzzle status else return false.
	 */
	private boolean isPieceFit(Piece piece, int row, int col) {
		int numOfRows = solution.length - 1;
		int numOfColumns = solution[0].length - 1;
		if (row == 0) { // Top row in the solution array.
			if (piece.getTop() != 0) { // in this case top value must be zero (if not return false).
				return false;
			}
		}
		// Check if current piece top value fit to above piece bottom value (sum is
		// zero).
		else if (!(isPieceFitTopBottom(piece, solution[row - 1][col]))) {
			return false; // if sum not zero return false
		}

		if (row == numOfRows) { // Bottom row in the solution array.
			if (piece.getBottom() != 0) { // in this case bottom value must be zero (if not return false).
				return false;
			}
		}

		if (col == 0) { // Right (first) column in the solution array.
			if (piece.getLeft() != 0) { // in this case right value must be zero (if not return false).
				return false;
			}
		}
		// Check if current piece left value fit to left piece right value (sum is
		// zero).
		else if (!(isPieceFitRightLeft(piece, solution[row][col - 1]))) {
			return false; // if sum not zero return false
		}
		if (col == numOfColumns) { // Left (last) row in the solution array.
			if (piece.getRight() != 0) { // in this case left value must be zero (if not return false).
				return false;
			}
		}
		// return true if the piece fit to the current puzzle status.
		return true;
	}

	private void setNextEmptyPlace() {
		prevPiecePlace[0] = nextEmptyPlace[0];
		prevPiecePlace[1] = nextEmptyPlace[1];
		if (nextEmptyPlace[1] < solution[0].length - 1) {
			nextEmptyPlace[1]++;
		} else if (nextEmptyPlace[0] < solution.length - 1) {
			nextEmptyPlace[0]++;
			nextEmptyPlace[1] = 0;
		}
	}

	/**
	 * This method remove the last piece piece that set in the puzzle solution
	 * array.
	 */
	private void removeLastPieceFromPuzzle() {
		nextEmptyPlace[0] = prevPiecePlace[0];
		nextEmptyPlace[1] = prevPiecePlace[1];
		solution[prevPiecePlace[0]][prevPiecePlace[1]] = null;
		if (prevPiecePlace[1] > 0) {
			prevPiecePlace[1]--;
		} else if (prevPiecePlace[0] > 0) {
			prevPiecePlace[0]--;
			prevPiecePlace[1] = solution[0].length - 1;
		}
	}

	/**
	 * This method get two pieces and check if the sum of current piece top value
	 * and the top piece bottom values is zero.
	 *
	 * @param currPiece
	 *            - the current piece.
	 * @param topPiece
	 *            - the piece that is top of the current piece.
	 *
	 * @return true if the sum is zero else return false.
	 */
	private boolean isPieceFitTopBottom(Piece currPiece, Piece topPiece) {
		if (currPiece.getTop() + topPiece.getBottom() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method get two pieces and check if the sum of current piece left value
	 * and the left piece right values is zero.
	 *
	 * @param currPiece
	 *            - the current piece.
	 * @param leftPiece
	 *            - the piece that in the left side of the current piece.
	 *
	 * @return true if the sum is zero else return false.
	 */
	private boolean isPieceFitRightLeft(Piece currPiece, Piece leftPiece) {
		if (currPiece.getLeft() + leftPiece.getRight() == 0) {
			return true;
		}
		return false;
	}

	public boolean checkSolution(Piece[][] sol) {
		solution = sol;
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution[0].length; j++) {
				if (!isPieceFit(solution[i][j], i, j)) {
					return false;
				}
			}
		}
		return true;
	}

}
