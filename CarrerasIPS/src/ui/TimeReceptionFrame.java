package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.InscriptionListController;
import util.MyFile;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.TextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JViewport;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;

public class TimeReceptionFrame extends JFrame {

	private JPanel contentPane;
	private JFileChooser jfc;
	private DefaultListModel<MyFile> modelolistLibrary;
	private JList<MyFile> listLibrary;
	private JLabel lblNewLabel;
	private JButton btnAbrir;
	private JScrollPane scrollPane;
	private JList<MyFile> list;
	private TextArea textArea;
	private JScrollPane scrollPane_1;
	private JPanel pnlViewport;
	private JPanel pnlViewportNorth;
	private JLabel lblDorsal;
	private JLabel lblTiempoInicio;
	private JLabel lblTiempoFinal;
	private JPanel pnlViewportCenter;
	private String raceId;
	private Panel panel;
	private JButton btnVolver;
	private InscriptionListController controller;

	/**
	 * Create the frame.
	 * @param id 
	 * @param inscriptionListController 
	 */
	public TimeReceptionFrame(String id, InscriptionListController inscriptionListController) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TimeReceptionFrame.class.getResource("/img/WhatsApp Image 2020-12-02 at 20.35.14.jpeg")));
		setTitle("Time Reception");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnAbrir());
		contentPane.add(getScrollPane_1_1());
		contentPane.add(getBtnVolver());
		this.controller=inscriptionListController;
		
		

		
		
	}
	
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Seleccionar archivo .csv a procesar:");
			lblNewLabel.setBounds(95, 24, 277, 13);
		}
		return lblNewLabel;
	}
	public JButton getBtnAbrir() {
		if (btnAbrir == null) {
			btnAbrir = new JButton("Abrir");
			
			btnAbrir.setBounds(315, 20, 85, 21);
		}
		return btnAbrir;
	}
	
	
	private JScrollPane getScrollPane_1_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane_1.setBounds(57, 51, 464, 197);
			scrollPane_1.setViewportView(getPnlViewport());
		}
		return scrollPane_1;
	}
	private JPanel getPnlViewport() {
		if (pnlViewport == null) {
			pnlViewport = new JPanel();
			pnlViewport.setLayout(new BorderLayout(0, 0));
			pnlViewport.add(getPnlViewportNorth(), BorderLayout.NORTH);
			pnlViewport.add(getPnlViewportCenter_1(), BorderLayout.CENTER);
		}
		return pnlViewport;
	}
	private JPanel getPnlViewportNorth() {
		if (pnlViewportNorth == null) {
			pnlViewportNorth = new JPanel();
			pnlViewportNorth.setBorder(new EmptyBorder(1, 0, 1, 0));
			pnlViewportNorth.setAutoscrolls(true);
			pnlViewportNorth.setLayout(new GridLayout(0, 3, 0, 0));
			pnlViewportNorth.add(getLblDorsal());
			pnlViewportNorth.add(getLblTiempoInicio());
			pnlViewportNorth.add(getLblTiempoFinal());
		}
		return pnlViewportNorth;
	}
	private JLabel getLblDorsal() {
		if (lblDorsal == null) {
			lblDorsal = new JLabel("Dorsal");
			lblDorsal.setHorizontalAlignment(SwingConstants.CENTER);
			lblDorsal.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDorsal.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return lblDorsal;
	}
	private JLabel getLblTiempoInicio() {
		if (lblTiempoInicio == null) {
			lblTiempoInicio = new JLabel("Tiempo inicio");
			lblTiempoInicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblTiempoInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTiempoInicio.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return lblTiempoInicio;
	}
	private JLabel getLblTiempoFinal() {
		if (lblTiempoFinal == null) {
			lblTiempoFinal = new JLabel("Tiempo final");
			lblTiempoFinal.setHorizontalAlignment(SwingConstants.CENTER);
			lblTiempoFinal.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTiempoFinal.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return lblTiempoFinal;
	}
	public JPanel getPnlViewportCenter_1() {
		if (pnlViewportCenter == null) {
			pnlViewportCenter = new JPanel();
			pnlViewportCenter.setBackground(Color.WHITE);
			pnlViewportCenter.setAutoscrolls(true);
			pnlViewportCenter.setLayout(new GridLayout(2, 0, 0, 0));
			pnlViewportCenter.add(getPanel());
			
		}
		return pnlViewportCenter;
	}
	private Panel getPanel() {
		if (panel == null) {
			panel = new Panel();
			panel.setLayout(new GridLayout(0, 3, 0, 0));
		}
		return panel;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					controller.loadRacesRows();
					
				}
			});
			btnVolver.setBounds(436, 264, 85, 21);
		}
		return btnVolver;
	}
}