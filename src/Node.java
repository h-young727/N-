public class Node implements Comparable<Node>
{
    private final int rowIndex;
    private final int columnIndex;
    private final int[] location;
    private int f, g, h;
    private final boolean pathable;
    private Node parent;
    private final String image;

    public Node(int rowIndex, int columnIndex, boolean pathable, String image)
    {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        location = new int[]{rowIndex, columnIndex};
        this.pathable = pathable;
        this.image = image;
    }

    public int getRowIndex()
    {
        return rowIndex;
    }

    public int getColumnIndex()
    {
        return columnIndex;
    }

    public int[] getLocation()
    {
        return location;
    }

    public int getG()
    {
        return g;
    }

    public boolean isPathable()
    {
        return pathable;
    }

    public String getImage()
    {
        return image;
    }

    public Node getParent()
    {
        return parent;
    }

    public void setF()
    {
        f = g + h;
    }

    public void setG(int g)
    {
        this.g = g;
    }

    public void setH(int h)
    {
        this.h = h;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Node)
        {
            return rowIndex == ((Node) o).getRowIndex() && columnIndex == ((Node) o).getColumnIndex();
        }

        return false;
    }

    @Override
    public int compareTo(Node n)
    {
        return f - n.f;
    }
}
