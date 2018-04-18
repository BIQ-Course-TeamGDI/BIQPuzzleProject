package com.att.biq.puzzle.infra;

import java.io.IOException;

import com.att.biq.puzzle.PuzzleManager;

public class Main
{

	public static void main(String[] args) throws IOException
	{
		PuzzleManager puzzleManager = new PuzzleManager(args[0], args[1]);
		puzzleManager.solvePuzzle();
	}
}
