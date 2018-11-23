package pgn.GUI;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import pgn.negocio.CocheNoExistenteException;
import pgn.negocio.MatriculaNovalidaException;
import pgn.negocio.Principal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class MuestraPorMatricula extends Baja {
	
	/**
	 * Create the dialog.
	 */
	public MuestraPorMatricula() {
		
		setTitle("Muestra Por Matricula\r\n");
		okButton.setVisible(false);
		btnMostrarConcesionario.setVisible(false);
		textField_Matricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				limpiar();
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mostrarCoche(Principal.getCoche(textField_Matricula.getText()));
				} catch (MatriculaNovalidaException e1) {
					JOptionPane.showMessageDialog(contentPanel, "Matricula No valida.");
				} catch (CocheNoExistenteException e1) {
					JOptionPane.showMessageDialog(contentPanel, "Este coche no está en el concesionario.");
				}
			}
		});
		btnBuscar.setBounds(317, 38, 91, 23);
		contentPanel.add(btnBuscar);
	}
}
