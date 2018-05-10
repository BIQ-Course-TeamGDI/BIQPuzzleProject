package com.att.biq.puzzle.utility;

import com.att.biq.puzzle.AnalyzeInputs;
import com.att.biq.puzzle.Piece;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class ValidatePuzzleSolutionTest {

    @Test
    public void validateSolution() throws IOException {
        // Sum of edges for a single piece and assert is zero
        String inputFile="./resources/TestOfTests/test2.in";
        String solutionFile="./resources/TestOfTests/test2.testOutRotate";
        boolean ans = ValidatePuzzleSolution.validate(inputFile,solutionFile);
        assertTrue(ans);


    }
}
