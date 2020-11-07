package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import business.race.RaceDto;
import controller.RaceCreationController;
import util.database.Database;

public class RaceCrationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JLabel lblNameRace;
	private JTextField textField;
	private JLabel lblDistancia;
	private JTextField textDistancia;
	private JLabel lblTipo;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JLabel lblNewLabel_4;
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
	private JTextField textField_2;
	private JComboBox cbIni1F;
	private JComboBox cbFin1F;
	private JPanel rowFem2;
	private JTextField textField_3;
	private JComboBox cbIni2F;
	private JComboBox cbFin2F;
	private JPanel pnlFemR1C2;
	private JPanel pnlFemR2C2;
	private JPanel pnlMasR1C2;
	private JPanel pnlMasR2C2;
	private JPanel panel;
	private JButton btnValidarCateg;
	private JLabel lblValCategory;
	private JButton btnCrear;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private JComboBox comboBoxDias;
	private JComboBox comboBoxMeses;
	private JComboBox comboBoxAños;
	private JTextField textFieldPlazas;
	private JRadioButton rdSinDefinir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaceCrationFrame frame = new RaceCrationFrame();
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
	public RaceCrationFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnlTitulo(), BorderLayout.NORTH);
		contentPane.add(getSpnlCenter(), BorderLayout.CENTER);
		contentPane.add(getPnlCategorias(), BorderLayout.SOUTH);
		controller = new RaceCreationController(this);
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
			pnlDato1.add(getLblNameRace());
			pnlDato1.add(getTextField());
		}
		return pnlDato1;
	}
	private JPanel getPanel_1_2() {
		if (pnlDato3 == null) {
			pnlDato3 = new JPanel();
			pnlDato3.add(getLblTipo());
			pnlDato3.add(getRdbtnNewRadioButton());
			pnlDato3.add(getRdbtnNewRadioButton_1());
		}
		return pnlDato3;
	}
	private JPanel getPanel_2_1() {
		if (pnlDato2 == null) {
			pnlDato2 = new JPanel();
			pnlDato2.add(getLblDistancia());
			pnlDato2.add(getTextDistancia());
			pnlDato2.add(getLblNewLabel_1());
		}
		return pnlDato2;
	}
	private JPanel getPanel_3_1() {
		if (pnlDato5 == null) {
			pnlDato5 = new JPanel();
			pnlDato5.add(getLblNewLabel_5());
			pnlDato5.add(getComboBoxDias());
			pnlDato5.add(getComboBoxMeses());
			pnlDato5.add(getComboBoxAños());
		}
		return pnlDato5;
	}
	private JPanel getPanel_4_1() {
		if (pnlDato4 == null) {
			pnlDato4 = new JPanel();
			pnlDato4.add(getLblNewLabel_4());
			pnlDato4.add(getTextField_1_1());
			pnlDato4.add(getRdSinDefinir());
		}
		return pnlDato4;
	}
	private JLabel getLblNameRace() {
		if (lblNameRace == null) {
			lblNameRace = new JLabel("Nombre:");
		}
		return lblNameRace;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("Distancia :");
		}
		return lblDistancia;
	}
	private JTextField getTextDistancia() {
		if (textDistancia == null) {
			textDistancia = new JTextField();
			textDistancia.setColumns(10);
		}
		return textDistancia;
	}
	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo: ");
		}
		return lblTipo;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("monta\u00F1a");
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("asfalto");
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("Numero m\u00E1ximo de plazas:");
		}
		return lblNewLabel_4;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("Fecha Carrera:");
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
	private JPanel getPnlMascView() {
		if (pnlMascView == null) {
			pnlMascView = new JPanel();
			pnlMascView.setLayout(new GridLayout(8, 0, 0, 0));
			pnlMascView.add(getRowMasc1());
			pnlMascView.add(getRowMasc2());
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
			Integer[] numeros = new Integer[100];
			for(int i = 0; i<numeros.length; i++) {
				numeros[i] = i+18;
			}
			cbIni1M.setModel(new DefaultComboBoxModel(numeros));
		}
		return cbIni1M;
	}

	private JComboBox getCbIni2M() {
		if (cbIni2M == null) {
			cbIni2M = new JComboBox();
			cbIni2M.setPreferredSize(new Dimension(50, 0));
			Integer[] numeros = new Integer[100];
			for(int i = 0; i<numeros.length; i++) {
				numeros[i] = i+18;
			}
			cbIni2M.setModel(new DefaultComboBoxModel(numeros));
			cbIni2M.setSelectedIndex(18);
		}
		return cbIni2M;
	}
	private JComboBox getCbFin2M() {
		if (cbFin2M == null) {
			cbFin2M = new JComboBox();
			cbFin2M.setPreferredSize(new Dimension(50, 0));
			Integer[] numeros = new Integer[100];
			for(int i = 0; i<numeros.length; i++) {
				numeros[i] = i+18;
			}
			cbFin2M.setModel(new DefaultComboBoxModel(numeros));
			cbFin2M.setSelectedIndex(27);
		}
		return cbFin2M;
	}
	private JComboBox getCbFin1M() {
		if (cbFin1M == null) {
			cbFin1M = new JComboBox();
			cbFin1M.setPreferredSize(new Dimension(50, 0));
			Integer[] numeros = new Integer[100];
			for(int i = 0; i<numeros.length; i++) {
				numeros[i] = i+18;
			}
			cbFin1M.setModel(new DefaultComboBoxModel(numeros));
			cbFin1M.setSelectedIndex(17);
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
			txtVeteranoM.setText("Veterano");
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
	private JPanel getPnlFemView() {
		if (pnlFemView == null) {
			pnlFemView = new JPanel();
			pnlFemView.setLayout(new GridLayout(8, 0, 0, 0));
			pnlFemView.add(getRowFem1());
			pnlFemView.add(getRowFem2());
		}
		return pnlFemView;
	}
	private JPanel getRowFem1() {
		if (rowFem1 == null) {
			rowFem1 = new JPanel();
			rowFem1.setLayout(new GridLayout(0, 2, 0, 0));
			rowFem1.add(getTextField_2());
			rowFem1.add(getPnlFemR1C2());
		}
		return rowFem1;
	}
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setToolTipText("La designacion del sexo se a\u00F1ade automaticamente al nombre de la categoria");
			textField_2.setText("Senior");
			textField_2.setColumns(10);
		}
		return textField_2;
	}
	private JComboBox getCbIni1F() {
		if (cbIni1F == null) {
			cbIni1F = new JComboBox();
			cbIni1F.setPreferredSize(new Dimension(50, 0));
			Integer[] numeros = new Integer[100];
			for(int i = 0; i<numeros.length; i++) {
				numeros[i] = i+18;
			}
			cbIni1F.setModel(new DefaultComboBoxModel(numeros));
		}
		return cbIni1F;
	}
	private JComboBox getCbFin1F() {
		if (cbFin1F == null) {
			cbFin1F = new JComboBox();
			cbFin1F.setPreferredSize(new Dimension(50, 0));
			Integer[] numeros = new Integer[100];
			for(int i = 0; i<numeros.length; i++) {
				numeros[i] = i+18;
			}
			cbFin1F.setModel(new DefaultComboBoxModel(numeros));
			cbFin1F.setSelectedIndex(17);
		}
		return cbFin1F;
	}
	private JPanel getRowFem2() {
		if (rowFem2 == null) {
			rowFem2 = new JPanel();
			rowFem2.setLayout(new GridLayout(0, 2, 0, 0));
			rowFem2.add(getTextField_3());
			rowFem2.add(getPnlFemR2C2());
		}
		return rowFem2;
	}
	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setToolTipText("La designacion del sexo se a\u00F1ade automaticamente al nombre de la categoria");
			textField_3.setText("Veterano");
			textField_3.setColumns(10);
		}
		return textField_3;
	}
	private JComboBox getCbIni2F() {
		if (cbIni2F == null) {
			cbIni2F = new JComboBox();
			cbIni2F.setPreferredSize(new Dimension(50, 0));Integer[] numeros = new Integer[100];
			for(int i = 0; i<numeros.length; i++) {
				numeros[i] = i+18;
			}
			cbIni2F.setModel(new DefaultComboBoxModel(numeros));
			cbIni2F.setSelectedIndex(18);
		}
		return cbIni2F;
	}
	private JComboBox getCbFin2F() {
		if (cbFin2F == null) {
			cbFin2F = new JComboBox();
			cbFin2F.setPreferredSize(new Dimension(50, 0));Integer[] numeros = new Integer[100];
			for(int i = 0; i<numeros.length; i++) {
				numeros[i] = i+18;
			}
			cbFin2F.setModel(new DefaultComboBoxModel(numeros));
			cbFin2F.setSelectedIndex(27);
		}
		return cbFin2F;
	}
	private JPanel getPnlFemR1C2() {
		if (pnlFemR1C2 == null) {
			pnlFemR1C2 = new JPanel();
			pnlFemR1C2.setLayout(new GridLayout(0, 2, 0, 0));
			pnlFemR1C2.add(getCbFin1F());
			pnlFemR1C2.add(getCbIni1F());
		}
		return pnlFemR1C2;
	}
	private JPanel getPnlFemR2C2() {
		if (pnlFemR2C2 == null) {
			pnlFemR2C2 = new JPanel();
			pnlFemR2C2.setLayout(new GridLayout(0, 2, 0, 0));
			pnlFemR2C2.add(getCbFin2F());
			pnlFemR2C2.add(getCbIni2F());
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
		}
		return btnCrear;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Cancelar");
		}
		return btnNewButton_1;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("km");
		}
		return lblNewLabel_1;
	}
	private JComboBox getComboBoxDias() {
		if (comboBoxDias == null) {
			comboBoxDias = new JComboBox();
			for(int a=1;a<=31;a++){
				comboBoxDias.addItem(a);
			}
			
		}
		return comboBoxDias;
	}
	private JComboBox getComboBoxMeses() {
		if (comboBoxMeses == null) {
			comboBoxMeses = new JComboBox();
			comboBoxMeses.addItem("ENERO");
			comboBoxMeses.addItem("FEBRERO");
			comboBoxMeses.addItem("MARZO");
			comboBoxMeses.addItem("ABRIL");
			comboBoxMeses.addItem("MAYO");
			comboBoxMeses.addItem("JUNIO");
			comboBoxMeses.addItem("JULIO");
			comboBoxMeses.addItem("AGOSTO");
			comboBoxMeses.addItem("SEPTIEMBRE");
			comboBoxMeses.addItem("OCTUBRE");
			comboBoxMeses.addItem("NOVIEMBRE");
			comboBoxMeses.addItem("DICIEMBRE");
			comboBoxMeses.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(getComboBoxMeses().getSelectedItem().equals("ABRIL") || getComboBoxMeses().getSelectedItem().equals("JUNIO") ||
							getComboBoxMeses().getSelectedItem().equals("SEPTIEMBRE") || getComboBoxMeses().getSelectedItem().equals("NOVIEMBRE")) {
						int dia = (int) comboBoxDias.getSelectedItem();
						comboBoxDias.removeAllItems();
						for(int a=1;a<=30;a++){
							comboBoxDias.addItem(a);
					}
						comboBoxDias.setSelectedItem(dia);
				}
					else if( getComboBoxMeses().getSelectedItem().equals("FEBRERO")) {
						int dia = (int) comboBoxDias.getSelectedItem();
						comboBoxDias.removeAllItems();
						if(añobisiesto((int)comboBoxAños.getSelectedItem())){
							for(int a=1;a<=29;a++){
								comboBoxDias.addItem(a);
							}
							comboBoxDias.setSelectedItem(dia);
						}
						else {
						for(int a=1;a<=28;a++){
							comboBoxDias.addItem(a);
						}
						comboBoxDias.setSelectedItem(dia);
						}
					}
					
					else {
						int dia = (int) comboBoxDias.getSelectedItem();
						comboBoxDias.removeAllItems();
						for(int a=1;a<=31;a++){
							comboBoxDias.addItem(a);
						}
						comboBoxDias.setSelectedItem(dia);
					}
			}
			});
			
		}
		return comboBoxMeses;
	}
	private boolean añobisiesto(int anio) {
		if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0)))
			return true;
		return false;
	}
	private JComboBox getComboBoxAños() {
		if (comboBoxAños == null) {
			comboBoxAños = new JComboBox();
			comboBoxAños.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int dia = (int) comboBoxDias.getSelectedItem();
					
					
					if(añobisiesto((int)comboBoxAños.getSelectedItem()) && comboBoxMeses.getSelectedItem().equals("FEBRERO")){
						comboBoxDias.removeAllItems();
						for(int a=1;a<=29;a++){
							comboBoxDias.addItem(a);
						}
						comboBoxDias.setSelectedItem(dia);
					}
					if(!añobisiesto((int)comboBoxAños.getSelectedItem()) && comboBoxMeses.getSelectedItem().equals("FEBRERO")){
						comboBoxDias.removeAllItems();
						for(int a=1;a<=28;a++){
							comboBoxDias.addItem(a);
					}
					comboBoxDias.setSelectedItem(dia);
					}
				}
			});
			for(int a=2020;a<=2040;a++){
				comboBoxAños.addItem(a);
			}
		}
		return comboBoxAños;
	}
	private JTextField getTextField_1_1() {
		if (textFieldPlazas == null) {
			textFieldPlazas = new JTextField();
			textFieldPlazas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String numero="";
					if(rdSinDefinir.isSelected()) {
						numero = JOptionPane.showInputDialog(textFieldPlazas,  "Está seleccionado que el numero de plazas está sin definir. Si desea añadir un limite de plazas, por favor, introducelo ahora");
						System.out.print(numero);
						if (isNumeric(numero) ) {
							rdSinDefinir.setSelected(false);
							textFieldPlazas.setText(numero);
						}
						else {
							if(numero==null || numero=="") {
							}
							else {
							String numero2 = null;
							if(isNumeric(numero2)==false) {
								numero2 = JOptionPane.showInputDialog(textFieldPlazas,  "Introduzca un número máximo de plazas válido");
								if(numero2==null) {
									
								}
							}
							rdSinDefinir.setSelected(false);
							textFieldPlazas.setText(numero2);
							}
							
						}
							
					}
					
				
				}
			});
			
			textFieldPlazas.setColumns(10);
		}
		return textFieldPlazas;
	}
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	private JRadioButton getRdSinDefinir() {
		if (rdSinDefinir == null) {
			rdSinDefinir = new JRadioButton("Sin definir");
			rdSinDefinir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldPlazas.setText("");
				}
			});
		}
		return rdSinDefinir;
	}
}
