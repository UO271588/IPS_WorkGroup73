package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.CarreraDto;

public class VentanaCompeticiones extends JFrame {

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
					VentanaCompeticiones frame = new VentanaCompeticiones();
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
	public VentanaCompeticiones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Titulo = new JTextField();
		Titulo.setBounds(186, 10, 196, 30);
		Titulo.setFont(new Font("Rockwell", Font.BOLD, 20));
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setText("Competiciones");
		contentPane.add(Titulo);
		Titulo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(87, 61, 400, 200);
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		crearPanelesCarrera(this.cargarCarreras());
		
	}
	
	public void crearPanelesCarrera(List<CarreraDto> carreras) {
		for(CarreraDto carrera: carreras) {
			JPanel panelCarrera = new JPanel();
			JTextField txtNombre = new JTextField();
			JTextField txtTipo = new JTextField();
			JTextField txtDistancia = new JTextField();
			JTextField txtCuota = new JTextField();
			JTextField txtFechaInscripcion = new JTextField();
			JTextField txtFechaCarrera = new JTextField();
			
			//Creacion textField nombre carrera
			txtNombre.setText(carrera.nombre);
			panelCarrera.add(txtNombre);
			txtNombre.setColumns(10);
			
			//Creacion textField tipo
			txtTipo.setText(carrera.tipo);
			panelCarrera.add(txtTipo);
			txtTipo.setColumns(10);
			
			//Creacion textFiled distancia
			txtDistancia.setText(carrera.distancia);
			panelCarrera.add(txtDistancia);
			txtDistancia.setColumns(10);
			
			//Creacion textField cuota inscripcion
			txtCuota.setText(carrera.precioInscripcion);
			panelCarrera.add(txtCuota);
			txtCuota.setColumns(10);
			
			//Creacion textField fechaLimite inscripcion
			txtFechaInscripcion.setText(carrera.fechaLimite);
			panelCarrera.add(txtFechaInscripcion);
			txtFechaInscripcion.setColumns(10);
			
			//Creacion textField fechaCompeticion
			txtFechaCarrera.setText(carrera.fechaCarrera);
			panelCarrera.add(txtFechaCarrera);
			txtFechaCarrera.setColumns(10);
			
			panel.add(panelCarrera);
		}		
	}
	
	private List<CarreraDto> cargarCarreras(){
		List<CarreraDto> carreras = new ArrayList<CarreraDto>();
		String SQL = "SELECT * FROM competition";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			pst = c.prepareStatement(SQL);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				CarreraDto carrera = new CarreraDto();
				carrera.nombre = rs.getString("NAME");
				carrera.tipo = rs.getString("TIPO");
				carrera.distancia = rs.getString("DISTANCE");
				carrera.precioInscripcion = rs.getString("INSCRIPTIONFEE");
				carrera.fechaLimite = rs.getString("INSCRIPTIONDATEEND");
				carrera.fechaCarrera = rs.getString("COMPETITIONDATE");
				carreras.add(carrera);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return carreras;
	}
}
