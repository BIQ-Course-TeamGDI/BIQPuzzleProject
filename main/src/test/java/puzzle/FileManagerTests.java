package puzzle;

import java.io.IOException;
import java.util.ArrayList;
import puzzle.infra.FileManager;
import org.junit.Test;
import puzzle.Piece;

public class FileManagerTests
{
	ArrayList<Piece> pieces;
	FileManager fileManager;

	@Test
	public void testFileManagment() throws IOException
	{
		fileManager = new FileManager("./resources/files/test6.in");
		fileManager.getPieces();
	}

}
