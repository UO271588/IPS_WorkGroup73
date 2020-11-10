package ui.metodosdepago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.race.RaceDto;
import model.inscription.InscriptionModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class PanelCreditCard extends JPanel {
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
	private JPanel panelCentral;
	private JPanel panelBotones;
	private JPanel cardPanel;

	/**
	 * Create the panel.
	 */
	public PanelCreditCard(JFrame frame, JPanel cardPanel) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		this.padre = frame;
		this.cardPanel = cardPanel;
		add(getPanelCentral(), BorderLayout.CENTER);
		add(getPanelBotones(), BorderLayout.SOUTH);
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					accionBotonOk();
				}
			});
		}
		return btnAceptar;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar\r\n");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CardLayout)cardPanel.getLayout()).show(cardPanel,"principal");;
				}
			});
		}
		return btnCancelar;
	}

	private JTextField getTxtNtarjeta() {
		if (txtNtarjeta == null) {
			txtNtarjeta = new JTextField();
			txtNtarjeta.setBounds(32, 66, 242, 20);
			txtNtarjeta.setText("\r\n");
			txtNtarjeta.setColumns(10);
		}
		return txtNtarjeta;
	}

	private JLabel getLblNtarjeta() {
		if (lblNtarjeta == null) {
			lblNtarjeta = new JLabel("N\u00BA Tarjeta");
			lblNtarjeta.setBounds(32, 39, 132, 14);
		}
		return lblNtarjeta;
	}

	private JLabel getLblFechacaducidad() {
		if (lblFechacaducidad == null) {
			lblFechacaducidad = new JLabel("FechaCaducidad");
			lblFechacaducidad.setBounds(32, 97, 132, 14);
		}
		return lblFechacaducidad;
	}

	private JTextField getTxtFechacaducidad() {
		if (txtFechacaducidad == null) {
			txtFechacaducidad = new JTextField();
			txtFechacaducidad.setBounds(32, 122, 86, 20);
			txtFechacaducidad.setColumns(10);
		}
		return txtFechacaducidad;
	}

	private JLabel getLblCvv() {
		if (lblCvv == null) {
			lblCvv = new JLabel("CVV");
			lblCvv.setBounds(32, 153, 132, 14);
		}
		return lblCvv;
	}

	private JTextField getTxtCvv() {
		if (txtCvv == null) {
			txtCvv = new JTextField();
			txtCvv.setBounds(32, 178, 61, 20);
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
				} else {
					JOptionPane.showMessageDialog(this, "El usuario con ese email no esta registrado en la carrera",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.setLayout(null);
			panelCentral.add(getTxtNtarjeta());
			panelCentral.add(getLblNtarjeta());
			panelCentral.add(getLblFechacaducidad());
			panelCentral.add(getTxtFechacaducidad());
			panelCentral.add(getLblCvv());
			panelCentral.add(getTxtCvv());
		}
		return panelCentral;
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelBotones.add(getBtnAceptar());
			panelBotones.add(getBtnCancelar());
		}
		return panelBotones;
	}
}
