import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeightedDirectedGraph {

	private List<Vertex> verticesList; //contains all vertices in this graph

	public WeightedDirectedGraph() {
		this.verticesList = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		this.verticesList.add(vertex);
	}

	////// Implement the methods below this line //////

	public List<Vertex> getShortestPath(Vertex sourceVertex, Vertex targetVertex) {
		// your code goes here
		List<Vertex> notvisited = new ArrayList<Vertex>();
		Vertex min = null;
		for(Vertex item:verticesList){
			item.setDistance(Double.POSITIVE_INFINITY);
			item.setPredecessor(null);
			notvisited.add(item);
		}
		sourceVertex.setDistance(0);
		while(!notvisited.isEmpty()){
			for(Vertex item:notvisited){
				if(min == null){
					min = item;
				}
				else{
					if(item.getDistance() < min.getDistance()){
						min = item;
					}
				}
			}
			notvisited.remove(min);
			for(Edge item:min.getAdjacenciesList()){
				if(item.getOtherVertex(min).isVisited()==false){
					double newDist = min.getDistance() + item.getWeight();
					if(newDist< item.getOtherVertex(min).getDistance()){
						item.getOtherVertex(min).setDistance(newDist);
						item.getOtherVertex(min).setPredecessor(min);
					}
				}
			}
			min= null;
		}
		Vertex curr = targetVertex;
		List<Vertex> path = new ArrayList<Vertex>();
		while(curr != null){
			path.add(curr);
			curr = curr.getPredecessor();
		}
		Collections.reverse(path);
		if(path.size()<2){
			path.clear();
		}
		return path;
	}
}