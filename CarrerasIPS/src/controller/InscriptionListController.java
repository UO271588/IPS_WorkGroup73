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

import model.inscription.InscriptionDto;
import ui.InsciptionsListFrame;
import ui.RegisterFrame;

public class InscriptionListController {

	
	
	InsciptionsListFrame vista;

	public InscriptionListController(InsciptionsListFrame vent) {
		this.vista = vent;
	}
	public void loadRows() {
		InscriptionDto dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		InscriptionDto dto2 = new InscriptionDto();
		dto2.Category = "gola2";
		dto2.dni = "232";
		dto2.nombre = "pepe2";
		dto2.inscriptionDate = LocalDate.now();
		dto2.inscriptionState = "PAGADO2";
		
		List<InscriptionDto> list = new ArrayList<InscriptionDto>();
		list.add(dto);
		list.add(dto2);
		
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.of(1456, 11, 12);
		dto.inscriptionState = "pendiente";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "Pendiente";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		dto = new InscriptionDto();
		dto.Category = "gola";
		dto.dni = "23";
		dto.nombre = "pepe";
		dto.inscriptionDate = LocalDate.now();
		dto.inscriptionState = "PAGADO";
		list.add(dto);
		
		createList(list);
	}
	
	private void createList(List<InscriptionDto> list) {
		int rows = 1;
		Collections.sort(list);
		JPanel panel = vista.getPnlViewportCenter();
		for(InscriptionDto dto : list) {

			panel.setLayout(new GridLayout(rows++, 0, 0, 0));
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
		lbl.setText(inscription.nombre);
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
		lbl.setText(inscription.inscriptionDate.toString());
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		lbl.setText(inscription.inscriptionState.toString());
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		return panel;
	}
}
