package puzzleTests;

import infra.FileManager;
import org.junit.Test;
import puzzle.AnalyzeInputs;
import puzzle.Puzzle;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		FileManager fileManagment = new FileManager(piecesFile);
		AnalyzeInputs analyzeInputs = new AnalyzeInputs(fileManagment.getPiecesFromFile(),fileManagment);
		analyzeInputs.analyzePicesList();
		Puzzle puzzle = new Puzzle(fileManagment.getPiecesFromFile(),analyzeInputs.getSolutionPossibleRows());
		puzzle.solve();
		String puzzleSolution = puzzle.solutionToString();
		assertEquals(puzzleSolution, ExpectedSolution);
	}

	@Test
	// test15.in
	public void test_4_rows_solution_puzzle_24_pieces() throws IOException
	{
		String ExpectedSolution = "16 21 2 17\n" +
				                  "13 1 18 9\n" +
				                  "19 3 5 10\n" +
				                  "15 7 6 24\n" +
				                  "8 23 4 11\n" +
				                  "20 14 22 12\n";
		String piecesFile = "./resources/puzzleFilesTests/test15.in";
		FileManager fileManagment = new FileManager(piecesFile);
		AnalyzeInputs analyzeInputs = new AnalyzeInputs(fileManagment.getPiecesFromFile(),fileManagment);
		analyzeInputs.analyzePicesList();
		Puzzle puzzle = new Puzzle(fileManagment.getPiecesFromFile(),analyzeInputs.getSolutionPossibleRows());
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
		FileManager fileManagment = new FileManager(piecesFile);
		AnalyzeInputs analyzeInputs = new AnalyzeInputs(fileManagment.getPiecesFromFile(),fileManagment);
		analyzeInputs.analyzePicesList();
		Puzzle puzzle = new Puzzle(fileManagment.getPiecesFromFile(),analyzeInputs.getSolutionPossibleRows());
		puzzle.solve();
		String puzzleSolution = puzzle.solutionToString();
		assertEquals(puzzleSolution, ExpectedSolution);
	}

    @Test
    public void test_solution_file() throws IOException {
        String inputPiecesFile = "./src/test/java/puzzleTests/resourcesPuzzleFiles/test14.in";
        String outputSolutionFile = "./src/test/java/puzzleTests/resourcesPuzzleFiles/test14.out";
        assertTrue(Puzzle.checkSolutionFile(inputPiecesFile,outputSolutionFile));
        //assertTrue(Puzzle.checkSolutionFile(inputPiecesFile,outputSolutionFile));
    }

	@Test
	public void test_not_good_solution_file() throws IOException {
		String inputPiecesFile = "./src/test/java/puzzleTests/resourcesPuzzleFiles/test14.in";
		String outputSolutionFile = "./src/test/java/puzzleTests/resourcesPuzzleFiles/test15.out";
		assertFalse(Puzzle.checkSolutionFile(inputPiecesFile,outputSolutionFile));
	}

}
