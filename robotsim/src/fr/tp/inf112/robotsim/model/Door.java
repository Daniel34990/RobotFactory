package fr.tp.inf112.robotsim.model;

import Interface.BasicRectangleShape;
import Interface.BasicStroke;
import Interface.BasicStyle;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

public class Door extends Components {
	
	private static final long serialVersionUID = 2L;
	private boolean open;
	private transient String doorType;
	
	public Door(String name, int xCoord, int yCoord, String doorType) {
		super(name, xCoord, yCoord);
		this.open = true;
		this.doorType=doorType;
	}

	public boolean isOpen() {
		return open;
	}

	@Override
	public Style getStyle() {
		// TODO Auto-generated method stub
		return new BasicStyle(RGBColor.GRAY,new BasicStroke(RGBColor.BLACK,2.0f,null));
	}

	@Override
	public Shape getShape() {
		if (doorType=="Horizontal") {
			return new BasicRectangleShape(1,5);
		}
		else if (doorType=="Vertical") {
			return new BasicRectangleShape(7,1);
		}
		return null;
	}
	
	
	public String getDoorType() {
		return doorType;
	}

	@Override
    public int[] getObstacleSize() {
        
        return new int[]{5, 1};
    }
	
}
