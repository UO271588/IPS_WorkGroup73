package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import business.race.RaceDto;
import controller.ClubInscriptionController;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class ClubInscriptionFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panelSouth;
	private JScrollPane scrollPane;
	private JPanel pnlheader;
	private JPanel pnlViewPort;
	private JPanel pnlh1;
	private JPanel panel_3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JPanel pnlhN;
	private JPanel pnlhE;
	private JLabel lblNewLabel_4;
	private ClubInscriptionController controller;
	private JButton btnCancelar;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_5;
	private JPanel panel_6;
	private JButton btnConfirm;
	private JPanel pnlNorth;
	private JPanel pnlClub;
	private JPanel pnlCard;
	private JPanel pnlNorthCenter;
	private JPanel pnlNorthEast;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_5;
	private JTextField textClub;
	private JPanel pnlForm;
	private JPanel pnlFormCampo;
	private JPanel pnlFormBoton;
	private JLabel lblNewLabel_5_1;
	private JTextField textEmail;
	private JButton btnAdd;
	private JPanel pnlhE_1;
	private JLabel textFecha;
	private JPanel pnlFile;
	private JPanel pnlSelecc;
	private JButton btnNewButton_2;
	private JTextField textNombreFichero;
	private JPanel pnlCargar;
	private JButton btnNewButton_3;
	private JPanel pnlEast;
	private JLabel lblCarrera;



	/**
	 * Create the frame.
	 * @param carrera 
	 */
	public ClubInscriptionFrame(RaceDto carrera) {
		setTitle("Inscripcion como club");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelSouth(), BorderLayout.SOUTH);
		contentPane.add(getPnlNorth(), BorderLayout.NORTH);

		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		this.controller = new ClubInscriptionController(this, carrera);
		getLblCarrera().setText(carrera.nombre);
	}
	private JPanel getPanelSouth() {
		if (panelSouth == null) {
			panelSouth = new JPanel();
			panelSouth.setLayout(new GridLayout(0, 7, 0, 0));
			panelSouth.add(getPanel_1());
			panelSouth.add(getBtnConfirm());
			panelSouth.add(getPanel_2());
			panelSouth.add(getPanel_5());
			panelSouth.add(getPanel_6());
			panelSouth.add(getBtnCancelar());
		}
		return panelSouth;
	}
	protected void validateAndAddToList() {
		String email = getTextEmail().getText();
		if(!controller.checkExist(email)){
			JOptionPane.showMessageDialog(null, "El email introducido no se corresponde con un email valido");
		}
		else {
			controller.addToViewport(getPnlViewPort(), email);
		}
		getTextEmail().setText("");
		
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			JViewport view = new JViewport();
			view.add(getPnlheader());
			scrollPane.setColumnHeader(view);
			scrollPane.setViewportView(getPnlViewPort());
		}
		return scrollPane;
	}
	private JPanel getPnlheader() {
		if (pnlheader == null) {
			pnlheader = new JPanel();
			pnlheader.setLayout(new BorderLayout(0, 0));
			pnlheader.add(getPnlh1(), BorderLayout.CENTER);
			pnlheader.add(getPanel_3(), BorderLayout.EAST);
		}
		return pnlheader;
	}
	public JPanel getPnlViewPort() {
		if (pnlViewPort == null) {
			pnlViewPort = new JPanel();
			pnlViewPort.setLayout(new GridLayout(10, 0, 0, 2));
		}
		return pnlViewPort;
	}
	private JPanel getPnlh1() {
		if (pnlh1 == null) {
			pnlh1 = new JPanel();
			pnlh1.setLayout(new GridLayout(0, 3, 0, 0));
			pnlh1.add(getPnlhN());
			pnlh1.add(getPnlhE());
			pnlh1.add(getPnlhE_1());
		}
		return pnlh1;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.LIGHT_GRAY);
			panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel_3.add(getLblNewLabel_4());
		}
		return panel_3;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Nombre");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Email");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNewLabel_3;
	}
	private JPanel getPnlhN() {
		if (pnlhN == null) {
			pnlhN = new JPanel();
			pnlhN.setBackground(Color.LIGHT_GRAY);
			pnlhN.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnlhN.add(getLblNewLabel_2());
		}
		return pnlhN;
	}
	private JPanel getPnlhE() {
		if (pnlhE == null) {
			pnlhE = new JPanel();
			pnlhE.setBackground(Color.LIGHT_GRAY);
			pnlhE.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnlhE.add(getLblNewLabel_3());
		}
		return pnlhE;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("Elim");
			lblNewLabel_4.setBackground(Color.LIGHT_GRAY);
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNewLabel_4;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Calcelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}
	private JPanel getPanel_1() {
		if (panel == null) {
			panel = new JPanel();
		}
		return panel;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
		}
		return panel_2;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
		}
		return panel_5;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
		}
		return panel_6;
	}
	private JButton getBtnConfirm() {
		if (btnConfirm == null) {
			btnConfirm = new JButton("Confirmar");
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					confirm();
				}
			});
		}
		return btnConfirm;
	}

	protected void confirm() {
		if(getTextClub().getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Debe introducir un nombre de club");
			return;
		}
		controller.confirm(getTextClub().getText());
		
	}
	private JPanel getPnlNorth() {
		if (pnlNorth == null) {
			pnlNorth = new JPanel();
			pnlNorth.setLayout(new GridLayout(2, 0, 0, 0));
			pnlNorth.add(getPnlClub());
			pnlNorth.add(getPnlCard());
		}
		return pnlNorth;
	}
	private JPanel getPnlClub() {
		if (pnlClub == null) {
			pnlClub = new JPanel();
			pnlClub.setBorder(new EmptyBorder(0, 0, 10, 0));
			pnlClub.setLayout(new BorderLayout(0, 0));
			pnlClub.add(getPnlNorthCenter(), BorderLayout.CENTER);
			pnlClub.add(getPnlNorthEast(), BorderLayout.EAST);
			pnlClub.add(getPanel_1_1(), BorderLayout.WEST);
		}
		return pnlClub;
	}
	private JPanel getPnlCard() {
		if (pnlCard == null) {
			pnlCard = new JPanel();
			pnlCard.setLayout(new CardLayout(0, 0));
			pnlCard.add(getPnlForm(), "name_13045853748700");
			pnlCard.add(getPnlFile(), "name_14801482300700");
		}
		return pnlCard;
	}
	private JPanel getPnlNorthCenter() {
		if (pnlNorthCenter == null) {
			pnlNorthCenter = new JPanel();
			pnlNorthCenter.setBorder(new EmptyBorder(20, 0, 0, 0));
			pnlNorthCenter.add(getLabel_1());
			pnlNorthCenter.add(getTextClub());
		}
		return pnlNorthCenter;
	}
	private JPanel getPnlNorthEast() {
		if (pnlNorthEast == null) {
			pnlNorthEast = new JPanel();
			pnlNorthEast.add(getBtnNewButton_1());
		}
		return pnlNorthEast;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Inscripcion desde Fichero");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changeCard();
				}
			});
		}
		return btnNewButton_1;
	}
	protected void changeCard() {
		((CardLayout)getPnlCard().getLayout()).next(getPnlCard());
		
	}

	private JLabel getLabel_1() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("Nombre del Club:");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblNewLabel_5;
	}
	private JTextField getTextClub() {
		if (textClub == null) {
			textClub = new JTextField();
			textClub.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textClub.setColumns(20);
		}
		return textClub;
	}
	private JPanel getPnlForm() {
		if (pnlForm == null) {
			pnlForm = new JPanel();
			pnlForm.setLayout(new BorderLayout(0, 0));
			pnlForm.add(getPnlFormCampo(), BorderLayout.CENTER);
			pnlForm.add(getPnlFormBoton(), BorderLayout.EAST);
		}
		return pnlForm;
	}
	private JPanel getPnlFormCampo() {
		if (pnlFormCampo == null) {
			pnlFormCampo = new JPanel();
			pnlFormCampo.add(getLblNewLabel_5_1());
			pnlFormCampo.add(getTextEmail());
		}
		return pnlFormCampo;
	}
	private JPanel getPnlFormBoton() {
		if (pnlFormBoton == null) {
			pnlFormBoton = new JPanel();
			pnlFormBoton.add(getBtnAdd());
		}
		return pnlFormBoton;
	}
	private JLabel getLblNewLabel_5_1() {
		if (lblNewLabel_5_1 == null) {
			lblNewLabel_5_1 = new JLabel("Email Atleta:");
			lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblNewLabel_5_1;
	}
	public JTextField getTextEmail() {
		if (textEmail == null) {
			textEmail = new JTextField();
			textEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textEmail.setColumns(20);
		}
		return textEmail;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("A\u00F1adir");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					validateAndAddToList();
				}
			});
		}
		return btnAdd;
	}
	private JPanel getPnlhE_1() {
		if (pnlhE_1 == null) {
			pnlhE_1 = new JPanel();
			pnlhE_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnlhE_1.setBackground(Color.LIGHT_GRAY);
			pnlhE_1.add(getTextFecha());
		}
		return pnlhE_1;
	}
	private JLabel getTextFecha() {
		if (textFecha == null) {
			textFecha = new JLabel("Fecha Nacimiento");
			textFecha.setHorizontalAlignment(SwingConstants.CENTER);
			textFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return textFecha;
	}
	private JPanel getPnlFile() {
		if (pnlFile == null) {
			pnlFile = new JPanel();
			pnlFile.setLayout(new BorderLayout(0, 0));
			pnlFile.add(getPnlSelecc(), BorderLayout.CENTER);
			pnlFile.add(getPnlCargar(), BorderLayout.EAST);
		}
		return pnlFile;
	}
	private JPanel getPnlSelecc() {
		if (pnlSelecc == null) {
			pnlSelecc = new JPanel();
			pnlSelecc.add(getBtnNewButton_2());
			pnlSelecc.add(getTextNombreFichero());
		}
		return pnlSelecc;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("Seleccionar Archivo");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.cargarArchivo();
				}
			});
		}
		return btnNewButton_2;
	}
	public JTextField getTextNombreFichero() {
		if (textNombreFichero == null) {
			textNombreFichero = new JTextField();
			textNombreFichero.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textNombreFichero.setColumns(20);
		}
		return textNombreFichero;
	}
	private JPanel getPnlCargar() {
		if (pnlCargar == null) {
			pnlCargar = new JPanel();
			pnlCargar.add(getBtnNewButton_3());
		}
		return pnlCargar;
	}
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("Cargar Datos");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.cargarDatosFichero();
				}
			});
		}
		return btnNewButton_3;
	}
	private JPanel getPanel_1_1() {
		if (pnlEast == null) {
			pnlEast = new JPanel();
			pnlEast.add(getLblCarrera());
		}
		return pnlEast;
	}
	private JLabel getLblCarrera() {
		if (lblCarrera == null) {
			lblCarrera = new JLabel("Aqui va nombre de carrera");
		}
		return lblCarrera;
	}
}
