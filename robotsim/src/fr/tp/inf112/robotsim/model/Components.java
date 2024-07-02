package fr.tp.inf112.robotsim.model;

import java.io.Serializable;

import fr.tp.inf112.projects.canvas.model.Figure;


/**
 * Classe abstraite représentant un composant dans le système.
 */
public abstract class Components implements Figure, Serializable {

    private static final long serialVersionUID = 1L;  // Utilisation d'un serialVersionUID pour la sérialisation

    protected String name;  // Nom du composant
    protected int xCoordinate;  // Coordonnée X du composant
    protected int yCoordinate;  // Coordonnée Y du composant

    private Factory factory;  // Référence à l'usine à laquelle le composant appartient

    /**
     * Constructeur de la classe Components.
     * 
     * @param name Le nom du composant
     * @param xCoordinate La coordonnée X du composant
     * @param yCoordinate La coordonnée Y du composant
     */
    public Components(String name, int xCoordinate, int yCoordinate) {
        this.name = name;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.factory = null;  // Initialisation de l'usine à null
    }

    /**
     * Récupère le nom du composant.
     * 
     * @return Le nom du composant
     */
    public String getName() {
        return name;
    }

    /**
     * Définit l'usine à laquelle le composant appartient.
     * 
     * @param factory L'usine
     */
    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    /**
     * Récupère la coordonnée X du composant.
     * 
     * @return La coordonnée X
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Récupère la coordonnée Y du composant.
     * 
     * @return La coordonnée Y
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Définit la coordonnée X du composant et notifie les observateurs de l'usine.
     * 
     * @param xCoordinate La nouvelle coordonnée X
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
        if (factory != null) {
            factory.notifyObservers();  // Notifie les observateurs de l'usine
        }
    }

    /**
     * Définit la coordonnée Y du composant et notifie les observateurs de l'usine.
     * 
     * @param yCoordinate La nouvelle coordonnée Y
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
        if (factory != null) {
            factory.notifyObservers();  // Notifie les observateurs de l'usine
        }
    }

    /**
     * Comportement par défaut des composants, peut être redéfini par les sous-classes.
     */
    public void behave() {
        
    }

    @Override
    public String toString() {
        return "[name=" + name + ", xCoord=" + xCoordinate + ", yCoord=" + yCoordinate + "]";
    }

    /**
     * Récupère la position du composant.
     * 
     * @return La position du composant
     */
    public Position getPosition() {
        return new Position(xCoordinate, yCoordinate);
    }

    /**
     * Récupère l'usine à laquelle le composant appartient.
     * 
     * @return L'usine
     */
    public Factory getFactory() {
        return factory;
    }

    /**
     * Récupère la taille de l'obstacle représenté par le composant.
     * 
     * @return Un tableau contenant les dimensions de l'obstacle
     */
    public int[] getObstacleSize() {
        return new int[]{0, 0};
    }
}
