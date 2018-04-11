package AnalyzeinputTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import infra.EnumSides;
import infra.FileManagment;
import puzzle.AnalyzeInputs;
import puzzle.Piece;

class AnalyzeinputTests {
	ArrayList<String> errors = new ArrayList<>();

	@Test
	//@ParameterizedTest(name = "fiboTest{index}: Values:{0}")
	//@CsvSource({ "13", "31", "133", "541", "1" })
	void ValidateSumOfEdgesGoodTest() {
		// Sum of edges for a single piece and assert is zero

		HashMap<EnumSides, Integer> edges = new HashMap<>();
		edges.put(EnumSides.LEFT, 0);
		edges.put(EnumSides.TOP, 0);
		edges.put(EnumSides.RIGHT, 0);
		edges.put(EnumSides.BOTTOM, 0);
		Piece pc1 = new Piece(10, edges);
				
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		errors = AnalyzeInputs.validateEdgesSum(pcs);
		assertFalse(errors.contains("Number of straight edges is invalid:10"));
	}

	@Test
	void ValidateSumOfEdgesBadTest() {
		// Sum of edges for single piece is not zero
		errors.clear();

		HashMap<EnumSides, Integer> edges = new HashMap<>();
		edges.put(EnumSides.LEFT, 1);
		edges.put(EnumSides.TOP, -1);
		edges.put(EnumSides.RIGHT, 1);
		edges.put(EnumSides.BOTTOM, 1);
		Piece pc1 = new Piece(10, edges);
		
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		errors = AnalyzeInputs.validateEdgesSum(pcs);
		assertTrue(errors.contains("Cannot solve puzzle: edges sum is not zero"));
	}

	@Test
	void ValidatePieceFormatGoodTest() {
		// Format of edges for single piece is good: 0,0,1,0

		HashMap<EnumSides, Integer> edges = new HashMap<>();
		edges.put(EnumSides.LEFT, 0);
		edges.put(EnumSides.TOP, 0);
		edges.put(EnumSides.RIGHT, 1);
		edges.put(EnumSides.BOTTOM, 0);
		Piece pc1 = new Piece(11, edges);
		
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		errors = AnalyzeInputs.validatePiecesFormat(pcs);
		assertFalse(errors.contains("Wrong elements format: 11"));

	}

	@Test
	void ValidatePieceFormatBadTest() {
		// Format of edges for single piece is bad: 0,0,2,0
		errors.clear();
		HashMap<EnumSides, Integer> edges = new HashMap<>();
		edges.put(EnumSides.LEFT, 0);
		edges.put(EnumSides.TOP, 2);
		edges.put(EnumSides.RIGHT, 0);
		edges.put(EnumSides.BOTTOM, 0);
		Piece pc1 = new Piece(13, edges);
		
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		errors = AnalyzeInputs.validatePiecesFormat(pcs);

		assertTrue(errors.contains("Wrong elements value on line: 13"));

	}

	@Test
	void ValidateWrongNumberOfStraightEdgesBadTest() {
		// Less than minimum number of straight edges
		HashMap<EnumSides, Integer> edges = new HashMap<>();
		edges.put(EnumSides.LEFT, 0);
		edges.put(EnumSides.TOP, 1);
		edges.put(EnumSides.RIGHT, 1);
		edges.put(EnumSides.BOTTOM, 0);
		Piece pc1 = new Piece(10, edges);
		
		HashMap<EnumSides, Integer> edges2 = new HashMap<>();
		edges2.put(EnumSides.LEFT, 0);
		edges2.put(EnumSides.TOP, 1);
		edges2.put(EnumSides.RIGHT, 0);
		edges2.put(EnumSides.BOTTOM, 1);
		Piece pc2 = new Piece(11, edges2); 
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		pcs.add(pc2);
	
		assertTrue(AnalyzeInputs.validateMinimumStraightEdges(pcs).isEmpty());

	}

	@Test
	void ValidateNumberOfStraightEdgesGoodTest() {
		// input has minimum+ number of straight edges
		HashMap<EnumSides, Integer> edges = new HashMap<>();
		edges.put(EnumSides.LEFT, 0);
		edges.put(EnumSides.TOP, 0);
		edges.put(EnumSides.RIGHT, 0);
		edges.put(EnumSides.BOTTOM, 0);
		Piece pc1 = new Piece(10, edges);
		
		HashMap<EnumSides, Integer> edges2 = new HashMap<>();
		edges2.put(EnumSides.LEFT, 0);
		edges2.put(EnumSides.TOP, 0);
		edges2.put(EnumSides.RIGHT, 0);
		edges2.put(EnumSides.BOTTOM, 0);
		Piece pc2 = new Piece(11, edges2); 
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		pcs.add(pc2);
		
		assertFalse(AnalyzeInputs.validateMinimumStraightEdges(pcs).isEmpty());

	}

	@Test
	void ValidatePiecesCornersGoodTest() {
		// Input has minimum+ corners
		errors.clear();
		HashMap<EnumSides, Integer> edges = new HashMap<>();
		edges.put(EnumSides.LEFT, 0);
		edges.put(EnumSides.TOP, 0);
		edges.put(EnumSides.RIGHT, 0);
		edges.put(EnumSides.BOTTOM, 0);
		Piece pc1 = new Piece(10, edges);
		
		HashMap<EnumSides, Integer> edges2 = new HashMap<>();
		edges2.put(EnumSides.LEFT, 0);
		edges2.put(EnumSides.TOP, 0);
		edges2.put(EnumSides.RIGHT, 0);
		edges2.put(EnumSides.BOTTOM, 0);
		Piece pc2 = new Piece(11, edges2); 
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		pcs.add(pc2);
		
		errors = AnalyzeInputs.validateMinimumCorners(pcs);
		assertFalse(errors.contains("Cannot solve puzzle: missing corner element: "));

	}

	@Test
	void ValidatePiecesCornersBadTest() {
		// Less than minimum corners
		errors.clear();
		
		HashMap<EnumSides, Integer> edges = new HashMap<>();
		edges.put(EnumSides.LEFT, 0);
		edges.put(EnumSides.TOP, 1);
		edges.put(EnumSides.RIGHT, 1);
		edges.put(EnumSides.BOTTOM, 0);
		Piece pc1 = new Piece(10, edges);
		
		HashMap<EnumSides, Integer> edges2 = new HashMap<>();
		edges2.put(EnumSides.LEFT, 0);
		edges2.put(EnumSides.TOP, -1);
		edges2.put(EnumSides.RIGHT, -1);
		edges2.put(EnumSides.BOTTOM, 0);
		Piece pc2 = new Piece(11, edges2); 
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(pc1);
		pcs.add(pc2);
		
		errors = AnalyzeInputs.validateMinimumCorners(pcs);
		assertTrue(errors.contains("Cannot solve puzzle: missing corner element: BR"));
		assertTrue(errors.contains("Cannot solve puzzle: missing corner element: TR"));
		assertTrue(errors.contains("Cannot solve puzzle: missing corner element: TL"));

	}

	@Test
	public void AnalyzeTest() throws IOException {
		String piecesFile = "C:\\BiqPassoverProject\\test1.in";
		String outputFile = "C:\\BiqPassoverProject\\test1.out";
		FileManagment fileManagment = new FileManagment(piecesFile);
		ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
		AnalyzeInputs.analyzePicesList(pieces, outputFile);

	}
}
