package puzzle;

import java.util.ArrayList;

import puzzle.infra.ErrorsManager;

/**
 * @author ilan Wallerstein
 *
 *         This class will analyze the pieces that came as input to check their
 *         validation for the solution before starting to find the solution
 */

public class AnalyzeInputs
{

	private ArrayList<String> errors = new ArrayList<>();
	private ArrayList<Integer> rows = new ArrayList<>();
	private ArrayList<Piece> input;

	public AnalyzeInputs(ArrayList<Piece> input)
	{
		this.input = input;

	}

	// getter for possible solution rows
	public ArrayList<Integer> getSolutionPossibleRows()
	{
		return rows;
	}

	// getter for errors
	public ArrayList<String> getErrorsList()
	{
		return errors;
	}

	/**
	 * This method check the validation of input pieces by: 1. correct edges sum
	 * 2. correct pieces format 3. minimum straight edges for each possible row
	 * matrix 4. minimum corners for each possible row matrix The method add
	 * errors in there any in errorList
	 */
	public void analyzePicesList()
	{

		validateEdgesSum();
		validatePiecesFormat();
		rows = validateMinimumStraightEdges();
		validateMinimumCorners();
	}

	/**
	 * @return rows: ArrayList This method run all over the possible puzzle
	 *         structure and looking for the minimum straight edges for each
	 *         structure. If found, it add this option to a list that will be
	 *         forward to the solver
	 */
	public ArrayList<Integer> validateMinimumStraightEdges()
	{

		int leftZeroEdges = 0, topZeroEdges = 0, rightZeroEdges = 0, bottomZeroEdges = 0;

		ArrayList<Integer> optionalRowsForSolution = new ArrayList<>();
		ArrayList<Integer> possibleSolutionRows = getPossibleSolutionRows(input.size());
		for (int numOfRows : possibleSolutionRows)
		{
			int numOfColumns = input.size() / numOfRows;
			leftZeroEdges = 0;
			topZeroEdges = 0;
			rightZeroEdges = 0;
			bottomZeroEdges = 0;
			for (Piece p : input)
			{
				if (p.getLeft() == 0)
					leftZeroEdges++;
				if (p.getTop() == 0)
					topZeroEdges++;
				if (p.getRight() == 0)
					rightZeroEdges++;
				if (p.getBottom() == 0)
					bottomZeroEdges++;
				if (leftZeroEdges >= numOfRows && topZeroEdges >= numOfColumns && rightZeroEdges >= numOfRows
						&& bottomZeroEdges >= numOfColumns)
				{
					optionalRowsForSolution.add(numOfRows);
					break;

				}
			}

		}
		if (optionalRowsForSolution.isEmpty())
			errors.add(ErrorsManager.ERROR_NUM_STRAIGHT_EDGES);
		return optionalRowsForSolution;
	}

	// get all possible matrixes for solution by input size
	private ArrayList<Integer> getPossibleSolutionRows(int size)
	{
		ArrayList<Integer> possibleSolutionRows = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++)
		{
			if (size % i == 0)
			{
				possibleSolutionRows.add(i);
			}
		}
		return possibleSolutionRows;
	}

	/**
	 * This method run all over the possible puzzle structure [after it was
	 * checked that it has minimum straight edges] and looking for the minimum
	 * corners for each structure. If found, it add this option to a list that
	 * will be forward to the solver
	 */
	public void validateMinimumCorners()
	{
		boolean leftTopCorner = false, topRightCorner = false, rightBottomCorner = false, bottomLeftCorner = false;

		leftTopCorner = false;
		topRightCorner = false;
		rightBottomCorner = false;
		bottomLeftCorner = false;
		for (Piece p : input)
		{
			if (p.getLeft() == 0 && p.getTop() == 0)
				leftTopCorner = true;
			if (p.getTop() == 0 && p.getRight() == 0)
				topRightCorner = true;
			if (p.getRight() == 0 && p.getBottom() == 0)
				rightBottomCorner = true;
			if (p.getBottom() == 0 && p.getLeft() == 0)
				bottomLeftCorner = true;
			if (leftTopCorner && topRightCorner && rightBottomCorner && bottomLeftCorner)
			{
				break;

			}
		}

		if (!leftTopCorner)
			errors.add(ErrorsManager.ERROR_MISSING_CORNER_ELEMENT + " TL");
		if (!topRightCorner)
			errors.add(ErrorsManager.ERROR_MISSING_CORNER_ELEMENT + " TR");
		if (!rightBottomCorner)
			errors.add(ErrorsManager.ERROR_MISSING_CORNER_ELEMENT + " BR");
		if (!bottomLeftCorner)
			errors.add(ErrorsManager.ERROR_MISSING_CORNER_ELEMENT + " BL");

	}

	/**
	 * This method check that input pieces edges are in the range of -1 to 1
	 */
	public void validatePiecesFormat()
	{
		for (Piece p : input)
		{
			if (!(p.getRight() >= -1 && p.getRight() <= 1 && p.getTop() >= -1 && p.getTop() <= 1 && p.getBottom() >= -1
					&& p.getBottom() <= 1 && p.getLeft() >= -1 && p.getLeft() <= 1))
			{
				errors.add(ErrorsManager.ERROR_WRONG_ELEMENTS_VALUES + p.getId());
			}
		}
	}

	/**
	 * This method check that input pieces edges sum is 0 --> the the shape is
	 * closed
	 */
	public void validateEdgesSum()
	{
		int temp = 0;
		for (Piece p : input)
			temp += p.getRight() + p.getTop() + p.getBottom() + p.getLeft();
		if (temp != 0)
		{
			errors.add(ErrorsManager.ERROR_EDGES_SUM_NOT_ZERO);
		}
	}

}
