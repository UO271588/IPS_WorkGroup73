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

public class TransactionFrame extends JFrame {

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
			rdbtnTransaction = new JRadioButton("Transacci\u00F3n");
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
			//Provisional
			RaceDto carrera = new RaceDto();
			BankAccountFrame baf= new BankAccountFrame(this,carrera);
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
