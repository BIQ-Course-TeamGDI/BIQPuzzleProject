package com.att.biq.puzzle.infra;

import java.io.IOException;

import com.att.biq.puzzle.PuzzleManager;

/**
 * 
 * @author Guy Bitan
 *
 */
public class Main
{
	public static String inputFile = "";
	public static String outputFile = "";
	public static boolean isRotate = false;
	public static int numOfThreads = 1;

	public static void main(String[] a) throws IOException
	{
		if (a.length < 2)
		{
			showHelp();
		}
		else
		{
			for (int i = 0; i < a.length; i++) // Loop around the arguments
			{
				if (a[i].equalsIgnoreCase("--help")) // Check each argument
				{
					showHelp();
				}
				else if (a[i].equalsIgnoreCase("-input"))
				{
					inputFile = a[++i];
				}
				else if (a[i].equalsIgnoreCase("-output"))
				{
					outputFile = a[++i];
				}
				else if (a[i].equalsIgnoreCase("-rotate"))
				{
					isRotate = true;
				}
				else if (a[i].equalsIgnoreCase("-threads"))
				{
					numOfThreads = Integer.parseInt(a[++i]);
				}
			}
			PuzzleManager puzzleManager = new PuzzleManager(inputFile, outputFile, isRotate, numOfThreads);
			puzzleManager.solvePuzzle();
		}
	}

	private static void showHelp()
	{
		System.out.println("BIQ Puzzle Project");
		System.out.println("");
		System.out.println("Usage:");
		System.out.println("\t-help\n\t\tThis help");
		System.out.println("");
		System.out.println("\t-input <input_file_name>");
		System.out.println("\t\tSpecify the input file position of the puzzle file to solve (mandatory)");
		System.out.println("");
		System.out.println("\t-output <output_file_name>");
		System.out.println("\t\tSpecify the output file position of the puzzle (for solution or errors)");
		System.out.println("");
		System.out.println("\t-rotate");
		System.out.println("\t\tOptional - indicating whether the puzzle pieces can be rotated");
		System.out.println("");
		System.out.println("\t-threads <num_threads>");
		System.out.println("\t\tOptional - indicating number of threads to use (including main)\r\n");
		System.out.println("");
		System.exit(1);
	}
}
