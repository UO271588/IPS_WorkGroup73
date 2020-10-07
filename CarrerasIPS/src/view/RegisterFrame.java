package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.client.Participant;
import business.ParticipantModel;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblDni;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblSexo;
	private JLabel lblCorreoelectronico;
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textMail;
	private JRadioButton rdbtnH;
	private JRadioButton rdbtnM;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblFechaNacimiento;
	private JFormattedTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		setTitle("Registro Corredor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnAceptar());
		contentPane.add(getBtnCancelar());
		contentPane.add(getLblDni());
		contentPane.add(getLblNombre());
		contentPane.add(getLblApellidos());
		contentPane.add(getLblSexo());
		contentPane.add(getLblCorreoelectronico());
		contentPane.add(getTextDNI());
		contentPane.add(getTextNombre());
		contentPane.add(getTextApellidos());
		contentPane.add(getTextMail());
		contentPane.add(getRdbtnH());
		contentPane.add(getRdbtnM());
		contentPane.add(getLblFechaNacimiento());
		contentPane.add(getTextField());
	}
	

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(265, 328, 114, 36);
		}
		return btnCancelar;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setBounds(67, 89, 127, 21);
		}
		return lblDni;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(67, 121, 127, 21);
		}
		return lblNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos");
			lblApellidos.setBounds(67, 153, 127, 21);
		}
		return lblApellidos;
	}
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo");
			lblSexo.setBounds(67, 185, 127, 21);
		}
		return lblSexo;
	}
	private JLabel getLblCorreoelectronico() {
		if (lblCorreoelectronico == null) {
			lblCorreoelectronico = new JLabel("Correo Electronico");
			lblCorreoelectronico.setBounds(67, 217, 127, 21);
		}
		return lblCorreoelectronico;
	}
	private JTextField getTextDNI() {
		if (textDNI == null) {
			textDNI = new JTextField();
			textDNI.setBounds(204, 89, 198, 20);
			textDNI.setColumns(10);
		}
		return textDNI;
	}
	private JTextField getTextNombre() {
		if (textNombre == null) {
			textNombre = new JTextField();
			textNombre.setColumns(10);
			textNombre.setBounds(204, 121, 198, 20);
		}
		return textNombre;
	}
	private JTextField getTextApellidos() {
		if (textApellidos == null) {
			textApellidos = new JTextField();
			textApellidos.setColumns(10);
			textApellidos.setBounds(204, 153, 198, 20);
		}
		return textApellidos;
	}
	private JTextField getTextMail() {
		if (textMail == null) {
			textMail = new JTextField();
			textMail.setColumns(10);
			textMail.setBounds(204, 217, 198, 20);
		}
		return textMail;
	}
	private JRadioButton getRdbtnH() {
		if (rdbtnH == null) {
			rdbtnH = new JRadioButton("H");
			buttonGroup.add(rdbtnH);
			rdbtnH.setBounds(214, 184, 40, 23);
		}
		return rdbtnH;
	}
	private JRadioButton getRdbtnM() {
		if (rdbtnM == null) {
			rdbtnM = new JRadioButton("M");
			buttonGroup.add(rdbtnM);
			rdbtnM.setBounds(265, 184, 46, 23);
		}
		return rdbtnM;
	}
	
	
	
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					boolean correct = true;
					boolean sexMale = false;
					Participant part = null;
					
					//comprobar que los campos de texto esta completos como deben
					String aviso = "";	//se almacenara la informacion incorrecta
					if(getTextNombre().getText().trim().length() == 0) {
						aviso += "El nombre es un campo obligatorio\n";
						correct = false;
					}
					if(getTextApellidos().getText().trim().length() == 0) {
						aviso += "El apellido es un campo obligatorio\n";
						correct = false;
					}
					if(getTextMail().getText().trim().length() == 0) {
						aviso += "El Mail es un campo obligatorio\n";
						correct = false;
					}
					else if(checkExistMailDB( getTextMail().getText())) {
						aviso += "Un usuario con el DNI introducido ya esta registrado como usuario\n";
						correct = false;
					}
					if(getTextDNI().getText().trim().length() == 0) {
						aviso += "El DNI es un campo obligatorio\n";
						correct = false;
					}
					else if(checkExistDniDB( getTextMail().getText())) {
						aviso += "Un usuario con el DNI introducido ya esta registrado como usuario\n";
						correct = false;
					}
					if(rdbtnH.isSelected()) {
						sexMale = true;
					}
					else if(rdbtnM.isSelected()) {
						sexMale = false;
					}
					else {
						aviso += "Debe de seleccionar el sexo\n";
						correct = false;
					}
					
					if(correct) {	//si esta correcto tenemos que añadir la entrada a la base de datos
						part = new Participant(getTextNombre().getText(), getTextApellidos().getText(),
								getTextDNI().getText(), getTextMail().getText(), new Date(), sexMale);
					}
					else {
						JOptionPane.showMessageDialog(null, "Hay campos incorrectos, reviselos y vuelva a pulsar el boton aceptar\n" + aviso);
					}
					
					if(correct = true) {
						ParticipantModel.addParticipant(part);
					}
					
				}
			});
			btnAceptar.setBounds(67, 328, 114, 36);
		}
		return btnAceptar;
	}
	protected boolean checkExistDniDB(String text) {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean checkExistMailDB(String text) {
		// TODO Auto-generated method stub
		return false;
	}
	private JLabel getLblFechaNacimiento() {
		if (lblFechaNacimiento == null) {
			lblFechaNacimiento = new JLabel("Fecha Nacimiento");
			lblFechaNacimiento.setBounds(67, 249, 127, 21);
		}
		return lblFechaNacimiento;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JFormattedTextField(new SimpleDateFormat("dd/MM/YYYY"));
			textField.setColumns(10);
			textField.setBounds(204, 248, 198, 20);
			textField.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if (!((c >= '0') && (c <= '9') ||
			         (c == KeyEvent.VK_BACK_SPACE) ||
			         (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
			      {
			        JOptionPane.showMessageDialog(null, "Please Enter Valid");
			        e.consume();
			      }
			    }
			  });
		}
		return textField;
	}
}
