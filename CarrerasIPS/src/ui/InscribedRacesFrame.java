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

public class InscribedRacesFrame extends JFrame implements Indentificable {

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

	/**
	 * Launch the application.
	 */


	
	
	/**
	 * Create the frame.
	 */
	public InscribedRacesFrame() {
		//descomentar esta linea para que funcione la pestaña de desing
		//initialize(null, null);
	}
	
	@Override
	public void initialize(ParticipantDtoPojo part, JFrame parent) {

		this.parentFrame = parent;
		this.setVisible(true);
		this.participant = part;
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
		controller.initialize(part.dni);
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
	protected void exit() {
		parentFrame.setVisible(true);
		this.dispose();
		
		
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
			lblMail = new JLabel("Ejemplo@Correo");
		}
		return lblMail;
	}
	public JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Taso Rodriguez Meana");
		}
		return lblName;
	}



	
}
