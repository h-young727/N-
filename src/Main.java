import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while (run)
        {
            System.out.print("Enter grid size: ");
            int n = scanner.nextInt();
            State state = new State(n);
            System.out.println("\nGenerating world...");
            state.printWorld(null, null, null);

            System.out.print("\nEnter 0 through " + (n - 1) + " for start row index: ");
            int startRowIndex = scanner.nextInt();

            System.out.print("Enter 0 through " + (n - 1) + " for start column index: ");
            int startColumnIndex = scanner.nextInt();

            System.out.print("Enter 0 through " + (n - 1) + " for goal row index: ");
            int goalRowIndex = scanner.nextInt();

            System.out.print("Enter 0 through " + (n - 1) + " for goal column index: ");
            int goalColumnIndex = scanner.nextInt();

            Node start = new Node(startRowIndex, startColumnIndex, true, "\uD83D\uDC2D");
            Node goal = new Node(goalRowIndex, goalColumnIndex, true, "\uD83E\uDDC0");

            state.insertNode(start);
            state.insertNode(goal);
            System.out.println();
            state.printWorld(null, start, goal);

            System.out.println("\nGenerating path...");

            ArrayList<Node> path = generatePathToGoal(state, start, goal);

            if (path != null)
            {
                System.out.println("\nPath found.");
                state.printWorld(path, start, goal);
            }
            else
            {
                System.out.println("\nPath not found.");
            }

            System.out.print("\nGenerate new world? (1 - Yes | 0 - No): ");
            int choice = scanner.nextInt();

            if (choice != 1)
            {

                run = false;
            }

            System.out.println();
        }

        System.out.println("Terminating.");
        System.exit(0);
    }

    private static ArrayList<Node> generatePathToGoal(State state, Node start, Node goal)
    {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        HashMap<int[], Node> closedList = new HashMap<>();

        start.setH(calculateH(start, goal));
        start.setF();
        openList.add(start);

        while (!openList.isEmpty())
        {
            Node current = openList.poll();

            if (current.equals(goal))
            {
                return generatePathFromStart(current);
            }

            closedList.put(current.getLocation(), current);
            ArrayList<Node> neighbors = state.generateNeighbors(current);

            for (Node neighbor : neighbors)
            {
                if (closedList.containsKey(neighbor.getLocation()))
                {
                    continue;
                }

                if (openList.contains(neighbor))
                {
                    int originalG = current.getG() + 10;
                    neighbor.setG(calculateG(neighbor));

                    if (originalG < neighbor.getG())
                    {
                        openList.remove(neighbor);
                        neighbor.setParent(current);
                        neighbor.setG(calculateG(neighbor));
                        neighbor.setF();

                        openList.add(neighbor);
                    }
                }
                else
                {
                    neighbor.setParent(current);
                    neighbor.setG(calculateG(neighbor));
                    neighbor.setH(calculateH(neighbor, goal));
                    neighbor.setF();

                    openList.add(neighbor);
                }
            }
        }

        return null;
    }

    private static ArrayList<Node> generatePathFromStart(Node n)
    {
        ArrayList<Node> result = new ArrayList<>();

        while (n.getParent() != null)
        {
            result.add(0, n);
            n = n.getParent();
        }

        result.add(0, n);

        return result;
    }

    private static int calculateG(Node n)
    {
        int result = 0;

        while (n.getParent() != null)
        {
            result += 10;
            n = n.getParent();
        }

        return result;
    }

    private static int calculateH(Node current, Node goal)
    {
        return 10 * (Math.abs(current.getRowIndex() - goal.getRowIndex()) + Math.abs(current.getColumnIndex() - goal.getColumnIndex()));
    }
}