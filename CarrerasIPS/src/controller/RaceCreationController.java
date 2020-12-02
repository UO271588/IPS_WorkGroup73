package controller;

import java.awt.Component;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.race.RaceDto;
import dbAccess.CompetitionsAccess;
import model.category.CategoryDto;
import model.category.CategoryModel;
import model.deadline.DeadLineDto;
import model.deadline.DeadLineModel;
import ui.RaceCreationFrame;
import util.TimeUtil;

public class RaceCreationController {
	
	private static final int MAXAGE = 117;

	private RaceCreationFrame view;

	public RaceCreationController(RaceCreationFrame view) {
		super();
		this.view = view;
	}

	public void addCategoryRow(JPanel pnlMascView) {
		System.out.println("hola");
		
		JComboBox cbLastCategory = (JComboBox)((JPanel)((JPanel)pnlMascView.getComponent(pnlMascView.getComponentCount()-1)).getComponent(1)).getComponent(1);
		pnlMascView.add(createRow((Integer)cbLastCategory.getSelectedItem()));
		pnlMascView.revalidate();
		pnlMascView.repaint();
		if(pnlMascView.getComponents().length > 8) {
			pnlMascView.setLayout(new GridLayout(pnlMascView.getComponents().length, 0, 0, 0));
		}

	}

	private Component createRow(int minInt) {
		JPanel row = new JPanel();
		row.setLayout(new GridLayout(0, 2, 0, 0));
		JTextField text = new JTextField("Nueva Categoria");
		row.add(text);
		JPanel rightCol = new JPanel();
		rightCol.setLayout(new GridLayout(0, 2, 0, 0));
		if(minInt >= MAXAGE -1) {
			rightCol.add(createComboBox(minInt));
		}
		else
			rightCol.add(createComboBox(minInt +1));
		
		if(minInt +10 > MAXAGE - 1) {
			rightCol.add(createComboBox(MAXAGE));
		}
		else
			rightCol.add(createComboBox(minInt + 10));		
		row.add(rightCol);
		return row;
	}

	private JComboBox createComboBox(int inicio) {
		JComboBox cb = new JComboBox();
		Integer[] numeros = new Integer[MAXAGE -17];
		for(int i = 0; i<numeros.length; i++) {
			numeros[i] = i+18;
		}
		cb.setModel(new DefaultComboBoxModel(numeros));
		System.out.println(inicio);
		cb.setSelectedIndex(inicio-18);
		return cb;
	}

	public void deleteCategoryRow(JPanel pnlMascView) {
		if(pnlMascView.getComponents().length > 1) {
			pnlMascView.remove(pnlMascView.getComponents().length -1);;
			pnlMascView.revalidate();
			pnlMascView.repaint();
		}

	}


	public boolean validateCategories(JPanel pnlMascView, JPanel pnlFemView) {
		return validateCategory(pnlMascView) && validateCategory(pnlFemView);
	}
	private boolean validateCategory(JPanel pnlView) {
		int first = 0;
		int second = 0;
		for(int i = 0; i< pnlView.getComponentCount(); i++) {
			JComboBox cb1 = (JComboBox)((JPanel)((JPanel)pnlView.getComponent(i)).getComponent(1)).getComponent(0);
			JComboBox cb2 = (JComboBox)((JPanel)((JPanel)pnlView.getComponent(i)).getComponent(1)).getComponent(1);
			first = (Integer)cb1.getSelectedItem();

			
			if(i!= 0 && first != second +1) {
				JOptionPane.showMessageDialog(null, "Error:\nNo puede haber huecos entre categorias");
				return false;
			}
			second = (Integer)cb2.getSelectedItem();
			if(first > second) {
				JOptionPane.showMessageDialog(null, "Error:\nLa edad de inicio debe de ser menor que la final");
				return false;
			}
		}
		return true;	
	}

	public ComboBoxModel<Integer> createcbModelNum() {

	
		Integer[] numeros = new Integer[100];
		for(int i = 0; i<numeros.length; i++) {
			numeros[i] = i+18;
		}
		return new DefaultComboBoxModel<Integer>(numeros);
	}

	public void createRace() {
		RaceDto race = new RaceDto();
		race.aforoActual = 0;
		if(view.getRdSinDefinir().isSelected())
			race.aforoMax = Integer.MAX_VALUE;
		else			
			race.aforoMax = Integer.parseInt(view.getTextFieldPlazas().getText());
		race.distancia = Double.parseDouble(view.getTextDistancia().getText());
		race.fechaCarrera = parseDate(view.getComboBoxDias(), view.getComboBoxMeses(), view.getComboBoxAños());
		race.fechaLimite = new Date();
		race.id = UUID.randomUUID().toString();
		race.nombre = view.getTextFieldName().getText();
		race.precioInscripcion = 0;
		race.reserved = view.getComboBoxReservados().getSelectedIndex();
		if(view.getRdbtnAsfalto().isSelected())
			race.tipo = "ASFALTO";
		else {
			race.tipo = "MONTANA";
		}
		if(view.getRdbtnSecuenc().isSelected()) {
			race.secuencial=true;
		}
		if(!view.getRdbtnSecuenc().isSelected()) {
			race.secuencial=false;
		}
		if(view.getRdbtnAlReg().isSelected()) {
			race.inMomentInscription=true;
		}
		if(!view.getRdbtnAlReg().isSelected()) {
			race.inMomentInscription=false;
		}
		createCategories(race.id);
		race.fechaLimite = createDeadLines(race.id);
		CompetitionsAccess.createRace(race);
		
		StringBuilder sb =  new StringBuilder();
		sb.append("Carrera Creada");
		sb.append("\nNombre: " + race.nombre);
		sb.append("\nTipo: " + race.tipo);
		sb.append("\nDistancia: " + race.distancia);
		sb.append("\nFecha Competicion: " + TimeUtil.dateToIsoString(race.fechaCarrera));
		if(view.getRdbtnSecuenc().isSelected()) {
			sb.append("\nAsignacion dorsales: secuencialmente");
		}
		else {
			sb.append("\nAsignacion dorsales: aleatoriamente");
		}
		if(view.getRdbtnAlReg().isSelected()) {
			sb.append("\nMomento asignacion dorsales: al registrarse");
		}
		else {
			sb.append("\nMomento asignacion dorsales: al finalizar plazo de inscripción");
		}
		
		System.out.println(sb);
		JOptionPane.showMessageDialog(null, sb.toString());
		
	}
	
	private Date createDeadLines(String id) {
		JPanel pnl = view.getPnlPlazosView();
		Date date = null;
		for(Component comp : pnl.getComponents()) {
			date =createDeadLine((JPanel)comp, id);
		}
		//retornamos el date del ultimo
		return date;
	}

	private Date createDeadLine(JPanel comp, String id) {
		DeadLineDto dto = new DeadLineDto();
		dto.idDeadLine = UUID.randomUUID().toString();
		JPanel pnlIni = (JPanel)comp.getComponent(0);
		JPanel pnlFin = (JPanel)comp.getComponent(1);
		
		JComboBox cb1= (JComboBox)pnlIni.getComponent(0);
		JComboBox cb2= (JComboBox)pnlIni.getComponent(1);
		JComboBox cb3= (JComboBox)pnlIni.getComponent(2);
		dto.setInitialDateAsDate(parseDate(cb1, cb2, cb3));
		cb1= (JComboBox)pnlFin.getComponent(0);
		cb2= (JComboBox)pnlFin.getComponent(1);
		cb3= (JComboBox)pnlFin.getComponent(2);
		dto.setFinalDateAsDate(parseDate(cb1, cb2, cb3));
		dto.fee = Double.parseDouble(((JTextField)comp.getComponent(2)).getText());
		dto.idCompetition = id;
		DeadLineModel.addDeadLine(dto);
		return dto.getFinalDateAsDate();
	}

	public Date parseDate(JComboBox cbd,JComboBox cbm, JComboBox cba) {
		 LocalDate local = LocalDate.of((int)cba.getSelectedItem(), cbm.getSelectedIndex()+1, (int)cbd.getSelectedItem());
		 ZoneId defaultZoneId = ZoneId.systemDefault();
		 Date date = Date.from(local.atStartOfDay(defaultZoneId).toInstant());
		//Date date = new Date( ((int)cba.getSelectedItem())+1900,cbm.getSelectedIndex(),(int)cbd.getSelectedItem());
		return date;
		
		
	}

	private void createCategories(String raceId) {
		createCategoriesFrom(view.getPnlFemView(), false, raceId);
		createCategoriesFrom(view.getPnlMascView(), true, raceId);
		
	}

	private void createCategoriesFrom(JPanel pnl, boolean isMale, String raceId) {
		CategoryDto category;
		for(int i = 0; i<pnl.getComponentCount(); i++) {
			category = createCategory((JPanel)pnl.getComponent(i));
			if(isMale)
				category.setSexMale();
			else
				category.setSexFemale();
			category.idCompetition = raceId;
			category.idCategory = UUID.randomUUID().toString();
			CategoryModel.addCategory(category);
		}
		
	}

	private CategoryDto createCategory(JPanel component) {
		CategoryDto category= new CategoryDto();
		JTextField text = (JTextField) component.getComponent(0);
		category.name = text.getText();
		JPanel pnl = (JPanel) component.getComponent(1);
		category.inital_Age = (int) ((JComboBox)pnl.getComponent(0)).getSelectedItem();
		category.final_Age = (int) ((JComboBox)pnl.getComponent(1)).getSelectedItem();		
		if(category.final_Age == MAXAGE) {
			category.final_Age = Integer.MAX_VALUE;
		}
		return category;
	}
}
