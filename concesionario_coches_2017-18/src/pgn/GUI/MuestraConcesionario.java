package pgn.GUI;

import javax.swing.JButton;
import pgn.negocio.Coche;
import pgn.negocio.Principal;

import java.awt.event.ActionListener;
import java.util.ListIterator;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MuestraConcesionario extends PadreDialog implements Modificable{

	protected JButton fechaIzquierda;
	protected JButton fechaDerecha;
	
	ListIterator<Coche> iteradorConcesionario = Principal.getIteratorConcesionario();
	protected boolean movDerecha= true;

	/**
	 * Create the dialog.
	 */
	public MuestraConcesionario() {
		modificarEntorno();

		fechaIzquierda = new JButton("<");
		fechaIzquierda.setBounds(236, 204, 84, 23);
		contentPanel.add(fechaIzquierda);
		fechaIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moverIzquierda();
			}
		});
	
		fechaDerecha = new JButton(">");
		fechaDerecha.setBounds(327, 204, 84, 23);
		contentPanel.add(fechaDerecha);
		fechaDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moverDerecha();
			}
		});

		moverDerecha();
		comprobacionIterador();
		fechaIzquierda.setEnabled(false);
	}

	protected void comprobacionIterador() {
		fechaIzquierda.setEnabled(true);
		fechaDerecha.setEnabled(true);
		if (!iteradorConcesionario.hasPrevious()) {
			fechaIzquierda.setEnabled(false);
		}
		if (!iteradorConcesionario.hasNext()) {
			fechaDerecha.setEnabled(false);
		}
	}

	protected void moverDerecha() {
		if (iteradorConcesionario.hasNext())
			mostrarCoche((Coche) iteradorConcesionario.next());
		if(!movDerecha)
			if (iteradorConcesionario.hasNext())
				mostrarCoche((Coche) iteradorConcesionario.next());
		movDerecha=true;
		comprobacionIterador();
	}

	protected void moverIzquierda() {
		if (iteradorConcesionario.hasPrevious())
			mostrarCoche((Coche) iteradorConcesionario.previous());
		if(movDerecha)
			if (iteradorConcesionario.hasPrevious())
				mostrarCoche((Coche) iteradorConcesionario.previous());
		movDerecha=false;
		comprobacionIterador();
	}

	@Override
	public void modificarEntorno() {
		setTitle("Muestra Concesionario \r\n");
		rdbtnAzul.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnPlata.setEnabled(false);	
		comboBox.setEnabled(false);
		comboBox_1.setEnabled(false);
		textField_Matricula.setEnabled(false);
		okButton.setVisible(false);
		
	}

}
