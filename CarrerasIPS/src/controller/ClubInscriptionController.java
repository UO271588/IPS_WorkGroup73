package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import business.race.RaceDto;
import dbAccess.CompetitionsAccess;
import model.inscription.InscriptionModel;
import model.participant.ParticipantDtoPojo;
import model.participant.ParticipantModel;
import ui.ClubInscriptionFrame;
import util.JustificanteClub;
import util.MyFile;

public class ClubInscriptionController {

	private ClubInscriptionFrame view;
	private JFileChooser jfc;
	private File selectedFile;
	private Map<String, ParticipantDtoPojo> enLista;
	private RaceDto race;;

	public ClubInscriptionController(ClubInscriptionFrame clubInscriptionFrame, RaceDto race) {
		this.race = race;
		this.view = clubInscriptionFrame;
		enLista = new HashMap();
	}

	public boolean checkExist(String email) {
		return !ParticipantModel.checkMail(email);
	}

	public void addToViewport(JPanel pnlViewPort, String email) {
		ParticipantDtoPojo dto = ParticipantModel.getByEmail(email);
		if(enLista.containsKey(dto.email)){
			JOptionPane.showMessageDialog(null, "el Participante introducido ya esta en la lista a añadir");
			return;
		}
		JPanel row = createRowFor(dto, pnlViewPort);
		pnlViewPort.add(row);
		pnlViewPort.repaint();
		pnlViewPort.revalidate();
	}
	
	public void cargarArchivo() {
		int respuesta = getSelector().showOpenDialog(null);
		if (respuesta == JFileChooser.APPROVE_OPTION);
			for (int i=0; i<jfc.getSelectedFiles().length;i++) {
				File seleccionado = jfc.getSelectedFiles()[i];
				//modelolistLibrary.addElement(new MyFile(jfc.getSelectedFiles()[i]));
					//frame.getBtnAbrir().setEnabled(false);
					selectedFile = seleccionado;
					view.getTextNombreFichero().setText(selectedFile.getName());
			}
			
	}
	public void cargarDatosFichero() {
		JPanel pnl = view.getPnlViewPort();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(selectedFile));
		String line;
		while((line = br.readLine()) != null) {
			addToViewport(pnl, line);
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	

	private JPanel createRowFor(ParticipantDtoPojo dto, JPanel pnlViewPort) {
		JPanel pnlRow = new JPanel();
		pnlRow.setLayout(new BorderLayout(0, 0));
		JPanel pnlName = new JPanel();
		pnlName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlName.setLayout(new BorderLayout(0, 0));

		JLabel lbName = new JLabel(dto.name);
		lbName.setHorizontalAlignment(SwingConstants.CENTER);
		pnlName.add(lbName, BorderLayout.CENTER);
		
		JPanel pnlMail = new JPanel();
		pnlMail.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlMail.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lbMail = new JLabel(dto.email);
		lbMail.setHorizontalAlignment(SwingConstants.CENTER);
		pnlMail.add(lbMail, BorderLayout.CENTER);
		
		
		JPanel pnlDate = new JPanel();
		pnlDate.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlDate.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lblDate = new JLabel(dto.birthDate);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDate.add(lblDate, BorderLayout.CENTER);
		
		JPanel pnlGrid = new JPanel();
		pnlGrid.setLayout(new GridLayout(0, 3, 0, 0));
		pnlGrid.add(pnlName);
		pnlGrid.add(pnlMail);
		pnlGrid.add(pnlDate);
		
		JPanel pnlBtn= new JPanel();
		pnlBtn.setLayout(new BorderLayout(0, 0));
		pnlBtn.add(createBtnCanc(pnlViewPort, pnlRow), BorderLayout.CENTER);
		
		pnlRow.add(pnlGrid, BorderLayout.CENTER);
		pnlRow.add(pnlBtn, BorderLayout.EAST);
		enLista.put(dto.email, dto);
		return pnlRow;
	}
	
	private JButton createBtnCanc(JPanel pnlViewPort, JPanel row) {
		JButton btn = new JButton("X");
		btn.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn.setBackground(Color.RED);		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlViewPort.remove(row);
				String email = ((JLabel)((JPanel)((JPanel)row.getComponent(0)).getComponent(1)).getComponent(0)).getText();
				enLista.remove( ((JLabel)((JPanel)((JPanel)row.getComponent(0)).getComponent(1)).getComponent(0)).getText());
				pnlViewPort.repaint();
				pnlViewPort.revalidate();
			}
		});
		return btn;
	}

	public void confirm(String nombreClub) {
		JustificanteClub jc = new JustificanteClub(nombreClub);
		InscriptionModel model = new InscriptionModel(race);
		for(String mail : enLista.keySet()) {
			if(InscriptionModel.existeYaInscripcion(InscriptionModel.getDni(mail),	race.nombre)) {
				jc.addDenegado(enLista.get(mail), "Ya esta inscrito en esta competicion");
				continue;
			}
			if(model.categoria(mail, race.nombre).isBlank()) {
				jc.addDenegado(enLista.get(mail), "No hay ninguna categoria para la edad del participante");
				continue;
			}
			if(!CompetitionsAccess.checkSlots(race.id)) {
				jc.addDenegado(enLista.get(mail), "No hay plazas libres para la competicion");
				continue;
			}
			incribe(enLista.get(mail));
			jc.addAceptado(enLista.get(mail), model.categoria(mail, race.nombre));
		}
		System.out.println(jc.toString());
		JOptionPane.showMessageDialog(null, jc.toString());
		
	}

	private void incribe(ParticipantDtoPojo dto) {
		InscriptionModel model = new InscriptionModel(race);
		model.insertParticipant(dto, race.id,  race.nombre);
		
	}

}
