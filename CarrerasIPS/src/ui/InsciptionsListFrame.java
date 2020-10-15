package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Button;
import java.awt.Panel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JViewport;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.InscriptionListController;

import java.awt.Color;

public class InsciptionsListFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnNewButton;
	private JPanel pnlNorth;
	private JLabel lblParticipantes;
	private JPanel pnlViewport;
	private JPanel pnlViewportNorth;
	private JPanel pnlViewportCenter;
	private JLabel lblDni;
	private JLabel lblNombre;
	private JLabel lblCategoria;
	private JLabel lblFecha;
	private JLabel lblNewLabel;
	private InscriptionListController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsciptionsListFrame frame = new InsciptionsListFrame();
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
	public InsciptionsListFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
		contentPane.add(getPnlNorth(), BorderLayout.NORTH);
		controller = new InscriptionListController(this);
		controller.loadRows();
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
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 0, 0, 0));
			panel.setLayout(new GridLayout(0, 4, 0, 0));
			panel.add(getBtnNewButton());
		}
		return panel;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Atras");
		}
		return btnNewButton;
	}
	private JPanel getPnlNorth() {
		if (pnlNorth == null) {
			pnlNorth = new JPanel();
			pnlNorth.add(getLblParticipantes());
		}
		return pnlNorth;
	}
	private JLabel getLblParticipantes() {
		if (lblParticipantes == null) {
			lblParticipantes = new JLabel("Participantes");
			lblParticipantes.setFont(new Font("Arial", Font.BOLD, 23));
		}
		return lblParticipantes;
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
			pnlViewportNorth.setLayout(new GridLayout(0, 5, 0, 0));
			pnlViewportNorth.add(getLblDni());
			pnlViewportNorth.add(getLblNombre());
			pnlViewportNorth.add(getLblCategoria());
			pnlViewportNorth.add(getLblFecha());
			pnlViewportNorth.add(getLblNewLabel());
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
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI");
			lblDni.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblDni.setHorizontalAlignment(SwingConstants.CENTER);
			lblDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblDni;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblNombre;
	}
	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Categoria");
			lblCategoria.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblCategoria;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
			lblFecha.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
			lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblFecha;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Estado");
			lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblNewLabel;
	}
	
}
