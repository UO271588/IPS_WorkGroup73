package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.payments.PaymentDto;

public class ResumenPagos extends JFrame {

	private JPanel contentPane;
	private List<PaymentDto> pagos;

	/**
	 * Create the frame.
	 */
	public ResumenPagos(List<PaymentDto> lista) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pagos = lista;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(new ResumenPagosBanco(pagos));
	}

}
