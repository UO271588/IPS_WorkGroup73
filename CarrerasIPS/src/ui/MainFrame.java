package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.RaceCreationController;
import model.clasification.ClasificationAccess;
import model.clasification.ClasificationDto;
import util.database.Main;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnAministradorDeCarreras;
	private JButton btnNewButton_1;
	private JButton btnNewButton_1_1;
	private JButton btnNewButton_2;
	private JButton btnApuntados;
	private JButton btnCrearCarreras;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JButton btnClasificaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getBtnNewButton_2());
		contentPane.add(getLabel_1());
		contentPane.add(getBtnCrearCarreras());
		contentPane.add(getLabel_2());
		contentPane.add(getBtnNewButton());
		contentPane.add(getLabel_5());
		contentPane.add(getBtnAministradorDeCarreras());
		contentPane.add(getLabel_3());
		contentPane.add(getBtnApuntados());
		contentPane.add(getLabel());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getBtnNewButton_1_1());
		contentPane.add(getLabel_4());
		contentPane.add(getBtnClasificaciones());
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
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Crear DB");
			btnNewButton_1.addActionListener(new ActionListener() {
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
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_1_1() {
		if (btnNewButton_1_1 == null) {
			btnNewButton_1_1 = new JButton("poblarDB");
			btnNewButton_1_1.addActionListener(new ActionListener() {
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
		return btnNewButton_1_1;
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
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("");
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("");
		}
		return label_2;
	}
	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("");
		}
		return label_3;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("");
		}
		return label_4;
	}
	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("");
		}
		return label_5;
	}
	
	private JButton getBtnClasificaciones() {
		if (btnClasificaciones == null) {
			btnClasificaciones = new JButton("Clasificaciones");
			btnClasificaciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = JOptionPane.showInputDialog("Introduzca el Identificador de la Carrera:");
					ClasificationAccess ca = new ClasificationAccess();
					try {
						List<ClasificationDto> clasificaciones = ca.findAllByRace(id);
						if(clasificaciones.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No se encuentra ninguna clasificacion para la carrera introducida","Clasificaciones no encontradas",JOptionPane.ERROR_MESSAGE);
						}else {
							new ClasificationsFrame(clasificaciones).setVisible(true);
						}
					} catch (SQLException e1) {
						JOptionPane.showConfirmDialog(null, "Se ha produccido un erros con la base de datos","Error de acceso BBDD",JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			});
		}
		return btnClasificaciones;
	}
}
