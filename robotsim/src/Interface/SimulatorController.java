package Interface;

import java.io.IOException;

import fr.tp.inf112.projects.canvas.controller.CanvasViewerController;
import fr.tp.inf112.projects.canvas.controller.Observer;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasPersistenceManager;
import fr.tp.inf112.robotsim.model.Factory;

public class SimulatorController implements CanvasViewerController{
	
	private Factory factoryModel;
	private CanvasPersistenceManager persistenceManager;
	
	public SimulatorController(Factory factoryModel, CanvasPersistenceManager persistenceManager) {
	this.factoryModel = factoryModel;
	this.persistenceManager = persistenceManager;
	}
	
	public boolean addObserver(Observer observer) {
	return factoryModel.addObserver(observer);
	}
	public boolean removeObserver(Observer observer) {
	return factoryModel.removeObserver(observer);
	}
	
	
	public void startAnimation() {
		int time = 1;
		factoryModel.setSimulationStarted(true);
		while (factoryModel.isSimulationStarted()) {
			factoryModel.behave(time);
			time++;
		try {
		Thread.sleep(60);
		}
		catch (InterruptedException ex) {
		ex.printStackTrace();
			}
		}
	}
	
	public void stopAnimation() {
	factoryModel.setSimulationStarted(false);
	}
	
	@Override
	public boolean isAnimationRunning() {
		// TODO Auto-generated method stub
		return factoryModel.isSimulationStarted();
	}
	
	public Canvas read(String canvasId) throws IOException {
	    return persistenceManager.read(canvasId);
	}
	public void persist(Canvas canvasModel) throws IOException {
	    persistenceManager.persist(canvasModel);
	}
	public boolean delete(Canvas canvasModel) throws IOException {
	    return persistenceManager.delete(canvasModel);
	}
	@Override
	public Canvas getCanvas() {
		// TODO Auto-generated method stub
		return factoryModel;
	}
	@Override
	public void setCanvas(Canvas canvasModel) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public CanvasPersistenceManager getPersistenceManager() {
	    return persistenceManager;
	}
	
	
	

}
