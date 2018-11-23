package pgn.negocio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Fichero {
	
	private String nombreDelFichero = null;
	private static File file = null;
	/**
	 * Intenta leer un fichero.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Concesionario leer(String nombreFicheroALeer)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(nombreFicheroALeer)))) {
			return (Concesionario) in.readObject();
		}
	}

	/**
	 * Escribe en un fichero con el nombre del fichero que se le halla puesto.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void escribir(Concesionario concesionario) throws FileNotFoundException, IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(nombreDelFichero)))) {
			out.writeObject(concesionario);
		}
	}

	public void setNombreFichero(String nombreDelFichero) {
		this.nombreDelFichero = nombreDelFichero;
	}

	public String getNombreFichero() {
		return nombreDelFichero;
	}
	
	//---------------------------------------------------------------------------------
	
	public static File getFile() {
		return file;
	}
	
	public static void setFile(File file) {
		Fichero.file = file;
	}
	
	public static Concesionario leer(File file) throws ClassNotFoundException, IOException {
		file = comprobarExtension(file);
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			return (Concesionario) in.readObject();
		}
	}
	
	public static void escribir(File file, Concesionario concesionario) throws IOException {
		file = comprobarExtension(file);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file, false)))) {
			out.writeObject(concesionario);
		}
	}
	
	public static boolean exist(File file) {
		file = comprobarExtension(file);
		return file.exists();
	}
	
	static File comprobarExtension(File file) {
		String nombreFichero = file.getPath();//probar getName()
        if (!nombreFichero.endsWith(".obj"))
            return new File(nombreFichero + ".obj");
        return file;
	}

}
