package fr.tp.inf112.robotsim.model;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable{
	
	private static final long serialVersionUID = 7L;
	protected int xCoordinates;
	protected int yCoordinates;
	
	public Position(int xCoordinates,int yCoordinates) {
		this.xCoordinates=xCoordinates;
		this.yCoordinates=yCoordinates;
	}

	public int getxCoordinates() {
		return xCoordinates;
	}

	public int getyCoordinates() {
		return yCoordinates;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return xCoordinates == position.xCoordinates && yCoordinates == position.yCoordinates;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinates, yCoordinates);
    }
}
