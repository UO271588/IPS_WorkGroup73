package ui.metodosdepago;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.RaceDto;
import model.inscription.InscriptionModel;
import util.DbUtil;
import util.TimeUtil;
import util.UnexpectedException;

public class TransferenceCard extends JPanel {

	private final JPanel panelCentral;
	private JTextField tfNumeroCuenta;
	private JTextField textField;
	private JTextField textField_1;
	private JFrame padre;
	private RaceDto carrera;
	private String email;
	
	/**
	 * Create the panel.
	 */
	public TransferenceCard(JFrame frame, RaceDto carrera, String email) {
		this.padre = frame;
		this.carrera = carrera;
		this.email = email;
		panelCentral = new JPanel();
		setBounds(100, 100, 450, 224);
		setLayout(new BorderLayout());
		panelCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);

		tfNumeroCuenta = new JTextField();
		tfNumeroCuenta.setText("0000-0000-0000-0000-0000-0000");
		tfNumeroCuenta.setEditable(false);
		tfNumeroCuenta.setBounds(130, 33, 294, 20);
		panelCentral.add(tfNumeroCuenta);
		tfNumeroCuenta.setColumns(10);

		JLabel lblNumerocuenta = new JLabel("N\u00BA de cuenta:");
		lblNumerocuenta.setBounds(28, 36, 92, 14);
		panelCentral.add(lblNumerocuenta);

		JLabel lblCantidadASuministrar = new JLabel("Precio Inscripcion:");
		lblCantidadASuministrar.setBounds(28, 67, 92, 14);
		panelCentral.add(lblCantidadASuministrar);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(129, 64, 86, 20);
		panelCentral.add(textField);
		textField.setColumns(10);
		textField.setText(getCantidad(carrera.id)+"€");

		JLabel lblFechalimite = new JLabel("Fecha limite:");
		lblFechalimite.setBounds(28, 98, 92, 14);
		panelCentral.add(lblFechalimite);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(130, 95, 86, 20);
		panelCentral.add(textField_1);
		textField_1.setColumns(10);
		
		Date fechaActual = new Date();
		fechaActual.setDate(new Date().getDate()+2);
		textField_1.setText(TimeUtil.dateToIsoString(fechaActual));

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotonOk();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);

	}
	
	public void accionBotonOk() {
		String dni = InscriptionModel.getDni(email);
		InscriptionModel.updateEstado("PENDIENTE", dni, carrera.id);
		padre.dispose();
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
