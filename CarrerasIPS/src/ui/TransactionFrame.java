package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
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
import ui.metodosdepago.PanelCreditCard;
import ui.metodosdepago.TransferenceCard;
import java.awt.Toolkit;

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
	private JPanel panelPestañas;
	private JPanel panelPrincipal;
	private JPanel panelCreditCard;
	private JPanel transferenceCard;

	public static void main(String[] args) {
		new TransactionFrame(new RaceDto(), "").setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	public TransactionFrame(RaceDto carrera, String email) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TransactionFrame.class.getResource("/img/WhatsApp Image 2020-12-02 at 20.35.14.jpeg")));
		this.email = email;
		setTitle("Metodos de Pago");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 389);
		this.carrera = carrera;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		getPaneCreditCard().add(getRdbtnCreditCard(), BorderLayout.WEST);
		getPaneCreditCard().add(getLblCreditCard(), BorderLayout.CENTER);
		contentPane.add(getPanelPestañas());
	}
	
	private JPanel getPaneCreditCard() {
		if (paneCreditCard == null) {
			paneCreditCard =  new JPanel();
			paneCreditCard.setBounds(10, 163, 515, 97);
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
			rdbtnTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return rdbtnTransaction;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Cancelar\r\n");
			btnNewButton.setBounds(439, 307, 86, 23);
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnNewButton;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("Aceptar\r\n");
			btnOk.setBounds(348, 307, 81, 23);
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lanzarVentanas();
				}
			});
			btnOk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnOk;
	}
	
	public void lanzarVentanas() {
		if(opcion == TRANSFERENCIA) {
			((CardLayout) getPanelPestañas().getLayout()).show(getPanelPestañas(), "transference");
		}
		if(opcion == TARJETADECREDITO) {
			((CardLayout) getPanelPestañas().getLayout()).show(getPanelPestañas(), "creditcard");
		}
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 11, 515, 97);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getRdbtnTransaction(), BorderLayout.WEST);
			panel.add(getLblTransaction());
		}
		return panel;
	}
	
	private JLabel getLblTransaction() {
		if (lblTransaction == null) {
			lblTransaction = new JLabel("");
			lblTransaction.setBounds(149, 11, 372, 97);
			setImagenAdaptada(lblTransaction,"/img/transferencia-logo.png");
		}
		return lblTransaction;
	}
	private JPanel getPanelPestañas() {
		if (panelPestañas == null) {
			panelPestañas = new JPanel();
			panelPestañas.setLayout(new CardLayout(0, 0));
			panelPestañas.add(getPanelPrincipal(), "principal");
			panelPestañas.add(getPanelCreditCard(), "creditcard");
			panelPestañas.add(getTransferenceCard(), "transference");
		}
		return panelPestañas;
	}
	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(null);
			panelPrincipal.add(getPanel());
			panelPrincipal.add(getPaneCreditCard());
			panelPrincipal.add(getBtnOk());
			panelPrincipal.add(getBtnNewButton());
		}
		return panelPrincipal;
	}
	
	private JPanel getPanelCreditCard() {
		if (panelCreditCard == null) {
			panelCreditCard = new PanelCreditCard(this,panelPestañas,carrera,email);
		}
		return panelCreditCard;
	}

	private JPanel getTransferenceCard() {
		if (transferenceCard == null) {
			transferenceCard = new TransferenceCard(this,carrera,email);
		}
		return transferenceCard;
	}
	
}
