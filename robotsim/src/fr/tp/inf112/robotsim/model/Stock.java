package fr.tp.inf112.robotsim.model;

import Interface.BasicRectangleShape;
import Interface.BasicStroke;
import Interface.BasicStyle;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

public class Stock extends Components {

	private static final long serialVersionUID = 8L;
	
	public Stock(int xCoord, int yCoord) {
		super("Stock", xCoord, yCoord);
		
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		return new BasicStyle(RGBColor.GREEN,new BasicStroke(RGBColor.BLACK,2.0f,null));
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new BasicRectangleShape(5,5);
	}
	
}