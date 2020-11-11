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
import java.time.LocalDate;
import java.time.Month;
import java.sql.Date;

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
import controller.RaceCreationController2;
import util.database.Database;
import javax.swing.JTextArea;
import java.awt.FlowLayout;

public class RaceCreationFrame extends JFrame {

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
	private JTextField textFieldName;
	private JLabel lblDistancia;
	private JTextField textDistancia;
	private JLabel lblTipo;
	private JRadioButton rdbtnMontania;
	private JRadioButton rdbtnAsfalto;
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
	private RaceCreationController2 controller2;
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
	private JPanel pnlPlazosHead;
	private JLabel lblPlazosCantidad;
	private JLabel lblPlazosFechaInicio;
	private JLabel lblPlazosFechaFinal;
	private JPanel pnlPlazosView;
	private JPanel row1;
	private JTextField textFieldCantidad;
	private JPanel pnlPlazosR1C2;
	private JPanel pnlPlazosR1C3;
	private JComboBox comboDias1;
	private JComboBox comboMeses1;
	private JComboBox comboAnios1;
	private JComboBox comboDias2;
	private JComboBox comboMeses2;
	private JComboBox comboAnios2;
	private JPanel pnlBotones_1;
	private JPanel pnlAdd_1;
	private JButton btnAdd_1;
	private JPanel pnlValidate_1;
	private JButton btnValidarPlazos;
	private JLabel lblValPlazos;
	private JPanel pnlRemove_1;
	private JButton btnRemove_1;
	private JButton btnvalidarFechaCarrera;

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
		setBounds(100, 100, 1324, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnlTitulo(), BorderLayout.NORTH);
		contentPane.add(getSpnlCenter(), BorderLayout.CENTER);
		contentPane.add(getPnlBotones_1(), BorderLayout.EAST);
		contentPane.add(getPnlCategorias(), BorderLayout.SOUTH);
		controller = new RaceCreationController(this);
		controller2 = new RaceCreationController2(this);
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
			scrollPane.setColumnHeaderView(getPnlPlazosHead());
			scrollPane.setViewportView(getPnlPlazosView());
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
			lbltitPlazos = new JLabel("Plazos inscripción");
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
			pnlDato1.add(getTextFieldName());
		}
		return pnlDato1;
	}
	private JPanel getPanel_1_2() {
		if (pnlDato3 == null) {
			pnlDato3 = new JPanel();
			pnlDato3.add(getLblTipo());
			pnlDato3.add(getRdbtnMontania());
			pnlDato3.add(getRdbtnAsfalto());
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
			pnlDato5.add(getBtnvalidarFechaCarrera());
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
	private JTextField getTextFieldName() {
		if (textFieldName == null) {
			textFieldName = new JTextField();
			textFieldName.setColumns(10);
		}
		return textFieldName;
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
	private JRadioButton getRdbtnMontania() {
		if (rdbtnMontania == null) {
			rdbtnMontania = new JRadioButton("monta\u00F1a");
			buttonGroup.add(rdbtnMontania);
		}
		return rdbtnMontania;
	}
	private JRadioButton getRdbtnAsfalto() {
		if (rdbtnAsfalto == null) {
			rdbtnAsfalto = new JRadioButton("asfalto");
			buttonGroup.add(rdbtnAsfalto);
		}
		return rdbtnAsfalto;
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
	public JPanel getPnlMascView() {
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
	public JPanel getPnlFemView() {
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
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						if(validaciones())
							createRace();
						
				}
				
			});
		}
		return btnCrear;
	}
	private boolean validaciones() {
		if(!validarNombre(getTextFieldName().getText())) {
			JOptionPane.showMessageDialog(null, "El nombre de la carrera no puede estar vacío");
			return false;
		}
		else if(!validarDistancia(getTextDistancia().getText())) {
			JOptionPane.showMessageDialog(null, "El numero de kilometros no es válido");
			return false;
		}
		else if(!validarTipo()) {
			JOptionPane.showMessageDialog(null, "Seleccione un tipo");
			return false;
		}
		else if(!validarPlazas()) {
			JOptionPane.showMessageDialog(null, "Seleccione un numero de plazas o marquelo como sin definir");
			return false;
		}
		else if(getBtnvalidarFechaCarrera().getBackground()!=Color.green) {
			JOptionPane.showMessageDialog(null, "Valide la fecha de la carrera");
			return false;
		}
		else if(!lblValPlazos.getText().equals("Validado")) {
			JOptionPane.showMessageDialog(null, "Valide los plazos de inscripcion en la carrera");
			return false;
		}
		return true;
	}
	protected void createRace() {
		if(controller.validateCategories(getPnlMascView(), getPnlFemView())) {
			if(controller2.validatePlazos(pnlPlazosView)) {
				System.out.println("Creando carrera");
			}
		}
	}
	
	protected boolean validarFecha() {
		LocalDate hoy = LocalDate.now();
		if(comboBoxDias.getSelectedItem()==null || comboBoxMeses.getSelectedItem()==null || comboBoxAños==null) {
			return false;
		}
		int day = comboBoxDias.getSelectedIndex()+1;
		int mes = comboBoxMeses.getSelectedIndex()+1;
		int year = (int)comboBoxAños.getSelectedItem();
		
		LocalDate introducida = LocalDate.of(year, mes, day);
		if(hoy.compareTo(introducida)>0) {
			return false;
		}
		return true;
	}

	protected boolean validarPlazas() {
		if(rdSinDefinir.isSelected()) {
			return true;
		}
		else {
			if(textFieldPlazas.getText()==null || textFieldPlazas.getText().isBlank() ||!isNumeric(textFieldPlazas.getText()))
				return false;
			else
				return true;
		}
	}
	

	private boolean validarTipo() {
		if(rdbtnAsfalto.isSelected() || rdbtnMontania.isSelected()) {
			return true;
		}
		return false;
	}
	private boolean validarDistancia(String d) {
		if(d.isEmpty() || d.isBlank() || !validacionNumero(d)) {

			return false;
		}
		if(isNumeric(d)) {
			if(Integer.parseInt(d) <0) {
				return false;
			}
			if(Double.parseDouble(d)<0) {
				return false;
			}
		}
		return true;
	}

	boolean validacionNumero(String numero) {
		if(isNumeric(numero) || isDouble(numero)) {
			return true;
		}
		return false;
	}
	private boolean validarNombre(String name) {
		if(name.isEmpty() || name.isBlank() )
			return false;
		return true;
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
	public JComboBox getComboBoxDias() {
		if (comboBoxDias == null) {
			comboBoxDias = new JComboBox();
			
			for(int a=1;a<=31;a++){
				comboBoxDias.addItem(a);
			}
			comboBoxDias.setSelectedItem(null);
			
		}
		return comboBoxDias;
	}
	public JComboBox getComboBoxMeses() {
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
			comboBoxMeses.setSelectedItem(null);
			comboBoxMeses.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					meses();
					}
				
				
			
			});
			
		}
		return comboBoxMeses;
	}
	protected void meses() {
		if(getComboBoxMeses().getSelectedItem().equals("ABRIL") || getComboBoxMeses().getSelectedItem().equals("JUNIO") ||
				getComboBoxMeses().getSelectedItem().equals("SEPTIEMBRE") || getComboBoxMeses().getSelectedItem().equals("NOVIEMBRE")) {

				
				if(comboBoxDias.getSelectedItem()!=null) {
					int dia = (int) comboBoxDias.getSelectedItem();
					comboBoxDias.removeAllItems();
					for(int a=1;a<=30;a++){
						comboBoxDias.addItem(a);
					}
					if(dia==31) {
						comboBoxDias.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Este mes no tiene 31 dias. Seleccione un día válido");
						
					}
					else {

						comboBoxDias.setSelectedItem(dia);
					}
				}
				else {
					comboBoxDias.removeAllItems();
					for(int a=1;a<=30;a++){
						comboBoxDias.addItem(a);
					}
					comboBoxDias.setSelectedItem(null);
					}
				}
	
		else if( getComboBoxMeses().getSelectedItem().equals("FEBRERO")) {
			
			
			if(añobisiesto((int)comboBoxAños.getSelectedItem())){
				if(comboBoxDias.getSelectedItem()!=null) {
					int dia = (int) comboBoxDias.getSelectedItem();
					comboBoxDias.removeAllItems();
					
						for(int a=1;a<=29;a++){
							comboBoxDias.addItem(a);
						}
					
					if(dia>29) {
						comboBoxDias.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
					}
					else {
						comboBoxDias.setSelectedItem(dia);
					}
				}
				else {
					comboBoxDias.removeAllItems();
					
					for(int a=1;a<=29;a++){
						comboBoxDias.addItem(a);
					}
					comboBoxDias.setSelectedItem(null);
				}
				}
			if(!añobisiesto((int)comboBoxAños.getSelectedItem())) {
				if(comboBoxDias.getSelectedItem()!=null) {
					int dia = (int) comboBoxDias.getSelectedItem();
					comboBoxDias.removeAllItems();
					
						for(int a=1;a<=28;a++){
							comboBoxDias.addItem(a);
						}
					
					if(dia>28) {
						comboBoxDias.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
					}
					else {
						comboBoxDias.setSelectedItem(dia);
					}
				}
				else {
					comboBoxDias.removeAllItems();
					
					for(int a=1;a<=29;a++){
						comboBoxDias.addItem(a);
					}
					comboBoxDias.setSelectedItem(null);
				}
			}
		}
				
		else {
			if(comboBoxDias.getSelectedItem()==null) {
			
			comboBoxDias.removeAllItems();
			for(int a=1;a<=31;a++){
				comboBoxDias.addItem(a);
			}
			comboBoxDias.setSelectedItem(null);
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
		
	}

	public boolean añobisiesto(int anio) {
		if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0)))
			return true;
		return false;
	}
	public JComboBox getComboBoxAños() {
		if (comboBoxAños == null) {
			comboBoxAños = new JComboBox();
			comboBoxAños.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					
					años();
					
				}
			});
			
			for(int a=2020;a<=2040;a++){
				comboBoxAños.addItem(a);
			}
			
		}
		return comboBoxAños;
	}
	protected void años() {
		if(comboBoxDias.getSelectedItem()!=null) {
			int dia = (int) comboBoxDias.getSelectedItem();
		if(añobisiesto((int)comboBoxAños.getSelectedItem()) && comboBoxMeses.getSelectedItem().equals("FEBRERO")){
			comboBoxDias.removeAllItems();
			for(int a=1;a<=29;a++){
				comboBoxDias.addItem(a);
			}
			if(dia>29) {
				comboBoxDias.setSelectedItem(null);
				JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
				
			}
			else {
			comboBoxDias.setSelectedItem(dia);
			}
			
		}
		if(!añobisiesto((int)comboBoxAños.getSelectedItem()) && comboBoxMeses.getSelectedItem().equals("FEBRERO")){
			comboBoxDias.removeAllItems();
			for(int a=1;a<=28;a++){
				comboBoxDias.addItem(a);
		}
			if(comboBoxMeses.getSelectedItem().equals("FEBRERO")) {
				if(dia>28) {
					comboBoxDias.setSelectedItem(null);
					JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
					
				}
				else {
				comboBoxDias.setSelectedItem(dia);
				}
			}
				
		}
		
		
		
		
		}
		
	}

	private JTextField getTextField_1_1() {
		if (textFieldPlazas == null) {
			textFieldPlazas = new JTextField();
			
			
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
	private static boolean isDouble(String cadena) {
		try {
			Double.parseDouble(cadena);
			return true;
		}
		catch (NumberFormatException nfe){
			return false;
		}
	}
	private JRadioButton getRdSinDefinir() {
		if (rdSinDefinir == null) {
			rdSinDefinir = new JRadioButton("Sin definir");
			rdSinDefinir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdSinDefinir.isSelected()) {
					textFieldPlazas.setText("");
					textFieldPlazas.setEnabled(false);
					}
					if(rdSinDefinir.isSelected()==false) {
						textFieldPlazas.setEnabled(true);
					}
				}
			});
		}
		return rdSinDefinir;
	}
	private JPanel getPnlPlazosHead() {
		if (pnlPlazosHead == null) {
			pnlPlazosHead = new JPanel();
			pnlPlazosHead.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnlPlazosHead.setLayout(new GridLayout(0, 3, 0, 0));
			
			pnlPlazosHead.add(getLblPlazosFechaInicio());
			pnlPlazosHead.add(getLblPlazosFechaFinal());
			pnlPlazosHead.add(getLblPlazosCantidad());
		}
		return pnlPlazosHead;
	}
	private JLabel getLblPlazosCantidad() {
		if (lblPlazosCantidad == null) {
			lblPlazosCantidad = new JLabel("Cantidad a pagar");
			lblPlazosCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPlazosCantidad;
	}
	private JLabel getLblPlazosFechaInicio() {
		if (lblPlazosFechaInicio == null) {
			lblPlazosFechaInicio = new JLabel("Fecha inicio");
			lblPlazosFechaInicio.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPlazosFechaInicio;
	}
	private JLabel getLblPlazosFechaFinal() {
		if (lblPlazosFechaFinal == null) {
			lblPlazosFechaFinal = new JLabel("Fecha final");
			lblPlazosFechaFinal.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPlazosFechaFinal;
	}
	private JPanel getPnlPlazosView() {
		if (pnlPlazosView == null) {
			pnlPlazosView = new JPanel();
			pnlPlazosView.setLayout(new GridLayout(8, 0, 0, 0));
			pnlPlazosView.add(getRow1());
		}
		return pnlPlazosView;
	}
	private JPanel getRow1() {
		if (row1 == null) {
			row1 = new JPanel();
			row1.setLayout(new GridLayout(0, 3, 0, 0));
			
			row1.add(getPnlPlazosR1C2());
			row1.add(getPanel_1_3());
			row1.add(getTextFieldCantidad());
		}
		return row1;
	}
	private JTextField getTextFieldCantidad() {
		if (textFieldCantidad == null) {
			textFieldCantidad = new JTextField();

		}
		return textFieldCantidad;
	}
	private JPanel getPnlPlazosR1C2() {
		if (pnlPlazosR1C2 == null) {
			pnlPlazosR1C2 = new JPanel();
			pnlPlazosR1C2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnlPlazosR1C2.add(getComboBoxDias1());
			pnlPlazosR1C2.add(getComboBoxMeses1());
			pnlPlazosR1C2.add(getComboBoxAnios1());
		}
		return pnlPlazosR1C2;
	}
	private JPanel getPanel_1_3() {
		if (pnlPlazosR1C3 == null) {
			pnlPlazosR1C3 = new JPanel();
			pnlPlazosR1C3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnlPlazosR1C3.add(getComboBoxDias2());
			pnlPlazosR1C3.add(getComboBoxMeses2());
			pnlPlazosR1C3.add(getComboBoxAnios2());
		}
		return pnlPlazosR1C3;
	}
	private JComboBox getComboBoxDias1() {
		if (comboDias1 == null) {
			comboDias1 = new JComboBox();
			
			for(int a=1;a<=31;a++){
				comboDias1.addItem(a);
			}
			comboDias1.setSelectedItem(null);
		}
		return comboDias1;
	}
	private JComboBox getComboBoxMeses1() {
		if (comboMeses1 == null) {
			comboMeses1 = new JComboBox();
			comboMeses1.addItem("ENERO");
			comboMeses1.addItem("FEBRERO");
			comboMeses1.addItem("MARZO");
			comboMeses1.addItem("ABRIL");
			comboMeses1.addItem("MAYO");
			comboMeses1.addItem("JUNIO");
			comboMeses1.addItem("JULIO");
			comboMeses1.addItem("AGOSTO");
			comboMeses1.addItem("SEPTIEMBRE");
			comboMeses1.addItem("OCTUBRE");
			comboMeses1.addItem("NOVIEMBRE");
			comboMeses1.addItem("DICIEMBRE");
			comboMeses1.setSelectedItem(null);
			comboMeses1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					meses1();
				}
			});
			
		}
		return comboMeses1;
	}
	protected void meses1() {
		if(getComboBoxMeses1().getSelectedItem().equals("ABRIL") || getComboBoxMeses1().getSelectedItem().equals("JUNIO") ||
				getComboBoxMeses1().getSelectedItem().equals("SEPTIEMBRE") || getComboBoxMeses1().getSelectedItem().equals("NOVIEMBRE")) {

				
				if(comboDias1.getSelectedItem()!=null) {
					int dia = (int) comboDias1.getSelectedItem();
					comboDias1.removeAllItems();
					for(int a=1;a<=30;a++){
						comboDias1.addItem(a);
					}
					if(dia==31) {
						comboDias1.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Este mes no tiene 31 dias. Seleccione un día válido");
						
					}
					else {

						comboDias1.setSelectedItem(dia);
					}
				}
				else {
					comboDias1.removeAllItems();
					for(int a=1;a<=30;a++){
						comboDias1.addItem(a);
					}
					comboDias1.setSelectedItem(null);
					}
				}
	
		else if( getComboBoxMeses1().getSelectedItem().equals("FEBRERO")) {
			
			
			if(añobisiesto((int)getComboBoxAnios1().getSelectedItem())){
				if(comboDias1.getSelectedItem()!=null) {
					int dia = (int) comboDias1.getSelectedItem();
					comboDias1.removeAllItems();
					
						for(int a=1;a<=29;a++){
							comboDias1.addItem(a);
						}
					
					if(dia>29) {
						comboDias1.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
					}
					else {
						comboDias1.setSelectedItem(dia);
					}
				}
				else {
					comboDias1.removeAllItems();
					
					for(int a=1;a<=29;a++){
						comboDias1.addItem(a);
					}
					comboDias1.setSelectedItem(null);
				}
				}
			if(!añobisiesto((int)comboAnios1.getSelectedItem())) {
				if(comboDias1.getSelectedItem()!=null) {
					int dia = (int) comboDias1.getSelectedItem();
					comboDias1.removeAllItems();
					
						for(int a=1;a<=28;a++){
							comboDias1.addItem(a);
						}
					
					if(dia>28) {
						comboDias1.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
					}
					else {
						comboDias1.setSelectedItem(dia);
					}
				}
				else {
					comboDias1.removeAllItems();
					
					for(int a=1;a<=29;a++){
						comboDias1.addItem(a);
					}
					comboDias1.setSelectedItem(null);
				}
			}
		}
				
		else {
			if(comboDias1.getSelectedItem()==null) {
			
			comboDias1.removeAllItems();
			for(int a=1;a<=31;a++){
				comboDias1.addItem(a);
			}
			comboDias1.setSelectedItem(null);
			}
			else {
				int dia = (int) comboDias1.getSelectedItem();
				comboDias1.removeAllItems();
				for(int a=1;a<=31;a++){
					comboDias1.addItem(a);
				}
				comboDias1.setSelectedItem(dia);
				}
			}
		
	}

	private JComboBox getComboBoxAnios1() {
		if (comboAnios1 == null) {
			comboAnios1 = new JComboBox();
			comboAnios1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anios1();
				}
			});
			for(int a=2020;a<=2040;a++){
				comboAnios1.addItem(a);
			}
		}
		return comboAnios1;
	}
	protected void anios1() {
		
		if(comboDias1.getSelectedItem()!=null) {
			int dia = (int) comboDias1.getSelectedItem();
		if(añobisiesto((int)comboAnios1.getSelectedItem()) && comboMeses1.getSelectedItem().equals("FEBRERO")){
			comboDias1.removeAllItems();
			for(int a=1;a<=29;a++){
				comboDias1.addItem(a);
			}
			if(dia>29) {
				comboDias1.setSelectedItem(null);
				JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
				
			}
			else {
			comboDias1.setSelectedItem(dia);
			}
			
		}
		if(!añobisiesto((int)comboAnios1.getSelectedItem()) && comboMeses1.getSelectedItem().equals("FEBRERO")){
			comboDias1.removeAllItems();
			for(int a=1;a<=28;a++){
				comboDias1.addItem(a);
		}
			if(comboMeses1.getSelectedItem().equals("FEBRERO")) {
				if(dia>28) {
					comboDias1.setSelectedItem(null);
					JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
					
				}
				else {
				comboDias1.setSelectedItem(dia);
				}
			}
				
		}
		}
	}

	private JComboBox getComboBoxDias2() {
		if (comboDias2 == null) {
			comboDias2 = new JComboBox();
			for(int a=1;a<=31;a++){
				comboDias2.addItem(a);
			}
			comboDias2.setSelectedItem(null);
		}
		return comboDias2;
	}
	private JComboBox getComboBoxMeses2() {
		if (comboMeses2 == null) {
			comboMeses2 = new JComboBox();
			comboMeses2 = new JComboBox();
			comboMeses2.addItem("ENERO");
			comboMeses2.addItem("FEBRERO");
			comboMeses2.addItem("MARZO");
			comboMeses2.addItem("ABRIL");
			comboMeses2.addItem("MAYO");
			comboMeses2.addItem("JUNIO");
			comboMeses2.addItem("JULIO");
			comboMeses2.addItem("AGOSTO");
			comboMeses2.addItem("SEPTIEMBRE");
			comboMeses2.addItem("OCTUBRE");
			comboMeses2.addItem("NOVIEMBRE");
			comboMeses2.addItem("DICIEMBRE");
			comboMeses2.setSelectedItem(null);
			comboMeses2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					meses2();
				}
			});
			
			
		}
		return comboMeses2;
	}
	private JComboBox getComboBoxAnios2() {
		if (comboAnios2 == null) {
			comboAnios2 = new JComboBox();
			comboAnios2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anios2();
				}
			});
			for(int a=2020;a<=2040;a++){
				comboAnios2.addItem(a);
			}
		}
		return comboAnios2;
	}
	
	protected void meses2() {
		if(getComboBoxMeses2().getSelectedItem().equals("ABRIL") || getComboBoxMeses2().getSelectedItem().equals("JUNIO") ||
				getComboBoxMeses2().getSelectedItem().equals("SEPTIEMBRE") || getComboBoxMeses2().getSelectedItem().equals("NOVIEMBRE")) {

				
				if(comboDias2.getSelectedItem()!=null) {
					int dia = (int) comboDias2.getSelectedItem();
					comboDias2.removeAllItems();
					for(int a=1;a<=30;a++){
						comboDias2.addItem(a);
					}
					if(dia==31) {
						comboDias2.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Este mes no tiene 31 dias. Seleccione un día válido");
						
					}
					else {

						comboDias2.setSelectedItem(dia);
					}
				}
				else {
					comboDias2.removeAllItems();
					for(int a=1;a<=30;a++){
						comboDias2.addItem(a);
					}
					comboDias2.setSelectedItem(null);
					}
				}
	
		else if( getComboBoxMeses2().getSelectedItem().equals("FEBRERO")) {
			
			
			if(añobisiesto((int)comboBoxAños.getSelectedItem())){
				if(comboDias2.getSelectedItem()!=null) {
					int dia = (int) comboDias2.getSelectedItem();
					comboDias2.removeAllItems();
					
						for(int a=1;a<=29;a++){
							comboDias2.addItem(a);
						}
					
					if(dia>29) {
						comboDias2.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
					}
					else {
						comboDias2.setSelectedItem(dia);
					}
				}
				else {
					comboDias2.removeAllItems();
					
					for(int a=1;a<=29;a++){
						comboDias2.addItem(a);
					}
					comboDias2.setSelectedItem(null);
				}
				}
			if(!añobisiesto((int)comboAnios2.getSelectedItem())) {
				if(comboDias2.getSelectedItem()!=null) {
					int dia = (int) comboDias2.getSelectedItem();
					comboDias2.removeAllItems();
					
						for(int a=1;a<=28;a++){
							comboDias2.addItem(a);
						}
					
					if(dia>28) {
						comboDias2.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
					}
					else {
						comboDias2.setSelectedItem(dia);
					}
				}
				else {
					comboDias2.removeAllItems();
					
					for(int a=1;a<=29;a++){
						comboDias2.addItem(a);
					}
					comboDias2.setSelectedItem(null);
				}
			}
		}
				
		else {
			if(comboDias2.getSelectedItem()==null) {
			
			comboDias2.removeAllItems();
			for(int a=1;a<=31;a++){
				comboDias2.addItem(a);
			}
			comboDias2.setSelectedItem(null);
			}
			else {
				int dia = (int) comboDias2.getSelectedItem();
				comboDias2.removeAllItems();
				for(int a=1;a<=31;a++){
					comboDias2.addItem(a);
				}
				comboDias2.setSelectedItem(dia);
				}
			}
		
	}
	
	protected void anios2() {
		
		if(comboDias2.getSelectedItem()!=null) {
			int dia = (int) comboDias2.getSelectedItem();
		if(añobisiesto((int)comboAnios2.getSelectedItem()) && comboMeses2.getSelectedItem().equals("FEBRERO")){
			comboDias2.removeAllItems();
			for(int a=1;a<=29;a++){
				comboDias2.addItem(a);
			}
			if(dia>29) {
				comboDias2.setSelectedItem(null);
				JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
				
			}
			else {
			comboDias2.setSelectedItem(dia);
			}
			
		}
		if(!añobisiesto((int)comboAnios2.getSelectedItem()) && comboMeses2.getSelectedItem().equals("FEBRERO")){
			comboDias2.removeAllItems();
			for(int a=1;a<=28;a++){
				comboDias2.addItem(a);
		}
			if(comboMeses2.getSelectedItem().equals("FEBRERO")) {
				if(dia>28) {
					comboDias2.setSelectedItem(null);
					JOptionPane.showMessageDialog(null, "Este mes no tiene tantos días. Seleccione un día válido");
					
				}
				else {
				comboDias2.setSelectedItem(dia);
				}
			}
				
		}
		}
	}
	private JPanel getPnlBotones_1() {
		if (pnlBotones_1 == null) {
			pnlBotones_1 = new JPanel();
			pnlBotones_1.setLayout(new GridLayout(3, 4, 20, 20));
			pnlBotones_1.add(getPnlAdd_1());
			pnlBotones_1.add(getPnlRemove_1());
			pnlBotones_1.add(getPnlValidate_1());
			
		}
		return pnlBotones_1;
	}
	private JPanel getPnlAdd_1() {
		if (pnlAdd_1 == null) {
			pnlAdd_1 = new JPanel();
			pnlAdd_1.setLayout(new GridLayout(0, 1, 0, 0));
			pnlAdd_1.add(getBtnAdd_1());
		}
		return pnlAdd_1;
	}
	private JButton getBtnAdd_1() {
		if (btnAdd_1 == null) {
			btnAdd_1 = new JButton("A\u00F1adir");
			btnAdd_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller2.addPlazosRow(getPnlPlazosView());
				}
			});
		}
		return btnAdd_1;
	}
	private JPanel getPnlValidate_1() {
		if (pnlValidate_1 == null) {
			pnlValidate_1 = new JPanel();
			pnlValidate_1.setLayout(new GridLayout(2, 1, 0, 0));
			pnlValidate_1.add(getBtnValidarPlazos());
			pnlValidate_1.add(getLblValPlazos());
		}
		return pnlValidate_1;
	}
	private JButton getBtnValidarPlazos() {
		if (btnValidarPlazos == null) {
			btnValidarPlazos = new JButton("Validar Plazos");
			btnValidarPlazos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(btnvalidarFechaCarrera.getBackground()!=Color.green) {
						lblValPlazos.setText("Valide fecha carrera");
						lblValPlazos.setForeground(Color.RED);
						btnvalidarFechaCarrera.setBackground(Color.red);
					}
				else if(controller2.validatePlazos(pnlPlazosView)) {
						lblValPlazos.setText("Validado");
						lblValPlazos.setForeground(Color.GREEN);
					}
					else {
						lblValPlazos.setText("Incorrecto");
						lblValPlazos.setForeground(Color.RED);
					}
				}
			});
		}
		return btnValidarPlazos;
	}
	private JLabel getLblValPlazos() {
		if (lblValPlazos == null) {
			lblValPlazos = new JLabel("Sin Validar");
			lblValPlazos.setHorizontalAlignment(SwingConstants.CENTER);
			lblValPlazos.setForeground(Color.BLACK);
		}
		return lblValPlazos;
	}
	private JPanel getPnlRemove_1() {
		if (pnlRemove_1 == null) {
			pnlRemove_1 = new JPanel();
			pnlRemove_1.setLayout(new GridLayout(1, 1, 0, 0));
			pnlRemove_1.add(getBtnRemove_1());
		}
		return pnlRemove_1;
	}
	private JButton getBtnRemove_1() {
		if (btnRemove_1 == null) {
			btnRemove_1 = new JButton("Eliminar");
			btnRemove_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller2.deleteCategoryRow(getPnlPlazosView());
				}
			});
		}
		return btnRemove_1;
	}
	private JButton getBtnvalidarFechaCarrera() {
		if (btnvalidarFechaCarrera == null) {
			btnvalidarFechaCarrera = new JButton("Validar");
			btnvalidarFechaCarrera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!validarFecha()) {
						btnvalidarFechaCarrera.setBackground(Color.red);
						JOptionPane.showMessageDialog(null, "La fecha introducida tiene que ser posterior al día de hoy");
						
					}
					else {
						btnvalidarFechaCarrera.setBackground(Color.green);
					}
				}
			});
		}
		return btnvalidarFechaCarrera;
	}
}