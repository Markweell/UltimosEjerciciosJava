package pgn.GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import pgn.negocio.Principal;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

/**
 * Ventana inicial del concesionario.
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private static JPanel contentPane;
	static JFileChooser jFileChooser = new JFileChooser();
	static {
		jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".obj", "obj"));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				VentanaPrincipal frame = new VentanaPrincipal();
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		configuracion_Ventana();

		JMenuBar menuBar = disenio_MenuArchivo();

		disenio_MenuCoche(menuBar);

		disenio_MenuAyuda(menuBar);
	}

	private void configuracion_Ventana() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				salir();
			}
		});
		setResizable(false);
		setTitle("Concesionario IES Gr\u00E1n Capitan");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}

	private JMenuBar disenio_MenuArchivo() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevoConcesionarioAltmayusn = new JMenuItem("Nuevo concesionario");
		mntmNuevoConcesionarioAltmayusn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoConcesionario();
			}
		});
		mntmNuevoConcesionarioAltmayusn
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mnArchivo.add(mntmNuevoConcesionarioAltmayusn);

		JMenuItem mntmAbrirConcesionario = new JMenuItem("Abrir concesionario ...");
		mntmAbrirConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirConcesionario();
			}
		});
		mnArchivo.add(mntmAbrirConcesionario);

		JMenuItem mntmGuardarCtrls = new JMenuItem("Guardar");
		mntmGuardarCtrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guardar(Principal.getFile());
				} catch (CancelarProcesoException e1) {

				}
			}
		});
		mntmGuardarCtrls.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmGuardarCtrls);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como ...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guardarComo();
				} catch (CancelarProcesoException e1) {

				}
			}
		});
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		mnArchivo.add(mntmSalir);
		return menuBar;
	}

	/**
	 * Diseño del menu de coche.
	 * 
	 * @param menuBar
	 */
	private void disenio_MenuCoche(JMenuBar menuBar) {
		JMenu mnNewMenu = new JMenu("Coche");
		mnNewMenu.setMnemonic('C');
		menuBar.add(mnNewMenu);

		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altaCoche();
			}
		});
		mntmAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmAlta);

		JMenuItem mntmBaja = new JMenuItem("Baja");
		mntmBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isEmpty()) {
					return;
				}
				baja();
			}
		});
		mntmBaja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmBaja);

		JSeparator separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);

		JMenuItem mntmMostrarconcesionario = new JMenuItem("Mostrar Concesionario");
		mnNewMenu.add(mntmMostrarconcesionario);
		mntmMostrarconcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isEmpty()) {
					return;
				}
				mostraConcesionario();
			}
		});

		JMenu mnBuscar = new JMenu("Buscar");
		mnBuscar.setMnemonic('B');
		menuBar.add(mnBuscar);

		JMenuItem mntmBusquedaColor = new JMenuItem("Busqueda Color");
		mntmBusquedaColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isEmpty()) {
					return;
				}
				muestraPorColor();
			}
		});
		mnBuscar.add(mntmBusquedaColor);

		JMenuItem mntmBusquedaMatricula = new JMenuItem("Busqueda Matricula");
		mntmBusquedaMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isEmpty()) {
					return;
				}
				muestraPorMatricula();
			}
		});
		mnBuscar.add(mntmBusquedaMatricula);
	}

	private void disenio_MenuAyuda(JMenuBar menuBar) {
		JMenu mnAyuda = new JMenu("Help");
		mnAyuda.setMnemonic('H');
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acercaDe();
			}
		});
		mnAyuda.add(mntmAcercaDe);

		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						ayuda();
					}
				});
			}
		});
		mnAyuda.add(mntmAyuda);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public static boolean isEmpty() {
		if (!Principal.isEmpty())
			return false;
		JOptionPane.showMessageDialog(contentPane, "¡¡Concesionario vacio!!");
		return true;
	}

	/**
	 * Muestra el JDialog Alta
	 */
	private static void altaCoche() {
		Alta dialog = new Alta();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Muestra el JDialog MuestraConcesionario
	 */
	public static void mostraConcesionario() {
		MuestraConcesionario dialog = new MuestraConcesionario();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Inicia el JDialog Baja
	 */
	private static void baja() {
		Baja dialog = new Baja();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Inicia El JDialog MuestraPorMatricula
	 */
	private void muestraPorMatricula() {
		MuestraPorMatricula dialog = new MuestraPorMatricula();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Abre la ventana de mostrar por Color
	 */
	private void muestraPorColor() {
		MuestraPorColor dialog = new MuestraPorColor();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	private void acercaDe() {
		AcercaDe dialog = new AcercaDe();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	private void ayuda() {
		Ayuda ayuda = Ayuda.ayudaSingleton();
		ayuda.setVisible(true);
	}

	/**
	 * Crea un nuevo concesionario guardadndo los cambios
	 */
	private void nuevoConcesionario() {
		try {
			evitaPerdidaInformacion();
			Principal.nuevo();
		} catch (CancelarProcesoException e) {

		}
	}

	private void evitaPerdidaInformacion() throws CancelarProcesoException {
		if (preguntaGuardarCambios())
			guardar(Principal.getFile());
	}

	private boolean preguntaGuardarCambios() throws CancelarProcesoException {
		if (Principal.getModificado()) {
			switch (JOptionPane.showOptionDialog(getContentPane(), "¿Desea guardar los cambios efectuados?",
					"¿Quiere guardar el documento?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
					null)) {
			case JOptionPane.YES_OPTION:
				return true;
			case JOptionPane.CLOSED_OPTION:
			case JOptionPane.CANCEL_OPTION:
				throw new CancelarProcesoException("Accion Interrumpida");
			}
		}
		return false;
	}

	private void abrirConcesionario() {
		try {
			evitaPerdidaInformacion();
			if (jFileChooser.showDialog(getContentPane(), "Abrir Fichero") == JFileChooser.APPROVE_OPTION) {
				try {
					Principal.leer(jFileChooser.getSelectedFile());
				} catch (ClassNotFoundException | IOException e) {
					JOptionPane.showMessageDialog(getContentPane(), "Error a abrir el archivo");
				}
			}
		} catch (CancelarProcesoException e1) {
		}
	}

	private void guardar(File file) throws CancelarProcesoException {
		try {
			if (file == null)
				guardarComo();
			else {
				Principal.escribir(file);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(getContentPane(), "Error al guardar el archivo");
		}
	}

	private void guardarComo() throws CancelarProcesoException {
		if (jFileChooser.showDialog(getContentPane(), "Guardar como...") != JFileChooser.APPROVE_OPTION)
			throw new CancelarProcesoException("Accion Interrumpida");

		File file = jFileChooser.getSelectedFile();
		if (Principal.exist(file)) {
			int opcion = JOptionPane.showOptionDialog(getContentPane(),
					"Hay un archivo con el mismo nombre, ¿Desea sobreescribirlo?", "Guardar", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE, null, null, null);
			if (opcion != JOptionPane.YES_OPTION)
				throw new CancelarProcesoException("Accion Interrumpida");
		}
		guardar(file);
	}

	private void salir() {
		try {
			evitaPerdidaInformacion();
			System.exit(0);
		} catch (CancelarProcesoException e) {
		}

	}
}
