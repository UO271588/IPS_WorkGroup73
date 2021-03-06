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
import java.awt.Toolkit;
import java.awt.Font;

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
	private JButton btnNewButton;
	private JFrame parent;

	/**
	 * Create the frame.
	 * 
	 * @param nombreCarrera
	 */
	public InscripcionFrame(RaceDto carrera, JFrame parent) {
		setTitle("Inscripci\u00F3n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InscripcionFrame.class.getResource("/img/WhatsApp Image 2020-12-02 at 20.02.37.jpeg")));
		this.parent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 303);
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
		contentPane.add(getBtnNewButton());
		//boton taso no esta en lazy
		JButton btnNewButton_1 = new JButton("Inscripcion como Club");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ClubInscriptionFrame(carrera).setVisible(true);
			}
		});
		btnNewButton_1.setBounds(281, 49, 178, 28);
		contentPane.add(btnNewButton_1);
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
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblEmail.setBounds(33, 95, 46, 14);
		}
		return lblEmail;
	}

	public JButton getBtnPagar() {
		if (btnPagar == null) {
			btnPagar = new JButton("PAGAR");
			btnPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(InscriptionModel.yaPagoOEstaPendiente(InscriptionModel.getDni(textFieldEmail.getText()),carrera.nombre)){
						JOptionPane.showMessageDialog(null, "Ya se ha pagado");
					}else if (InscriptionModel.existeYaInscripcion(InscriptionModel.getDni(textFieldEmail.getText()),
							carrera.nombre)) {
						comprobarCampos();
						TransactionFrame tf = new TransactionFrame(carrera, textFieldEmail.getText());
						tf.setVisible(true);
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
			lblNombreCompeticion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNombreCompeticion.setBounds(33, 134, 121, 14);
		}
		return lblNombreCompeticion;
	}

	public JTextField getTextFieldEmail() {
		if (textFieldEmail == null) {
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(85, 87, 241, 32);
			textFieldEmail.setColumns(10);
		}
		return textFieldEmail;
	}

	public JTextField getTextFieldNombreCompeticion() {
		if (textFieldNombreCompeticion == null) {
			textFieldNombreCompeticion = new JTextField();
			textFieldNombreCompeticion.setBounds(164, 126, 162, 32);
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
			fechaHoy.setBounds(232, 8, 150, 31);
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
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("ATR\u00C1S");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame jf = ((RacesFrame) parent).getAnterior();
					parent.dispose();
					jf.setVisible(true);
					dispose();
					
				}
			});
			btnNewButton.setBounds(20, 235, 85, 21);
		}
		return btnNewButton;
	}
}
