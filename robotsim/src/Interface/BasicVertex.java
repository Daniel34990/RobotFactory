package Interface;

import fr.tp.inf112.projects.canvas.model.Vertex;

public class BasicVertex implements Vertex {
	int xCoordinate;
	int yCoordinate;
	public BasicVertex(int xCoordinate, int yCoordinate) {
		super();
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	public int getxCoordinate() {
		return xCoordinate;
	}
	public int getyCoordinate() {
		return yCoordinate;
	}
	
}
