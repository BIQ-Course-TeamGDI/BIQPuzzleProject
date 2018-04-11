package puzzleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import infra.FileManager;
import puzzle.AnalyzeInputs;
import puzzle.Piece;
import puzzle.Puzzle;

public class testPuzzle
{

	@Test
	// test17.in
	public void test_3_rows_solution_puzzle_12_pieces() throws IOException
	{
		String ExpectedSolution = "4 7 1 6 \n" +
				                  "8 2 3 11 \n" +
		FileManager fileManagment = new FileManager(piecesFile,"");
		String piecesFile = "./src/test/java/puzzleTests/resourcesPuzzleFiles/test17.in";
		ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
		ArrayList<Integer> possibleRows = AnalyzeInputs.analyzePicesList(pieces,"");
		Puzzle puzzle = new Puzzle(pieces,possibleRows);
		puzzle.solve();
		String puzzleSolution = puzzle.solutionToString();
		assertEquals(puzzleSolution, ExpectedSolution);
	}

	@Test
	// test15.in
	public void test_4_rows_solution_puzzle_24_pieces() throws IOException
	{
		String ExpectedSolution = "16 21 2 17 \n" +
		FileManager fileManagment = new FileManager(piecesFile,"");
				                  "19 3 5 10 \n" +
				                  "15 7 24 6  \n" +
				                  "8 23 4 11 \n" +
				                  "20 14 22 12 \n";
		String piecesFile = "./src/test/java/puzzleTests/resourcesPuzzleFiles/test15.in";
		ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
		ArrayList<Integer> possibleRows = AnalyzeInputs.analyzePicesList(pieces,"");
		Puzzle puzzle = new Puzzle(pieces,possibleRows);
		puzzle.solve();
		String puzzleSolution = puzzle.solutionToString();
		assertEquals(puzzleSolution, ExpectedSolution);
	}

	@Test
	// test18.in
	public void NO_solution_puzzle() throws IOException
	{
		String ExpectedSolution = "Cannot solve puzzle: it seems that there is no proper solution";
		FileManager fileManagment = new FileManager(piecesFile,"");
		ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
		Puzzle puzzle = new Puzzle(pieces);
		puzzle.solve();
		puzzle.printSolution();
	}

}
