package com.att.biq.puzzle;

import java.io.IOException;

import org.junit.Test;

import com.att.biq.puzzle.infra.FileManager;

public class MyTests
{
	@Test
	public void GetPiecesFromFileAndAnalayzeFileWithErrors() throws IOException
	{
		FileManager fileManager = new FileManager("./resources/FileManagerTestsFiles/test3.in");
		fileManager.setPiecesFromFile();
		fileManager.getPieces();
		fileManager.getAllErrors();
	}
}
