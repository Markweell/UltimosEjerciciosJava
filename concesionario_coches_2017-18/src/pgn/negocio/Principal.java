package pgn.negocio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
/**
 * Envoltorio del concesionario donde vamos a comprobar si está o no modificado. 
 * @author Marcos Gallardo Pérez
 */
public class Principal {

	private static Concesionario concesionario = new Concesionario();
	private static boolean modificado = false;

	/**
	 * Da de alta un coche en el concesionario.
	 * 
	 * @param matricula
	 * @param color
	 * @param modelo
	 * @throws CocheYaExistente
	 * @throws ColorNullException
	 * @throws ModeloNullException
	 * @throws MatriculaNovalidaException
	 */
	public static void alta(Modelo modelo, Color color,String matricula)
			throws CocheYaExistente, ColorNullException, ModeloNullException, MatriculaNovalidaException {
		concesionario.alta(modelo, color, matricula);
		setModificado(true);
	}

	/**
	 * Da de baja un coche en el concesionario.
	 * 
	 * @param matricula
	 * @return
	 * @throws MatriculaNovalidaException
	 */
	public static boolean baja(String matricula) throws MatriculaNovalidaException {
		if (concesionario.baja(matricula)) {
			setModificado(true);
			return true;
		}
		return false;
	}

	/**
	 * Obtienes el concesionario.
	 * 
	 * @return Concesionario
	 */
	public static ListIterator<Coche> getIteratorConcesionario() {
		return concesionario.iterator();
	}

	/**
	 * Devuelve true en caso de el que concesionario este vacío
	 * 
	 * @return
	 */
	public static boolean isEmpty() {
		return concesionario.isEmpty();
	}

	/**
	 * Obtienes el tamaño del concesionario
	 * 
	 * @return Tamaño ArrayList
	 */
	public static int size() {
		return concesionario.size();
	}

	/**
	 * Busqueda de un coche en el concesionario por la Matricula
	 * 
	 * @param matricula
	 * @return Coche
	 * @throws MatriculaNovalidaException
	 * @throws CocheNoExistenteException
	 */
	public static Coche getCoche(String matricula) throws MatriculaNovalidaException, CocheNoExistenteException {
		return concesionario.getCoche(matricula);
	}

	/**
	 * Busqueda del coche en el concesionario por color
	 * 
	 * @param color
	 * @return
	 * @throws ColorNullException
	 */
	public static ArrayList<Coche> busquedaPorColor(Color color) {
		return concesionario.busquedaPorColor(color);
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static boolean esValida(String string) {
		try {
			Coche.comprobarMatricula(string);
			return true;
		} catch (MatriculaNovalidaException e) {
			return false;
		}
	}

	/**
	 * Cambia el estado de modificado.
	 * 
	 * @param modificado
	 */
	public static void setModificado(boolean modificado) {
		Principal.modificado = modificado;
	}

	/**
	 * Obtienes el estado de Modificado
	 * @return True en caso de que este modificado
	 */
	public static boolean getModificado() {
		return modificado;
	}

	/**
	 * Crea un nuevo concesionario.
	 */
	public static void nuevo() {
		concesionario = new Concesionario();
		setModificado(false);
		Fichero.setFile(null);
	}

	/**
	 * Guarda el concesionario
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void escribir(File file) throws IOException {
		Fichero.escribir(file, concesionario);
		Fichero.setFile(file);
		setModificado(false);
	}

	/**
	 * Abre un concesionario
	 * @param file
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void leer(File file) throws ClassNotFoundException, IOException {
		concesionario = Fichero.leer(file);
		Fichero.setFile(file);
	}

	/**
	 * Comprueba si existe un concesionario.
	 * 
	 * @param file
	 * @return true en caso de que exista el concesionario.
	 */
	public static boolean exist(File file) {
		return Fichero.exist(file);
	}

	/**
	 * Modifica el campo File
	 * 
	 * @param file
	 */
	public static void setFile(File file) {
		Fichero.setFile(file);
	}

	/**
	 * Obtienes el campo File
	 * 
	 * @return File
	 */
	public static File getFile() {
		return Fichero.getFile();
	}

}
