package pgn.GUI;

import javax.swing.JDialog;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Ayuda extends JDialog {
	private static Ayuda ayuda;

	/**
	 * Create the dialog.
	 */
	private Ayuda() {
		setTitle("Ayuda");
		setResizable(false);
		setModal(false);
		setBounds(100, 100, 450, 334);
		getContentPane().setLayout(null);

		JTextArea ayudaText = new JTextArea();
		ayudaText.setBounds(12, 12, 426, 286);
		getContentPane().add(ayudaText);
		ayudaText.setText(
				"Bienvenido a la guía de ayuda del Concesionario de Coches."
				+ "\n\nEl menú archivo permite crear, abrir, guardar o guardar "
				+ "\ncomo un concesionario.\n\nMenú Coche:\nAlta: dá de alta coches"
				+ " en el concesionario.\nBaja: dá de baja coches del concesionario."
				+ "\nMostrar concesionario: muestra los coches dentro del concesionario\n\n"
				+ "Menú buscar: \nNos ofrece dos opciones de búsqueda, por matrícula y "
				+ "por color.\n\nMenú de ayuda: \nNos da información sobre los creadores"
				+ " o nos ofrece ayuda sobre el manejo \nde la aplicación\n");
		ayudaText.setEditable(false);
		
	}

	public static Ayuda ayudaSingleton() {
		if (ayuda == null)
			ayuda = new Ayuda();
		return ayuda;

	}
}
