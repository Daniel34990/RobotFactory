package fr.tp.inf112.robotsim.model;

import java.util.LinkedHashSet;
import java.util.Set;

import Interface.BasicPolygonShape;
import Interface.BasicStroke;
import Interface.BasicStyle;
import Interface.BasicVertex;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.Vertex;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

/**
 * Classe représentant un convoyeur à bande dans le système.
 */
public class BandConveyor extends Components {

    private static final long serialVersionUID = 3L;  // Utilisation d'un serialVersionUID pour la sérialisation

    private Area deliveryArea;
    private static final int SCALE_FACTOR = 1;  // Utilisation d'une constante nommée pour le facteur de mise à l'échelle

    /**
     * Constructeur du convoyeur à bande.
     * 
     * @param name Le nom du convoyeur
     * @param xCoord La coordonnée X du convoyeur
     * @param yCoord La coordonnée Y du convoyeur
     */
    public BandConveyor(String name, int xCoord, int yCoord) {
        super(name, xCoord, yCoord);
        this.deliveryArea = null;  // Initialisation de la zone de livraison à null
    }

    /**
     * Récupère la zone de livraison associée au convoyeur.
     * 
     * @return La zone de livraison
     */
    public Area getDeliveryArea() {
        return deliveryArea;
    }

    @Override
    public Style getStyle() {
        // Création du style du convoyeur avec une couleur rouge et un contour noir
        return new BasicStyle(RGBColor.RED, new BasicStroke(RGBColor.BLACK, 2.0f, null));
    }

    @Override
    public Shape getShape() {
        // Création des sommets du polygone représentant la forme du convoyeur
        Vertex v1 = new BasicVertex(xCoordinate, yCoordinate);
        Vertex v2 = new BasicVertex(xCoordinate, yCoordinate + 20 * SCALE_FACTOR);
        Vertex v3 = new BasicVertex(xCoordinate - SCALE_FACTOR, yCoordinate + 20 * SCALE_FACTOR);
        Vertex v4 = new BasicVertex(xCoordinate - SCALE_FACTOR, yCoordinate + 21 * SCALE_FACTOR);
        Vertex v5 = new BasicVertex(xCoordinate + 7 * SCALE_FACTOR, yCoordinate + 21 * SCALE_FACTOR);
        Vertex v6 = new BasicVertex(xCoordinate + 7 * SCALE_FACTOR, yCoordinate + 20 * SCALE_FACTOR);
        Vertex v7 = new BasicVertex(xCoordinate + 6 * SCALE_FACTOR, yCoordinate + 20 * SCALE_FACTOR);
        Vertex v8 = new BasicVertex(xCoordinate + 6 * SCALE_FACTOR, yCoordinate);

        // Utilisation d'un LinkedHashSet pour garantir l'ordre des sommets
        Set<Vertex> vertexSet = new LinkedHashSet<>();
        vertexSet.add(v1);
        vertexSet.add(v2);
        vertexSet.add(v3);
        vertexSet.add(v4);
        vertexSet.add(v5);
        vertexSet.add(v6);
        vertexSet.add(v7);
        vertexSet.add(v8);

        return new BasicPolygonShape(vertexSet);
    }
}

