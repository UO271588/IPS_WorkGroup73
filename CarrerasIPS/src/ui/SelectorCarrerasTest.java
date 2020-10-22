package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.race.RaceDto;
import util.database.Database;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SelectorCarrerasTest extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectorCarrerasTest frame = new SelectorCarrerasTest();
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
	public SelectorCarrerasTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getTextField());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("CarreraID:");
			lblNewLabel.setBounds(10, 104, 100, 21);
		}
		return lblNewLabel;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(94, 104, 133, 21);
			textField.setColumns(10);
		}
		return textField;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("ver participantes");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RaceDto carrera = null;
					try {
						carrera = getDto(getTextField().getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(carrera.id + " " +  carrera.nombre);
					new InsciptionsListFrame(carrera).setVisible(true);;
				}
			});
			btnNewButton.setBounds(68, 157, 170, 23);
		}
		return btnNewButton;
	}

	protected RaceDto getDto(String text) throws SQLException {
		String sql = "SELECT name FROM competition where IDcompetition = ?";
		Database db = new Database();
		Connection c = db.getConnection();
		PreparedStatement pst =  c.prepareStatement(sql);
		pst.setString(1, text);
		ResultSet rs = pst.executeQuery();
		rs.next();
		RaceDto dto = new RaceDto();
		dto.id = text;
		dto.nombre = rs.getString(1);
		System.out.println(dto.id + " " +  dto.nombre);
		return dto;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Registrarse");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new RegisterFrame().setVisible(true);
				}
			});
			btnNewButton_1.setBounds(266, 11, 124, 30);
		}
		return btnNewButton_1;
	}
}
