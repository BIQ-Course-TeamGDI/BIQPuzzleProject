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
		PuzzleIndexer puzzleIndexer = new PuzzleIndexer(pieces,isRotate);
		for (Integer numOfRows : rows) {
			int numOfColumns = pieces.size()/numOfRows;
			Puzzle puzzle = new Puzzle(puzzleIndexer,numOfRows,numOfColumns);
			puzzle.solve();
			if (!puzzle.getSolution().equals(null)) {
				puzzle.saveSolution2File(outPutFile);
				return;
			}
		}
	}

	public void solvePuzzleByThreads(int numOfThreads) throws IOException, InterruptedException {
		boolean waitForThread = true;
		Puzzle puzzle=null;
		ArrayList<Integer> rows = readAnalyzeAndGetPossibleSolutionRows(inputPiecesFile);
//		rows.clear();
//		rows.add(1);
//		rows.add(2);
//		rows.add(4);

		threadsCount = ((numOfThreads > rows.size()) ? rows.size() : numOfThreads);
		System.out.println("Number of threads that will run for solution is: " + threadsCount);
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadsCount);
		PuzzleIndexer puzzleIndexer = new PuzzleIndexer(pieces,isRotate);
		for (int row : rows) {
			int numOfColumns = pieces.size()/row;
			puzzle = new Puzzle(puzzleIndexer,row,numOfColumns);
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
