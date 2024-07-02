package Interface;

import fr.tp.inf112.projects.canvas.model.Color;

public class BasicColor implements Color{
	private int redComponent;
    private int greenComponent;
    private int blueComponent;
	public BasicColor(int redComponent, int greenComponent, int blueComponent) {
		super();
		this.redComponent = redComponent;
		this.greenComponent = greenComponent;
		this.blueComponent = blueComponent;
	}
	public int getRedComponent() {
		return redComponent;
	}
	public int getGreenComponent() {
		return greenComponent;
	}
	public int getBlueComponent() {
		return blueComponent;
	}
}
