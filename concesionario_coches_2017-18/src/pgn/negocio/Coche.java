/**
 * Coche
 * @author Marcos Gallardo Pérez
 * @version 1.0
 */
package pgn.negocio;

import java.io.Serializable;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
public class Coche implements Serializable{
	/**
	 * Patrón de la matricula.
	 */
	private static final String MATRICULA = "\\d{4}[\\s-]?[(A-Z)&&[^AEIOUQÑ]]{3}";
	/**
	 * MOdelo del coche
	 */
	private Modelo modelo;
	/**
	 * Color del coche
	 */
	private Color color;
	/**
	 * Matricula del coche.
	 */
	private String matricula;
	/**
	 * Patrones validos para la matricula
	 */
	private static Pattern patronMatricula = Pattern.compile(MATRICULA);

	/**
	 * Constructor del coche.
	 * 
	 * @param modelo
	 * @param color
	 * @param matricula
	 */
	Coche(Modelo modelo, Color color, String matricula)
			throws ColorNullException, ModeloNullException, MatriculaNovalidaException {
		setModelo(modelo);
		setColor(color);
		setMatricula(matricula);
	}
	/**
	 * Crea un coche solo con la matricula
	 * @param matricula
	 * @throws MatriculaNovalidaException
	 */
	Coche (String matricula) throws MatriculaNovalidaException{
		setMatricula(matricula);
	}
	/**
	 * Asigna la matrícula
	 * @param matricula
	 * @throws MatriculaNovalidaException salta cuando la matrícula no sigue el patrón de matrícula.
	 */
	private void setMatricula(String matricula) throws MatriculaNovalidaException {
		comprobarMatricula(matricula);
		this.matricula = getFormaCanonica(matricula);
	}
	/**
	 * Comprueba si un matricula cumple o no el patrón especificado. 
	 * @param matricula
	 * @throws MatriculaNovalidaException
	 */
	public static void comprobarMatricula(String matricula) throws MatriculaNovalidaException {
		if (!patronMatricula.matcher(matricula).matches())
			throw new MatriculaNovalidaException("La matricula no cumple el patrón especificado");
	}
	private String getFormaCanonica(String matricula) {
		return matricula.replaceAll("[- ]","");
	}
	
	/**
	 * Devuelve el color del coche 
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Devuleve la Matricula del coche
	 * @return Matricula
	 */
	public String getMatricula() {
		return matricula;
	}
	
	/**
	 * Devuelve el modulo
	 * @return
	 */
	public Modelo getModelo() {
		return modelo;
	}
	
	/**
	 * Asigna el color al coche
	 * @param color
	 * @throws ColorNullException salta cuando el color es nulo
	 */
	private void setColor(Color color) throws ColorNullException {
		if (color == null) 
			throw new ColorNullException("Color nulo, el color debe ser uno válido");
		
		this.color = color;
	}
	/**
	 * Asigna el modelo del coche. 
	 * @param modelo
	 * @throws ModeloNullException
	 */
	private void setModelo(Modelo modelo) throws ModeloNullException {
		if (modelo == null)
			throw new ModeloNullException("Modelo nulo, el modelo debe ser válido");
		
		this.modelo = modelo;
	}
	/**
	 * Muestra los campos del coche
	 */
	public String toString() {
		String[] tipoCoche= {"Seat","BMW"};
		int i=1;
		if(modelo == Modelo.CORDOBA||modelo ==Modelo.IBIZA||modelo ==Modelo.TOLEDO)
			i=0;
		String mensaje ="[" +tipoCoche[i]+" "+ modelo+"] de color: ["+color+"] con matricula["+matricula+"]";
		return mensaje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equalsIgnoreCase(other.matricula))
			return false;
		return true;
	}
	
}
