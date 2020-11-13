package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.race.RaceDto;
import model.participant.ParticipantModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReciboTarjeta extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNombreatleta;
	private JTextField txtNombreAtleta;
	private JLabel lblNombreCarrera;
	private JTextField txtNombreCarrera;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JButton btnOk;
	private RaceDto carrera;
	private String email;
	private double cantidad;

	/**
	 * Create the frame.
	 * @param d 
	 * @param email 
	 * @param carrera 
	 */
	public ReciboTarjeta(RaceDto carrera, String email, double cantidad) {
		this.carrera = carrera;
		this.email = email;
		this.cantidad = cantidad;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getLblNombreatleta());
			panel.add(getTxtNombreAtleta());
			panel.add(getLblNombreCarrera());
			panel.add(getTxtNombreCarrera());
			panel.add(getLblCantidad());
			panel.add(getTxtCantidad());
			panel.add(getBtnOk());
		}
		return panel;
	}
	private JLabel getLblNombreatleta() {
		if (lblNombreatleta == null) {
			lblNombreatleta = new JLabel("Nombre Atleta:");
			lblNombreatleta.setBounds(10, 11, 122, 14);
		}
		return lblNombreatleta;
	}
	private JTextField getTxtNombreAtleta() {
		if (txtNombreAtleta == null) {
			txtNombreAtleta = new JTextField();
			txtNombreAtleta.setEditable(false);
			txtNombreAtleta.setText(ParticipantModel.getByEmail(email).getName());
			txtNombreAtleta.setBounds(10, 36, 202, 20);
			txtNombreAtleta.setColumns(10);
		}
		return txtNombreAtleta;
	}
	private JLabel getLblNombreCarrera() {
		if (lblNombreCarrera == null) {
			lblNombreCarrera = new JLabel("Nombre Carrera:");
			lblNombreCarrera.setBounds(10, 69, 122, 14);
		}
		return lblNombreCarrera;
	}
	private JTextField getTxtNombreCarrera() {
		if (txtNombreCarrera == null) {
			txtNombreCarrera = new JTextField();
			txtNombreCarrera.setText(carrera.nombre);
			txtNombreCarrera.setEditable(false);
			txtNombreCarrera.setBounds(10, 94, 202, 20);
			txtNombreCarrera.setColumns(10);
		}
		return txtNombreCarrera;
	}
	private JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad:");
			lblCantidad.setBounds(10, 125, 148, 14);
		}
		return lblCantidad;
	}
	private JTextField getTxtCantidad() {
		if (txtCantidad == null) {
			txtCantidad = new JTextField();
			txtCantidad.setText(cantidad+"€");
			txtCantidad.setEditable(false);
			txtCantidad.setBounds(10, 150, 86, 20);
			txtCantidad.setColumns(10);
		}
		return txtCantidad;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnOk.setBounds(325, 217, 89, 23);
		}
		return btnOk;
	}
	
}
