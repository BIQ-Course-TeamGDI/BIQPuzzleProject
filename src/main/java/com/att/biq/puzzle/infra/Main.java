package com.att.biq.puzzle.infra;

import java.io.IOException;
import com.att.biq.puzzle.PuzzleManager;

/**
 * 
 * @author Guy Bitan
 *
 */
public class Main {
	public static String inputFile = "C:\\BiqPassoverProject\\t50.in";
	public static String outputFile = "C:\\BiqPassoverProject\\out.out";
	public static boolean isRotate = false;
	public static int numOfThreads = 3;

	public static void main(String[] a) throws IOException, InterruptedException {
		PuzzleManager puzzleManager = new PuzzleManager(inputFile, outputFile, isRotate, numOfThreads);
		puzzleManager.solvePuzzle();

	}

}
