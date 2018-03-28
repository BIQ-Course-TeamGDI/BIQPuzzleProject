package puzzle;
import java.util.ArrayList;
import org.apache.log4j.Logger;
/**
 * 
 * @author Guy Bitan
 *
 */
public class ErrorsManagment
{
	static final String ERROR_MISSING_IN_FILE = "Input file doesn't exist in: ";
	static final String ERROR_MISSING_ELEMENTS = "Missing puzzle element(s) with the following IDs: ";
	static final String ERROR_WRONG_ELEMENT_IDS = "Wrong element IDs: ";
	static final String ERROR_WRONG_ELEMENTS_FORMAT = "Wrong elements format: ";
	static final String ERROR_NUM_ELEMENTS = "Number of elements is invalid: ";

	final static Logger logger = Logger.getLogger(FileManagment.class);
	private ArrayList<String> errorsList = new ArrayList<String>();

	public void addError(String errorMsg)
	{
		errorsList.add(errorMsg);
	}

	public void printErrors()
	{
		for (String error : errorsList)
		{
			logger.error(error);
		}
	}
}
