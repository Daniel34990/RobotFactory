package fr.tp.inf112.robotsim.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import Interface.*;
import fr.tp.inf112.projects.canvas.view.*;
import fr.tp.inf112.robotsim.model.*;

/**
 * Classe principale pour lancer l'application de simulation.
 */
public class FinalApp {
    private static final Logger LOGGER = Logger.getLogger(FinalApp.class.getName());

    /**
     * Méthode principale pour démarrer la simulation.
     * 
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        LOGGER.info("Démarrage de la simulation...");
        LOGGER.config("Avec les paramètres " + Arrays.toString(args) + ".");

        String fileExtension = "cvs"; 
        String documentTypeLabel = "Canvas Document";

        FileCanvasChooser canvasChooser = new FileCanvasChooser(fileExtension, documentTypeLabel);
        BasicPersistenceManager persistenceManager = new BasicPersistenceManager(canvasChooser);

        // Création de l'usine et des composants
        Factory usine = new Factory("Usine1");
        Robot robot1 = new Robot("Robot1");
        Robot robot2 = new Robot("Robot2");
        Robot robot3 = new Robot("Robot3");
        robot2.setSpeed(2);

        Rooms room1 = new Rooms("Salle de Production 1", 15, 15);
        Rooms room2 = new Rooms("Salle de Production 2", 50, 55); // 50,15
        Rooms room3 = new Rooms("Salle de Production 3", 10, 55);
        Rooms room4 = new Rooms("Salle de Recharge", 50, 15);

        Area area1 = new Area("Zone de Production 1", 20, 20);
        Area area2 = new Area("Zone de Production 2", 53, 60); // 55,20
        Area area3 = new Area("Zone de Production 3", 18, 60);

        BandConveyor bc1 = new BandConveyor("Convoyeur", 85, 55);

        ProductionUnit p1 = new ProductionUnit("Machine1", 27, 27);
        ProductionUnit p2 = new ProductionUnit("Machine2", 60, 68);
        ProductionUnit p3 = new ProductionUnit("Machine3", 25, 68); // 62,27

        Door d1 = new Door("Porte1", 15 + 10, 15 + 30, "Horizontal");
        Door d2 = new Door("Porte2", 50 + 10, 15 + 30, "Horizontal");
        Door d3 = new Door("Porte3", 60 + 10, 55 + 30, "Horizontal");
        Door d4 = new Door("Porte4", 40 + 10, 35 + 30, "Vertical");
        Door d5 = new Door("Porte5", 30 + 10, 35 + 30, "Vertical");

        // Ajout des composants à l'usine
        usine.addComponent(room1);
        usine.addComponent(room2);
        usine.addComponent(room3);
        usine.addComponent(room4);
        usine.addComponent(area1);
        usine.addComponent(area2);
        usine.addComponent(area3);
        usine.addComponent(d1);
        usine.addComponent(d2);
        usine.addComponent(d3);
        usine.addComponent(d4);
        usine.addComponent(d5);
        usine.addComponent(bc1);
        usine.addComponent(p1);
        usine.addComponent(p2);
        usine.addComponent(p3);
        usine.addComponent(robot1);
        usine.addComponent(robot2);
        usine.addComponent(robot3);

        // Activer les unités de production
        p1.setActive(true);
        p2.setActive(true);
        p3.setActive(true);

        // Persist et démarrage de la simulation
        try {
            persistenceManager.persist(usine);
            SimulatorController controller = new SimulatorController(usine, persistenceManager);
            CanvasViewer canvasViewer = new CanvasViewer(controller);
            controller.startAnimation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
