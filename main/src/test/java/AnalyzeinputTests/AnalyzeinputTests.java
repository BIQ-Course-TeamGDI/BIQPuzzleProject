package AnalyzeinputTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import puzzle.AnalyzeInputs;
import puzzle.Piece;

class AnalyzeinputTests {
	ArrayList<String> errors = new ArrayList<>();

	@Test
	void ValidateSumOfEdgesGoodTest() {
		// Sum of edges for a single piece and assert is zero
		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(0);
		Mockito.when(p1.getRight()).thenReturn(0);
		Mockito.when(p1.getBottom()).thenReturn(0);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);

		assertTrue(AnalyzeInputs.validateEdgesSum(pcs).isEmpty());

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
		assertFalse(errors.isEmpty());
		assertTrue(errors.get(0).contains("Number of straight edges is invalid"));
	}

	@Test
	void ValidatePieceFormatGoodTest() {
		// Format of edges for single piece is good: 0,0,1,0

		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(0);
		Mockito.when(p1.getRight()).thenReturn(1);
		Mockito.when(p1.getBottom()).thenReturn(0);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);

		assertTrue(AnalyzeInputs.validatePiecesFormat(pcs).isEmpty());

	}

	@Test
	void ValidatePieceFormatBadTest() {
		// Format of edges for single piece is bad: 0,0,2,0
		errors.clear();

		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(2);
		Mockito.when(p1.getRight()).thenReturn(0);
		Mockito.when(p1.getBottom()).thenReturn(0);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		errors = AnalyzeInputs.validatePiecesFormat(pcs);
		assertFalse(errors.isEmpty());
		assertTrue(errors.get(0).contains("Wrong elements format"));

	}

	@Test
	void ValidateWrongPiecesIdsBadTest() {
		// Puzzle with 2 pieces have id: 5
		errors.clear();
		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getId()).thenReturn(5);
		Piece p2 = Mockito.mock(Piece.class);
		Mockito.when(p2.getId()).thenReturn(2);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		pcs.add(p2);
		errors = AnalyzeInputs.validatePiecesIds(pcs);
		assertFalse(errors.isEmpty());
		assertTrue(errors.get(0).contains("Wrong element IDs"));

	}

	@Test
	void ValidateMissingPiecesIdsBadTest() {
		// Puzzle with non-sequenced ids :1,3,9

		errors.clear();

		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getId()).thenReturn(1);
		Piece p2 = Mockito.mock(Piece.class);
		Mockito.when(p2.getId()).thenReturn(2);
		Piece p3 = Mockito.mock(Piece.class);
		Mockito.when(p3.getId()).thenReturn(4);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		pcs.add(p2);
		pcs.add(p3);
		errors = AnalyzeInputs.validatePiecesIds(pcs);
		assertFalse(errors.isEmpty());
		assertTrue(errors.get(0).contains("Wrong element IDs"));

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
		assertFalse(AnalyzeInputs.validateMinimumStraightEdges(pcs).isEmpty());

	}

	@Test
	void ValidatePiecesCornersGoodTest() {
		// Input has minimum+ corners
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
		ArrayList<Integer> rows = new ArrayList<Integer>();
		rows.add(1);
		rows.add(2);
		assertFalse(AnalyzeInputs.validateMinimumCorners(pcs, rows).isEmpty());
	}

	@Test
	void ValidatePiecesCornersBaddTest() {
		// Less than minimum corners
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
		ArrayList<Integer> rows = new ArrayList<Integer>();
		rows.add(1);
		rows.add(2);
		assertTrue(AnalyzeInputs.validateMinimumCorners(pcs, rows).isEmpty());

	}
}
