package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import business.race.RaceDto;
import controller.InscripcionController;
import dbAccess.CompetitionsAccess;
import model.inscription.InscriptionModel;
import util.DbUtil;
import util.TimeUtil;
import util.UnexpectedException;
import java.awt.Toolkit;

public class RacesFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Titulo;
	private JPanel panel;
	private JPanel pnlNorth;
	private JLabel lblNombre;
	private JLabel lblDistancia;
	private JLabel lblTipo;
	private JLabel lblCuota;
	private JLabel lblFechaInscripcion;
	private JLabel lblFechaCarrera;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JFrame parent;


	/**
	 * Create the frame.
	 */
	public RacesFrame(JFrame parent) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RacesFrame.class.getResource("/img/WhatsApp Image 2020-12-02 at 20.02.37.jpeg")));
		setTitle("Carreras Disponibles");
		this.parent=parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 445);
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
		Titulo.setBorder(new EmptyBorder(5, 5, 5, 5));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 51, 765, 304);
		contentPane.add(scrollPane);

		JPanel panelCentral = new JPanel();
		scrollPane.setViewportView(panelCentral);
		panelCentral.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panelCentral.add(getPnlNorth(),BorderLayout.NORTH);
		panelCentral.add(panel, BorderLayout.CENTER);
		panel.setBorder(null);

		CompetitionsAccess ca = new CompetitionsAccess();
		List<RaceDto> carreras = ca.findAllRaces();

		Collections.sort(carreras, new Comparator<RaceDto>() {
			@Override
			public int compare(RaceDto p1, RaceDto p2) {

				return ((RaceDto) p1).fechaLimite.compareTo(((RaceDto) p2).fechaLimite);

			}
		});
		panel.setLayout(new GridLayout(carreras.size(), 1, 0, 0));
		contentPane.add(getBtnNewButton_1());
		if (carreras.size() < 10) {
			panel.setLayout(new GridLayout(10, 0, 0, 0));
		}
		
		crearPanelesCarrera(carreras);

	}
	public double getCantidad(String idCompeticion) {
		String sql = "select d.fee from inscription_deadline d, inscription i where d.idcompetition=? and i.inscriptiondate>=d.initialdate and i.inscriptiondate<=d.finaldate";
		double cantidad = 0;
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, idCompeticion);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cantidad = rs.getDouble("fee");
			}
			

			return cantidad;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				cn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
public Date getFechaInicioCarrera(String idcompetition) {
		
		String sql = "select min(initialdate) from inscription_deadline where idcompetition=?";
		String fecha = "";
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, idcompetition);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fecha = rs.getString(1);
				
			}

			return TimeUtil.isoStringToDate(fecha);
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				cn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public int getAforoActual(String id) {
		String sql = "select count(*) from inscription where idcompetition=?";
		int aforo = 0;
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				aforo = rs.getInt(1);
			}
			

			return aforo;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				cn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void crearPanelesCarrera(List<RaceDto> carreras) {
		for (RaceDto carrera : carreras) {
			if (carrera.fechaCarrera.compareTo(Calendar.getInstance().getTime()) > 0
					&& carrera.fechaLimite.compareTo(Calendar.getInstance().getTime()) > 0) {
				int aforoActual = getAforoActual(carrera.id);
				Date inicio= getFechaInicioCarrera(carrera.id);
				if(inicio.compareTo(Calendar.getInstance().getTime())<=0) {
				JPanel panelCarrera = new JPanel();
				panelCarrera.setLayout(new GridLayout(0,7,0,0));
				JTextField txtNombre = new JTextField();
				JTextField txtTipo = new JTextField();
				JTextField txtDistancia = new JTextField();
				JTextField txtCuota = new JTextField();
				JTextField txtFechaInscripcion = new JTextField();
				JTextField txtFechaCarrera = new JTextField();
				JButton btnRegistro = new JButton();

				// Creacion textField nombre carrera
				txtNombre.setText(carrera.nombre);
				panelCarrera.add(txtNombre);
				txtNombre.setColumns(10);
				txtNombre.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField tipo
				txtTipo.setText(carrera.tipo);
				panelCarrera.add(txtTipo);
				txtTipo.setColumns(5);
				txtTipo.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textFiled distancia
				txtDistancia.setText(Double.toString(carrera.distancia) + "Km");
				panelCarrera.add(txtDistancia);
				txtDistancia.setColumns(5);
				txtDistancia.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField cuota inscripcion
				txtCuota.setText(getCantidad(carrera.id) + "€");
				panelCarrera.add(txtCuota);
//			txtCuota.setColumns(6);
				txtCuota.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField fechaLimite inscripcion
				txtFechaInscripcion.setText(TimeUtil.dateToIsoString(carrera.fechaLimite));
				panelCarrera.add(txtFechaInscripcion);
//			txtFechaInscripcion.setColumns(8);
				txtFechaInscripcion.setHorizontalAlignment(JTextField.CENTER);

				// Creacion textField fechaCompeticion
				txtFechaCarrera.setText(TimeUtil.dateToIsoString(carrera.fechaCarrera));
				panelCarrera.add(txtFechaCarrera);
//			txtFechaCarrera.setColumns(8);
				txtFechaCarrera.setHorizontalAlignment(JTextField.CENTER);

				// Creacion boton de registro
				btnRegistro.setText("Registrarse");
				panelCarrera.add(btnRegistro);
				btnRegistro.setHorizontalAlignment(JTextField.CENTER);
				System.out.print("aforo actual: "+ aforoActual);
				System.out.print("aforo maximo: "+ carrera.aforoMax);
				if (aforoActual >= carrera.aforoMax) {
					btnRegistro.setEnabled(false);
				} else {
					btnRegistro.setEnabled(true);
				}
				btnRegistro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									
									
									InscriptionModel im = new InscriptionModel(carrera);
									InscripcionFrame iv = new InscripcionFrame(carrera, getVentana());
									InscripcionController ic = new InscripcionController(im, iv);
									iv.setVisible(true);
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				});

				panel.add(panelCarrera);
			}
			}
		}
	}
	public JFrame getVentana() {
		return this;
	}
	public void close() {
		parent.setVisible(true);
		this.dispose();
		
		
	}
	
	public JFrame getAnterior() {

		return this.parent;
	}

	private JPanel getPnlNorth() {
		if (pnlNorth == null) {
			pnlNorth = new JPanel();
			pnlNorth.setAutoscrolls(true);
			pnlNorth.setBorder(new EmptyBorder(1, 0, 1, 0));
			pnlNorth.setLayout(new GridLayout(0,7,0,0));
			pnlNorth.add(getLblNombre());
			pnlNorth.add(getLblTipo());
			pnlNorth.add(getLblDistancia());
			pnlNorth.add(getLblCuota());
			pnlNorth.add(getLblFechaInscripcion());
			pnlNorth.add(getLblFechaCarrera());
		}
		return pnlNorth;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblNombre;
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo");
			lblTipo.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblTipo;
	}

	private JLabel getLblCuota() {
		if (lblCuota == null) {
			lblCuota = new JLabel("Cuota");
			lblCuota.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblCuota.setHorizontalAlignment(SwingConstants.CENTER);
			lblCuota.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblCuota;
	}

	private JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("Distancia");
			lblDistancia.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblDistancia.setHorizontalAlignment(SwingConstants.CENTER);
			lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDistancia.setSize(40, getHeight());;
		}
		return lblDistancia;
	}

	private JLabel getLblFechaInscripcion() {
		if (lblFechaInscripcion == null) {
			lblFechaInscripcion = new JLabel("Fecha Fin Inscripcion");
			lblFechaInscripcion.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblFechaInscripcion.setHorizontalAlignment(SwingConstants.CENTER);
			lblFechaInscripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblFechaInscripcion;
	}

	private JLabel getLblFechaCarrera() {
		if (lblFechaCarrera == null) {
			lblFechaCarrera = new JLabel("Fecha Carrera");
			lblFechaCarrera.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblFechaCarrera.setHorizontalAlignment(SwingConstants.CENTER);
			lblFechaCarrera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblFechaCarrera;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("ATR\u00C1S");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					close();
				}
				
			});
			btnNewButton_1.setBounds(24, 377, 85, 21);
			
		}
		return btnNewButton_1;
	}
}
