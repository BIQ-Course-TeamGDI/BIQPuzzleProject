package fileManagmentTests;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import infra.FileManagment;
import puzzle.Piece;

public class JunitTests
{
	ArrayList<Piece> pieces;
	FileManagment fileManagment;

	@Test
	@DisplayName("1")
	public void testFileManagment() throws IOException
	{
		fileManagment = new FileManagment("./resources/files/test6.in","./resources/files/test6.out");
		pieces = fileManagment.getPicesFromFile();
	}

}
