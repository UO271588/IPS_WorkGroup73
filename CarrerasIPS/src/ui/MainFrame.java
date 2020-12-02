package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.RaceCreationController;
import model.clasification.ClasificationAccess;
import model.clasification.ClasificationDto;
import model.inscription.InscriptionDto;
import model.inscription.InscriptionModel;
import model.payments.PaymentDto;
import util.MyFile;
import util.TimeUtil;
import util.database.Main;

import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnAministradorDeCarreras;
	private JButton btnCrearDB;
	private JButton btnPoblarDB;
	private JButton btnNewButton_2;
	private JButton btnApuntados;
	private JButton btnCrearCarreras;
	private JButton btnCargarArchivo;
	private JFileChooser jfc;
	private DefaultListModel<MyFile> modelolistLibrary;
	private JPanel panelOrganizador;
	private JPanel panelParticipante;
	private JPanel panelBaseDatos;
	private JPanel panelAdministrador;
	private JLabel lblImagen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					        if ("Nimbus".equals(info.getName())) {
					            UIManager.setLookAndFeel(info.getClassName());
					            break;
					        }
					    }
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/img/WhatsApp Image 2020-12-02 at 20.02.37.jpeg")));
		setTitle("Ventana Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 543);
		modelolistLibrary = new DefaultListModel<MyFile>();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelOrganizador());
		contentPane.add(getPanelParticipante());
		contentPane.add(getLblImagen());
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Inscripcion");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					launchParticipant();
				}
			});
		}
		return btnNewButton;
	}
	protected void launchParticipant() {
		new RacesFrame(this).setVisible(true);
		this.setVisible(false);
		
	}

	private JButton getBtnAministradorDeCarreras() {
		if (btnAministradorDeCarreras == null) {
			btnAministradorDeCarreras = new JButton("Aministrador de Carreras");
			btnAministradorDeCarreras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					launchAdministrator();
				}
			});
		}
		return btnAministradorDeCarreras;
	}

	protected void launchAdministrator() {
		new InsciptionsListFrame(this).setVisible(true);
		this.setVisible(false);
		
	}
	private JButton getBtnCrearDB() {
		if (btnCrearDB == null) {
			btnCrearDB = new JButton("Crear DB");
			btnCrearDB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Main.createTables();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btnCrearDB;
	}
	private JButton getBtnPoblarDB() {
		if (btnPoblarDB == null) {
			btnPoblarDB = new JButton("Poblar DB");
			btnPoblarDB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Main.addData();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btnPoblarDB;
	}
	
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("Registrarse");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new RegisterFrame().setVisible(true);
				}
			});
		}
		return btnNewButton_2;
	}
	private JButton getBtnApuntados() {
		if (btnApuntados == null) {
			btnApuntados = new JButton("Car. Apuntadas");
			btnApuntados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					launchCarrerasApuntadas();
				}
			});
		}
		return btnApuntados;
	}
	protected void launchCarrerasApuntadas() {
		new InscribedRacesFrame(this);
		
	}

	private JButton getBtnCrearCarreras() {
		if (btnCrearCarreras == null) {
			btnCrearCarreras = new JButton("Crear Carreras");
			btnCrearCarreras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					launchCrearCarreras();
				}
			});
		}
		return btnCrearCarreras;
	}

	protected void launchCrearCarreras() {
		this.setVisible(false);
		new RaceCreationFrame(this).setVisible(true);
		
	}
	
	private JButton getBtnCargarArchivo() {
		if (btnCargarArchivo == null) {
			btnCargarArchivo = new JButton("Cargar Archivo Banco");
			btnCargarArchivo.setBounds(128, 83, 162, 23);
			btnCargarArchivo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarArchivoFileChooser();
					
				}
			});
		}
		return btnCargarArchivo;
	}

	public List<PaymentDto> leerFichero(File file) {
		FileReader lector = null;
		BufferedReader br = null;
		List<PaymentDto> payments = new ArrayList<PaymentDto>();
		try {
			lector = new FileReader(file);
			br = new BufferedReader(lector);
			String linea;
			while (br.ready()) {
				linea = br.readLine();
				if (!linea.contains("//")) {
					String[] partes = linea.split(",");
					if (partes.length == 4) {
						PaymentDto pago = gestionarPago(partes[0], partes[1], partes[2], partes[3]);
						if(pago.carrera!= null && !pago.carrera.isEmpty()  ) {
							payments.add(pago);
						}
						
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return payments;
	}

	public PaymentDto gestionarPago(String dni, String idcarrera, String cantidad, String fecha) {
		InscriptionModel im = new InscriptionModel();
		List<InscriptionDto> inscripcionescarrera = im.getInscriptionList(idcarrera);
		for (InscriptionDto inscripcion : inscripcionescarrera) {
			if (inscripcion.dni.equals(dni)) {
				Date fechaInscripcion = TimeUtil.isoStringToDate(inscripcion.getInscriptionDate());
				LocalDateTime fechaIns = Instant.ofEpochMilli(fechaInscripcion.getTime()).atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				LocalDateTime fechaLimite = fechaIns.plusDays(2);
				Date fechatrans = TimeUtil.isoStringToDate(fecha);
				LocalDateTime fechaPago = Instant.ofEpochMilli(fechatrans.getTime()).atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				if (fechaLimite.plusDays(1).isAfter(fechaPago) && fechaIns.isBefore(fechaPago)) {
					double dinero = im.getDineroAPagar(idcarrera,fecha);
					if(dinero - Double.parseDouble(cantidad) > 0) {
						im.updateEstado("CANCELADO", dni, idcarrera,fecha,"Dinero insuficiente");
					}else if(dinero - Double.parseDouble(cantidad) == 0) {
						im.updateEstado("PAGADO", dni, idcarrera,fecha,null);
					}else {
						im.updateEstado("PAGADO", dni, idcarrera,fecha,null);
					}
					
					System.out.println("Fecha de pago: " + fechaPago);
					System.out.println("Fecha limite de inscripcion: " + fechaLimite);
					System.out.println("Dinero a pagar: " + dinero);
					System.out.println("Dinero suministrado: " + dinero);
					System.out.println();
				}
				
				PaymentDto pago = new PaymentDto();
				pago.dni = dni;
				pago.carrera = idcarrera;
				pago.dinero = Integer.parseInt(cantidad);
				pago.fecha = fecha;
				return pago;
			}
		}
		
		return new PaymentDto();
	}	
	
	private void cargarArchivoFileChooser() {
		int respuesta = getSelector().showOpenDialog(null);
		if(respuesta == JFileChooser.APPROVE_OPTION) {
			File seleccionado = jfc.getSelectedFile();
			if(!modelolistLibrary.contains(seleccionado)) {
				modelolistLibrary.addElement(new MyFile(jfc.getSelectedFile()));
				List<PaymentDto> pagos = leerFichero(seleccionado);
				new ResumenPagos(pagos).setVisible(true);
			}
		}
	}
	
	private JFileChooser getSelector() {
		if(jfc == null) {
			jfc = new JFileChooser();
			jfc.setMultiSelectionEnabled(false);
			jfc.setFileFilter(new FileNameExtensionFilter(".txt","txt"));
			String desktopPath = System.getProperty("user.home") + "/Desktop";
			jfc.setCurrentDirectory(new File (desktopPath));
		}
		return jfc;
	}
	private JPanel getPanelOrganizador() {
		if (panelOrganizador == null) {
			panelOrganizador = new JPanel();
			panelOrganizador.setBounds(15, 192, 593, 159);
			panelOrganizador.setLayout(new GridLayout(0, 2, 20, 0));
			panelOrganizador.add(getPanelBaseDatos());
			panelOrganizador.add(getPanelAdministrador());
		}
		return panelOrganizador;
	}
	private JPanel getPanelParticipante() {
		if (panelParticipante == null) {
			panelParticipante = new JPanel();
			panelParticipante.setBorder(new TitledBorder(null, "Participante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelParticipante.setBounds(5, 361, 613, 138);
			panelParticipante.setLayout(new GridLayout(0, 3, 20, 0));
			panelParticipante.add(getBtnApuntados());
			panelParticipante.add(getBtnNewButton());
			panelParticipante.add(getBtnNewButton_2());
		}
		return panelParticipante;
	}
	
	private void setImagenAdaptada(JLabel label, String rutaImagen) {
		Image imgOriginal = new ImageIcon(rutaImagen).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(imgEscalada);
		label.setIcon(icon);
	}
	private JPanel getPanelBaseDatos() {
		if (panelBaseDatos == null) {
			panelBaseDatos = new JPanel();
			panelBaseDatos.setBorder(new TitledBorder(null, "Base de datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelBaseDatos.setLayout(new GridLayout(2, 1, 0, 15));
			panelBaseDatos.add(getBtnPoblarDB());
			panelBaseDatos.add(getBtnCrearDB());
		}
		return panelBaseDatos;
	}
	private JPanel getPanelAdministrador() {
		if (panelAdministrador == null) {
			panelAdministrador = new JPanel();
			panelAdministrador.setBorder(new TitledBorder(null, "Organizador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelAdministrador.setLayout(new GridLayout(3, 1, 0, 10));
			panelAdministrador.add(getBtnCrearCarreras());
			panelAdministrador.add(getBtnAministradorDeCarreras());
			panelAdministrador.add(getBtnCargarArchivo());
		}
		return panelAdministrador;
	}
	private JLabel getLblImagen() {
		if (lblImagen == null) {
			lblImagen = new JLabel("");
			lblImagen.setIcon(new ImageIcon(MainFrame.class.getResource("/img/WhatsApp Image 2020-12-02 at 20.35.14.jpeg")));
			lblImagen.setBounds(116, 20, 400, 160);
		}
		return lblImagen;
	}
}
