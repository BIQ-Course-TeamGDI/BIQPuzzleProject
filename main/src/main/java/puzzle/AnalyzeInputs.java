/*package puzzle;

import java.util.ArrayList;
import infra.ErrorsManagment;
import puzzle.PuzzleSolver;

*//**
 * @author iw4360
 *
 *         This class will analyze the pieces that came as input to check their
 *         validation for the solution before starting to find the solution
 *//*

public class AnalyzeInputs {

	private static ArrayList<String> errors = new ArrayList<>();

	public static ArrayList<Integer> analyzePicesList(ArrayList<Piece> input, String outPutFile) {

		validateEdgesSum(input);
		validatePiecesFormat(input);
		ArrayList<Integer> rows = validateMinimumStraightEdges(input);
		validateMinimumCorners(input);

		if (errors.isEmpty())
			return rows;
		else {

			File fout = new File(outPutFile);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(fout);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

				for (String s : errors)
					bw.write("ERROR: " + s + "\n");
				bw.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	*//**
	 * @param input:
	 *            ArrayList
	 * @return rows: ArrayList
	 * @see This method run all over the possible puzzle structure and looking for
	 *      the minimum straight edges for each structure. If found, it add this
	 *      option to a list that will be forward to the solver
	 *//*
	public static ArrayList<Integer> validateMinimumStraightEdges(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		int leftZeroEdges = 0, topZeroEdges = 0, rightZeroEdges = 0, bottomZeroEdges = 0;

		ArrayList<Integer> optionalRowsForSolution = new ArrayList<>();
		ArrayList<Integer> possibleSolutionRows = getPossibleSolutionRows(input.size());
		for (int numOfRows : possibleSolutionRows) {
			int numOfColumns = input.size() / numOfRows;
			leftZeroEdges = 0;
			topZeroEdges = 0;
			rightZeroEdges = 0;
			bottomZeroEdges = 0;
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
		if (optionalRowsForSolution.isEmpty())
			errors.add(ErrorsManagment.ERROR_NUM_STRAIGHT_EDGES);
		return optionalRowsForSolution;
	}

	//get all possible matrixes for solution by input size   
	  private ArrayList<Integer> getPossibleSolutionRows(int size) {
	        ArrayList<Integer> possibleSolutionRows = new ArrayList<Integer>();
	        for(int i = 1; i<=size;i++){
	            if(size%i==0){
	                possibleSolutionRows.add(i);
	            }
	        }
	        return possibleSolutionRows;
	    }
	*//**
	 * @param input:ArrayList
	 * @param rows:
	 *            ArrayList
	 * @return optionalRows: ArrayList
	 * @see: This method run all over the possible puzzle structure [after it was
	 *       checked that it has minimum straight edges] and looking for the minimum
	 *       corners for each structure. If found, it add this option to a list that
	 *       will be forward to the solver
	 *//*
	public static ArrayList<String> validateMinimumCorners(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		boolean leftTopCorner = false, topRightCorner = false, rightBottomCorner = false, bottomLeftCorner = false;

		leftTopCorner = false;
		topRightCorner = false;
		rightBottomCorner = false;
		bottomLeftCorner = false;
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
				break;

			}
		}

		if (!leftTopCorner)
			errors.add(ErrorsManagment.ERROR_MISSING_CORNER_ELEMENT + " TL");
		if (!topRightCorner)
			errors.add(ErrorsManagment.ERROR_MISSING_CORNER_ELEMENT + " TR");
		if (!rightBottomCorner)
			errors.add(ErrorsManagment.ERROR_MISSING_CORNER_ELEMENT + " BR");
		if (!bottomLeftCorner)
			errors.add(ErrorsManagment.ERROR_MISSING_CORNER_ELEMENT + " BL");
		return errors;

	}

	*//**
	 * @param input:
	 *            ArrayList
	 * @return errors: Arraylist
	 * @see: This method check that input pieces edges arein the range of -1 to 1
	 *//*
	public static ArrayList<String> validatePiecesFormat(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		for (Piece p : input) {
			if (!(p.getRight() >= -1 && p.getRight() <= 1 && p.getTop() >= -1 && p.getTop() <= 1 && p.getBottom() >= -1
					&& p.getBottom() <= 1 && p.getLeft() >= -1 && p.getLeft() <= 1)) {
				errors.add(ErrorsManagment.ERROR_WRONG_ELEMENTS_VALUES + p.getId());
			}
		}
		return errors;
	}

	*//**
	 * @param input:
	 *            ArrayList
	 * @return
	 * @see: This method check that input pieces edges sum is 0 --> the the shape is
	 *       closed
	 *//*
	public static ArrayList<String> validateEdgesSum(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		int temp = 0;
		for (Piece p : input)
			temp += p.getRight() + p.getTop() + p.getBottom() + p.getLeft();
		if (temp != 0) {
			errors.add(ErrorsManagment.ERROR_EDGES_SUM_NOT_ZERO);
		}

		return errors;
	}

}
*/