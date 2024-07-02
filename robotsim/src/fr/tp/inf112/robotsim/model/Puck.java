package fr.tp.inf112.robotsim.model;

import java.util.ArrayList;

import Interface.BasicOvalShape;
import Interface.BasicStroke;
import Interface.BasicStyle;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Stroke;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

/**
 * Classe représentant un Puck dans le système.
 */
public class Puck extends Components {

    private static final long serialVersionUID = 9L;
    private static int nextId = 1; // Identifiant statique pour chaque instance de Puck

    private ArrayList<Robot> robots;
    private int stockX;
    private int stockY;

    /**
     * Constructeur pour créer un Puck.
     * 
     * @param xCoordinate La coordonnée X du Puck
     * @param yCoordinate La coordonnée Y du Puck
     * @param stockX La coordonnée X du stock
     * @param stockY La coordonnée Y du stock
     */
    public Puck(int xCoordinate, int yCoordinate, int stockX, int stockY) {
        super("Puck" + nextId, xCoordinate, yCoordinate);
        this.robots = new ArrayList<>();
        this.stockX = stockX;
        this.stockY = stockY;
        nextId++;
    }

    /**
     * Définit la coordonnée X du stock.
     * 
     * @param stockX La nouvelle coordonnée X du stock
     */
    public void setStockX(int stockX) {
        this.stockX = stockX;
    }

    /**
     * Définit la coordonnée Y du stock.
     * 
     * @param stockY La nouvelle coordonnée Y du stock
     */
    public void setStockY(int stockY) {
        this.stockY = stockY;
    }

    /**
     * Vérifie si un robot est à proximité pour charger le Puck.
     */
    public void testIfRobot() {
        for (Components c : this.getFactory().getComponents()) {
            if (c instanceof Robot) {
                if (Math.abs(xCoordinate - c.getxCoordinate()) < 1 && Math.abs(yCoordinate - c.getyCoordinate()) < 1
                        && robots.isEmpty()) {
                    Robot r = (Robot) c;
                    robots.add(r);
                    r.getPuckLoad().add(this);
                }
            }
        }
    }

    /**
     * Vérifie si le Puck est au niveau du stock.
     */
    public void testIfStock() {
        if (stockX == xCoordinate && stockY == yCoordinate && robots.size() == 1) {
            Robot r = robots.remove(0);
            r.setPuckLoad(new ArrayList<>());
        }
    }

    /**
     * Comportement du Puck à chaque étape de la simulation.
     */
    public void behave() {
        this.testIfRobot();
        this.testIfStock();
        if (robots.size() == 1) {
            xCoordinate = robots.get(0).getxCoordinate();
            yCoordinate = robots.get(0).getyCoordinate();
        }
    }

    /**
     * Retourne le style graphique du Puck.
     * 
     * @return Le style du Puck
     */
    @Override
    public Style getStyle() {
        Stroke stroke = new BasicStroke(RGBColor.BLACK, 2.0f, null);
        return new BasicStyle(RGBColor.BLACK, stroke);
    }

    /**
     * Retourne la forme graphique du Puck.
     * 
     * @return La forme du Puck
     */
    @Override
    public Shape getShape() {
        return new BasicOvalShape(2, 1);
    }
}

