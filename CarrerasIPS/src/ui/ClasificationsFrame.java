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
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class ClasificationsFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panelSeleccion;
	private JScrollPane scrollPane;
	private JComboBox<String> cbClasificacion;
	private JPanel paneCentral;
	private JPanel paneInformacion;
	private JLabel txtSexo;
	private JLabel txtNombre;
	private JLabel txtTiempo;
	private List<ClasificationDto> clasificaciones = new ArrayList<ClasificationDto>();
	private JPanel panelBotones;
	private JButton btnVolver;
	private JButton btnCancelar;
	private ClasificationController controller;
	private JSplitPane splitPane;
	private JPanel panel_1;
	private JPanel panel;
	private JPanel pnlSelcNort;
	private JLabel lblFiltro;
	private JPanel pnlSelecCenter;
	private JLabel lblCategorias;
	private JPanel pnlCbCat;
	private JComboBox<String> cbCategory;
	private JPanel paneParticipantes;
	private JPanel pnlTodos;
	private JButton btnNewButton;
	private JLabel lblCategoria;
	private JPanel pnlNum;
	private JLabel lblPos;
	private JLabel lblNewLabel;
	private JLabel pnlClub;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ClasificationsFrame(List<ClasificationDto> clasificaciones2) {
		setTitle("Clasificaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 984, 647);
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
			//scrollPane.setRowHeaderView(getPaneInformacion());
		}
		return scrollPane;
	}

	public JComboBox<String> getCbCategory() {
		if (cbClasificacion == null) {
			cbClasificacion = new JComboBox<String>();
			cbClasificacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int indice = cbClasificacion.getSelectedIndex();
					if(indice < 3) {
						String seleccion = cbClasificacion.getItemAt(indice);
						clasificaciones.sort(new ComparatorClasificaciones());

						paneParticipantes.setLayout(new GridLayout(Math.max(10, clasificaciones.size()), 1, 0, 0));
						paneParticipantes.removeAll();
						crearPanelesCarrera(clasificaciones, seleccion);
					}
					else {
						clasificaciones.sort(new ComparatorClasificaciones());

						paneParticipantes.setLayout(new GridLayout(Math.max(10, clasificaciones.size()), 1, 0, 0));
						paneParticipantes.removeAll();
						controller.loadRows((String)getCbCategory().getSelectedItem(), paneParticipantes);

					}
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
			paneInformacion.setBorder(new EmptyBorder(6, 0, 6, 0));
			paneInformacion.setLayout(new GridLayout(0, 6, 0, 0));
			paneInformacion.add(getPnlNum());
			paneInformacion.add(getTxtSexo());
			paneInformacion.add(getTxtNombre());
			paneInformacion.add(getPnlClub());
			paneInformacion.add(getLblCategoria());
			paneInformacion.add(getTxtTiempo());
		}
		return paneInformacion;
	}

	private JLabel getTxtSexo() {
		if (txtSexo == null) {
			txtSexo = new JLabel();
			txtSexo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtSexo.setHorizontalAlignment(SwingConstants.CENTER);
			txtSexo.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtSexo.setText("Sexo");
		}
		return txtSexo;
	}

	private JLabel getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JLabel();
			txtNombre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
			txtNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtNombre.setText("Nombre");
		}
		return txtNombre;
	}

	private JLabel getTxtTiempo() {
		if (txtTiempo == null) {
			txtTiempo = new JLabel();
			txtTiempo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			txtTiempo.setHorizontalAlignment(SwingConstants.CENTER);
			txtTiempo.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtTiempo.setText("Tiempo");
		}
		return txtTiempo;
	}

	public class ComparatorClasificaciones implements Comparator<ClasificationDto> {

		@Override
		public int compare(ClasificationDto o1, ClasificationDto o2) {
			if(o1.tiempoInicio.equals("--:--:--") && !o2.tiempoInicio.equals("--:--:--")) {
				return -1;
			}
			if(o2.tiempoInicio.equals("--:--:--") && !o1.tiempoInicio.equals("--:--:--")) {
				return 1;
			}
			if(o1.tiempoFinal.equals("--:--:--") && !o2.tiempoFinal.equals("--:--:--")) {
				return -1;
			}
			if(o2.tiempoFinal.equals("--:--:--") && !o1.tiempoFinal.equals("--:--:--")) {
				return 1;
			}
			
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



		int posicion = 1;
		for (ClasificationDto clasificacion : clasificaciones) {
			if (sexo.equals("ABSOLUTA")) {
				JPanel panelClasificacion = new JPanel();
				panelClasificacion.setLayout(new GridLayout(1, 5, 1, 2));
				panelClasificacion.setBackground(Color.white);
				JLabel txtDorsal = new JLabel();
				JLabel txtCategory = new JLabel();
				JLabel txtNombre = new JLabel();
				JLabel txtSexo = new JLabel();
				JLabel txtPosicion = new JLabel();
				JLabel txtTiempo = new JLabel();
				JPanel pnlNum = new JPanel();
				pnlNum.setBackground(Color.white);
				txtDorsal.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtCategory.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtNombre.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtSexo.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtPosicion.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtTiempo.setBorder(new LineBorder(new Color(0, 0, 0)));

				pnlNum.setLayout(new GridLayout(1, 2, 1, 0));


				panelClasificacion.add(pnlNum);
				// Creacion textField poscion
				txtPosicion.setText(posicion + "");
				txtPosicion.setHorizontalAlignment(JTextField.CENTER);
				pnlNum.add(txtPosicion);

				// Creacion textField Dorsal
				txtDorsal.setText(clasificacion.dorsal);
				txtDorsal.setHorizontalAlignment(JTextField.CENTER);
				pnlNum.add(txtDorsal);

				// Creacion textField sexo
				txtSexo.setText(clasificacion.sexo);
				panelClasificacion.add(txtSexo);
				txtSexo.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField nombre participante
				txtNombre.setText(ParticipantModel.getBasicData(clasificacion.dni).name);
				panelClasificacion.add(txtNombre);
				txtNombre.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField Dorsal
				txtCategory.setText(clasificacion.categoryname);
				panelClasificacion.add(txtCategory);
				txtCategory.setHorizontalAlignment(JTextField.CENTER);

				if( clasificacion.tiempoInicio.equals("--:--:--")){
					txtTiempo.setText("dns");
				}
				else if(clasificacion.tiempoFinal.equals("--:--:--")){
					txtTiempo.setText("dnf");
				}
				else {
					// Creacion textField tiempo
					String[] tiempoInicial = clasificacion.tiempoInicio.split(":");
					String[] tiempoFinal = clasificacion.tiempoFinal.split(":");

					int segundosIniciales = Integer.parseInt(tiempoInicial[0]) * 3600
							+ Integer.parseInt(tiempoInicial[1]) * 60 + Integer.parseInt(tiempoInicial[2]);
					int segundosFinales = Integer.parseInt(tiempoFinal[0]) * 3600 + Integer.parseInt(tiempoFinal[1]) * 60
							+ Integer.parseInt(tiempoFinal[2]);

					int segundosTotales = segundosFinales - segundosIniciales;

					int horas = segundosTotales / 3600;
					int minutos = (segundosTotales - 3600 * horas) / 60;
					int segundos = (segundosTotales - 3600 * horas) - (minutos * 60);

					String tiempo = horas + ":" + minutos + ":" + segundos;
					txtTiempo.setText(tiempo);
				}

				

				panelClasificacion.add(txtTiempo);
				txtTiempo.setHorizontalAlignment(JTextField.CENTER);

				paneParticipantes.add(panelClasificacion);
				paneParticipantes.repaint();
				this.setSize(this.getWidth() + 1, this.getHeight() + 1);
				this.setSize(this.getWidth() - 1, this.getHeight() - 1);
				posicion++;
			} else if (clasificacion.sexo.equals(sexo)) {
				JPanel panelClasificacion = new JPanel();
				panelClasificacion.setLayout(new GridLayout(1, 5, 1, 2));
				panelClasificacion.setBackground(Color.white);
				JLabel txtDorsal = new JLabel();
				JLabel txtCategory = new JLabel();
				JLabel txtNombre = new JLabel();
				JLabel txtSexo = new JLabel();
				JLabel txtPosicion = new JLabel();
				JLabel txtTiempo = new JLabel();
				JPanel pnlNum = new JPanel();
				pnlNum.setBackground(Color.white);
				txtDorsal.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtCategory.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtNombre.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtSexo.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtPosicion.setBorder(new LineBorder(new Color(0, 0, 0)));
				txtTiempo.setBorder(new LineBorder(new Color(0, 0, 0)));

				pnlNum.setLayout(new GridLayout(1, 2, 1, 0));


				panelClasificacion.add(pnlNum);
				// Creacion textField poscion
				txtPosicion.setText(posicion + "");
				txtPosicion.setHorizontalAlignment(JTextField.CENTER);
				pnlNum.add(txtPosicion);

				// Creacion textField Dorsal
				txtDorsal.setText(clasificacion.dorsal);
				txtDorsal.setHorizontalAlignment(JTextField.CENTER);
				pnlNum.add(txtDorsal);

				// Creacion textField sexo
				txtSexo.setText(clasificacion.sexo);
				panelClasificacion.add(txtSexo);
				txtSexo.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField nombre participante
				txtNombre.setText(ParticipantModel.getBasicData(clasificacion.dni).name);
				panelClasificacion.add(txtNombre);
				txtNombre.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField Dorsal
				txtCategory.setText(clasificacion.categoryname);
				panelClasificacion.add(txtCategory);
				txtCategory.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField tiempo
				if( clasificacion.tiempoInicio.equals("--:--:--")){
					txtTiempo.setText("dns");
				}
				else if(clasificacion.tiempoFinal.equals("--:--:--")){
					txtTiempo.setText("dnf");
				}
				else {
					// Creacion textField tiempo
					String[] tiempoInicial = clasificacion.tiempoInicio.split(":");
					String[] tiempoFinal = clasificacion.tiempoFinal.split(":");

					int segundosIniciales = Integer.parseInt(tiempoInicial[0]) * 3600
							+ Integer.parseInt(tiempoInicial[1]) * 60 + Integer.parseInt(tiempoInicial[2]);
					int segundosFinales = Integer.parseInt(tiempoFinal[0]) * 3600 + Integer.parseInt(tiempoFinal[1]) * 60
							+ Integer.parseInt(tiempoFinal[2]);

					int segundosTotales = segundosFinales - segundosIniciales;

					int horas = segundosTotales / 3600;
					int minutos = (segundosTotales - 3600 * horas) / 60;
					int segundos = (segundosTotales - 3600 * horas) - (minutos * 60);

					String tiempo = horas + ":" + minutos + ":" + segundos;
					txtTiempo.setText(tiempo);
				}
				panelClasificacion.add(txtTiempo);
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
			panel.add(getLblCategorias());
			panel.add(getPnlCbCat());
		}
		return panel;
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
			pnlSelecCenter.setLayout(new GridLayout(6, 2, 0, 0));
			pnlSelecCenter.add(getPanel());
			pnlSelecCenter.add(getPnlTodos());
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
	private JPanel getPaneParticipantes() {
		if (paneParticipantes == null) {
			paneParticipantes = new JPanel();
			paneParticipantes.setLayout(new BorderLayout(0, 0));
		}

		return paneParticipantes;
	}
	private JPanel getPnlTodos() {
		if (pnlTodos == null) {
			pnlTodos = new JPanel();
			pnlTodos.setBorder(new EmptyBorder(0, 5, 0, 5));
			pnlTodos.setLayout(new GridLayout(3, 3, 0, 0));
			pnlTodos.add(getBtnNewButton());
		}
		return pnlTodos;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Mostrar Todas");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.loadAll(paneParticipantes);
					//paneParticipantes.setLayout(new GridLayout(Math.max(10, clasificaciones.size()), 1, 0, 0));
				}
			});
		}
		return btnNewButton;
	}
	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel();
			lblCategoria.setText("Categoria");
			lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblCategoria.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return lblCategoria;
	}
	private JPanel getPnlNum() {
		if (pnlNum == null) {
			pnlNum = new JPanel();
			pnlNum.setLayout(new GridLayout(0, 2, 0, 0));
			pnlNum.add(getLblPos());
			pnlNum.add(getLblNewLabel());
		}
		return pnlNum;
	}
	private JLabel getLblPos() {
		if (lblPos == null) {
			lblPos = new JLabel("Posicion");
			lblPos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			lblPos.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblPos.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPos;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Dorsal");
			lblNewLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	private JLabel getPnlClub() {
		if (pnlClub == null) {
			pnlClub = new JLabel();
			pnlClub.setText("Club");
			pnlClub.setHorizontalAlignment(SwingConstants.CENTER);
			pnlClub.setFont(new Font("Tahoma", Font.BOLD, 14));
			pnlClub.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return pnlClub;
	}
}