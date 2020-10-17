package ui;

import java.awt.EventQueue;
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

public class TransactionFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton rdbtnTransaction;
	private JButton btnNewButton;
	private JButton btnOk;
	private JPanel panel;
	private int opcion = 0;
	private JLabel lblTransaction;
	private final static int TRANSFERENCIA = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionFrame frame = new TransactionFrame();
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
	public TransactionFrame() {
		setTitle("Metodos de Pago");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnOk());
		contentPane.add(getPanel());
		
	}
	
	private void setImagenAdaptada(JLabel label, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_SMOOTH);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 label.setIcon(icon);
	}

	private JRadioButton getRdbtnTransaction() {
		if (rdbtnTransaction == null) {
			rdbtnTransaction = new JRadioButton("Transferencia");
			rdbtnTransaction.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(rdbtnTransaction.isSelected()) {
						opcion = TRANSFERENCIA;
					}
				}
			});
			rdbtnTransaction.setBounds(30, 51, 142, 25);
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
			BankAccountFrame baf= new BankAccountFrame(this);
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
			lblTransaction.setBounds(178, 11, 324, 97);
			setImagenAdaptada(lblTransaction,"/img/transferencia-logo.png");
		}
		return lblTransaction;
	}
}
