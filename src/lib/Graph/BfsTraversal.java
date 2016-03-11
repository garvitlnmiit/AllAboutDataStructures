/**
 * @Title: BFS Graph traversal
 * @GFG: http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 * @Author: Garvit Sharma <garvits45@gmail.com>
 **/

import java.util.LinkedList;
import java.util.Iterator;

// Graph class structure
class Graph
{
	// Number of vertices
	private int V;

	// Adjecency lists
	private LinkedList<Integer>[] adj;

	// Graph contructor
	Graph(int numOfVertices) {
		V = numOfVertices;

		adj = new LinkedList[V];

		for(int iter=0; iter<V; iter++) {
			adj[iter] = new LinkedList<Integer>();
		}
	}

	// Function to add an edge into an graph
	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	// Prints BFS traversal from a given source S
	void bfsTraversal(int source) {

		int cur;

		// To mark vertices visited.
		boolean visited[] = new boolean[V];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the source node as visited and enqueue it
		visited[source] = true;

		queue.add(source);

		// Iteration
		while (queue.size() != 0) {

			// Dequeue
			cur = queue.poll();

			System.out.println(cur + " ");

			Iterator<Integer> iter = adj[cur].listIterator();
			while (iter.hasNext()) {
				cur = iter.next();
				if (!visited[cur]) {
					visited[cur] = true;
					queue.add(cur);
				}
			}
		}
	}
}

public class BfsTraversal
{
	public static void main(String[] args) {

		// Creating graph 'g'
		Graph g = new Graph(4);
		g.addEdge(0,1);
		g.addEdge(0,2);
		g.addEdge(1,2);
		g.addEdge(2,0);
		g.addEdge(2,3);
		g.addEdge(3,3);

		System.out.println("Following is Breadth First Traversal "+
                           "(starting from vertex 2)");

		g.bfsTraversal(2);
	}
}