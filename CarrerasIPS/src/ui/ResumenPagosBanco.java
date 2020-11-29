package ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.payments.PaymentDto;

public class ResumenPagosBanco extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	private JPanel panel;
	private JLabel lblDni;
	private JLabel lblDineroSuministrado;
	private JLabel lblFechaSuministro;
	private JLabel lblCarrera;
	private List<PaymentDto> lista;

	/**
	 * Create the panel.
	 */
	public ResumenPagosBanco(List<PaymentDto> lista) {
		this.lista = lista;
		setLayout(new BorderLayout(0, 0));
		add(getPanel(), BorderLayout.NORTH);
		add(getPanel_1(), BorderLayout.CENTER);
		crearPanelesPagos();
		

	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
		}
		return panel_1;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(1, 4, 0, 0));
			panel.add(getLblDni());
			panel.add(getLblCarrera());
			panel.add(getLblDineroSuministrado());
			panel.add(getLblFechaSuministro());
		}
		return panel;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI");
			lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDni;
	}

	private JLabel getLblDineroSuministrado() {
		if (lblDineroSuministrado == null) {
			lblDineroSuministrado = new JLabel("PAGO\r\n");
			lblDineroSuministrado.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDineroSuministrado;
	}

	private JLabel getLblFechaSuministro() {
		if (lblFechaSuministro == null) {
			lblFechaSuministro = new JLabel("FECHA PAGO");
			lblFechaSuministro.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblFechaSuministro;
	}

	private JLabel getLblCarrera() {
		if (lblCarrera == null) {
			lblCarrera = new JLabel("CARRERA");
			lblCarrera.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCarrera;
	}

	public void crearPanelesPagos() {
		getPanel_1().setLayout(new GridLayout(Math.max(10, lista.size()), 1, 0, 0));
		getPanel_1().removeAll();

		for (PaymentDto pago : lista) {
			JPanel panelPago = new JPanel();
			panelPago.setLayout(new GridLayout(1, 4, 0, 0));
			JTextField txtDni = new JTextField();
			JTextField txtCarrera = new JTextField();
			JTextField txtDinero = new JTextField();
			JTextField txtFecha = new JTextField();

			// Creacion textField Dni
			txtDni.setText(pago.dni);
			panelPago.add(txtDni);
			txtDni.setColumns(10);
			txtDni.setHorizontalAlignment(JTextField.CENTER);
			txtDni.setEditable(false);

			// Creacion textField Carrera
			txtCarrera.setText(pago.carrera);
			panelPago.add(txtCarrera);
			txtCarrera.setColumns(10);
			txtCarrera.setHorizontalAlignment(JTextField.CENTER);
			txtCarrera.setEditable(false);

			// Creacion textField Dinero
			txtDinero.setText(pago.dinero + "");
			panelPago.add(txtDinero);
			txtDinero.setColumns(10);
			txtDinero.setHorizontalAlignment(JTextField.CENTER);
			txtDinero.setEditable(false);

			// Creacion textField Dinero
			txtFecha.setText(pago.fecha);
			panelPago.add(txtFecha);
			txtFecha.setColumns(10);
			txtFecha.setHorizontalAlignment(JTextField.CENTER);
			txtFecha.setEditable(false);

			getPanel_1().add(panelPago);
			getPanel_1().repaint();
			this.setSize(this.getWidth() + 1, this.getHeight() + 1);
			this.setSize(this.getWidth() - 1, this.getHeight() - 1);

		}
	}
}
