package puzzleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import infra.FileManagment;
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
				                  "9 10 5 12 \n";
		String piecesFile = "./src/test/java/puzzleTests/resourcesPuzzleFiles/test17.in";
		FileManagment fileManagment = new FileManagment(piecesFile);
		ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
		AnalyzeInputs analyze = new AnalyzeInputs(pieces);
		ArrayList<Integer> possibleRows = analyze.getSolutionPossibleRows();
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
				                  "13 1 18 9 \n" +
				                  "19 3 5 10 \n" +
				                  "15 7 24 6  \n" +
				                  "8 23 4 11 \n" +
				                  "20 14 22 12 \n";
		String piecesFile = "./src/test/java/puzzleTests/resourcesPuzzleFiles/test15.in";
		FileManagment fileManagment = new FileManagment(piecesFile);
		ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
		AnalyzeInputs analyze = new AnalyzeInputs(pieces);
		ArrayList<Integer> possibleRows = analyze.getSolutionPossibleRows();
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
		String piecesFile = "./src/test/java/puzzleTests/resourcesPuzzleFiles/test18.in";
		FileManagment fileManagment = new FileManagment(piecesFile);
		ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
		AnalyzeInputs analyze = new AnalyzeInputs(pieces);
		ArrayList<Integer> possibleRows = analyze.getSolutionPossibleRows();
		Puzzle puzzle = new Puzzle(pieces,possibleRows);
		puzzle.solve();
		String puzzleSolution = puzzle.solutionToString();
		assertEquals(puzzleSolution, ExpectedSolution);
	}

    @Test
    public void test_solution_file() throws IOException {
        String inputPiecesFile = "./src/test/java/puzzleTests/resourcesPuzzleFiles/test14.in";
        String outputSolutionFile = "./src/test/java/puzzleTests/resourcesPuzzleFiles/test14.out";
        Puzzle.testPuzzleSolutions(inputPiecesFile,outputSolutionFile);
    }

}
