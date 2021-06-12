import java.util.ArrayList;

public class CourseSchedule {
    private class Graph {
        int node;
        ArrayList<Graph> children;
        private Graph (int nodeValue) {
            node = nodeValue;
            children = new ArrayList<>();
        }
        private void addChildren(int childrenValue) {
            children.add(new Graph(childrenValue));
        }
        private ArrayList<Graph> getChildren() {
            return children;
        }
        private int getNode() {
            return node;
        }
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Graph> allGraphs = new ArrayList<>();
        for (int i = 0; i < prerequisites.length; i++) {
            Graph currGraph = getGraph(allGraphs, prerequisites[i][0]);
            int pos = allGraphs.indexOf(currGraph);
            currGraph.addChildren(prerequisites[i][1]);
            if (pos != -1) {
                allGraphs.set(pos, currGraph);
            } else {
                allGraphs.add(currGraph);
            }
        }
        for (int i = 0; i < allGraphs.size(); i++) {
            boolean[] visited = new boolean[numCourses];
            if (checkForCycle(allGraphs, allGraphs.get(i), visited)) {
                return false;
            }
        }
        return true;
    }

    public Graph getGraph(ArrayList<Graph> allGraphs, int numToFind) {
        for (Graph graph : allGraphs) {
            if (graph.node == numToFind) {
                return graph;
            }
        }
        return new Graph(numToFind);
    }

    public boolean checkForCycle(ArrayList<Graph> allGraphs,
                                 Graph currGraph, boolean[] visited) {
        if (visited[currGraph.getNode()] == true) {
            return true;
        }
        visited[currGraph.getNode()] = true;
        boolean containsCycle = false;
        for (int i = 0; i < currGraph.getChildren().size(); i++) {
            containsCycle = checkForCycle(allGraphs,
                    currGraph.getChildren().get(i), visited) == true
                    ? true : containsCycle;

        }
        return containsCycle;
    }
}
