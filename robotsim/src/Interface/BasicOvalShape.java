package Interface;

import fr.tp.inf112.projects.canvas.model.OvalShape;

public class BasicOvalShape implements OvalShape{
	private int width;
	
	private int height;

	public BasicOvalShape(int height, int width) {
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
