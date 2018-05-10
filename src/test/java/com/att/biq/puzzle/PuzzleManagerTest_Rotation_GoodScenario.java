package com.att.biq.puzzle;

import com.att.biq.puzzle.utility.ValidatePuzzleSolution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PuzzleManagerTest_Rotation_GoodScenario
{
	private String pathToFilesFolder = "./resources/PuzzleManagerTest_GoodScenarioFiles/";
	private String pathToSolutionFilesFolder = "./resources/PuzzleManagerTest_GoodScenarioFiles/Rotation_good_solution/";
	private String piecesInputFile;
	private String testOutputFile;

	@Parameterized.Parameters
	public static Collection<Object[]> data()
	{
		return Arrays.asList(new Object[][]
		{
				{ "test1.in", "test1.testOut" },
				{ "test2.in", "test2.testOut" },
				{ "test9.in", "test9.testOut" },
				{ "test10.in", "test10.testOut" },
				{ "test11.in", "test11.testOut" },
				{ "test12.in", "test12.NorRotationTestOut" },
				{ "test13.in", "test13.testOut" },
				{ "test14.in", "test14.testOut" },
				{ "test15.in", "test15.testOut" },
				{ "test16.in", "test16.testOut" },
				{ "test17.in", "test17.testOut" }, });
	}

	public PuzzleManagerTest_Rotation_GoodScenario(String piecesInputFile, String testOutputFile)
	{
		this.piecesInputFile = pathToFilesFolder + piecesInputFile;
		this.testOutputFile = pathToSolutionFilesFolder + testOutputFile;
	}

	@Test
	public void testGoodFilesSolution() throws IOException
	{
		PuzzleManager puzzleManager = new PuzzleManager(piecesInputFile, testOutputFile,true,4);
		puzzleManager.solvePuzzle();
		assertTrue(ValidatePuzzleSolution.validate(piecesInputFile, testOutputFile));
	}
}
