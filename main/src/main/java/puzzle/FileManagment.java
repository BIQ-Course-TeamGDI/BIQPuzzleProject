package puzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class FileManagment implements FileParamsInterface, ErrorsInterface
{
	private File fileInput;
	private int numElements = 0;
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	final static Logger logger = Logger.getLogger(FileManagment.class);
	private ArrayList<String> errorsList = new ArrayList<String>();

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
			errorsList.add(ERROR_MISSING_IN_FILE + fileInput.getAbsolutePath());
		}
		printErrors();
	}

	private void printErrors()
	{
		for (String error : errorsList)
		{
			logger.error(error);
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
						errorsList.add(ERROR_NUM_ELEMENTS + sCurrentLine);
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
			return pieces;
		}
		else
		{
			return null;
		}
	}

	public boolean isIdsAndSizeValids()
	{
		StringBuffer missingElementsBuffer = new StringBuffer();
		boolean status = true;
		if (pieces.size() == numElements)
		{
			Collections.sort(pieces);
			for (int i = 0; i < numElements; i++)
			{
				if (pieces.get(i).getId() != i + 1)
				{
					status = false;
					missingElementsBuffer.append(i + 1 + ",");
				}
			}
			status = true;
		}
		else
		{
			status = false;
		}
		if (missingElementsBuffer.length() < 0)
		{
			errorsList.add(ErrorsInterface.ERROR_MISSING_ELEMENTS + missingElementsBuffer);
		}
		return status;
	}

	public static void main(String[] args) throws IOException
	{
		FileManagment fileManagment = new FileManagment("input.txt");
		fileManagment.loadAsText();
		fileManagment.getPieces();
	}
}
