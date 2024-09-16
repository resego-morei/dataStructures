import java.util.ArrayList;
import java.util.List;

public class UnweightedUndirectedGraph {

	private List<Vertex> verticesList; //contains all vertices in this graph
	private List<Vertex> articulationPoints; // add articulation points for this graph to this list

	public UnweightedUndirectedGraph() {
		this.verticesList = new ArrayList<>();
		this.articulationPoints = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		this.verticesList.add(vertex);
	}

	/*Sorts the articulationPoints list based on the names of the vertices. Do not modify this*/
	public void sortArticulationPoints() {
		if (articulationPoints != null)
			articulationPoints.sort(new VertexNameSorter());
	}

	////// Implement the methods below this line //////

	public List<Vertex> getArticulationPoints() {
		/* 
		  Your code to add articulation points to the list goes here. 
		You may add helper functions and fields. No extra imports or classes.
		 */
		
		sortArticulationPoints(); // sort list before returning
		return articulationPoints;
	}

}
