package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.race.RaceDto;
import model.RaceCreationController;
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
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RaceCrationFrame extends JFrame {

	private JPanel contentPane;
	private JPanel pnlTitulo;
	private JSplitPane spnlCenter;
	private JPanel pnlConfBasica;
	private JPanel pnlConfPlazos;
	private JPanel pnlCategorias;
	private JPanel pnlBotones;
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
	private JPanel pnlMasc;
	private JPanel pnlTitMasc;
	private JScrollPane sclMasc;
	private JLabel lblMasculina;
	private JPanel pnlMascHead;
	private JPanel pnlMascView;
	private JLabel lblMascNombre;
	private JLabel lblMascInici;
	private JLabel lblMascFin;
	private RaceCreationController controller;
	private JPanel pnlAdd;
	private JPanel pnlRemove;
	private JPanel pnlValidate;
	private JButton btnAdd;
	private JPanel pnlRbtAdd;
	private JRadioButton rbtnAddM;
	private JRadioButton rbtnAddF;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JButton btnRemove;
	private JPanel pnlRbtRemove;
	private JRadioButton rbtnRemoveM;
	private JRadioButton rbtnRemoveF;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JPanel rowMasc1;
	private JTextField txtSeniorM;
	private JComboBox cbIni1M;
	private JComboBox cbFin1M;
	private JPanel rowMasc2;
	private JTextField txtVeteranoM;
	private JComboBox cbIni2M;
	private JComboBox cbFin2M;
	private JPanel pnlFem;
	private JPanel pnlTitFem;
	private JLabel lblFemenina;
	private JScrollPane sclFem;
	private JPanel pnlFemHead;
	private JLabel lblFemNombre;
	private JLabel lblFemInici;
	private JLabel lblFemFin;
	private JPanel pnlFemView;
	private JPanel rowFem1;
	private JTextField txtSeniorF;
	private JComboBox cbFin1F;
	private JComboBox cbIni1F;
	private JPanel rowFem2;
	private JTextField txtVeteranoAF;
	private JComboBox cbFin2F;
	private JComboBox cbIniF2;
	private JPanel pnlFemR1C2;
	private JPanel pnlFemR2C2;
	private JPanel pnlMasR1C2;
	private JPanel pnlMasR2C2;
	private JPanel panel;
	private JButton btnValidarCateg;
	private JLabel lblValCategory;
	private JButton btnCrear;
	private JButton btnNewButton_1;
	private JPanel rowMasc3;
	private JTextField txtVeteranoBM;
	private JPanel pnlMasR2C2_1;
	private JComboBox cbIni3M;
	private JComboBox cbFin3M;
	private JPanel rowFem3;
	private JTextField txtVeteranoBF;
	private JPanel pnlMasR2C2_1_1;
	private JComboBox cbIni3F;
	private JComboBox cbFin3F;
	private JFrame parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaceCrationFrame frame = new RaceCrationFrame( new MainFrame());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param parent 
	 * @param actionListener 
	 */
	public RaceCrationFrame(JFrame parent) {
		this.parent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller = new RaceCreationController(this);
		setBounds(100, 100, 845, 602);
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
			pnlCategorias.add(getPanel(), BorderLayout.SOUTH);
		}
		return pnlCategorias;
	}
	private JPanel getPnlBotones() {
		if (pnlBotones == null) {
			pnlBotones = new JPanel();
			pnlBotones.setLayout(new GridLayout(3, 4, 20, 20));
			pnlBotones.add(getPnlAdd());
			pnlBotones.add(getPnlRemove());
			pnlBotones.add(getPnlValidate());
		}
		return pnlBotones;
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
			spnCategorias.setDividerSize(4);
			spnCategorias.setLeftComponent(getPnlMasc());
			spnCategorias.setRightComponent(getPnlFem());
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
	private JPanel getPnlMasc() {
		if (pnlMasc == null) {
			pnlMasc = new JPanel();
			pnlMasc.setLayout(new BorderLayout(0, 0));
			pnlMasc.add(getPnlTitMasc(), BorderLayout.NORTH);
			pnlMasc.add(getSclMasc(), BorderLayout.CENTER);
		}
		return pnlMasc;
	}
	private JPanel getPnlTitMasc() {
		if (pnlTitMasc == null) {
			pnlTitMasc = new JPanel();
			pnlTitMasc.add(getLblMasculina());
		}
		return pnlTitMasc;
	}
	private JScrollPane getSclMasc() {
		if (sclMasc == null) {
			sclMasc = new JScrollPane();
			sclMasc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			sclMasc.setColumnHeaderView(getPnlMascHead());
			sclMasc.setViewportView(getPnlMascView());
		}
		return sclMasc;
	}
	private JLabel getLblMasculina() {
		if (lblMasculina == null) {
			lblMasculina = new JLabel("Masculina");
		}
		return lblMasculina;
	}
	private JPanel getPnlMascHead() {
		if (pnlMascHead == null) {
			pnlMascHead = new JPanel();
			pnlMascHead.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnlMascHead.setLayout(new GridLayout(0, 3, 0, 0));
			pnlMascHead.add(getLblMascNombre());
			pnlMascHead.add(getLblMascInici());
			pnlMascHead.add(getLblMascFin());
		}
		return pnlMascHead;
	}
	public JPanel getPnlMascView() {
		if (pnlMascView == null) {
			pnlMascView = new JPanel();
			pnlMascView.setLayout(new GridLayout(8, 0, 0, 0));
			pnlMascView.add(getRowMasc1());
			pnlMascView.add(getRowMasc2());
			pnlMascView.add(getRowMasc3());
		}
		return pnlMascView;
	}
	private JLabel getLblMascNombre() {
		if (lblMascNombre == null) {
			lblMascNombre = new JLabel("Nombre");
			lblMascNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblMascNombre;
	}
	private JLabel getLblMascInici() {
		if (lblMascInici == null) {
			lblMascInici = new JLabel("Inicio");
			lblMascInici.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblMascInici;
	}
	private JLabel getLblMascFin() {
		if (lblMascFin == null) {
			lblMascFin = new JLabel("Fin");
			lblMascFin.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblMascFin;
	}
	private JPanel getPnlAdd() {
		if (pnlAdd == null) {
			pnlAdd = new JPanel();
			pnlAdd.setLayout(new GridLayout(0, 1, 0, 0));
			pnlAdd.add(getBtnAdd());
			pnlAdd.add(getPnlRbtAdd());
		}
		return pnlAdd;
	}
	private JPanel getPnlRemove() {
		if (pnlRemove == null) {
			pnlRemove = new JPanel();
			pnlRemove.setLayout(new GridLayout(2, 3, 0, 0));
			pnlRemove.add(getBtnRemove());
			pnlRemove.add(getPnlRbtRemove());
		}
		return pnlRemove;
	}
	private JPanel getPnlValidate() {
		if (pnlValidate == null) {
			pnlValidate = new JPanel();
			pnlValidate.setLayout(new GridLayout(2, 1, 0, 0));
			pnlValidate.add(getBtnValidarCateg());
			pnlValidate.add(getLblValCategory());
		}
		return pnlValidate;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("A\u00F1adir");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getRbtnAddM().isSelected()) {
						controller.addCategoryRow(getPnlMascView());						
					}
					else if(getRbtnAddF().isSelected()) {
						controller.addCategoryRow(getPnlFemView());						
					}
				}
			});
		}
		return btnAdd;
	}
	private JPanel getPnlRbtAdd() {
		if (pnlRbtAdd == null) {
			pnlRbtAdd = new JPanel();
			pnlRbtAdd.add(getRbtnAddM());
			pnlRbtAdd.add(getRbtnAddF());
		}
		return pnlRbtAdd;
	}
	private JRadioButton getRbtnAddM() {
		if (rbtnAddM == null) {
			rbtnAddM = new JRadioButton("M");
			rbtnAddM.setSelected(true);
			buttonGroup_1.add(rbtnAddM);
		}
		return rbtnAddM;
	}
	private JRadioButton getRbtnAddF() {
		if (rbtnAddF == null) {
			rbtnAddF = new JRadioButton("F");
			buttonGroup_1.add(rbtnAddF);
		}
		return rbtnAddF;
	}
	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Eliminar");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getRbtnRemoveM().isSelected()) {
						controller.deleteCategoryRow(getPnlMascView());
					}
					if(getRbtnRemoveF().isSelected()) {
						controller.deleteCategoryRow(getPnlFemView());
					}
				}
			});
		}
		return btnRemove;
	}
	private JPanel getPnlRbtRemove() {
		if (pnlRbtRemove == null) {
			pnlRbtRemove = new JPanel();
			pnlRbtRemove.add(getRbtnRemoveM());
			pnlRbtRemove.add(getRbtnRemoveF());
		}
		return pnlRbtRemove;
	}
	private JRadioButton getRbtnRemoveM() {
		if (rbtnRemoveM == null) {
			rbtnRemoveM = new JRadioButton("M");
			rbtnRemoveM.setSelected(true);
			buttonGroup_2.add(rbtnRemoveM);
		}
		return rbtnRemoveM;
	}
	private JRadioButton getRbtnRemoveF() {
		if (rbtnRemoveF == null) {
			rbtnRemoveF = new JRadioButton("F");
			buttonGroup_2.add(rbtnRemoveF);
		}
		return rbtnRemoveF;
	}
	private JPanel getRowMasc1() {
		if (rowMasc1 == null) {
			rowMasc1 = new JPanel();
			rowMasc1.setLayout(new GridLayout(0, 2, 0, 0));
			rowMasc1.add(getTxtSeniorM());
			rowMasc1.add(getPnlMasR1C2());
		}
		return rowMasc1;
	}
	private JTextField getTxtSeniorM() {
		if (txtSeniorM == null) {
			txtSeniorM = new JTextField();
			txtSeniorM.setToolTipText("La designacion del sexo se a\u00F1ade automaticamente al nombre de la categoria");
			txtSeniorM.setText("Senior");
			txtSeniorM.setColumns(10);
		}
		return txtSeniorM;
	}
	private JComboBox getCbIni1M() {
		if (cbIni1M == null) {
			cbIni1M = new JComboBox();
			cbIni1M.setPreferredSize(new Dimension(50, 0));
			cbIni1M.setModel(controller.createcbModelNum());
		}
		return cbIni1M;
	}

	private JComboBox getCbIni2M() {
		if (cbIni2M == null) {
			cbIni2M = new JComboBox();
			cbIni2M.setPreferredSize(new Dimension(50, 0));
			cbIni2M.setModel(controller.createcbModelNum());
			cbIni2M.setSelectedIndex(17);
		}
		return cbIni2M;
	}
	private JComboBox getCbFin2M() {
		if (cbFin2M == null) {
			cbFin2M = new JComboBox();
			cbFin2M.setPreferredSize(new Dimension(50, 0));
			cbFin2M.setModel(controller.createcbModelNum());
			cbFin2M.setSelectedIndex(36);
		}
		return cbFin2M;
	}
	private JComboBox getCbFin1M() {
		if (cbFin1M == null) {
			cbFin1M = new JComboBox();
			cbFin1M.setPreferredSize(new Dimension(50, 0));
			cbFin1M.setModel(controller.createcbModelNum());
			cbFin1M.setSelectedIndex(16);
		}
		return cbFin1M;
	}
	private JPanel getRowMasc2() {
		if (rowMasc2 == null) {
			rowMasc2 = new JPanel();
			rowMasc2.setLayout(new GridLayout(0, 2, 0, 0));
			rowMasc2.add(getTxtVeteranoM());
			rowMasc2.add(getPanel_2_1_1());
		}
		return rowMasc2;
	}
	private JTextField getTxtVeteranoM() {
		if (txtVeteranoM == null) {
			txtVeteranoM = new JTextField();
			txtVeteranoM.setToolTipText("La designacion del sexo se a\u00F1ade automaticamente al nombre de la categoria");
			txtVeteranoM.setText("Veterano A");
			txtVeteranoM.setColumns(10);
		}
		return txtVeteranoM;
	}

	private JPanel getPnlFem() {
		if (pnlFem == null) {
			pnlFem = new JPanel();
			pnlFem.setLayout(new BorderLayout(0, 0));
			pnlFem.add(getPnlTitFem(), BorderLayout.NORTH);
			pnlFem.add(getSclFem(), BorderLayout.CENTER);
		}
		return pnlFem;
	}
	private JPanel getPnlTitFem() {
		if (pnlTitFem == null) {
			pnlTitFem = new JPanel();
			pnlTitFem.add(getLblFemenina());
		}
		return pnlTitFem;
	}
	private JLabel getLblFemenina() {
		if (lblFemenina == null) {
			lblFemenina = new JLabel("Femenina");
		}
		return lblFemenina;
	}
	private JScrollPane getSclFem() {
		if (sclFem == null) {
			sclFem = new JScrollPane();
			sclFem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			sclFem.setColumnHeaderView(getPnlFemHead());
			sclFem.setViewportView(getPnlFemView());
		}
		return sclFem;
	}
	private JPanel getPnlFemHead() {
		if (pnlFemHead == null) {
			pnlFemHead = new JPanel();
			pnlFemHead.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnlFemHead.setLayout(new GridLayout(0, 3, 0, 0));
			pnlFemHead.add(getLblFemNombre());
			pnlFemHead.add(getLblFemInici());
			pnlFemHead.add(getLblFemFin());
		}
		return pnlFemHead;
	}
	private JLabel getLblFemNombre() {
		if (lblFemNombre == null) {
			lblFemNombre = new JLabel("Nombre");
			lblFemNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblFemNombre;
	}
	private JLabel getLblFemInici() {
		if (lblFemInici == null) {
			lblFemInici = new JLabel("Inicio");
			lblFemInici.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblFemInici;
	}
	private JLabel getLblFemFin() {
		if (lblFemFin == null) {
			lblFemFin = new JLabel("Fin");
			lblFemFin.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblFemFin;
	}
	public JPanel getPnlFemView() {
		if (pnlFemView == null) {
			pnlFemView = new JPanel();
			pnlFemView.setLayout(new GridLayout(8, 0, 0, 0));
			pnlFemView.add(getRowFem1());
			pnlFemView.add(getRowFem2());
			pnlFemView.add(getRowFem3());
		}
		return pnlFemView;
	}
	private JPanel getRowFem1() {
		if (rowFem1 == null) {
			rowFem1 = new JPanel();
			rowFem1.setLayout(new GridLayout(0, 2, 0, 0));
			rowFem1.add(getTxtSeniorF());
			rowFem1.add(getPnlFemR1C2());
		}
		return rowFem1;
	}
	private JTextField getTxtSeniorF() {
		if (txtSeniorF == null) {
			txtSeniorF = new JTextField();
			txtSeniorF.setToolTipText("La designacion del sexo se a\u00F1ade automaticamente al nombre de la categoria");
			txtSeniorF.setText("Senior");
			txtSeniorF.setColumns(10);
		}
		return txtSeniorF;
	}
	private JComboBox getCbFin1F() {
		if (cbFin1F == null) {
			cbFin1F = new JComboBox();
			cbFin1F.setPreferredSize(new Dimension(50, 0));
			cbFin1F.setModel(controller.createcbModelNum());
			cbFin1F.setSelectedIndex(16);
		}
		return cbFin1F;
	}
	private JComboBox getCbIni1F() {
		if (cbIni1F == null) {
			cbIni1F = new JComboBox();
			cbIni1F.setPreferredSize(new Dimension(50, 0));
			cbIni1F.setModel(controller.createcbModelNum());
		}
		return cbIni1F;
	}
	private JPanel getRowFem2() {
		if (rowFem2 == null) {
			rowFem2 = new JPanel();
			rowFem2.setLayout(new GridLayout(0, 2, 0, 0));
			rowFem2.add(getTxtVeteranoAF());
			rowFem2.add(getPnlFemR2C2());
		}
		return rowFem2;
	}
	private JTextField getTxtVeteranoAF() {
		if (txtVeteranoAF == null) {
			txtVeteranoAF = new JTextField();
			txtVeteranoAF.setToolTipText("La designacion del sexo se a\u00F1ade automaticamente al nombre de la categoria");
			txtVeteranoAF.setText("Veterano A");
			txtVeteranoAF.setColumns(10);
		}
		return txtVeteranoAF;
	}
	private JComboBox getCbFin2F() {
		if (cbFin2F == null) {
			cbFin2F = new JComboBox();
			cbFin2F.setPreferredSize(new Dimension(50, 0));
			cbFin2F.setModel(controller.createcbModelNum());
			cbFin2F.setSelectedIndex(36);
		}
		return cbFin2F;
	}
	private JComboBox getCbIniF2() {
		if (cbIniF2 == null) {
			cbIniF2 = new JComboBox();
			cbIniF2.setPreferredSize(new Dimension(50, 0));
			cbIniF2.setModel(controller.createcbModelNum());
			cbIniF2.setSelectedIndex(17);
		}
		return cbIniF2;
	}
	private JPanel getPnlFemR1C2() {
		if (pnlFemR1C2 == null) {
			pnlFemR1C2 = new JPanel();
			pnlFemR1C2.setLayout(new GridLayout(0, 2, 0, 0));
			pnlFemR1C2.add(getCbIni1F());
			pnlFemR1C2.add(getCbFin1F());
		}
		return pnlFemR1C2;
	}
	private JPanel getPnlFemR2C2() {
		if (pnlFemR2C2 == null) {
			pnlFemR2C2 = new JPanel();
			pnlFemR2C2.setLayout(new GridLayout(0, 2, 0, 0));
			pnlFemR2C2.add(getCbIniF2());
			pnlFemR2C2.add(getCbFin2F());
		}
		return pnlFemR2C2;
	}
	private JPanel getPnlMasR1C2() {
		if (pnlMasR1C2 == null) {
			pnlMasR1C2 = new JPanel();
			pnlMasR1C2.setLayout(new GridLayout(0, 2, 0, 0));
			pnlMasR1C2.add(getCbIni1M());
			pnlMasR1C2.add(getCbFin1M());
		}
		return pnlMasR1C2;
	}
	private JPanel getPanel_2_1_1() {
		if (pnlMasR2C2 == null) {
			pnlMasR2C2 = new JPanel();
			pnlMasR2C2.setLayout(new GridLayout(0, 2, 0, 0));
			pnlMasR2C2.add(getCbIni2M());
			pnlMasR2C2.add(getCbFin2M());
		}
		return pnlMasR2C2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 6, 0, 0));
			panel.add(getBtnCrear());
			panel.add(getBtnNewButton_1());
		}
		return panel;
	}
	private JButton getBtnValidarCateg() {
		if (btnValidarCateg == null) {
			btnValidarCateg = new JButton("Validar Categorias");
			btnValidarCateg.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(controller.validateCategories(getPnlMascView(), getPnlFemView())) {
						getLblValCategory().setText("Validado");
						getLblValCategory().setForeground(Color.GREEN);
					}
					else {
						getLblValCategory().setText("Incorrecto");
						getLblValCategory().setForeground(Color.RED);
					}
				}
			});
		}
		return btnValidarCateg;
	}
	private JLabel getLblValCategory() {
		if (lblValCategory == null) {
			lblValCategory = new JLabel("Sin Validar");
			lblValCategory.setForeground(Color.BLACK);
			lblValCategory.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblValCategory;
	}
	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createRace();
				}
			});
		}
		return btnCrear;
	}
	protected void createRace() {
		if(controller.validateCategories(getPnlMascView(), getPnlFemView()))
			controller.createRace();
		
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Cancelar");
		}
		return btnNewButton_1;
	}
	private JPanel getRowMasc3() {
		if (rowMasc3 == null) {
			rowMasc3 = new JPanel();
			rowMasc3.setLayout(new GridLayout(0, 2, 0, 0));
			rowMasc3.add(getTxtVeteranoBM());
			rowMasc3.add(getPnlMasR2C2_1());
		}
		return rowMasc3;
	}
	private JTextField getTxtVeteranoBM() {
		if (txtVeteranoBM == null) {
			txtVeteranoBM = new JTextField();
			txtVeteranoBM.setToolTipText("La designacion del sexo se a\u00F1ade automaticamente al nombre de la categoria");
			txtVeteranoBM.setText("Veterano B");
			txtVeteranoBM.setColumns(10);
		}
		return txtVeteranoBM;
	}
	private JPanel getPnlMasR2C2_1() {
		if (pnlMasR2C2_1 == null) {
			pnlMasR2C2_1 = new JPanel();
			pnlMasR2C2_1.setLayout(new GridLayout(0, 2, 0, 0));
			pnlMasR2C2_1.add(getCbIni3M());
			pnlMasR2C2_1.add(getCbFin3M());
		}
		return pnlMasR2C2_1;
	}
	private JComboBox getCbIni3M() {
		if (cbIni3M == null) {
			cbIni3M = new JComboBox();
			cbIni3M.setPreferredSize(new Dimension(50, 0));
			cbIni3M.setModel(controller.createcbModelNum());
			cbIni3M.setSelectedIndex(37);
		}
		return cbIni3M;
	}
	private JComboBox getCbFin3M() {
		if (cbFin3M == null) {
			cbFin3M = new JComboBox();
			cbFin3M.setPreferredSize(new Dimension(50, 0));
			cbFin3M.setModel(controller.createcbModelNum());
			cbFin3M.setSelectedIndex(99);
		}
		return cbFin3M;
	}
	private JPanel getRowFem3() {
		if (rowFem3 == null) {
			rowFem3 = new JPanel();
			rowFem3.setLayout(new GridLayout(0, 2, 0, 0));
			rowFem3.add(getTxtVeteranoBF());
			rowFem3.add(getPnlMasR2C2_1_1());
		}
		return rowFem3;
	}
	private JTextField getTxtVeteranoBF() {
		if (txtVeteranoBF == null) {
			txtVeteranoBF = new JTextField();
			txtVeteranoBF.setToolTipText("La designacion del sexo se a\u00F1ade automaticamente al nombre de la categoria");
			txtVeteranoBF.setText("Veterano B");
			txtVeteranoBF.setColumns(10);
		}
		return txtVeteranoBF;
	}
	private JPanel getPnlMasR2C2_1_1() {
		if (pnlMasR2C2_1_1 == null) {
			pnlMasR2C2_1_1 = new JPanel();
			pnlMasR2C2_1_1.setLayout(new GridLayout(0, 2, 0, 0));
			pnlMasR2C2_1_1.add(getCbIni3F());
			pnlMasR2C2_1_1.add(getCbFin3F());
		}
		return pnlMasR2C2_1_1;
	}
	private JComboBox getCbIni3F() {
		if (cbIni3F == null) {
			cbIni3F = new JComboBox();
			cbIni3F.setModel(controller.createcbModelNum());
			cbIni3F.setSelectedIndex(37);
			cbIni3F.setPreferredSize(new Dimension(50, 0));
		}
		return cbIni3F;
	}
	private JComboBox getCbFin3F() {
		if (cbFin3F == null) {
			cbFin3F = new JComboBox();
			cbFin3F.setModel(controller.createcbModelNum());
			cbFin3F.setSelectedIndex(99);
			cbFin3F.setPreferredSize(new Dimension(50, 0));
		}
		return cbFin3F;
	}
}
