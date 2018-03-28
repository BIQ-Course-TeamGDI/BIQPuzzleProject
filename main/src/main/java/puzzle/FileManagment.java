package puzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 
 * @author Guy Bitan
 *
 */
public class FileManagment
{
	static final int SIDES = 5;
	static final String NUM_ELEMENTS_STR = "NumElements=";
	static final String SEPARATOR = " ";
	private File fileInput;
	private int numElements = 0;
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	private ErrorsManagment errors = new ErrorsManagment();

	public FileManagment(String inputFilePath)
	{
		fileInput = new File(inputFilePath);

	}

	public void loadAsText() throws IOException, FileManagmentException
	{
		if (fileInput.exists())
		{
			try (FileInputStream fis = new FileInputStream(fileInput))
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String sCurrentLine = br.readLine();
				while ((sCurrentLine = br.readLine()) != null)
				{
					if (!sCurrentLine.startsWith("#") && isNumElementsValid(sCurrentLine))
					{
						int[] piece = getLineAsPiece(sCurrentLine);

						if (piece != null)
						{
							addPiece(piece);
						}

					}
				}
			}
		}
		else
		{
			errors.addError(ErrorsManagment.ERROR_MISSING_IN_FILE + fileInput.getAbsolutePath());
			throw new FileNotFoundException(ErrorsManagment.ERROR_MISSING_IN_FILE + fileInput.getAbsolutePath());
		}
	}

	private boolean isNumElementsValid(String sCurrentLine)
	{
		if (numElements == 0)
		{
			if (sCurrentLine.startsWith(NUM_ELEMENTS_STR))
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
						errors.addError(e.getMessage());
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

	private int[] getLineAsPiece(String sCurrentLine)
	{
		int[] goodPiece = new int[SIDES];

		if (sCurrentLine.startsWith(NUM_ELEMENTS_STR))
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

	public ArrayList<Piece> getPieces()
	{
		if (isIdsAndSizeValids())
		{
			Collections.sort(pieces);
			return pieces;
		}
		else
		{
			errors.printErrors();
			return null;
		}
	}

	public boolean isIdsAndSizeValids()
	{
		String missingElements = "";
		String wrongElements = "";
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
					missingElements += (i + 1) + ",";
				}
			}
		}
		else
		{
			errors.addError(ErrorsManagment.ERROR_NUM_ELEMENTS_NOT_EQUAL_TO_ACTUAL_PIECES + numElements
					+ " and actual is:" + pieces.size());
			status = false;
		}
		if (!missingElements.isEmpty())
		{
			status = false;
			missingElements = missingElements.substring(0, missingElements.lastIndexOf(","));
			errors.addError(ErrorsManagment.ERROR_MISSING_ELEMENTS + missingElements);
		}
		if (!wrongElements.isEmpty())
		{
			status = false;
			wrongElements = wrongElements.substring(0, wrongElements.lastIndexOf(","));
			errors.addError(ErrorsManagment.ERROR_WRONG_ELEMENT_IDS + wrongElements);
		}
		return status;
	}
}
