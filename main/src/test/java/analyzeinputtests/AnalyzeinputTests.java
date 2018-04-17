package AnalyzeinputTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import infra.FileManager;
import org.junit.Test;
import puzzle.AnalyzeInputs;
import puzzle.Piece;

public class AnalyzeinputTests {

	@Test
	public void ValidateSumOfEdgesGoodTest() {
		// Sum of edges for a single piece and assert is zero

		HashMap<Integer, Integer> edges = new HashMap<>();
		edges.put(0, 0);
		edges.put(1, 0);
		edges.put(2, 0);
		edges.put(3, 0);
		Piece pc1 = new Piece(10, edges);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		AnalyzeInputs analyze = new AnalyzeInputs(pcs);

		analyze.validateEdgesSum();
		assertTrue(analyze.getErrorsList().isEmpty());
	}

	@Test
	public void ValidateSumOfEdgesBadTest() {
		// Sum of edges for single piece is not zero
		HashMap<Integer, Integer> edges = new HashMap<>();
		edges.put(0, 1);
		edges.put(1, -1);
		edges.put(2, 1);
		edges.put(3, 1);
		Piece pc1 = new Piece(10, edges);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		AnalyzeInputs analyze = new AnalyzeInputs(pcs);
		analyze.validateEdgesSum();
		assertTrue(analyze.getErrorsList().contains("Cannot solve puzzle: edges sum is not zero"));
	}

	@Test
	public void ValidatePieceFormatGoodTest() {
		// Format of edges for single piece is good: 0,0,1,0
		HashMap<Integer, Integer> edges = new HashMap<>();
		edges.put(0, 0);
		edges.put(1, 0);
		edges.put(2, 1);
		edges.put(3, 0);
		Piece pc1 = new Piece(11, edges);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		AnalyzeInputs analyze = new AnalyzeInputs(pcs);

		analyze.validatePiecesFormat();
		assertFalse(analyze.getErrorsList().contains("Wrong elements format: 11"));

	}

	@Test
	public void ValidatePieceFormatBadTest() {
		// Format of edges for single piece is bad: 0,0,2,0

		HashMap<Integer, Integer> edges = new HashMap<>();
		edges.put(0, 0);
		edges.put(1, 2);
		edges.put(2, 0);
		edges.put(3, 0);
		Piece pc1 = new Piece(13, edges);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		AnalyzeInputs analyze = new AnalyzeInputs(pcs);
		analyze.validatePiecesFormat();

		assertTrue(analyze.getErrorsList().contains("Wrong elements value on line: 13"));

	}

	@Test
	public void ValidateWrongNumberOfStraightEdgesBadTest() {
		// Less than minimum number of straight edges

		HashMap<Integer, Integer> edges = new HashMap<>();
		edges.put(0, 0);
		edges.put(1, 1);
		edges.put(2, 1);
		edges.put(3, 0);
		Piece pc1 = new Piece(10, edges);

		HashMap<Integer, Integer> edges2 = new HashMap<>();
		edges2.put(0, 0);
		edges2.put(1, 1);
		edges2.put(2, 0);
		edges2.put(3, 1);
		Piece pc2 = new Piece(11, edges2);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		pcs.add(pc2);
		AnalyzeInputs analyze = new AnalyzeInputs(pcs);
		assertTrue(analyze.validateMinimumStraightEdges().isEmpty());

	}

	@Test
	public void ValidateNumberOfStraightEdgesGoodTest() {
		// input has minimum+ number of straight edges

		HashMap<Integer, Integer> edges = new HashMap<>();
		edges.put(0, 0);
		edges.put(1, 0);
		edges.put(2, 0);
		edges.put(3, 0);
		Piece pc1 = new Piece(10, edges);

		HashMap<Integer, Integer> edges2 = new HashMap<>();
		edges2.put(0, 0);
		edges2.put(1, 0);
		edges2.put(2, 0);
		edges2.put(3, 0);
		Piece pc2 = new Piece(11, edges2);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		pcs.add(pc2);
		AnalyzeInputs analyze = new AnalyzeInputs(pcs);

		assertFalse(analyze.validateMinimumStraightEdges().isEmpty());

	}

	@Test
	public void ValidatePiecesCornersGoodTest() {
		// Input has minimum+ corners

		HashMap<Integer, Integer> edges = new HashMap<>();
		edges.put(0, 0);
		edges.put(1, 0);
		edges.put(2, 0);
		edges.put(3, 0);
		Piece pc1 = new Piece(10, edges);

		HashMap<Integer, Integer> edges2 = new HashMap<>();
		edges2.put(0, 0);
		edges2.put(1, 0);
		edges2.put(2, 0);
		edges2.put(3, 0);
		Piece pc2 = new Piece(11, edges2);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		pcs.add(pc2);
		AnalyzeInputs analyze = new AnalyzeInputs(pcs);

		analyze.validateMinimumCorners();
		assertFalse(analyze.getErrorsList().contains("Cannot solve puzzle: missing corner element: "));

	}

	@Test
	public void ValidatePiecesCornersBadTest() {
		// Less than minimum corners
		int[] edges = new int[4];
		edges[0] = 0;
		edges[1] = 1;
		edges[2] = 1;
		edges[3] = 0;
		Piece pc1 = new Piece(10, edges);

		HashMap<Integer, Integer> edges2 = new HashMap<>();
		edges2.put(0, 0);
		edges2.put(1, -1);
		edges2.put(2, -1);
		edges2.put(3, 0);
		Piece pc2 = new Piece(11, edges2);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		pcs.add(pc2);
		AnalyzeInputs analyze = new AnalyzeInputs(pcs);
		analyze.validateMinimumCorners();
		assertTrue(analyze.getErrorsList().contains("Cannot solve puzzle: missing corner element: BR"));
		assertTrue(analyze.getErrorsList().contains("Cannot solve puzzle: missing corner element: TR"));
		assertTrue(analyze.getErrorsList().contains("Cannot solve puzzle: missing corner element: TL"));

	}

	@Test
	public void GetPiecesFromFileAndAnalayzeBadFileTest() throws IOException {
		String piecesFile = "./resources/analyzedInputTestsFiles/test1.in";
		FileManager fileManager = new FileManager(piecesFile);
		AnalyzeInputs analyze = new AnalyzeInputs(fileManager.getPieces());
		analyze.analyzePicesList();
		assertTrue(analyze.getErrorsList().contains("Cannot solve puzzle: wrong number of straight edges"));
		assertTrue(analyze.getErrorsList().contains("Cannot solve puzzle: missing corner element: BR"));
		assertTrue(analyze.getErrorsList().contains("Cannot solve puzzle: missing corner element: BL"));

	}

	@Test
	public void GetPiecesFromFileAndAnalayzeGoodFileTest() throws IOException {
		String piecesFile = "./resources/analyzedInputTestsFiles/test2.in";
		FileManager fileManager = new FileManager(piecesFile);
		AnalyzeInputs analyze = new AnalyzeInputs(fileManager.getPieces());
		analyze.analyzePicesList();
		assertTrue(analyze.getErrorsList().isEmpty());

	}

}
