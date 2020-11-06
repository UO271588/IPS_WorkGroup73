package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.race.RaceDto;
import model.inscription.InscriptionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class InscripcionFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFichaInscripcionAtleta;
	private JLabel lblEmail;
	private JLabel lblNombreCompeticion;
	private JTextField textFieldEmail;
	private JTextField textFieldNombreCompeticion;
	private JButton btnInscribirse;
	private JTextField fechaHoy;
	private String nombreCarrera;
	private RaceDto carrera;
	private JButton btnPagar;

	/**
	 * Create the frame.
	 * 
	 * @param nombreCarrera
	 */
	public InscripcionFrame(RaceDto carrera) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblFichaInscripcionAtleta());
		contentPane.add(getLblEmail());
		contentPane.add(getLblNombreCompeticion());
		contentPane.add(getTextFieldEmail());
		contentPane.add(getTextFieldNombreCompeticion());
		contentPane.add(getBtnInscribirse());
		contentPane.add(getFechaHoy());
		this.nombreCarrera = carrera.nombre;
		this.carrera = carrera;
		textFieldNombreCompeticion.setText(nombreCarrera);
		contentPane.add(getBtnPagar());
	}

	public JLabel getLblFichaInscripcionAtleta() {
		if (lblFichaInscripcionAtleta == null) {
			lblFichaInscripcionAtleta = new JLabel("FICHA INSCRIPCION ATLETA");
			lblFichaInscripcionAtleta.setBounds(33, 11, 166, 14);
		}
		return lblFichaInscripcionAtleta;
	}

	public JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("email:");
			lblEmail.setBounds(33, 90, 46, 14);
		}
		return lblEmail;
	}

	public JButton getBtnPagar() {
		if (btnPagar == null) {
			btnPagar = new JButton("PAGAR");
			btnPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (InscriptionModel.existeYaInscripcion(InscriptionModel.getDni(textFieldEmail.getText()),
							carrera.nombre)) {
						comprobarCampos();
						TransactionFrame tf = new TransactionFrame(carrera, textFieldEmail.getText());
						tf.setVisible(true);
					}else if(InscriptionModel.yaPagoOEstaPendiente(InscriptionModel.getDni(textFieldEmail.getText()),carrera.nombre)){
						JOptionPane.showMessageDialog(null, "Ya se ha pagado");
					}else {
						JOptionPane.showMessageDialog(null, "No estas inscrito en la competicion");
					}
				}
			});
			btnPagar.setBounds(214, 205, 89, 23);
		}
		return btnPagar;
	}

	public JLabel getLblNombreCompeticion() {
		if (lblNombreCompeticion == null) {
			lblNombreCompeticion = new JLabel("nombre Competicion:");
			lblNombreCompeticion.setBounds(33, 129, 121, 14);
		}
		return lblNombreCompeticion;
	}

	public JTextField getTextFieldEmail() {
		if (textFieldEmail == null) {
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(85, 87, 241, 20);
			textFieldEmail.setColumns(10);
		}
		return textFieldEmail;
	}

	public JTextField getTextFieldNombreCompeticion() {
		if (textFieldNombreCompeticion == null) {
			textFieldNombreCompeticion = new JTextField();
			textFieldNombreCompeticion.setBounds(164, 126, 162, 20);
			textFieldNombreCompeticion.setColumns(10);
			textFieldNombreCompeticion.setEditable(false);

		}
		return textFieldNombreCompeticion;
	}

	public JButton getBtnInscribirse() {
		if (btnInscribirse == null) {
			btnInscribirse = new JButton("INSCRIBIRSE");

			btnInscribirse.setBounds(313, 205, 111, 23);
		}
		return btnInscribirse;
	}

	public void setFechaHoy(String fecha) {
		fechaHoy.setText(fecha.toString());

	}

	public JTextField getFechaHoy() {
		if (fechaHoy == null) {
			fechaHoy = new JTextField();
			fechaHoy.setEditable(false);
			fechaHoy.setBounds(232, 8, 150, 20);
			fechaHoy.setColumns(10);
		}
		return fechaHoy;
	}

	public boolean comprobarCampos() {
		if (textFieldEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Introduzca un email porfavor");
			return false;
		}

		return true;
	}
}
