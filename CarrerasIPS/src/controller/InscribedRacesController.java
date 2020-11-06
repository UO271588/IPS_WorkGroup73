package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import business.race.RaceDto;
import model.inscription.InscriptionDto;
import model.inscription.InscriptionModel;
import model.participant.ParticipantDto;
import model.participant.ParticipantModel;
import ui.InscribedRacesFrame;
import util.TimeUtil;

public class InscribedRacesController {

	private InscribedRacesFrame view;

	public InscribedRacesController(InscribedRacesFrame inscribedRacesFrame) {
		InscriptionModel cm = new InscriptionModel();
		view = inscribedRacesFrame;
	}

	public void initialize(String dni) {
		List<InscriptionDto> lista = InscriptionModel.getInscribedCompetitions(dni);
		Collections.sort(lista, Collections.reverseOrder());
		ParticipantDto part = ParticipantModel.getBasicData(dni);
		System.out.println(dni);
		formatPnlNorth(part);
		loadRows(lista);

	}

	private void loadRows(List<InscriptionDto> list) {
		int rows = 1;
		JPanel panel = view.getPnlViewportCenter();
		for(InscriptionDto dto : list) {

			panel.setLayout(new GridLayout(rows++, 0, 0, 0));
			if(rows <10) {	//para que las filas no sean demasiado grandes y siga quedando bien
				panel.setLayout(new GridLayout(10, 0, 0, 0));
			}
			panel.add(createRow(dto));
		}
		
	}

	private Component createRow(InscriptionDto dto) {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(6, 0, 0, 0));
		JLabel lbl = new JLabel();
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		lbl.setText(dto.name);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		
		lbl = new JLabel();
		lbl.setText("" +dto.getInscriptionState());
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		
		
		lbl = new JLabel();
		lbl.setText(dto.inscriptionDate);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		return panel;
	}

	private void formatPnlNorth(ParticipantDto part) {
		// TODO Auto-generated method stub
		view.getLblName().setText(part.name + " " + part.surname);
		view.getLblMail().setText(part.email);
		view.getLblTitle().setBorder(new EmptyBorder(0, 0, 0, view.getPnlData().getWidth()));


	}

}
