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
//need to remove
	@BeforeEach
	public void setup() {
		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(0);
		Mockito.when(p1.getRight()).thenReturn(0);
		Mockito.when(p1.getBottom()).thenReturn(0);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
	}

	@Test
	void ValidateSumOfEdgesGoodTest() {
		// TODO: sum edges for a single piece and assert is zero

		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(0);
		Mockito.when(p1.getRight()).thenReturn(0);
		Mockito.when(p1.getBottom()).thenReturn(0);

		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		AnalyzeInputs.validateEdgesSum(pcs);
		// need to add assert
	}

	@Test
	void ValidateSumOfEdgesBadTest() {
		// TODO: 5,-5,0,0

		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(5);
		Mockito.when(p1.getTop()).thenReturn(-5);
		Mockito.when(p1.getRight()).thenReturn(0);
		Mockito.when(p1.getBottom()).thenReturn(0);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		AnalyzeInputs.validateEdgesSum(pcs);
		// need to add assert
	}

	@Test
	void ValidatePieceFormatGoodTest() {
		// TODO: 0,0,1,0

		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(0);
		Mockito.when(p1.getRight()).thenReturn(1);
		Mockito.when(p1.getBottom()).thenReturn(0);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		AnalyzeInputs.validateEdgesSum(pcs);
		// need to add assert

	}

	@Test
	void ValidatePieceFormatBadTest() {
		// TODO: 0,0,2,0

		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getLeft()).thenReturn(0);
		Mockito.when(p1.getTop()).thenReturn(2);
		Mockito.when(p1.getRight()).thenReturn(0);
		Mockito.when(p1.getBottom()).thenReturn(0);
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		AnalyzeInputs.validateEdgesSum(pcs);
		// need to add assert

	}

	@Test
	void ValidateWrongPiecesIdsBadTest() {
		// TODO: puzzle with 7 pieces have id:9
		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getId()).thenReturn(5);
		Piece p2 = Mockito.mock(Piece.class);
		Mockito.when(p2.getId()).thenReturn(2);
	
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		pcs.add(p2);
		AnalyzeInputs.validatePiecesIds(pcs);
		// need to add assert

	}

	@Test
	void ValidateMissingPiecesIdsBadTest() {
		// TODO: puzzle with ids :1,3,9
		Piece p1 = Mockito.mock(Piece.class);
		Mockito.when(p1.getId()).thenReturn(1);
		Piece p2 = Mockito.mock(Piece.class);
		Mockito.when(p2.getId()).thenReturn(2);
		Piece p3 = Mockito.mock(Piece.class);
		Mockito.when(p3.getId()).thenReturn(4);
	
		ArrayList<Piece> pcs = new ArrayList<>();
		pcs.add(p1);
		pcs.add(p2);
		AnalyzeInputs.validatePiecesIds(pcs);
	
	}

	@Test
	void ValidateWrongNumberOfStraightEdgesBadTest() {
		// TODO: wrong number of straight edges
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
		AnalyzeInputs.validateMinimumStraightEdges(pcs);
		
		// need to add assert

	}

	@Test
	void ValidateNumberOfStraightEdgesGoodTest() {
		// TODO: minimum+ number of straight edges
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
		AnalyzeInputs.validateMinimumStraightEdges(pcs);
		
		// need to add assert

	}

	@Test
	void ValidatePiecesCornersGoodTest() {
		// TODO: minimum+ corner
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
		AnalyzeInputs.validateMinimumCorners(pcs, null);
		
		// need to add assert

	}

	@Test
	void ValidatePiecesCornersBaddTest() {
		// TODO: > corners
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
		AnalyzeInputs.validateMinimumCorners(pcs, null);
		
		// need to add assert

	}
}
