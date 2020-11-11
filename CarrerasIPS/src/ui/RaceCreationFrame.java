package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.race.RaceDto;
import util.database.Database;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

public class RaceCreationFrame extends JFrame {

	private JPanel contentPane;
	private JPanel pnlTitulo;
	private JSplitPane spnlCenter;
	private JPanel pnlConfBasica;
	private JPanel pnlConfPlazos;
	private JPanel pnlCategorias;
	private JPanel pnlBotones;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JScrollPane scrollPane;
	private JSplitPane spnCategorias;
	private JLabel lblNewLabel;
	private JPanel pnlTitCat;
	private JLabel lblCategoriasTit;
	private JPanel pnlTitPlazos;
	private JLabel lbltitPlazos;
	private JPanel pblTitBasic;
	private JLabel lblTitBasic;
	private JPanel pnlDatosBasic;
	private JPanel pnlDato1;
	private JPanel pnlDato3;
	private JPanel pnlDato2;
	private JPanel pnlDato5;
	private JPanel pnlDato4;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JLabel lblNewLabel_2;
	private JTextField textField_1;
	private JLabel lblNewLabel_3;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JLabel lblNewLabel_4;
	private JComboBox comboBox;
	private JLabel lblNewLabel_5;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaceCreationFrame frame = new RaceCreationFrame();
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
	public RaceCreationFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnlTitulo(), BorderLayout.NORTH);
		contentPane.add(getSpnlCenter(), BorderLayout.CENTER);
		contentPane.add(getPnlCategorias(), BorderLayout.SOUTH);
	}

	protected RaceDto getDto(String text) throws SQLException {
		String sql = "SELECT name FROM competition where IDcompetition = ?";
		Database db = new Database();
		Connection c = db.getConnection();
		PreparedStatement pst =  c.prepareStatement(sql);
		pst.setString(1, text);
		ResultSet rs = pst.executeQuery();
		rs.next();
		RaceDto dto = new RaceDto();
		dto.id = text;
		dto.nombre = rs.getString(1);
		System.out.println(dto.id + " " +  dto.nombre);
		return dto;
	}
	private JPanel getPnlTitulo() {
		if (pnlTitulo == null) {
			pnlTitulo = new JPanel();
			pnlTitulo.add(getLblNewLabel());
		}
		return pnlTitulo;
	}
	private JSplitPane getSpnlCenter() {
		if (spnlCenter == null) {
			spnlCenter = new JSplitPane();
			spnlCenter.setLeftComponent(getPnlConfBasica());
			spnlCenter.setRightComponent(getPnlConfPlazos());
		}
		return spnlCenter;
	}
	private JPanel getPnlConfBasica() {
		if (pnlConfBasica == null) {
			pnlConfBasica = new JPanel();
			pnlConfBasica.setLayout(new BorderLayout(0, 0));
			pnlConfBasica.add(getPanel_6(), BorderLayout.NORTH);
			pnlConfBasica.add(getPanel_7(), BorderLayout.CENTER);
		}
		return pnlConfBasica;
	}
	private JPanel getPnlConfPlazos() {
		if (pnlConfPlazos == null) {
			pnlConfPlazos = new JPanel();
			pnlConfPlazos.setLayout(new BorderLayout(0, 0));
			pnlConfPlazos.add(getScrollPane());
			pnlConfPlazos.add(getPanel_1_1(), BorderLayout.NORTH);
		}
		return pnlConfPlazos;
	}
	private JPanel getPnlCategorias() {
		if (pnlCategorias == null) {
			pnlCategorias = new JPanel();
			pnlCategorias.setLayout(new BorderLayout(0, 0));
			pnlCategorias.add(getPnlBotones(), BorderLayout.EAST);
			pnlCategorias.add(getSpnCategorias(), BorderLayout.CENTER);
			pnlCategorias.add(getPanel_4(), BorderLayout.NORTH);
		}
		return pnlCategorias;
	}
	private JPanel getPnlBotones() {
		if (pnlBotones == null) {
			pnlBotones = new JPanel();
			pnlBotones.setLayout(new GridLayout(3, 4, 20, 20));
			pnlBotones.add(getBtnNewButton());
			pnlBotones.add(getBtnNewButton_1());
			pnlBotones.add(getBtnNewButton_2());
		}
		return pnlBotones;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("New button");
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("New button");
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("New button");
		}
		return btnNewButton_2;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
		}
		return scrollPane;
	}
	private JSplitPane getSpnCategorias() {
		if (spnCategorias == null) {
			spnCategorias = new JSplitPane();
		}
		return spnCategorias;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Configuracion de Carrera");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
		}
		return lblNewLabel;
	}
	private JPanel getPanel_4() {
		if (pnlTitCat == null) {
			pnlTitCat = new JPanel();
			pnlTitCat.add(getLblCategoriasTit());
		}
		return pnlTitCat;
	}
	private JLabel getLblCategoriasTit() {
		if (lblCategoriasTit == null) {
			lblCategoriasTit = new JLabel("Categorias");
		}
		return lblCategoriasTit;
	}
	private JPanel getPanel_1_1() {
		if (pnlTitPlazos == null) {
			pnlTitPlazos = new JPanel();
			pnlTitPlazos.add(getLbltitPlazos());
		}
		return pnlTitPlazos;
	}
	private JLabel getLbltitPlazos() {
		if (lbltitPlazos == null) {
			lbltitPlazos = new JLabel("Plazos");
		}
		return lbltitPlazos;
	}
	private JPanel getPanel_6() {
		if (pblTitBasic == null) {
			pblTitBasic = new JPanel();
			pblTitBasic.add(getLblTitBasic());
		}
		return pblTitBasic;
	}
	private JLabel getLblTitBasic() {
		if (lblTitBasic == null) {
			lblTitBasic = new JLabel("Datos Basicos");
		}
		return lblTitBasic;
	}
	private JPanel getPanel_7() {
		if (pnlDatosBasic == null) {
			pnlDatosBasic = new JPanel();
			pnlDatosBasic.setLayout(new GridLayout(5, 0, 0, 0));
			pnlDatosBasic.add(getPanel_8());
			pnlDatosBasic.add(getPanel_2_1());
			pnlDatosBasic.add(getPanel_1_2());
			pnlDatosBasic.add(getPanel_4_1());
			pnlDatosBasic.add(getPanel_3_1());
		}
		return pnlDatosBasic;
	}
	private JPanel getPanel_8() {
		if (pnlDato1 == null) {
			pnlDato1 = new JPanel();
			pnlDato1.add(getLblNewLabel_1());
			pnlDato1.add(getTextField());
		}
		return pnlDato1;
	}
	private JPanel getPanel_1_2() {
		if (pnlDato3 == null) {
			pnlDato3 = new JPanel();
			pnlDato3.add(getLblNewLabel_3());
			pnlDato3.add(getRdbtnNewRadioButton());
			pnlDato3.add(getRdbtnNewRadioButton_1());
		}
		return pnlDato3;
	}
	private JPanel getPanel_2_1() {
		if (pnlDato2 == null) {
			pnlDato2 = new JPanel();
			pnlDato2.add(getLblNewLabel_2());
			pnlDato2.add(getTextField_1());
		}
		return pnlDato2;
	}
	private JPanel getPanel_3_1() {
		if (pnlDato5 == null) {
			pnlDato5 = new JPanel();
			pnlDato5.add(getLblNewLabel_5());
		}
		return pnlDato5;
	}
	private JPanel getPanel_4_1() {
		if (pnlDato4 == null) {
			pnlDato4 = new JPanel();
			pnlDato4.add(getLblNewLabel_4());
			pnlDato4.add(getComboBox());
		}
		return pnlDato4;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("New label");
		}
		return lblNewLabel_1;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("New label");
		}
		return lblNewLabel_2;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
		}
		return textField_1;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("New label");
		}
		return lblNewLabel_3;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("mont");
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("ruta");
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("New label");
		}
		return lblNewLabel_4;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
		}
		return comboBox;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("New label");
		}
		return lblNewLabel_5;
	}
}
