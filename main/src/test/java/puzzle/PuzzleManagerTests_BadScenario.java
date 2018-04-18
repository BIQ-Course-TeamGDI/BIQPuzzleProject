package puzzle;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import puzzle.Puzzle;
import puzzle.utility.validatePuzzleSolution;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PuzzleManagerTests_BadScenario {
    private String pathToFilesFolder="./resources/endToEndTestsFiles/BadPuzzleSolutionFiles/";
    private String piecesInputFile;
    private String testOutputFile;

    @Parameterized.Parameters
    public static Collection<Object[]> data (){
        return Arrays.asList(new Object[][] {
                {"test1.in","test1.testOut"},
                {"test14.in","test14.testOut"},
        });
    }

    public PuzzleManagerTests_BadScenario(String piecesInputFile, String testOutputFile) {
        this.piecesInputFile=pathToFilesFolder + piecesInputFile;
        this.testOutputFile=pathToFilesFolder + testOutputFile;
    }

    @Test
    public void testGoodFilesSolution() throws IOException
    {
        assertFalse(validatePuzzleSolution.validate(piecesInputFile, testOutputFile));
        //assertFalse(Puzzle.validateSolution(piecesInputFile,testOutputFile));
    }


}
