public class Main {
	/* some helper functions to help you construct the two types of graphs */
	
	private static void connectDirectedVertices(Vertex sourceVertex, Vertex destinationVertex, double cost) {
		Edge e = new Edge(sourceVertex, destinationVertex, cost); /* edge has a cost */
		sourceVertex.addNeighbour(e); /* only direct the edge from the source vertex to destination (but not vice-versa) */
	}

	private static void connectUndirectedVertices(Vertex v1, Vertex v2) {
		Edge e = new Edge(v1, v2, 0); /* the weight of these edges are all set to 0 and can be ignored */
		v1.addNeighbour(e);
		v2.addNeighbour(e); /* add the edge to both vertices essentially making the edge function in both directions */
	}

	public static void main(String[] args) {

		// TASK 1: Shortest path test
		Vertex vertexL = new Vertex("L");
		Vertex vertexM = new Vertex("M");
		Vertex vertexN = new Vertex("N");
		Vertex vertexO = new Vertex("O");
		Vertex vertexP = new Vertex("P");

		connectDirectedVertices(vertexL, vertexN, 10);
		connectDirectedVertices(vertexL, vertexM, 17);
		connectDirectedVertices(vertexN, vertexM, 5);
		connectDirectedVertices(vertexN, vertexO, 9);
		connectDirectedVertices(vertexN, vertexP, 11);
		connectDirectedVertices(vertexM, vertexO, 1);
		connectDirectedVertices(vertexO, vertexP, 6);

		WeightedDirectedGraph directedGraph = new WeightedDirectedGraph();
		directedGraph.addVertex(vertexL);
		directedGraph.addVertex(vertexM);
		directedGraph.addVertex(vertexN);
		directedGraph.addVertex(vertexO);
		directedGraph.addVertex(vertexP);
		System.out.println("showing path");
		System.out.println("Shortest Path from " + vertexL.getName() + " to " + vertexP.getName() + " : " + directedGraph.getShortestPath(vertexL, vertexP));

		/* Expected output
		   Shortest Path from L to P : [L, N, P]
		*/

		// TASK 2 Constructing the undirected graph from page 430 in the text book Figure 8.16 (a)
		Vertex vertexA = new Vertex("A");
		Vertex vertexB = new Vertex("B");
		Vertex vertexC = new Vertex("C");
		Vertex vertexD = new Vertex("D");
		Vertex vertexE = new Vertex("E");
		Vertex vertexF = new Vertex("F");
		Vertex vertexG = new Vertex("G");
		Vertex vertexH = new Vertex("H");
		Vertex vertexI = new Vertex("I");
		Vertex vertexJ = new Vertex("J");
		Vertex vertexK = new Vertex("K");

		UnweightedUndirectedGraph undirectedGraph = new UnweightedUndirectedGraph();
		connectUndirectedVertices(vertexA, vertexC);
		connectUndirectedVertices(vertexA, vertexF);
		connectUndirectedVertices(vertexA, vertexD);
		connectUndirectedVertices(vertexC, vertexF);
		connectUndirectedVertices(vertexF, vertexD);
		connectUndirectedVertices(vertexD, vertexB);
		connectUndirectedVertices(vertexD, vertexE);
		connectUndirectedVertices(vertexB, vertexE);
		connectUndirectedVertices(vertexB, vertexG);
		connectUndirectedVertices(vertexB, vertexH);
		connectUndirectedVertices(vertexG, vertexH);
		connectUndirectedVertices(vertexG, vertexK);
		connectUndirectedVertices(vertexH, vertexK);
		connectUndirectedVertices(vertexH, vertexI);
		connectUndirectedVertices(vertexI, vertexJ);
		undirectedGraph.addVertex(vertexA);
		undirectedGraph.addVertex(vertexB);
		undirectedGraph.addVertex(vertexC);
		undirectedGraph.addVertex(vertexD);
		undirectedGraph.addVertex(vertexE);
		undirectedGraph.addVertex(vertexF);
		undirectedGraph.addVertex(vertexG);
		undirectedGraph.addVertex(vertexH);
		undirectedGraph.addVertex(vertexI);
		undirectedGraph.addVertex(vertexJ);
		undirectedGraph.addVertex(vertexK);

		System.out.println("Articulation points: " + undirectedGraph.getArticulationPoints());

		/* Expected output
		   Articulation points: [B, D, H, I]
		 */
	}
}