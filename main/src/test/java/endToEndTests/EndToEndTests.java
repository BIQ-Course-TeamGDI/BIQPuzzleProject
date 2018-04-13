package endToEndTests;


import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import puzzle.PuzzleManager;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class EndToEndTests {
    private String pathToFilesFolder="./resources/endToEndTestsFiles/";
    private String piecesInputFile;
    private String amirOutputFile;
    private String testOutputFile;

    @Parameterized.Parameters
    public static Collection<Object[]> data (){
        return Arrays.asList(new Object[][] {
                {"test1.in","test1.amirOut","test1.testOut"},
                {"test2.in","test2.amirOut","test2.testOut"},
                {"test3.in","test3.amirOut","test3.testOut"},
                {"test4.in","test4.amirOut","test4.testOut"},
                {"test5.in","test5.amirOut","test5.testOut"},
                {"test6.in","test6.amirOut","test6.testOut"},
                {"test7.in","test7.amirOut","test7.testOut"},
                {"test8.in","test8.amirOut","test8.testOut"},
                {"test9.in","test9.amirOut","test9.testOut"},
                {"test10.in","test10.amirOut","test10.testOut"},
                {"test11.in","test11.amirOut","test11.testOut"},
                {"test12.in","test12_a.amirOut","test12.testOut"},
                {"test13.in","test13.amirOut","test13.testOut"},
                {"test14.in","test14.amirOut","test14.testOut"},
                {"test15.in","test15_b.amirOut","test15.testOut"},
                {"test16.in","test16.amirOut","test16.testOut"},
                {"test17.in","test17.amirOut","test17.testOut"},
                {"test18.in","test18.amirOut","test18.testOut"},
        });
    }

    public EndToEndTests(String piecesInputFile, String amirOutputFile, String testOutputFile){
        this.piecesInputFile=pathToFilesFolder + piecesInputFile;
        this.amirOutputFile=pathToFilesFolder + amirOutputFile;
        this.testOutputFile=pathToFilesFolder + testOutputFile;
    }

    @Test
    public void testAmirFiles() throws IOException
    {
        PuzzleManager puzzleManager = new PuzzleManager(piecesInputFile,testOutputFile);
        puzzleManager.solvePuzzle();
        File testOutput = new File(testOutputFile);
        File amirOutput = new File(amirOutputFile);
        assertTrue("The files differ!", FileUtils.contentEquals(amirOutput, testOutput));
    }


}
