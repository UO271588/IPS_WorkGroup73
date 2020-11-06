package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.IdentificationFrameController;
import model.participant.ParticipantDtoPojo;
import util.interfaces.Indentificable;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IdentificationFrame extends JFrame {

	private JPanel contentPane;
	private IdentificationFrameController controller;
	private JButton btnAceptar;
	private JButton btnVolver;
	private JTextField textField;
	private JLabel lblMail;
	private JLabel lblTitle;
	private Indentificable nextFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IdentificationFrame frame = new IdentificationFrame(new InscribedRacesFrame());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**Esta es una ventana pensada para usarse cuando se necesita identificacion
	 * Se le tiene que pasar un JFrame que implemente la interfaz identificable
	 * una vez hecho el registro con exito esta ventana genera una ventana del tipo introducido
	 * por el en el constructor, ademas le introduce a traves del metodo setUser (de la interfaz identificable)
	 * el dto del participante que se ha identificado
	 * 
	 * 
	 */
	public IdentificationFrame(Indentificable nextFrame) {
		this.controller = new IdentificationFrameController(this);
		this.nextFrame = nextFrame;
		setTitle("Identificacion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnAceptar());
		contentPane.add(getBtnVolver());
		contentPane.add(getTextField());
		contentPane.add(getLblMail());
		contentPane.add(getLblTitle());
	}
	public Indentificable getNextFrame() {
		return nextFrame;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionAceptar();
				}
			});
			btnAceptar.setBounds(322, 229, 116, 36);
		}
		return btnAceptar;
	}
	protected void actionAceptar() {
		controller.identificate(this.getTextField().getText());
	
		
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnVolver.setBounds(68, 229, 116, 36);
		}
		return btnVolver;
	}
	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(140, 141, 180, 20);
		}
		return textField;
	}
	private JLabel getLblMail() {
		if (lblMail == null) {
			lblMail = new JLabel("Email: ");
			lblMail.setHorizontalAlignment(SwingConstants.TRAILING);
			lblMail.setBounds(30, 141, 100, 20);
		}
		return lblMail;
	}
	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Identificacion: por favor introduzca el Email");
			lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
			lblTitle.setBounds(68, 27, 383, 59);
		}
		return lblTitle;
	}
}
