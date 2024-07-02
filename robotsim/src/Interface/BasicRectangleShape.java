package Interface;

import fr.tp.inf112.projects.canvas.model.RectangleShape;

public class BasicRectangleShape implements RectangleShape {
	private int width;
	
	private int height;

	public BasicRectangleShape(int height, int width) {
		super();
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
