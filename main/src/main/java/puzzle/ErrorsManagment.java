package puzzle;
/**
 * 
 * @author Guy Bitan
 *
 */
public interface ErrorsManagment
{
	final String ERROR_MISSING_IN_FILE = "Input file doesn't exist in: ";
    final String ERROR_NUM_ELEMENTS_NOT_EQUAL_TO_ACTUAL_PIECES = "Number of elements is not equal to actual pieces NumElements=";
	final String ERROR_MISSING_ELEMENTS = "Missing puzzle element(s) with the following IDs: ";
	final String ERROR_WRONG_ELEMENT_IDS = "Wrong element IDs: ";
	final String ERROR_WRONG_ELEMENTS_FORMAT = "Wrong elements format: ";
	final String ERROR_NUM_ELEMENTS = "Number of elements is invalid: ";
}
