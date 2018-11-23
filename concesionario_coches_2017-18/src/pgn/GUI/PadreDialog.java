package pgn.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import pgn.negocio.Coche;
import pgn.negocio.CocheNoExistenteException;
import pgn.negocio.Modelo;
import pgn.negocio.Principal;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import pgn.negocio.Marca;
import pgn.negocio.MatriculaNovalidaException;

import javax.swing.ButtonGroup;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class PadreDialog extends JDialog {

	protected final JPanel contentPanel = new JPanel();
	protected JTextField textField_Matricula;

	final ButtonGroup buttonGroup = new ButtonGroup();
	public static JRadioButton rdbtnPlata;
	public static JRadioButton rdbtnRojo;
	public static JRadioButton rdbtnAzul;

	public JComboBox<Object> comboBox;
	public JComboBox<Marca> comboBox_1;

	public JButton okButton;
	public JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public PadreDialog() {
		disenioVentana();

		disenio_GrupoJRadioButton();

		disenio_Matricula();

		disenio_ComboBoxMarca();

		disenio_ComboBoxModelo();

		disenioButtonPane();
		
	}
	private void disenioVentana() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	}
	
	
	private void disenio_Matricula() {
		JLabel lblMatricula = new JLabel("MATRICULA:");
		lblMatricula.setBounds(56, 42, 75, 14);
		contentPanel.add(lblMatricula);
		
		textField_Matricula = new JTextField();
		textField_Matricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_Matricula.setForeground(java.awt.Color.BLACK);
			}

			public void focusLost(FocusEvent arg0) {
				try {
					textField_Matricula.setText(textField_Matricula.getText().toUpperCase().replaceAll("[- ]", ""));
					textField_Matricula.setForeground(java.awt.Color.GREEN);
					Principal.getCoche(textField_Matricula.getText());
				} catch (MatriculaNovalidaException e) {
					textField_Matricula.setForeground(java.awt.Color.RED);
				} catch (CocheNoExistenteException e) {
				}
			}
		});
		textField_Matricula.setBounds(159, 37, 100, 25);
		contentPanel.add(textField_Matricula);
		textField_Matricula.setColumns(10);
	}
	private void disenio_ComboBoxMarca() {
		JLabel lblMarca = new JLabel("MARCA:");
		lblMarca.setBounds(67, 93, 46, 14);
		contentPanel.add(lblMarca);

		comboBox_1 = new JComboBox<Marca>();
		comboBox_1.setModel(new DefaultComboBoxModel<Marca>(Marca.values()));
		comboBox_1.setSelectedItem(null);
		comboBox_1.setBounds(67, 118, 110, 22);
		contentPanel.add(comboBox_1);
	}
	
	private void disenio_ComboBoxModelo() {
		JLabel lblModelo = new JLabel("MODELO:");
		lblModelo.setBounds(292, 93, 84, 14);
		contentPanel.add(lblModelo);

		comboBox = new JComboBox<Object>();
		comboBox.setBounds(292, 118, 100, 22);
		comboBox.setModel(new DefaultComboBoxModel<Object>());
		contentPanel.add(comboBox);

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.setModel(new DefaultComboBoxModel<Object>(getModelo(comboBox_1)));
			}
		});
	}



	private void disenio_GrupoJRadioButton() {
		rdbtnPlata = new JRadioButton("PLATA");
		rdbtnPlata.setBounds(67, 174, 75, 23);
		contentPanel.add(rdbtnPlata);

		rdbtnRojo = new JRadioButton("ROJO");
		rdbtnRojo.setBounds(194, 174, 75, 23);
		contentPanel.add(rdbtnRojo);

		rdbtnAzul = new JRadioButton("AZUL");
		rdbtnAzul.setBounds(317, 174, 75, 23);
		contentPanel.add(rdbtnAzul);

		buttonGroup.add(rdbtnAzul);
		buttonGroup.add(rdbtnRojo);
		buttonGroup.add(rdbtnPlata);
	}

	/**
	 * Limpia todos los datos guardados.
	 */
	protected void limpiar() {
		textField_Matricula.setText("");
		textField_Matricula.setForeground(java.awt.Color.BLACK);
		buttonGroup.clearSelection();
		
		comboBox.setSelectedItem(null);
		comboBox_1.setSelectedItem(null);
	}

	/**
	 * Muestra el coche por el entorno gráfico
	 * 
	 * @param coche
	 */
	protected void mostrarCoche(Coche coche) {
		switch(coche.getColor()) {
		case AZUL:
			rdbtnAzul.setSelected(true);
			break;
		case PLATA:
			rdbtnPlata.setSelected(true);
			break;
		case ROJO:
			rdbtnRojo.setSelected(true);
			break;
		}
		comboBox_1.setSelectedItem(coche.getModelo().getMarca());
		comboBox.setSelectedItem(coche.getModelo());
		textField_Matricula.setText(coche.getMatricula());
	}

	protected Object[] getModelo(JComboBox<Marca> comboBoxMarca) {
		Marca marca = (Marca) comboBoxMarca.getSelectedItem();
		ArrayList<Modelo> modelos = new ArrayList<Modelo>();
		for (Modelo modelo : Modelo.values())
			if (modelo.getMarca() == marca)
				modelos.add(modelo);
		return modelos.toArray();
	}

	private void disenioButtonPane() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
			okButton = new JButton("Ok");
			okButton.setActionCommand("Ok");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		

			cancelButton = new JButton("Salir");
			cancelButton.setActionCommand("Salir");
			cancelButton.setText("Salir");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			buttonPane.add(cancelButton);
	}


}
