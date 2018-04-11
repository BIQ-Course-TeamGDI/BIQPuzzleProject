package fileManagmentTests;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import infra.FileManager;
import puzzle.Piece;

public class JunitTests
{
	ArrayList<Piece> pieces;
	FileManager fileManagment;

	@Test
	@DisplayName("1")
	public void testFileManagment() throws IOException
	{
		fileManagment = new FileManager("./resources/files/test6.in");
		pieces = fileManagment.getPicesFromFile();
	}

}
