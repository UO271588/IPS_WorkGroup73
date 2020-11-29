package ui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.inscription.InscriptionDto;
import model.inscription.InscriptionModel;
import model.payments.PaymentDto;
import util.MyFile;
import util.TimeUtil;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class FicheroBancoCard extends JPanel {
	private JTextField txtNombreFichero;
	private JLabel lblNombrefichero;
	private JPanel panel_1;
	private JButton btnCargarArchivo;
	private JFileChooser jfc;
	private DefaultListModel<MyFile> modelolistLibrary;

	/**
	 * Create the panel.
	 */
	public FicheroBancoCard() {
		setLayout(new BorderLayout(0, 0));
		add(getPanel_1(), BorderLayout.CENTER);
		modelolistLibrary = new DefaultListModel<MyFile>();

	}

	private JTextField getTxtNombreFichero() {
		if (txtNombreFichero == null) {
			txtNombreFichero = new JTextField();
			txtNombreFichero.setBounds(69, 82, 306, 20);
			txtNombreFichero.setColumns(10);
		}
		return txtNombreFichero;
	}

	private JLabel getLblNombrefichero() {
		if (lblNombrefichero == null) {
			lblNombrefichero = new JLabel("Introduzca el nombre del fichero:");
			lblNombrefichero.setBounds(69, 57, 159, 14);
		}
		return lblNombrefichero;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getSelector());
			panel_1.add(getBtnCargarArchivo());
		}
		return panel_1;
	}

	private JButton getBtnCargarArchivo() {
		if (btnCargarArchivo == null) {
			btnCargarArchivo = new JButton("CargarArchivo");
			btnCargarArchivo.setBounds(128, 83, 162, 23);
			btnCargarArchivo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarArchivoFileChooser();
					
				}
			});
			btnCargarArchivo.setBackground(Color.GREEN);
		}
		return btnCargarArchivo;
	}

	public List<PaymentDto> leerFichero(File file) {
		FileReader lector = null;
		BufferedReader br = null;
		List<PaymentDto> payments = new ArrayList<PaymentDto>();
		try {
			lector = new FileReader(file);
			br = new BufferedReader(lector);
			String linea;
			while (br.ready()) {
				linea = br.readLine();
				if (!linea.contains("//")) {
					String[] partes = linea.split(",");
					if (partes.length == 4) {
						payments.add(gestionarPago(partes[0], partes[1], partes[2], partes[3]));
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return payments;
	}

	public PaymentDto gestionarPago(String dni, String idcarrera, String cantidad, String fecha) {
		InscriptionModel im = new InscriptionModel();
		List<InscriptionDto> inscripcionescarrera = im.getInscriptionList(idcarrera);
		for (InscriptionDto inscripcion : inscripcionescarrera) {
			if (inscripcion.dni.equals(dni)) {
				Date fechaInscripcion = TimeUtil.isoStringToDate(inscripcion.getInscriptionDate());
				LocalDateTime fechaIns = Instant.ofEpochMilli(fechaInscripcion.getTime()).atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				LocalDateTime fechaLimite = fechaIns.plusDays(2);
				Date fechatrans = TimeUtil.isoStringToDate(fecha);
				LocalDateTime fechaPago = Instant.ofEpochMilli(fechatrans.getTime()).atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				if (fechaLimite.plusDays(1).isAfter(fechaPago) && fechaIns.isBefore(fechaPago)) {
					double dinero = im.getDineroAPagar(idcarrera,fecha);
					if(dinero - Double.parseDouble(cantidad) > 0) {
						im.updateEstado("CANCELADO", dni, idcarrera,fecha,"Dinero insuficiente");
					}else if(dinero - Double.parseDouble(cantidad) == 0) {
						im.updateEstado("PAGADO", dni, idcarrera,fecha,null);
					}else {
						im.updateEstado("PAGADO", dni, idcarrera,fecha,null);
					}
					
					System.out.println("Fecha de pago: " + fechaPago);
					System.out.println("Fecha limite de inscripcion: " + fechaLimite);
					System.out.println("Dinero a pagar: " + dinero);
					System.out.println("Dinero suministrado: " + dinero);
					System.out.println();
				}
				
				PaymentDto pago = new PaymentDto();
				pago.dni = dni;
				pago.carrera = idcarrera;
				pago.dinero = Integer.parseInt(cantidad);
				pago.fecha = fecha;
				return pago;
			}
		}
		
		return new PaymentDto();
	}	
	
	private void cargarArchivoFileChooser() {
		int respuesta = getSelector().showOpenDialog(null);
		if(respuesta == JFileChooser.APPROVE_OPTION) {
			File seleccionado = jfc.getSelectedFile();
			if(!modelolistLibrary.contains(seleccionado)) {
				modelolistLibrary.addElement(new MyFile(jfc.getSelectedFile()));
				getBtnCargarArchivo().setEnabled(false);
				List<PaymentDto> pagos = leerFichero(seleccionado);
				new ResumenPagos(pagos).setVisible(true);
			}
		}
	}
	
	private JFileChooser getSelector() {
		if(jfc == null) {
			jfc = new JFileChooser();
			jfc.setMultiSelectionEnabled(false);
			jfc.setFileFilter(new FileNameExtensionFilter(".txt","txt"));
			String desktopPath = System.getProperty("user.home") + "/Desktop";
			jfc.setCurrentDirectory(new File (desktopPath));
		}
		return jfc;
	}
}
