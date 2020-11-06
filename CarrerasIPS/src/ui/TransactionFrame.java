package ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import business.race.RaceDto;
import java.awt.BorderLayout;

public class TransactionFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JRadioButton rdbtnTransaction;
	private JButton btnCancelar;
	private JButton btnOk;
	private JPanel paneTransaction;
	private int opcion = 0;
	private JLabel lblTransaction;
	private final static int TRANSFERENCIA = 1;
	private RaceDto carrera;
	private String email;

	/**
	 * Create the frame.
	 */
	public TransactionFrame(RaceDto carrera, String email) {
		this.email = email;
		setTitle("Metodos de Pago");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 389);
		this.carrera = carrera;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnOk());
		contentPane.add(getPaneTransaction());
	}
	
	private void setImagenAdaptada(JLabel label, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_SMOOTH);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 label.setIcon(icon);
	}

	private JRadioButton getRdbtnTransaction() {
		if (rdbtnTransaction == null) {
			rdbtnTransaction = new JRadioButton("Transacci\u00F3n");
			rdbtnTransaction.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(rdbtnTransaction.isSelected()) {
						opcion = 1;
					}
				}
			});
			rdbtnTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return rdbtnTransaction;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar\r\n");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancelar.setBounds(456, 321, 85, 21);
		}
		return btnCancelar;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("Aceptar\r\n");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lanzarVentanas();
				}
			});
			btnOk.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnOk.setBounds(361, 321, 85, 21);
		}
		return btnOk;
	}
	
	public void lanzarVentanas() {
		if(opcion == TRANSFERENCIA) {
			BankAccountFrame baf= new BankAccountFrame(this,carrera,email);
			baf.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			baf.setVisible(true);
		}
	}
	
	private JPanel getPaneTransaction() {
		if (paneTransaction == null) {
			paneTransaction = new JPanel();
			paneTransaction.setBounds(10, 10, 531, 119);
			paneTransaction.setLayout(new BorderLayout(0, 0));
			paneTransaction.add(getRdbtnTransaction(), BorderLayout.WEST);
			paneTransaction.add(getLblTransaction());
		}
		return paneTransaction;
	}
	private JLabel getLblTransaction() {
		if (lblTransaction == null) {
			lblTransaction = new JLabel("");
			setImagenAdaptada(lblTransaction,"/img/transferencia-logo.png");
		}
		return lblTransaction;
	}
}
