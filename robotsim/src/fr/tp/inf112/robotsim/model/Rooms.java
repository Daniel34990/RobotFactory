package fr.tp.inf112.robotsim.model;

import java.util.ArrayList;

import Interface.*;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.*;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

public class Rooms extends Components {

	private static final long serialVersionUID = 11L;
	private ArrayList<Area> AreaList;

	public Rooms(String name, int xCoord, int yCoord) {
		super(name, xCoord, yCoord);
		AreaList = new ArrayList<>();
	}

	public ArrayList<Area> getAreaList() {
		return AreaList;
	}

	
	@Override
	public String toString() {
		String b="";
		for (Area a: AreaList) {
			b+=" "+ a.toString();
		}
		return super.toString() + b;
	}

	@Override
	public Style getStyle() {
		return new BasicStyle(RGBColor.CYAN,new BasicStroke(RGBColor.BLACK,2.0f,null));
	}

	@Override
	public Shape getShape() {
		return new BasicRectangleShape(30,30);
	}
	
	@Override
    public int[] getObstacleSize() {
        
        return new int[]{30, 30};
    }
}
