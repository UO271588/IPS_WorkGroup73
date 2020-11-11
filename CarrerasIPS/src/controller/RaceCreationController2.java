package controller;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.RaceCrationFrame;

public class RaceCreationController2 {

	private static final int MAXAGE = 117;

	private RaceCrationFrame view;
	private JComboBox comboDias1;
	private JComboBox comboMeses1;
	private JComboBox comboAnios1;
	private JComboBox comboDias2;
	private JComboBox comboMeses2;
	private JComboBox comboAnios2;
	public RaceCreationController2(RaceCrationFrame view) {
		super();
		this.view = view;
	}

	public void addPlazosRow(JPanel pnlPlazosView) {
		
		pnlPlazosView.add(createRow());
		pnlPlazosView.revalidate();
		pnlPlazosView.repaint();
		if(pnlPlazosView.getComponents().length > 8) {
			pnlPlazosView.setLayout(new GridLayout(pnlPlazosView.getComponents().length, 0, 0, 0));
		}

	}

	private Component createRow() {
		comboDias1=null;
		comboDias2=null;
		comboAnios1=null;
		comboAnios2=null;
		comboMeses1=null;
		comboMeses2=null;
		JPanel row = new JPanel();
		row.setLayout(new GridLayout(0, 3, 0, 0));
		JTextField text = new JTextField("");
		
		JPanel fechaInicio = new JPanel();
		fechaInicio.setLayout(new FlowLayout());
		fechaInicio.add(getComboBoxDias1());
		fechaInicio.add(getComboBoxMeses1());
		fechaInicio.add(getComboBoxAnios1());
		JPanel fechaFinal = new JPanel();
		fechaFinal.setLayout(new FlowLayout());
		fechaFinal.add(getComboBoxDias2());
		fechaFinal.add(getComboBoxMeses2());
		fechaFinal.add(getComboBoxAnios2());
		row.add(fechaInicio);
		row.add(fechaFinal);
		row.add(text);
		return row;
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


	public void deleteCategoryRow(JPanel pnlPlazosView) {
		if(pnlPlazosView.getComponents().length > 1) {
			pnlPlazosView.remove(pnlPlazosView.getComponents().length -1);
			pnlPlazosView.revalidate();
			pnlPlazosView.repaint();
		}

	}


	public boolean validatePlazos(JPanel pnlPlazosView) {
		return validatePlazo(pnlPlazosView);
	}
	private boolean validatePlazo(JPanel pnlPlazosView) {
		
		LocalDate fechaInicio=null;
		LocalDate fechaFinal=null;
		boolean cambioAño=false;
		
		for(int i = 0; i< pnlPlazosView.getComponentCount(); i++) {
			JTextField cantidad = (JTextField)((JPanel)(pnlPlazosView.getComponent(i))).getComponent(2);
			
			JComboBox cb1 = (JComboBox)((JPanel)((JPanel)pnlPlazosView.getComponent(i)).getComponent(0)).getComponent(0);
			JComboBox cb2 = (JComboBox)((JPanel)((JPanel)pnlPlazosView.getComponent(i)).getComponent(0)).getComponent(1);
			JComboBox cb3 = (JComboBox)((JPanel)((JPanel)pnlPlazosView.getComponent(i)).getComponent(0)).getComponent(2);
			


			
			JComboBox cb4 = (JComboBox)((JPanel)((JPanel)pnlPlazosView.getComponent(i)).getComponent(1)).getComponent(0);
			JComboBox cb5 = (JComboBox)((JPanel)((JPanel)pnlPlazosView.getComponent(i)).getComponent(1)).getComponent(1);
			JComboBox cb6 = (JComboBox)((JPanel)((JPanel)pnlPlazosView.getComponent(i)).getComponent(1)).getComponent(2);
			
			
			if(cb1.getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Introduzca un dia de fecha inicial");
				
				return false;
			}
			else if(cb2.getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Introduzca un mes de fecha inicial");
				return false;
			}
			else if(cb3.getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Introduzca un año de fecha inicial");
				return false;
			}
			else if(cb4.getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Introduzca un dia de fecha final");
				return false;
			}
			else if(cb5.getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Introduzca un mes de fecha final");
				return false;
			}
			else if(cb6.getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "Introduzca un año de fecha final");
				return false;
			}
			
			if(cantidad.getText().isBlank() || formatoValido(cantidad.getText())==false) {
				JOptionPane.showMessageDialog(null, "Introduzca una cantidad válida");
				return false;
			}
			
			if(i!=0) {
				LocalDate aux = fechaFinal;
				fechaInicio = LocalDate.of((int)cb3.getSelectedItem(), (int)cb2.getSelectedIndex()+1, (int)cb1.getSelectedItem());
				
				fechaFinal = LocalDate.of((int)cb6.getSelectedItem(), (int)cb5.getSelectedIndex()+1, (int)cb4.getSelectedItem());
				if(fechaInicio.compareTo(fechaFinal)>0) {
					JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser mayor que la fecha final");
					return false;
				}
				if(aux.getMonthValue()==12 && aux.getDayOfMonth()==31) {
					if(fechaInicio.getYear()-aux.getYear()==1) {
						cambioAño=true;
					}
				}
				if(!validarFechaHoy(fechaInicio)|| !validarFechaHoy(fechaFinal)) {
					JOptionPane.showMessageDialog(null, "Las fecha no pueden ser anteriores al dia de hoy");
					return false;
				}
				if (cambioAño==false && (fechaInicio.getDayOfYear()-aux.getDayOfYear()!=1 || fechaInicio.getYear()!=aux.getYear())) {
					JOptionPane.showMessageDialog(null, "los plazos deben ir seguidos");
					return false;
				}
				if(validarFecha(fechaFinal)==false) {
					JOptionPane.showMessageDialog(null, "Las fecha no pueden ser posteriores a la fecha de carrera");
					return false;
				}
			}
			
			if(i==0) {
				fechaInicio = LocalDate.of((int)cb3.getSelectedItem(), (int)cb2.getSelectedIndex()+1, (int)cb1.getSelectedItem());
				fechaFinal = LocalDate.of((int)cb6.getSelectedItem(), (int)cb5.getSelectedIndex()+1, (int)cb4.getSelectedItem());
				if(fechaInicio.compareTo(fechaFinal)>0) {
					JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser mayor que la fecha final");
					return false;
				}
				if(!validarFechaHoy(fechaInicio)||!validarFechaHoy(fechaFinal)) {
					JOptionPane.showMessageDialog(null, "Las fechas no pueden ser anteriores al dia de hoy");
					return false;
				}
				
				if(validarFecha(fechaFinal)==false) {
					JOptionPane.showMessageDialog(null, "Las fecha no pueden ser posteriores a la fecha de carrera");
					return false;
				}
				
			}
			
			

			
			
		}
		return true;
		
	}
	protected boolean validarFechaHoy(LocalDate nueva) {
		LocalDate hoy = LocalDate.now();

		if(hoy.compareTo(nueva)>0) {
			return false;
		}
		return true;
	}
	protected boolean validarFecha(LocalDate nueva) {
		
		
		int day = view.getComboBoxDias().getSelectedIndex()+1;
		int mes = view.getComboBoxMeses().getSelectedIndex()+1;
		int year = (int)view.getComboBoxAños().getSelectedItem();
		
		LocalDate introducida = LocalDate.of(year, mes, day);
		if(nueva.compareTo(introducida)>=0) {
			return false;
		}
		return true;
	}
	
	private boolean formatoValido(String cadena) {
		if(isNumeric( cadena) || isDouble(cadena)) {
			return true;
		}
		return false;
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
			
			
			if(añobisiesto((int)getComboBoxAnios2().getSelectedItem())){
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


public boolean añobisiesto(int anio) {
	if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0)))
		return true;
	return false;
}
}
