package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import business.race.RaceDto;
import model.inscription.InscriptionDto;
import model.inscription.InscriptionModel;
import ui.InsciptionsListFrame;
import ui.RegisterFrame;

public class InscriptionListController {

	
	
	InsciptionsListFrame vista;
	private RaceDto carrera;

	public InscriptionListController(InsciptionsListFrame vent, RaceDto carrera) {
		this.vista = vent;
		this.carrera = carrera;
	}
	public void loadRows() {
		InscriptionModel model = new InscriptionModel();
		
		
		List<InscriptionDto> list = model.getInscriptionList(carrera.id);
		
		for(InscriptionDto dto : list) {
			System.out.println(list.size());
			System.out.println(dto.name);
			System.out.println(dto.getInscriptionDate());
	}
		createList(list);
		
	}
	
	private void createList(List<InscriptionDto> list) {
		int rows = 1;
		Collections.sort(list);
		JPanel panel = vista.getPnlViewportCenter();
		for(InscriptionDto dto : list) {
			
			panel.setLayout(new GridLayout(rows++, 0, 0, 0));
			if(rows <10) {	//para que las filas no sean demasiado grandes y siga quedando bien
				panel.setLayout(new GridLayout(10, 0, 0, 0));
			}
			panel.add(createRow(dto));
		}
		
		
	}
	
	/**
	 * Metodo que crea una de las filas de la tabla
	 * @return
	 */
	private JPanel createRow(InscriptionDto inscription) {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(6, 0, 0, 0));
		JLabel lbl = new JLabel();
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		lbl.setText(inscription.dni);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		lbl.setText(inscription.name);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		lbl.setText(inscription.Category);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		lbl.setText(inscription.getInscriptionDate().toString());
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		lbl.setText(inscription.getInscriptionState().toString());
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		return panel;
	}
	public void exit() {
		vista.dispose();
		
	}
}
