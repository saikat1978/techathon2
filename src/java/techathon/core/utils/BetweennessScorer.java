package techathon.core.utils;



import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Hypergraph;
import techathon.core.graph.UserNode;

public class BetweennessScorer implements VertexScorer<UserNode, Double>{

	public BetweennessScorer(Hypergraph graph){
		/*
		 * Step 1: Calculate the shortest path between each pair of nodes.
		 */
		DijkstraShortestPath paths = new DijkstraShortestPath((Graph) graph);
//		paths.getDistance(source, target);
	}
	
	public Double getVertexScore(UserNode arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
