package puzzle;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JunitTests
{
	ArrayList<Piece> pieces;
	FileManagment fileManagment;
	
	@Test
	@DisplayName("1")
	public void testFileManagment() throws IOException, FileManagmentException
	{
		fileManagment = new FileManagment("./resources/input.txt");
		pieces = fileManagment.getPicesFromFile();
	}
	
}
