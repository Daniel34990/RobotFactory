package fr.tp.inf112.robotsim.model;
import java.util.ArrayList;


import Interface.*;
import fr.tp.inf112.projects.canvas.controller.Observable;
import fr.tp.inf112.projects.canvas.controller.Observer;
import fr.tp.inf112.projects.canvas.model.*;
import fr.tp.inf112.projects.canvas.model.impl.RGBColor;

public class Factory extends Components implements Canvas, Observable {
	
	private static final long serialVersionUID = 5L;
	private static int nextId = 1;
	private String id;
	private String name;
	private ArrayList<Components> components;
	private int height;
	private int width;
	private transient ArrayList<Observer> observers;
	private boolean simulationStarted;
	private transient FactoryGraph factorygraph;
	private ArrayList<Puck> pucks;
	private ArrayList<Robot> robots;
	private final Stock stock = new Stock(85,40);
	private final Area robotArea = new Area("Robot Area",80,90);
	private static ChargingPoint chargingArea= new ChargingPoint("Charging Station",66,23);
	
	
	public Area getRobotArea() {
		return robotArea;
	}

	public ChargingPoint getChargingArea() {
		return chargingArea;
	}



	public ArrayList<Robot> getRobots() {
		return robots;
	}



	public Stock getStock() {
		return stock;
	}

	public Factory(String name) {
		super(name,0,0);
		this.name = name;
		this.components = new ArrayList<>();
		components.add(stock);
		components.add(robotArea);
		this.addComponent(chargingArea);
		this.height = 100;
		this.width = 100;
		this.observers=new ArrayList<>();
		this.simulationStarted=false;
		this.id = Integer.toString(nextId++);
		this.factorygraph=new FactoryGraph();
		this.pucks=new ArrayList<>();
		this.robots = new ArrayList<>();
		
	}
	
	public FactoryGraph getFactorygraph() {
		return factorygraph;
	}

	public String getName() {
		return name;
	}
	public ArrayList<Components> getComponents() {
		return components;
	}
	public ArrayList<Figure> getFigures() {
		ArrayList<Figure> ret= new ArrayList<Figure>();
		ret.addAll(components);
		ret.add(chargingArea);
		ret.addAll(robots);
		ret.addAll(pucks);
		return ret;
		
	}	
	
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public Style getStyle() {
		Stroke Trait=new BasicStroke(RGBColor.GRAY,2.0f,null);
		Style SimpleUsine=new BasicStyle(new RGBColor(220,220,220),Trait);
		return SimpleUsine;
	}
	
	public boolean addComponent(Components component) {
        for (Components c : components) {
            if (component.equals(c)) {
                return false;
            }
        }
        component.setFactory(this);
        components.add(component);
        
        if (component instanceof Robot) {
        	robots.add((Robot) component);
        	((Robot)component).addPath(robotArea);
        }

        if (component instanceof Rooms) {
            // Obtenir la taille de l'obstacle
            Rooms room = (Rooms) component;
            int[] obstacleSize = room.getObstacleSize();
            int obstacleWidth = obstacleSize[0];
            int obstacleHeight = obstacleSize[1];

            // Définir les positions des coins de la pièce
            Position topLeft = new Position(room.getxCoordinate(), room.getyCoordinate());
            Position bottomRight = new Position(room.getxCoordinate() + obstacleWidth , room.getyCoordinate() + obstacleHeight);

            factorygraph.addObstacle(topLeft, bottomRight);
        
        }
        
        if (component instanceof BandConveyor) {
        	 BandConveyor bc = (BandConveyor) component;
        	 Position topLeft = new Position(bc.getxCoordinate(), bc.getyCoordinate());
             Position bottomRight = new Position(bc.getxCoordinate() + 3 , bc.getyCoordinate() + 20);
             factorygraph.addObstacle(topLeft, bottomRight);

        }
        if (component instanceof Door) {
        	 // Obtenir la taille de l'obstacle
            Door door = (Door) component;
        
            // Définir les positions des coins de la pièce
            Position topLeft;
            Position bottomRight;
            if (door.getDoorType()=="Horizontal") {
            	topLeft = new Position(door.getxCoordinate(), door.getyCoordinate() - 3);
            	bottomRight = new Position(door.getxCoordinate() + 3, door.getyCoordinate() + 3);
            }
            else {
            	topLeft = new Position(door.getxCoordinate()-10, door.getyCoordinate()+1);
            	bottomRight = new Position(door.getxCoordinate()+10, door.getyCoordinate() + 5 - 1);
            }

            factorygraph.removeObstacle(topLeft, bottomRight);
        }

        return true;
    }
	
	public boolean addPuck(Puck puck) {
		 for (Puck p : pucks) {
	            if (p.equals(puck)) {
	                return false;
	            }
	        
		 }
		 puck.setFactory(this);
         pucks.add(puck);
         return true;
	}

	
	public void Print() {
	    System.out.println("Factory [name=" + name + "]");
	    for (Components c : components) {
	        System.out.println(" " + c.toString()); 
	    }
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void behave(int time) {
	    for (Components c : components) {
	        if (c instanceof Robot) { 
	            Robot robot = (Robot) c; 
	            robot.behave(); 
	        }
	        if (c instanceof ProductionUnit) { 
	        	ProductionUnit machine = (ProductionUnit) c; 
	            machine.behave(time); 
	        }
	        if (c instanceof ChargingPoint) { 
	        	ChargingPoint charge = (ChargingPoint) c; 
	            charge.behave(); 
	        }
	    }
	    for (Puck p: pucks) {
	    	p.behave();
	        }
	    
	}

	@Override
	public boolean addObserver(Observer observer) {
		// TODO Auto-generated method stub
		return observers.add(observer);
	}

	@Override
	public boolean removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		return observers.remove(observer);
	}
	protected void notifyObservers() { // To be called every time model data is modified
		for (final Observer observer : observers) {
		observer.modelChanged();
		}
		}

	public boolean isSimulationStarted() {
		return simulationStarted;
	}

	public void setSimulationStarted(boolean simulationStarted) {
		this.simulationStarted = simulationStarted;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id=id;
	}

	public ArrayList<Observer> getObservers() {
		if (observers==null) {
			return new ArrayList<>();
		}
		return observers;
	}
	
	public Robot getIdleRobot() {
		Robot idleRobot = robots.get(0);
		for (int i=0; i<robots.size();i++) {
				Robot robot = robots.get(i);
				int idleLenght = (idleRobot.getPath().size()-1)*1000+idleRobot.getTrajectory().size();
				int robotLenght = (robot.getPath().size()-1)*1000+robot.getTrajectory().size();
				if (idleLenght > robotLenght & !(robot.getPath().get(0) instanceof Puck)) {
					idleRobot=robot;
			}
		}
		return idleRobot;
	}
	
}
	
	
	
