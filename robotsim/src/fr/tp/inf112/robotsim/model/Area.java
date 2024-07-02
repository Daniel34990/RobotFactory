package fr.tp.inf112.robotsim.model;

import java.util.ArrayList;

import Interface.BasicRectangleShape;
import Interface.BasicStroke;
import Interface.BasicStyle;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

/**
 * Classe représentant une zone dans le système.
 */
public class Area extends Components {

    private static final long serialVersionUID = 2L;  // Utilisation d'un serialVersionUID pour la sérialisation

    private ArrayList<ProductionUnit> productionsIn;
    private ArrayList<Puck> pucksIn;

    /**
     * Constructeur de la zone.
     * 
     * @param name Le nom de la zone
     * @param xCoord La coordonnée X de la zone
     * @param yCoord La coordonnée Y de la zone
     */
    public Area(String name, int xCoord, int yCoord) {
        super(name, xCoord, yCoord);
        this.productionsIn = new ArrayList<>();  // Initialisation de la liste des unités de production
        this.pucksIn = new ArrayList<>();  // Initialisation de la liste des pucks
    }

    /**
     * Récupère la liste des unités de production dans la zone.
     * 
     * @return La liste des unités de production
     */
    public ArrayList<ProductionUnit> getProductionsIn() {
        return productionsIn;
    }

    /**
     * Récupère la liste des pucks dans la zone.
     * 
     * @return La liste des pucks
     */
    public ArrayList<Puck> getPucksIn() {
        return pucksIn;
    }

    @Override
    public Style getStyle() {
        // Création du style de la zone avec une couleur orange et un contour noir avec des tirets
        return new BasicStyle(RGBColor.ORANGE, new BasicStroke(RGBColor.BLACK, 2.0f, new float[]{5.0f, 3.0f}));
    }

    @Override
    public Shape getShape() {
        // Création de la forme de la zone avec un rectangle basique de taille 20x20
        return new BasicRectangleShape(20, 20);
    }
}

