package pgn.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

@SuppressWarnings("serial")
public class Concesionario implements Serializable, Iterable<Coche> {
	/**
	 * Arraylist donde vamos a guardar todos los coches.
	 */
	ArrayList<Coche> concesionario=new ArrayList<Coche>();
	/**
	 * Da de alta un coche
	 * @param coche
	 * @throws CocheYaExistente si la matricula del coche ya esta repetida
	 * @throws MatriculaNovalidaException 
	 * @throws ModeloNullException 
	 * @throws ColorNullException 
	 */
	 
	public void alta (Modelo modelo,Color color, String matricula) throws CocheYaExistente, ColorNullException, ModeloNullException, MatriculaNovalidaException{
		Coche coche = new Coche(modelo, color, matricula);
		if (concesionario.contains(coche))
			throw new CocheYaExistente("Este coche ya existe");
		concesionario.add(coche);
	}
	
	
	/**
	 * @throws MatriculaNovalidaException 
	 * @throws CocheNoExistenteException 
	 * 
	 */
	public Coche getCoche(String matricula) throws MatriculaNovalidaException, CocheNoExistenteException {
	try {
		return concesionario.get(concesionario.indexOf(new Coche(matricula)));
	}catch(IndexOutOfBoundsException e){
		throw new CocheNoExistenteException("Ese coche no existe");
	}
	} 
	
	/**
	 * Da de baja un coche
	 * @param coche
	 * @throws MatriculaNovalidaException 
	 */
	public boolean baja(String matricula) throws MatriculaNovalidaException {
		return concesionario.remove(new Coche(matricula));
	}
	
	/**
	 * Devuelve el tamaño del ArrayList
	 */
	public int size() {
		return concesionario.size();
	}
	
	/**
	 * 
	 */
	public ArrayList<Coche> busquedaPorColor(Color color) {
		ArrayList<Coche> cochesPorColor = new ArrayList<Coche>();
		for (Coche coche : concesionario) {
			if (color==coche.getColor()) {
				cochesPorColor.add(coche);
			}
		}
		return cochesPorColor;
	}
	/**
	 * Muestra todos los coches del concesionario
	 */
	public String toString() {
		String mensaje="";
		for (Coche coche: concesionario) {
			mensaje+="\n" + coche.toString();
		}
		return mensaje;
	}
	
	/**
	 * Devuelve true en caso de que el concesionario esté vacío
	 * @return
	 */
	public boolean isEmpty() {
		return concesionario.isEmpty();
	}
	
	@Override
	public ListIterator<Coche> iterator() {
		return concesionario.listIterator();
	}

}
