package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.client.Participant;
import util.TimeUtil;
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
import java.awt.Font;
import javax.swing.SwingConstants;

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
	private JLabel lblRegistroParticipante;
	private JTextField textAno;
	private JTextField textMes;
	private JTextField textDia;
	private JLabel lblD;
	private JLabel lblMes;
	private JLabel lblAo;

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
		contentPane.add(getLblRegistroParticipante());
		contentPane.add(getTextAno());
		contentPane.add(getTextMes());
		contentPane.add(getTextDia());
		contentPane.add(getLblD());
		contentPane.add(getLblMes());
		contentPane.add(getLblAo());
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
					else if(!checkMail()) {
						aviso += "Un usuario con el E-Mail introducido ya esta registrado como usuario\n";
						correct = false;
					}
					if(getTextDNI().getText().trim().length() == 0) {
						aviso += "El DNI es un campo obligatorio\n";
						correct = false;
					}
					else if(!checkDni()) {
						aviso += "Un usuario con el DNI introducido ya esta registrado como usuario\n";
						correct = false;
					}
					if(!checkAnoValido()) {
						aviso += "No ha seleccionado una fecha valida\n";
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
					
					//deprecated
					Date date = new Date(Integer.parseInt(getTextAno().getText()),
										Integer.parseInt(getTextMes().getText()),
										Integer.parseInt(getTextDia().getText()));
					if(correct) {	//si esta correcto tenemos que añadir la entrada a la base de datos
						part = new Participant(getTextNombre().getText(), getTextApellidos().getText(),
								getTextDNI().getText(), getTextMail().getText(), date, sexMale);
						ParticipantModel.addParticipant(part);
					}
					else {
						JOptionPane.showMessageDialog(null, "Hay campos incorrectos, reviselos y vuelva a pulsar el boton aceptar\n" + aviso);
					}
				}
			});
			btnAceptar.setBounds(67, 328, 114, 36);
		}
		return btnAceptar;
	}
	protected boolean checkAnoValido() {
		return TimeUtil.chechDate(textAno.getText(), textMes.getText(), textDia.getText());
	}

	protected boolean checkDni() {
		return ParticipantModel.checkDni( getTextDNI().getText());
	}

	protected boolean checkMail() {
		return ParticipantModel.checkMail(getTextMail().getText());
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
			lblDni.setLabelFor(getTextDNI());
			lblDni.setBounds(67, 89, 127, 21);
		}
		return lblDni;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setLabelFor(getTextNombre());
			lblNombre.setBounds(67, 121, 127, 21);
		}
		return lblNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos");
			lblApellidos.setLabelFor(lblApellidos);
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
			lblCorreoelectronico.setLabelFor(getTextMail());
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
	
	
	private JLabel getLblFechaNacimiento() {
		if (lblFechaNacimiento == null) {
			lblFechaNacimiento = new JLabel("Fecha Nacimiento");
			lblFechaNacimiento.setBounds(67, 249, 127, 21);
		}
		return lblFechaNacimiento;
	}
	private JLabel getLblRegistroParticipante() {
		if (lblRegistroParticipante == null) {
			lblRegistroParticipante = new JLabel("Registro Participante");
			lblRegistroParticipante.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroParticipante.setFont(new Font("Times New Roman", Font.BOLD, 27));
			lblRegistroParticipante.setBounds(67, 11, 335, 50);
		}
		return lblRegistroParticipante;
	}
	private JTextField getTextAno() {
		if (textAno == null) {
			textAno = new JTextField();
			textAno.setBounds(364, 249, 38, 20);
			textAno.setColumns(10);
		}
		return textAno;
	}
	private JTextField getTextMes() {
		if (textMes == null) {
			textMes = new JTextField();
			textMes.setColumns(10);
			textMes.setBounds(294, 249, 30, 20);
		}
		return textMes;
	}
	private JTextField getTextDia() {
		if (textDia == null) {
			textDia = new JTextField();
			textDia.setColumns(10);
			textDia.setBounds(224, 249, 30, 20);
		}
		return textDia;
	}
	private JLabel getLblD() {
		if (lblD == null) {
			lblD = new JLabel("dia");
			lblD.setLabelFor(getTextDia());
			lblD.setBounds(204, 249, 20, 20);
		}
		return lblD;
	}
	private JLabel getLblMes() {
		if (lblMes == null) {
			lblMes = new JLabel("mes");
			lblMes.setLabelFor(getTextMes());
			lblMes.setBounds(265, 249, 30, 20);
		}
		return lblMes;
	}
	private JLabel getLblAo() {
		if (lblAo == null) {
			lblAo = new JLabel("a\u00F1o");
			lblAo.setLabelFor(getTextAno());
			lblAo.setBounds(334, 249, 30, 20);
		}
		return lblAo;
	}
}
