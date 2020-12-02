package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.InscribedRacesController;
import model.inscription.InscriptionModel;
import model.participant.ParticipantDto;
import model.participant.ParticipantDtoPojo;
import util.interfaces.Indentificable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class InscribedRacesFrame extends JFrame {

	private JPanel contentPane;
	private JPanel pnlSouth;
	private JButton btnNewButton;
	private JPanel pnlNorth;
	private JLabel lblTitle;
	private JPanel pnlViewport;
	private JPanel pnlViewportNorth;
	private JPanel pnlViewportCenter;
	private JLabel lblCombreCarrera;
	private JLabel lblEstadoIns;
	private JLabel lblDate;
	private JScrollPane scrollPane;
	private InscribedRacesController controller;
	private JPanel pnlData;
	private JLabel lblMail;
	private JLabel lblName;
	private JFrame parentFrame;
	private ParticipantDtoPojo participant;
	private JLabel lblNewLabel;
	private JTextField txtMail;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */


	
	

	
	public  InscribedRacesFrame(JFrame parent) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InscribedRacesFrame.class.getResource("/img/WhatsApp Image 2020-12-02 at 20.02.37.jpeg")));
		setTitle("Carreras Inscritas");
		ParticipantDtoPojo part;
		this.parentFrame = parent;
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPnlSouth(), BorderLayout.SOUTH);
		contentPane.add(getPnlNorth(), BorderLayout.NORTH);
		controller = new InscribedRacesController(this); 
	}
	


	protected void exit() {
		parentFrame.setVisible(true);
		this.dispose();
		
		
	}


	protected void readEmail() {
		controller.identificateAndLoad(getTxtMail().getText());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	public ParticipantDtoPojo getParticipant() {
		return participant;
	}


	public void setParticipant(ParticipantDtoPojo participant) {
		this.participant = participant;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setViewportView(getPnlViewport());
			JViewport view = new JViewport();
			view.add(getPnlRow());
			scrollPane.setColumnHeader(view);
		}
		return scrollPane;
	}
	private JPanel getPnlSouth() {
		if (pnlSouth == null) {
			pnlSouth = new JPanel();
			pnlSouth.setBorder(new EmptyBorder(5, 0, 0, 0));
			pnlSouth.setLayout(new GridLayout(0, 4, 0, 0));
			pnlSouth.add(getBtnVolver());
			pnlSouth.add(getLblNewLabel());
			pnlSouth.add(getTxtMail());
			pnlSouth.add(getBtnBuscar());
		}
		return pnlSouth;
	}
	public JButton getBtnVolver() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Atras");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					exit();
				}
			});
		}
		return btnNewButton;
	}


	private JPanel getPnlNorth() {
		if (pnlNorth == null) {
			pnlNorth = new JPanel();
			pnlNorth.setLayout(new BorderLayout(0, 0));
			pnlNorth.add(getLblTitle());
			pnlNorth.add(getPnlData(), BorderLayout.WEST);
		}
		return pnlNorth;
	}
	public JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Carreras Inscritas:");
			lblTitle.setBorder(new EmptyBorder(0, 0, 0, 10));
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
		}
		return lblTitle;
	}
	private JPanel getPnlViewport() {
		if (pnlViewport == null) {
			pnlViewport = new JPanel();
			pnlViewport.setLayout(new BorderLayout(0, 0));
			pnlViewport.add(getPnlViewportCenter(), BorderLayout.CENTER);

			
		}
		return pnlViewport;
	}
	private JPanel getPnlRow() {
		if (pnlViewportNorth == null) {
			pnlViewportNorth = new JPanel();
			pnlViewportNorth.setAutoscrolls(true);
			pnlViewportNorth.setBorder(new EmptyBorder(1, 0, 1, 0));
			pnlViewportNorth.setLayout(new GridLayout(0, 3, 0, 0));
			pnlViewportNorth.add(getLblCombreCarrera());
			pnlViewportNorth.add(getLblEstadoIns());
			pnlViewportNorth.add(getLblDate());
		}
		return pnlViewportNorth;
	}
	public JPanel getPnlViewportCenter() {
		if (pnlViewportCenter == null) {
			pnlViewportCenter = new JPanel();
			pnlViewportCenter.setAutoscrolls(true);
			pnlViewportCenter.setBackground(Color.WHITE);
			pnlViewportCenter.setLayout(new GridLayout(2, 0, 0, 0));
		}
		return pnlViewportCenter;
	}
	public JLabel getLblCombreCarrera() {
		if (lblCombreCarrera == null) {
			lblCombreCarrera = new JLabel("Nombre");
			lblCombreCarrera.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblCombreCarrera.setHorizontalAlignment(SwingConstants.CENTER);
			lblCombreCarrera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblCombreCarrera;
	}
	public JLabel getLblEstadoIns() {
		if (lblEstadoIns == null) {
			lblEstadoIns = new JLabel("Estado de Inscripcion");
			lblEstadoIns.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblEstadoIns.setHorizontalAlignment(SwingConstants.CENTER);
			lblEstadoIns.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblEstadoIns;
	}
	public JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Ultima Modificacion");
			lblDate.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblDate.setHorizontalAlignment(SwingConstants.CENTER);
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblDate;
	}

	public JPanel getPnlData() {
		if (pnlData == null) {
			pnlData = new JPanel();
			pnlData.setLayout(new GridLayout(2, 0, 0, 0));
			pnlData.add(getLblMail());
			pnlData.add(getLblName());
		}
		return pnlData;
	}
	public JLabel getLblMail() {
		if (lblMail == null) {
			lblMail = new JLabel("");
		}
		return lblMail;
	}
	public JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("");
		}
		return lblName;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Introduce Email:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		return lblNewLabel;
	}
	private JTextField getTxtMail() {
		if (txtMail == null) {
			txtMail = new JTextField();
			txtMail.setColumns(10);
		}
		return txtMail;
	}
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					readEmail();
				}
			});
		}
		return btnBuscar;
	}


}
