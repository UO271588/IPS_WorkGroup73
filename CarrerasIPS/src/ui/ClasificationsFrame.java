package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.race.RaceDto;
import controller.InscripcionController;
import model.clasification.ClasificationAccess;
import model.clasification.ClasificationDto;
import model.inscription.InscriptionModel;
import model.participant.ParticipantModel;
import util.TimeUtil;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;
import java.awt.FlowLayout;

public class ClasificationsFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
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
	private JButton btnAceptar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClasificationsFrame frame = new ClasificationsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ClasificationsFrame() throws SQLException {
		setTitle("Clasificaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 588);
		ClasificationAccess ca = new ClasificationAccess();
		clasificaciones = ca.findAllByRace("3435476");
		añadirClasificaciones();
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPaneCentral(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getPanelBotones(), BorderLayout.SOUTH);
	}
	
	private void añadirClasificaciones() {
		ClasificationDto clasificacion = new ClasificationDto();
		clasificacion.dni = "2352352D";
		clasificacion.idcarrera = "3435476";
		clasificacion.sexo = "HOMBRE";
		clasificacion.posicion = "1";
		clasificacion.tiempoInicio = "17:45:48";
		clasificacion.tiempoFinal = "18:40:13";
		clasificaciones.add(clasificacion);
		ClasificationDto clasificacion1 = new ClasificationDto();
		clasificacion1.dni = "2352352352F";
		clasificacion1.idcarrera = "3435476";
		clasificacion1.sexo = "HOMBRE";
		clasificacion1.posicion = "1";
		clasificacion1.tiempoInicio = "17:45:49";
		clasificacion1.tiempoFinal = "18:55:45";
		clasificaciones.add(clasificacion1);
		ClasificationDto clasificacion2 = new ClasificationDto();
		clasificacion2.dni = "73472383B";
		clasificacion2.idcarrera = "3435476";
		clasificacion2.sexo = "MUJER";
		clasificacion2.posicion = "1";
		clasificacion2.tiempoInicio = "17:45:47";
		clasificacion2.tiempoFinal = "18:30:15";
		clasificaciones.add(clasificacion2);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getCbClasificacion());
		}
		return panel;
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
					crearPanelesCarrera(clasificaciones,seleccion);
				}
			});
			
			cbClasificacion.setFont(new Font("Tahoma", Font.BOLD, 14));
			cbClasificacion.setModel(new DefaultComboBoxModel<String>(new String[] {"HOMBRE", "MUJER", "ABSOLUTA"}));
			
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
			int segundosFinalesO1= Integer.parseInt(tiempoFinalO1[0]) * 3600 + Integer.parseInt(tiempoFinalO1[1]) * 60
					+ Integer.parseInt(tiempoFinalO1[2]);

			int segundosTotalesO1 = segundosFinalesO1 - segundosInicialesO1;
			
			String[] tiempoInicialO2 = o2.tiempoInicio.split(":");
			String[] tiempoFinalO2 = o2.tiempoFinal.split(":");

			int segundosInicialesO2 = Integer.parseInt(tiempoInicialO2[0]) * 3600
					+ Integer.parseInt(tiempoInicialO2[1]) * 60 + Integer.parseInt(tiempoInicialO2[2]);
			int segundosFinalesO2= Integer.parseInt(tiempoFinalO2[0]) * 3600 + Integer.parseInt(tiempoFinalO2[1]) * 60
					+ Integer.parseInt(tiempoFinalO2[2]);

			int segundosTotalesO2 = segundosFinalesO2 - segundosInicialesO2;
			
			return segundosTotalesO1-segundosTotalesO2;		
		}		
	} 

	public void crearPanelesCarrera(List<ClasificationDto> clasificaciones, String sexo) {
		
		clasificaciones.sort(new ComparatorClasificaciones());

		paneParticipantes.setLayout(new GridLayout(Math.max(10, clasificaciones.size()), 1, 0, 0));
		paneParticipantes.removeAll();

		int posicion = 1;
		for (ClasificationDto clasificacion : clasificaciones) {
			if(sexo.equals("ABSOLUTA")) {
				JPanel panelClasificacion = new JPanel();
				panelClasificacion.setLayout(new GridLayout(1, 4, 0, 0));
				JTextField txtNombre = new JTextField();
				JTextField txtSexo = new JTextField();
				JTextField txtPosicion = new JTextField();
				JTextField txtTiempo = new JTextField();

				// Creacion textField poscion
				txtPosicion.setText(posicion+"");
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

				int segundosTotales = segundosFinales - segundosIniciales;

				int horas = segundosTotales / 3600;
				int minutos = (segundosTotales - 3600 * horas) / 60;
				int segundos = (segundosTotales - 3600 * horas) - (minutos * 60);

				String tiempo = horas + ":" + minutos + ":" + segundos;
				txtTiempo.setText(tiempo);
				panelClasificacion.add(txtTiempo);
				txtTiempo.setColumns(10);
				txtTiempo.setHorizontalAlignment(JTextField.CENTER);

				paneParticipantes.add(panelClasificacion);
				paneParticipantes.repaint();
				this.setSize(this.getWidth()+1,this.getHeight()+1);
				this.setSize(this.getWidth()-1,this.getHeight()-1);
				posicion++;
			}else if (clasificacion.sexo.equals(sexo)) {
				JPanel panelClasificacion = new JPanel();
				panelClasificacion.setLayout(new GridLayout(1, 4, 0, 0));
				JTextField txtNombre = new JTextField();
				JTextField txtSexo = new JTextField();
				JTextField txtPosicion = new JTextField();
				JTextField txtTiempo = new JTextField();

				// Creacion textField poscion
				txtPosicion.setText(posicion+"");
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

				int segundosTotales = segundosFinales - segundosIniciales;

				int horas = segundosTotales / 3600;
				int minutos = (segundosTotales - 3600 * horas) / 60;
				int segundos = (segundosTotales - 3600 * horas) - (minutos * 60);

				String tiempo = horas + ":" + minutos + ":" + segundos;
				txtTiempo.setText(tiempo);
				panelClasificacion.add(txtTiempo);
				txtTiempo.setColumns(10);
				txtTiempo.setHorizontalAlignment(JTextField.CENTER);

				paneParticipantes.add(panelClasificacion);
				paneParticipantes.repaint();
				this.setSize(this.getWidth()+1,this.getHeight()+1);
				this.setSize(this.getWidth()-1,this.getHeight()-1);
				posicion ++;
			}
		}
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelBotones.add(getBtnAceptar());
			panelBotones.add(getBtnCancelar());
		}
		return panelBotones;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar\r\n");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
				}
			});
		}
		return btnAceptar;
	}
	
	private void cerrarVentana () {
		this.dispose();
	}
	
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
				}
			});
		}
		return btnCancelar;
	}
}
