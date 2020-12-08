package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.RaceDto;
import model.inscription.InscriptionModel;
import java.awt.Toolkit;

public class CreditcardFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JTextField txtNtarjeta;
	private JLabel lblNtarjeta;
	private JLabel lblFechacaducidad;
	private JTextField txtFechacaducidad;
	private JLabel lblCvv;
	private JTextField txtCvv;
	private JFrame padre;
	private RaceDto carrera;
	private String email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditcardFrame frame = new CreditcardFrame();
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
	public CreditcardFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreditcardFrame.class.getResource("/img/WhatsApp Image 2020-12-02 at 20.35.14.jpeg")));
		setTitle("Pago con tarjeta de credito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnAceptar());
		contentPane.add(getBtnCancelar());
		contentPane.add(getTxtNtarjeta());
		contentPane.add(getLblNtarjeta());
		contentPane.add(getLblFechacaducidad());
		contentPane.add(getTxtFechacaducidad());
		contentPane.add(getLblCvv());
		contentPane.add(getTxtCvv());
	}

	public CreditcardFrame(TransactionFrame transactionFrame, RaceDto carrera, String email) {
		this.padre = transactionFrame;
		this.carrera = carrera;
		this.email = email;
		setTitle("Pago con tarjeta de credito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnAceptar());
		contentPane.add(getBtnCancelar());
		contentPane.add(getTxtNtarjeta());
		contentPane.add(getLblNtarjeta());
		contentPane.add(getLblFechacaducidad());
		contentPane.add(getTxtFechacaducidad());
		contentPane.add(getLblCvv());
		contentPane.add(getTxtCvv());
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					accionBotonOk();
				}
			});
			btnAceptar.setBounds(236, 227, 89, 23);
		}
		return btnAceptar;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar\r\n");
			btnCancelar.setBounds(335, 227, 89, 23);
		}
		return btnCancelar;
	}

	private JTextField getTxtNtarjeta() {
		if (txtNtarjeta == null) {
			txtNtarjeta = new JTextField();
			txtNtarjeta.setText("\r\n");
			txtNtarjeta.setBounds(37, 51, 258, 25);
			txtNtarjeta.setColumns(10);
		}
		return txtNtarjeta;
	}

	private JLabel getLblNtarjeta() {
		if (lblNtarjeta == null) {
			lblNtarjeta = new JLabel("N\u00BA Tarjeta");
			lblNtarjeta.setBounds(37, 26, 152, 14);
		}
		return lblNtarjeta;
	}

	private JLabel getLblFechacaducidad() {
		if (lblFechacaducidad == null) {
			lblFechacaducidad = new JLabel("FechaCaducidad");
			lblFechacaducidad.setBounds(37, 87, 137, 14);
		}
		return lblFechacaducidad;
	}

	private JTextField getTxtFechacaducidad() {
		if (txtFechacaducidad == null) {
			txtFechacaducidad = new JTextField();
			txtFechacaducidad.setBounds(37, 112, 86, 27);
			txtFechacaducidad.setColumns(10);
		}
		return txtFechacaducidad;
	}

	private JLabel getLblCvv() {
		if (lblCvv == null) {
			lblCvv = new JLabel("CVV");
			lblCvv.setBounds(37, 150, 107, 14);
		}
		return lblCvv;
	}

	private JTextField getTxtCvv() {
		if (txtCvv == null) {
			txtCvv = new JTextField();
			txtCvv.setBounds(37, 175, 86, 31);
			txtCvv.setColumns(10);
		}
		return txtCvv;
	}

	public void accionBotonOk() {
		if (getTxtNtarjeta().getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo Nº de Tarjeta no es valido o esta vacio", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (getTxtFechacaducidad().getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo Fecha de Caducidad no es valido o esta vacio", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (getTxtCvv().getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo CVV no es valido o esta vacio", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			String dni = InscriptionModel.getDni(email);
			if (dni != null) {
				if (InscriptionModel.existeYaInscripcion(dni, carrera.id)) {
					InscriptionModel.updateEstado("PAGADO", dni, carrera.id);
					padre.dispose();
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "El usuario con ese email no esta registrado en la carrera",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
