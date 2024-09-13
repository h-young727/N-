import java.util.ArrayList;

public class State
{
    private final int n;
    private final Node[][] world;

    public State(int n)
    {
        this.n = n;
        world = new Node[n][n];

        for (int rowIndex = 0; rowIndex < n; rowIndex++)
        {
            for (int columnIndex = 0; columnIndex < n; columnIndex++)
            {
                if ((int) (Math.random() * 10) == 0)
                {
                    world[rowIndex][columnIndex] = new Node(rowIndex, columnIndex, false, "\uD83E\uDEA4");
                }
                else
                {
                    world[rowIndex][columnIndex] = new Node(rowIndex, columnIndex, true, "\uD83D\uDD73️");
                }
            }
        }
    }

    public void insertNode(Node n)
    {
        world[n.getRowIndex()][n.getColumnIndex()] = n;
    }

    public Node getNode(int rowIndex, int columnIndex) {
        if (!(rowIndex < 0 || rowIndex > n - 1 || columnIndex < 0 || columnIndex > n - 1))
        {
            return world[rowIndex][columnIndex];
        }

        return null;
    }

    public ArrayList<Node> generateNeighbors(Node n)
    {
        ArrayList<Node> neighbors = new ArrayList<>();
        int[][] possibleLocations = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int[] possibleLocation : possibleLocations)
        {
            Node possibleNeighbor = getNode(n.getRowIndex() + possibleLocation[0], n.getColumnIndex() + possibleLocation[1]);
            if (possibleNeighbor != null && possibleNeighbor.isPathable())
            {
                neighbors.add(possibleNeighbor);
            }
        }

        return neighbors;
    }

    public void printWorld(ArrayList<Node> path, Node start, Node goal)
    {
        for (int rowIndex = 0; rowIndex < n; rowIndex++)
        {
            for (int columnIndex = 0; columnIndex < n; columnIndex++)
            {
                Node current = getNode(rowIndex, columnIndex);
                if (path != null)
                {
                    if (path.contains(current) && !current.equals(start) && !current.equals(goal))
                    {
                        System.out.print("\uD83D\uDC01");
                    }
                    else
                    {
                        System.out.print(current.getImage());
                    }
                }
                else
                {
                    System.out.print(current.getImage());
                }
            }
            System.out.println();
        }
    }
}
