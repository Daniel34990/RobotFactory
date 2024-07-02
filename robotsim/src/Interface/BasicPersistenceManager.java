package Interface;

import java.io.*;
import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasChooser;
import fr.tp.inf112.projects.canvas.model.impl.AbstractCanvasPersistenceManager;

public class BasicPersistenceManager extends AbstractCanvasPersistenceManager{
	
	/*private final String basePath = "/Users/daniel/eclipse"; pour moi*/
	
	public BasicPersistenceManager(CanvasChooser canvasChooser) {
		super(canvasChooser);
		// TODO Auto-generated constructor stub
	}

	@Override
    public Canvas read(String canvasId) throws IOException {
        File file = new File(canvasId);
        if (!file.exists()) {
            throw new FileNotFoundException("Le fichier " + file.getPath() + " n'existe pas.");
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Canvas) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Erreur lors de la désérialisation du Canvas", e);
        }
    }

	public void persist(Canvas canvasModel) throws IOException {
        /*File file = new File(basePath, canvasModel.getId()+".cvs"); pour moi*/
		File file = new File(canvasModel.getId()+".cvs");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(canvasModel);
        } catch (IOException e) {
            throw new IOException("Impossible de sauvegarder le Canvas", e);
        }
    }

	@Override
    public boolean delete(Canvas canvasModel) throws IOException {
        File file = new File(canvasModel.getId());
        if (!file.exists()) {
            throw new FileNotFoundException("Le fichier " + file.getPath() + " à supprimer n'existe pas.");
        }

        return file.delete();
    }
}
