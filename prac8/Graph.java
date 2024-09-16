// Name:
// Student number:
import java.util.ArrayList;
import java.util.List;
 
public class Graph {
 
	private List<Vertex> verticesList;

	public Graph() {
		this.verticesList = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		this.verticesList.add(vertex);
	}

	public void reset() {
		for(Vertex vertex : verticesList) {
			vertex.setVisited(false);
			vertex.setPredecessor(null);
			vertex.setDistance(Double.POSITIVE_INFINITY);
		}
	}

	////// Implement the methods below this line //////

	public List<Vertex> getShortestPath(Vertex sourceVertex, Vertex targetVertex) {
		
		// Your code here
		if(sourceVertex == targetVertex){
			List<Vertex> path = new ArrayList<Vertex>();
			sourceVertex.setDistance(0);
			path.add(sourceVertex);
			return path;
		}
		else{
			List<Vertex> toBeChecked = new ArrayList<Vertex>();
			for(Vertex item:verticesList){
				item.setDistance(Double.POSITIVE_INFINITY);
				item.setPredecessor(null);
			}
			sourceVertex.setDistance(0);
			toBeChecked.add(sourceVertex);
			Vertex curr = null;
			while(!toBeChecked.isEmpty()){
				curr = toBeChecked.remove(0);
				for(Edge v:curr.getAdjacenciesList()){
					double newDist = curr.getDistance() + v.getWeight();
					if(newDist< v.getEndVertex().getDistance()){
						v.getEndVertex().setDistance(newDist);
						v.getEndVertex().setPredecessor(curr);
						if(!toBeChecked.contains(v.getEndVertex())){
							toBeChecked.add(v.getEndVertex());
						}
					}
				}
			}
			curr = targetVertex;
			List<Vertex> path = new ArrayList<Vertex>();
			while(curr != null){
				path.add(curr);
				curr = curr.getPredecessor();
			}
			// Collections.reverse(path);
			List<Vertex> r = new ArrayList<>();
			for(int i=path.size()-1;i>=0;i--){
				r.add(path.get(i));
			}
			if(r.size()<2){
				r.clear();
			}
			return r;
		}
	}

	public double getShortestPathDistance(Vertex sourceVertex, Vertex targetVertex) {
 
		// Your code here
		List<Vertex> path = getShortestPath(sourceVertex, targetVertex);
		if(!path.isEmpty()){
			return path.get(path.size()-1).getDistance();
		}
		else if(targetVertex.getPredecessor()==null && sourceVertex != targetVertex){
			return Double.POSITIVE_INFINITY;
		}
		else{
			return 0;
		}
	}

}