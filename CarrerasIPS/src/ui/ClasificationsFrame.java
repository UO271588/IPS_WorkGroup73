package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ClasificationController;
import model.clasification.ClasificationDto;
import model.participant.ParticipantModel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;

public class ClasificationsFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panelSeleccion;
	private JScrollPane scrollPane;
	private JPanel paneParticipantes;
	private JComboBox<String> cbClasificacion;
	private JPanel paneCentral;
	private JPanel paneInformacion;
	private JTextField txtPosicion;
	private JTextField txtSexo;
	private JTextField txtNombre;
	private JTextField txtTiempo;
	private List<ClasificationDto> clasificaciones = new ArrayList<ClasificationDto>();
	private JPanel panelBotones;
	private JButton btnVolver;
	private JButton btnCancelar;
	private ClasificationController controller;
	private JSplitPane splitPane;
	private JPanel panel_1;
	private JPanel panel;
	private JLabel lblBasicos;
	private JPanel pnlCB1;
	private JPanel pnlSelcNort;
	private JLabel lblFiltro;
	private JPanel pnlSelecCenter;
	private JLabel lblCategorias;
	private JPanel pnlCbCat;
	private JComboBox<String> cbCategory;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ClasificationsFrame(List<ClasificationDto> clasificaciones2) {
		setTitle("Clasificaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 588);
		this.clasificaciones = clasificaciones2;
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelBotones(), BorderLayout.SOUTH);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);
		controller = new ClasificationController(clasificaciones, this);
		controller.loadClasifications();
	}

	private JPanel getPanelSeleccion() {
		if (panelSeleccion == null) {
			panelSeleccion = new JPanel();
			panelSeleccion.setBorder(new EmptyBorder(7, 7, 7, 7));
			panelSeleccion.setLayout(new BorderLayout(0, 0));
			panelSeleccion.add(getPnlSelcNort(), BorderLayout.NORTH);
			panelSeleccion.add(getPnlSelecCenter(), BorderLayout.CENTER);
		}
		return panelSeleccion;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getPaneParticipantes());
		}
		return scrollPane;
	}

	private JPanel getPaneParticipantes() {
		if (paneParticipantes == null) {
			paneParticipantes = new JPanel();
			paneParticipantes.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return paneParticipantes;
	}

	private JComboBox<String> getCbClasificacion() {
		if (cbClasificacion == null) {
			cbClasificacion = new JComboBox<String>();
			cbClasificacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int indice = cbClasificacion.getSelectedIndex();
					String seleccion = cbClasificacion.getItemAt(indice);
					crearPanelesCarrera(clasificaciones, seleccion);
				}
			});
			cbClasificacion.setModel(new DefaultComboBoxModel<String>(new String[] { "HOMBRE", "MUJER", "ABSOLUTA" }));
			cbClasificacion.setSelectedIndex(2);
			cbClasificacion.setFont(new Font("Tahoma", Font.BOLD, 14));

		}
		return cbClasificacion;
	}

	private JPanel getPaneCentral() {
		if (paneCentral == null) {
			paneCentral = new JPanel();
			paneCentral.setLayout(new BorderLayout(0, 0));
			paneCentral.add(getScrollPane(), BorderLayout.CENTER);
			paneCentral.add(getPaneInformacion(), BorderLayout.NORTH);
		}
		return paneCentral;
	}

	private JPanel getPaneInformacion() {
		if (paneInformacion == null) {
			paneInformacion = new JPanel();
			paneInformacion.setLayout(new GridLayout(1, 0, 0, 0));
			paneInformacion.add(getTxtPosicion());
			paneInformacion.add(getTxtSexo());
			paneInformacion.add(getTxtNombre());
			paneInformacion.add(getTxtTiempo());
		}
		return paneInformacion;
	}

	private JTextField getTxtPosicion() {
		if (txtPosicion == null) {
			txtPosicion = new JTextField();
			txtPosicion.setHorizontalAlignment(SwingConstants.CENTER);
			txtPosicion.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtPosicion.setText("Posicion");
			txtPosicion.setColumns(10);
		}
		return txtPosicion;
	}

	private JTextField getTxtSexo() {
		if (txtSexo == null) {
			txtSexo = new JTextField();
			txtSexo.setHorizontalAlignment(SwingConstants.CENTER);
			txtSexo.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtSexo.setText("Sexo");
			txtSexo.setColumns(10);
		}
		return txtSexo;
	}

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
			txtNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtNombre.setText("Nombre");
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}

	private JTextField getTxtTiempo() {
		if (txtTiempo == null) {
			txtTiempo = new JTextField();
			txtTiempo.setHorizontalAlignment(SwingConstants.CENTER);
			txtTiempo.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtTiempo.setText("Tiempo");
			txtTiempo.setColumns(10);
		}
		return txtTiempo;
	}

	public class ComparatorClasificaciones implements Comparator<ClasificationDto> {

		@Override
		public int compare(ClasificationDto o1, ClasificationDto o2) {
			String[] tiempoInicialO1 = o1.tiempoInicio.split(":");
			String[] tiempoFinalO1 = o1.tiempoFinal.split(":");
			int segundosInicialesO1 = Integer.parseInt(tiempoInicialO1[0]) * 3600
					+ Integer.parseInt(tiempoInicialO1[1]) * 60 + Integer.parseInt(tiempoInicialO1[2]);
			int segundosFinalesO1 = Integer.parseInt(tiempoFinalO1[0]) * 3600 + Integer.parseInt(tiempoFinalO1[1]) * 60
					+ Integer.parseInt(tiempoFinalO1[2]);

			int segundosTotalesO1 = segundosFinalesO1 - segundosInicialesO1;

			String[] tiempoInicialO2 = o2.tiempoInicio.split(":");
			String[] tiempoFinalO2 = o2.tiempoFinal.split(":");

			int segundosInicialesO2 = Integer.parseInt(tiempoInicialO2[0]) * 3600
					+ Integer.parseInt(tiempoInicialO2[1]) * 60 + Integer.parseInt(tiempoInicialO2[2]);
			int segundosFinalesO2 = Integer.parseInt(tiempoFinalO2[0]) * 3600 + Integer.parseInt(tiempoFinalO2[1]) * 60
					+ Integer.parseInt(tiempoFinalO2[2]);

			int segundosTotalesO2 = segundosFinalesO2 - segundosInicialesO2;

			if (segundosTotalesO1 <= 0) {
				return 1;
			} else if (segundosTotalesO2 <= 0) {
				return -1;
			} else if (segundosTotalesO1 - segundosTotalesO2 == 0) {
				return -1;
			} else {
				return segundosTotalesO1 - segundosTotalesO2;
			}
		}
	}

	public void crearPanelesCarrera(List<ClasificationDto> clasificaciones, String sexo) {

		clasificaciones.sort(new ComparatorClasificaciones());

		paneParticipantes.setLayout(new GridLayout(Math.max(10, clasificaciones.size()), 1, 0, 0));
		paneParticipantes.removeAll();

		int posicion = 1;
		for (ClasificationDto clasificacion : clasificaciones) {
			if (sexo.equals("ABSOLUTA")) {
				JPanel panelClasificacion = new JPanel();
				panelClasificacion.setLayout(new GridLayout(1, 4, 0, 0));
				JTextField txtNombre = new JTextField();
				JTextField txtSexo = new JTextField();
				JTextField txtPosicion = new JTextField();
				JTextField txtTiempo = new JTextField();

				// Creacion textField poscion
				txtPosicion.setText(posicion + "");
				panelClasificacion.add(txtPosicion);
				txtPosicion.setColumns(10);
				txtPosicion.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField sexo
				txtSexo.setText(clasificacion.sexo);
				panelClasificacion.add(txtSexo);
				txtSexo.setColumns(10);
				txtSexo.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField nombre participante
				txtNombre.setText(ParticipantModel.getBasicData(clasificacion.dni).name);
				panelClasificacion.add(txtNombre);
				txtNombre.setColumns(10);
				txtNombre.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField tiempo
				String[] tiempoInicial = clasificacion.tiempoInicio.split(":");
				String[] tiempoFinal = clasificacion.tiempoFinal.split(":");
				int segundosIniciales = Integer.parseInt(tiempoInicial[0]) * 3600
						+ Integer.parseInt(tiempoInicial[1]) * 60 + Integer.parseInt(tiempoInicial[2]);
				int segundosFinales = Integer.parseInt(tiempoFinal[0]) * 3600 + Integer.parseInt(tiempoFinal[1]) * 60
						+ Integer.parseInt(tiempoFinal[2]);
				if (segundosIniciales != 0 && segundosFinales != 0) {
					int segundosTotales = segundosFinales - segundosIniciales;

					int horas = segundosTotales / 3600;
					int minutos = (segundosTotales - 3600 * horas) / 60;
					int segundos = (segundosTotales - 3600 * horas) - (minutos * 60);

					String tiempo = horas + ":" + minutos + ":" + segundos;
					txtTiempo.setText(tiempo);
				} else {
					txtTiempo.setText("---");
				}
				panelClasificacion.add(txtTiempo);
				txtTiempo.setColumns(10);
				txtTiempo.setHorizontalAlignment(JTextField.CENTER);

				paneParticipantes.add(panelClasificacion);
				paneParticipantes.repaint();
				this.setSize(this.getWidth() + 1, this.getHeight() + 1);
				this.setSize(this.getWidth() - 1, this.getHeight() - 1);
				posicion++;
			} else if (clasificacion.sexo.equals(sexo)) {
				JPanel panelClasificacion = new JPanel();
				panelClasificacion.setLayout(new GridLayout(1, 4, 0, 0));
				JTextField txtNombre = new JTextField();
				JTextField txtSexo = new JTextField();
				JTextField txtPosicion = new JTextField();
				JTextField txtTiempo = new JTextField();

				// Creacion textField poscion
				txtPosicion.setText(posicion + "");
				panelClasificacion.add(txtPosicion);
				txtPosicion.setColumns(10);
				txtPosicion.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField sexo
				txtSexo.setText(clasificacion.sexo);
				panelClasificacion.add(txtSexo);
				txtSexo.setColumns(10);
				txtSexo.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField nombre participante
				txtNombre.setText(ParticipantModel.getBasicData(clasificacion.dni).name);
				panelClasificacion.add(txtNombre);
				txtNombre.setColumns(10);
				txtNombre.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField tiempo
				String[] tiempoInicial = clasificacion.tiempoInicio.split(":");
				String[] tiempoFinal = clasificacion.tiempoFinal.split(":");

				int segundosIniciales = Integer.parseInt(tiempoInicial[0]) * 3600
						+ Integer.parseInt(tiempoInicial[1]) * 60 + Integer.parseInt(tiempoInicial[2]);
				int segundosFinales = Integer.parseInt(tiempoFinal[0]) * 3600 + Integer.parseInt(tiempoFinal[1]) * 60
						+ Integer.parseInt(tiempoFinal[2]);

				if (segundosIniciales != 0 && segundosFinales != 0) {
					int segundosTotales = segundosFinales - segundosIniciales;

					int horas = segundosTotales / 3600;
					int minutos = (segundosTotales - 3600 * horas) / 60;
					int segundos = (segundosTotales - 3600 * horas) - (minutos * 60);

					String tiempo = horas + ":" + minutos + ":" + segundos;
					txtTiempo.setText(tiempo);
				} else {
					txtTiempo.setText("---");
				}
				panelClasificacion.add(txtTiempo);
				txtTiempo.setColumns(10);
				txtTiempo.setHorizontalAlignment(JTextField.CENTER);

				paneParticipantes.add(panelClasificacion);
				paneParticipantes.repaint();
				this.setSize(this.getWidth() + 1, this.getHeight() + 1);
				this.setSize(this.getWidth() - 1, this.getHeight() - 1);
				posicion++;
			}
		}
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelBotones.add(getBtnVolver());
		}
		return panelBotones;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
				}
			});
		}
		return btnVolver;
	}

	private void cerrarVentana() {
		this.dispose();
	}

	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setDividerSize(2);
			splitPane.setRightComponent(getPaneCentral());
			splitPane.setLeftComponent(getPanelSeleccion());
		}
		return splitPane;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			panel.add(getLblBasicos());
			panel.add(getPnlCB1());
			panel.add(getLblCategorias());
			panel.add(getPnlCbCat());
		}
		return panel;
	}
	private JLabel getLblBasicos() {
		if (lblBasicos == null) {
			lblBasicos = new JLabel("Basicos:");
			lblBasicos.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblBasicos;
	}
	private JPanel getPnlCB1() {
		if (pnlCB1 == null) {
			pnlCB1 = new JPanel();
			pnlCB1.add(getCbClasificacion());
		}
		return pnlCB1;
	}
	private JPanel getPnlSelcNort() {
		if (pnlSelcNort == null) {
			pnlSelcNort = new JPanel();
			pnlSelcNort.add(getLblFiltro());
		}
		return pnlSelcNort;
	}
	private JLabel getLblFiltro() {
		if (lblFiltro == null) {
			lblFiltro = new JLabel("Filtros");
			lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblFiltro;
	}
	private JPanel getPnlSelecCenter() {
		if (pnlSelecCenter == null) {
			pnlSelecCenter = new JPanel();
			pnlSelecCenter.add(getPanel());
		}
		return pnlSelecCenter;
	}
	private JLabel getLblCategorias() {
		if (lblCategorias == null) {
			lblCategorias = new JLabel("Categorias:");
			lblCategorias.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCategorias;
	}
	private JPanel getPnlCbCat() {
		if (pnlCbCat == null) {
			pnlCbCat = new JPanel();
			pnlCbCat.add(getCbCategory());
		}
		return pnlCbCat;
	}
	public JComboBox<String> getCbCategory() {
		if (cbCategory == null) {
			cbCategory = new JComboBox<String>();
			cbCategory.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clasificaciones.sort(new ComparatorClasificaciones());
					System.out.println("hola");

					paneParticipantes.setLayout(new GridLayout(Math.max(10, clasificaciones.size()), 1, 0, 0));
					paneParticipantes.removeAll();
					controller.loadRows((String)getCbCategory().getSelectedItem(), paneParticipantes);
				}
			});
			cbCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return cbCategory;
	}
}