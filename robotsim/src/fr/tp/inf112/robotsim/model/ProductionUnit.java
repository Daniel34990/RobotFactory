package fr.tp.inf112.robotsim.model;

import Interface.BasicRectangleShape;
import Interface.BasicStroke;
import Interface.BasicStyle;
import fr.tp.inf112.projects.canvas.model.Shape;
import fr.tp.inf112.projects.canvas.model.Style;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;
import java.util.Random;

/**
 * Classe représentant une unité de production dans le système.
 */
public class ProductionUnit extends Components {

    private static final long serialVersionUID = 8L;  // Utilisation d'un serialVersionUID pour la sérialisation
    private int productionRate;
    private static int instanceCount = 0;
    private boolean isActive;
    private static final Random RANDOM = new Random();  // Nom de la constante en majuscules selon les conventions

    /**
     * Constructeur de l'unité de production.
     * 
     * @param name Le nom de l'unité de production
     * @param xCoord La coordonnée X de l'unité
     * @param yCoord La coordonnée Y de l'unité
     */
    public ProductionUnit(String name, int xCoord, int yCoord) {
        super(name, xCoord, yCoord);
        this.isActive = false;
        this.productionRate = 150 + 30 * instanceCount;  // Calcul du taux de production basé sur le nombre d'instances
        instanceCount++;
    }

    /**
     * Définit l'état actif ou inactif de l'unité de production.
     * 
     * @param isActive L'état de l'unité de production
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Récupère le taux de production de l'unité.
     * 
     * @return Le taux de production
     */
    public int getProductionRate() {
        return productionRate;
    }

    @Override
    public String toString() {
        return "ProductionUnit [ProductionRate=" + productionRate + "]";
    }

    @Override
    public Style getStyle() {
        // Création du style de l'unité de production avec une couleur rouge et un contour noir
        return new BasicStyle(RGBColor.RED, new BasicStroke(RGBColor.BLACK, 2.0f, null));
    }

    @Override
    public Shape getShape() {
        // Création de la forme de l'unité de production avec un rectangle basique de taille 5x5
        return new BasicRectangleShape(5, 5);
    }

    /**
     * Comportement de l'unité de production au fil du temps.
     * 
     * @param time Le temps actuel
     */
    public void behave(int time) {
        if (time % productionRate == 0 && isActive) {  // Utilisation de && au lieu de & pour la condition
            int deltaX = RANDOM.nextInt(11) - 5;  // Déplacement aléatoire en X entre -5 et 5
            int deltaY = RANDOM.nextInt(11) - 5;  // Déplacement aléatoire en Y entre -5 et 5
            Stock factoryStock = this.getFactory().getStock();
            int stockX = factoryStock.getxCoordinate() + RANDOM.nextInt(5);
            int stockY = factoryStock.getyCoordinate() + RANDOM.nextInt(5);
            Puck newPuck = new Puck(this.xCoordinate + deltaX, this.yCoordinate + deltaY, stockX, stockY);
            this.getFactory().addPuck(newPuck);
            Stock aroundStock = new Stock(stockX, stockY);
            
            Robot idleRobot = this.getFactory().getIdleRobot();
            idleRobot.addPath(newPuck);
            idleRobot.addPath(aroundStock);
        }
    }
}
