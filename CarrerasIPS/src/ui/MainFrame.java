package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.database.Main;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnAministradorDeCarreras;
	private JButton btnNewButton_1;
	private JButton btnNewButton_1_1;
	private JButton btnNewButton_2;

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
		setBounds(100, 100, 639, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnAministradorDeCarreras());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getBtnNewButton_1_1());
		contentPane.add(getBtnNewButton_2());
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Participante");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					launchParticipant();
				}
			});
			btnNewButton.setBounds(358, 150, 175, 53);
		}
		return btnNewButton;
	}
	protected void launchParticipant() {
		new RacesFrame().setVisible(true);
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
			btnAministradorDeCarreras.setBounds(63, 150, 175, 53);
		}
		return btnAministradorDeCarreras;
	}

	protected void launchAdministrator() {
		new SelectorCarrerasTest().setVisible(true);
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
			btnNewButton_1.setBounds(63, 292, 175, 53);
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
			btnNewButton_1_1.setBounds(358, 292, 175, 53);
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
			btnNewButton_2.setBounds(266, 11, 124, 30);
		}
		return btnNewButton_2;
	}
}
