package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import business.race.RaceDto;

import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;

public class TransactionFrame extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnTransaction;
	private JButton btnNewButton;
	private JButton btnOk;
	private JPanel panel;
	private JPanel paneCreditCard;
	private JRadioButton rdbtnCreditCard;
	private JLabel lblCreditCard;
	private int opcion = 0;
	private JLabel lblTransaction;
	private final static int TRANSFERENCIA = 1;
	private final static int TARJETADECREDITO = 2;
	private RaceDto carrera;
	private String email;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) {
		new TransactionFrame(new RaceDto(), "").setVisible(true);
	}
	
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
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnOk());
		contentPane.add(getPanel());
		
		contentPane.add(getPaneCreditCard());
		
		paneCreditCard.add(getRdbtnCreditCard(), BorderLayout.WEST);
		
		paneCreditCard.add(getLblCreditCard(), BorderLayout.CENTER);
	}
	
	private JPanel getPaneCreditCard() {
		if (paneCreditCard == null) {
			paneCreditCard =  new JPanel();
			paneCreditCard.setBounds(10, 140, 531, 128);
			paneCreditCard.setLayout(new BorderLayout(0, 0));
		}
		return paneCreditCard;
	}
	
	private JRadioButton getRdbtnCreditCard() {
		if (rdbtnCreditCard == null) {
			rdbtnCreditCard = new JRadioButton("Tarjeta de Credito");
			buttonGroup.add(rdbtnCreditCard);
			rdbtnCreditCard.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(rdbtnCreditCard.isSelected()) {
						opcion = 2;
					}
				}
			});
			rdbtnCreditCard.setBounds(30, 51, 113, 25);
			rdbtnCreditCard.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return rdbtnCreditCard;
	}
	
	private JLabel getLblCreditCard() {
		if(lblCreditCard == null) {
			lblCreditCard = new JLabel("");
			lblCreditCard.setBounds(149, 11, 372, 97);
			setImagenAdaptada(lblCreditCard,"/img/tarjeta-mastercard.png");
			
		}
		return lblCreditCard;
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
			rdbtnTransaction.setSelected(true);
			buttonGroup.add(rdbtnTransaction);
			rdbtnTransaction.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(rdbtnTransaction.isSelected()) {
						opcion = 1;
					}
				}
			});
			rdbtnTransaction.setBounds(30, 51, 113, 25);
			rdbtnTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return rdbtnTransaction;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Cancelar\r\n");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNewButton.setBounds(456, 321, 85, 21);
		}
		return btnNewButton;
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
		if(opcion == TARJETADECREDITO) {
			CreditcardFrame baf= new CreditcardFrame(this,carrera,email);
			baf.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			baf.setVisible(true);
		}
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 10, 531, 119);
			panel.setLayout(null);
			panel.add(getRdbtnTransaction());
			panel.add(getLblTransaction());
		}
		return panel;
	}
	
	private JLabel getLblTransaction() {
		if (lblTransaction == null) {
			lblTransaction = new JLabel("");
			lblTransaction.setBounds(180, 11, 341, 97);
			setImagenAdaptada(lblTransaction,"/img/transferencia-logo.png");
			lblTransaction.setBounds(149, 11, 372, 97);
		}
		return lblTransaction;
	}
}
