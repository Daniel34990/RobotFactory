package fr.tp.inf112.robotsim.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class FactoryGraph implements Serializable{
	private static final long serialVersionUID = 6L;
    
    private Graph<Position, DefaultEdge> graph;
    private Map<Position, Position> vertexMap;
    
    
    public FactoryGraph() {
        graph = new SimpleWeightedGraph<>(DefaultEdge.class);
        vertexMap = new HashMap<>();
        createGraph();
    }
    
    private void createGraph() {
        // Créer les sommets
        for (int x = 1; x < 101; x++) {
            for (int y = 1; y < 101; y++) {
                Position vertex = new Position(x, y);
                graph.addVertex(vertex);
                vertexMap.put(vertex, vertex);
            }
        }

        // Créer les arêtes pour les sommets adjacents
        for (Position vertex : graph.vertexSet()) {
            addEdgesForVertex(vertex);
        }
    }

    private void addEdgesForVertex(Position vertex) {
        int x = vertex.getxCoordinates();
        int y = vertex.getyCoordinates();
        
        // Ajoute une arête pour chaque voisin adjacent (orthogonal)
        addEdgeIfValid(vertex, new Position(x + 1, y), 1.0);
        addEdgeIfValid(vertex, new Position(x - 1, y), 1.0);
        addEdgeIfValid(vertex, new Position(x, y + 1), 1.0);
        addEdgeIfValid(vertex, new Position(x, y - 1), 1.0);
        
        // Ajoute une arête pour chaque voisin diagonal
        addEdgeIfValid(vertex, new Position(x + 1, y + 1), Math.sqrt(2));
        addEdgeIfValid(vertex, new Position(x + 1, y - 1), Math.sqrt(2));
        addEdgeIfValid(vertex, new Position(x - 1, y + 1), Math.sqrt(2));
        addEdgeIfValid(vertex, new Position(x - 1, y - 1), Math.sqrt(2));
        
        
    }

    private void addEdgeIfValid(Position p1, Position p2, double weight) {
        if (vertexMap.containsKey(p2)) {
            graph.addEdge(p1, vertexMap.get(p2));
            graph.setEdgeWeight(graph.getEdge(p1, vertexMap.get(p2)), weight);
        }
    }
    
    private Position findVertex(Position position) {
        return vertexMap.get(position);
    }
    
    public ArrayList<Position> findPath(Components source, Components target) {
        Position sourceVertex = findVertex(source.getPosition());
        Position targetVertex = findVertex(target.getPosition());

        if (sourceVertex == null || targetVertex == null) {
            throw new IllegalArgumentException("Source or target position is not in the graph");
        }
        
        DijkstraShortestPath<Position, DefaultEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graph);
        GraphPath<Position, DefaultEdge> path = dijkstraShortestPath.getPath(sourceVertex, targetVertex);

        if (path != null) {
            return new ArrayList<>(path.getVertexList());
        } else {
            return new ArrayList<>();
        }
    }
    
    
    public ArrayList<Position> findPath(Position sourcePosition, Position targetPosition) {
        Position sourceVertex = findVertex(sourcePosition);
        Position targetVertex = findVertex(targetPosition);

        if (sourceVertex == null || targetVertex == null) {
            throw new IllegalArgumentException("Source or target position is not in the graph");
        }

        DijkstraShortestPath<Position, DefaultEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graph);
        GraphPath<Position, DefaultEdge> path = dijkstraShortestPath.getPath(sourceVertex, targetVertex);

        if (path != null) {
            return new ArrayList<>(path.getVertexList());
        } else {
            return new ArrayList<>();
        }
    }
    
    private static final int RobotWidth = 4;
    
    public void addObstacle(Position topLeft, Position bottomRight) {
        // Ajout des murs horizontaux (haut et bas)
        for (int i = 0; i < 2; i++) {
            for (int x = topLeft.getxCoordinates() - i; x <= bottomRight.getxCoordinates() + i; x++) {
                Position topWall = new Position(x, topLeft.getyCoordinates() - i + 1);
                Position bottomWall = new Position(x, bottomRight.getyCoordinates() + i - 1);
                removeEdgesForVertex(topWall);
                removeEdgesForVertex(bottomWall);
            }
        }

        // Ajout des murs verticaux (gauche et droite)
        for (int i = 0; i < 4; i++) {
            for (int y = topLeft.getyCoordinates() - i; y <= bottomRight.getyCoordinates() + i; y++) {
                Position leftWall = new Position(topLeft.getxCoordinates() - i, y);
                Position rightWall = new Position(bottomRight.getxCoordinates() + i, y);
                removeEdgesForVertex(leftWall);
                removeEdgesForVertex(rightWall);
            }
        }
    }
    
    public void removeObstacle(Position topLeft, Position bottomRight) {
    	
    	for (int x = topLeft.getxCoordinates(); x <= bottomRight.getxCoordinates(); x++) {
    		for (int y = topLeft.getyCoordinates() ; y <= bottomRight.getyCoordinates() ; y++) {
    			Position vertex = new Position(x,y);
    			this.addEdgesForVertex(vertex);
    		}
    	}
    }


    private void removeEdgesForVertex(Position vertex) {
        Set<DefaultEdge> edges = graph.edgesOf(vertex);
        List<DefaultEdge> edgesToRemove = new ArrayList<>(edges);
        for (DefaultEdge edge : edgesToRemove) {
            graph.removeEdge(edge);
        }
    } 
    

}
