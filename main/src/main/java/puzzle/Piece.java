package puzzle;

import java.util.HashMap;

enum EnumSides
{
    TOP, BOTTOM, RIGHT, LEFT;
};
public class Piece {

    public static int numOfElements = 0;
    private int id;
    private HashMap<EnumSides, Integer> pice = new HashMap<EnumSides, Integer>();

    public Piece(int id, HashMap<EnumSides, Integer> shape)
    {
        super();
        this.id = id;
        this.pice = shape;
        numOfElements++;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getBottom()
    {
        return pice.get(EnumSides.BOTTOM);
    }

    public int getTop()
    {
        return pice.get(EnumSides.TOP);
    }

    public int getLeft()
    {
        return pice.get(EnumSides.LEFT);
    }

    public int getRight()
    {
        return pice.get(EnumSides.RIGHT);
    }
}
