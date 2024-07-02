package fr.tp.inf112.robotsim.model;

import java.util.Random;

import Interface.BasicRectangleShape;
import Interface.BasicStroke;
import Interface.BasicStyle;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

/**
 * Classe représentant un point de recharge dans le système.
 */
public class ChargingPoint extends Components {

    private static final long serialVersionUID = 4L;  // Utilisation d'un serialVersionUID pour la sérialisation

    private boolean busy;
    private static final double CHARGING_RATE = 1.0;  // Utilisation d'une constante nommée pour le taux de recharge
    private static final Random RANDOM = new Random();  // Utilisation d'une constante pour l'instance Random

    /**
     * Constructeur du point de recharge.
     * 
     * @param name Le nom du point de recharge
     * @param xCoord La coordonnée X du point de recharge
     * @param yCoord La coordonnée Y du point de recharge
     */
    public ChargingPoint(String name, int xCoord, int yCoord) {
        super(name, xCoord, yCoord);
        this.busy = true;  // Initialisation du point de recharge à occupé
    }

    /**
     * Vérifie si le point de recharge est occupé.
     * 
     * @return true si le point de recharge est occupé, sinon false
     */
    public boolean isBusy() {
        return busy;
    }

    @Override
    public String toString() {
        return super.toString() + "busy=" + busy + "]";
    }

    @Override
    public Style getStyle() {
        // Création du style du point de recharge avec une couleur rouge et un contour noir
        return new BasicStyle(RGBColor.RED, new BasicStroke(RGBColor.BLACK, 2.0f, null));
    }

    @Override
    public Shape getShape() {
        // Création de la forme du point de recharge avec un rectangle basique de taille 10x10
        return new BasicRectangleShape(10, 10);
    }

    /**
     * Comportement du point de recharge pour gérer la recharge des robots.
     */
    public void behave() {
        for (Robot r : this.getFactory().getRobots()) {
            if (!r.isCharging()) {
                if (r.getChargeX() == r.getxCoordinate() && r.getChargeY() == r.getyCoordinate()) {
                    r.setCharging(true);
                    r.setNeedCharge(false);
                } else if (r.getBattery() < 50 && !r.isNeedCharge()) {
                    int deltaX = RANDOM.nextInt(6); 
                    int deltaY = RANDOM.nextInt(6); 
                    ChargingPoint factoryCharge = this.getFactory().getChargingArea(); 
                    int chargeX = factoryCharge.getxCoordinate() + deltaX;
                    int chargeY = factoryCharge.getyCoordinate() + deltaY;
                    ChargingPoint aroundChargingPoint = new ChargingPoint("ChargingPoint", chargeX, chargeY);
                    r.setChargeX(chargeX);
                    r.setChargeY(chargeY);
                    r.addPath(aroundChargingPoint);
                    r.setNeedCharge(true);
                }
            } else {
                if (r.getBattery() > 99.0) {
                    r.setCharging(false);
                } else {
                    r.setBattery(r.getBattery() + CHARGING_RATE);
                }
            }
        }
    }
}

