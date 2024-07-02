package Interface;

import java.util.Set;

import fr.tp.inf112.projects.canvas.model.PolygonShape;
import fr.tp.inf112.projects.canvas.model.Vertex;

public class BasicPolygonShape implements PolygonShape{
	Set<Vertex> vertices;
	
	
	public BasicPolygonShape(Set<Vertex> vertices) {
		super();
		this.vertices = vertices;
	}


	@Override
	public Set<Vertex> getVertices() {
		// TODO Auto-generated method stub
		return vertices;
	}

}
