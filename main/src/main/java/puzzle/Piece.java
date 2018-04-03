package puzzle;

import java.util.HashMap;
/**
 * 
 * @author Guy Bitan
 *
 */
public class Piece implements Comparable<Piece>
{

	public static int numOfElements = 0;
	private int id;
	private HashMap<EnumSides, Integer> piece = new HashMap<EnumSides, Integer>();

	public Piece(int id, HashMap<EnumSides, Integer> piece)
	{
		this.id = id;
		this.piece = piece;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getBottom()
	{
		return piece.get(EnumSides.BOTTOM);
	}

	public int getTop()
	{
		return piece.get(EnumSides.TOP);
	}

	public int getLeft()
	{
		return piece.get(EnumSides.LEFT);
	}

	public int getRight()
	{
		return piece.get(EnumSides.RIGHT);
	}

	@Override
	public int compareTo(Piece piece)
	{
		return getId().compareTo(piece.getId());
	}
}
