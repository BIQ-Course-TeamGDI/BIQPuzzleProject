package infra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import puzzle.Piece;

/**
 * 
 * @author Guy Bitan
 *
 */
public class FileManager extends ErrorsManagment
{
	private final int SIDES = 5;
	private final String NUM_OF_ELEMENTS_KEY = "NumElements=";
	private final String SEPARATOR = " ";
	private final String COMMENTLINE = "#";
	private File inputFilePath;
	private int numElements = 0;
	private ArrayList<Piece> pieces = new ArrayList<Piece>();

	public FileManager(String inputFilePath)
	{
		this.inputFilePath = new File(inputFilePath);
	}

	public ArrayList<Piece> getPices() throws IOException
	{
		if (inputFilePath.exists())
		{
			try (FileInputStream fis = new FileInputStream(inputFilePath))
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null)
				{
					if (!sCurrentLine.startsWith(COMMENTLINE) && isNumElementsValid(sCurrentLine))
					{
						if (!sCurrentLine.replaceAll(" ", "").startsWith(NUM_OF_ELEMENTS_KEY))
						{
							int[] piece = convertLineToArr(sCurrentLine);

							if (piece != null)
							{
								addPiece(piece);
							}
						}
					}
				}
			}
			return getAllPieces();
		}
		else
		{
			add(ERROR_MISSING_IN_FILE + inputFilePath.getAbsolutePath());
			return null;
		}
	}

	private boolean isNumElementsValid(String sCurrentLine) throws IOException
	{
		if (numElements == 0)
		{
			if (sCurrentLine.replaceAll(" ", "").startsWith(NUM_OF_ELEMENTS_KEY))
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
						numElements = Integer.parseInt(value);
						return true;
					}
					catch (Exception e)
					{
						add(e.getMessage());
						return false;
					}
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return true;
		}
	}

	private int[] convertLineToArr(String sCurrentLine)
	{
		int[] goodPiece = new int[SIDES];

		if (sCurrentLine.startsWith(NUM_OF_ELEMENTS_KEY))
		{
			return null;
		}
		else
		{
			String[] lineArr = sCurrentLine.split(SEPARATOR);
			if (lineArr.length == 5)
			{
				int i = 0;
				for (String str : lineArr)
				{
					try
					{
						goodPiece[i++] = Integer.parseInt(str);
					}
					catch (Exception e)
					{
						return null;
					}
				}
			}
		}
		return goodPiece;
	}

	private void addPiece(int[] pieceArr)
	{
		int id = pieceArr[0];
		HashMap<EnumSides, Integer> pieceMap = new HashMap<EnumSides, Integer>();
		pieceMap.put(EnumSides.LEFT, pieceArr[1]);
		pieceMap.put(EnumSides.TOP, pieceArr[2]);
		pieceMap.put(EnumSides.RIGHT, pieceArr[3]);
		pieceMap.put(EnumSides.BOTTOM, pieceArr[4]);
		pieces.add(new Piece(id, pieceMap));
	}

	private ArrayList<Piece> getAllPieces()
	{
		if (isIdsAndSizeAreValids())
		{
			Collections.sort(pieces);
			return pieces;
		}
		else
		{
			return null;
		}
	}

	private boolean isIdsAndSizeAreValids()
	{
		String missingElements = "", wrongElements = "";
		boolean status = true;
		int[] arr = new int[numElements];

		if (pieces.size() == numElements)
		{
			for (int i = 0; i < numElements; i++)
			{
				int id = pieces.get(i).getId();

				if (id > numElements)
				{
					wrongElements += pieces.get(i).getId() + ",";
				}
				else
				{
					arr[id - 1] = 1;
				}
			}
			for (int i = 0; i < numElements; i++)
			{
				if (arr[i] != 1)
				{
					missingElements += i + ",";
				}
			}
		}
		else
		{
			add(ERROR_NUM_ELEMENTS_NOT_EQUAL_TO_ACTUAL_PIECES + numElements + " and actual is:" + pieces.size());
			status = false;
		}
		if (!missingElements.isEmpty())
		{
			status = false;
			missingElements = missingElements.substring(0, missingElements.lastIndexOf(","));
			add(ERROR_MISSING_ELEMENTS + missingElements);
		}
		if (!wrongElements.isEmpty())
		{
			status = false;
			wrongElements = wrongElements.substring(0, wrongElements.lastIndexOf(","));
			add(ERROR_WRONG_ELEMENT_IDS + wrongElements);
		}
		return status;
	}
}
