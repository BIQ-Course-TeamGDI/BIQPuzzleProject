package puzzle;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.log4j.Logger;

import puzzleSolver.PuzzleSolver;

/**
 * @author iw4360
 *
 *         This class will analyze the pieces that came as input to check their
 *         validation for the solution before starting to find the solution
 */

public class AnalyzeInputs {

	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	private static ArrayList<String> errors = new ArrayList<>();

	public ArrayList<Integer> analyzePicesList(ArrayList<Piece> input) {

		if (validateEdgesSum(input).isEmpty()) {
			if (validatePiecesIds(input).isEmpty()) {
				if (validatePiecesFormat(input).isEmpty()) {
					ArrayList<Integer> rows = validateMinimumStraightEdges(input);
					if (!rows.isEmpty())
						return validateMinimumCorners(input, rows);
				}
			}
		}
		return null;
	}

	/**
	 * @param input:
	 *            ArrayList
	 * @return rows: ArrayList
	 * @see This method run all over the possible puzzle structure and looking for
	 *      the minimum straight edges for each structure. If found, it add this
	 *      option to a list that will be forward to the solver
	 */
	public static ArrayList<Integer> validateMinimumStraightEdges(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		int leftZeroEdges = 0, topZeroEdges = 0, rightZeroEdges = 0, bottomZeroEdges = 0;
		ArrayList<Integer> optionalRowsForSolution = new ArrayList<>();
		ArrayList<Integer> possibleSolutionRows = PuzzleSolver.getPossibleSolutionRows(input.size());
		for (int numOfRows : possibleSolutionRows) {
			int numOfColumns = input.size() / numOfRows;
			for (Piece p : input) {
				if (p.getLeft() == 0)
					leftZeroEdges++;
				if (p.getTop() == 0)
					topZeroEdges++;
				if (p.getRight() == 0)
					rightZeroEdges++;
				if (p.getBottom() == 0)
					bottomZeroEdges++;
				if (leftZeroEdges >= numOfRows && topZeroEdges >= numOfColumns && rightZeroEdges >= numOfRows
						&& bottomZeroEdges >= numOfColumns) {
					optionalRowsForSolution.add(numOfRows);
					break;

				}
			}

		}
		return optionalRowsForSolution;
	}

	/**
	 * @param input:ArrayList
	 * @param rows:
	 *            ArrayList
	 * @return optionalRows: ArrayList
	 * @see: This method run all over the possible puzzle structure [after it was
	 *       checked that it has minimum straight edges] and looking for the minimum
	 *       corners for each structure. If found, it add this option to a list that
	 *       will be forward to the solver
	 */
	public static ArrayList<Integer> validateMinimumCorners(ArrayList<Piece> input, ArrayList<Integer> rows) {
		// TODO Auto-generated method stub
		boolean leftTopCorner = false, topRightCorner = false, rightBottomCorner = false, bottomLeftCorner = false;
		ArrayList<Integer> optionalRowsForSolution = new ArrayList<>();

		for (int numOfRows : rows) {
			for (Piece p : input) {
				if (p.getLeft() == 0 && p.getTop() == 0)
					leftTopCorner = true;
				if (p.getTop() == 0 && p.getRight() == 0)
					topRightCorner = true;
				if (p.getRight() == 0 && p.getBottom() == 0)
					rightBottomCorner = true;
				if (p.getBottom() == 0 && p.getLeft() == 0)
					bottomLeftCorner = true;
				if (leftTopCorner && topRightCorner && rightBottomCorner && bottomLeftCorner) {
					optionalRowsForSolution.add(numOfRows);
					break;

				}
			}

		}
		if(!leftTopCorner)
			errors.add(ErrorsManagment.ERROR_MISSING_CORNER_ELEMENT + ": TL" );
		if(!topRightCorner)
			errors.add(ErrorsManagment.ERROR_MISSING_CORNER_ELEMENT + ": TR" );
		if(!rightBottomCorner)
			errors.add(ErrorsManagment.ERROR_MISSING_CORNER_ELEMENT + ": BR" );
		if(!bottomLeftCorner)
			errors.add(ErrorsManagment.ERROR_MISSING_CORNER_ELEMENT + ": BL" );

		return optionalRowsForSolution;

	}

	/**
	 * @param input:
	 *            ArrayList
	 * @return errors: Arraylist
	 * @see: This method check that input pieces edges arein the range of -1 to 1
	 */
	private ArrayList<String> validatePiecesFormat(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		for (Piece p : input) {
			if (p.getRight() >= -1 && p.getRight() <= 1 && p.getTop() >= -1 && p.getTop() <= 1 && p.getBottom() >= -1
					&& p.getBottom() <= 1 && p.getLeft() >= -1 && p.getLeft() <= 1) {
				System.out.println("Id: " + p.getId() + ". is not valid!");
				errors.add(ErrorsManagment.ERROR_WRONG_ELEMENT_IDS + " " + p.getId());
			}
		}
		return errors;
	}

	/**
	 * @param input:
	 *            ArrayList
	 * @return
	 * @see: This method check that input pieces edges sum is 0 --> the the shape is
	 *       closed
	 */
	public static ArrayList<String> validateEdgesSum(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		for (Piece p : input) {
			if (p.getRight() + p.getTop() + p.getBottom() + p.getLeft() != 0) {
				System.out.println("Id: " + p.getId() + ". is not valid!");
				// Logger.error(ErrorsManagment.ERROR_MISSING_ELEMENTS + " ");
				errors.add(ErrorsManagment.ERROR_NUM_STRAIGHT_EDGES);

			}
		}
		return errors;
	}

	/**
	 * @param input:
	 *            ArrayList
	 * @return
	 * @see: This method check that input pieces ids is correct
	 */
	public static ArrayList<String> validatePiecesIds(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		// Arrays.sort(input.toArray());
		Collections.sort(input);
		if (input.size() != input.indexOf(input.size() - 1)) {
			System.out.println("Missing Ids of pieces...");
			errors.add(ErrorsManagment.ERROR_WRONG_ELEMENT_IDS);
			// Need to find out what pieces are missing...
		}
		checkPiecesSequences(input);

		return errors;
	}

	private static void checkPiecesSequences(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		for (int i = 0; i < input.size() ; i++) {
			if (input.get(i).getId() != i) {
				System.out.println("Wrong Ids of pieces...");
				errors.add(ErrorsManagment.ERROR_WRONG_ELEMENT_IDS +":  "+ input.get(i).getId());
			}
		}

	}

}
