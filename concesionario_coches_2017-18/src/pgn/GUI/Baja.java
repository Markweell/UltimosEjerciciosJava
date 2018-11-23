package pgn.GUI;

import javax.swing.JOptionPane;
import pgn.negocio.CocheNoExistenteException;
import pgn.negocio.MatriculaNovalidaException;
import pgn.negocio.Principal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Baja extends PadreDialog implements Modificable {

	static JButton btnMostrarConcesionario;

	/**
	 * Create the dialog.
	 */
	public Baja() {
		modificarEntorno();

		btnMostrarConcesionario = new JButton("Mostrar Concesionario");
		btnMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (VentanaPrincipal.isEmpty()) {
					return;
				}
				limpiar();
				VentanaPrincipal.mostraConcesionario();
			}
		});
		btnMostrarConcesionario.setBounds(245, 214, 189, 23);
		contentPanel.add(btnMostrarConcesionario);

		okButton.setText("Eliminar Coche");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Principal.getCoche(textField_Matricula.getText());
					mostrarCoche(Principal.getCoche(textField_Matricula.getText()));
					if (JOptionPane.showConfirmDialog(contentPanel, "¿Estas seguro que desea eliminar?",
							"Eliminando... ", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
						limpiar();
						return;
					}
					if (Principal.baja(textField_Matricula.getText()))
						limpiar();
					if(VentanaPrincipal.isEmpty()) {
						JOptionPane.showMessageDialog(getContentPane(), "No quedan coches");
						dispose();
						}
				} catch (MatriculaNovalidaException e1) {
					JOptionPane.showMessageDialog(contentPanel, "Matricula No valida.");
				} catch (CocheNoExistenteException e1) {
					JOptionPane.showMessageDialog(contentPanel, "Este coche no está en el concesionario.");
				}
			}
		});
	}

	@Override
	public void modificarEntorno() {
		setTitle("Baja\r\n");
		rdbtnAzul.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		comboBox.setEnabled(false);
		comboBox_1.setEnabled(false);
	}
}
