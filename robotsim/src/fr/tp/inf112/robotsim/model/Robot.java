package fr.tp.inf112.robotsim.model;

import java.util.ArrayList;

import Interface.BasicOvalShape;
import Interface.BasicStroke;
import Interface.BasicStyle;
import fr.tp.inf112.projects.canvas.model.*;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

/**
 * Classe représentant un Robot dans le système.
 */
public class Robot extends Components {
    private static final long serialVersionUID = 10L;
    private static final double DISCHARGE_RATE = 0.1; // Taux de décharge de la batterie
    private static int instanceCounter = 0; // Compteur d'instances pour positionner les robots

    private double speed;
    private double battery;
    private boolean charging;
    private boolean needCharge;
    private ArrayList<Puck> puckLoad;
    private ArrayList<Components> path;
    private ArrayList<Position> trajectory;
    private int chargeX;
    private int chargeY;

    /**
     * Constructeur pour créer un Robot.
     * 
     * @param name Le nom du robot
     */
    public Robot(String name) {
        super(name, 83 + instanceCounter * 5, 95); // Position initiale du robot
        this.speed = 1;
        this.battery = 100;
        this.charging = false;
        this.needCharge = false;
        this.puckLoad = new ArrayList<>();
        this.path = new ArrayList<>();
        this.trajectory = new ArrayList<>();
        instanceCounter++;
    }

    // Getters et setters

    public boolean isNeedCharge() {
        return needCharge;
    }

    public void setNeedCharge(boolean needCharge) {
        this.needCharge = needCharge;
    }

    public int getChargeX() {
        return chargeX;
    }

    public void setChargeX(int chargeX) {
        this.chargeX = chargeX;
    }

    public int getChargeY() {
        return chargeY;
    }

    public void setChargeY(int chargeY) {
        this.chargeY = chargeY;
    }

    public ArrayList<Position> getTrajectory() {
        return trajectory;
    }

    @Override
    public String getName() {
        return String.format("%s (%.1f%%)", name, battery);
    }

    @Override
    public String toString() {
        return super.toString() + ", speed=" + speed + ", battery=" + battery + ", charging=" + charging + ", puckLoad=" + puckLoad + "]";
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public BasicOvalShape getShape() {
        return new BasicOvalShape(3, 2);
    }

    @Override
    public Style getStyle() {
        Stroke stroke = new BasicStroke(RGBColor.BLACK, 2.0f, null);
        return new BasicStyle(RGBColor.GREEN, stroke);
    }

    /**
     * Comportement du Robot à chaque étape de la simulation.
     */
    public void behave() {
        if (trajectory != null && !trajectory.isEmpty() && !charging) {
            moveAlongTrajectory();
            return;
        }

        if (path == null || path.size() < 2) {
            return;
        }

        if (!charging) {
            followPath();
        }
    }

    /**
     * Déplace le robot le long de sa trajectoire.
     */
    private void moveAlongTrajectory() {
        Position nextPosition = null;
        if (trajectory.size() > speed) {
            for (int i = 0; i < speed; i++) {
                nextPosition = trajectory.remove(0);
            }
        } else {
            nextPosition = trajectory.remove(0);
        }
        battery -= DISCHARGE_RATE * speed * speed;
        move(nextPosition);
    }

    /**
     * Fait suivre le chemin défini au robot.
     */
    private void followPath() {
        Components currentComponent = path.remove(0);

        if (!path.isEmpty()) {
            Components nextComponent = path.get(0);
            FactoryGraph factoryGraph = this.getFactory().getFactorygraph();

            ArrayList<Position> newTrajectory = factoryGraph.findPath(currentComponent, nextComponent);

            if (newTrajectory != null && !newTrajectory.isEmpty()) {
                setTrajectory(newTrajectory);
            }
        }
    }

    public void addPath(Components component) {
        path.add(component);
    }

    public void move(Position position) {
        this.setxCoordinate(position.getxCoordinates());
        this.setyCoordinate(position.getyCoordinates());
    }

    public double getBattery() {
        return battery;
    }

    public boolean isCharging() {
        return charging;
    }

    public ArrayList<Puck> getPuckLoad() {
        return puckLoad;
    }

    public ArrayList<Components> getPath() {
        return path;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setBattery(double battery) {
        this.battery = battery;
        this.getFactory().notifyObservers();
    }

    public void setCharging(boolean charging) {
        this.charging = charging;
    }

    public void setPuckLoad(ArrayList<Puck> puckLoad) {
        this.puckLoad = puckLoad;
    }

    public void setPath(ArrayList<Components> path) {
        this.path = path;
    }

    public void setTrajectory(ArrayList<Position> trajectory) {
        this.trajectory = trajectory;
    }
}
