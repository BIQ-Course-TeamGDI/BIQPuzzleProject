package testAmirFiles;


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
public class TestAmirFiles {

    private String pathToFilesFolder="./resources/amirFiles/";
    private String piecesInputFile;
    private String amirOutputFile;
    private String testOutputFile;

    @Parameterized.Parameters
    public static Collection<Object[]> data (){
        return Arrays.asList(new Object[][] {
                {"test1.in","test1.out","test1.testOut"},
                {"test2.in","test2.out","test2.testOut"},
                {"test3.in","test3.out","test3.testOut"},
                {"test4.in","test4.out","test4.testOut"},
                {"test5.in","test5.out","test5.testOut"},
                {"test6.in","test6.out","test6.testOut"},
                {"test7.in","test7.out","test7.testOut"},
                {"test8.in","test8.out","test8.testOut"},
                {"test9.in","test9.out","test9.testOut"},
                {"test10.in","test10.out","test10.testOut"},
                {"test11.in","test11.out","test11.testOut"},
                {"test12.in","test12_a.out","test12.testOut"},
                {"test13.in","test13.out","test13.testOut"},
                {"test14.in","test14.out","test14.testOut"},
                {"test15.in","test15_b.out","test15.testOut"},
                {"test16.in","test16.out","test16.testOut"},
                {"test17.in","test17.out","test17.testOut"},
                {"test18.in","test18.out","test18.testOut"},
        });
    }

    public TestAmirFiles(String piecesInputFile, String amirOutputFile, String testOutputFile){
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
