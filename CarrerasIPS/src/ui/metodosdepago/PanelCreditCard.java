package ui.metodosdepago;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.sqlite.util.StringUtils;

import business.race.RaceDto;
import model.inscription.InscriptionModel;
import ui.ReciboTarjeta;
import util.DbUtil;
import util.UnexpectedException;

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
	public PanelCreditCard(JFrame frame, JPanel cardPanel, RaceDto carrera, String email) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		this.padre = frame;
		this.cardPanel = cardPanel;
		this.carrera = carrera;
		this.email = email;
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
					((CardLayout) cardPanel.getLayout()).show(cardPanel, "principal");
					;
				}
			});
		}
		return btnCancelar;
	}

	private JTextField getTxtNtarjeta() {
		if (txtNtarjeta == null) {
			txtNtarjeta = new JTextField();
			txtNtarjeta.setBounds(32, 66, 242, 20);
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
		String numero = getTxtNtarjeta().getText();
		String cvv = getTxtCvv().getText();
		String fecha = getTxtFechacaducidad().getText();
		if (getTxtNtarjeta().getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo Nº de Tarjeta esta vacio", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (getTxtFechacaducidad().getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo Fecha de Caducidad esta vacio", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (getTxtCvv().getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo CVV esta vacio", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(!isNumeric(getTxtNtarjeta().getText())){
			JOptionPane.showMessageDialog(this, "El campo Nº de Tarjeta no es un numero", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(fecha.split("-").length != 3 || !isNumeric(fecha.split("-")[0]) || !isNumeric(fecha.split("-")[1]) || !isNumeric(fecha.split("-")[2])){
			JOptionPane.showMessageDialog(this, "El campo Fecha Caducidad no es valido", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(!isNumeric(getTxtCvv().getText())){
			JOptionPane.showMessageDialog(this, "El campo CVV no es un numero", "Error", JOptionPane.ERROR_MESSAGE);
		}else {
			String dni = InscriptionModel.getDni(email);
			if (dni != null) {
				InscriptionModel.updateEstado("INSCRITO", dni, carrera.id);
				new ReciboTarjeta(carrera,email,getCantidad(carrera.id)).setVisible(true);
				padre.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "El usuario con ese email no esta registrado en la carrera",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
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
}
