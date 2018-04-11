package infra;

import java.util.ArrayList;

/**
 * 
 * @author Guy Bitan
 *
 */
public abstract class ErrorsManagment
{
	final static String ERROR_MISSING_IN_FILE = "Input file doesn't exist in: ";
	final static String ERROR_NUM_ELEMENTS_NOT_EQUAL_TO_ACTUAL_PIECES = "Number of elements is not equal to actual pieces NumElements=";
	final static String ERROR_MISSING_ELEMENTS = "Missing puzzle element(s) with the following IDs: ";
	final static String ERROR_WRONG_ELEMENT_IDS = "Wrong element IDs: ";
	final static String ERROR_WRONG_ELEMENTS_FORMAT = "Wrong elements format: ";
	final static String ERROR_NUM_ELEMENTS = "Number of elements is invalid: ";
	final static String ERROR_NUM_STRAIGHT_EDGES = "Cannot solve puzzle: wrong number of straight edges";
	final static String ERROR_MISSING_CORNER_ELEMENT = "Cannot solve puzzle: missing corner element:";
	final static String ERROR_WRONG_ELEMENTS_VALUES = "Wrong elements value on line: ";
	final static String ERROR_EDGES_SUM_NOT_ZERO = "Cannot solve puzzle: edges sum is not zero";

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
