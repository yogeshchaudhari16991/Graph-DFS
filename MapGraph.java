import java.util.Stack;
import java.util.Vector;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import GeographicPoint;



public class MapGraph {
	
	private HashMap<GeographicPoint, MapNode> vertices;
	private Vector visited;
	private List<GeographicPoint> path;

	public MapGraph()
	{
		vertices = new HashMap<GeographicPoint, MapNode>();
		visited = new Vector();
		path = new ArrayList<GeographicPoint>();
	}
	
	
	public int getNumVertices()
	{
		return vertices.size();
	}
	
	public Set<GeographicPoint> getVertices()
	{
		return vertices.keySet();
	}
	
	public int getNumEdges()
	{
		Set<GeographicPoint> verticesSet = getVertices();
		int numberOfVertices = getNumVertices();
		int numEdges = 0;
		for(GeographicPoint location : verticesSet){
			MapNode vertex = vertices.get(location);
			numEdges += vertex.getNumOfNeighbors();
		}
		return numEdges;
	}

	
	public boolean addVertex(GeographicPoint location)
	{
		if(location != null){
			if(! vertices.containsKey(location)){
				MapNode vertex = new MapNode(location, new ArrayList<MapEdge>());
				vertices.put(location, vertex);
				return true;
			}
		}
		return false;
	}
	
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
			if(from != null && to !=  null && roadName != null && roadType != null
					&& vertices.containsKey(from) && vertices.containsKey(to) && length > 0){
				MapEdge edge = new MapEdge(from, to, roadName, length, roadType);
				MapNode vertex = vertices.get(from);
				vertex.addNeighbor(edge);
				vertices.put(from, vertex);
			}
			else{
				throw new IllegalArgumentException();
			}
	}

	public List<GeographicPoint> dfs(GeographicPoint start, 
			 					     GeographicPoint goal)
	{
		if(start.equals(goal)){
			path.add(start);
			return path;
		}
		else
		{
			path.add(start);
			visited.add(start);
			MapNode current = vertices.get(start);
			for(MapEdge neighbor : current.getNeighbor()){
				GeographicPoint next = neighbor.getEnd();
				if(! visited.contains(next))
					path = dfs(next, goal);
				if(path.contains(goal)){
					return path;
				}
			}
			path.remove(start);
			return path;
		}
	}
	
	
	
	public static void main(String[] args)
	{
		System.out.print("Create or import a new map in MapGraph object. Call dfs method with source and destination vertex");
	}
	
}
