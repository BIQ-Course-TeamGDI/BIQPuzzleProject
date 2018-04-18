package puzzle;

import org.junit.Test;

import com.att.biq.puzzle.AnalyzeInputs;
import com.att.biq.puzzle.Piece;
import com.att.biq.puzzle.Puzzle;
import com.att.biq.puzzle.infra.FileManager;
import com.att.biq.puzzle.utility.validatePuzzleSolution;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class PuzzleTests
{

	private String inputFilesPath = "./resources/testsInputFiles/";

	@Test
	public void test_3_rows_solution_puzzle_12_pieces(){
		// create 12 pieces
		ArrayList<Piece> pieces = new ArrayList<>();
		ArrayList<Integer> sol = new ArrayList<>();
		Piece p = new Piece(4,new int[]{0,0,1,-1});
		pieces.add(p);
		p = new Piece(7,new int[]{-1,0,-1,1});
		pieces.add(p);
		p = new Piece(1,new int[]{1,0,-1,-1});
		pieces.add(p);
		p = new Piece(6,new int[]{1,0,0,1});
		pieces.add(p);
		p = new Piece(8,new int[]{0,1,-1,-1});
		pieces.add(p);
		p = new Piece(2,new int[]{1,-1,1,-1});
		pieces.add(p);
		p = new Piece(3,new int[]{-1,1,-1,1});
		pieces.add(p);
		p = new Piece(11,new int[]{1,-1,0,-1});
		pieces.add(p);
		p = new Piece(9,new int[]{0,1,-1,0});
		pieces.add(p);
		p = new Piece(10,new int[]{1,1,-1,0});
		pieces.add(p);
		p = new Piece(5,new int[]{1,-1,1,0});
		pieces.add(p);
		p = new Piece(12,new int[]{-1,1,0,0});
		pieces.add(p);

		sol.add(1);
		sol.add(2);
		sol.add(3);
		sol.add(12);

		Puzzle puzzle = new Puzzle(pieces,sol);
		puzzle.solve();

		String ExpectedSolution = "4 7 1 6\n" +
				                  "8 2 3 11\n" +
				                  "9 10 5 12\n";
		String puzzleSolution = puzzle.solutionToString();
		assertEquals(puzzleSolution, ExpectedSolution);
	}



	@Test
	// test15.in
	public void test_4_rows_solution_puzzle_24_pieces() throws IOException {
		String ExpectedSolution = "16 21 2 17\n" +
				                  "13 1 18 9\n" +
				                  "19 7 6 10\n" +
				                  "15 3 5 24\n" +
				                  "8 23 4 11\n" +
				                  "20 14 22 12\n";
		String piecesFile = inputFilesPath +"test15.in";
		FileManager fileManager = new FileManager(piecesFile);
		fileManager.setPiecesFromFile();
		AnalyzeInputs analyzeInputs = new AnalyzeInputs(fileManager.getPieces());
		analyzeInputs.analyzePicesList();
		Puzzle puzzle = new Puzzle(fileManager.getPieces(),analyzeInputs.getSolutionPossibleRows());
		puzzle.solve();
		String puzzleSolution = puzzle.solutionToString();
		assertEquals(puzzleSolution, ExpectedSolution);
	}

	@Test
	// test18.in
	public void NO_solution_puzzle() throws IOException {
		String ExpectedSolution = "Cannot solve puzzle: it seems that there is no proper solution\n";
		String piecesFile = inputFilesPath +"test18.in";
		FileManager fileManager = new FileManager(piecesFile);
		fileManager.setPiecesFromFile();
		AnalyzeInputs analyzeInputs = new AnalyzeInputs(fileManager.getPieces());
		analyzeInputs.analyzePicesList();
		Puzzle puzzle = new Puzzle(fileManager.getPieces(),analyzeInputs.getSolutionPossibleRows());
		puzzle.solve();
		String puzzleSolution = puzzle.solutionToString();
		assertEquals(puzzleSolution, ExpectedSolution);
	}

    @Test
    public void test_good_solution_file() throws IOException {
        String inputPiecesFile = inputFilesPath +"test14.in";
        String outputSolutionFile = "./resources/puzzleTests_outputFiles/test14.testOut";
        assertTrue(validatePuzzleSolution.validate(inputPiecesFile, outputSolutionFile));
    }

	@Test
	public void test_not_good_solution_file() throws IOException {
		String inputPiecesFile = inputFilesPath +"test15.in";
		String outputSolutionFile = "./resources/puzzleTests_outputFiles/test14.testOut";
		assertFalse(validatePuzzleSolution.validate(inputPiecesFile,outputSolutionFile));
	}
	@Test
	public void GetPiecesFromFileAndAnalayzeFileWithErrors() throws IOException
	{
		FileManager fileManager = new FileManager("./resources/analyzedInputTestsFiles/test3.in");
		fileManager.setPiecesFromFile();
		AnalyzeInputs analyze = new AnalyzeInputs(fileManager.getPieces());
		analyze.analyzePicesList();
		assertTrue(!analyze.getErrorsList().isEmpty());
	}
}
