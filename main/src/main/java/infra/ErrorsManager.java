package infra;

import java.io.*;
import java.util.ArrayList;

/**
 * 
 * @author Guy Bitan
 *
 */
public abstract class ErrorsManagment
{
	public final static String ERROR_MISSING_IN_FILE = "Input file doesn't exist in: ";
	public final static String ERROR_NUM_ELEMENTS_NOT_EQUAL_TO_ACTUAL_PIECES = "Number of elements is not equal to actual pieces NumElements=";
	public final static String ERROR_MISSING_ELEMENTS = "Missing puzzle element(s) with the following IDs: ";
	public final static String ERROR_WRONG_ELEMENT_IDS = "Wrong element IDs: ";
	public final static String ERROR_WRONG_ELEMENTS_FORMAT = "Wrong elements format: ";
	public final static String ERROR_NUM_ELEMENTS = "Number of elements is invalid: ";
	public final static String ERROR_NUM_STRAIGHT_EDGES = "Cannot solve puzzle: wrong number of straight edges";
	public final static String ERROR_MISSING_CORNER_ELEMENT = "Cannot solve puzzle: missing corner element:";
	public final static String ERROR_WRONG_ELEMENTS_VALUES = "Wrong elements value on line: ";
	public final static String ERROR_EDGES_SUM_NOT_ZERO = "Cannot solve puzzle: edges sum is not zero";

	private ArrayList<String> errorsList = new ArrayList<String>();

	public void addError(String errorMsg)
	{
		errorsList.add(errorMsg);
	}

	public void clear()
	{
		errorsList.clear();
	}

	public ArrayList<String> getAllErrors()
	{
		return errorsList;
	}

	public void printErrorsToFile(String outPutFile) throws IOException {
		File fout = new File(outPutFile);
		try (FileOutputStream fos = new FileOutputStream(fout) ;BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)) ){
			for (String error : errorsList){
				bw.write(error);
			}
		}
	}
}
