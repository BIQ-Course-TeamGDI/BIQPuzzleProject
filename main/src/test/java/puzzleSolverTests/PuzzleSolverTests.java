package puzzleSolverTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import puzzle.EnumSides;
import puzzle.Piece;
import puzzle.Puzzle;
import puzzleSolver.PuzzleSolver;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuzzleSolverTests {

    private Puzzle puzzle;

    @BeforeEach
    public void createPuzzle(){
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        HashMap<EnumSides, Integer> shape1 = new HashMap<EnumSides, Integer>();
        shape1.put(EnumSides.LEFT,0);
        shape1.put(EnumSides.TOP,0);
        shape1.put(EnumSides.RIGHT,1);
        shape1.put(EnumSides.BOTTOM,1);
        Piece p1 = new Piece(1,shape1);
        pieces.add(p1);

        HashMap<EnumSides, Integer> shape2 = new HashMap<EnumSides, Integer>();
        shape2.put(EnumSides.LEFT,-1);
        shape2.put(EnumSides.TOP,0);
        shape2.put(EnumSides.RIGHT,0);
        shape2.put(EnumSides.BOTTOM,1);
        Piece p2 = new Piece(2,shape2);
        pieces.add(p2);

        HashMap<EnumSides, Integer> shape3 = new HashMap<EnumSides, Integer>();
        shape3.put(EnumSides.LEFT,0);
        shape3.put(EnumSides.TOP,-1);
        shape3.put(EnumSides.RIGHT,1);
        shape3.put(EnumSides.BOTTOM,0);
        Piece p3 = new Piece(3,shape3);
        pieces.add(p3);

        HashMap<EnumSides, Integer> shape4 = new HashMap<EnumSides, Integer>();
        shape4.put(EnumSides.LEFT,-1);
        shape4.put(EnumSides.TOP,-1);
        shape4.put(EnumSides.RIGHT,0);
        shape4.put(EnumSides.BOTTOM,0);
        Piece p4 = new Piece(4,shape4);
        pieces.add(p4);

        puzzle = new Puzzle(pieces);
    }


    @Test
    public void testGetPossibleSolutionRows(){
        ArrayList<Integer> possibleSolutionRows = new ArrayList<Integer>();
        possibleSolutionRows.add(1);
        possibleSolutionRows.add(2);
        possibleSolutionRows.add(4);
        assertEquals(PuzzleSolver.getPossibleSolutionRows(puzzle.size()),possibleSolutionRows);
    }

    @Test
    public void NotGood_testGetPossibleSolutionRows(){
        ArrayList<Integer> possibleSolutionRows = new ArrayList<Integer>();
        possibleSolutionRows.add(1);
        possibleSolutionRows.add(2);
        possibleSolutionRows.add(3);
        possibleSolutionRows.add(4);
        assertNotEquals(PuzzleSolver.getPossibleSolutionRows(puzzle.size()),possibleSolutionRows);
    }

    @Test
    public void testisPieceFit(){
        Piece[][] solution = new Piece[3][3];
        Piece piece = puzzle.getPuzzlePieces().get(0);
        assertTrue(PuzzleSolver.isPieceFit(piece,solution,0,0));

        solution[0][0] = piece;
        piece = puzzle.getPuzzlePieces().get(1);
        assertTrue(PuzzleSolver.isPieceFit(piece,solution,0,1));

        solution[0][1] = piece;
        piece = puzzle.getPuzzlePieces().get(2);
        assertTrue(PuzzleSolver.isPieceFit(piece,solution,1,0));

        solution[1][0] = piece;
        piece = puzzle.getPuzzlePieces().get(3);
        assertTrue(PuzzleSolver.isPieceFit(piece,solution,1,1));


    }

}
