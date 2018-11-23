package pgn.GUI;

import javax.swing.JButton;
import pgn.negocio.Color;
import pgn.negocio.Principal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class MuestraPorColor extends MuestraConcesionario {
	
	JButton btnBuscar;
	int numeroCochesDeEseColor;
	JLabel lblElNumeroDe;
	/**
	 * Create the dialog.
	 */
	public MuestraPorColor() {
		configuracionInicial();
		
		lblElNumeroDe = new JLabel("");
		lblElNumeroDe.setBounds(26, 170, 396, 23);
		contentPanel.add(lblElNumeroDe);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				movDerecha = true;
				moverDerecha();
				comprobacionIterador();
				fechaIzquierda.setEnabled(false);
				lblElNumeroDe.setText("El numero de coches de ese color es: "+ numeroCochesDeEseColor);
				btnBuscar.setEnabled(false);
			}
		});
		btnBuscar.setBounds(67, 204, 91, 23);
		contentPanel.add(btnBuscar);

		rdbtnAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionButton(rdbtnAzul,Color.AZUL);
			}
		});
		rdbtnRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionButton(rdbtnRojo,Color.ROJO);
			}
		});
		rdbtnPlata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionButton(rdbtnPlata,Color.PLATA);
			}
		});
	}
	
	private void configuracionInicial() {
		setTitle("Muestra Por Color\r\n");
		rdbtnAzul.setLocation(317, 59);
		rdbtnRojo.setLocation(317, 33);
		rdbtnPlata.setLocation(317, 7);
		rdbtnAzul.setEnabled(true);
		rdbtnRojo.setEnabled(true);
		rdbtnPlata.setEnabled(true);
		desactivarFlechas();
		limpiar();
	}
	
	private void desactivarFlechas() {
		fechaDerecha.setEnabled(false);
		fechaIzquierda.setEnabled(false);
	}
	/**
	 * Acciones que ocurren cuando activas el JRadioButton
	 * @param radioButton
	 * @param color
	 */
	private void accionButton(JRadioButton radioButton,Color color) {
		iteradorConcesionario = Principal.busquedaPorColor(color).listIterator();
		numeroCochesDeEseColor = Principal.busquedaPorColor(color).size();
		limpiar();
		radioButton.setSelected(true);
		desactivarFlechas();
		btnBuscar.setEnabled(true);
		lblElNumeroDe.setText("");
	}

}
