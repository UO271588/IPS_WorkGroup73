package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.race.RaceDto;
import dbAccess.CompetitionsAccess;

public class RacesFrame extends JFrame {

	private JPanel contentPane;
	private JTextField Titulo;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RacesFrame frame = new RacesFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RacesFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Titulo = new JTextField();
		Titulo.setEditable(false);
		Titulo.setBounds(186, 10, 196, 30);
		Titulo.setFont(new Font("Rockwell", Font.BOLD, 20));
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setText("Competiciones");
		contentPane.add(Titulo);
		Titulo.setColumns(10);
		Titulo.setBorder(new EmptyBorder(5,5,5,5));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 51, 567, 304);
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		panel.setBorder(null);
		scrollPane.setViewportView(panel);
		
		
		CompetitionsAccess ca = new CompetitionsAccess();
		List<RaceDto> carreras = ca.findAllRaces();
		panel.setLayout(new GridLayout(0, 1, 0, carreras.size()));
		crearPanelesCarrera(carreras);
		
	}
	
	public void crearPanelesCarrera(List<RaceDto> carreras) {
		for(RaceDto carrera: carreras) {
			JPanel panelCarrera = new JPanel();
			JTextField txtNombre = new JTextField();
			JTextField txtTipo = new JTextField();
			JTextField txtDistancia = new JTextField();
			JTextField txtCuota = new JTextField();
			JTextField txtFechaInscripcion = new JTextField();
			JTextField txtFechaCarrera = new JTextField();
			JButton btnRegistro = new JButton();
			
			//Creacion textField nombre carrera
			txtNombre.setText(carrera.nombre);
			panelCarrera.add(txtNombre);
			txtNombre.setColumns(10);
			txtNombre.setHorizontalAlignment(JTextField.CENTER);
			
			//Creacion textField tipo
			txtTipo.setText(carrera.tipo);
			panelCarrera.add(txtTipo);
			txtTipo.setColumns(5);
			txtTipo.setHorizontalAlignment(JTextField.CENTER);
			
			//Creacion textFiled distancia
			txtDistancia.setText(Double.toString(carrera.distancia)+"Km");
			panelCarrera.add(txtDistancia);
			txtDistancia.setColumns(5);
			txtDistancia.setHorizontalAlignment(JTextField.CENTER);
			
			//Creacion textField cuota inscripcion
			txtCuota.setText(Double.toString(carrera.precioInscripcion)+"€");
			panelCarrera.add(txtCuota);
//			txtCuota.setColumns(6);
			txtCuota.setHorizontalAlignment(JTextField.CENTER);
			
			//Creacion textField fechaLimite inscripcion
			txtFechaInscripcion.setText(carrera.fechaLimite.toString());
			panelCarrera.add(txtFechaInscripcion);
//			txtFechaInscripcion.setColumns(8);
			txtFechaInscripcion.setHorizontalAlignment(JTextField.CENTER);
			
			//Creacion textField fechaCompeticion
			txtFechaCarrera.setText(carrera.fechaCarrera.toString());
			panelCarrera.add(txtFechaCarrera);
//			txtFechaCarrera.setColumns(8);
			txtFechaCarrera.setHorizontalAlignment(JTextField.CENTER);
			
			//Creacion boton de registro
			btnRegistro.setText("Registrarse");
			panelCarrera.add(btnRegistro);
			btnRegistro.setHorizontalAlignment(JTextField.CENTER);
			
			panel.add(panelCarrera);
		}		
	}
	
	
}
