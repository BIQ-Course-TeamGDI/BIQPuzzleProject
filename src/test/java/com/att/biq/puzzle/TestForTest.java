package com.att.biq.puzzle;

import com.att.biq.puzzle.infra.FileManager;
import org.junit.Test;

import java.io.IOException;

public class TestForTest {

    @Test
    public void Puzzle() throws IOException, InterruptedException {

        String piecesInputFile="./resources/TestOfTests/test100.in";
        String testOutputFile="./resources/TestOfTests/test100.testOut";


        FileManager fileManager = new FileManager(piecesInputFile);
        fileManager.setPiecesFromFile();
        AnalyzeInputs analyzeInputs = new AnalyzeInputs(fileManager.getPieces());
        analyzeInputs.analyzePicesList();

        PuzzleManager puzzleManager = new PuzzleManager(piecesInputFile,testOutputFile,true,2);
        //PuzzleManager puzzleManager = new PuzzleManager(piecesInputFile,testOutputFile,false,0);
        puzzleManager.solvePuzzle();


    }
}
