package puzzle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManagment
{

	public void loadAsText(FileInputStream fis) throws IOException
	{
		try (BufferedReader br = new BufferedReader(new InputStreamReader(fis)))
		{
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null)
			{
				if (sCurrentLine.startsWith("#"))
				{
					
				}	
				
			}

		}
	}
}
