package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.race.RaceDto;
import model.inscription.InscriptionModel;
import util.TimeUtil;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class BankAccountFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNumeroCuenta;
	private JTextField textField;
	private JTextField textField_1;
	private JFrame padre;
	private RaceDto carrera;
	private String email;

	/**
	 * Create the dialog.
	 */
	public BankAccountFrame(JFrame padre, RaceDto carrera,String email) {
		setTitle("Informacion para el abono");
		this.padre = padre;
		this.carrera = carrera;
		this.email = email;
		setBounds(100, 100, 450, 224);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		tfNumeroCuenta = new JTextField();
		tfNumeroCuenta.setText("0000-0000-0000-0000-0000-0000");
		tfNumeroCuenta.setEditable(false);
		tfNumeroCuenta.setBounds(130, 33, 294, 20);
		contentPanel.add(tfNumeroCuenta);
		tfNumeroCuenta.setColumns(10);

		JLabel lblNumerocuenta = new JLabel("N\u00BA de cuenta:");
		lblNumerocuenta.setBounds(28, 36, 92, 14);
		contentPanel.add(lblNumerocuenta);

		JLabel lblCantidadASuministrar = new JLabel("Precio Inscripcion:");
		lblCantidadASuministrar.setBounds(28, 67, 92, 14);
		contentPanel.add(lblCantidadASuministrar);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(129, 64, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		textField.setText(carrera.precioInscripcion+"€");

		JLabel lblFechalimite = new JLabel("Fecha limite:");
		lblFechalimite.setBounds(28, 98, 92, 14);
		contentPanel.add(lblFechalimite);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(130, 95, 86, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		Date fechaActual = new Date();
		fechaActual.setDate(new Date().getDate()+2);
		textField_1.setText(TimeUtil.dateToIsoString(fechaActual));

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotonOk();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

	}
	
	
	public void accionBotonOk() {
		String dni = InscriptionModel.getDni(email);
		InscriptionModel.updateEstado("PENDIENTE", dni, carrera.id);
		padre.dispose();
		dispose();
	}
}
