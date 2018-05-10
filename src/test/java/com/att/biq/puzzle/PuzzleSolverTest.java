package com.att.biq.puzzle;

import com.att.biq.puzzle.infra.FileManager;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class PuzzleSolverTest {



    @Test
    public void test_puzzleSolver(){
        Piece p1 = new Piece(0,new int[]{0,0,-1,0});
        Piece p2 = new Piece(1,new int[]{1,0,0,0});
        Piece p3 = new Piece(2,new int[]{1,0,-1,0});
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(p1);
        pieces.add(p2);
        pieces.add(p3);
        PuzzleIndexer puzzleIndexer = new PuzzleIndexer(pieces,false);
        PuzzleSolver puzzleSolver = new PuzzleSolver(puzzleIndexer,1,3);
        //puzzleSolver.solve();
    }

    @Test
    public void test_puzzleSolver100() throws IOException {
        //String inputPiecesFile ="./resources/indexer/test12.in";
        //String outPutFile = "./resources/indexer/test12.testOut";
        //String inputPiecesFile ="./resources/indexer/test16.in";
        //String outPutFile = "./resources/indexer/test16.testOut";
        //String inputPiecesFile ="./resources/indexer/test24.in";
        //String outPutFile = "./resources/indexer/test24.testOut";
        //String inputPiecesFile ="./resources/indexer/test40.in";
        //String outPutFile = "./resources/indexer/test40.testOut";
        String inputPiecesFile ="./resources/indexer/test100.in";
        String outPutFile = "./resources/indexer/test100.testOut";

        FileManager fileManager = new FileManager(inputPiecesFile);
        fileManager.setPiecesFromFile();
        if (fileManager.getAllErrors().size() != 0)
        {
            fileManager.printErrorsToFile(outPutFile);
            return;
        }
        AnalyzeInputs analyzeInputs = new AnalyzeInputs(fileManager.getPieces());
        analyzeInputs.analyzePicesList();
        if (fileManager.getAllErrors().size() != 0)
        {
            fileManager.printErrorsToFile(outPutFile);
            return;
        }

//        PuzzleIndexer puzzleIndexer = new PuzzleIndexer(fileManager.getPieces(),false);
//        Puzzle puzzle = new Puzzle(puzzleIndexer);
//        puzzle.solve(3, 8);
//        for(int numOfRows : analyzeInputs.getSolutionPossibleRows()){
//            if(puzzle.getSolution()==null) {
//                int numOfColumns = fileManager.getPieces().size() / numOfRows;
//                System.out.println("Search solution for " + numOfRows + "x" + numOfColumns+ " board"  );
//                puzzle.solve(numOfRows, numOfColumns);
//            }
//        }
//        puzzle.saveSolution2File(outPutFile);
//        System.out.println(puzzle.solution2String());
    }
}
