package AnalyzeinputTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;


import infra.EnumSides;
import infra.FileManager;
import org.junit.Test;
import puzzle.AnalyzeInputs;
import puzzle.Piece;

public class AnalyzeinputTests {


    @Test
       public void ValidateSumOfEdgesGoodTest() {
        // Sum of edges for a single piece and assert is zero

        HashMap<EnumSides, Integer> edges = new HashMap<>();
        edges.put(EnumSides.LEFT, 0);
        edges.put(EnumSides.TOP, 0);
        edges.put(EnumSides.RIGHT, 0);
        edges.put(EnumSides.BOTTOM, 0);
        Piece pc1 = new Piece(10, edges);
        ArrayList<Piece> pcs = new ArrayList<>();
        pcs.add(pc1);
        FileManager fileManager = new FileManager();
        AnalyzeInputs analyze = new AnalyzeInputs(pcs, fileManager);

        analyze.validateEdgesSum();
        assertFalse(fileManager.getAllErrors().contains("Number of straight edges is invalid:10"));
    }

    @Test
    public void ValidateSumOfEdgesBadTest() {
        // Sum of edges for single piece is not zero
        HashMap<EnumSides, Integer> edges = new HashMap<>();
        edges.put(EnumSides.LEFT, 1);
        edges.put(EnumSides.TOP, -1);
        edges.put(EnumSides.RIGHT, 1);
        edges.put(EnumSides.BOTTOM, 1);
        Piece pc1 = new Piece(10, edges);

        ArrayList<Piece> pcs = new ArrayList<>();
        pcs.add(pc1);
        FileManager fileManager = new FileManager();
        AnalyzeInputs analyze = new AnalyzeInputs(pcs, fileManager);
        analyze.validateEdgesSum();
        assertTrue(fileManager.getAllErrors().contains("Cannot solve puzzle: edges sum is not zero"));
    }

    @Test
    public void ValidatePieceFormatGoodTest() {
        // Format of edges for single piece is good: 0,0,1,0
        HashMap<EnumSides, Integer> edges = new HashMap<>();
        edges.put(EnumSides.LEFT, 0);
        edges.put(EnumSides.TOP, 0);
        edges.put(EnumSides.RIGHT, 1);
        edges.put(EnumSides.BOTTOM, 0);
        Piece pc1 = new Piece(11, edges);

        ArrayList<Piece> pcs = new ArrayList<>();
        pcs.add(pc1);
        FileManager fileManager = new FileManager();
        AnalyzeInputs analyze = new AnalyzeInputs(pcs, fileManager);

        analyze.validatePiecesFormat();
        assertFalse(fileManager.getAllErrors().contains("Wrong elements format: 11"));

    }

    @Test
    public void ValidatePieceFormatBadTest() {
        // Format of edges for single piece is bad: 0,0,2,0

        HashMap<EnumSides, Integer> edges = new HashMap<>();
        edges.put(EnumSides.LEFT, 0);
        edges.put(EnumSides.TOP, 2);
        edges.put(EnumSides.RIGHT, 0);
        edges.put(EnumSides.BOTTOM, 0);
        Piece pc1 = new Piece(13, edges);

        ArrayList<Piece> pcs = new ArrayList<>();
        pcs.add(pc1);
        FileManager fileManager = new FileManager();
        AnalyzeInputs analyze = new AnalyzeInputs(pcs, fileManager);
        analyze.validatePiecesFormat();

        assertTrue(fileManager.getAllErrors().contains("Wrong elements value on line: 13"));

    }

    @Test
    public void ValidateWrongNumberOfStraightEdgesBadTest() {
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
        FileManager fileManager = new FileManager();
        AnalyzeInputs analyze = new AnalyzeInputs(pcs, fileManager);

        assertTrue(analyze.validateMinimumStraightEdges().isEmpty());

    }

    @Test
    public void ValidateNumberOfStraightEdgesGoodTest() {
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
        FileManager fileManager = new FileManager();
        AnalyzeInputs analyze = new AnalyzeInputs(pcs, fileManager);

        assertFalse(analyze.validateMinimumStraightEdges().isEmpty());

    }

    @Test
    public void ValidatePiecesCornersGoodTest() {
        // Input has minimum+ corners

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
        FileManager fileManager = new FileManager();
        AnalyzeInputs analyze = new AnalyzeInputs(pcs, fileManager);

        analyze.validateMinimumCorners();
        assertFalse(fileManager.getAllErrors().contains("Cannot solve puzzle: missing corner element: "));

    }

    @Test
    public void ValidatePiecesCornersBadTest() {
        // Less than minimum corners

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
        FileManager fileManager = new FileManager();
        AnalyzeInputs analyze = new AnalyzeInputs(pcs, fileManager);
        analyze.validateMinimumCorners();
        assertTrue(fileManager.getAllErrors().contains("Cannot solve puzzle: missing corner element: BR"));
        assertTrue(fileManager.getAllErrors().contains("Cannot solve puzzle: missing corner element: TR"));
        assertTrue(fileManager.getAllErrors().contains("Cannot solve puzzle: missing corner element: TL"));

    }

    
}
