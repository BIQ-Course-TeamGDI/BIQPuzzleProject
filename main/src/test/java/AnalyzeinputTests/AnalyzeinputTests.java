package AnalyzeinputTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.mockito.Mockito;

import infra.FileManagment;
import puzzle.AnalyzeInputs;
import puzzle.Piece;
import puzzle.Puzzle;

class AnalyzeinputTests {
	ArrayList<String> errors = new ArrayList<>();

	@Test
	void ValidateSumOfEdgesGoodTest() {
		// Sum of edges for a single piece and assert is zero
		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getId()).thenReturn(10);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(0);
		Mockito.when(p1.getRight()).thenReturn(0);
		Mockito.when(p1.getBottom()).thenReturn(0);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		errors = AnalyzeInputs.validateEdgesSum(pcs);
		assertFalse(errors.contains("Number of straight edges is invalid:10"));

	}

	@Test
	void ValidateSumOfEdgesBadTest() {
		// Sum of edges for single piece is not zero
		errors.clear();

		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(1);
		Mockito.when(p1.getTop()).thenReturn(-1);
		Mockito.when(p1.getRight()).thenReturn(1);
		Mockito.when(p1.getBottom()).thenReturn(1);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		errors = AnalyzeInputs.validateEdgesSum(pcs);
		// assertFalse(errors.isEmpty());
		assertTrue(errors.contains("Cannot solve puzzle: edges sum is not zero"));
	}

	@Test
	void ValidatePieceFormatGoodTest() {
		// Format of edges for single piece is good: 0,0,1,0

		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getId()).thenReturn(11);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(0);
		Mockito.when(p1.getRight()).thenReturn(1);
		Mockito.when(p1.getBottom()).thenReturn(0);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		errors = AnalyzeInputs.validatePiecesFormat(pcs);
		assertFalse(errors.contains("Wrong elements format: 11"));

	}

	@Test
	void ValidatePieceFormatBadTest() {
		// Format of edges for single piece is bad: 0,0,2,0
		errors.clear();

		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getId()).thenReturn(13);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(2);
		Mockito.when(p1.getRight()).thenReturn(0);
		Mockito.when(p1.getBottom()).thenReturn(0);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		errors = AnalyzeInputs.validatePiecesFormat(pcs);

		assertTrue(errors.contains("Wrong elements value on line: 13"));

	}

	@Test
	void ValidateWrongNumberOfStraightEdgesBadTest() {
		// Less than minimum number of straight edges
		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(1);
		Mockito.when(p1.getRight()).thenReturn(1);
		Mockito.when(p1.getBottom()).thenReturn(0);

		Piece p2 = Mockito.mock(Piece.class);
		Mockito.when(p2.getLeft()).thenReturn(0);
		Mockito.when(p2.getTop()).thenReturn(1);
		Mockito.when(p2.getRight()).thenReturn(0);
		Mockito.when(p2.getBottom()).thenReturn(1);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		pcs.add(p2);
		assertTrue(AnalyzeInputs.validateMinimumStraightEdges(pcs).isEmpty());

	}

	@Test
	void ValidateNumberOfStraightEdgesGoodTest() {
		// input has minimum+ number of straight edges
		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(0);
		Mockito.when(p1.getRight()).thenReturn(0);
		Mockito.when(p1.getBottom()).thenReturn(0);

		Piece p2 = Mockito.mock(Piece.class);
		Mockito.when(p2.getLeft()).thenReturn(0);
		Mockito.when(p2.getTop()).thenReturn(0);
		Mockito.when(p2.getRight()).thenReturn(0);
		Mockito.when(p2.getBottom()).thenReturn(0);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		pcs.add(p2);
		assertFalse(AnalyzeInputs.validateMinimumStraightEdges(pcs).isEmpty());

	}

	@Test
	void ValidatePiecesCornersGoodTest() {
		// Input has minimum+ corners
		errors.clear();
		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(0);
		Mockito.when(p1.getRight()).thenReturn(0);
		Mockito.when(p1.getBottom()).thenReturn(0);

		Piece p2 = Mockito.mock(Piece.class);
		Mockito.when(p2.getLeft()).thenReturn(0);
		Mockito.when(p2.getTop()).thenReturn(0);
		Mockito.when(p2.getRight()).thenReturn(0);
		Mockito.when(p2.getBottom()).thenReturn(0);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		pcs.add(p2);
		errors = AnalyzeInputs.validateMinimumCorners(pcs);
		assertFalse(errors.contains("Cannot solve puzzle: missing corner element: "));

	}

	@Test
	void ValidatePiecesCornersBadTest() {
		// Less than minimum corners
		errors.clear();
		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(1);
		Mockito.when(p1.getRight()).thenReturn(1);
		Mockito.when(p1.getBottom()).thenReturn(0);

		Piece p2 = Mockito.mock(Piece.class);
		Mockito.when(p2.getLeft()).thenReturn(0);
		Mockito.when(p2.getTop()).thenReturn(-1);
		Mockito.when(p2.getRight()).thenReturn(-1);
		Mockito.when(p2.getBottom()).thenReturn(0);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		pcs.add(p2);
		errors = AnalyzeInputs.validateMinimumCorners(pcs);
		assertTrue(errors.contains("Cannot solve puzzle: missing corner element: BR"));
		assertTrue(errors.contains("Cannot solve puzzle: missing corner element: TR"));
		assertTrue(errors.contains("Cannot solve puzzle: missing corner element: TL"));

	}

	@Test
	public void AnalyzeTest() throws IOException {
		String piecesFile = "C:\\BiqPassoverProject\\test1.in";
		FileManagment fileManagment = new FileManagment(piecesFile);
		ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
		AnalyzeInputs.analyzePicesList(pieces);

	}
}
