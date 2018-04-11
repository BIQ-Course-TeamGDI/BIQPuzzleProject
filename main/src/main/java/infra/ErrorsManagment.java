package infra;

import java.util.ArrayList;

/**
 * 
 * @author Guy Bitan
 *
 */
public class ErrorsManagment
{
	final String ERROR_MISSING_IN_FILE = "Input file doesn't exist in: ";
	final String ERROR_NUM_ELEMENTS_NOT_EQUAL_TO_ACTUAL_PIECES = "Number of elements is not equal to actual pieces NumElements=";
	final String ERROR_MISSING_ELEMENTS = "Missing puzzle element(s) with the following IDs: ";
	final String ERROR_WRONG_ELEMENT_IDS = "Wrong element IDs: ";
	final String ERROR_WRONG_ELEMENTS_FORMAT = "Wrong elements format: ";
	final String ERROR_NUM_ELEMENTS = "Number of elements is invalid: ";
	final String ERROR_NUM_STRAIGHT_EDGES = "Cannot solve puzzle: wrong number of straight edges";
	final String ERROR_MISSING_CORNER_ELEMENT = "Cannot solve puzzle: missing corner element:";
	final String ERROR_WRONG_ELEMENTS_VALUES = "Wrong elements value on line: ";
	final String ERROR_EDGES_SUM_NOT_ZERO = "Cannot solve puzzle: edges sum is not zero";

	private ArrayList<String> errorsList = new ArrayList<String>();

	public void add(String errorMsg)
	{
		errorsList.add(errorMsg);
	}

	public void clear()
	{
		errorsList.clear();
	}

	public void printErrors()
	{
		for (String error : errorsList)
		{
			System.out.println(error);
		}
	}
}
