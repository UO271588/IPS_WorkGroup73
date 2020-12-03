package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ReceiptFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldName;
	private JTextField textFieldCompetition;
	private JTextField textFieldCategory;
	private JTextField textFieldDate;
	private JTextField textFieldAmount;
	private JTextField textFieldState;
	private JLabel lblDorsal;
	private JTextField textField;
	private Integer dorsal;






	public ReceiptFrame(String nombre, String nombreCompeticion, String categoria, String dateToIsoString,
			double cantidad, String estado,Integer dorsal) {
		setTitle("Justificante");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReceiptFrame.class.getResource("/img/WhatsApp Image 2020-12-02 at 20.35.14.jpeg")));
		this.dorsal = dorsal;
		setBounds(100, 100, 493, 478);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblReceipt = new JLabel("Receipt");
		lblReceipt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReceipt.setBounds(183, 10, 83, 34);
		contentPanel.add(lblReceipt);
		
		JLabel lblName = new JLabel("Athlete name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(65, 81, 90, 19);
		contentPanel.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setEditable(false);
		textFieldName.setBounds(183, 79, 214, 24);
		contentPanel.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblCompetition = new JLabel("Competition: ");
		lblCompetition.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCompetition.setBounds(65, 128, 90, 21);
		contentPanel.add(lblCompetition);
		
		textFieldCompetition = new JTextField();
		textFieldCompetition.setEditable(false);
		textFieldCompetition.setBounds(183, 125, 214, 24);
		contentPanel.add(textFieldCompetition);
		textFieldCompetition.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category: ");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategory.setBounds(65, 222, 90, 23);
		contentPanel.add(lblCategory);
		
		textFieldCategory = new JTextField();
		textFieldCategory.setEditable(false);
		textFieldCategory.setBounds(183, 219, 214, 24);
		contentPanel.add(textFieldCategory);
		textFieldCategory.setColumns(10);
		
		JLabel lblInscriptionDate = new JLabel("Inscription date:");
		lblInscriptionDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInscriptionDate.setBounds(65, 264, 108, 19);
		contentPanel.add(lblInscriptionDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setEditable(false);
		textFieldDate.setBounds(183, 260, 214, 24);
		contentPanel.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmount.setBounds(66, 307, 58, 19);
		contentPanel.add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setEditable(false);
		textFieldAmount.setBounds(183, 305, 214, 24);
		contentPanel.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		JLabel lblState = new JLabel("Inscription state:");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblState.setBounds(65, 350, 108, 23);
		contentPanel.add(lblState);
		
		textFieldState = new JTextField();
		textFieldState.setEditable(false);
		textFieldState.setBounds(183, 347, 214, 26);
		contentPanel.add(textFieldState);
		textFieldState.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		this.textFieldCategory.setText(categoria);
		this.textFieldCompetition.setText(nombreCompeticion);
		this.textFieldName.setText(nombre);
		this.textFieldDate.setText(dateToIsoString);
		this.textFieldState.setText(estado);
		this.textFieldAmount.setText(cantidad+"");
		contentPanel.add(getLblDorsal());
		contentPanel.add(getTextField());
	}
	private JLabel getLblDorsal() {
		if (lblDorsal == null) {
			lblDorsal = new JLabel("Dorsal:");
			lblDorsal.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDorsal.setBounds(65, 179, 46, 19);
		}
		return lblDorsal;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setEditable(false);
			textField.setText(dorsal +"");
			textField.setBounds(183, 175, 214, 24);
			textField.setColumns(10);
		}
		return textField;
	}
}
