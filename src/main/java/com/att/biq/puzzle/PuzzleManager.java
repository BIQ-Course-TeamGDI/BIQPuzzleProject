package com.att.biq.puzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.att.biq.puzzle.infra.FileManager;

public class PuzzleManager {

	private String inputPiecesFile;
	private String outPutFile;
	private ArrayList<Piece> pieces;
	AtomicBoolean hasSolution = new AtomicBoolean(false);
	private boolean isRotate;
	private int numOfThreads = -1;
	AnalyzeInputs analyzeInputs = null;
	int threadsCount = 0;

	public PuzzleManager(String inputPiecesFile, String outPutFile) {
		this.inputPiecesFile = inputPiecesFile;
		this.outPutFile = outPutFile;
		pieces = new ArrayList<>();
	}

	public PuzzleManager(String inputPiecesFile, String outPutFile, boolean isRotate) {
		this.inputPiecesFile = inputPiecesFile;
		this.outPutFile = outPutFile;
		this.isRotate = isRotate;
		pieces = new ArrayList<>();
	}

	public PuzzleManager(String inputPiecesFile, String outPutFile, int numthreads) {
		this.inputPiecesFile = inputPiecesFile;
		this.outPutFile = outPutFile;
		this.numOfThreads = numthreads;
		pieces = new ArrayList<>();
	}

	public PuzzleManager(String inputPiecesFile, String outPutFile, boolean isRotate, int numthreads) {
		this.inputPiecesFile = inputPiecesFile;
		this.outPutFile = outPutFile;
		this.isRotate = isRotate;
		this.numOfThreads = numthreads;
		pieces = new ArrayList<>();
	}

	public void solvePuzzle() throws IOException, InterruptedException {

		if (numOfThreads <= 1)
			solvePuzzleRegular();
		else
			solvePuzzleByThreads(numOfThreads);

	}

	public void solvePuzzleRegular() throws IOException {
		ArrayList<Integer> rows = readAnalyzeAndGetPossibleSolutionRows(inputPiecesFile);
		for (Integer row : rows) {
			Puzzle puzzle = new Puzzle(pieces, row);
			puzzle.solve();
			if (!puzzle.getSolution().equals(null)) {
				puzzle.saveSolution2File(outPutFile);
				return;
			}
		}
	}

	public void solvePuzzleByThreads(int numOfThreads) throws IOException, InterruptedException {
		boolean waitForThread = true;
		Puzzle puzzle = null;
		
		ArrayList<Integer> rows = readAnalyzeAndGetPossibleSolutionRows(inputPiecesFile);

		// if there are errors - write to to output file
		if (analyzeInputs.getErrorsList().size() != 0)
			new Puzzle(pieces).saveSolution2File(outPutFile);
		else {
			threadsCount = ((numOfThreads > rows.size()) ? rows.size() : numOfThreads);
			System.out.println("Number of threads that will run for solution is: " + threadsCount);
			ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadsCount);
			for (int row : rows) {
				puzzle = new Puzzle(pieces, row);
				executor.execute(puzzle);
			}
			executor.shutdown();
			while (waitForThread) {

				executor.awaitTermination(5, TimeUnit.MINUTES);
				if (!hasSolution.getAndSet(true)) {

					puzzle.saveSolution2File(outPutFile);
				}
				if (puzzle.iSolved) {
					waitForThread = false;

				}

			}
		}
	}

	/*public void solvePuzzleByThreads1(int numthreads) throws IOException, InterruptedException {
		Puzzle p = null;
		ArrayList<Integer> rows = readAnalyzeAndGetPossibleSolutionRows(inputPiecesFile);
		// run threads only if no errors...
		int threadsCount = ((numthreads > rows.size()) ? rows.size() : numthreads);
		System.out.println("Number of threads that will run for solution is: " + threadsCount);
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadsCount);
		Puzzle[] puzzles = new Puzzle[threadsCount];
		int i = 0;
		for (int row : rows) {
			puzzles[i] = new Puzzle(pieces, row);
			executor.execute(puzzles[i]);
			i++;
		}
		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.MINUTES);
		for (int j = 0; j < puzzles.length; j++) {
			if (puzzles[j].getSolution() != null)
				p = puzzles[j];
			break;
		}

		if (p.getSolution() != null && !hasSolution.getAndSet(true) && executor.getCompletedTaskCount() >= 1) {
			p.saveSolution2File(outPutFile);
		}
	}*/

	// read elements from file, analyze it and return possible solution rows or null
	public ArrayList<Integer> readAnalyzeAndGetPossibleSolutionRows(String inputPiecesFile) throws IOException {
		FileManager fileManager = new FileManager(inputPiecesFile);
		fileManager.setPiecesFromFile();
		if (fileManager.getAllErrors().size() != 0) {
			fileManager.printErrorsToFile(outPutFile);
			return null;
		}
		pieces = fileManager.getPieces();
		analyzeInputs = new AnalyzeInputs(fileManager.getPieces());
		analyzeInputs.analyzePicesList();
		if (fileManager.getAllErrors().size() != 0) {
			fileManager.printErrorsToFile(outPutFile);
			return null;
		}

		return analyzeInputs.getSolutionPossibleRows();
	}

}
