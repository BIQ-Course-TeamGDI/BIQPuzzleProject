package puzzle;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class JunitTests
{
	@Test
	public void testFileManagment() throws IOException, FileManagmentException
	{
		FileManagment fileManagment = new FileManagment("./resources/input.txt");
		ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
	}
}
