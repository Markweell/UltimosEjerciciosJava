package pgn.GUI;

import javax.swing.JOptionPane;
import pgn.negocio.CocheYaExistente;
import pgn.negocio.Color;
import pgn.negocio.ColorNullException;
import pgn.negocio.MatriculaNovalidaException;
import pgn.negocio.Modelo;
import pgn.negocio.ModeloNullException;
import pgn.negocio.Principal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Alta extends PadreDialog implements Modificable {

	static Color color;

	/**
	 * Create the dialog.
	 */
	public Alta() {
		modificarEntorno();

		eventos_ButonGroup();

		evento_AnadirCoche();
	}

	@Override
	public void modificarEntorno() {
		setTitle("Alta\r\n");
		setModal(true);
	}

	public static void eventos_ButonGroup() {
		rdbtnPlata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = Color.PLATA;
			}
		});
		rdbtnRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = Color.ROJO;
			}
		});
		rdbtnAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = Color.AZUL;
			}
		});
	}

	private void evento_AnadirCoche() {
		okButton.setText("Añadir Coche");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Principal.alta((Modelo) comboBox.getSelectedItem(), color, textField_Matricula.getText());
					limpiar();
					color = null;
				} catch (CocheYaExistente | ColorNullException | ModeloNullException | MatriculaNovalidaException e) {
					JOptionPane.showMessageDialog(contentPanel, "Error!  " + e.getMessage());
				}
			}
		});
	}

}
