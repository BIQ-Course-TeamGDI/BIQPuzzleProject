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
	public void testFileManager() throws IOException
	{
		fileManager = new FileManager("./resources/testsInputFiles/test6.in");
		fileManager.getPieces();
	}

}
