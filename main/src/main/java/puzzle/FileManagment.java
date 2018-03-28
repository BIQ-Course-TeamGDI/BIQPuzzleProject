package puzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileManagment
{
	private File fileInput;
	private int numElements = 0;
	private int[][] pieces;
	private int lineNumber = 0;

	public FileManagment(String inputFilePath)
	{
		fileInput = new File(inputFilePath);
	}

	public void loadAsText() throws IOException
	{
		if (fileInput.exists())
		{
			try (FileInputStream fis = new FileInputStream(fileInput))
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String sCurrentLine;
				sCurrentLine = br.readLine();
				if (isNumElementsValid(sCurrentLine))
				{
					pieces = new int[numElements][5];
					
					while ((sCurrentLine = br.readLine()) != null && !sCurrentLine.startsWith("#"))
					{
						if (isLineValid(sCurrentLine))
						{
							addPiece(sCurrentLine);
						}

					}
				}
				else
				{
					return;
				}

			}
		}
	}

	private boolean isLineValid(String sCurrentLine)
	{
		return true;
	}

	private boolean isNumElementsValid(String sCurrentLine)
	{
		if (sCurrentLine.startsWith("NumElements="))
		{
			String[] numElementsArr = sCurrentLine.split("=");
			String value = numElementsArr[1].trim();

			if (value.isEmpty())
			{
				return false;
			}
			else
			{
				try
				{
					this.numElements = Integer.parseInt(value);
				}
				catch (Exception e)
				{
					return false;
				}

			}

		}
		return true;
	}

	private void addPiece(int[] piece)
	{
		int[] piece;
		this.pieces[lineNumber] = piece;
	}

	public static void main(String[] args) throws IOException
	{
		FileManagment fileManagment = new FileManagment("input.txt");
		fileManagment.loadAsText();
	}

}
