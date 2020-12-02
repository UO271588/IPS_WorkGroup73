package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.time.TimeDto;
import model.time.TimeModel;
import ui.TimeReceptionFrame;
import util.MyFile;

public class TimeController {
	private JFileChooser jfc;
	private DefaultListModel<MyFile> modelolistLibrary;
	private TimeReceptionFrame frame;
	private TimeModel model;
	private String raceId;
	
	public TimeController(TimeReceptionFrame frame, TimeModel model, String id) {
		this.frame=frame;
		this.model=model;
		this.raceId=id;
		modelolistLibrary = new DefaultListModel<MyFile>();
		init();
		
	}

	private void init() {
		frame.getBtnAbrir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cargarArchivo();
			}
		});
		
	}

	private void cargarArchivo() {
		int respuesta = getSelector().showOpenDialog(null);
		if (respuesta == JFileChooser.APPROVE_OPTION);
			for (int i=0; i<jfc.getSelectedFiles().length;i++) {
				File seleccionado = jfc.getSelectedFiles()[i];
				modelolistLibrary.addElement(new MyFile(jfc.getSelectedFiles()[i]));
					frame.getBtnAbrir().setEnabled(false);
					leerFichero(seleccionado);
			}
			
	}
	private JFileChooser getSelector() {
		if (jfc == null) {
			jfc =new JFileChooser();
			jfc.setMultiSelectionEnabled(true);
			jfc.setFileFilter(new FileNameExtensionFilter("Archivos csv", "csv"));
			String desktopPath = System.getProperty("user.home") + "/Desktop";
			jfc.setCurrentDirectory(new File(desktopPath));
			
		}
		return jfc;
	}
	
	public void leerFichero(File seleccionado) {
		List<TimeDto> list = new ArrayList<>();
		List<TimeDto> listNoInscritas = new ArrayList<>();
		List<TimeDto> listIncorrectas = new ArrayList<>();
		BufferedReader bufferLectura = null;
		 try {
		  bufferLectura = new BufferedReader(new FileReader(seleccionado));
		  String linea = bufferLectura.readLine();
		  
		  while (linea != null) {

		   String[] campos = linea.split(";"); 
		   int dorsal = Integer.parseInt(campos[0]);
		   String tiempoInicio = campos[1];
		   String tiempoFinal = campos[2];
		  
		   linea = bufferLectura.readLine();
		   TimeDto dto = new TimeDto(dorsal,tiempoInicio,tiempoFinal);
		   boolean yaMos=false;
		   
		   if(model.existDorsal(dorsal,raceId)==true) {
			   for(TimeDto t:list) {
				   if(t.getDorsal()==dto.getDorsal() ) {
					   yaMos=true;
				   }
				   else {
					   yaMos=false;
				   }
			   }
			   if(yaMos==false) {
				   if(comprobarTime(tiempoInicio)&& comprobarTime(tiempoFinal)) {
					   list.add(dto);
				   }
				   else {
					   listIncorrectas.add(dto);
				   }
			   }
		   }
		   
		   else {
			   listNoInscritas.add(dto);
		   }
		  }
		  
		  if(!listNoInscritas.isEmpty()) {
			  boolean mostrado=false;
			  List<TimeDto> mostrados = new ArrayList<>();
			  String msg ="No seran mostrados los siguientes dorsales ya que no estan inscritos en la carrera: \n";
			  for(TimeDto d:listNoInscritas) {
				  for(TimeDto m:mostrados) {
					  if((d.getDorsal())==(m.getDorsal())) {
						  mostrado=true;
						  break;
					  }
					  else {
						  mostrado=false;
					  }
				  }
				  if(mostrado==false) {
				  msg+=d.getDorsal()+ "  ";
				  mostrados.add(d);
				  }
			  }
			  JOptionPane.showMessageDialog(null, msg);
		  }
		  
		  if(!listIncorrectas.isEmpty()) {
			  boolean mostrado=false;
			  List<TimeDto> mostrados = new ArrayList<>();
			  String msg ="No seran mostrados los siguientes dorsales ya que los tiempos no estan en modo correcto: \n";
			  for(TimeDto d:listIncorrectas) {
				  for(TimeDto m:mostrados) {
					  if((d.getDorsal())==(m.getDorsal())) {
						  mostrado=true;
						  break;
					  }
					  else {
						  mostrado=false;
					  }
				  }
				  if(mostrado==false) {
				  msg+=d.getDorsal()+ " ("+ d.getTiempoInicio() + " " + d.getTiempoFinal()+") ";
				  mostrados.add(d);
				  }
			  }
			  JOptionPane.showMessageDialog(null, msg);
		  }
		  if(!list.isEmpty()) {
		  createTimeList(list);
		  }
		  
		 }
		 
		 catch (IOException e) {
		  e.printStackTrace();
		 }
		 finally {
		  if (bufferLectura != null) {
		   try {
		    bufferLectura.close();
		   } 
		   catch (IOException e) {
		    e.printStackTrace();
		   }
		  }
		 }
	}
	private boolean comprobarTime(String tiempo) {
		String[] t = tiempo.split(":");
		if(t.length!=3) {
			return false;
		}
		if(!isNumeric(t[0]) || !isNumeric(t[1]) || !isNumeric(t[2])) {
			return false;
		}
		return true;
	}
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	private void createTimeList(List<TimeDto> list) {
		int rows = 1;
		JPanel panel = frame.getPnlViewportCenter_1();
		panel.removeAll();
		for(TimeDto dto : list) {
			panel.setLayout(new GridLayout(rows++, 0, 0, 0));
			if(rows <10) {	//para que las filas no sean demasiado grandes y siga quedando bien
				panel.setLayout(new GridLayout(10, 0, 0, 0));
			}
			panel.add(createRow(dto));
			panel.repaint();
			panel.revalidate();
		}
	}
	

	
	private JPanel createRow(TimeDto dto) {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(6, 0, 0, 0));
		JLabel lbl = new JLabel();
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		lbl.setText(dto.getDorsal()+"");
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		lbl.setText(dto.getTiempoInicio());
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		lbl.setText(dto.getTiempoFinal());
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		if(!model.existeYaClasificacion(raceId,dto.getDorsal())) {
		model.insert(raceId,dto.getDorsal(),dto.getTiempoInicio(),dto.getTiempoFinal());
		}
		return panel;
		
	}
}
