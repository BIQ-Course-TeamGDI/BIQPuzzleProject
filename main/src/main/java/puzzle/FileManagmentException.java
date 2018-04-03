package puzzle;

public class FileManagmentException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	String msg;

	public FileManagmentException(String msg)
	{
		this.msg = msg;
	}

}
