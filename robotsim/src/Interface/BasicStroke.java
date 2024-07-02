package Interface;

import fr.tp.inf112.projects.canvas.model.Color;
import fr.tp.inf112.projects.canvas.model.Stroke;

public class BasicStroke implements Stroke{
	private Color color; // La couleur du trait
    private float thickness; // L'épaisseur du trait
    private float[] dashPattern; // Le motif de pointillés du trait
	public BasicStroke(Color color, float thickness, float[] dashPattern) {
		super();
		this.color = color;
		this.thickness = thickness;
		this.dashPattern = dashPattern;
	}
	public Color getColor() {
		return color;
	}
	public float getThickness() {
		return thickness;
	}
	public float[] getDashPattern() {
		return dashPattern;
	}
    
    
}
